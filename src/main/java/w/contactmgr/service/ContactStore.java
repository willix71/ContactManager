package w.contactmgr.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import w.contactmgr.exception.ConflictException;
import w.contactmgr.model.Contact;

@Service
public class ContactStore implements IStore<Contact, Long> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactStore.class);

	private Resource storeResource = new ClassPathResource("contacts.json");
	
	private Map<Long, Contact> store;

	private ObjectMapper mapper = new ObjectMapper();
	
	protected Map<Long, Contact> loadStore() {
		LOGGER.info("Loading Contacts...");
		
		try {
			Collection<?> contacts = mapper.readValue(storeResource.getInputStream(), Collection.class);
			
			Map<Long, Contact> lstore = new HashMap<Long, Contact>();
			for (Object o : contacts) {
				Contact c = mapper.convertValue(o, Contact.class);
				lstore.put(c.getId(), c);
			}
			
			LOGGER.info("Loaded {} contacts", contacts.size());
			
			return lstore;
			
		} catch (Exception e) {
			throw new RuntimeException("Failed to load contact store", e);
		}
	};
	
	protected Map<Long, Contact> getStore() {
		if (store == null) {
			store = loadStore();
		}
		return store;
	}
		
	@Override
	public Collection<Contact> loadAll() {
		// todo klone each value
		return getStore().values();
	}

	@Override
	public Contact load(Long id) {
		Contact c = getStore().get(id);
		if (c == null)
			return null;
		else
			return c.klone();
	}

	@Override
	public void save(Long id, Contact t) {
		if (id == null) {
			id = System.currentTimeMillis();
			t.setId(id);
		} else if (!id.equals(t.getId())) {
			throw new ConflictException();
		}

		getStore().put(id, t.klone());
	}

	@Override
	public void delete(Long id) {
		getStore().remove(id);
	}

}
