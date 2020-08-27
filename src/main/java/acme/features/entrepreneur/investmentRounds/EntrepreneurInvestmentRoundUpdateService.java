
package acme.features.entrepreneur.investmentRounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.parameters.Parameter;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && !investmentRound.isFinalMode();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "amount", "round", "creation", "optionalLink", "ticker", "finalMode");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;

		int investmentRoundId = request.getModel().getInteger("id");

		investmentRound = this.repository.findOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();

		if (!errors.hasErrors("title")) {
			Parameter p = this.repository.findParameters();
			String spamWords = p.getSpamwords();
			Double spamThreshold = p.getSpamthreshold();
			String description = entity.getTitle();
			errors.state(request, !parameterMethods.isSpam(description, spamWords, spamThreshold), "title", "entrepreneur.investmentRound.form.error.spamDescription");
		}

		if (entity.isFinalMode()) {
			Collection<Activity> activities = this.repository.findManyActivitiesByInvestmentRoundId(investmentRoundId);

			if (!activities.isEmpty()) {
				Double amount = entity.getAmount().getAmount();
				Double sum = this.repository.sumBudgetsByInvestmentRoundId(investmentRoundId);
				errors.state(request, Double.compare(amount, sum) == 0, "finalMode", "entrepreneur.investmentRound.form.error.budgetsNotSumAmount");
			} else {
				errors.state(request, !activities.isEmpty(), "finalMode", "entrepreneur.investmentRound.form.error.budgetsIsNull");
			}

		}

		if (!errors.hasErrors("description")) {
			Parameter p = this.repository.findParameters();
			String spamWords = p.getSpamwords();
			Double spamThreshold = p.getSpamthreshold();
			String description = entity.getDescription();
			errors.state(request, !parameterMethods.isSpam(description, spamWords, spamThreshold), "description", "entrepreneur.investmentRound.form.error.spamDescription");
		}

		if (!errors.hasErrors("amount")) {
			Money amount = entity.getAmount();
			errors.state(request, amount.getCurrency().contentEquals("EUR") || amount.getCurrency().contentEquals("€"), "amount", "entrepreneur.investmentRound.amount.currency.error");
		}

		if (!errors.hasErrors("ticker")) {

			Collection<InvestmentRound> investmentRounds = this.repository.findAllInvestmentRounds();
			String ticker = entity.getTicker();
			boolean aux = investmentRounds.stream().map(x -> x.getTicker()).anyMatch(x -> x.equals(ticker));
			errors.state(request, !aux || ticker.equals(request.getModel().getString("ticker")), "ticker", "entrepreneur.investmentRound.ticker.tickerInUse");

			boolean isFirstOk, isSecondOk;
			List<String> tickerParts = new ArrayList<>();
			tickerParts = Arrays.asList(entity.getTicker().split("-"));
			Integer lenght = entity.getRound().toString().length();

			if (lenght > 2) {
				isFirstOk = tickerParts.stream().anyMatch(x -> entrepreneur.getSector().toString().substring(0, 3).trim().equalsIgnoreCase(x.trim()));
				errors.state(request, isFirstOk, "ticker", "entrepreneur.investmentRound.ticker.activity.error");
			} else if (lenght == 2) {
				isFirstOk = tickerParts.stream().anyMatch(x -> entrepreneur.getSector().toString().substring(0, 2).concat("X").equalsIgnoreCase(x));
				errors.state(request, isFirstOk, "ticker", "entrepreneur.investmentRound.ticker.activity.error");
			} else {
				isFirstOk = tickerParts.stream().anyMatch(x -> entrepreneur.getSector().toString().substring(0, 1).concat("XX").equalsIgnoreCase(x));
				errors.state(request, isFirstOk, "ticker", "entrepreneur.investmentRound.ticker.activity.error");
			}

			isFirstOk = tickerParts.stream().anyMatch(x -> StringUtils.isAllUpperCase(x.trim()));
			errors.state(request, isFirstOk, "ticker", "entrepreneur.investmentRound.ticker.uppercase.error");

			isSecondOk = tickerParts.stream().anyMatch(x -> entity.getCreation().toString().substring(entity.getCreation().toString().length() - 2, entity.getCreation().toString().length()).equals(x.trim()));
			errors.state(request, isSecondOk, "ticker", "entrepreneur.investmentRound.ticker.year.error");

		}
	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
