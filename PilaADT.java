package calculadorafinal;

public interface PilaADT<T> {
    public T pop();
    public T peek();
    public boolean isEmpty();
    public boolean isFull();
    public boolean push(T dato);
}
