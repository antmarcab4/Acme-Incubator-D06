
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	private AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "objetivoRookie", "premioRookie", "objetivoAverage", "premioAverage", "objetivoExpert", "premioExpert");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean isValidB, isValidS, isValidG;
		Calendar calendar;
		Date minimumDeadline;
		isValidB = false;
		isValidS = false;
		isValidG = false;

		if (!errors.hasErrors("premioRookie")) {
			isValidB = entity.getPremioRookie().getCurrency().contentEquals("€") || entity.getPremioRookie().getCurrency().contentEquals("EUR");
			errors.state(request, isValidB, "premioRookie", "administrator.challenge.form.error.invalidmoney");

		}
		if (!errors.hasErrors("premioAverage")) {
			isValidS = entity.getPremioAverage().getCurrency().contentEquals("€") || entity.getPremioAverage().getCurrency().contentEquals("EUR");
			errors.state(request, isValidS, "premioAverage", "administrator.challenge.form.error.invalidmoney");

		}
		if (!errors.hasErrors("premioExpert")) {
			isValidG = entity.getPremioExpert().getCurrency().contentEquals("€") || entity.getPremioExpert().getCurrency().contentEquals("EUR");
			errors.state(request, isValidG, "premioExpert", "administrator.challenge.form.error.invalidmoney");

		}
		if (isValidB && isValidS) {
			if (!errors.hasErrors("premioRookie") && !errors.hasErrors("premioAverage")) {
				errors.state(request, entity.getPremioRookie().getAmount() < entity.getPremioAverage().getAmount(), "premioAverage", "administrator.challenge.form.error.invalidreward");
			}
		}
		if (isValidS && isValidG) {
			if (!errors.hasErrors("premioAverage") && !errors.hasErrors("premioExpert")) {
				errors.state(request, entity.getPremioAverage().getAmount() < entity.getPremioExpert().getAmount(), "premioExpert", "administrator.challenge.form.error.invalidreward");
			}
		}
		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 30);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.challenge.form.error.invaliddeadline");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
