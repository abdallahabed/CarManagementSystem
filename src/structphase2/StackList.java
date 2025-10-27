package structphase2;

public class StackList {
	private LinkedList<Car> linkedList;

	public StackList() {
		linkedList = new LinkedList<>();
	}

	public boolean isEmpty() {
		return linkedList.isEmpty();

	}

	public void push(Car o) {

		linkedList.addFisrt(o);

	}

	public Object pop() {
		if (!isEmpty()) {

			Object tmp = linkedList.getFirst();
			linkedList.removeFirst();
			
			return tmp;

		} else {
			System.out.println("stack is empty");
			return null;
		}
	}

	public Object top() {
		if (!isEmpty()) {
			return linkedList.getLast();
		} else {
			System.out.println("stack is empty");
			return null;
		}
	}

	public int size() {
		return linkedList.getCount();
	}

//	public boolean isFull() {
//		if (linkedList != null)
//			return false;
//	}

	public void print() {
		linkedList.printList();
	}

	public LinkedList<Car> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<Car> linkedList) {
		this.linkedList = linkedList;
	}

}
