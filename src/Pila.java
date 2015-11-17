import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by 14270729b on 13/11/15.
 */
public class Pila {
    private int tamanio;
    private int ultimoElemento;
    private int arr[];

    /**
     *
     */
    public Pila(){
        ultimoElemento=-1;
        tamanio=10;
        arr=new int[tamanio];
    }

    /**
     *
     * @param tamanio
     */
    public Pila(int tamanio){
        if(tamanio<0)tamanio=10;
        ultimoElemento=-1;
        this.tamanio=tamanio;
        arr=new int[tamanio];
    }

    /**
     *
     * @param num
     * @throws ExcepcionPilaLlena
     */
    public void add(int num) throws ExcepcionPilaLlena {
        if(ultimoElemento==tamanio-1){
            throw new ExcepcionPilaLlena("Pila llena");
        }
        arr[++ultimoElemento]=num;
    }

    /**
     *
     * @return
     * @throws ExcepcionPilaVacia
     */
    public int get() throws ExcepcionPilaVacia {
        if(ultimoElemento==-1)
            throw new ExcepcionPilaVacia("Pila vacia");

        return arr[ultimoElemento--];
    }

    /**
     *
      * @return
     * @throws ExcepcionPilaVacia
     */
    public int getMax() throws ExcepcionPilaVacia {
        if(ultimoElemento==-1){
            throw new ExcepcionPilaVacia("Pila vacia");
        }else {
            int max = arr[ultimoElemento];
            for (int i = 0; i <= ultimoElemento; i++) {
                if (max < arr[i]) max = arr[i];
            }
            return max;
        }
    }

    /**
     *
      * @return
     * @throws ExcepcionPilaVacia
     */
    public int getMin() throws ExcepcionPilaVacia {
        if(ultimoElemento==-1){
            throw new ExcepcionPilaVacia("Pila vacia");
        }else {
            int min = arr[ultimoElemento];
            for (int i = 0; i <= ultimoElemento; i++) {
                if (min > arr[i]) min = arr[i];
            }
            return min;
        }
    }

    /**
     *
     * @param num
     * @return
     * @throws ExcepcionPilaVacia
     */
    public int getNumPos(int num) throws ExcepcionPilaVacia {
        if(ultimoElemento==-1){
            throw new ExcepcionPilaVacia("Pila vacia");
        }else{
            for(int i=0; i<=ultimoElemento;i++){
                if(num==arr[i])return i;
            }
            return -1;
        }
    }

    /**
     *
     * @param args
     * @throws ExcepcionPilaLlena
     * @throws ExcepcionPilaVacia
     */
    public static void main(String[] args) throws ExcepcionPilaLlena, ExcepcionPilaVacia {
        Scanner entrada = new Scanner(System.in);
        Pila pila = new Pila(15);
        DecimalFormat formato= new DecimalFormat("#00");
        int num=0;
        System.out.println("Llenamos la pila con los siguientes 15 números y en el orden que aparecen:");
        for(int i=0;i<15;i++){
            num=(int)((Math.random())*99+1);
            System.out.print("\t" + formato.format(num));
            pila.add(num);

        }
        System.out.print("\n\tMax :" + pila.getMax());
        System.out.print("\tMin :" + pila.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " + num + " ? " + pila.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + pila.getNumPos(20));

        System.out.println("\nQuitamos los últimos 10 números de la pila (los que aparecen por pantalla):");
        for(int i=0;i<10;i++){
            System.out.print("\t" + formato.format(pila.get()));
        }
        System.out.print("\n\tMax :" + pila.getMax());
        System.out.print("\tMin :" + pila.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " + num + " ? " + pila.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " +pila.getNumPos(20));
        System.out.println("\nLlenamos 10 posiciones vacias con otros 10 números: ");
        for(int i=0;i<10;i++){
            num=(int)((Math.random())*99+1);
            System.out.print("\t"+formato.format(num));
            pila.add(num);
        }
        System.out.print("\n\tMax :" + pila.getMax());
        System.out.print("\tMin :" + pila.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " + num + " ? " + pila.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + pila.getNumPos(20));
        System.out.println("\nVaciamos la pila entera: ");
        for(int i=0;i<15;i++){
            System.out.print("\t" + formato.format(pila.get()));
        }
        System.out.println("\n\nAhora presiona cualquier tecla para ver que pasa cuando se intenta extraer algo" +
                "de una pila vacia: ");
        entrada.nextLine();
        System.out.println(pila.get());
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