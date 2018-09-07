public class GoogleInterview {
    public static class Queue {
	class Node {
            int data;
            Node next;
            Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
	}
	Node head = null;
	Node tail = null;
	final int size;
	int count = 0;
	
	Queue(int size) {
            this.size = size;
	}

	void enqueue(int data) {
            if (head == null) {
                head = new Node(data, null);
                tail = head;
                count++;
                return;
            }
            Node n = new Node(data, null);
            head.next = n;
            head = n;

            if (count == size) {
                tail = tail.next;
            } else {
                count++;
            }
	}

	double getAvg() {
            int sum = 0;
            int i = 0;
            for (Node n = tail; n != null; n = n.next) {
                sum += n.data;
                i++;
            }
            return sum/i;
	}
    }

    public static void main(String[] args) {
        Queue q = new Queue(3);
        q.enqueue(7);
        System.out.println(q.getAvg());
        q.enqueue(8);
        System.out.println(q.getAvg());
        q.enqueue(9);
        System.out.println(q.getAvg());
        q.enqueue(10);
        System.out.println(q.getAvg());        
        
    }
        
}
