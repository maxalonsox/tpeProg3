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
    	HashMap<String,Procesador> asignaciones = new HashMap<>();
    	for (Procesador p: procesadores) asignaciones.put(p.getId(),p);
    	Asignacion solucion = new Asignacion();
    	this.cantidadCandidatosConsiderados = 0;
    	for (Tarea tarea : super.getTareas()) {
            Procesador mejorProcesador = null;
            int mejorTiempoTotal = Integer.MAX_VALUE;
            for (Procesador procesador : super.getProcesadores()) {
                if (puedeAsignar(procesador, tarea, asignaciones, tiempoMaxSinRefrigeracion)) {
                    this.cantidadCandidatosConsiderados++;
                    int tiempoTotal = calcularTiempoTotal(procesador, tarea, asignaciones);
                    if (tiempoTotal < mejorTiempoTotal) {
                        mejorTiempoTotal = tiempoTotal;
                        mejorProcesador = procesador;
                    }
                }
            }
            if (mejorProcesador != null) {
            	asignaciones.get(mejorProcesador.getId()).asignarTarea(tarea);
            } else {
                System.out.println("No se pudo asignar la tarea " + tarea.getId());
            }
        }
        solucion.setMejorSolucion(asignaciones);
        imprimirAsignaciones(solucion);
    }
	
    private int calcularTiempoTotal(Procesador procesador, Tarea tarea, HashMap<String,Procesador> asignaciones) {
        return tarea.getTiempoEjecucion() + procesador.getTiempoMaximoEjecucion();
    }

    private void imprimirAsignaciones(Asignacion solucion) {
    	int tiempoMayor = 0;
        System.out.println("GREEDY");
        for (Procesador p: solucion.getMejorSolucion().values()) {
            tiempoMayor = Math.max(tiempoMayor, p.getTiempoMaximoEjecucion());

            System.out.println("\nPROCESADOR " + p.getId() + ":");
            for (Tarea t : p.getTareasAsignadas()) {
            	System.out.print("(" + t.getId() + ") (" + t.getTiempoEjecucion() + ")");
            	if (t.esCritica()) System.out.print(" (C)\n");
            	else System.out.print("\n");
            }
        }
        System.out.println("\nTiempo máximo de ejecución: " + tiempoMayor);
        System.out.println("Cantidad de candidatos considerados: " + this.cantidadCandidatosConsiderados);
    }
}