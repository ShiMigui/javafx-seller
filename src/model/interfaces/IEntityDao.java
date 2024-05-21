package model.interfaces;

import java.sql.ResultSet;
import java.util.List;

public interface IEntityDao<E> {
    E convert(ResultSet rs);

    E findById(Integer id);

    void insert(E obj);

    void update(E obj);

    List<E> findAll();

    void removeById(Integer id);
}
