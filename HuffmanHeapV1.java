package pahoSample;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

class MakeHeap {
	 
	 HashMap<Integer, Character> huffmanHeap = new HashMap<Integer, Character>();
	int frequencyHeapArray[];
	int frequencyHeapArraylength;
	char huffmanHeapArray[];
	int huffmanHeapArraylength;
	
	public MakeHeap(int size){
		
		frequencyHeapArraylength = 0;
		frequencyHeapArray = new int[size +1];
		huffmanHeapArray = new char[size+1];
		huffmanHeapArraylength = 0;
	}
		
	public void insert(char element, int frequency){
		
		int currentPosition;
		 if(frequencyHeapArraylength==frequencyHeapArray.length){
			 System.out.println("Heap is Full");	 
		 }
		 ++huffmanHeapArraylength;	 
		 currentPosition = ++frequencyHeapArraylength;
		 while(currentPosition>0 && frequencyHeapArray[currentPosition/2]>frequency){
			 frequencyHeapArray[currentPosition] = frequencyHeapArray[currentPosition/2];
			 huffmanHeapArray[currentPosition] = huffmanHeapArray[currentPosition/2];
			 currentPosition = currentPosition/2;
		 }
		 huffmanHeapArray[currentPosition] = element;
		 frequencyHeapArray[currentPosition] = frequency;
		 
	} 
	
	public void delete (){
		// this method will delete the element from the array which in turn will tell which element(minimum) to delete from heap
		
		if(frequencyHeapArraylength == 0){
			System.out.println("Cant Be deleted. No Element is left in the heap");
		}
		
		int deletedItem = huffmanHeapArray[0];
		huffmanHeapArray[0] = huffmanHeapArray[huffmanHeapArraylength];
		--huffmanHeapArraylength;
		
	}
	
	public void printfrequencyHeapArray(){
		
		for(int i = 1;i<=frequencyHeapArraylength;i++){
			System.out.print(huffmanHeapArray[i] + "  " + frequencyHeapArray[i]);
			System.out.println();
		}
	}
	
	public void convertToHeap(){
		
		for(int i=1;i<=frequencyHeapArraylength; i++){
			
			huffmanHeap.put(frequencyHeapArray[i], huffmanHeapArray[i]);
			
		}	
	}
	
	public void printHeap(){
		
		  Set set = huffmanHeap.entrySet();
	      Iterator iterator = set.iterator();
	      
	      while(iterator.hasNext()) {
	    	  
	         Map.Entry entry = (Map.Entry)iterator.next();
	         System.out.print("Character is: "+ entry.getValue() + " & Frequency is: ");
	         System.out.println(entry.getKey());
	      
	      }
	}
	
	
	
	
	public void extractMinimum(){
		
		//This method will extract minimum from the heap and delete from the heap
		
	}
	
	public void buildHuffmanHeap(){
		
			// this will extract minimum from the heap and add and finally put it back in the heap
		
	}
}



public class Heap {
	
	public static void main(String args[]){
		
		int frequency[] = {0,55,99,25,999,666,9875,525};
		char data[] = {'a', 'b', 'c','d', 'e', 'f','h','t'};
		
		MakeHeap object1 = new MakeHeap(frequency.length);
		
		for(int i = 0; i<frequency.length;i++){
			object1.insert(data[i], frequency[i] );
		}
		
		object1.printfrequencyHeapArray();
		
	}
}
