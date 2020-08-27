
package acme.features.investor.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.activities.Activity;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/activity/")
public class InvestorActivityController extends AbstractController<Investor, Activity> {

	@Autowired
	private InvestorActivityShowService					showservice;
	@Autowired
	private InvestorActivityListFromInvestmentService	listFromInvestmentService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showservice);
		super.addCustomCommand(CustomCommand.LIST_FROM_INVESTMENT, BasicCommand.LIST, this.listFromInvestmentService);
	}

}
