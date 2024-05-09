package model.services;

import java.util.List;

public interface JPAServiceInterface<E> {
	List<E> findAll();
}
