
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.parameters.Parameter;
import acme.entities.roles.Entrepreneur;
import acme.features.investor.application.ParameterMethods;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		int applicationId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;
		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		entrepreneur = application.getIRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getId() == principal.getActiveRoleId();
		result = result && application.getStatus().equals("pending");
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "creationMoment", "statement", "moneyOffer", "investor", "iRound");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;

	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean NotBlankJustification;
		NotBlankJustification = false;

		//Check if rejected
		if (request.getModel().getString("status").equals("rejected")) {
			NotBlankJustification = !request.getModel().getString("justification").trim().equals("");
			errors.state(request, NotBlankJustification, "justification", "entrepreneur.application.reject.form.error.invalidjustification");

		}
		//		//Check max money amount
		//		if (request.getModel().getString("status").equals("accepted")) {
		//			int iRoundId = entity.getIRound().getId();
		//			Collection<Application> apps = this.repository.findManyByInvestmentRound(iRoundId);
		//			Double totalAmount = entity.getMoneyOffer().getAmount();
		//			for (Application app : apps) {
		//				if (app.getStatus().equals("accepted")) {
		//					totalAmount += app.getMoneyOffer().getAmount();
		//				}
		//			}
		//			boolean maxAmountReached = totalAmount <= entity.getIRound().getAmount().getAmount();
		//			System.out.println("max iRound amount: " + entity.getIRound().getAmount().getAmount());
		//			System.out.println("total amount: " + totalAmount);
		//			errors.state(request, maxAmountReached, "status", "entrepreneur.application.accept.form.error.tooMuchMoney");
		//
		//			//			totalAmount <= acceptedAmount
		//		}

		//check spam
		if (!errors.hasErrors("justification")) {
			Parameter p = this.repository.findParameters();
			String spamWords = p.getSpamwords();
			Double spamThreshold = p.getSpamthreshold();
			String justification = entity.getJustification();
			errors.state(request, !ParameterMethods.isSpam(justification, spamWords, spamThreshold), "justification", "investor.application.form.error.spamJustification");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
