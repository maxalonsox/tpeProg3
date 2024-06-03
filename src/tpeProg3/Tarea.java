package tpeProg3;

public class Tarea {
    private String id;
    private String nombreTarea;
    private Integer tiempoEjecucion;
    private boolean critica;
    private Integer prioridad;

    public Tarea(String id, String nombreTarea, Integer tiempoEjecucion, boolean critica, Integer prioridad){
        this.id = id;
        this.nombreTarea = nombreTarea;
        this.tiempoEjecucion = tiempoEjecucion;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    // Getters y Setters
    public String getId(){
        return this.id;
    }
    public String getNombreTarea(){
        return this.nombreTarea;
    }
    public Integer getTiempoEjecucion(){
        return this.tiempoEjecucion;
    }
    public boolean esCritica(){
        return this.critica;
    }
    public Integer getPrioridad(){
        return this.prioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id='" + id + '\'' +
                ", nombreTarea='" + nombreTarea + '\'' +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", critica=" + critica +
                ", prioridad=" + prioridad +
                '}';
    }
}
