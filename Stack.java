public class Stack {
    class Item {
        int data;
        Item prev;
        Item nextMin;
    }
    
    Item head;
    Item min;

    public void push(int data) {
        Item t = new Item();
        t.data = data;
        t.prev = head;

        if (head == null) {
            min = t;
        }

        if (t.data < min.data) {
            t.nextMin = min;
            min = t;
        }
        
        head = t;
    }

    public Item pop() {
        if (head != null) {
            Item item = head;

            if (min == item) {
                min = item.nextMin;
                item.nextMin = null;
            }
            
            head = head.prev;            
            return item;
        }
        return null;
    }

    public Item getMin() {
        return min;
    }

    public static void main(String[] args) {
        Stack s = new Stack();
        // s.push(1);
        // s.push(2);
        // System.out.println(s.pop().data);
        // System.out.println(s.pop().data);
        // System.out.println(s.pop());
        // s.push(3);
        // System.out.println(s.pop().data);

        s.push(2);
        s.push(3);
        s.push(1);
        s.push(4);

        System.out.println(s.getMin().data); //1
        s.pop(); // 4
        System.out.println(s.getMin().data); //1
        s.pop(); // 1
        System.out.println(s.getMin().data); //2
        s.pop(); // 3
        System.out.println(s.getMin().data); //2
        s.pop(); // 2
        System.out.println(s.getMin()); //null

        s.push(4);
        s.push(3);
        s.push(2);
        s.push(1);

        System.out.println(s.getMin().data); //1
        s.pop();
        System.out.println(s.getMin().data); //2
        s.pop();
        System.out.println(s.getMin().data); //3
        s.pop();
        System.out.println(s.getMin().data); //4
        s.pop();
        System.out.println(s.getMin()); //null

        
        
    }
}
