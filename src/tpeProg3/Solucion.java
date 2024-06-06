package tpeProg3;

import java.util.*;

public abstract class Solucion {
	protected List<Tarea> tareas;
    protected List<Procesador> procesadores;

    public Solucion(List<Tarea> t, List<Procesador> p) {
        this.tareas = t;
        this.procesadores = p;
    }
    
    public boolean puedeAsignar(Procesador procesador, Tarea tarea, HashMap<String,Procesador> asignaciones, int tiempoMaxSinRefrigeracion) {
        int tareasCriticas = 0;
        int tiempoTotal = 0;

        for (Procesador p: asignaciones.values()) {
            if (p.equals(procesador)) {
            	for (Tarea t : p.getTareasAsignadas()) {
            		if (t.esCritica()) {
            			tareasCriticas++;
            		}
            		tiempoTotal += t.getTiempoEjecucion();            		
            	}
            }
        }

        if (tarea.esCritica() && tareasCriticas >= 2) {
            return false;
        }
        if (!procesador.estaRefrigerado() && (tiempoTotal + tarea.getTiempoEjecucion() > tiempoMaxSinRefrigeracion)) {
            return false;
        }
        return true;
    }
}

