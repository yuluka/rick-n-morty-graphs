package dataStructures;

import java.util.ArrayList;
import java.util.List;

import model.Square;

/**
 * A structure that contains many vertexes (nodes) connected to each other. 
 * 
 * This structure has a matrix that contains vertexes connections information. In each index 
 * of that matrix can be an integer number. If that number is less than 0, it means that the 
 * the vertexes, to which the intersection corresponds, are not connected.
 * However, if that number is greater than or equal to 0, it means that the the vertexes, 
 * to which the intersection corresponds, are connected, and the number is the weight of
 * the edge that connects the two vertexes.
 * 
 * @author Yuluka Gigante Muriel
 *
 * @param <T> the type of value the vertexes in the graph will contain. 
 */
public class Graph<T> implements IGraph<T> {

	private List<Vertex<T>> vertexes;
	
	private ArrayList<ArrayList<Integer>> adjacentMatrix;

	/**
	 * Constructs an empty graph with no vertexes, and initializes the list with all the 
	 * vertexes of the graph.
	 */
	public Graph() {
		this.vertexes = new ArrayList<>();
		this.adjacentMatrix = new ArrayList<ArrayList<Integer>>();
	}

	@Override
	public boolean addVertex(Vertex<T> v) {
		/*
		 * The vertex to add already exists in the graph.
		 * 
		 * There can not be two vertexes with the same value in the graph.
		 */
		if(searchVertex(v.getValue()) != null) { 
			return false;
		}
		
		vertexes.add(v);
		v.setIdNum(vertexes.size());
		
		updateAdjacentMatrixAdd();
		
		return true;
	}

	@Override
	public boolean removeVertex(Vertex<T> v) {
		/*if(searchVertex(v.getValue()) == null) {
			return false;
		}
		
		/*
		 * If the vertex to remove is the last one, we can just remove it from the array with all 
		 * vertexes and decrease the matrix size. However, if that's not the case, we have to 
		 * decrease the idNum of all the next vertexes and decrease the matrix size.
		 * 
		 * To decrease the matrix size, we can put all the values of the matrix one index back and
		 * remove the last space.
		 */
		if(v.getIdNum() == vertexes.size()) {
			vertexes.remove(v.getIdNum()-1);
			
			updateAdjacentMatrixSubstract(v.getIdNum());
			
			return true;
		}
		
		updateAdjacentMatrixSubstract(v.getIdNum());

		vertexes.remove(v.getIdNum()-1);
		
		for (int i = v.getIdNum()-1; i < vertexes.size(); i++) {
			vertexes.get(i).setIdNum(vertexes.get(i).getIdNum()-1);
		}
		
		return true;
	}

	@Override
	public boolean addEdge(Vertex<T> v1, Vertex<T> v2, int weight) {
		if(searchEdge(v1, v2)) {
			return false;
		}
		
		adjacentMatrix.get(v1.getIdNum()-1).set(
				v2.getIdNum()-1, weight);
		
		adjacentMatrix.get(v2.getIdNum()-1).set(
				v1.getIdNum()-1, weight);
		
		return true;
	}

	@Override
	public boolean removeEdge(Vertex<T> v1, Vertex<T> v2) {
		if(!searchEdge(v1, v2)) {
			return false;
		}
		
		adjacentMatrix.get(v1.getIdNum()-1).set(v2.getIdNum()-1, -1);
		adjacentMatrix.get(v2.getIdNum()-1).set(v1.getIdNum()-1, -1);
				
		return true;
	}

	@Override
	public Vertex<T> searchVertex(Vertex<T> v) {
		return searchVertex(v.getValue());
	}

	@Override
	public Vertex<T> searchVertex(T value) {
		for (int i = 0; i < adjacentMatrix.size(); i++) {
			if(vertexes.get(i).getValue().equals(value)) {
				return vertexes.get(i);
			}
		}
		
		return null;
	}

	@Override
	public boolean searchEdge(Vertex<T> v1, Vertex<T> v2) {
		if(searchVertex(v1.getValue()) == null || searchVertex(v2.getValue()) == null) {
			/*
			 * Some vertex doesn't exist in the graph.
			 */
			return false;
		}
		
		for (int i = 0; i < adjacentMatrix.size(); i++) {
			int aux = adjacentMatrix.get(v1.getIdNum()-1).get(v2.getIdNum()-1);
			
			if(aux >= 0) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Updates the adjacent matrix by adding one space in the main array and in all the arrays
	 * inside each index of the main array.
	 */
	private void updateAdjacentMatrixAdd() {
		adjacentMatrix.add(new ArrayList<Integer>());
		
		for (int j = 0; j < vertexes.size(); j++) {
			adjacentMatrix.get(vertexes.size()-1).add(-1);
		}
		
		for (int j = 0; j < vertexes.size()-1; j++) {
			adjacentMatrix.get(j).add(-1);
		}
	}
	
	/**
	 * Updates the adjacent matrix by substracting one space in the main array and in all the 
	 * arrays inside each index of the main array.
	 * 
	 * @param vIdNum the idNum of the vertex from which spaces are to be changed.
	 */
	private void updateAdjacentMatrixSubstract(int vIdNum) {
		if(vIdNum == adjacentMatrix.size()) {
			adjacentMatrix.remove(vIdNum-1);
			
			for (int i = 0; i < adjacentMatrix.size(); i++) {
				adjacentMatrix.get(i).remove(vIdNum-1);
			}
			
			return;
		}
		
		for (int i = 0; i < adjacentMatrix.size(); i++) {
			if(i == vIdNum-1) {
				++i;
			} 
			
			for (int j = vIdNum-1; j < adjacentMatrix.size(); j++) {
				if(j == adjacentMatrix.size()-1) {
					adjacentMatrix.get(i).remove(j);
					break;
				}
				
				adjacentMatrix.get(i).set(j, adjacentMatrix.get(i).get(j+1));
			}	
			
			if(i == adjacentMatrix.size()-1) {
				
				for (int j = vIdNum-1; j < adjacentMatrix.size()-1; j++) {
					adjacentMatrix.set(j, adjacentMatrix.get(j+1));
				}
				
				adjacentMatrix.remove(i);
				
				break;
			} 
		}
	}
	
	/**
	 * Returns the vertex at the specified position in the list of the vertexes.
	 * 
	 * @param i the index of the vertex to return.
	 * @return the vertex placed in the specified index. Null if the specified index is equals 
	 * or greater than the size of the vertexes list.
	 */
	public Vertex<T> get(int i) {
		if(i >= vertexes.size()) {
			return null;
		}
		
		return vertexes.get(i);
	}
	
	/**
	 * Returns the list placed in the specified position in the adjacency matrix.
	 * 
	 * @param i the index of the list to return.
	 * @return list placed in the specified position in the adjacency matrix.
	 */
	public ArrayList<Integer> getMatrix(int i) {
		if(i > adjacentMatrix.size()) {
			return null;
		}
		
		return adjacentMatrix.get(i);		
	}

	public List<Vertex<T>> getVertexes() {
		return vertexes;
	}

	public void setVertexes(List<Vertex<T>> vertexes) {
		this.vertexes = vertexes;
	}

	public ArrayList<ArrayList<Integer>> getAdjacentMatrix() {
		return adjacentMatrix;
	}

	@Override
	public ArrayList<Vertex<T>> dijkstra(Vertex<T> initialV) {
		ArrayList<Vertex<T>> prevs = new ArrayList<>();
		ArrayList<Integer> dists = new ArrayList<>();
		
		ArrayList<Pair<Vertex<T>>> pq = new ArrayList<>();
		
		ArrayList<Vertex<T>> adjacents = getAdjacents(initialV);
		ArrayList<Integer> adjacentWeights = getAdjacentsWeights(initialV);
		
		dists.add(0);
		
		for (int i = 0; i < vertexes.size(); i++) {
			if(!vertexes.get(i).equals(initialV)) {
				dists.add(i, Integer.MAX_VALUE);
			}
			
			prevs.add(null);
			
			pq.add(new Pair<Vertex<T>>(vertexes.get(i), dists.get(i)));
			sortList(pq);
		}
		
		while(pq.size() > 0) {
			Vertex<T> u = pq.get(0).getValue();
			adjacents = getAdjacents(u);
			adjacentWeights = getAdjacentsWeights(u);
			
			int distU = pq.remove(0).getWeight();
			sortList(pq);
			
			for (int i = 0; i < adjacents.size(); i++) {
				int uIndex = searchIndex(u);
				int alt = dists.get(uIndex) + adjacentWeights.get(i);
				int adjacentIndex = searchIndex(adjacents.get(i));
				
				if(alt < dists.get(adjacentIndex)) {
					dists.set(adjacentIndex, alt);
					prevs.set(adjacentIndex, u);
					
					
//					Q.decrease_priority(v,alt)
					int indexInPQ = searchIndex(pq, adjacents.get(i));
					
					pq.get(indexInPQ).setWeight(alt);
					sortList(pq);
				}
			}
		}
		
		return prevs;
	}
	
	public ArrayList<Vertex<T>> getAdjacents(Vertex<T> u) {
		ArrayList<Vertex<T>> adjacents = new ArrayList<>();
		int uIndex = searchIndex(u);
		
		for (int i = 0; i < vertexes.size(); i++) {
			if(adjacentMatrix.get(uIndex).get(i) > -1) {
				adjacents.add(vertexes.get(i));
			}
		}
		
		return adjacents;
	}
	
	public ArrayList<Integer> getAdjacentsWeights(Vertex<T> u) {
		ArrayList<Integer> adjacents = new ArrayList<>();
		int uIndex = searchIndex(u);
		
		for (int i = 0; i < vertexes.size(); i++) {
			if(adjacentMatrix.get(uIndex).get(i) > -1) {
				adjacents.add(adjacentMatrix.get(uIndex).get(i));
			}
		}
		
		return adjacents;
	}
	
	public ArrayList<Pair<Vertex<T>>> sortList(ArrayList<Pair<Vertex<T>>> list) {
		for (int i = 1; i < list.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (list.get(i).getWeight() < list.get(j).getWeight()) {
					Pair<Vertex<T>> aux = list.get(i);
					list.remove(i);
					list.add(j,aux);
					break;
				}
			}
		}
		
		return list;
	}
	
	public int searchIndex(ArrayList<Pair<Vertex<T>>> list, Vertex<T> goal) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getValue().getIdNum() == goal.getIdNum()) {
				return i;
			}
		}
		
		return -1;
	}
	
	public int searchIndex(Vertex<T> goal) {
		for (int i = 0; i < vertexes.size(); i++) {
			if(vertexes.get(i).getIdNum() == goal.getIdNum()) {
				return i;
			}
		}
		
		return -1;
	}
	
}
