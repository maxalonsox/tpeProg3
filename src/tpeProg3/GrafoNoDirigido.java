package tpeProg3;

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

    @Override
    public void agregarArco(String verticeId1, String verticeId2, T etiqueta) {
        super.agregarArco(verticeId1, verticeId2, etiqueta);
        super.agregarArco(verticeId2, verticeId1, etiqueta);
    }

    @Override
    public void borrarArco(String verticeId1, String verticeId2) {
        super.borrarArco(verticeId1, verticeId2);
        super.borrarArco(verticeId2, verticeId1);
    }

    @Override
    public int cantidadArcos() {
        return super.cantidadArcos() / 2;
    }

}
