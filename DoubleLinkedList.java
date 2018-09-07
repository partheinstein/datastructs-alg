public class DoubleLinkedList {
    private class Node {
      int val;
      Node prev;
      Node next;

      Node(int val, Node prev, Node next) {
        this.val = val;
        this.prev = prev;
        this.next = null;
      }
    }

    private Node head;

    public void add(int val) {
      Node n = new Node(val, null, null);
      if (head == null) {
        head = n;
        return;
      }

      Node c = head;
      while (c.next != null) {
        c = c.next;
      }

      c.next = n;
      n.prev = c;
   }

   public boolean delete(int val) {
     if (head == null) {
       return false;
     }

     Node c = head;
     while (c.next != null && c.val != val) {
       c = c.next;
     }

     if (c == head && c.val == val) {
       head = c.next;

       // delink c
       if (c.next != null) {
         c.next.prev = head;
       }

       c.prev = null;
       c.next = null;
       return true;
     }

     if (c.val != val) {
       return false;
     }

     // delink c
     if (c.next != null) {
       c.next.prev = c.prev;
     }

     // c.prev can't be null here
     c.prev.next = c.next;

     c.prev = null;
     c.next = null;
     return true;
   }

   public void print() {
     if (head == null) {
       System.out.println("empty");
       return;
     }
     Node c = head;
     while (c != null) {
       System.out.print(c.val + " ");
       c = c.next;
     }
   }

   public static void main(String[] args) {
     DoubleLinkedList l = new DoubleLinkedList();
     l.add(1);
     l.add(-1);
     l.print();
     System.out.println();
     l.delete(1);
     System.out.println();
     l.print();
     l.delete(-1);
     System.out.println();
     l.print();
     l.add(1);
     l.add(10);
     l.add(2);
     System.out.println();
     l.print();
     l.delete(10);
     System.out.println();
     l.print();
   }
       
}
