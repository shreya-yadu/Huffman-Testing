package pahoSample;

 class MakeHeap {

	int frequencyheap[];
	int frequencyheaplength;
	char huffmanheap[];
	int huffmanheaplength;
	
	public MakeHeap(int size){
		
		frequencyheaplength = 0;
		frequencyheap = new int[size +1];
		huffmanheap = new char[size+1];
		huffmanheaplength = 0;
	}
		
	public boolean isFull(){
		
		return frequencyheaplength==frequencyheap.length;
	}
		
	public void insert(char element, int frequency){
		
		int currentPosition;
		 if(isFull()){
			 System.out.println("Heap is Full");	 
		 }
		 ++huffmanheaplength;	 
		 currentPosition = ++frequencyheaplength;
		 while(currentPosition>0 && frequencyheap[currentPosition/2]>frequency){
			 frequencyheap[currentPosition] = frequencyheap[currentPosition/2];
			 huffmanheap[currentPosition] = huffmanheap[currentPosition/2];
			 currentPosition = currentPosition/2;
		 }
		 huffmanheap[currentPosition] = element;
		 frequencyheap[currentPosition] = frequency;
		 
	}
	
	public void printfrequencyheap(){
		
		for(int i = 0;i<=frequencyheaplength;i++){
			System.out.print(huffmanheap[i] + "  " + frequencyheap[i]);
			System.out.println();
			
		}
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
		
		object1.printfrequencyheap();
		
	}
}
