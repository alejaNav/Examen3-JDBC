package daos;

import java.util.List;

public interface Dao<T> {

    List<T> getall();

    T getById(long id);

    void insert(T t);

    void delete(long id);

    void update(T t);
}
