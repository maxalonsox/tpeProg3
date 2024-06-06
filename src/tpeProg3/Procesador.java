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

    public String getId() { return id; }
    
    public String getCodigo() { return codigo; }
    
    public boolean estaRefrigerado() { return estaRefrigerado; }
    
    public int getAnoFuncionamiento() { return anoFuncionamiento; }
    
    public List<Tarea> getTareasAsignadas() {
    	ArrayList<Tarea> copy = new ArrayList<>();
    	for (Tarea t: this.tareasAsignadas) copy.add(t);
    	return copy;
    }
    
    public int getTiempoMaximoEjecucion() {
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
        return "Procesador{" +
                "id='" + id + '\'' +
                ", codigo='" + codigo + '\'' +
                ", estaRefrigerado=" + estaRefrigerado +
                ", anoFuncionamiento=" + anoFuncionamiento +
                '}';
    }
}
