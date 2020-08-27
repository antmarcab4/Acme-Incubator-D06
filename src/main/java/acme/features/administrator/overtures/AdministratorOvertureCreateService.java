
package acme.features.administrator.overtures;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

	@Autowired
	private AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "minMoney", "maxMoney", "email");

	}

	@Override
	public Overture instantiate(final Request<Overture> request) {

		assert request != null;

		Overture result;
		result = new Overture();
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.overture.deadline.error");
		}

		if (!errors.hasErrors("minMoney")) {
			Money min = entity.getMinMoney();
			errors.state(request, min.getCurrency().contentEquals("EUR") || min.getCurrency().contentEquals("€"), "minMoney", "administrator.overture.minMoney.currency.error");
		}

		if (!errors.hasErrors("maxMoney")) {
			Money max = entity.getMaxMoney();
			errors.state(request, max.getCurrency().contentEquals("EUR") || max.getCurrency().contentEquals("€"), "maxMoney", "administrator.overture.maxMoney.currency.error");
		}

		if (!errors.hasErrors("minMoney") && !errors.hasErrors("maxMoney")) {
			Money min = entity.getMinMoney();
			Money max = entity.getMaxMoney();
			errors.state(request, max.getAmount() >= min.getAmount(), "minMoney", "administrator.overture.moneyRange.error");
		}

	}

	@Override
	public void create(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		this.repository.save(entity);

	}

}
