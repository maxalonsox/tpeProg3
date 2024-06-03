package tpeProg3;

import tpeProg3.utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;

// NO modificar la interfaz de esta clase ni sus métodos públicos.
// Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
// de implementación.

public class Servicios {
    private HashMap<String,Tarea> tareasHashMap;
    private HashMap<String, Procesador> procesadoresHashMap;
    private Tree tareasTree;

    //Expresar la complejidad temporal del constructor.
    public Servicios(String pathProcesadores, String pathTareas){
        tareasTree = new Tree();
        tareasHashMap = new HashMap<>();
        procesadoresHashMap = new HashMap<>();

        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores, procesadoresHashMap);
        reader.readTasks(pathTareas,tareasHashMap,tareasTree);
    }


    // Expresar la complejidad temporal del servicio 1.
    // Servicio 1: Dado un identificador de tarea obtener toda la información de la tarea asociada.
    public Tarea servicio1(String ID) {
        return tareasHashMap.get(ID);
    }
    // El servicio 1 tiene una complejidad computacional de o(1) por que usamos una estructura HashMap para almacenar
    // las tareas, en un HashMap el acceso por clave tiene complejidad computacional o(1)


    // Expresar la complejidad temporal del servicio 2.
    // Servicio 2: Permitir que el usuario decida si quiere ver todas las tareas críticas o no críticas y generar el listado apropiado resultante.
    public List<Tarea> servicio2(boolean esCritica) {
        ArrayList<Tarea> salida = new ArrayList<>();
        for (Tarea t: tareasHashMap.values()) {
            if (esCritica==t.esCritica()){
                salida.add(t);
            }
        }
        return salida;
    }
    // El servicio 2 tiene una complejidad o(n) ya que debe recorrer toda la lista de tareas para comprobar
    // las que cumplen el criterio de si es critica o no.


    // Expresar la complejidad temporal del servicio 3.
    // Servicio 3: Obtener todas las tareas entre 2 niveles de prioridad indicados.
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return tareasTree.getTareasEntreNivelDePrioridad(prioridadInferior, prioridadSuperior);
    }
    // El servicio 3 es o(n) donde n es la cantidad de tareas

    public HashMap<String, Procesador> getProcesadoresHashMap() {
        HashMap<String, Procesador> copia = new HashMap(procesadoresHashMap);
        return copia;
    }

    public HashMap<String, Tarea> getTareasHashMap() {
        HashMap<String, Tarea> copia = new HashMap(tareasHashMap);
        return copia;
    }
}
