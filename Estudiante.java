public class Estudiante {
    private String matricula;
    private String nombre;
    private double calificacion;

    public Estudiante(String matricula, String nombre, double calificacion) {
        this.matricula = matricula;
        this.nombre = nombre;
        setCalificacion(calificacion);
    }

    public String getMatricula() { return matricula; }
    public String getNombre() { return nombre; }
    public double getCalificacion() { return calificacion; }

    public void setCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 100) {
            throw new IllegalArgumentException("La calificacion debe estar entre 0 y 100.");
        }
        this.calificacion = calificacion;
    }

    public boolean aprobo() { return calificacion >= 70; }

    public String mostrarInformacion() {
        String estado = aprobo() ? "Aprobado" : "No aprobado";
        return "Matricula: " + matricula + " | Nombre: " + nombre
            + " | Calificacion: " + String.format("%.2f", calificacion)
            + " | Estado: " + estado;
    }
}
