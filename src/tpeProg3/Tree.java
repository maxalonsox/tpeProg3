package tpeProg3;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public void add(Tarea value) {
        if (this.root == null)
            this.root = new TreeNode(value);
        else
            this.add(this.root, value);
    }

    private void add(TreeNode actual, Tarea value) {
        if (actual.getValue().getTiempoEjecucion() > value.getTiempoEjecucion()) {
            if (actual.getLeft() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setLeft(temp);
            } else {
                add(actual.getLeft(), value);
            }
        } else if (actual.getValue().getTiempoEjecucion() < value.getTiempoEjecucion()) {
            if (actual.getRight() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setRight(temp);
            } else {
                add(actual.getRight(), value);
            }
        }
    }

    // Complejidad O(1) - Devuelve el valor de la raíz del árbol
    public Tarea getRoot() {
        if (this.root != null) {
            return this.root.getValue();
        } else {
            return null;
        }
    }

    public boolean hasElem(Tarea tarea) {
        return hasElem(root, tarea);
    }

    // Complejidad es O(h) - h es la altura del árbol
    private boolean hasElem(TreeNode node, Tarea value) {
        if (node == null) {
            return false;
        }

        if (node.getValue().equals(value)) {
            return true;
        }

        // Recorrer hacia la izquierda si el valor es menor que el valor del nodo actual
        if (value.getTiempoEjecucion() < node.getValue().getTiempoEjecucion()) {
            return hasElem(node.getLeft(), value);
        }
        // Recorrer hacia la derecha si el valor es mayor que el valor del nodo actual
        else {
            return hasElem(node.getRight(), value);
        }
    }

    // Complejidad O(1) - Solo verifica si la raíz es nula
    public boolean isEmpty() {
        return this.root == null;
    }

    // Complejidad O(n) - En el peor caso tiene que recorrer todo el árbol
    public void insert(Tarea value) {
        add(root, value);
    }

    public void printPosOrder() {
        printPosOrder(root);
    }

    // Complejidad O(n) - Tiene que recorrer todo el árbol
    private void printPosOrder(TreeNode actual) { // Izq, Der, Raíz
        if (actual == null)
            System.out.println(" - ");
        else {
            printPosOrder(actual.getLeft());
            printPosOrder(actual.getRight());
            System.out.print(actual.getValue() + " ");
        }
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    // Complejidad O(n) - Tiene que recorrer todo el árbol
    private void printPreOrder(TreeNode actual) { // Raíz, Izq, Der
        if (actual == null)
            System.out.println(" - ");
        else {
            System.out.print(actual.getValue() + " ");
            printPreOrder(actual.getLeft());
            printPreOrder(actual.getRight());
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    // Complejidad O(n) - Tiene que recorrer todo el árbol
    private void printInOrder(TreeNode actual) { // Izq, Raíz, Der
        if (actual == null)
            System.out.println(" - ");
        else {
            printInOrder(actual.getLeft());
            System.out.print(actual.getValue() + " ");
            printInOrder(actual.getRight());
        }
    }

    public List<Integer> getLongestBranch() {
        return getLongestBranch(root);
    }

    // Complejidad es O(n) - Recorre todos los nodos del árbol
    private List<Integer> getLongestBranch(TreeNode actual) {
        // Si la rama es null devuelvo lista vacía
        if (actual == null) {
            ArrayList<Integer> aux = new ArrayList<>();
            return aux;
        }

        // Llamadas recursivas en izquierda y derecha
        ArrayList<Integer> right = (ArrayList<Integer>) getLongestBranch(actual.getRight());
        ArrayList<Integer> left = (ArrayList<Integer>) getLongestBranch(actual.getLeft());

        // Comparo el size de los ArrayList
        // inserto el nodo de acuerdo al tamañao
        if (right.size() < left.size()) {
            left.add(actual.getValue().getTiempoEjecucion());
        } else {
            right.add(actual.getValue().getTiempoEjecucion());
        }

        // Retorno el mas largo
        if (right.size() > left.size()) {
            return right;
        } else {
            return left;
        }
    }

    public List<Integer> getFrontera() {
        List<Integer> resultado = new ArrayList<>();
        getFrontera(resultado, root);
        return resultado;
    }

    // Complejidad es O(n) - Recorre todos los nodos del árbol
    private void getFrontera(List<Integer> resultado, TreeNode actual) {
        if (actual != null) {
            if (actual.esHoja())
                resultado.add(actual.getValue().getTiempoEjecucion());
            else {
                getFrontera(resultado, actual.getLeft());
                getFrontera(resultado, actual.getRight());
            }
        }
    }

    public Integer getMaxElement() {
        return getMaxElement(root);
    }

    private Integer getMaxElement(TreeNode actual) {
        if (actual == null)
            return null;
        if (actual.getRight() != null)
            return getMaxElement(actual.getRight());
        else {
            return actual.getValue().getTiempoEjecucion();
        }
    }

    public List<Integer> getElementAtLevel(int lvl) {
        List<Integer> resultado = new ArrayList<>();
        getElementAtLevel(lvl, root, resultado, 0);
        return resultado;
    }

    private void getElementAtLevel(int lvl, TreeNode actual, List<Integer> resultado, int contador) {
        if (actual != null) {
            if (contador == lvl) {
                resultado.add(actual.getValue().getTiempoEjecucion());
            } else {
                getElementAtLevel(lvl, actual.getLeft(), resultado, contador + 1);
                getElementAtLevel(lvl, actual.getRight(), resultado, contador + 1);
            }
        }
    }

    public TreeNode getNodoMenorIzquierdo(TreeNode actual) {
        // Recorre el subárbol hacia la izquierda hasta encontrar el nodo más pequeño
        if (actual.getLeft() != null) {
            actual = actual.getLeft();
        }
        return actual;
    }

    public List<Tarea> getTareasEntreNivelDePrioridad(Integer limiteInferior, Integer limiteSuperior) {
        ArrayList<Tarea> result = new ArrayList<>();
        getTareasEntreNivelDePrioridad(this.root, limiteInferior, limiteSuperior, result);
        return result;
    }

    private void getTareasEntreNivelDePrioridad(TreeNode actual, Integer limiteInferior, Integer limiteSuperior, List<Tarea> result) { //O(n) n:elementos del arbol
        if (actual != null) {
            if ((actual.getValue().getNivelPrioridad() > limiteInferior) && (actual.getValue().getNivelPrioridad() < limiteSuperior)) result.add(actual.getValue());
            getTareasEntreNivelDePrioridad(actual.getLeft(), limiteInferior, limiteSuperior, result);
            getTareasEntreNivelDePrioridad(actual.getRight(), limiteInferior, limiteSuperior, result);
        }
    }

//    public boolean delete(Integer value) {
//        if (value != null) {
//            root = delete(root, value);
//            // Se actualiza el nodo raíz en caso de que se elimine
//            // Retorna true para indicar que se eliminó el valor
//            return true;
//        }
//        // Si el valor es nulo, retorna false porque no se puede eliminar
//        return false;
//    }

//    private TreeNode delete(TreeNode actual, Integer value) {
//        // Comprueba si el valor a eliminar es menor que el valor del nodo actual
//        if (value < actual.getValue()) {
//            // Si es menor, se llama recursivamente al método delete con el nodo izquierdo
//            actual.setLeft(delete(actual.getLeft(), value));
//        } else if (value > actual.getValue()) {
//            // Si es mayor, se llama recursivamente al método delete con el nodo derecho
//            actual.setRight(delete(actual.getRight(), value));
//        } else {
//            // Si el valor es igual al valor del nodo actual, se procede a eliminarlo
//            // Caso 1: El nodo no tiene hijo izquierdo
//            if (actual.getLeft() == null) {
//                // Retorna el hijo derecho del nodo actual (puede ser nulo o no)
//                return actual.getRight();
//            }
//            // Caso 2: El nodo no tiene hijo derecho
//            else if (actual.getRight() == null) {
//                // Retorna el hijo izquierdo del nodo actual
//                return actual.getLeft();
//            }
//            // Caso 3: El nodo tiene dos hijos
//
//            // Encuentra el nodo con el valor mínimo en el subárbol derecho
//            TreeNode nodoMenor = getNodoMenorIzquierdo(actual.getRight());
//            // Copia el valor mínimo al nodo actual
//            actual.setValue(nodoMenor.getValue());
//            // Elimina el nodo con el valor mínimo del subárbol derecho
//            actual.setRight(delete(actual.getRight(), nodoMenor.getValue()));
//
//        }
//        // Retorna el nodo actual modificado
//        return actual;
//    }
}