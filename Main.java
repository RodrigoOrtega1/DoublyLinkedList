import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        DoubleLinkedList<String> lista = new DoubleLinkedList<>();

        lista.add(0,"elemento1");
        lista.add(1,"elemento2");
        lista.add(2,"elemento3");
        lista.add(3,"elemento4");
        System.out.println(lista);

        Scanner inputScanner  = new Scanner(System.in);

        do{
            System.out.println("Menu:\n" +
                               "[1]Agregar una cadena a la lista\n" +
                               "[2]Eliminar una cadena de la lista\n" +
                               "[3]Limpiar la lista\n" +
                               "[4]Verificar si un elemento está en la lista\n" +
                               "[5]Obtener un elemento de la lista\n" +
                               "[6]Verificar si la lista está vacía\n" +
                               "[7]Obtener la longitud de la lista\n" +
                               "[8]Obtener la reversa de la lista\n" +
                               "[9]Cortar la lista\n" +
                               "[10]Mostrar la lista\n" +
                               "[11]Salir del menu");
            int userInput = inputScanner.nextInt();
            int i;
            String e = "";
            switch (userInput){
                case 1:
                    try{
                        System.out.println("Escribe en que posición quieres agregar el elemento:");
                        i = inputScanner.nextInt();
                        inputScanner.nextLine();
                        System.out.println("Escribe la String a agregar:");
                        e = inputScanner.nextLine();
                        lista.add(i,e);
                    }
                    catch (InputMismatchException error){
                        throw new InputMismatchException("Por favor inserta un tipo de dato válido");
                    }
                    break;
                case 2:
                    try{
                        System.out.println("Elige el índice a eliminar");
                        i = inputScanner.nextInt();
                        System.out.println("Elemento que eliminaste: " + lista.remove(i));
                    }
                    catch (InputMismatchException error){
                        throw new InputMismatchException("Por favor inserta un tipo de dato válido");
                    }
                    break;
                case 3:
                    lista.clear();
                    break;
                case 4:
                    try{
                        System.out.println("Elemento a buscar:");
                        inputScanner.nextLine();
                        e = inputScanner.nextLine();
                        System.out.println("El elemento está en la lista: " + lista.contains(e));
                    }
                    catch (InputMismatchException error){
                        throw new InputMismatchException("Por favor inserta un tipo de dato válido");
                    }
                    break;
                case 5:
                    try{
                        System.out.println("Posición a obtener:");
                        i = inputScanner.nextInt();
                        System.out.println("En la posición " + i + " está el elemento: " + lista.get(i));
                    }
                    catch(InputMismatchException error){
                        throw new InputMismatchException("Por favor inserta un tipo de dato válido");
                    }
                    break;
                case 6:
                    System.out.println("Está vacía?: " + lista.isEmpty());
                    break;
                case 7:
                    System.out.println("Numero de elementos en la lista: " + lista.size());
                    break;
                case 8:
                    lista.revert();
                    System.out.println(lista);
                    break;
                case 9:
                    try{
                        System.out.println("Cortar a la izquierda: false, cortar a la derecha: true");
                        boolean side = inputScanner.nextBoolean();
                        System.out.println(lista.cut(side));
                    }
                    catch (InputMismatchException error){
                        throw new InputMismatchException("Por favor inserta un tipo de dato válido");
                    }
                    break;
                case 10:
                    System.out.println(lista);
                    break;
                case 11:
                    inputScanner.close();
                    return;
                default:
                    System.out.println("Numero inválido");
            }
        } while(true);
    }
}
