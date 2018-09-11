public class Queue1 {
    final int size;
    final char[] arr;
    int qIdx = 0;
    int dIdx = 0;

    Queue(int size) {
        this.size = size;
        this.arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = '.';
        }
    }

    void enqueue(char c) {
        System.out.println("enqueue " + c);
        arr[qIdx] = c;
        qIdx = (qIdx + 1) % size;
    }

    char dequeue() {
        System.out.println("dequeue ");
        char c = '.';
        if (dIdx > qIdx) {
            c = arr[dIdx];
            arr[dIdx] = '.';
            dIdx = (dIdx + 1) % size;
        }
        return c;
    }

    void print() {
        System.out.println("qIdx: " + qIdx + ", dIdx: " + dIdx);
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.enqueue('a');
        q.print();
        q.enqueue('b');
        q.print();
        q.dequeue();
        q.print();
        q.dequeue();
        q.print();
        q.dequeue();
        q.print();
        q.dequeue();
        q.print();
        q.enqueue('c');
        q.print();
        q.enqueue('d');
        q.print();
        q.dequeue();
        q.print();
        q.enqueue('e');
        q.print();
        q.enqueue('f');
        q.print();
    }
}
