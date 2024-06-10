package tpeProg3;

import java.util.*;

public class Greedy extends Solucion{
	private int cantidadCandidatosConsiderados;
	
	public Greedy(List<Tarea> tareas, List<Procesador> procesadores) {
        super(tareas, procesadores);
        this.cantidadCandidatosConsiderados = 0;
	}
	
	/*
     * Estrategia: Algoritmo Greedy para asignación de tareas minimizando el tiempo de ejecución máximo.
     */
    public void greedyAsignacion(int tiempoMaxSinRefrigeracion) {
    	Asignacion solucion = new Asignacion();		//Wrapper que va guardando la asignacion final.
    	for (Procesador p: procesadores) solucion.setProcesador(p); //Inicializa la asignacion con todos los procesadores y sus listas de tareas asignadas vacías.
    	for (Tarea tarea : super.getTareas()) {						//Recorre todas las tareas, este es mi conjunto que de estar vacío, es porque hallé una solución.
            Procesador mejorProcesador = null;
            int mejorTiempoTotal = Integer.MAX_VALUE;
            for (Procesador procesador : super.getProcesadores()) {
                if (puedeAsignar(procesador, tarea, tiempoMaxSinRefrigeracion)) { 	//Condición para ver si es un candidado posible.
                    this.cantidadCandidatosConsiderados++;
                    int tiempoTotal = tarea.getTiempoEjecucion() + procesador.getTiempoMaximoEjecucion();
                    if (tiempoTotal < mejorTiempoTotal) {							//Si es un candidato mejor, lo actualiza.
                        mejorTiempoTotal = tiempoTotal;
                        mejorProcesador = procesador;
                    }
                }
            }
            if (mejorProcesador != null) solucion.getProcesador(mejorProcesador.getId()).asignarTarea(tarea);  //Si el candidato no es null, lo agrega a la solución, si es null sale del bucle.
            else break;
        }
        imprimirAsignaciones(solucion);
    }

    private void imprimirAsignaciones(Asignacion solucion) {
        System.out.println("GREEDY");
        if (solucion.getAllTareasAsignadas().size() < super.getTareas().size()) System.out.print("No se puede hallar una solución con esta técnica."); //Si no se pudieron asignas todas las tareas, avisa que no se puede hallar una solución mediante esta técnica.
        else {
        	for (Procesador p: solucion.getMejorSolucion().values()) {
        		System.out.println("\nPROCESADOR " + p.getId() + ":");
        		for (Tarea t : p.getTareasAsignadas()) {
        			System.out.print("(" + t.getId() + ") (" + t.getTiempoEjecucion() + ")");
        			if (t.esCritica()) System.out.print(" (C)\n");
        			else System.out.print("\n");
        		}
        	}
        	System.out.println("\nTiempo máximo de ejecución: " + solucion.getTiempoMaximoEjecucion());
        	System.out.println("Cantidad de candidatos considerados: " + this.cantidadCandidatosConsiderados);
        }
    }
}