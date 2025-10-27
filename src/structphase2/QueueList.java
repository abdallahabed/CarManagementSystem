package structphase2;

public class QueueList {
	private int size;

	private LinkedList<Orders> linkedList;

	public QueueList() {
		linkedList = new LinkedList<>();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Orders dequeue() {
		if (isEmpty())
			return null;
		else {
			Orders o = linkedList.getFirst();
			linkedList.removeFirst();
			return o;
		}
	}

	public void enqueue(Orders o) {
		size++;
		linkedList.addLast(o);
	}

//	public void printQueue() {
//		for (int i = 0; i < size; i++) {
//			Orders o = dequeue();
//			System.out.println(
//					o.getStatus() + " " + o.getCustomer().getName() + " " + o.getCustomer().getMobileNo() + "test");
//			enqueue(o);
//		}
//	}

}
