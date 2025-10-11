package interfaces;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    void add(T item);
    void remove(T item);
    Optional<T> findById(int id);
    List<T> getAll();
}