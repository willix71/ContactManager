package w.contactmgr.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import w.contactmgr.exception.ConflictException;
import w.contactmgr.model.Contact;

@Service
public class ContactStore extends SimpleStore<Contact, Long> {

	public ContactStore() {
		save(1L, new Contact(1,"william"));
		save(2L, new Contact(2,"ralph"));
		save(3L, new Contact(3,"marc"));
	}
	
	@Override
	public Collection<Contact> loadAll() {
		return super.loadAll();
	}

	@Override
	public Contact load(Long id) {
		return super.load(id).klone();
	}

	@Override
	public void save(Long id, Contact t) {
		if (id == null) {
			id = System.currentTimeMillis();
			t.setId(id);
		} else if (!id.equals(t.getId())) {
			throw new ConflictException();
		}
		super.save(id, t.klone());
	}

	
}
