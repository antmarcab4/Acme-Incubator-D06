
package acme.features.entrepreneur.activity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	private EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result = false;
		boolean aux = false;
		boolean aux2 = false;
		boolean aux3 = false;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Principal principal;

		principal = request.getPrincipal();
		investmentRoundId = request.getModel().getInteger("investmentRoundId");
		investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);
		aux2 = !investmentRound.isFinalMode();
		aux3 = investmentRound.getEntrepreneur().getUserAccount().getId() == principal.getAccountId();

		if (request.getPrincipal().hasRole(Entrepreneur.class)) {
			aux = true;
		}

		result = aux && aux2 && aux3;

		return result;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("investmentRoundId", request.getModel().getInteger("investmentRoundId"));

		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;

		Activity result;
		result = new Activity();

		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		InvestmentRound investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);

		result.setInvestmentRound(investmentRound);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date fecha;
		InvestmentRound invstRound;

		if (!errors.hasErrors("title")) {
			Parameter p = this.repository.findParameters();
			String spamWords = p.getSpamwords();
			Double spamThreshold = p.getSpamthreshold();
			String title = entity.getTitle().toLowerCase();
			errors.state(request, !parameterMethods.isSpam(title, spamWords, spamThreshold), "title", "entrepreneur.activity.form.error.spamTitle");
		}

		if (!errors.hasErrors("budget")) {
			Money budget = entity.getBudget();
			Double sum = this.repository.sumBudgetsByInvestmentRoundId(request.getModel().getInteger("investmentRoundId"));
			invstRound = this.repository.findOneInvestmentRoundById(request.getModel().getInteger("investmentRoundId"));

			if (sum == null) {
				sum = 0.;
			}
			errors.state(request, sum + budget.getAmount() <= invstRound.getAmount().getAmount(), "budget", "entrepreneur.activity.budget.biggerThanAmount.error");
			errors.state(request, budget.getCurrency().contentEquals("EUR") || budget.getCurrency().contentEquals("€"), "budget", "entrepreneur.activity.budget.currency.error");
		}

		if (!errors.hasErrors("start")) {
			calendar = new GregorianCalendar();
			fecha = calendar.getTime();
			errors.state(request, entity.getStart().after(fecha), "start", "entrepreneur.activity.start.error.dateInPast");
		}

		if (!errors.hasErrors("end")) {
			calendar = new GregorianCalendar();
			fecha = calendar.getTime();
			errors.state(request, entity.getEnd().after(fecha), "end", "entrepreneur.activity.start.error.dateInPast");
			errors.state(request, entity.getEnd().after(entity.getStart()), "end", "entrepreneur.activity.start.error.incorrectEnd");
		}

	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
