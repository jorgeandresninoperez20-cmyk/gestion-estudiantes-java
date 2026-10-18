import java.util.ArrayList;
import java.util.Scanner;

public class GestionEstudiantes {
    private static final Scanner teclado = new Scanner(System.in);
    private static final ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");
            switch (opcion) {
                case 1: registrarEstudiante(); break;
                case 2: listarEstudiantes(); break;
                case 3: buscarEstudiante(); break;
                case 4: mostrarPromedioGeneral(); break;
                case 5: System.out.println("Programa finalizado. Gracias."); break;
                default: System.out.println("Opcion no valida. Elige del 1 al 5.");
            }
            System.out.println();
        } while (opcion != 5);
        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== SISTEMA DE GESTION DE ESTUDIANTES =====");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Mostrar todos los estudiantes");
        System.out.println("3. Buscar por matricula");
        System.out.println("4. Calcular promedio general");
        System.out.println("5. Salir");
    }

    private static void registrarEstudiante() {
        System.out.println("--- Registro de estudiante ---");
        String matricula = leerTexto("Matricula: ");
        if (buscarPorMatricula(matricula) != null) {
            System.out.println("Error: ya existe esa matricula.");
            return;
        }
        String nombre = leerTexto("Nombre completo: ");
        double calificacion = leerDouble("Calificacion (0 a 100): ");
        while (calificacion < 0 || calificacion > 100) {
            System.out.println("La calificacion debe estar entre 0 y 100.");
            calificacion = leerDouble("Vuelve a escribir la calificacion: ");
        }
        Estudiante nuevo = new Estudiante(matricula, nombre, calificacion);
        estudiantes.add(nuevo);
        System.out.println("Estudiante registrado correctamente.");
        System.out.println(nuevo.mostrarInformacion());
    }

    private static void listarEstudiantes() {
        System.out.println("--- Lista de estudiantes ---");
        if (estudiantes.isEmpty()) {
            System.out.println("Todavia no hay estudiantes registrados.");
            return;
        }
        for (Estudiante estudiante : estudiantes)
            System.out.println(estudiante.mostrarInformacion());
    }

    private static void buscarEstudiante() {
        String matricula = leerTexto("Escribe la matricula: ");
        Estudiante encontrado = buscarPorMatricula(matricula);
        if (encontrado == null)
            System.out.println("No se encontro un estudiante con esa matricula.");
        else
            System.out.println(encontrado.mostrarInformacion());
    }

    private static Estudiante buscarPorMatricula(String matricula) {
        for (Estudiante estudiante : estudiantes)
            if (estudiante.getMatricula().equalsIgnoreCase(matricula)) return estudiante;
        return null;
    }

    private static void mostrarPromedioGeneral() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay calificaciones para calcular el promedio.");
            return;
        }
        double suma = 0;
        for (Estudiante estudiante : estudiantes) suma += estudiante.getCalificacion();
        System.out.printf("Promedio general: %.2f%n", suma / estudiantes.size());
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("Este campo no puede quedar vacio.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Integer.parseInt(teclado.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Escribe un numero entero valido."); }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Double.parseDouble(teclado.nextLine().trim().replace(',', '.')); }
            catch (NumberFormatException e) { System.out.println("Escribe una calificacion numerica."); }
        }
    }
}
