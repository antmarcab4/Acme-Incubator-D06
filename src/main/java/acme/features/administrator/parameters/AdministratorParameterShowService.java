
package acme.features.administrator.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.parameters.Parameter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorParameterShowService implements AbstractShowService<Administrator, Parameter> {

	@Autowired
	private AdministratorParameterRepository repository;


	@Override
	public boolean authorise(final Request<Parameter> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Parameter> request, final Parameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamwords", "spamthreshold", "activitySector");

	}

	@Override
	public Parameter findOne(final Request<Parameter> request) {
		assert request != null;

		Parameter result;
		result = this.repository.findManyAll().stream().findFirst().get();
		return result;
	}

}
