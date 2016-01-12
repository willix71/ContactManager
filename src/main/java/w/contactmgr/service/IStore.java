package w.contactmgr.service;

import java.util.Collection;

public interface IStore<T, ID> {
	Collection<T> loadAll();
	T load(ID id);
	void save(ID id, T t);
	void delete(ID id);
}
