
package acme.features.authenticated.technologyRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/technology-record/")
public class AuthenticatedTechnologyRecordController extends AbstractController<Authenticated, TechnologyRecord> {

	@Autowired
	private AuthenticatedTechnologyRecordListService	listservice;
	@Autowired
	private AuthenticatedTechnologyRecordShowService	showservice;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listservice);
		super.addBasicCommand(BasicCommand.SHOW, this.showservice);
	}

}
