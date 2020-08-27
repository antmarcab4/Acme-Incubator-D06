
package acme.features.investor.investmentRounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorInvestmentRoundShowService2 implements AbstractShowService<Investor, InvestmentRound> {

	@Autowired
	InvestorInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "amount", "round", "creation", "optionalLink", "ticker");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		// TODO Auto-generated method stub
		assert request != null;

		InvestmentRound result;
		Application aux;
		int id;

		id = request.getModel().getInteger("applicationId");
		aux = this.repository.findOneApplicationById(id);
		result = aux.getIRound();
		return result;
	}

}
