package dataStructures;

public class Vertex<T> {
	
	private T value;
	private int idNum;
	
	/**
	 * Constructs an empty vertex without value, and initializes the list with the adjacent 
	 * vertexes.
	 */
	public Vertex() {
		this.value = null;
		this.idNum = -1;
	}

	/**
	 * Creates a vertex with the specified value, and initializes the list with the adjacent 
	 * vertexes.
	 * 
	 * @param value the value of the vertex. It can be of any type.
	 */
	public Vertex(T value) {
		this.value = value;
		this.idNum = -1;
	}
	
	/**
	 * Returns the value contained in the actual vertex.
	 * 
	 * @return the value in the vertex.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value in the vertex to the new one specified.
	 * 
	 * @param value the new value of the vertex.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}	
	
	
}
