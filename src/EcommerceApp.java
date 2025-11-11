import models.users.User;
import repository.ProductRepository;
import repository.UserRepository;
import service.AuthService;
import service.MenuService;

import java.util.Scanner;

public class EcommerceApp {
    private final ProductRepository productRepository = new ProductRepository();
    private final UserRepository userRepository = new UserRepository(productRepository);
    private final AuthService authManager = new AuthService(userRepository);
    private final MenuService menuManager = new MenuService();
    private final Scanner scanner = new Scanner(System.in);

    public EcommerceApp() {
    }

    public void run() {
        boolean running = true;

        while (running) { // 🔄 Bucle principal: vuelve acá después de cerrar sesión
            User user = null;
            int option = 0;

            // ============================
            //     MENÚ PRINCIPAL
            // ============================
            do {
                menuManager.showWelcomeMenu();
                System.out.print("> ");

                String input = scanner.nextLine().trim();

                try {
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Debes ingresar un número válido.\n");
                    continue;
                }

                switch (option) {
                    case 1 -> {
                        // Registro
                        user = menuManager.showMenuRegister(scanner);
                        if (user != null) {
                            authManager.register(user);
                            userRepository.add(user);
                            System.out.println("\nRegistro exitoso!!. Ya puede iniciar sesión.");
                        }
                        user = null; // permanece null hasta que inicie sesión
                    }

                    case 2 -> {
                        // Login
                        user = menuManager.showMenuLogin(scanner, authManager);

                        if (user == null) {
                            System.out.println("Credenciales incorrectas. Intente nuevamente.\n");
                        } else {
                            System.out.println("\nLogin exitoso. Bienvenido " + user.getName());
                        }
                    }

                    case 0 -> {
                        System.out.println("Saliendo del sistema...");
                        running = false; // 🔚 sale del programa completamente
                    }

                    default -> System.out.println("Opción inválida.\n");
                }

            } while (user == null && option != 0 && running);

            // ============================
            //     MENÚ DEL USUARIO
            // ============================
            while (user != null) {
                int optionMenu = -1;

                try {
                    menuManager.showMenu(user);
                    System.out.print("> ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Debes ingresar un número válido");
                        System.out.print("> ");
                        scanner.nextLine();
                    }

                    optionMenu = scanner.nextInt();
                    scanner.nextLine();

                    // Cerrar sesión
                    if (optionMenu == 0) {
                        System.out.println("\nVolviendo al menú principal...");
                        user = null; // 👈 Esto hace que vuelva al while principal
                        break;
                    }

                    // Salir del programa desde el menú interno
                    if (optionMenu == 99) {
                        System.out.println("Saliendo del sistema...");
                        user = null;
                        running = false;
                        break;
                    }

                    menuManager.processOption(user, optionMenu, scanner);

                } catch (Exception e) {
                    System.out.println("Ocurrió un error: " + e.getMessage());
                    scanner.nextLine();
                }
            }

            if (running && user == null) {
                System.out.println("\nSesión cerrada.");
            }
        }

        System.out.println("Programa finalizado correctamente.");
    }
}
