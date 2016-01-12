package w.contactmgr.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleStore<T, ID> implements IStore<T, ID> {

	public Map<ID,T> store = new HashMap<>();
	
	public Collection<T> loadAll() {
		return store.values();
	}

	public T load(ID id) {
		return store.get(id);
	}

	public void save(ID id, T t) {
		store.put(id, t);
	}

	public void delete(ID id) {
		store.remove(id);		
	}

}
