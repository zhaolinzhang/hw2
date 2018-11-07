package hw7;

//Introduction to Software Testing
//Authors: Paul Ammann & Jeff Offutt
//Chapter 7, page ??
//No JUnit tests at this time

public class BoundedQueue2 {
// Overview:  a BoundedQueue2 is a mutable, bounded FIFO data structure
// of fixed size , with size being fixed at 2.
// A typical Queue is [], [o1], or [o1, o2], where neither o1 nor o2
// are ever null.  Older elements are listed before newer ones.

	private final Object[] elements;
	private int size, front, back;
	private final int CAPACITY = 2;

	public BoundedQueue2() {
		elements = new Object[CAPACITY];
		size = 0;
		front = 0;
		back = 0;
	}

	public void enQueue(Object o) throws NullPointerException, IllegalStateException {
		// Modifies: this
		// Effects: If argument is null
		// throw NullPointerException
		// else if this is full, throw
		// IllegalStateException,
		// else make o the newest
		// element of this
		if (o == null)
			throw new NullPointerException("BoundedQueue2.enQueue");
		else if (size == CAPACITY)
			throw new IllegalStateException("BoundedQueue2.enQueue");
		else {
			size++;
			elements[back] = o;
			back = (back + 1) % CAPACITY;
		}
	}

	public Object deQueue() throws IllegalStateException {
		// Modifies: this
		// Effects: If queue is empty, throw IllegalStateException,
		// else remove and return oldest element of this

		if (size == 0)
			throw new IllegalStateException("BoundedQueue2.deQueue");
		else {
			size--;
			Object o = elements[(front % CAPACITY)];
			elements[front] = null;
			front = (front + 1) % CAPACITY;
			return o;
		}
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean isFull() {
		return (size == CAPACITY);
	}

	public String toString() {
		String result = "[";
		for (int i = 0; i < size; i++) {
			result += elements[(front + i) % CAPACITY].toString();
			if (i < size - 1) {
				result += ", ";
			}
		}
		result += "]";
		return result;
	}
	
	public String status() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		sb.append('[');
		sb.append(this.elements[0]);
		sb.append(',');
		sb.append(this.elements[1]);
		sb.append(']');
		sb.append(',');
		sb.append(this.size);
		sb.append(',');
		sb.append(this.front);
		sb.append(',');
		sb.append(this.back);
		sb.append(']');
		return sb.toString();
	}
	
	public static void main(String[] argc) {
		BoundedQueue2 q = new BoundedQueue2();
		System.out.println(q.status());
		
		Object obj = "Obj";
		q.enQueue(obj);
		System.out.println(q.status());
		
		q.enQueue(obj);
		System.out.println(q.status());
		
		q.deQueue();
		System.out.println(q.status());
		
		q.enQueue(obj);
		System.out.println(q.status());
		
		q.deQueue();
		System.out.println(q.status());
		
		q.deQueue();
		System.out.println(q.status());
		
		q.enQueue(obj);
		System.out.println(q.status());
		
		q.deQueue();
		System.out.println(q.status());
	}

}
