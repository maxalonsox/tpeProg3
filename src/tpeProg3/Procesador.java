package tpeProg3;

import java.util.*;

public class Procesador {
    private String id;
    private String codigo;
    private boolean estaRefrigerado;
    private int anoFuncionamiento;
    private List<Tarea> tareasAsignadas;

    public Procesador(String id, String codigo, boolean estaRefrigerado, int anoFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.estaRefrigerado = estaRefrigerado;
        this.anoFuncionamiento = anoFuncionamiento;
        this.tareasAsignadas = new ArrayList<>();
    }
    
    public Procesador(Procesador p) {
    	this.id = p.getId();
    	this.codigo = p.getCodigo();
    	this.estaRefrigerado = p.estaRefrigerado();
    	this.anoFuncionamiento = p.getAnoFuncionamiento();
    	this.tareasAsignadas = new ArrayList<>();
    	for (Tarea t: p.getTareasAsignadas()) {
    		this.tareasAsignadas.add(t);
    	}
    }

    public String getId() {
    	return this.id;
    }
    
    public String getCodigo() {
    	return this.codigo;
    }
    
    public boolean estaRefrigerado() {
    	return this.estaRefrigerado;
    }
    
    public int getAnoFuncionamiento() {
    	return this.anoFuncionamiento;
    }
    
    public List<Tarea> getTareasAsignadas() { //Devuelve una copia de la lista de tareas asignadas.
    	ArrayList<Tarea> copy = new ArrayList<>();
    	for (Tarea t: this.tareasAsignadas) copy.add(t);
    	return copy;
    }
    
    public int getCantidadTareasCriticas() { //Devuelve el número de tareas críticas que tiene asignadas el procesador.
    	int tareasCriticas = 0;
    	for (Tarea t: this.tareasAsignadas)
	    	if (t.esCritica()) {
				tareasCriticas++;
			}
    	return tareasCriticas;
    }
    
    public int getTiempoMaximoEjecucion() { //Devuelve la suma de los tiempos de ejecución de sus tareas asignadas.
    	int tiempoMaximo = 0;
    	for (Tarea t: this.tareasAsignadas) tiempoMaximo += t.getTiempoEjecucion();
    	return tiempoMaximo;
    }
    
    public void asignarTarea(Tarea t) {
    	this.tareasAsignadas.add(t);
    }
    
    public void desasignarTarea(Tarea t) {
    	this.tareasAsignadas.remove(t);
    }

    @Override
    public String toString() {
        return "{Procesador: " +
                "id = '" + this.id + '\'' +
                ", codigo = '" + this.codigo + '\'' +
                ", estaRefrigerado = " + this.estaRefrigerado +
                ", anoFuncionamiento = " + this.anoFuncionamiento +
                '}';
    }
}
