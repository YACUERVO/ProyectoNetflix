import modelo.Perfil;
import modelo.Usuario;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Usuario> usuarios;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main sistema = new Main();
        sistema.iniciar();
    }

    public Main() {
        usuarios = new HashMap<>();
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    agregarPerfil();
                    break;
                case 4:
                    accederPerfil();
                    break;
                case 5:
                    listarPerfiles();
                    break;
                case 0:
                    System.out.println("Gracias");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Agregar perfil");
        System.out.println("4. Acceder a perfil");
        System.out.println("5. Listar perfiles");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void registrarUsuario() {
        System.out.println("\n=== REGISTRO DE USUARIO ===");
        System.out.print("Ingrese nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese celular: ");
        String celular = scanner.nextLine();

        if (usuarios.containsKey(email)) {
            System.out.println("Error: El email ya está registrado");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombres, apellidos, email, celular);
        usuarios.put(email, nuevoUsuario);
        System.out.println("Usuario registrado exitosamente");
        System.out.println("Su contraseña es: " + nuevoUsuario.getPassword());
    }

    private void iniciarSesion() {
        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = usuarios.get(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            System.out.println("Sesión iniciada correctamente");
        } else {
            System.out.println("Error: Credenciales incorrectas");
        }
    }

    private void agregarPerfil() {
        System.out.println("\n=== AGREGAR PERFIL ===");
        System.out.print("Ingrese email de la cuenta: ");
        String email = scanner.nextLine();

        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado");
            return;
        }

        System.out.print("Ingrese nombre del perfil: ");
        String nombrePerfil = scanner.nextLine();
        System.out.print("Ingrese PIN para el perfil: ");
        String pin = scanner.nextLine();

        if (usuario.agregarPerfil(nombrePerfil, pin)) {
            System.out.println("Perfil agregado exitosamente");
        } else {
            System.out.println("Error: No se pudo agregar el perfil (límite alcanzado o nombre duplicado)");
        }
    }

    private void accederPerfil() {
        System.out.println("\n=== ACCEDER A PERFIL ===");
        System.out.print("Ingrese email de la cuenta: ");
        String email = scanner.nextLine();

        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado");
            return;
        }

        System.out.print("Ingrese nombre del perfil: ");
        String nombrePerfil = scanner.nextLine();
        System.out.print("Ingrese PIN del perfil: ");
        String pin = scanner.nextLine();

        boolean accesoConcedido = false;
        for (Perfil perfil : usuario.getPerfiles()) {
            if (perfil.getNombre().equals(nombrePerfil) && perfil.getPin().equals(pin)) {
                accesoConcedido = true;
                break;
            }
        }

        if (accesoConcedido) {
            System.out.println("Acceso concedido al perfil: " + nombrePerfil);
        } else {
            System.out.println("Error: Nombre de perfil o PIN incorrectos");
        }
    }

    private void listarPerfiles() {
        System.out.println("\n=== LISTAR PERFILES ===");
        System.out.print("Ingrese email de la cuenta: ");
        String email = scanner.nextLine();

        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado");
            return;
        }

        if (usuario.getPerfiles().isEmpty()) {
            System.out.println("No hay perfiles registrados");
            return;
        }

        System.out.println("Perfiles de la cuenta:");
        for (Perfil perfil : usuario.getPerfiles()) {
            System.out.println("- " + perfil.getNombre());
        }
    }
}
