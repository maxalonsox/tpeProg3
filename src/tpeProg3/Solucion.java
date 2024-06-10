package tpeProg3;

import java.util.*;

public abstract class Solucion {
	protected List<Tarea> tareas;
    protected List<Procesador> procesadores;
    private static final int MAX_TAREAS_CRITICAS = 2;    //Máximas tareas críticas permitidas por procesador.

    public Solucion(List<Tarea> t, List<Procesador> p) {
        this.tareas = t;
        this.procesadores = p;
    }
    
    public List<Tarea> getTareas() {
    	ArrayList<Tarea> copy = new ArrayList<>(this.tareas);
    	return copy;
    }
    
    public List<Procesador> getProcesadores() {
    	ArrayList<Procesador> copy = new ArrayList<>(this.procesadores);
    	return copy;
    }
    
    public boolean puedeAsignar(Procesador procesador, Tarea tarea, int tiempoMaxSinRefrigeracion) {   //Chequea que se cumpla con las restricciones.
        if (tarea.esCritica() && procesador.getCantidadTareasCriticas() >= MAX_TAREAS_CRITICAS) {
            return false;
        }
        if (!procesador.estaRefrigerado() && (procesador.getTiempoMaximoEjecucion() + tarea.getTiempoEjecucion() > tiempoMaxSinRefrigeracion)) {
            return false;
        }
        return true;
    }
}

