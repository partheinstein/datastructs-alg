import java.nio.ByteBuffer;

public class BufferFun {
    public static void main(String[] args) {
        
        ByteBuffer buf1 = ByteBuffer.allocate(3);
        System.out.println(buf1); // pos = 0, limit = 3

        buf1.put(new byte[]{1});
        System.out.println(buf1); // pos = 1, limit = 3

        buf1.flip(); // pos = 0, limit = 1
        System.out.println(buf1);

        buf1.get(); // 1
        System.out.println(buf1);

        buf1.clear(); // pos = 0, limit = 3
        System.out.println(buf1);

        buf1.put(new byte[]{1, 2}); //pos = 2, limit = 3
        System.out.println(buf1);

        buf1.flip(); // pos = 0, limit = 2
        System.out.println(buf1);
        
        buf1.get(); // pos = 1, limit = 2
        System.out.println(buf1);
        System.out.println(buf1.remaining()); // 1

        buf1.compact(); // pos = 1, limit =3
        System.out.println(buf1);

        buf1.get(); // pos = 2, limit = 3
        System.out.println(buf1);
        System.out.println(buf1.remaining()); // oddly enough it is 1 

        System.out.println(buf1.get()); // last elem which we didn't set; 0  java

        buf1.clear(); // pos = 0, limit = 3
        buf1.put(new byte[]{8}); // pos = 1, limit = 3
        buf1.flip(); // pos = 0, limit = 1
        buf1.mark(); // mark = 0
        buf1.get(); // 8
        buf1.reset(); // pos = 0, limit = 1
        System.out.println(buf1.get()); // 8
        
        

    }
}
