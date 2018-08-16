import edu.princeton.cs.algs4.StdOut;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    static int RADIX = 256;

    public static String[] sort(String[] asciis) {
        int maxLength = Integer.MIN_VALUE;
        for (String s : asciis) {
            maxLength = maxLength > s.length() ? maxLength : s.length();
        }
        String[] res = asciis.clone();
        for (int d = maxLength - 1; d >= 0; d--) {
            res = sortHelperLSD(res, d);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {

        int[] counts = new int[RADIX];
        for (String s: asciis) {
            if (s.length() - 1 < index) {
                counts[0]++;
            } else {
                counts[s.charAt(index)]++;
            }
        }

        int[] starts = new int[RADIX];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s: asciis) {
            if (s.length() -1 < index) {
                int place = starts[0];
                sorted[place] = s;
                starts[0] += 1;
            } else {
                int place = starts[s.charAt(index)];
                sorted[place] = s;
                starts[s.charAt(index)] += 1;
            }
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = new String[]{"sa", "b", "zsd", "gwrsyj", "gds", "gwasyj"};
        asciis = sort(asciis);
        for (String s: asciis) {
            StdOut.println(s);
        }
    }
}
