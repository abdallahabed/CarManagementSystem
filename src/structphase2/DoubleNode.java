package structphase2;

public class DoubleNode<T> {
	Brand element;
	DoubleNode<T> next;
	DoubleNode<T> previous;

	public DoubleNode(Brand element) {
		setElement(element);
	}

	public Brand getElement() {
		return element;
	}

	public void setElement(Brand element) {
		this.element = element;
	}

	public DoubleNode<T> getNext() {
		return next;
	}

	public void setNext(DoubleNode<T> next) {
		this.next = next;
	}

	public DoubleNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleNode<T> previous) {
		this.previous = previous;
	}

}
