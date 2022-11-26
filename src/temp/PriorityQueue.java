package temp;

public class PriorityQueue<P extends Comparable<P>,V> {

	private LinkedList<PriorityElement<P,V>> list;

	public PriorityQueue() {
		this.list = new LinkedList<>();
	}
	
	public void insert(P priority, V value) {
		PriorityElement<P, V> couple = new PriorityElement<>(priority, value);
		list.add(couple);
		siftUp(list.size()-1);
	}
	
	public void insert(PriorityElement<P, V> newElement) {
		list.add(newElement);
		siftUp(list.size()-1);
	}
	
	public PriorityElement<P, V> maximum() {
		return list.getFront().getValue();
	}
	
	public PriorityElement<P, V> extractMax() {
		PriorityElement<P, V> element = list.getFront().getValue();
		
		swap(0, list.size()-1);
		list.remove(list.size()-1);
		maxHeapify();
		
		return element;
	}
	
	public void increaseKey(P priority, V value) throws Exception {
		int index = search(value);
		
		if(index < 0) {
			return;
		}
		
		PriorityElement<P, V> element = list.get(index).getValue();
		
//		if(element.getPriority().compareTo(priority) > 0) {
//			throw new Exception("You are trying to decrease the priority.");
//		}
		
		element.setPriority(priority);
		
		siftUp(index);
	}
	
	public int search(V value) {
		int index = -1;
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getValue().getValue().equals(value)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public void siftUp(int indexToSift) {
		while(indexToSift > 0) {
			PriorityElement<P, V> father = list.get((indexToSift-1)/2).getValue();
			PriorityElement<P, V> current = list.get(indexToSift).getValue();
			
			if(current.getPriority().compareTo(father.getPriority()) > 0) {//Comparación: current.getPriority() > father.getPriority()
				swap(indexToSift, (indexToSift-1)/2);
				indexToSift = (indexToSift-1)/2;
			} else {
				break;
			}
		}
	}
	
	public void maxHeapify() {
		 int i = 0;
		 int j = list.size()-1;
		 
		 while(i <= (j-1)/2) {
			 int largest = i;
			 
			 PriorityElement<P, V> son = list.get(i*2+1).getValue();//Left son
			 PriorityElement<P, V> element = list.get(i).getValue();
			 
			 if(i*2+1 <= j && son.getPriority().compareTo(element.getPriority()) > 0) {
				 largest = i*2+1;
			 }
			 
			 son = list.get(i*2+2).getValue();//Right son
			 element = list.get(largest).getValue();
			 
			 if(i*2+2 <= j && son.getPriority().compareTo(element.getPriority()) > 0) {
				 largest = i*2+2;
			 }
			 
			 if(largest != i) {
				 swap(i, largest);
				 
				 i = largest;
			 } else {
				 break;
			 }
		 }
	}
	
	public void swap(int i, int j) {
		PriorityElement<P, V> temp = list.get(i).getValue();
		
		list.get(i).setValue(list.get(j).getValue());
		list.get(j).setValue(temp);
	}
	
	public boolean contains(V element) {
		return contains(new PriorityElement<>(null, element));
	}
	
	public boolean contains(PriorityElement<P, V> element) {
		if(search(element.getValue()) < 0) {
			return false;
		}
		
		return true;
	}
	
	public String printPriorityQueue() {
		String info = "";
		
		for (int i = 0; i < list.size(); i++) {
			info += list.get(i).getValue().getValue();
		}
		
		return info;
	}
	
	public LinkedList<PriorityElement<P, V>> getList() {
		return list;
	}
	
	public PriorityElement<P, V> get(int i) {
		return list.get(i).getValue();
	}

	public int size() {
		return list.size();
	}
}
