package tpeProg3;

public class Tarea {
    private String id;
    private String nombre;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int nivelPrioridad;

    public Tarea(String id, String nombre, int tiempoEjecucion, boolean esCritica, int nivelPrioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.esCritica = esCritica;
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getTiempoEjecucion() { return tiempoEjecucion; }
    public boolean esCritica() { return esCritica; }
    public int getNivelPrioridad() { return nivelPrioridad; }

    @Override
    public String toString() {
        return "Tarea{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", esCritica=" + esCritica +
                ", nivelPrioridad=" + nivelPrioridad +
                '}';
    }
}
