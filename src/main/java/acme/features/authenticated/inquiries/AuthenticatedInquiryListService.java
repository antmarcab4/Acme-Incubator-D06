
package acme.features.authenticated.inquiries;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInquiryListService implements AbstractListService<Authenticated, Inquiry> {

	@Autowired
	AuthenticatedInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "minMoney", "maxMoney");
	}

	@Override
	public Collection<Inquiry> findMany(final Request<Inquiry> request) {
		assert request != null;
		Collection<Inquiry> result;

		Calendar aux = Calendar.getInstance();
		Date date2 = new Date();
		aux.setTime(date2);

		result = this.repository.findManyInquiresActive(aux.getTime());

		return result;
	}

}
