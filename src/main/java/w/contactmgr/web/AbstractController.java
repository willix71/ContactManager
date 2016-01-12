package w.contactmgr.web;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import w.contactmgr.model.Contact;
import w.contactmgr.service.IStore;

public abstract class AbstractController<T, ID> {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	protected final IStore<T, ID> store;

	public AbstractController(IStore<T, ID> store) {
		LOGGER.info("Controller initialized");
		this.store = store;
	}
	
	/**
	 * curl -i http://localhost:8080/spring/rest/contact/1
	 * 
	 * curl -i --header "Accept: application/json" http://localhost:8880/ContactManager/rest/contact/1
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public T findById(@PathVariable("id") final ID id) {
		return store.load(id);
	}

	/**
	 * curl -i http://localhost:8880/ContactManager/rest/contact
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<T> findAll() {
		return store.loadAll();
	}

	/**
	 * curl -iH "Content-Type: application/json" -X POST -d '{"name":"henri"}' http://localhost:8880/ContactManager/rest/contact
	 *
	 * or save the json to a file and replace it by @filename
	 * 
	 * or -d param=value&param2=value2
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody final T t) {
		store.save(null,t);
	}

	/**
	 * curl -iH "Content-Type: application/json" -X PUT -d '{"id":"1","name":"willy123"}' http://localhost:8880/ContactManager/rest/contact/1
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") final ID id, @RequestBody final T t) {
		store.save(id, t);
	}

	/**
	 * curl -iH "Content-Type: application/json" -X PATCH -d '{"name":"willy123"}' http://localhost:8880/ContactManager/rest/contact/1
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@PathVariable("id") final ID id, @RequestBody final Map<String,?> values) {		
		LOGGER.info("patching <{}> with {}", id, values);
		
		T t = store.load(id);
		 
		for (String key: values.keySet()) {
			try {
				// TODO generic
				Method m = Contact.class.getMethod("set" +key.substring(0,1).toUpperCase()+key.substring(1) , String.class);
				m.invoke(t, values.get(key));
			} catch(Exception e) {
				throw new IllegalArgumentException("Can't set value for " + key, e);
			}
		}
		
		store.save(id, t);
	}
	
	/**
	 * curl -i -X DELETE http://localhost:8880/ContactManager/rest/contact/1
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") final ID id) {
		store.delete(id);
	}
}
