package tpeProg3;

import java.util.*;

public class Asignacion {
    private HashMap<String, Procesador> mejorSolucion;

    public Asignacion() {
        mejorSolucion = new HashMap<>();
    }

    public HashMap<String, Procesador> getMejorSolucion() {    //Devuelve una copia de la mejor solución encontrada.
    	HashMap<String,Procesador> copy = new HashMap<>(this.mejorSolucion);
        return copy;
    }

    public void setMejorSolucion(HashMap<String, Procesador> nuevaSolucion) {    //Actualiza la mejor solución.
        mejorSolucion.clear();
        for (Map.Entry<String, Procesador> entry : nuevaSolucion.entrySet()) {
            mejorSolucion.put(entry.getKey(), new Procesador(entry.getValue()));
        }
    }

    public boolean isEmpty() {
        return mejorSolucion.isEmpty();
    }
    
    public List<Tarea> getAllTareasAsignadas() {       //Devuelve una lista con TODAS las tareas asignadas hasta el momento teniendo en cuenta todos los procesadores.
    	ArrayList<Tarea> tareasAsignadas = new ArrayList<>();
    	for (Procesador p: this.mejorSolucion.values()) {
    		for (Tarea t: p.getTareasAsignadas()) {
    			if (!tareasAsignadas.contains(t)) tareasAsignadas.add(t);
    		}
    	}
    	return tareasAsignadas;
    }
    
    public int getTiempoMaximoEjecucion() {  		//Calcula el tiempo máximo de ejecución de la solución.
    	int tiempoMaximo = 0;
    	for (Procesador p: this.mejorSolucion.values()) tiempoMaximo = Math.max(tiempoMaximo, p.getTiempoMaximoEjecucion());
    	return tiempoMaximo;
    }
    
    public void setProcesador(Procesador p) {
    	this.mejorSolucion.put(p.getId(), p);
    }
    
    public Procesador getProcesador(String idProcesador) {
    	return this.mejorSolucion.get(idProcesador);
    }
}
