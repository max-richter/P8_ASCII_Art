import java.util.Iterator;

public class DrawingStackIterator implements Iterator<DrawingChange> {

	private Node<DrawingChange> next;
	
	public DrawingStackIterator(Node<DrawingChange>top) {
		this.next = top;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return next.getNext() == null;
	}

	@Override
	public DrawingChange next() {
		// TODO Auto-generated method stub
		if (!hasNext()) {
			return null;
		}
		Node<DrawingChange> curr = next;
		next = next.getNext();
		return curr.getData();
	}

}
