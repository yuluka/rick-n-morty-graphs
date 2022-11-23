package dataStructures;

public interface ALIGraph<T> {

	/*
	 * Reminder: Two different vertexes can not have the same value.
	 */
	
	/**
	 * Adds a vertex to the graph only if the vertex didn't exist previously.
	 * 
	 * @param v the vertex to add.
	 * @return true if the vertex to add didn't exist previously. False otherwise.
	 */
	boolean addVertex(ALVertex<T> v);
	
	/**
	 * Removes the specified vertex of the graph if the vertex exists. Otherwise, it does
	 * nothing.
	 * 
	 * @param v the vertex to remove.
	 * @return true if the vertex to remove exists. False otherwise.
	 */
	boolean removeVertex(ALVertex<T> v);
	
	/**
	 * Adds an edge between the two vertexes specified. If the edge already exists does nothing.
	 * 
	 * @param v1 the end of the edge.
	 * @param v2 the other end of the edge.
	 * @param weight the weight of the edge.
	 * @return true if the edge doesn't exist. False otherwise.
	 */
	boolean addEdge(ALVertex<T> v1, ALVertex<T> v2, int weight);
	
	/**
	 * Removes an edge, that already exists, between the two specified vertexes. If the edge 
	 * doesn't exist, does nothing.
	 * 
	 * @param v1 the end of the edge.
	 * @param v2 the other end of the edge.
	 * @return true if the edge exists. False otherwise.
	 */
	boolean removeEdge(ALVertex<T> v1, ALVertex<T> v2);
	
	/**
	 * Search for a vertex and returns it. If the vertex is not found, returns null.
	 * 
	 * @param v the searched vertex.
	 * @return the vertex found, or null.
	 */
	ALVertex<T> searchVertex(ALVertex<T> v);
	
	/**
	 * Search for a vertex, by using its value, and returns it. If the vertex is not found, 
	 * returns null.
	 * 
	 * @param value the value of the searched vertex.
	 * @return the vertex found, or null.
	 */
	ALVertex<T> searchVertex(T value);
	
	/**
	 * Search for a edge between the two specified vertexes.
	 * 
	 * @param v1 the end of the edge.
	 * @param v2 the other end of the edge.
	 * @return true if the edge exists. False otherwise.
	 */
	boolean searchEdge(ALVertex<T> v1, ALVertex<T> v2);
}
