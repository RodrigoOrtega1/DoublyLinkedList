import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Actividad6 {

    public static int arrayEncuentraValorMitad(ArrayList<String> arrayList, int b){
        int mitad = (arrayList.size()/2) + 1;
        
        if (b < 0 || b > arrayList.size() || b > mitad || !arrayList.contains(arrayList.get(b))){
            arrayList.get(b);
            return -1;
        } else {
            return 1;
        }
    }
        


    public static void main(String[] args){
        long inicio;
        long fin;
        
        Random random = new Random();
        SimpleLinkedList<String> listaSimple = new SimpleLinkedList<>();
        DoubleLinkedList<String> listaDoble = new DoubleLinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        int n = 0;
        while(n < 100000){
            listaDoble.add(listaDoble.size(), (random.ints(1000, 10, 1000).toString()));
            listaSimple.add(listaSimple.size(), (random.ints(1000, 10, 1000).toString()));
            arrayList.add(random.ints(1000, 10, 1000).toString());    
            n++;
        }
        
        System.out.println(listaDoble.size());
        System.out.println(listaSimple.size());
        System.out.println(arrayList.size());

        //Lista simplemente ligada
        inicio = System.currentTimeMillis();
        //listaSimple.add(random.nextInt(listaSimple.size()), "a");
        //listaSimple.revert();
        //listaSimple.clear();
        //listaSimple.get(random.nextInt(listaSimple.size()));
        //listaSimple.encuentraValorMitad(5000);
        fin = System.currentTimeMillis();
        System.out.println("Lista simplemente ligada: " + (fin - inicio) + "ms.");
        
        //Lista doblemente ligada
        inicio = System.currentTimeMillis();
        //listaDoble.add(random.nextInt(listaDoble.size()), "a");
        //listaDoble.revert();
        //listaSimple.clear();
        //listaDoble.get(random.nextInt(listaDoble.size()));
        listaDoble.encuentraValorMitad(5000);
        fin = System.currentTimeMillis();
        System.out.println("Lista doblemente ligada: " + (fin - inicio) + "ms.");

        //ArrayList
        inicio = System.currentTimeMillis();
        //Collections.reverse(arrayList);
        //arrayList.clear();
        //arrayList.get(random.nextInt(arrayList.size()));
        //arrayEncuentraValorMitad(arrayList, 5000);
        //fin = System.currentTimeMillis();
        System.out.println("Array list: " + (fin - inicio) + "ms.");

        

    }
}
