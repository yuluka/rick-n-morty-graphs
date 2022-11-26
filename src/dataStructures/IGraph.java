package dataStructures;

import java.util.ArrayList;

public interface IGraph<T> {

	/*
	 * Reminder: Two different vertexes can not have the same value.
	 */
	
	/**
	 * Adds a vertex to the graph only if the vertex didn't exist previously.
	 * 
	 * @param v the vertex to add.
	 * @return true if the vertex to add didn't exist previously. False otherwise.
	 */
	boolean addVertex(Vertex<T> v);
	
	/**
	 * Removes the specified vertex of the graph if the vertex exists. Otherwise, it does
	 * nothing.
	 * 
	 * @param v the vertex to remove.
	 * @return true if the vertex to remove exists. False otherwise.
	 */
	boolean removeVertex(Vertex<T> v);
	
	/**
	 * Adds an edge between the two vertexes specified. If the edge already exists does nothing.
	 * 
	 * @param v1 the end of the edge.
	 * @param v2 the other end of the edge.
	 * @param weight the weight of the edge.
	 * @return true if the edge doesn't exist. False otherwise.
	 */
	boolean addEdge(Vertex<T> v1, Vertex<T> v2, int weight);
	
	/**
	 * Removes an edge, that already exists, between the two specified vertexes. If the edge 
	 * doesn't exist, does nothing.
	 * 
	 * @param v1 the end of the edge.
	 * @param v2 the other end of the edge.
	 * @return true if the edge exists. False otherwise.
	 */
	boolean removeEdge(Vertex<T> v1, Vertex<T> v2);
	
	/**
	 * Search for a vertex and returns it. If the vertex is not found, returns null.
	 * 
	 * @param v the searched vertex.
	 * @return the vertex found, or null.
	 */
	Vertex<T> searchVertex(Vertex<T> v);
	
	/**
	 * Search for a vertex, by using its value, and returns it. If the vertex is not found, 
	 * returns null.
	 * 
	 * @param value the value of the searched vertex.
	 * @return the vertex found, or null.
	 */
	Vertex<T> searchVertex(T value);
	
	/**
	 * Search for an edge between the two specified vertexes.
	 * 
	 * @param v1 the end of the edge.
	 * @param v2 the other end of the edge.
	 * @return true if the edge exists. False otherwise.
	 */
	boolean searchEdge(Vertex<T> v1, Vertex<T> v2);
	
	/**
	 * Searches for the minimum weight path between the specified initial vertex and all 
	 * the other vertexes in the graph, and returns the list with the previous vertexes 
	 * to be visited to take the minimum weight path to each vertex in the graph.
	 * 
	 * @param initialV the initial vertex.
	 * @return the list with the previous vertexes to be visited to take the minimum 
	 * weight path to each vertex in the graph.
	 */
	ArrayList<Vertex<T>> dijkstra(Vertex<T> initialV);
}
