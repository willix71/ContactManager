package w.contactmgr.web;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
//	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void patch(@PathVariable("id") final ID id, @RequestBody final Map<String,?> resource, final HttpServletResponse response) {
//		Preconditions.checkNotNull(resource);
//		
//		T entity = service.load(id);
//		
//		Object entityId = PersistenceUtils.getIdValue(entity);
//		
//		RestPreconditions.checkFound(entity);
//		
//		// we need a new mapper else old mapping might corrupt our current mappings.
//		ModelMapper m = WebConfig.newModelMapper();
//		m.map(resource, entity);
//
//		// make sure we did not overwrite the id
//		Object entityId2 = PersistenceUtils.getIdValue(entity);
//		if (!entityId.equals(entityId2)) {
//			new ConflictException("Ids", entityId, entityId2);
//		}
//		
//		if (LOGGER.isDebugEnabled()) {
//			StringBuilder sb = new StringBuilder("Mappings\n");
//			for(TypeMap<?,?> tm: m.getTypeMaps()) {
//				sb.append("\t").append(tm).append("=").append(tm.getMappings()).append("\n");
//			}
//			LOGGER.debug(sb.toString());
//		}
//		
//		service.save(entity);
//	}
	
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
