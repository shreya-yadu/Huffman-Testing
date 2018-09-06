/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreya
 */



import java.util.HashMap;

public class binaryheap 
    {

    private Node[] array_heap;
    private int maxCapacity;
    private int elements_tot;

    public binaryheap (int max) 
    {
        array_heap = new Node[max];
        elements_tot = 0;
    }
public class Node 
    {
        // for the heap to be sorted the key is frequencyuency
        int frequency;
        // Real data needed to be encoded
        int data;

        Node lft;
        Node rht;

        public Node(int frequency, int data) {
            this.frequency = frequency;
            this.data = data;
        }

        @Override
        public String toString() {
            String child = lft == null ? "" : "[" + lft + "$" + rht + "]";
            return "(" + frequency + "," + data + child + ")";
        }
    } 

    public boolean isEmpty() 
    {
        return elements_tot == 0;
    }

    public Node insert(int frequency, int data) {
        if ( isFull() ) 
        {
            System.err.println("Error: heap is full!");
            return null;
        }
        Node N = new Node(frequency, data);
        
        array_heap[elements_tot] = N;
        up_heap(elements_tot);
        elements_tot++;
        
        return N;
    }
    
    public boolean isFull() 
    {
        return elements_tot == array_heap.length ;
    }

    public void up_heap (int index) 
    {
        Node lastElement = array_heap[index];
        int parentIndex = (index - 1) / 2 ;
        while ( (index > 0) && (lastElement.frequency < array_heap[parentIndex].frequency) ) 
        {
            array_heap[index] = array_heap[parentIndex];
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2 ;
        }
        array_heap[index] = lastElement;
    }

    public void down_up (int index ) 
    {
        int child_min = findMin(index * 2 + 1, index * 2 + 2);
        Node root_temp = array_heap[index];       // saving the root 
        while ( (child_min < elements_tot) &&
                (array_heap[child_min].frequency < root_temp.frequency)) 
        {
            array_heap[index] = array_heap[child_min];
            index = child_min;
            child_min = findMin(child_min * 2 + 1, child_min * 2 + 2);
        }
        array_heap[index] = root_temp;
    }
    
    public int findMin(int from, int to) 
    {
        int child_min = from;
        for (int x = from + 1; (x <= to && x < elements_tot); x++) 
        {
            if ( (array_heap[child_min].frequency) > (array_heap[x].frequency) )
                child_min = x;
        }
        return child_min;
    }

    public Node ExtractRoot() 
    {
        return array_heap[0];
    }
    
    void Treebuild() 
    {
        while (elements_tot > 1) 
        {
            Node one = extractMin();
            Node two = extractMin();
            Node new_value_insert = insert(one.frequency + two.frequency, -1);
            new_value_insert.lft = one;
            new_value_insert.rht = two;
        }
    }

    public Node extractMin() 
    {
        Node min = array_heap[0];
        elements_tot = elements_tot - 1 ;
        array_heap[0] = array_heap[elements_tot];
        down_up(0);
        return min;
    }
    
    void populateCodes(Node N, String path, HashMap<Integer, String> codes) {
        if (N.data != -1) {
            codes.put(N.data, path);
        } else {
            populateCodes(N.lft, path + "0", codes);
            populateCodes(N.rht, path + "1", codes);
        }
    }
}