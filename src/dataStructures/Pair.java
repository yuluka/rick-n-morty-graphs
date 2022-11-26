package dataStructures;

/**
 * An object that contains just two parameters: its value and its weight. Its objective is to be 
 * used to represent edges between vertexes in a graph.
 * 
 * The value can be of any type, and represents the things inside the vertex. The weight is just 
 * of the edge between this and the other vertexes.
 * 
 * @author Yuluka G. M.
 *
 * @param <V> the type of the vertex value.
 */
public class Pair<V> {
	
	private V value;
	private int weight;

	/**
	 * Constructs a pair with a value inside the vertex and the weight of the edge.
	 * 
	 * @param value
	 * @param weight
	 */
	public Pair(V value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
