package tpeProg3;

import java.util.*;

public class Backtracking extends Solucion{
    private int estadosGenerados;

    public Backtracking(List<Tarea> tareas, List<Procesador> procesadores) {
        super(tareas, procesadores);
    }

    /*
     * Estrategia: Backtracking para asignación de tareas minimizando el tiempo de ejecución máximo.
     * Complejidad: Ya que la cantidad de estados generados están dados por la sumatoria de P^T (P¹ + P² + P³ + ... P^T) siendo P la cantidad de procesadores y T la cantidad de tareas.
     * Para simplificarlo diremos que la complejidad es de O((P^T)P), así siempre la cantidad de estados generados será mejor que esa cantidad.
     */
    
    public void backtrackingAsignacion(int tiempoMaxSinRefrigeracion) {
        estadosGenerados = 0;
        HashMap<String,Procesador> asignaciones = new HashMap<>();
        Asignacion mejorSolucion = new Asignacion(); 					//Wrapper que guarda el HashMap con la mejor solución obtenida.
        for (Procesador p: procesadores) asignaciones.put(p.getId(),p); //Inicializa asignaciones con todos los procesadores y sus listas de tareas vacías.
        backtrack(asignaciones, 0, tiempoMaxSinRefrigeracion, mejorSolucion);
        imprimirSolucion(mejorSolucion);
    }

    private void backtrack(HashMap<String,Procesador> asignaciones, int index, int tiempoMaxSinRefrigeracion, Asignacion mejorSolucion) {
    	if (index == tareas.size()) { //Estoy en una hoja -> Calculo solución
            int tiempoAsignacion = 0;
            for (Procesador p : asignaciones.values()) { //calculo el tiempo máximo de ejecución (tiempo del procesador que más tarde).
                tiempoAsignacion = Math.max(p.getTiempoMaximoEjecucion(), tiempoAsignacion);
            }
            int mejorTiempo = 0;
            for (Procesador p : mejorSolucion.getMejorSolucion().values()) { //calculo el tiempo máximo de ejecución (tiempo del procesador que más tarde).
                mejorTiempo = Math.max(p.getTiempoMaximoEjecucion(), mejorTiempo);
            }

            if (mejorSolucion.isEmpty() || tiempoAsignacion < mejorTiempo) { //Si es la primera solución que encuentro o es mejor que la que tengo guardada, actualizo.
                mejorSolucion.setMejorSolucion(asignaciones);
            }
        } else {
        	Tarea tarea = this.tareas.get(index);
        	for (Procesador procesador : this.procesadores) {
        		if (puedeAsignar(procesador, tarea, tiempoMaxSinRefrigeracion)) {   //Poda (checkeo si es asignable teniendo en cuenta las restricciones.
        			estadosGenerados++; 											//Solo genera el estado si se puede asignar la tarea a ese procesador.
        			asignaciones.get(procesador.getId()).asignarTarea(tarea);
        			backtrack(asignaciones, index + 1, tiempoMaxSinRefrigeracion, mejorSolucion);
        			asignaciones.get(procesador.getId()).desasignarTarea(tarea);
        		}
        	}	
        }
    }

    private void imprimirSolucion(Asignacion mejorSolucion) {
        System.out.println("BACKTRACKING");
        if (mejorSolucion.getAllTareasAsignadas().size() < super.getTareas().size()) System.out.println("No se puede hallar una solución con esta técnica.\n"); //Si no se pudieron asignas todas las tareas, avisa que no se puede hallar una solución mediante esta técnica.
        else {
        	for (Procesador p: mejorSolucion.getMejorSolucion().values()) {
        		System.out.println("\nPROCESADOR " + p.getId() + ":");
        		for (Tarea t : p.getTareasAsignadas()) {
        			System.out.print("(" + t.getId() + ") (" + t.getTiempoEjecucion() + ")");
        			if (t.esCritica()) System.out.print(" (C)\n");
        			else System.out.print("\n");
        		}
        	}
        	System.out.println("\nTiempo máximo de ejecución: " + mejorSolucion.getTiempoMaximoEjecucion());
        	System.out.println("Cantidad de estados generados: " + estadosGenerados + "\n");
        }
    }
}