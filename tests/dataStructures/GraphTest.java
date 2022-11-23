package dataStructures;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

	private Graph<Integer> graph;
	
	public void setupStage() {
		graph = new Graph<>();
	}
	
	@Test
	void addVertexTest1() {
		setupStage();
		
		graph.addVertex(new Vertex<Integer>(1));
		
		assertEquals(1, graph.getAdjacentMatrix().size());
		assertEquals(1, graph.getAdjacentMatrix().get(0).size());
		
		assertEquals(-1, graph.getAdjacentMatrix().get(0).get(0));
	}
	
	@Test
	void addVertexTest2() {
		setupStage();
		
		graph.addVertex(new Vertex<Integer>(1));
		graph.addVertex(new Vertex<Integer>(2));
		
		assertEquals(2, graph.getAdjacentMatrix().size());
		assertEquals(2, graph.getAdjacentMatrix().get(0).size());
		
		assertEquals(-1, graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(-1, graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(-1, graph.getAdjacentMatrix().get(1).get(0));
		assertEquals(-1, graph.getAdjacentMatrix().get(1).get(1));

	}
	
	@Test
	void addVertexTest3() {
		setupStage();
		
		graph.addVertex(new Vertex<Integer>(1));
		graph.addVertex(new Vertex<Integer>(2));
		graph.addVertex(new Vertex<Integer>(3));
		
		assertEquals(3, graph.getAdjacentMatrix().size());
		assertEquals(3, graph.getAdjacentMatrix().get(0).size());
	}
	
	@Test
	void addEdgeTest1() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.addEdge(v1, v2, 2);
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(2,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(2,graph.getAdjacentMatrix().get(1).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(1));
	}
	
	@Test
	void addEdgeTest2() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.addEdge(v1, v2, 2);
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(2));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(2));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(1));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(2));
	}
	
	@Test
	void addEdgeTest3() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v3, v1, 999);
		
		assertEquals(2,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(2,graph.getAdjacentMatrix().get(1).get(0));
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(1));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(2));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(1));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(2));
		
		assertEquals(999,graph.getAdjacentMatrix().get(0).get(2));
		assertEquals(999,graph.getAdjacentMatrix().get(2).get(0));
		
	}
	
	@Test
	void removeVertexTest1() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.removeVertex(v3);
		
		assertEquals(2, graph.getAdjacentMatrix().size());
	}
	
	@Test
	void removeVertexTest2() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.addEdge(v1, v2, 2);
		
		graph.removeVertex(v3);
		
		assertEquals(2, graph.getAdjacentMatrix().size());
		
		assertEquals(2,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(2,graph.getAdjacentMatrix().get(1).get(0));
	}
	
	@Test
	void removeVertexTest3() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 6);
		
		graph.removeVertex(v3);
		
		assertEquals(2, graph.getAdjacentMatrix().size());
		
		assertEquals(2,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(2,graph.getAdjacentMatrix().get(1).get(0));
	}
	
	@Test
	void removeVertexTest4() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		
		graph.removeVertex(v2);
		
		assertEquals(2, graph.getAdjacentMatrix().size());
		assertEquals(2, graph.getAdjacentMatrix().get(0).size());
		assertEquals(2, graph.getAdjacentMatrix().get(1).size());
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(1));

		assertEquals(999,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(999,graph.getAdjacentMatrix().get(1).get(0));
	}
	
	@Test
	void removeVertexTest5() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		
		graph.removeVertex(v2);
		
		assertEquals(2, graph.getAdjacentMatrix().size());
		assertEquals(2, graph.getAdjacentMatrix().get(0).size());
		assertEquals(2, graph.getAdjacentMatrix().get(1).size());
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(1));

		assertEquals(999,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(999,graph.getAdjacentMatrix().get(1).get(0));
		
		assertEquals(2,graph.getVertexes().size());
		
		assertEquals(1,graph.getVertexes().get(0).getIdNum());
		assertEquals(2,graph.getVertexes().get(1).getIdNum());
	}
	
	@Test
	void removeVertexTest6() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		graph.addEdge(v4, v2, 13);
		
		graph.removeVertex(v2);
		
		assertEquals(3, graph.getAdjacentMatrix().size());
		assertEquals(3, graph.getAdjacentMatrix().get(0).size());
		assertEquals(3, graph.getAdjacentMatrix().get(1).size());
		assertEquals(3, graph.getAdjacentMatrix().get(2).size());
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(2));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(1));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(2));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(1));
		assertEquals(-1,graph.getAdjacentMatrix().get(2).get(2));

		assertEquals(999,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(999,graph.getAdjacentMatrix().get(1).get(0));
		
		assertEquals(3,graph.getVertexes().size());
		
		assertEquals(1,graph.getVertexes().get(0).getIdNum());
		assertEquals(2,graph.getVertexes().get(1).getIdNum());
		assertEquals(3,graph.getVertexes().get(2).getIdNum());
	}
	
	@Test
	void removeVertexTest7() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		graph.addEdge(v4, v2, 13);
		
		graph.removeVertex(v2);
		graph.removeVertex(v4);
		
		assertEquals(2, graph.getAdjacentMatrix().size());
		assertEquals(2, graph.getAdjacentMatrix().get(0).size());
		assertEquals(2, graph.getAdjacentMatrix().get(1).size());
		
		assertEquals(-1,graph.getAdjacentMatrix().get(0).get(0));
		assertEquals(-1,graph.getAdjacentMatrix().get(1).get(1));
		
		assertEquals(999,graph.getAdjacentMatrix().get(0).get(1));
		assertEquals(999,graph.getAdjacentMatrix().get(1).get(0));
		
		assertEquals(2,graph.getVertexes().size());
		
		assertEquals(1,graph.getVertexes().get(0).getIdNum());
		assertEquals(2,graph.getVertexes().get(1).getIdNum());
		
		assertEquals(1,graph.getVertexes().get(0).getValue());
		assertEquals(3,graph.getVertexes().get(1).getValue());
	}
	
	@Test
	void searchVertexTest1() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		assertNotNull(graph.searchVertex(v2));
	}
	
	@Test
	void searchVertexTest2() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		assertNotNull(graph.searchVertex(v4));
	}
	
	@Test
	void searchVertexTest3() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		assertNotNull(graph.searchVertex(v4));
		
		graph.removeVertex(v4);
		
		assertNull(graph.searchVertex(v4));
	}
	
	@Test
	void searchVertexTest4() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		assertFalse(graph.addVertex(v4));
	}
	
	@Test
	void searchEdgeTest1() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		graph.addEdge(v4, v2, 13);
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v2, v1));
		
		assertTrue(graph.searchEdge(v3, v1));
	}
	
	@Test
	void searchEdgeTest2() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		graph.addEdge(v4, v2, 13);
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v2, v1));
		
		graph.removeVertex(v2);
		
		assertFalse(graph.searchEdge(v2, v1));
	}
	
	@Test
	void searchEdgeTest3() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		graph.addEdge(v4, v2, 13);
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v2, v1));
		
		graph.removeEdge(v2,v1);
		
		assertFalse(graph.searchEdge(v2, v1));
	}
	
	@Test
	void removeEdgeTest1() {
		setupStage();
		
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
				
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.addEdge(v1, v2, 2);
		graph.addEdge(v1, v3, 999);
		graph.addEdge(v4, v2, 13);
		
		assertTrue(graph.removeEdge(v1, v2));
		
		assertEquals(-1, graph.getAdjacentMatrix().get(v1.getIdNum()-1).get(v2.getIdNum()-1));
		assertEquals(-1, graph.getAdjacentMatrix().get(v2.getIdNum()-1).get(v1.getIdNum()-1));
	}
}
