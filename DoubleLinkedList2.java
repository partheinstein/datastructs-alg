/**
 * Double Linked list with head and tail
 */
public class DoubleLinkedList2 {
  private class Node {
    int val;
    Node prev;
    Node next;
    Node(int val, Node prev, Node next) {
      this.val = val;
      this.prev = prev;
      this.next = next;
    }
  }

  private Node head = null;
  private Node tail = null;

  public void add(int val) {
    if (head == null) {
      head = new Node(val, null, null);
      tail = head;
      return;
    }

    Node n = new Node(val, tail, null);
    tail.next = n;
    
    tail = n;
    return;
  }

  public boolean delete(int val) {
    if (head == null) {
      return false;
    }

    Node c = head;
    while(c != null && c.val != val) {
      c = c.next;
    }

    if (c == null) {
      return false;
    }

    if (c == head) {
      head = c.next;
    }

    if (c == tail) {
      tail = c.prev;
    } 

    if (c.prev != null) {
      c.prev.next = c.next;
    }

    if (c.next != null) {
      c.next.prev = c.prev;
    }

    c.prev = null;
    c.next = null;

    return true;
  }

  public void print() {
    if (head == null) {
      System.out.print("empty");
      return;
    }
    for (Node c = head; c != null; c = c.next) {
      System.out.print(c.val + " ");
    }
  }

  public static void main(String[] args) {
    LinkedList2 l = new LinkedList2();
    l.add(1);
    l.add(2);
    l.add(3);
    l.print();
    l.delete(1);
    System.out.println();
    l.print();
    l.delete(3);
    System.out.println();
    l.print();
    l.delete(2);
    System.out.println();
    l.print();
    System.out.println();
    l = new LinkedList2();
    l.add(1);
    l.print();
    System.out.println();
    l.delete(1);
    l.print();
    System.out.println();
  }
}
 
