package tpeProg3;

public class Arco<T> {

    private String verticeOrigen;
    private String verticeDestino;
    private T etiqueta;

    public Arco(String verticeOrigen, String verticeDestino, T etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
    }

    public String getVerticeOrigen() {
        return verticeOrigen;
    }

    public String getVerticeDestino() {
        return verticeDestino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

}