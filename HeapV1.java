package pahoSample;

 class MakeHeap {

	int child = 2;
	int binaryheap[];
	int heaplength;
	
	public MakeHeap(int size){
		
		heaplength = 0;
		binaryheap = new int[size +1];
		
	}
		
	public boolean isFull(){
		
		return heaplength==binaryheap.length;
	}
		
	public void insert(int element){
		
		int currentPosition;
		 if(isFull()){
			 System.out.println("Heap is Full");	 
		 }
		 
		 currentPosition = ++heaplength;
		 while(currentPosition>0 && binaryheap[currentPosition/2]>element){
			 binaryheap[currentPosition] = binaryheap[currentPosition/2];
			 currentPosition = currentPosition/2;
		 }
		 binaryheap[currentPosition] = element;
		 
	}
	
	public void printBinaryHeap(){
		
		for(int i = 1;i<binaryheap.length;i++){
			System.out.println(binaryheap[i]);
		}
	}
}



public class Heap {
	
	public static void main(String args[]){
		
		int array[] = {6, 7, 12, 10, 15, 17, 5};
		
		MakeHeap object1 = new MakeHeap(array.length);
		
		for(int i = 0; i<array.length;i++){
			object1.insert(array[i]);
		}
		
		object1.printBinaryHeap();
		
	}
}
