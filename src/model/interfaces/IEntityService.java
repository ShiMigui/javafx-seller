package model.interfaces;

import java.util.List;

public interface IEntityService<E extends IEntity> {
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

    default void insertOrUpdate(E obj) {
	if (obj.getId() != null)
	    update(obj);
	else
	    insert(obj);
    }

    default void remove(E obj) {
	if (obj == null)
	    throw new NullPointerException("Id was null!");

	remove(obj.getId());
    }

    default void remove(Integer id) {
	if (id == null)
	    throw new NullPointerException("Id was null!");

	getEntityDao().remove(id);
    }
}
