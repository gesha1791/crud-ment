package chaplinskiy.crud.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t);
    T update (T t);
    T getById(ID id);
    List<T> getAll();
    void deleteById(ID id);
}
