package hw2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Assignment: SWE-637 HW2
 * Date:       09-17-2018
 * Authors:    Zhaolin Zhang
 */

public class BoundedQueueTest {
	/* 
	 * Overview: This is a test class for
	 * testing BoundQueue.class
	 */
	
	private BoundedQueue q;
	// Set up
	@Before
	public void setUp() {
		q = new BoundedQueue(3);
	}
	
	// Tear down
	@After
	public void tearDown() {
		q = null;
	}
	
	/*
	 * Part 1: Testing constructor
	 */
	
	@Test
	public void testForNullQueue() {
		q = null;
		assertNull("Verify queue is null", q);
	}
	
	@Test
	public void testForEmptyQueue() {
		q = new BoundedQueue(0);
		assertNotNull("Verify queue is null", q);
		assertTrue("Verify queue is empty", q.isEmpty());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testForNegativeCapacity() {
		q = new BoundedQueue(-1);
	}
	
	@Test
	public void testForPositiveCapacity() {
		assertNotNull(q);
		assertTrue("Verify queue is empty", q.isEmpty());
	}
	
	/*
	 * Part 2: Testing enQueue() method
	 */
	@Test (expected = NullPointerException.class)
	public void testForNullElement() {
		q.enQueue("dog");
		q.enQueue(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testForSoloNullElement() {
		q.enQueue(null);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testForOverload() {
		q.enQueue("cat");
		q.enQueue("dog");
		q.enQueue("bird");
		q.enQueue("dargon");
	}
	
	@Test
	public void testForSoloElement() {
		q.enQueue("cat");
		assertNotNull("Verify queue is null", q);
		assertFalse("Verify queue is not empty", q.isEmpty());
	}
	
	@Test
	public void testForThreeElements() {
		q.enQueue("cat");
		q.enQueue("dog");
		q.enQueue("bird");
		assertNotNull("Verify queue is null", q);
		assertFalse("Verify queue is not empty", q.isEmpty());
		assertTrue("Verify queue is full", q.isFull());
	}
	
	/*
	 * Part 2: Testing deQueue() method
	 */
	@Test (expected = IllegalStateException.class)
	public void testForBelowZeroCapacity() {
		q = new BoundedQueue(0);
		q.deQueue();
	}
	
	@Test
	public void testForSoloElementDequeue() {
		q.enQueue("cat");
		q.deQueue();
		assertNotNull("Verify queue is null", q);
		assertTrue("Verify queue is empty", q.isEmpty());
	}
	
	@Test
	public void testForTwoElementsDequeue() {
		q.enQueue("cat");
		q.enQueue("dog");
		q.deQueue();
		assertNotNull("Verify queue is null", q);
		assertFalse("Verify queue is not empty", q.isEmpty());
		q.deQueue();
		assertTrue("Verify queue is empty", q.isEmpty());
	}
	
	/*
	 * Part 3: Testing isEmpty() method
	 */
	@Test
	public void testForEmpty() {
		assertTrue("Verify queue is empty", q.isEmpty());
	}
	
	@Test
	public void testForNotEmpty() {
		q.enQueue("Hello");
		assertFalse("Verify queue is not empty", q.isEmpty());
	}
	
	/*
	 * Part 4: Testing isFull() method
	 */
	@Test
	public void testForNotFull() {
		assertFalse("Verify queue is not full", q.isFull());
	}
	
	@Test
	public void testForFull() {
		q.enQueue("cat");
		q.enQueue("dog");
		q.enQueue("bird");
		assertTrue("Verify queue is full", q.isFull());
	}
	
	/*
	 * Part 5: Testing toString() method
	 */
	@Test
	public void testForNoElement() {
		assertTrue("Verify empty queue to string", q.toString().equals("[]"));
	}
	
	@Test
	public void testForSoloStringElement() {
		q.enQueue("Hello");
		assertTrue("Verify solo string element queue to string", q.toString().equals("[Hello]"));
	}
	
	@Test
	public void testForTwoStringElement() {
		q.enQueue("Hello");
		q.enQueue("World");
		assertTrue("Verify two string elements queue to string", q.toString().equals("[Hello, World]"));
	}
	
	@Test
	public void testForSoloIntegerElement() {
		q.enQueue(1);
		assertTrue("Verify solo integer element queue to string", q.toString().equals("[1]"));
	}
	
	@Test
	public void testForTwoIntegerElement() {
		q.enQueue(1);
		q.enQueue(2);
		assertTrue("Verify two integer element queue to string", q.toString().equals("[1, 2]"));
	}
	
	@Test
	public void testForMixedElement() {
		q.enQueue(1);
		q.enQueue("Hello");
		assertTrue("Verify mixed element queue to string", q.toString().equals("[1, Hello]"));
	}

}
