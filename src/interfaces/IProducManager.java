package interfaces;

public interface IProducManager {

    void createProduct(String name, String description, int stock);
    void getAllProducts();
    void searchProduct(int id);
    void updateProduct();
    void deleteProduct(int id);
}

