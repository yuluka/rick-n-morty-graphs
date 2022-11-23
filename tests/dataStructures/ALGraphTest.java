package dataStructures;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ALGraphTest {

	private ALGraph<Integer> graph;
	
	public void setupStage1() {
		graph = new ALGraph<>();
	}
	
	public void setupStage2() {
		graph = new ALGraph<>();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.addEdge(v1, v2,2);
	}
	
	@Test
	void creationTest1() {
		setupStage1();
		
		assertNotNull(graph);
	}
	
	//Adds the vertex correctly.
	@Test
	void addVertexTest1() {
		setupStage1();
		
		graph.addVertex(new ALVertex<>(1));
		
		assertNotNull(graph.searchVertex(1));
	}

	//Returns false if the vertex to add already existed.
	@Test
	void addVertexTest2() {
		setupStage1();
		
		graph.addVertex(new ALVertex<>(1));
		
		assertFalse(graph.addVertex(new ALVertex<>(1)));
	}
	
	//Returns true if the vertex to add doesn't exist.
	@Test
	void addVertexTest3() {
		setupStage1();
		
		assertTrue(graph.addVertex(new ALVertex<>(1)));
	}
	
	//Returns false if the vertex to remove doesn't exist.
	@Test
	void removeVertexTest1() {
		setupStage1();
		
		assertFalse(graph.removeVertex(new ALVertex<>(1)));
	}
	
	//Returns true if the vertex to remove already existed.
	@Test
	void removeVertexTest2() {
		setupStage1();
		
		graph.addVertex(new ALVertex<>(1));
		
		assertTrue(graph.removeVertex(new ALVertex<>(1)));
	}
	
	//Removes the specified vertex correctly.
	@Test
	void removeVertexTest3() {
		setupStage1();
		
		graph.addVertex(new ALVertex<>(1));
		
		graph.removeVertex(new ALVertex<>(1));
		
		assertNull(graph.searchVertex(1));
	}
	
	//The two vertexes has each other as adjacent.
	@Test
	void addEdgeTest1() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		ALVertex<Integer> v3 = new ALVertex<>(3);
		ALVertex<Integer> v4 = new ALVertex<>(4);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2,2);
		graph.addEdge(v3, v2,8);
		graph.addEdge(v4, v2,1);
		
		assertTrue(v1.searchAdjacent(v2));
		assertTrue(v2.searchAdjacent(v1));
		
		int index = v1.searchAdjacentIndex(v2);
		assertEquals(2, v1.getAdjacents().get(index).getWeight());
		
		index = v2.searchAdjacentIndex(v1);
		assertEquals(2, v2.getAdjacents().get(index).getWeight());
		
		assertTrue(v3.searchAdjacent(v2));
		assertTrue(v2.searchAdjacent(v3));
		
		index = v3.searchAdjacentIndex(v2);
		assertEquals(8, v3.getAdjacents().get(index).getWeight());
		
		index = v2.searchAdjacentIndex(v3);
		assertEquals(8, v2.getAdjacents().get(index).getWeight());
		
		assertTrue(v4.searchAdjacent(v2));
		assertTrue(v2.searchAdjacent(v4));
		
		index = v4.searchAdjacentIndex(v2);
		assertEquals(1, v4.getAdjacents().get(index).getWeight());
		
		index = v2.searchAdjacentIndex(v4);
		assertEquals(1, v2.getAdjacents().get(index).getWeight());
	}
	
	//Returns true if the edge didn't exist before.
	@Test
	void addEdgeTest2() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		assertTrue(graph.addEdge(v1, v2,2));
	}
	
	//Returns false if the edge existed before.
	@Test
	void addEdgeTest3() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.addEdge(v1, v2,2);
		
		assertFalse(graph.addEdge(v1, v2,2));
	}
	
	//Removes the edge correctly.
	@Test
	void removeEdgeTest1() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		ALVertex<Integer> v3 = new ALVertex<>(3);
		ALVertex<Integer> v4 = new ALVertex<>(4);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2,2);
		graph.addEdge(v3, v2,8);
		graph.addEdge(v4, v2,1);
		
		graph.removeEdge(v3, v2);
		
		assertFalse(graph.searchEdge(v3, v2));
		
		assertTrue(v1.searchAdjacent(v2));
		assertTrue(v2.searchAdjacent(v1));
		assertTrue(v4.searchAdjacent(v2));
		assertTrue(v2.searchAdjacent(v4));
	}
	
	//Removes the edge correctly regardless of the order of the vertexes.
	@Test
	void removeEdgeTest2() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.addEdge(v1, v2,2);
		
		graph.removeEdge(v2, v1);
		
		assertFalse(graph.searchEdge(v1, v2));
		assertFalse(graph.searchEdge(v2, v1));
	}
	
	//Returns true if the edge exists.
	@Test
	void removeEdgeTest3() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.addEdge(v1, v2,2);
		
		assertTrue(graph.removeEdge(v2, v1));
	}
	
	//Returns false if the edge doesn't exist.
	@Test
	void removeEdgeTest4() {
		setupStage1();
		
		ALVertex<Integer> v1 = new ALVertex<>(1);
		ALVertex<Integer> v2 = new ALVertex<>(2);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		assertFalse(graph.removeEdge(v1, v2));
	}
}
