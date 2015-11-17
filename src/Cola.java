/**
 * Created by pau on 17/11/15.
 */
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by pau on 14/11/15.
 */
public class Cola {
    private int inicio;
    private int fin;
    private int arr[];
    private int tamanio;

    /**
     *
     */
    public Cola(){
        inicio=-1;
        fin=-1;
        tamanio=10;
        arr=new int [tamanio];
    }

    /**
     *
     * @param tamanio
     */
    public Cola (int tamanio){
        if(tamanio<0)tamanio=10;
        inicio=-1;
        fin=-1;
        this.tamanio=tamanio;
        arr=new int [tamanio];
    }

    /**
     *
     * @param num
     * @throws ColaLlenaException
     */
    public void add(int num) throws ColaLlenaException  {
        if(fin>=inicio && fin<inicio+tamanio) {
            if(inicio==-1)inicio=0;
            arr[++fin % tamanio] = num;
        }else
            throw new ColaLlenaException ("La cola esta llena, no se puede añadir ningún número más.");
    }

    /**
     *
     * @return
     * @throws ColaVaciaException
     */
    public int get() throws ColaVaciaException {

        if(inicio<=fin){

            if(inicio==tamanio){
                inicio=0;
                fin-=tamanio;

                return arr[inicio++];

            }else if(inicio==-1&&fin>=0)
                inicio=0;

            return arr[inicio++%tamanio];

        }
        else
            throw new ColaVaciaException("La cola esta vacia");
    }

    /**
     *
     * @return
     * @throws ColaVaciaException
     */
    public int getMax() throws ColaVaciaException {
        int max;
        if(fin==-1 || inicio>fin || fin>=(inicio+tamanio)|| fin==inicio&&fin>0) throw new ColaVaciaException("La cola esta vacia.");
        else{
            max= arr[inicio];
            if(fin<tamanio){
                for(int i=inicio;i<=fin;i++){
                    if(max<arr[i])max=arr[i];
                }
                return max;
            }else{
                for(int i=inicio;i<tamanio;i++){
                    if(max<arr[i])max=arr[i];
                }
                for(int i=0;i<=fin%tamanio;i++){
                    if(max<arr[i])max=arr[i];
                }
                return max;
            }
        }
    }

    /**
     *
     * @return
     * @throws ColaVaciaException
     */
    public int getMin() throws ColaVaciaException {
        int min;
        if(fin==-1 || inicio>fin || fin>=(inicio+tamanio)|| fin==inicio&&fin>0) throw new ColaVaciaException("La cola " +
                "esta vacia.");
        else{
            min= arr[inicio];
            if(fin<tamanio){
                for(int i=inicio;i<=fin;i++){
                    if(min>arr[i])min=arr[i];
                }
                return min;
            }else{
                for(int i=inicio;i<tamanio;i++){
                    if(min>arr[i])min=arr[i];
                }
                for(int i=0;i<=fin%tamanio;i++){
                    if(min>arr[i])min=arr[i];
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
    public int getNumPos(int num) throws ColaVaciaException {
        if(fin==-1 || inicio>fin || fin>=(inicio+tamanio)|| fin==inicio&&fin>0) throw new ColaVaciaException("La cola " +
                "esta vacia.");
        else{
            if(fin<tamanio){
                for(int i=inicio;i<=fin;i++){
                    if(num==arr[i])return i;
                }
                return -1;
            }else{
                for(int i=inicio;i<tamanio;i++){
                    if(num==arr[i])return i;
                }
                for(int i=0;i<=fin%tamanio;i++){
                    if(num==arr[i])return i;
                }
                return -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        Cola cola= new Cola(15);
        DecimalFormat formato= new DecimalFormat("#00");
        int num=0;
        System.out.println("Llenamos la cola con los siguientes 15 números y en el orden que aparecen:");
        for(int i=0;i<15;i++){
            num=(int)((Math.random())*99+1);
            System.out.print("\t"+formato.format(num));
            cola.add(num);
        }
        System.out.print("\n\tMax :" + cola.getMax());
        System.out.print("\tMin :" + cola.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " + num + " ? " + cola.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + cola.getNumPos(20));

        System.out.println("\nQuitamos los primeros 10 números de la cola (los que aparecen por pantalla):");
        for(int i=0;i<10;i++){
            System.out.print("\t" + formato.format(cola.get()));
        }
        System.out.print("\n\tMax :" + cola.getMax());
        System.out.print("\tMin :" + cola.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " + num + " ? " + cola.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + cola.getNumPos(20));
        System.out.println("\nLlenamos las 10 posiciones que hemos vaciado con otros 10 números: ");
        for(int i=0;i<10;i++){
            num=(int)((Math.random())*99+1);
            System.out.print("\t"+formato.format(num));
            cola.add(num);
        }
        System.out.print("\n\tMax :" + cola.getMax());
        System.out.print("\tMin :" + cola.getMin());
        System.out.println("\n\tEn que posición se encuentra el número " + num + " ? " + cola.getNumPos(num));
        System.out.println("\tEn que posición se encuentra el número 20 ? " + cola.getNumPos(20));
        System.out.println("\nVaciamos la cola entera: ");
        for(int i=0;i<15;i++){
            System.out.print("\t" + formato.format(cola.get()));
        }
        System.out.println("\n\nAhora presiona cualquier tecla para ver que pasa cuando se intenta extraer algo" +
                "de una cola vacia: ");
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
