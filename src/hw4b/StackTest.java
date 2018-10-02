package hw4b;

import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

	private Stack<Integer> stack;

	@Before
	public void setUp() {
		stack = new Stack<Integer>();
	}

	@After
	public void tearDown() {
		stack = null;
	}

	// 2 tests for push() method
	// The 1 characteristics associated with push() are: C1

	// Test 1 for push() method: C1 - T
	@Test
	public void testPushBaseCase() {
		stack.push(1);
		assertTrue(stack.top() == 1);
	}

	// Test 2 for push() method: C1 - F
	@Test
	public void testPushC1() {
		stack.push(null);
		assertTrue(stack.top() == null);
	}

	// 3 tests for top() method
	// The 2 characteristics associated with top() are: C2, C3

	// Test 1 for top() method: C2 - T, C3 - T
	@Test
	public void testTopBaseCase() {
		stack.push(1);
		assertTrue(stack.top() == 1);
	}

	// Test 2 for top() method: C2 - F, C3 - T
	@Test
	public void testTopC2() {
		stack.push(null);
		assertTrue(stack.top() == null);
	}

	// Test 3 for top() method: C2 - F, C3 - F
	@Test(expected = EmptyStackException.class)
	public void testTopC2C3() {
		stack.top();
	}

	// 3 tests for pop() method
	// The 2 characteristics associated with pop() are: C2, C3

	// Test 1 for pop() method: C2 - T, C3 - T
	@Test
	public void testPopBaseCase() {
		stack.push(1);
		Integer i = stack.pop();
		assertTrue(i == 1);
	}

	// Test 2 for pop() method: C2 - F, C3 - T
	@Test
	public void testPopC2() {
		stack.push(null);
		Integer i = stack.pop();
		assertTrue(i == null);
	}

	// Test 3 for pop() method: C2 - F, C3 - F
	@Test(expected = EmptyStackException.class)
	public void testPopC2C3() {
		stack.pop();
	}
	
	// 2 tests for sizeOf() method
	// The 1 characteristics associated with sizeOf() are: C3
	
	// Test 1 for sizeOf() method: C3 - T
	@Test
	public void testSizeOfBaseCase() {
		stack.push(1);
		assertTrue(stack.sizeOf() == 1);
	}
	
	// Test 1 for sizeOf() method: C3 - F
	@Test
	public void testSizeOfC3() {
		assertTrue(stack.sizeOf() == 0);
	}

}
