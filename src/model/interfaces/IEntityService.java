package model.interfaces;

import java.util.List;

public interface IEntityService<E> {
	IEntityDao<E> getEntityDao();

	default List<E> findAll() {
		return getEntityDao().findAll();
	}

	default E findById(Integer id) {
		return getEntityDao().findById(id);
	}

	default void update(E obj) {
		getEntityDao().update(obj);
	}

	default void insert(E obj) {
		getEntityDao().insert(obj);
	}
}
