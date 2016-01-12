package w.contactmgr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import w.contactmgr.model.Contact;
import w.contactmgr.service.IStore;

@Controller
@RequestMapping(value = "/contact")
public class ContactController extends AbstractController<Contact, Long> {

	@Autowired
	public ContactController(IStore<Contact, Long> store) {
		super(store);
	}
}
