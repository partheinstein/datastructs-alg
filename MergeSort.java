public class MergeSort {
    
    public static void main(String[] args) {
//        int[] arr = new int[]{1};
//        int[] arr = new int[]{4, 1};          
//       int[] arr = new int[]{1, 4, 2};          
//        int[] arr = new int[]{1, 4, 2, 0};      
        int[] arr = new int[]{1, 2, 4, 1, 3, 10};
//        int[] arr = new int[]{1, 2, 4, 1, 3, 10, 0, 5};

        System.out.print("orig: ");
        print(arr);
        
        mergeSort(arr, 0, arr.length - 1);

        System.out.print("sort: ");
        print(arr);
    }

    // arr = 1, 4, 2, 0
    // p = 0
    // r = 4
    // q = 2
    // mergeSort(arr, 0, 2)                                                                                                   mergeSort(arr, 3, 4)
    // p = 0                                                                                                                  p = 3
    // r = 2                                                                                                                  r = 4
    // q = 1                                                                                                                  q = 3
    // mergeSort(arr, 0, 1)     mergeSort(arr, 2, 2)                                                                          mergeSort(arr, 3, 3)      mergeSort(arr, 4, 4)
    // p = 0                    p = 2                                                                                         3 < 3 false               4 < 4 false
    // r = 1                    r = 2                                                                                         
    // q = 0                    2 < 2 false                                                                                   merge(arr, 3, 3, 4)
    // mergeSort(arr, 0, 0)                                                                                                   leftSize = (3 - 3) + 1 = 1
    // 0 < 0 false                                                                                                            rightSize = (4 - 3) + 1 = 2
    //                                                                                                                        left = inf
    // merge(arr, 0, 1, 2)                                                                                                    right = 0, inf
    // leftSize = (1 - 0) + 1 = 2                                                                                             r = 4
    // rightSize = (2 - 1) + 1 = 2                                                                                            k = 3; i = 0; j = 0; arr[3] = 0; i = i + 1
    //                                                                                                                        k = 4; k = r; stop
    //                                                                                                                        arr = 1, 4, 2, 0
    // left = 1, inf                                                                                                          
    // right = 4, inf                                                                                                         
    // r = 2                                                                                                                  
    // k = 0; i = 0; j = 0; left[0] = 1; right[0] = 4; arr[0] = left[0] = 1; i = i + 1 = 1                                    
    // k = 1; i = 1; j = 0; left[1] = inf; right[0] = 4; arr[1] = right[0] = 4; j = j + 1 = 1                                 
    // k = 2; k == r; stop
    // arr = 1, 4, 2, 0                                                                                                       

    // merge(arr, 0, 2, 4)
    // leftSize = (2 - 0) + 1 = 3
    // rightSize = (4 - 2) + 1 = 3
    // left = 1, 4, inf
    // right = 2, 0, inf
    // r = 4
    // k = 0; i = 0; j = 0; left[0] = 1; right[0] = 0; arr[0] = left[0] = 1; i = i + 1 = 1
    // k = 1; i = 1; j = 2; left[1] = 4; right[0] = 2; arr[1] = right[0] = 2; j = j + 1 = 1
    // k = 2; i = 1; j = 1; left[1] = 4; right[1] = 0; arr[2] = right[1] = 0; j = j + 1 = 2
    // k = 3; i = 1; j = 2; left[1] = 4; right[2] = inf; arr[3] = left[1] = 4; i = i + 1 = 2
    // k = 4; k == r; stop
    // arr = 1, 2, 0, 4

    private static void mergeSort(int[] arr, int p, int r) {
        System.out.println("mergeSort(arr," + p + "," + r + ")");
        if (p < r) {
            System.out.println("p < r");
            int q = (p + r)/2;
            System.out.println("q = (" + p + " + " + r + ")/2 = " + q);
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        } else {
            System.out.println("p >= r");
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        System.out.println("merge(arr," + p + "," + q + "," + r + ")");
        System.out.print("- arr: ");
        print(arr);
        int leftSize = (q - p) + 1;  
        int rightSize = (r - q); 
        int[] left = new int[leftSize + 1]; 
        int[] right = new int[rightSize + 1];
        for(int i = 0; i < left.length - 1; i++) {
            left[i] = arr[p + i];
        }

        for(int j = 0; j < right.length - 1; j++) {
            right[j] = arr[q + 1 + j];
        }

        left[left.length - 1] = 100000;
        right[right.length - 1] = 100000;
        System.out.print("- left:");
        print(left);
        System.out.print("- right:");
        print(right);

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i = i + 1;
            } else {
                arr[k] = right[j];
                j = j + 1;
            }
            System.out.println("-- " + "p=" + p + " q=" + q + " r=" + r + " i=" + i + " j=" + j + " k=" + k + " arr[" + k +"]=" + arr[k]);
        }

        System.out.print("- arr: ");
        print(arr);

    }

    private static void print(int[] arr) {
        System.out.print(" ");
        for (int i=0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }


}
