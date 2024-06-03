package tpeProg3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Solucion {
    protected Integer tiempoMaximo;
    protected HashMap<String, List<Tarea>> asignaciones;

    public Solucion(Integer tiempoMaximo, HashMap<String, Procesador> procesadores) {
        this.tiempoMaximo = tiempoMaximo;
        this.asignaciones = new HashMap<>();

        for(Procesador p : procesadores.values()){
            this.asignaciones.put(p.getIdProcesador(), new ArrayList<>());
        }
    }

    public HashMap<String, List<Tarea>> getAsignaciones(){
        return this.asignaciones;
    }
    public Integer getTiempoMaximo(){
        return this.tiempoMaximo;
    }

    public void setTiempoMaximo(Integer tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public abstract Solucion resolver(Integer tiempoMaximo, HashMap<String,Procesador> procesadorHashMap, ArrayList<Tarea> tareas);
}
