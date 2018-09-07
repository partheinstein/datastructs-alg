public class Queue {
    class Item {
        int data;
        Item prev;
        Item next;
    }

    Item head = null;
    Item tail = null;

    public void enqueue(int data) {
        Item t = new Item();
        t.data = data;
        t.next = null;
        t.prev = head;
        if (head != null) {
            head.next = t;
        }
        head = t;

        if (tail == null) {
            tail = t;
        }
        
    }

    public Item dequeue() {
        if (tail == null) {
            return null;
        }

        Item t = tail;
        tail = tail.next;
        if (tail != null) {
            tail.prev = null;
        }
        t.next = null;
        t.prev = null;
        return t;
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue().data);
        System.out.println(q.dequeue().data);
        System.out.println(q.dequeue().data);
        q.enqueue(4);
        System.out.println(q.dequeue().data);
    }
}
        
          
