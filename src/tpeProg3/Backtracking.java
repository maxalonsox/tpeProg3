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
        Asignacion mejorSolucion = new Asignacion();
        for (Procesador p: procesadores) asignaciones.put(p.getId(),p);
        backtrack(asignaciones, 0, tiempoMaxSinRefrigeracion, mejorSolucion);
        imprimirSolucion(mejorSolucion);
    }

    private void backtrack(HashMap<String,Procesador> asignaciones, int index, int tiempoMaxSinRefrigeracion, Asignacion mejorSolucion) {
    	if (index == tareas.size()) { //Estoy en una hoja -> Calculo solución
    		int tiempoAsignacion = 0;
            for (Procesador p : asignaciones.values()) {
                tiempoAsignacion = Math.max(p.getTiempoMaximoEjecucion(), tiempoAsignacion);
            }

            int mejorTiempo = 0;
            for (Procesador p : mejorSolucion.getMejorSolucion().values()) {
                mejorTiempo = Math.max(p.getTiempoMaximoEjecucion(), mejorTiempo);
            }

            if (mejorSolucion.isEmpty() || tiempoAsignacion < mejorTiempo) {
                mejorSolucion.setMejorSolucion(asignaciones);
            }
        } else {
        	Tarea tarea = this.tareas.get(index);
        	for (Procesador procesador : this.procesadores) {
        		if (puedeAsignar(procesador, tarea, asignaciones, tiempoMaxSinRefrigeracion)) {
        			estadosGenerados++; //Solo genera el estado si se puede asignar la Tarea a ese Procesador.
        			asignaciones.get(procesador.getId()).asignarTarea(tarea);
        			backtrack(asignaciones, index + 1, tiempoMaxSinRefrigeracion, mejorSolucion);
        			asignaciones.get(procesador.getId()).desasignarTarea(tarea);
        		}
        	}	
        }
    }

    private void imprimirSolucion(Asignacion mejorSolucion) {
        int tiempoMayor = 0;
        System.out.println("BACKTRACKING");
        for (Procesador p: mejorSolucion.getMejorSolucion().values()) {
            tiempoMayor = Math.max(tiempoMayor, p.getTiempoMaximoEjecucion());

            System.out.println("\nPROCESADOR " + p.getId() + ":");
            for (Tarea t : p.getTareasAsignadas()) {
            	System.out.print("(" + t.getId() + ") (" + t.getTiempoEjecucion() + ")");
            	if (t.esCritica()) System.out.print(" (C)\n");
            	else System.out.print("\n");
            }
        }
        System.out.println("\nTiempo máximo de ejecución: " + tiempoMayor);
        System.out.println("Cantidad de estados generados: " + estadosGenerados);
    }
}