package temp;

public class PriorityElement<P extends Comparable<P>,V> {

	private P priority;
	private V value;
	//Agregar atributo para verificar cuál element entró antes y así verificar lo mismo que se verifica en el maxHeapify.
	
	public PriorityElement(P priority, V value) {
		this.priority = priority;
		this.value = value;
	}

	public P getPriority() {
		return priority;
	}

	public void setPriority(P priority) {
		this.priority = priority;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
