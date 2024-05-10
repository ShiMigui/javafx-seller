package model.interfaces;

import java.util.List;

public interface IEntityService<E> {
	List<E> findAll();
}
