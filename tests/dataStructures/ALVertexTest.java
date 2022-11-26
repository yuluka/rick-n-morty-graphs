package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ALVertexTest {
	
	private ALVertex<Integer> vertex;
	
	//Creates an empty vertex.
	public void setupStage1() {
		vertex = new ALVertex<>();
	}
	
	public void setupStage2() {
		vertex = new ALVertex<>(3);
	}
	
	public void setupStage3() {
		vertex = new ALVertex<>(3);
		
		vertex.addAdjacent(new ALVertex<>(9),2);
		vertex.addAdjacent(new ALVertex<>(6),4);
	}
	
	//Tests that the creation is working correctly.
	@Test
	void creationTest() {
		setupStage1();
		
		assertNotNull(vertex);
	}
	
	//Tests that the value, of the empty vertex, is null.
	@Test
	void creationTest2() {
		setupStage1();
		
		assertNull(vertex.getValue());
	}
	
	//Tests that the adjacent vertexes list is not null.
	@Test
	void creationTest3() {
		setupStage1();
		
		assertNotNull(vertex.getAdjacents());
	}
	
	//Tests that the adjacent vertexes list is not null, but when we use the other constructor.
	@Test
	void creationTest4() {
		setupStage2();
		
		assertNotNull(vertex.getAdjacents());
	}

	//Tests that the new vertex is not null, but when we use the other constructor.
	@Test
	void creationTest5() {
		setupStage2();
		
		assertNotNull(vertex);
	}
	
	//Tests that the new vertex has the specified value.
	@Test
	void creationTest6() {
		setupStage2();
		
		assertNotNull(vertex.getValue());
		assertEquals(3, vertex.getValue());
	}
	
	//Tests that the vertex value is updated to the new one.
	@Test
	void setTest1() {
		setupStage1();
		
		vertex.setValue(8);
		
		assertEquals(8, vertex.getValue());
	}
	
	//Tests that the adjacent vertex is added correctly.
	@Test
	void addAdjacentTest1() {
		setupStage2();
		
		ALVertex<Integer> newAdjacent = new ALVertex<>(9);
		assertTrue(vertex.addAdjacent(newAdjacent,6));
		
		assertTrue(vertex.searchAdjacent(newAdjacent));
	}
	
	//Tests that addAdjacent method returns false if the adjacent vertex already existed before.
	@Test
	void addAdjacentTest2() {
		setupStage2();
		
		ALVertex<Integer> newAdjacent = new ALVertex<>(9);
		vertex.addAdjacent(newAdjacent,6);
		
		assertFalse(vertex.addAdjacent(newAdjacent,6));
	}
	
	//Tests that the removeAdjacent method removes the specified vertex correctly.
	@Test
	void removeAdjacentTest1() {
		setupStage3();
		
		assertTrue(vertex.removeAdjacent(new ALVertex<>(9)));
		assertFalse(vertex.searchAdjacent(new ALVertex<>(9)));
	}
	
	//Tests that the removeAdjacent method removes the specified vertex correctly.
	@Test
	void removeAdjacentTest2() {
		setupStage3();
		
		vertex.removeAdjacent(new ALVertex<>(9));
		assertFalse(vertex.removeAdjacent(new ALVertex<>(9)));
	}
}
