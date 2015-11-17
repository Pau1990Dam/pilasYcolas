package Gen;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @param <E>
 */
public class Cola <E> {
    private int inicio=-1;
    private int fin=-1;
    private int tamanio;
    private E[] cola;

    /**
     *
     * @param arr
     */
    public Cola(E [] arr) {
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null) fin=i;
        }
        if(fin>=0)inicio=0;
        else inicio = -1;
        tamanio=arr.length;
        cola =arr;
    }

    /**
     *
     * @param value
     * @throws ColaLlenaException
     */
    public void add(E value) throws ColaLlenaException {
        if(fin>=inicio && fin<inicio+tamanio) {
            if(inicio==-1)inicio=0;
            cola[++fin % tamanio] = value;
        }else
            throw new ColaLlenaException ("La cola esta llena, no se puede añadir ningún número más.");
    }

    /**
     *
     * @return
     * @throws ColaVaciaException
     */
    public E get() throws ColaVaciaException {

        if(inicio<=fin){
            if(inicio==tamanio){
                inicio=0;
                fin-=tamanio;
                return cola[inicio++];

            }else if(inicio==-1&&fin>=0)
                inicio=0;

            return cola[inicio++%tamanio];
        }
        else
            throw new ColaVaciaException("La cola esta vacia");
    }

    /**
     *
     * @param <E>
     * @return
     * @throws ColaVaciaException
     */
    public <E extends Comparable<E>> E getMax() throws ColaVaciaException {
        E max;
        if(fin==-1 || inicio>fin || fin>=(inicio+tamanio)|| fin==inicio&&fin>0) throw new ColaVaciaException("La cola esta vacia.");
        else{
            max=(E)cola[inicio];
            if(fin<tamanio){
                for(int i=inicio;i<=fin;i++){
                    if(max.compareTo((E)(cola[i]))<0)max=(E)cola[i];
                }
                return max;
            }else{
                for(int i=inicio;i<tamanio;i++){
                    if(max.compareTo((E)(cola[i]))<0)max=(E)cola[i];
                }
                for(int i=0;i<=fin%tamanio;i++){
                    if(max.compareTo((E)(cola[i]))<0)max=(E)cola[i];
                }
                return max;
            }
        }
    }

    /**
     *
     * @param <E>
     * @return
     * @throws ColaVaciaException
     */
    public <E extends Comparable<E>> E getMin() throws ColaVaciaException {
        E min;
        if(fin==-1 || inicio>fin || fin>=(inicio+tamanio)|| fin==inicio&&fin>0) throw new ColaVaciaException("La cola esta vacia.");
        else{
            min=(E)cola[inicio];
            if(fin<tamanio){
                for(int i=inicio;i<=fin;i++){
                    if(min.compareTo((E)(cola[i]))>0)min=(E)cola[i];
                }
                return min;
            }else{
                for(int i=inicio;i<tamanio;i++){
                    if(min.compareTo((E)(cola[i]))>0)min=(E)cola[i];
                }
                for(int i=0;i<=fin%tamanio;i++){
                    if(min.compareTo((E)(cola[i]))>0)min=(E)cola[i];
                }
                return min;
            }
        }
    }

    /**
     *
     * @param num
     * @return
     * @throws ColaVaciaException
     */
    public int getNumPos(E num) throws ColaVaciaException {
        if(fin==-1 || inicio>fin || fin>=(inicio+tamanio)|| fin==inicio&&fin>0) throw new ColaVaciaException("La cola " +
                "esta vacia.");
        else{
            if(fin<tamanio){
                for(int i=inicio;i<=fin;i++){
                    if(num.equals(cola[i]))return i;
                }
                return -1;
            }else{
                for(int i=inicio;i<tamanio;i++){
                    if(num.equals(cola[i]))return i;
                }
                for(int i=0;i<=fin%tamanio;i++){
                    if(num.equals(cola[i]))return i;
                }
                return -1;
            }
        }
    }

    public static void main(String[] args) throws ColaVaciaException, ColaLlenaException {
        //Probado también con Character e Integer
        Scanner entrada= new Scanner(System.in);
        Double num=0.1;
        Double array[]={2.2, 3.8, 4.9, 6.6, 3.0, 1.1, 2.5, 15.32, 8.0, 10.0, 11.2, 15.09, 92.1, 4.3, 7.7};//15 números
        DecimalFormat formato= new DecimalFormat("#0.00");
        Cola cola = new Cola(array);
        System.out.println("Valor Máximo de la cola:"+cola.getMax());
        System.out.println("Valor Mínimo de la cola:"+cola.getMin());
        System.out.println("Posición del double 3.0 en la cola: "+cola.getNumPos(3.0));
        System.out.println("Posición del double 8.6 en la cola: "+cola.getNumPos(8.6));

        System.out.println("\nQuitamos los primeros 10 números de la cola (los que aparecen por pantalla):");
        for(int i=0;i<10;i++){
            System.out.print("\t" + formato.format(cola.get()));
        }
        System.out.print("\n\tMax :" + cola.getMax());
        System.out.print("\tMin :" + cola.getMin());
        System.out.println("\n\tEn que posición se encuentra el número 92.1 ?  " + cola.getNumPos(92.1));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + cola.getNumPos(20));
        System.out.println("\nLlenamos las 10 posiciones que hemos vaciado con otros 10 números: ");
        for(int i=0;i<10;i++){
            num=(Double)((Math.random())*99+1);
            System.out.print("\t"+formato.format(num));
            cola.add(num);
        }
        System.out.print("\n\tMax :" + cola.getMax());
        System.out.print("\tMin :" + cola.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " +formato.format(num)  + " ? " + cola.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + cola.getNumPos(20));
        System.out.println("\nVaciamos la cola entera: ");
        for(int i=0;i<15;i++){
            System.out.print("\t" + formato.format(cola.get()));
        }
        System.out.println("\nAhora presiona cualquier tecla para ver que pasa cuando se intenta extraer algo" +
                " de una cola vacia: ");
        entrada.nextLine();
        System.out.println(cola.get());
    }

}


class ColaVaciaException extends Exception {
    public ColaVaciaException() {
        super();
    }

    public ColaVaciaException(String mensage) {
        super(mensage);
    }

}
class ColaLlenaException extends Exception {
    public ColaLlenaException() {
        super();
    }

    public ColaLlenaException(String mensage) {
        super(mensage);
    }

}

