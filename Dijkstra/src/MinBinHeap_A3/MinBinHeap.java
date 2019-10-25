package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	  private int size;
	  private static final int arraySize = 10000; //Everything in the array will initially 
	                                              //be null. This is ok! Just build out 
	                                              //from array[1]

	  public MinBinHeap() {
	    this.array = new EntryPair[arraySize];
	    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
	                                             //of child/parent computations...
	                                             //the book/animation page both do this.
	  }
	    
	  //Please do not remove or modify this method! Used to test your entire Heap.
	  @Override
	  public EntryPair[] getHeap() { 
	    return this.array;
	  }

	@Override
	public void insert(EntryPair entry) {
		
		if(size == 0){
			array[1] = entry;
			size++;
		}
		else {
			size++;
			array[size] = entry;
			
			BubbleUp(size);
			return;
		}
	
		
		
	}
	public void BubbleUp(int a) {
		
		while (compare(array[Math.floorDiv(a, 2)], array[a]) && a > 1) {
			EntryPair temp = array[Math.floorDiv(a, 2)];
			array[Math.floorDiv(a, 2)] = array[a];
			array[a] = temp;
			a = Math.floorDiv(a, 2);
		}
		
	}
	
	public void BubbleDown(int a) {
		while (a * 2 != size + 1) {
			// find which branch has higher priority
			int nextChild = higherPriority(a * 2);
			// swap if priorities are out of order
			if (compare(array[a], array[nextChild])) {
				EntryPair temp = array[a];
				array[a] = array[nextChild];
				array[nextChild]= temp;
				a = nextChild;
			} else {
				break;
			}
		}
		return;
		
	}

	@Override
	public void delMin() {
		
		if( size == 0) {
			return;
		}
		else if(size == 1) {
			array[1] = null;
			size--;
			return;
		}
		else {
			
		array[1] = array[size];
		size--;
		array[size+1] = null;
		
		if (size != 1) {
			BubbleDown(1);
		}
		return;
		}
		
		
	}

	@Override
	public EntryPair getMin() {
		if(size == 0) {
			return null;
		}
		else{
			return array[1];
		}
	}

	@Override
	public int size() {
		
		return size;
	}
	
	public int higherPriority (int a) {
		if(a == size){
			return a;
			
		}
		else {
			if (array[a] != null && array[a + 1] != null) {
				if(array[a].priority < array[a + 1].priority) {
					return a;
				}
				else {
					return a+1;
				}
			}
			else {
				return a;
			}
		}
	
	}
	public boolean compare (EntryPair one, EntryPair two) {
		if (one != null && two != null) {
			if(one.priority > two.priority) {
				return true;
			}
			else {
				return false;
			}
		} 
		else{
			return false;
		}
		
	}

	@Override
	public void build(EntryPair[] entries) {
		
		 size = entries.length;
		
		for (int i = 0; i < size; i++) {
			array[i + 1] = entries[i];
		}
		
		for (int i = size; i >= 1; i--) {
			BubbleDown(i);
		}
		
		
	}
}
