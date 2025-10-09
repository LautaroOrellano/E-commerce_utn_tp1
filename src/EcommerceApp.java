import clases.entidades.users.Admin;
import clases.entidades.users.Customer;
import clases.entidades.users.User;
import clases.gestoras.AuthManager;
import clases.gestoras.MenuManager;
import enums.Rol;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EcommerceApp {

    private List<User> users = new ArrayList<>();
    private AuthManager authManager = new AuthManager(users);
    private MenuManager menuManager = new MenuManager();
    private Scanner scanner = new Scanner(System.in);

    public EcommerceApp() {
        authManager.register(new Admin("Lautaro", "Orellano", "admin@mail.com", "1234"));
        authManager.register(new Customer("Juan", "Perez", "juan@mail.com", "abcd",
                12345678L, 123456789L, "Calle Falsa 123", 25));
    }

    public void run() {
        int opcion = 0;

        do {
            System.out.println("=== Bienvenido a ?? Ecommerce ===");

            System.out.println("Email: ");
            String email = scanner.nextLine();

            System.out.println("Password");
            String password = scanner.nextLine();

            User user = authManager.login(email, password);

            if (user != null) {
                System.out.println("Login exitoso. Bienvenido " + user.getName());
                user.getMenu();
                System.out.println("Elige una opcion correcta.");
                opcion = scanner.nextInt();
                menuManager.processOption(user, opcion);
            } else {
                System.out.println("Credenciales incorrectas.");
            }
        } while (opcion != 0);

    }
}
