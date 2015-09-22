package calculadorafinal;

import java.util.Collection;

public class Pila<T> implements PilaADT<T> {
    private T[] colec;
    private int tope;
    private final int max=100;
    
    public Pila(){
        colec=(T[])(new Object[max]);
        tope=-1;
    }
    public boolean isEmpty(){
        return tope==-1;
    }
    public T pop(){
        T resp=null;
        if(!isEmpty()){
            resp=colec[tope];
            tope--;
        }
        return resp;
    }
    public T peek(){
        T resp=null;
        if(!isEmpty())
            resp=colec[tope];
        return resp;
    }
    public boolean isFull(){
        return tope+1==max;
    }
    public boolean push(T dato){
        boolean resp=false;
        if(!isFull()){
            tope++;
            colec[tope]=dato;
            resp=true;
        }
        return resp;
    }
    /*public static Number suma2(Pila<? extends Number> a){
        return a.pop().intValue()+suma2(a).intValue();
    }
    public static int suma(int[] a, int n){
        if(n>1)
            return a[n-1]+suma(a, n-1);
        else
            return a[n-1]; 
    }*/
}
