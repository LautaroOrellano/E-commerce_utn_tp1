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
        authManager.register(new Admin("Lautaro", "Orellano", "lautaro@gmail.com", "1234"));
        authManager.register(new Customer("Juan", "Perez", "juan@mail.com", "abcd",
                12345678L, 123456789L, "Calle Falsa 123", 25));
    }

    public void run() {
        User user = null;
        int option = 0;

        do {
            System.out.println("=== Bienvenido a UTN Ecommerce ===");
            System.out.println("\nElige una opcion para comenzar");
            System.out.println("1- Registrarse");
            System.out.println("2- Iniciar Sesion");
            System.out.println("0- Exit");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Sección Registro: ");
                    System.out.println("Ingrese su nombre");
                    String name = scanner.nextLine();
                    System.out.println("Ingrese su apellido");
                    String lastName = scanner.nextLine();
                    System.out.println("Ingrese su email");
                    String email = scanner.nextLine();
                    System.out.println("Ingrese su password");
                    String password = scanner.nextLine();
                    if (name == null || name.isEmpty()) {
                        System.out.println("No ingreso su nombre, intentelo de nuevo");
                    } else if (lastName == null || lastName.isEmpty()) {
                        System.out.println("No ingreso su apellido, intentelo de nuevo");
                    } else if (email == null || email.isEmpty()) {
                        System.out.println("No ingreso su email, intentelo de nuevo");
                    } else if ( password == null || password.isEmpty()) {
                        System.out.println("No ingreso su contraseña, intentelo de nuevo");
                    }
                    user = new Customer(name, lastName, email, password);
                    authManager.register(user);
                    System.out.println("Registro exitoso. Ya puede iniciar sesion");
                    user = null;
                }
                case 2 -> {
                    System.out.println("Sección de login: ");
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Password");
                    String password = scanner.nextLine();
                    user = authManager.login(email, password);
                    if (user == null) {
                        System.out.println("Credenciales incorrectas. Intente nuevamente.");
                    } else {
                        System.out.println("Login exitoso. Bienvenido " + user.getName());
                    }
                }
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion inválida.\n");
            }
        } while (user== null && option != 0 );

        if (user != null) {
            int optionMenu = 0;
            do {
                user.getMenu();
                System.out.println("Elige una opcion correcta.");
                optionMenu = scanner.nextInt();
                scanner.nextLine();

                menuManager.processOption(user, optionMenu, scanner);

            } while (optionMenu != 0);

            System.out.println("\nSesión cerrada.");
        }
    }
}
