import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<T> extends List<T> implements StringQueue<T>{

    public StringQueueImpl(){
        super();
    }

    public boolean isEmpty(){
        return super.isEmpty();
    }

    public void put(T item){
        insertAtBack(item);
    }

    public T get() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return removeFromFront();
    }

    public T peek() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return getFirstListNodeData();
    }

    public void printQueue(PrintStream stream){
        if (size() == 0){
            stream.println("Empty Queue List.");    
        }
        else{

            ListNode<T> current = getFirstListNode();

            while (current != null){
                stream.println(current.getData());
                current = current.getNext();
            }
        }
    }

    public int size(){
        return super.size();
    }
}