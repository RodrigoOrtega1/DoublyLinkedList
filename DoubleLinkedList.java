import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de la interfaz TDAList
 * @author Ortega Venegas Rodrigo Aldair 318036104
 * @author San Martin Macias Juan Daniel 318181637
 * @version 1.0 13-9-2022
 * @since Estructuras de datos 2023-1. Práctica 2.
 */

public class DoubleLinkedList<T> implements TDAList<T>{

    /**
     * Clase interna Nodo
     */
    private class Nodo{
        
        private T elemento;
        private Nodo siguiente;
        private Nodo anterior;

        /**
         * Constructor de la clase Nodo
         * @param elemento el elemento que contendrá el nuevo Nodo.
         */
        public Nodo(T elemento){
            this.elemento = elemento;
        }

        /**
         * Regresa el elemento almacenado en el Nodo
         * @return el elemento almacenado en el Nodo
         */
        public T getElemento(){
            return this.elemento;
        }

        /**
         * Regresa el Nodo anterior.
         * @return el Nodo anterior
         */
        public Nodo getAnterior(){
            return this.anterior;
        }

        /**
         * Regresa el Nodo siguiente.
         * @return el Nodo siguiente
         */
        public Nodo getSiguiente(){
            return this.siguiente;
        }

        /**
         * Asigna el Nodo anterior
         * @param nuevoAnterior el nuevo nodo a asignar
         */
        public void setAnterior(Nodo nuevoAnterior){
            anterior = nuevoAnterior;
        }

        /**
         * Permite cambiar el siguiente del nodo
         * @param nuevoSiguiente el nuevo nodo a asignar
         */
        public void setSiguiente(Nodo nuevoSiguiente){
            siguiente = nuevoSiguiente;
        }
    }

    //Atributos de clase DoubleLinkedList
    private Nodo cabeza;
    private Nodo cola;
    private int tamano;

    /**
     * Método que agrega un elemento a la lista en una posicion designada.
     * @param i posicion en la que se agregará el nuevo elemento
     * @param e elemento que se agregará a la lista 
     * @throws IndexOutOfBoundsException  si 'i' no está en el rango de la lista
     */
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

    /**
     * Método que se encarga de limpiar la lista
     */
    @Override
    public void clear() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    /**
     * Método encargado de buscar en la lista un elemento dado.
     * @param e el elemento a buscar.
     * @return True si el elemento sí se encuentra en la lista, false en cualquier otro caso.
     */
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

    /**
     * Regresa el elemento en la posicion que se indica
     * @param i la posicion del elemento que se busca.
     * @return el elemento en la posicion i
     * @throws IndexOutOfBoundsException si el elemento está fuera del rango de la lista
     */
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

    /**
     * Método que se encarga de revisar si la lista está vacía o no
     * @return True si la lista tiene al menos un elemento, False en cualquier otro caso
     */
    @Override
    public boolean isEmpty() {
        return cabeza == null;
    }

    /**
     * Método encargado de eliminar el Nodo en la posción dada
     * @param i la posicion donde se encuentra el nodo a eliminar
     * @return el elemento eliminado
     * @throws IndexOutOfBoundsException en caso en el que 'i' no está en el rango de la lista
     */
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

    /**
     * Método encargado de regresar el tamaño de la lista
     * @return el tamaño de la lista
     */
    @Override
    public int size() {
        return this.tamano;
    }

    /**
     * Método encargado de invertir la lista 
     */
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

    /**
     * Método que se encarga de unir dos listas
     * @param listaB lista que se unirá con la actual
     */
    public void unirListas(DoubleLinkedList<T> listaB){
        Nodo actualA = this.cabeza;
        Nodo actualB = listaB.cabeza;
        Nodo auxA = null;
        Nodo auxB = null;
        while(actualA != null && actualB != null){
            auxA = actualA.getSiguiente();
            auxB = actualB.getSiguiente();
            actualA.setSiguiente(actualB);
            actualB.setAnterior(actualA);
            actualA = auxA;
            actualB = auxB;

        }
    }

    /**
     * Método que se encarga de buscar en la primera mitad de la lista
     * @param B el elemento a buscar
     * @return 1 si el elemento se encuentra en la lista, -1 en caso de que no.
     */
    public int encuentraValorMitad(int b){
        /* if(B < 0 || B > tamano){
            throw new IndexOutOfBoundsException("'i' debe estar dentro del rango de la lista");
        }
        else{
            int longitud = this.size();
            int mitad = (longitud/2) + 1;
            Nodo aux = new Nodo(get(B));
            for (int i = 0; i < mitad; i++){
                if(this.get(i) == aux){
                    return 1;
                }
            }
            return -1;
        } */
        if(b < 0 || b > tamano || !contains(get(b))){
            return -1;
        }
        int mitad = (tamano / 2) + 1;
        if (b <= mitad){
            System.out.println("Elemento en indice " + b + ": " + get(b));
        } else {
            return -1;
        }
        return -1;
    }

    /**
     * Clase interna encargada del iterator en una lista doblemente ligada
     */
    public class DoubleListIterator implements Iterator<T>{

        private Nodo actual = cabeza;

        /**
         * Método que verifica si el Nodo que lo manda a llamar tiene un siguiente
         * @return True si tiene un Nodo siguiente, False en cualquier otro caso
         */
        public boolean hasNext() {
            return actual != null;
        }

        /**
         * Método que accede al Nodo siguiente
         * @return el siguiente Nodo
         */
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T temp = actual.getElemento();
            actual = actual.getSiguiente();
            return temp;
        }
    }

    /**
     * Método que regresa el iterador de la lista
     * @return el iterador de la lista.
     */
    public Iterator<T> listIterador() {
        return new DoubleListIterator();
    }
}
