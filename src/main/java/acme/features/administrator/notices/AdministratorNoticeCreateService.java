
package acme.features.administrator.notices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDateTime");

	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "header", "title", "body", "deadline", "optionalLinks");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("checkbox", "false");
		} else {
			request.transfer(model, "checkbox");
		}

	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		// TODO Auto-generated method stub
		Notice result;
		Date creationMoment;
		result = new Notice();
		creationMoment = new Date();

		result.setCreationDateTime(creationMoment);

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;
		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.notice.error.incorrectDeadline");
		}

		isAccepted = request.getModel().getBoolean("checkbox");
		errors.state(request, isAccepted, "checkbox", "administrator.notice.error.confirmCreate");

		if (!errors.hasErrors("optionalLinks") && !entity.getOptionalLinks().isEmpty()) {
			boolean isURLs;
			List<String> optLinks = new ArrayList<>();
			optLinks = Arrays.asList(entity.getOptionalLinks().split(","));
			isURLs = optLinks.stream().allMatch(x -> UrlUtils.isAbsoluteUrl(x.trim()));
			errors.state(request, isURLs, "optionalLinks", "administrator.notice.error.NotUrls");
		}

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		// TODO Auto-generated method stub
		Date creationMoment;

		//		creationMoment = new Date();
		//
		//		entity.setCreationDateTime(creationMoment);
		this.repository.save(entity);
	}
}
