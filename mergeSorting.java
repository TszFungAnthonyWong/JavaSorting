class Solution {
    public static void mergesort(int[] array) {
        try{
            mergesort(array, new int[array.length], 0, array.length - 1);
        }catch(Exception e){}
        
    }

    public static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) throws Exception{
        if (leftStart >= rightEnd) {
            return;
        }

        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;

        Runnable r1 = () -> {
            try {
                mergesort(array, temp, leftStart, leftEnd);
            } catch (Exception e) {
                //TODO: handle exception
            }
           
        };

        Runnable r2 = () -> {
            try {
                mergesort(array, temp, rightStart, rightEnd);
            } catch (Exception e) {
                //TODO: handle exception
            }
           
        };

       Thread t1 = new Thread(r1);
       Thread t2 = new Thread(r2);

       t1.start();
       t2.start();

       t1.join();
       t2.join();
      
        mergehalves(array, temp, leftStart, rightEnd);
    }

    public static void mergehalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] > array[right]) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];
                right++;
            }
            index++;
        }
        System.arraycopy(array, left, temp, index, leftEnd - left + 1);
        System.arraycopy(array, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, array, leftStart, size);
    }
}

public class mergeSorting {
    public static void main(String[] args) {

        int[] array = { 7, 12, 5, 57, 234, 16, 2, 653, 3, 8, 765, 434, 56, 90, 5, 335, 8, 1, 1 };
        int[] asd = { 5, 4, 6, 7, 3, 1, 2, 8 };

        Solution.mergesort(array);

        for(int k : array){
            System.out.println(k);
        }

    }
}