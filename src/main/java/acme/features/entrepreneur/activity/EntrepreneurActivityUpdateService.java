
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
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		boolean result;
		//int investmentRoundId;
		int activityId;
		Activity activity;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		activityId = request.getModel().getInteger("id");
		activity = this.repository.findOneById(activityId);
		investmentRound = activity.getInvestmentRound();
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

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

		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

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

		if (entity.getInvestmentRound().isFinalMode()) {

			errors.state(request, !entity.getInvestmentRound().isFinalMode(), "budget", "entrepreneur.activity.form.error.finalMode");

		} else {

			if (!errors.hasErrors("title")) {
				Parameter p = this.repository.findParameters();
				String spamWords = p.getSpamwords();
				Double spamThreshold = p.getSpamthreshold();
				String title = entity.getTitle();
				errors.state(request, !parameterMethods.isSpam(title, spamWords, spamThreshold), "title", "entrepreneur.activity.form.error.spamTitle");
			}

			if (!errors.hasErrors("budget")) {
				Money budget = entity.getBudget();
				Double sum = this.repository.sumBudgetsByInvestmentRoundId(entity.getInvestmentRound().getId());
				invstRound = this.repository.findOneInvestmentRoundById(entity.getInvestmentRound().getId());

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
	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
