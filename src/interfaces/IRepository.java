package interfaces;

import java.util.List;

public interface IRepository<T> {
    void add(T item);
    void remove(T item);
    T findById(int id);
    List<T> getAll();
}