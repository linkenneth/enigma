package enigma;

/** A class for utility functions used throughout Enigma.
 *  @author Kenneth Lin (cs61b-ga)
 */
public class Utils {

    /** Adds A to B to return the sum of A and B, but ensures takes the
     *  modulo Main.A_LEN of the result. Ensures the result is positive by
     *  adding Main.A_LEN to result before modulo. */
    static int cycAdd(int a, int b) {
        return (a + b + Main.A_LEN) % Main.A_LEN;
    }

    /** Returns an int[] from a char[] CHARR. */
    static int[] intToCharArray(char[] chArr) {
        int[] result = new int[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            result[i] = (int) chArr[i];
        }
        return result;
    }

    /** Shifts ARR, an array, N places to the right. Items at the
     *  end are cycled around to the front of the array. */
    static void shiftArray(Object[] arr, int n) {
        Object[] tmp = new Object[n];
        System.arraycopy(arr, arr.length - n, tmp, 0, n);
        System.arraycopy(arr, 0, arr, n, arr.length - n);
        System.arraycopy(tmp, 0, arr, 0, n);
    }

}

