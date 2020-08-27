
package acme.features.authenticated.overtures;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedOvertureListService implements AbstractListService<Authenticated, Overture> {

	@Autowired
	AuthenticatedOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "minMoney", "maxMoney");

	}

	@Override
	public Collection<Overture> findMany(final Request<Overture> request) {

		assert request != null;
		Collection<Overture> result;

		Calendar aux = Calendar.getInstance();
		Date date2 = new Date();
		aux.setTime(date2);

		result = this.repository.findManyAllActives(aux.getTime());
		return result;

	}

}
