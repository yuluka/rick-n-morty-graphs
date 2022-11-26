package temp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {

	private PriorityQueue<Integer, String> pq;
	
	public void setupStage1() {
		pq = new PriorityQueue<>();
	}
	
	@Test
	void test() {
		setupStage1();
		
		pq.insert(4, "a");
		pq.insert(2, "b");
		pq.insert(1, "c");
		pq.insert(5, "d");
		pq.insert(8, "e");
		pq.insert(10, "f");
		pq.insert(6, "g");
		pq.insert(2, "h");
		pq.insert(3, "i");
		
		System.out.println(pq.printPriorityQueue());
	}

}
