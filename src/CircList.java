import java.io.*;
import java.util.*;


public class CircList<T>{
    
    private ListNode<T> rearNode;
    static int numOfNodes = 0;

    public CircList(){
        rearNode = null;
    }

    public void enqueue(T ndata){
        ListNode<T> nNode = new ListNode<T>(ndata);   //Dimiourgia neou Komvou
        ListNode<T> temp;

        if (rearNode == null){
            rearNode = nNode;  
            nNode.setNext(nNode);
            numOfNodes++;
        }
        else{
            temp = rearNode.getNext();
            rearNode.setNext(nNode);
            nNode.setNext(temp);
            rearNode = nNode;
            numOfNodes++;
        }
    }

    public T dequeue() throws NoSuchElementException{
        T toBeReturned = rearNode.getNext().getData();
        if (numOfNodes > 1){
            rearNode.setNext(rearNode.getNext().getNext());
            numOfNodes--;
        }
        else if (numOfNodes == 1){
              numOfNodes = 0;
              rearNode = null;
        }
        else
            throw new NoSuchElementException();
        
        return toBeReturned;

    }

    public ListNode<T> getHead(){          //getHead() me xrisi MONO tou Pointer rearNode
        return rearNode.getNext();
    }

    public ListNode<T> getTail(){       
        return rearNode;
    }

    public int size(){
        return numOfNodes;
  }

}