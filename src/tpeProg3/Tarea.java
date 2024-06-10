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

    public String getId() {
    	return this.id;
    }
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public int getTiempoEjecucion() {
    	return this.tiempoEjecucion;
    }

    public boolean esCritica() {
    	return this.esCritica;
    }
    public int getNivelPrioridad() {
    	return this.nivelPrioridad;
    }

    @Override
    public String toString() {
        return "{Tarea: " +
                "id = '" + this.id + '\'' +
                ", nombre = '" + this.nombre + '\'' +
                ", tiempoEjecucion = " + this.tiempoEjecucion +
                ", esCritica = " + this.esCritica +
                ", nivelPrioridad = " + this.nivelPrioridad +
                '}';
    }
    
    public boolean equals(Tarea t) {
    	return (this.getId() == t.getId());
    }
}
