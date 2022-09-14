import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de la interfaz TDAList
 * @author Ortega Venegas Rodrigo Aldair 318036104
 * @version 1.0 13-9-2022
 * @since Estructuras de datos 2023-1. Práctica 2.
 */

public class DoubleLinkedList<T> implements TDAList<T>{

    private class Nodo{
        //Elemento del nodo
        public T elemento;
        //Nodo siguiente
        public Nodo siguiente;
        //Nodo anterior
        public Nodo anterior;

        //Constructor de clase Nodo
        public Nodo(T elemento){
            this.elemento = elemento;
        }

        //Accede al elemento del nodo
        public T getElemento(){
            return elemento;
        }

        //Accede al anterior del nodo
        public Nodo getAnterior(){
            return anterior;
        }

        //Accede al siguiente del nodo
        public Nodo getSiguiente(){
            return siguiente;
        }

        //Permite cambiar el anterior del nodo
        public void setAnterior(Nodo nuevoAnterior){
            anterior = nuevoAnterior;
        }

        //Permite cambiar el siguiente del nodo
        public void setSiguiente(Nodo nuevoSiguiente){
            siguiente = nuevoSiguiente;
        }
    }

    //Atributos de clase DoubleLinkedList
    private Nodo cabeza;
    private Nodo cola;
    private int tamano;

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {

        // 'i' no está en el rango de la lista
        if(i < 0 || i > size()){
            throw new IndexOutOfBoundsException("'i' debe en el rango de la lista");
        }

        Nodo nuevo = new Nodo(e);
        // Cuando está vacía
        if(isEmpty()){
            cabeza = nuevo;
            cola = nuevo;
            tamano++;
            return;
        }

        // Cuando está al inicio de la lista
        else if(i == 0){
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
            tamano++;
            return;
        }

        // Agregar al final de la lista
        else if(i == tamano){
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
            tamano++;
            return;
        }

        // Si 'i' está en la primera mitad de la lista o justo en medio
        else if(i < tamano/2 || i == tamano/2){
            Nodo pointer = cabeza;
            for(int j = 0; j < i - 1; j++){
                pointer = pointer.getSiguiente();
            }
            nuevo.setSiguiente(pointer.siguiente);
            nuevo.setAnterior(pointer);
            pointer.siguiente.setAnterior(nuevo);
            pointer.setSiguiente(nuevo);
            tamano ++;
            return;
        }

        // Si 'i' está en la segunda mitad de la lista
        else if(i > tamano/2){
            Nodo pointer = cola;
            for(int j = size(); j > i + 1; j--){
                pointer = pointer.getAnterior();
            }
            nuevo.setAnterior(pointer.anterior);
            nuevo.setSiguiente(pointer);
            pointer.anterior.setSiguiente(nuevo);
            pointer.setAnterior(nuevo);
            tamano ++;
            return;
        }
    }

    @Override
    public void clear() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    @Override
    public boolean contains(T e) {
        // Si la lista está vacía
        if(isEmpty()){
            System.out.println("La lista está vacía");
            return false;
        }

        Nodo pointerHead = cabeza;
        Nodo pointerTail = cola;
        Nodo aComparar = new Nodo(e);
        
        if(aComparar.getElemento().equals(cabeza.getElemento())){
            return true;
        }
        
        else if(aComparar.getElemento().equals(cola.getElemento())){
            return true;
        }
        
        // Si la el tamano de la lista es par
        if(tamano%2 == 0){
            for(int i = 0; i < tamano/2; i++){
                pointerHead = pointerHead.getSiguiente();
                if (aComparar.getElemento().equals(pointerHead.getElemento())){
                    return true;
                } else continue;
            }
            for(int j = size(); j > tamano/2; j--) {
                pointerTail = pointerTail.getAnterior();
                if(aComparar.getElemento().equals(pointerTail.getElemento())){
                    return true;
                } else continue;
            }
        }

        // Si el tamano de la lista es impar
        else{
            for(int i = 0; i <= tamano/2; i++){
                pointerHead = pointerHead.getSiguiente();
                if (aComparar.getElemento().equals(pointerHead.getElemento())){
                    return true;
                } else continue;
            }
            for(int j = size(); j >= tamano/2; j--) {
                pointerTail = pointerTail.getAnterior();
                if(aComparar.getElemento().equals(pointerTail.getElemento())){
                    return true;
                } else continue;
            }
        }
        return false;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException {

        // Si 'i' está fuera del rango de la lista
        if(i < 0 || i > tamano){
            throw new IndexOutOfBoundsException("'i' debe estar en el rango de la lista");
        }

        // Si la lista está vacía
        if(isEmpty()){
            System.out.println("La lista está vacía");
            return null;
        }

        if(i == 0){
            return cabeza.getElemento();
        }

        else if(i == size()){
            return cola.getElemento();
        }

        // Si 'i' está en la primera mitad de la lista o exactamente a la mitad
        else if(i < tamano/2 || i == tamano/2){
            Nodo pointer = cabeza;
            for(int j = 0; j < i; j++){
                pointer = pointer.getSiguiente();
            }
            return pointer.getElemento();
        }

        // Si 'i' está en la segunda mitad de la lista
        else if(i > tamano/2){
            Nodo pointer = cola;
            for(int j = tamano - 1; j > i; j--){
                pointer = pointer.getAnterior();
            }
            return pointer.getElemento();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return cabeza == null;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        // Caso en el que 'i' no está en el rango de la lista
        if(i < 0 || i > tamano){
            throw new IndexOutOfBoundsException("'i' debe estar dentro del rango de la lista");
        }

        T remove = null;

        // Caso donde elimina el inicio
        if(i == 0){
            remove = cabeza.getElemento();
            cabeza = cabeza.getSiguiente();
            //cabeza.setAnterior(null);
            tamano--;
            return remove;
        }

        // Caso donde elimina al ultimo de la lista
        else if(i == tamano ){
            remove = cola.getElemento();
            cola = cola.getAnterior();
            cola.setSiguiente(null);
            tamano--;
            return remove;
        }

        // Caso donde itera por la cabeza
        else if(i < tamano/2){
            Nodo iterador = cabeza;
            for (int j = 0; j < i - 1; j++) {
                iterador = iterador.getSiguiente();
            }
            // Nodo auxiliar para ubicar las referencias
            Nodo aux = iterador.getSiguiente();
            // Pasamos referencia del nodo al siguiente
            iterador.setSiguiente(aux.getSiguiente());
            // Del nodo siguiente de aux ponemos su referencia al anterior del iterador
            Nodo aux_0 = aux.getSiguiente();
            aux_0.setAnterior(aux.getAnterior());
            tamano--;
            remove = (T)aux_0.getElemento();
        }

        // Caso donde itera por la cola
        else if(i > tamano/2 || i == tamano/2){
            Nodo iterador1 = cola;
            for(int k = tamano; k > i + 1; k--){
                iterador1 = iterador1.getAnterior();
            }
            Nodo aux1 = iterador1.getAnterior();
            iterador1.setAnterior(aux1.getAnterior());
            //Del nodo anterior ponemos el siguiente al siguiente del iterador
            Nodo aux_1 = aux1.getAnterior();
            aux_1.setSiguiente(aux1.getSiguiente());
            tamano--;
            remove = (T)aux_1.getElemento();
        }
        return remove;
    }

    @Override
    public int size() {
        return tamano;
    }

    //@Override
    public void revert() {
        // Si la lista está vacía
        if (tamano == 0){
            System.out.println("La lista está vacía");
            return;
        }

        // Si la lista tiene un solo elemento
        if(tamano == 1){
            return;
        }

        Nodo temp = null;
        Nodo actual = cabeza;
        while (actual != null){
            temp = actual.getSiguiente();
            actual.setSiguiente(actual.getAnterior());
            actual.setAnterior(temp);
            //temp.setAnterior(actual);
            actual = actual.getAnterior();
        }

        temp = cabeza;
        cabeza = cola;
        cola = temp;
    }

   //@Override
    public TDAList<T> cut(boolean side) {
        DoubleLinkedList<T> lista = new DoubleLinkedList<>();
        // Mitad derecha
        Nodo pointer;
        int indice = 0;
        if(side == true){
            pointer = cola;
            for(int i = tamano; i > tamano/2; i--){
                lista.add(indice, pointer.getElemento());
                pointer = pointer.getAnterior();
                indice++;
            }

        }
        //Mitad izquierda
        else {
            pointer = cabeza;
            for(int i = 0; i < tamano/2; i++){
                lista.add(indice, pointer.getElemento());
                pointer = pointer.getSiguiente();
                indice++;
            }
        }
        return lista;
    }

    @Override
    public String toString(){
        String formato = "";
        Nodo iterador = cabeza;
        
        while(iterador != null){
            formato += iterador.getElemento() + "\n";
            iterador = iterador.getSiguiente();
        }
        return formato;
    }

    public class DoubleListIterator implements Iterator<T>{

        private Nodo actual = cabeza;

        public boolean hasNext() {
            return actual != null;
        }

        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T temp = actual.getElemento();
            actual = actual.getSiguiente();
            return temp;
        }
    }

    //@Override
    public Iterator<T> listIterador() {
        return new DoubleListIterator();
    }
}
