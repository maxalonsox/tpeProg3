package tpeProg3;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        Servicios servicios = new Servicios("./src/tpeProg3/datasets/Procesadores.csv", "./src/tpeProg3/datasets/Tareas.csv");
        System.out.println(servicios.servicio1("T2"));
        System.out.println(servicios.servicio2(false));
        System.out.println(servicios.servicio3(0,10000));

        //Integer tiempoMaximo, HashMap<String, Procesador> procesadores
        Backtracking b = new Backtracking(80,servicios.getProcesadoresHashMap());

        Solucion s= b.resolver(1000, servicios.getProcesadoresHashMap(), new ArrayList<>(servicios.getTareasHashMap().values()));
        System.out.println(s.toString());
	}
}