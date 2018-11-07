import java.util.Iterator;

public class DrawingStack implements StackADT<DrawingChange> {

	Node<DrawingChange> top;
	int size;
	
	public DrawingStack() {
		this.top = null;
		this.size = 0;
	}
	
	@Override
	public void push(DrawingChange element) throws IllegalArgumentException {
		Node<DrawingChange> newNode = new Node<>(element, top);
		top = newNode;
		size++;
	}
	
	@Override
	public DrawingChange pop() {
		
		if (isEmpty()) {
			throw new NullPointerException("STACK IS EMPTY"); // change this message for final
		}
		
		DrawingChange remove = top.getData();
		top = top.getNext();
		size--; // decrement size of stack
		return remove; // returns removed data
		
	}
	
	@Override
	public DrawingChange peek() {
		
		if (isEmpty()) {
			throw new NullPointerException("STACK IS EMPTY"); // change for final
		}
		
		return top.getData(); // return the current stack
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public Iterator<DrawingChange> iterator() {
		
		return new DrawingStackIterator(top);
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.top == null;
	}
	
}
