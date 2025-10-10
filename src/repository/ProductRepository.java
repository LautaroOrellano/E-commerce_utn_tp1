package repository;

import clases.entidades.Product;
import interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IRepository<Product> {
    private List<Product> products = new ArrayList<>();

    @Override
    public void add(Product item) {
        products.add(item);
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }

    @Override
    public void remove(Product item) {

    }
}
