package hw3;

import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

	/* 
	 * Overview: This is a test class for
	 * testing Stack.class
	 */
	
	private Stack<Object> stack;
	// Set up
	@Before
	public void setUp() {
		stack = new Stack<>();
	}
	
	// Tear down
	@After
	public void tearDown() {
		stack = null;
	}
	
	/*
	 * Part 3: Testing pop() method
	 */
	
	/*
	 * Test for an empty stack (with size == 0)
	 */
	@Test (expected = EmptyStackException.class)
	public void testForZeroSize() {
		stack.pop();
	}
	
	/*
	 * Test for a non-empty stack and has been over called pop()
	 */
	@Test (expected = EmptyStackException.class)
	public void testForOverCalledPop() {
		stack.push("Hello");
		stack.pop();
		stack.pop();
	}
	
	/*
	 * Test for a solo string element.
	 * The result of pop() should equal the string itself.
	 */
	@Test
	public void testForSoloStringElement() {
		stack.push("Hello");
		String result = (String) stack.pop();
		assertTrue("Test for solo string element", result.equals("Hello"));
	}
	
	/*
	 * Test for a solo integer element.
	 * The result of pop() should equal the integer itself.
	 */
	@Test
	public void testForSoloIntegerElement() {
		stack.push((Integer)3);
		Integer result = (Integer) stack.pop();
		assertTrue("Test for solo integer element", result == 3);
	}
	
	/*
	 * Test for a two string elements.
	 * The result of pop() should equal the second element that has been pushed in.
	 */
	@Test
	public void testForTwoString() {
		stack.push("Hello");
		stack.push("world");
		String result = (String) stack.pop();
		assertTrue("Test for two string elements", result.equals("world"));
	}
	
	/*
	 * Test for a two mixed type elements.
	 * The result of pop() should equal the second element that has been pushed in.
	 */
	@Test
	public void testForTwoMixedElements() {
		stack.push("Hello");
		stack.push((Integer)3);
		Integer result = (Integer) stack.pop();
		assertTrue("Test for two mixed elements", result == 3);
	}
	
	/*
	 * Part 4: Testing sizeOf() method
	 */
	
	/*
	 * Test for an empty stack
	 * The result of sizeOf() should be 0
	 */
	@Test
	public void testForEmptyStackSize() {
		assertTrue("Test for empty stack size", stack.sizeOf() == 0);
	}
	
	/*
	 * Test for a solo String element
	 * The result of sizeOf() should be 1
	 */
	@Test
	public void testForSoloStringElementSize() {
		stack.push("Hello");
		assertTrue("Test for solo string element size", stack.sizeOf() == 1);
	}
	
	/*
	 * Test for a solo Integer element
	 * The result of sizeOf() should be 1
	 */
	@Test
	public void testForSoloIntegerElementSize() {
		stack.push((Integer)3);
		assertTrue("Test for solo integer element size", stack.sizeOf() == 1);
	}
	
	/*
	 * Test for 100 random integer elements
	 * The result of sizeOf() should be 100
	 */
	@Test
	public void testFor100IntegerElementSize() {
		for (int i = 0; i < 100; i++) {
			Random rand = new Random();
			stack.push(rand.nextInt(100));
		}
		assertTrue("Test for 100 integer element size", stack.sizeOf() == 100);
	}
	
	/*
	 * Test for 10000 random integer elements
	 * The result of sizeOf() should be 10000
	 */
	@Test
	public void testFor10000IntegerElementSize() {
		for (int i = 0; i < 10000; i++) {
			Random rand = new Random();
			stack.push(rand.nextInt(100));
		}
		assertTrue("Test for 10000 integer element size", stack.sizeOf() == 10000);
	}
}
