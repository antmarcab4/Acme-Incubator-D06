
package acme.features.investor.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.parameters.Parameter;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Investor.class);
		return result;

	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "status", "iRound", "investor");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("iRoundId", request.getModel().getInteger("iRoundId"));

		request.unbind(entity, model, "ticker", "statement", "moneyOffer");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;
		result = new Application();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		InvestmentRound iRound;
		int iRoundId;
		iRoundId = request.getModel().getInteger("iRoundId");
		iRound = this.repository.findInvestmentRoundById(iRoundId);
		result.setIRound(iRound);

		String status = "pending";
		result.setStatus(status);

		Investor investor;
		Integer investorId;
		investorId = request.getPrincipal().getActiveRoleId();
		investor = this.repository.findInvestorById(investorId);
		result.setInvestor(investor);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Entrepreneur entrepreneur = entity.getIRound().getEntrepreneur();

		//Check the amount of money that is going to be invested
		if (!errors.hasErrors("moneyOffer")) {
			Double mny = entity.getMoneyOffer().getAmount();
			Double max = entity.getIRound().getAmount().getAmount();
			boolean tooMuchMoney = max >= mny;
			errors.state(request, tooMuchMoney, "moneyOffer", "investor.application.moneyOffer.error.maxSurpassed");
		}

		//Check if money is in euros
		if (!errors.hasErrors("moneyOffer")) {
			Money mon = entity.getMoneyOffer();
			errors.state(request, mon.getCurrency().contentEquals("EUR") || mon.getCurrency().contentEquals("€"), "moneyOffer", "investor.application.moneyOffer.currency.error");
		}

		//Check ticker
		if (!errors.hasErrors("ticker")) {

			Collection<Application> aplications = this.repository.findAllApplications();
			String reference = entity.getTicker();
			boolean aux = aplications.stream().map(x -> x.getTicker()).anyMatch(x -> x.equals(reference));
			errors.state(request, !aux, "ticker", "investor.application.ticker.error.tickerInUse");

			boolean isFirstOk, isSecondOk;
			List<String> tickerParts = new ArrayList<>();
			tickerParts = Arrays.asList(entity.getTicker().split("-"));
			Integer lenght = entity.getIRound().toString().length();

			if (lenght > 2) {
				isFirstOk = tickerParts.stream().anyMatch(x -> entrepreneur.getSector().toString().substring(0, 3).trim().equalsIgnoreCase(x.trim()));
				errors.state(request, isFirstOk, "ticker", "investor.application.ticker.error.activity");
			} else if (lenght == 2) {
				isFirstOk = tickerParts.stream().anyMatch(x -> entrepreneur.getSector().toString().substring(0, 2).concat("X").equalsIgnoreCase(x));
				errors.state(request, isFirstOk, "ticker", "investor.application.ticker.error.activity");
			} else {
				isFirstOk = tickerParts.stream().anyMatch(x -> entrepreneur.getSector().toString().substring(0, 1).concat("XX").equalsIgnoreCase(x));
				errors.state(request, isFirstOk, "ticker", "investor.application.ticker.error.activity");
			}

			isFirstOk = tickerParts.stream().anyMatch(x -> StringUtils.isAllUpperCase(x.trim()));
			errors.state(request, isFirstOk, "ticker", "investor.application.ticker.error.uppercase");

			isSecondOk = tickerParts.stream().anyMatch(x -> entity.getCreationMoment().toString().substring(entity.getCreationMoment().toString().length() - 2, entity.getCreationMoment().toString().length()).equals(x.trim()));
			errors.state(request, isSecondOk, "ticker", "investor.application.ticker.error.year");

		}

		//Check spam
		if (!errors.hasErrors("statement")) {
			Parameter p = this.repository.findParameters();
			String spamWords = p.getSpamwords();
			Double spamThreshold = p.getSpamthreshold();
			String statement = entity.getStatement().toLowerCase();
			errors.state(request, !ParameterMethods.isSpam(statement, spamWords, spamThreshold), "statement", "investor.application.form.error.spamStatement");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
