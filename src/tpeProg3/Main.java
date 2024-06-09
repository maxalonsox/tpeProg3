package tpeProg3;

public class Main {
    public static void main(String args[]) {
        Servicios servicios = new Servicios("./src/tpeProg3/datasets/Procesadores.csv", "./src/tpeProg3/datasets/Tareas.csv");
        System.out.println(servicios.servicio1("T1"));
        System.out.println(servicios.servicio2(true));
        System.out.println(servicios.servicio3(90,93));

        Backtracking b = new Backtracking(servicios.getTareas(), servicios.getProcesadores());
        b.backtrackingAsignacion(1000);

//        Greedy g = new Greedy(servicios.getTareas(), servicios.getProcesadores());
//        g.greedyAsignacion(1000);
	}
}