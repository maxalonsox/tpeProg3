package tpeProg3;

import tpeProg3.utils.CSVReader;

import java.util.*;
import java.util.stream.Collectors;

// NO modificar la interfaz de esta clase ni sus métodos públicos.
// Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
// de implementación.

public class Servicios {
	private HashMap<String, Tarea> tareasMap;
    private HashMap<String, Procesador> procesadoresMap;
    private Tree tareasTree;

    //Expresar la complejidad temporal del constructor.
    public Servicios(String pathProcesadores, String pathTareas){
    	tareasMap = new HashMap<>();
        procesadoresMap = new HashMap<>();
        tareasTree = new Tree();
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores, procesadoresMap);
        reader.readTasks(pathTareas,tareasMap,tareasTree);
    }


    // Servicio 1: Dado un identificador de tarea obtener toda la información de la tarea asociada.
    
    // El servicio 1 tiene una complejidad computacional de o(1) porque usamos una estructura HashMap para almacenar
    // las tareas, en un HashMap el acceso por clave tiene complejidad computacional o(1)

    public Tarea servicio1(String ID) {
        return tareasMap.get(ID);
    }
    
    
    // Servicio 2: Permitir que el usuario decida si quiere ver todas las tareas críticas o no críticas y generar el listado apropiado resultante.
    
    // El servicio 2 tiene una complejidad o(n) siendo n la cantidad de tareas, ya que debe recorrer toda la lista de tareas para comprobar
    // las que cumplen el criterio de si es critica o no.

    public List<Tarea> servicio2(boolean esCritica) {
        return tareasMap.values().stream()
                .filter(tarea -> tarea.esCritica() == esCritica)
                .collect(Collectors.toList());
    }
    

    // Servicio 3: Obtener todas las tareas entre 2 niveles de prioridad indicados.
    
    // El servicio 3 es o(n) donde n es la cantidad de tareas

    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return tareasMap.values().stream()
                .filter(tarea -> tarea.getNivelPrioridad() >= prioridadInferior && tarea.getNivelPrioridad() <= prioridadSuperior)
                .collect(Collectors.toList());
    }

    public List<Procesador> getProcesadores() {
        return new ArrayList<>(procesadoresMap.values());
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareasMap.values());
    }
}
