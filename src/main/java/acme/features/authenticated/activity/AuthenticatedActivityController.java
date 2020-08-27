
package acme.features.authenticated.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.activities.Activity;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/activity/")
public class AuthenticatedActivityController extends AbstractController<Authenticated, Activity> {

	@Autowired
	private AuthenticatedActivityShowService				showservice;
	@Autowired
	private AuthenticatedActivityListFromInvestmentService	listFromInvestmentService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showservice);
		super.addCustomCommand(CustomCommand.LIST_FROM_INVESTMENT, BasicCommand.LIST, this.listFromInvestmentService);
		;
	}

}
