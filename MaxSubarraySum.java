/**
 * Maximum Subarray Problem
 * Given an array of integers, find contiguous subarray within it which has the 
 * largest sum
 * input: {-2, 1, -3, 4, -1, 2, 1, -5, 4}
 * output: {4, -1, 2, 1} with sum 6
 */
public class MaxSubarraySum {
    public static int[] findMaxSubArraySum(int[] arr){
    
      int maxSum = -10000;
      int maxi = 0;
      int maxLength = arr.length;

      for (int i=0; i < arr.length; i++) {              
        int sum = arr[i];                               
        int length = 1;                                 

        if (sum > maxSum) {
           maxSum = sum;
           maxLength = length;
           maxi = i;
        }
           
        for (int j = i + 1; j < arr.length; j++) {
          sum = sum + arr[j];
          length = length + 1;          
          if (sum > maxSum) {
            maxSum = sum;
            maxLength = length;
            maxi = i;        
          }
        }
      }

      System.out.println("maxSum: " + maxSum);

      int[] subArr = new int[maxLength];
      System.arraycopy(arr, maxi, subArr, 0, maxLength);
      System.out.print("subarr: ");
      print(subArr);
      System.out.println("len: " + maxLength);
      return subArr;
    }

    // copied from 
    // stack overflow
    public static int solve2(int[] input) {

        int bestSum = -Integer.MAX_VALUE;

        for (int start = 0; start < input.length; start++) {

            // Compute the sum of input[start, end] and update
            // 'bestSum' if we found a new max subarray sum.

            // Set the sum to initial input value to handle edge case
            // of all the values being negative.
            int sum = input[start];
            bestSum = Math.max(sum, bestSum);

            for (int end = start+1; end < input.length; end++) {
                sum += input[end];
                bestSum = Math.max(sum, bestSum);
            }
        }

        return bestSum;
    }
    
    public static void print(int[] arr) {
      for(int i : arr) {
        System.out.print(i + " ");
      }
      System.out.println();
    }

    public static boolean assertEquals(int[] expected, int[] actual) {
      if (expected.length != actual.length) {
        return false;
      }
      for (int i = 0; i < expected.length; i++) {
        if (expected[i] != actual[i]) {
          return false;
        }
      }
      return true;
    }

    public static void test(int[] array, int[] expected) {
      System.out.print("input: ");     
      print(array);
      System.out.print("expected: ");     
      print(expected);
      int[] actual = findMaxSubArraySum(array);
      System.out.println("testPassed? " + assertEquals(expected, actual));
      System.out.println(solve2(array));
    }
    
    public static void main(String[] args) {
        // empty
        test(new int[]{}, new int[]{});
        System.out.println("--");

        // 1 element
        test(new int[]{-8}, new int[]{-8});
        System.out.println("--");
        
        // all negatives
        test(new int[]{-8, -3, -6, -2, -5, -4}, new int[]{-2});
        System.out.println("--");

        // combo
        test(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, new int[]{4, -1, 2, 1});
        System.out.println("--");

        // all positive
        test(new int[]{2, 1, 3, 4, 1, 2, 1, 5, 4}, new int[]{2, 1, 3, 4, 1, 2, 1, 5, 4});
    }
}
