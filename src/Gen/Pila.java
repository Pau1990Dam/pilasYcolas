package Gen;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Pila <E>{
    private final int tamanio;
    private int tope=-1;
    private E[] elementos;

    /**
     *
     * @param arr
     */
    public Pila( E[]arr )
    {
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null)tope=i;
        }
        tamanio=arr.length;
        elementos=arr;
    }

    /**
     *
     * @param value
     * @throws ExcepcionPilaLlena
     */
    public void add( E value ) throws ExcepcionPilaLlena {
        if ( tope == tamanio - 1 ) // si la pila está llena
            throw new ExcepcionPilaLlena("La Pila esta llena, no se puede meter "+value  );
        elementos[++tope] = value; // coloca valorAMeter en la Pila
    }

    /**
     *
     * @return
     * @throws ExcepcionPilaVacia
     */
    public E get() throws ExcepcionPilaVacia {
        if ( tope == -1 ) // si la pila está vacía
            throw new ExcepcionPilaVacia( "La Pila esta vacia, no se puede sacar ningún valor" );
        return elementos[ tope-- ]; // elimina y devuelve el elemento superior de la Pila
    }

    /**
     *
     * @param <E>
     * @return
     * @throws ExcepcionPilaVacia
     */
    public <E extends Comparable <E>> E getMax () throws ExcepcionPilaVacia{
        if(tope==-1){
            throw new ExcepcionPilaVacia( "La Pila esta vacia, no se puede mostrar ningún valor ");
        }
        E max= (E) elementos[tope];
        for(int i=0; i<=tope;i++){
            if(max.compareTo((E)(elementos[i]))<0)max=(E)(elementos[i]);
        }
        return max;
    }

    /**
     *
      * @param <E>
     * @return
     * @throws ExcepcionPilaVacia
     */
    public <E extends Comparable <E>> E getMin () throws ExcepcionPilaVacia{
        if(tope==-1){
            throw new ExcepcionPilaVacia( "La Pila esta vacia, no se mostrar ningún valor ");
        }
        E min= (E) elementos[tope];
        for(int i=0; i<=tope;i++){
            if(min.compareTo((E)(elementos[i]))>0)min=(E)(elementos[i]);
        }
        return min;
    }

    /**
     *
     * @param value
     * @return
     * @throws ExcepcionPilaVacia
     */
    public int geNumPos(E value)throws ExcepcionPilaVacia{
        if(tope==-1) throw new ExcepcionPilaVacia( "La Pila esta vacia" );
        for(int i=0;i<=tope;i++){
            if(value.equals(elementos[i]))
                return i;
        }
        return -1;
    }


    public static void main(String[] args) throws ExcepcionPilaLlena, ExcepcionPilaVacia {
        //Probado también con Character e Integer
        Scanner entrada= new Scanner(System.in);
        Integer enteros[]={1,2,3,4,8,12,0,6,10};
        Integer num;
        DecimalFormat formato= new DecimalFormat("#00");
        Pila p= new Pila (enteros);
        System.out.println("Valor máximo de la pila: "+p.getMax());
        System.out.println("Valor mínimo de la pila:  "+p.getMin());
        System.out.println("Posición del entero 6 en la pila: "+p.geNumPos(6));
        System.out.println("\nDesapilamos todos los elemtos de la pila: ");
        for(int i=0;i<9;i++){
            System.out.print("\t"+formato.format(p.get()));
        }
        System.out.println("\nApilamos nuevos elementos: ");
        for(int i=0;i<9;i++){
            num=(int)((Math.random())*99+1);
            System.out.print("\t" + formato.format(num));
            p.add(num);
        }
        System.out.println("Valor máximo de la pila: "+p.getMax());
        System.out.println("Valor mínimo de la pila:  "+p.getMin());
        System.out.println("Posición del entero 6 en la pila: "+p.geNumPos(6));

        System.out.println("\nPresiona cualquier tecla para ver lo que pasa si intentamos apilar un nuevo elemeto en una " +
                "pila llena");
        entrada.nextLine();
        p.add(5);
    }
}

class ExcepcionPilaLlena extends Exception{
    public ExcepcionPilaLlena(){
        super();
    }

    public ExcepcionPilaLlena(String mensage){
        super(mensage);
    }

}
class ExcepcionPilaVacia extends Exception{
    public ExcepcionPilaVacia(){
        super();
    }

    public ExcepcionPilaVacia(String mensage){
        super(mensage);
    }

}