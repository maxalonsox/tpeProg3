package tpeProg3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking extends Solucion{
    private int cantidadEstadosGenerados;
    private HashMap<String, List<Tarea>> mejoresAsignaciones;

    public Backtracking(Integer tiempoMaximo, HashMap<String, Procesador> procesadores) {
        super(tiempoMaximo, procesadores);
        this.cantidadEstadosGenerados = 0;
        this.mejoresAsignaciones = new HashMap<>();
        // Initialize mejoresAsignaciones with empty lists
        for (String idProcesador : procesadores.keySet()) {
            this.mejoresAsignaciones.put(idProcesador, new ArrayList<>());
        }
    }

    public int getCantidadEstadosGenerados() {
        return cantidadEstadosGenerados;
    }

    @Override
    public Backtracking resolver(Integer tiempoMaximoNoRefrigerado, HashMap<String, Procesador> procesadorHashMap, ArrayList<Tarea> tareas) {
        ArrayList<Procesador> procesadores = new ArrayList<>(procesadorHashMap.values());
        backtrack(0, procesadores, tareas, tiempoMaximoNoRefrigerado);
        // Update the asignaciones with the best found solution
        this.asignaciones = new HashMap<>(this.mejoresAsignaciones);
        return this;
    }

    private void backtrack(Integer tareaActual, ArrayList<Procesador> procesadores, ArrayList<Tarea> tareas, Integer tiempoMaximoNoRefrigerado){
        cantidadEstadosGenerados++;
        if(tareaActual < tareas.size()){
            Tarea tarea = tareas.get(tareaActual); // Me guardo la tarea que quiero procesar
            for(Procesador p : procesadores){
                if(esAsignable(tarea, p, tiempoMaximoNoRefrigerado)){
                    asignar(tarea, p);
                    backtrack(tareaActual + 1, procesadores, tareas, tiempoMaximoNoRefrigerado);
                    desasignar(tarea, p);
                }
            }
        }
        int maxTiempoActual = 0;
        for (Procesador p : procesadores){ // Calcular el tiempo max de todos los procesadores
            maxTiempoActual = Math.max(maxTiempoActual, getTiempoProcesador(p));
        }
        if(maxTiempoActual < this.getTiempoMaximo()){ // Actualizar la mejor asignacion si hay una mejor
            this.setTiempoMaximo(maxTiempoActual);
            actualizarMejoresAsignaciones(procesadores);
        }
    }

    private boolean esAsignable(Tarea t, Procesador p, int tiempoMaximoNoRefrigerado){ // Verificar que el procesador no refrigerado no exceda el tiempo max permitido
        // Verificar si el procesador puede aceptar más tareas críticas
        if (t.esCritica() && (p.getCantTareasCriticasPermitidas() == p.getCantTareasCriticasProcesadas())) {
            return false;
        }
        // Verificar si el procesador no refrigerado excede el tiempo máximo permitido
        if (!p.esRefrigerado() && (getTiempoProcesador(p) + t.getTiempoEjecucion() > tiempoMaximoNoRefrigerado)) {
            return false;
        }
        return true;
    }

    private int getTiempoProcesador(Procesador p){
        int tiempoTotal = 0;
        for(Tarea t : this.asignaciones.get(p.getIdProcesador())){ // Esto devuelve una lista de tareas asociadas a este procesador y sumo todos sus tiempos
            tiempoTotal += t.getTiempoEjecucion();
        }
        return tiempoTotal;
    }

    private void asignar(Tarea t, Procesador p){
        this.asignaciones.get(p.getIdProcesador()).add(t);
    }

    private void desasignar(Tarea t, Procesador p){
        this.asignaciones.get(p.getIdProcesador()).remove(t);
    }

    private void actualizarMejoresAsignaciones(List<Procesador> procesadores){
        for (Procesador p : procesadores) {
            List<Tarea> mejoresTareas = new ArrayList<>(this.asignaciones.get(p.getIdProcesador()));
            this.mejoresAsignaciones.put(p.getIdProcesador(), mejoresTareas);
        }
    }

    @Override
    public String toString() {
        return "Backtracking{" +
                "cantidadEstadosGenerados=" + cantidadEstadosGenerados +
                ", mejoresAsignaciones=" + mejoresAsignaciones +
                '}';
    }
}