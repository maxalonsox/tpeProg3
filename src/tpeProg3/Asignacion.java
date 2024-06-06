package tpeProg3;

import java.util.*;

public class Asignacion {
    private HashMap<String, Procesador> mejorSolucion;

    public Asignacion() {
        mejorSolucion = new HashMap<>();
    }

    public HashMap<String, Procesador> getMejorSolucion() {
        return mejorSolucion;
    }

    public void setMejorSolucion(HashMap<String, Procesador> nuevaSolucion) {
        mejorSolucion.clear();
        for (Map.Entry<String, Procesador> entry : nuevaSolucion.entrySet()) {
            mejorSolucion.put(entry.getKey(), new Procesador(entry.getValue()));
        }
    }

    public boolean isEmpty() {
        return mejorSolucion.isEmpty();
    }
}
