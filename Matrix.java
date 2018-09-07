public class Matrix {

    public static int[][] multiply(int[][] m1, int[][] m2) {
        if (m1[0].length != m2.length) {
            return null;
        }

        int[][] res = new int[m1.length][m2[0].length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[i].length; j++) {
                int sum = 0;
                for (int k = 0; k < m2[i].length; k++) {
                    sum += m1[i][k] * m2[k][j];
                }
                System.out.println();
                        
                res[i][j] = sum;
            }
        }
        
        
        return res;
        
                       
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] arr = new int[2][3];
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 3;
        arr[1][0] = 4;
        arr[1][1] = 5;
        arr[1][2] = 6;
        //        print(arr);

        int[][] m1 = new int[2][2];
        m1[0][0] = 1;
        m1[0][1] = 2;
        arr[1][0] = 3;
        arr[1][1] = 4;

        int[][] res = multiply(m1, m1);
        print(res);
    }
}
        
