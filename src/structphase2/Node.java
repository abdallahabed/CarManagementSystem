package structphase2;

public class Node<T> {
	T element;
	Node<T> next;

	public Node(T element) {
		setElement(element);
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

}