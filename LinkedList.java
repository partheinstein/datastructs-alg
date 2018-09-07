/*
 * Linked list with head
 *
 */
public class LinkedList {
    static class Node {
        int val;
        Node next;
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node head = null;

    public void add(int val) {
        // add check to ensure val is valid
        Node n = new Node(val, null);
        add(n);
    }

    public void add(Node n) {
        if (head == null) {
            head = n;
            n.next = null;
            return;
        }
        
        Node p = null;
        Node c = head;
        while (c != null) {
            p = c;
            c = c.next;
        }
        p.next = n;
        n.next = c;
    }

    /**
     * delete a node with given value
     * @param value to delete
     * @return true if deleted, false otherwise
     */
    public boolean delete(int val) {
        if (head == null) {
            return false;
        }

        Node p = head;
        Node c = head;
        while (c != null) {
            if (c.val == val) {
                p.next = c.next;
                // delete c
                return true;
            }
            p = c;
            c = c.next;
        }
        return false;
    }

    public void print() {
        Node c = head;
        while (c != null) {
            System.out.print(c.val + " ");
            c = c.next;
        }
        System.out.println();
    }

    public void removeDuplicates() {
        if (head == null || head.next == null) {
            return;
        }
        Node n = head;
        while(n != null) {
            Node p = n;
            Node c = n.next;
            while (c != null) {
                if (c.val == n.val) {
                    p.next = c.next;
                    c.next = null;
                    p = c.next;
                } else {
                    p = c;
                }
                c = c.next;
            }
            n = n.next;
        }
    }

    public void deleteNode(Node n) {
        if (n == null || head == null) {
            return;
        }

        if (n == head) {
            head = null;
            return;
        }

        if (n.next == null) {
            // cant copy from null;
            n.val = -1;
            return;
        }

        Node next = n.next;
        n.val = next.val;
        n.next = next.next;
    }

    // doesn't actually find the circle
    // just finds the meeting point where
    // the slow and fast pointers meet
    // see findBegCircle2
    public Node findBegCircle() {
        Node s = head;
        Node f = head;
        while (s != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                return s;
            }
        }
        return null;
    }

    public Node findBegCircle2() {
        Node s = head;
        Node f = head;

        System.out.println("s: " + s.val);
        System.out.println("f: " + f.val);

        while (f.next != null) {
            s = s.next;
            f = f.next.next;

            System.out.println("s: " + s.val);
            System.out.println("f: " + f.val);
            
            if (s == f) {
                break;
            }
        }
        if (f.next == null) {
            return null;
        }
        
        System.out.println("s == f; meeting point");
        System.out.println("s: " + s.val);
        System.out.println("f: " + f.val);

        s = head;
        System.out.println("reset s to head...");
        System.out.println("s: " + s.val);
        System.out.println("f: " + f.val);
        while (s != f) {
            s = s.next;
            f = f.next;
            System.out.println("s: " + s.val);
            System.out.println("f: " + f.val);            
        }
        return f;
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        //        l.add(1);
        //        l.add(2);
        //        l.add(5);
        //        System.out.println();
        //        l.print();
        //        l.delete(2);
        //        System.out.println();
        //        l.print();

        // l.add(1);
        // l.add(2);
        // l.add(2);
        // l.add(3);
        // l.add(3);
        // l.add(1);

        // l.print();

        // l.removeDuplicates();

        // l.print();

        // l = new LinkedList();
        // l.add(1);
        // l.add(2);
        // l.add(1);

        // l.print();
        // l.removeDuplicates();
        // l.print();

        // l = new LinkedList();
        // l.add(1);
        // l.add(2);
        // l.add(2);
        // l.add(1);
        // l.add(3);

        // l.print();
        // l.removeDuplicates();
        // l.print();

        Node n1 = new Node(1, null);
        l.add(n1);

        Node n2 = new Node(2, null);
        l.add(n2);

        Node n3 = new Node(3, null);
        l.add(n3);

        Node n4 = new Node(4, null);
        l.add(n4);

        n4.next = n2;

        Node n = l.findBegCircle();
        System.out.println(n.val);
        n = l.findBegCircle2();
        System.out.println(n.val);
        


        //        l.print();
        // l.deleteNode(n2);
        //        l.print();

        //l.deleteNode(n4);
        //l.print();        

    }
}
