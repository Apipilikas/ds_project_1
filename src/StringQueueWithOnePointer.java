import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer<T> extends CircList<T> implements StringQueue<T>{

    public StringQueueWithOnePointer(){
        super();
    }
    
    public boolean isEmpty(){
        if (super.size() == 0)
            return true;
        else
            return false;
    }

    public void put(T item){
        super.enqueue(item);
    }

    public T get() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return super.dequeue();    
    }

    public T peek() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return super.getHead().getData();
    }

    public void printQueue(PrintStream stream){
        if (size() == 0)
            stream.println("Empty Queue List.");
        else{
            ListNode<T> current = getHead();
            int counter = 0;
            while (counter < size()){
                stream.println(current.getData());
                counter ++;
                current = current.getNext();
            }
        }

    }

    public int size(){
        return super.size();
    }

}