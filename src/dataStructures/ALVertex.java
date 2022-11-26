package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class ALVertex<T> {
	
	private List<Pair<ALVertex<T>>> adjacents;
	
	private T value;
	
	/**
	 * Constructs an empty vertex without value, and initializes the list with the adjacent 
	 * vertexes.
	 */
	public ALVertex() {
		this.adjacents = new ArrayList<>();
		
		this.value = null;
	}

	/**
	 * Creates a vertex with the specified value, and initializes the list with the adjacent 
	 * vertexes.
	 * 
	 * @param value the value of the vertex. It can be of any type.
	 */
	public ALVertex(T value) {
		this.value = value;
		
		this.adjacents = new ArrayList<>();
	}

	/**
	 * Returns a list that contains all the adjacent vertexes.
	 * 
	 * @return the list with the adjacent vertexes.
	 */
	public List<Pair<ALVertex<T>>> getAdjacents() {
		return adjacents;
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
	
	/**
	 * Adds an adjacent vertex to the actual. Basically, creates an edge between the actual
	 * vertex and the vertex specified and returns true. However, if the edge already existed
	 * previously, the method does nothing and returns false.
	 * 
	 * @param v the vertex to join with.
	 * @param weight the weight of the edge.
	 * @return true if the vertexes was not joined previously. False otherwise.
	 */
	public boolean addAdjacent(ALVertex<T> v, int weight) {
		int index = searchAdjacentIndex(v);
		
		if(index != -1) {
			return false;
		}
		
		Pair<ALVertex<T>> newAdjacent = new Pair<>(v, weight);
		
		adjacents.add(newAdjacent);
		
		return true;
	}
	
	/**
	 * Removes an adjacent vertex to the actual. Basically, removes an edge between the actual
	 * vertex and the one specified, and returns true. However, if the edge doesn't exist, the
	 * method returns false.
	 * 
	 * @param v the vertex to be disconnected.
	 * @return true if the edge already existed. False otherwise.
	 */
	public boolean removeAdjacent(ALVertex<T> v) {
		int index = searchAdjacentIndex(v);
		
		if(index == -1) {
			return false;
		}
		
		adjacents.remove(index);
		
		return true;
	}
	
	/**
	 * Search the specified vertex by using its value. If there exists an adjacent vertex 
	 * with, exactly, the same value, returns true. Otherwise returns false.
	 * 
	 * @param v the searched vertex.
	 * @return true if the vertex was found. False otherwise.
	 */
	public boolean searchAdjacent(ALVertex<T> v) {
		for (int i = 0; i < adjacents.size(); i++) {
			if(adjacents.get(i).getValue().getValue().equals(v.getValue())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Search the specified vertex by using its value. If there exists an adjacent vertex 
	 * with, exactly, the same value, returns the index where is placed. Otherwise returns -1.
	 * 
	 * @param v the searched vertex.
	 * @return the index where is placed the vertex. -1 otherwise.
	 */
	public int searchAdjacentIndex(ALVertex<T> v) {
		for (int i = 0; i < adjacents.size(); i++) {
			if(adjacents.get(i).getValue().getValue().equals(v.getValue()))	{
				return i;
			}
		}
		
		return -1;
	}
}
