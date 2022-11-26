package temp;

public class LinkedList<T> {
	
	private Node<T> front;
	private Node<T> back;
	private int size;
	
	public LinkedList() {
		this.size = 0;
	}
	
	public void add(T toAdd) {
		Node<T> newNode = new Node<>(toAdd);
		
		if(front == null) {
			front = newNode;
			back = newNode;
			front.setNext(back);
			front.setPrevious(back);
			back.setNext(front);
			back.setPrevious(front);
		} else {
			back.setNext(newNode);
			newNode.setPrevious(back);
			newNode.setNext(front);
			back = newNode;
			
			front.setPrevious(back);
		}
		
		++size;
	}
	
	public Node<T> remove(int i) {
		Node<T> node = get(i);
		
		if(front == null) {
			//Lanzar excepción
		} else if(front == back) {
			front = null;
			back = null;
		} else if(i == 0) {
			back.setNext(front.getNext());
			front.getNext().setPrevious(back);
			
			front = front.getNext();
		} else {
			if(i == size-1) {
				back = node.getPrevious();
			}
			
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());
		}
		
		--size;
		
		return node;
	}
	
	public Node<T> get(int i) {
		Node<T> current = front;
		
		for (int j = 0; j < i; j++) {
			current = current.getNext();
		}
		
		return current;
	}
	
	public String printList() {
		return printList(front,"");
	}
	
	public String printList(Node<T> current, String listStr) {
		if(front == null) {
			return "";
		} else if(current == back) {
			return listStr + "[ " + current.getValue() + " ]	";
		}
		
		listStr += "[ " + current.getValue() + " ]	";
		
		return printList(current.getNext(), listStr);
	}

	public Node<T> getFront() {
		return front;
	}

	public void setFront(Node<T> front) {
		this.front = front;
	}

	public Node<T> getBack() {
		return back;
	}

	public void setBack(Node<T> back) {
		this.back = back;
	}

	public int size() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
