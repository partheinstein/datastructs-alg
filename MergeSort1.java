public class MergeSort1 {

    
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 2, 0};      


        System.out.print("orig: ");
        print(arr);
        mergeSort(arr, 0, arr.length - 1);
        System.out.print("sort: ");
        print(arr);

    }

    private static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r)/2;
            mergeSort(arr, p, q);
            mergeSort(arr, q+1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int leftSize = q - p + 1;
        int rightSize = r - q;   
        int[] left = new int[leftSize + 1];
        int[] right = new int[rightSize + 1];

        for (int i = 0; i < left.length - 1; i++) {
            left[i] = arr[p + i];
        }

        for (int j = 0; j < right.length - 1; j++) {
            right[j] = arr[q + j];
        }

        left[left.length - 1] = 10000;
        right[right.length - 1] = 10000;

        for (int i = 0, j = 0, k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i++];
            } else {
                arr[k] = right[j++];
            }
        }
        
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
