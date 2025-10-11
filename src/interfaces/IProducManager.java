package interfaces;

public interface IProducManager {

    void createProduct(String name, String description, int stock);
    void getAllProducts();
    void searchProduct(int id);
    void updateProduct(int id, String name, String description, int stock);
    void deleteProduct(int id);
}

