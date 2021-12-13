import java.io.*;
import java.util.NoSuchElementException;

public class StringStackImpl<T> extends List<T> implements StringStack<T>{

    public StringStackImpl(){
        super();
    }

    public boolean isEmpty(){
        return super.isEmpty();
    }

    public void push(T item){
        insertAtFront(item);
    }

    public T pop() throws NoSuchElementException{
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

    public void printStack(PrintStream stream){
        if (size() == 0){
            stream.println("Empty Stack List.");
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