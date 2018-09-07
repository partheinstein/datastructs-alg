public class SortedList {
  private class Node {
    int val;
    Node next;
    Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  private Node head = null;
  private int order = 0;

  public SortedList() {
  }

  public SortedList(int order) {
    if (order < 0 || order > 1) {
      throw new IllegalArgumentException("order must be 0 or 1");
    }
    this.order = order;
  }

  public void add(int val) {
    Node n = new Node(val, null);
    if (head == null) {
      head = n;
      return;
    }

    Node p = head;
    Node c = head;

    while (c != null && ((order == 0 && c.val < val) || (order == 1 && c.val > val))) {
      p = c;
      c = c.next;
    }

    if (head == c) {
      // first element is bigger than
      // value to add
      head = n;
      n.next = c;
      return;
    }

    p.next = n;
    n.next = c;

    // asc order
    // add(1) - node(1, null)
    // add(-1)
    // head = node(1, null)
    // p = node(1, null)
    // c = node(1, null)
    // c != null && 1 < -1 false
    // head = node(-1, null)
    // -1.next = 1.next
    // add(0)
    // p = -1
    // c = 1
    // -1.next = 0
    // 0.next = 1

    // desc order
    // add(1)
    // add(-1)
    // p = 1
    // c = null
    // p.next = null
    // p.next = -1
    // n.next = null
    // 1, -1
    // add(0)
    // p = 1
    // c = 1
    // p = 1
    // c = -1
    // 1.next = 0
    // 0.next = -1
    // 1, 0, -1
    // add(2)
    // p = 1
    // c = 1
    // 1 > 2 false
    // head == c true
    // head = 2
    // 2.next = 1
    // 2, 1, 0. -1
  }

  public boolean delete(int val) {
    if (head == null) {
      return false;
    }

    Node p = head;
    Node c = head;
    while (c != null && ((order == 0 && c.val < val) || (order == 1 && c.val > val))) {
      p = c;
      c = c.next;
    }

    // reached the end or val is not present
    if (c == null || c.val != val) {
      return false;
    }

    if (c == head) {
      head = c.next;
      return true;
    }

    p.next = c.next;
    return true;
  }

  public void print() {
    Node c = head;
    while (c != null) {
      System.out.print(c.val + " " );
      c = c.next;
    }
  }

  public static void main(String[] args) {
    SortedList l = new SortedList();
    l.add(1);
    l.add(-1);
    l.add(0);
    l.add(-100);
    l.add(2);
    System.out.println();
    l.print();
    l.delete(0);
    System.out.println();
    l.print();
    l.delete(-100);
    System.out.println();
    l.print();

    l = new SortedList();
    l.delete(100);

    l = new SortedList(1);
    l.add(1);
    l.add(-1);
    l.add(0);
    l.add(2);
    System.out.println();
    l.print();
    l.delete(0);
    System.out.println();
    l.print();
    l.delete(2);
    System.out.println();
    l.print();

    System.out.println();
  }
}
      
