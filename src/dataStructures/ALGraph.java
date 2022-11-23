package dataStructures;

import java.util.ArrayList;
import java.util.List;

/*
 * AL means Adjacency list.
 */
public class ALGraph<T> implements ALIGraph<T> {
	
	private List<ALVertex<T>> vertexes;

	/**
	 * Constructs an empty graph with no vertexes, and initializes the list with all the 
	 * vertexes of the graph.
	 */
	public ALGraph() {
		this.vertexes = new ArrayList<>();
	}

	@Override
	public boolean addVertex(ALVertex<T> v) {
		if(searchVertex(v) != null) {
			return false;
		} 
		
		vertexes.add(v);
		
		return true;
	}

	@Override
	public boolean removeVertex(ALVertex<T> v) {
		int index = searchVertexIndex(v);
		
		if(index == -1) {
			return false;
		}
		
		vertexes.remove(index);
		
		return true;
	}

	@Override
	public boolean addEdge(ALVertex<T> v1, ALVertex<T> v2, int weight) {
		if(searchVertex(v1) == null || searchVertex(v2) == null) { //Some vertex doesn't exist in the graph.
			return false;
		}
		
		if(searchEdge(v1, v2)) {
			return false;
		}
		
		v1.addAdjacent(v2,weight);
		v2.addAdjacent(v1,weight);
		
		return true;
	}

	@Override
	public boolean removeEdge(ALVertex<T> v1, ALVertex<T> v2) {
		if(searchVertex(v1) == null || searchVertex(v2) == null) { //Some vertex doesn't exist in the graph.
			return false;
		}
		
		if(!searchEdge(v1, v2)) {
			return false;
		}
		
		v1.removeAdjacent(v2);
		v2.removeAdjacent(v1);
		
		return true;
	}

	@Override
	public ALVertex<T> searchVertex(ALVertex<T> v) {
		return searchVertex(v.getValue());
	}

	@Override
	public ALVertex<T> searchVertex(T value) {
		for (int i = 0; i < vertexes.size(); i++) {
			if(vertexes.get(i).getValue() == value) {
				return vertexes.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Search for a vertex and returns it index. If the vertex is not found, returns -1.
	 * 
	 * @param v the searched vertex.
	 * @return the vertex index found, or -1.
	 */
	public int searchVertexIndex(ALVertex<T> v) {
		return searchVertexIndex(v.getValue());
	}
	
	/**
	 * Search for a vertex and returns it index. If the vertex is not found, returns -1.
	 * 
	 * @param v the searched vertex value.
	 * @return the vertex index found, or -1.
	 */
	public int searchVertexIndex(T value) {
		for (int i = 0; i < vertexes.size(); i++) {
			if(vertexes.get(i).getValue() == value) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public boolean searchEdge(ALVertex<T> v1, ALVertex<T> v2) {
		if(v1.searchAdjacent(v2) && v2.searchAdjacent(v1)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Returns the list with all the vertexes in the graph.
	 * 
	 * @return the list with all the vertexes in the graph
	 */
	public List<ALVertex<T>> getVertexes() {
		return vertexes;
	}
	
	/**
	 * Returns the vertex at the specified position in the list of the vertexes.
	 * 
	 * @param i the index of the vertex to return.
	 * @return the vertex placed in the specified index. Null if the specified index is equals 
	 * or greater than the size of the vertexes list.
	 */
	public ALVertex<T> get(int i) {
		if(i >= vertexes.size()) {
			return null;
		}
		
		return vertexes.get(i);
	}
}
