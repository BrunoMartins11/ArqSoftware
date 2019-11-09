package Data;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    public T get(int id);
    public List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);
}
