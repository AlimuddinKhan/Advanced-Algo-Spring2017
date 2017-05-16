import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Problem Statement: "Luggage 10664"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 4/5/17
 */
public class Luggage {

    /**
     * Solves the sum in O(2^n)
     * @param a
     * @param index
     * @param targetSum
     * @return
     */
    public static boolean isEqualDistributionPossibleUtil
                            (int[] a,
                             int index,
                             int targetSum){

        // BASE CASE 1: negative target sum not possible
        if (targetSum < 0){
            return false;
        }
        // BASE CASE 2: we have got the target sum
        if (targetSum == 0) {
            return true;
        }

        // BASE CASE 2:
        if (index == 0) {
            return false;
        }

        // BASE CASE 4:
        return isEqualDistributionPossibleUtil
                (a, index -1,
                targetSum - a[index - 1]) // including
                 || isEqualDistributionPossibleUtil
                (a, index -1, targetSum); // excluding
    }


    /**
     * Solves the sum in O(2^n)
     * @param a
     * @return
     */
    public static boolean isEqualDistributionPossible(int[] a){
        int sum = IntStream.of(a).sum();
        if (sum %2 != 0)
            return false;

        return isEqualDistributionPossibleUtil
                    (a, a.length, sum/2);


    }


    /**
     * solves the subset sum problme in a dynamic
     * O(MxN) time complexity
     * @param a
     * @return
     */
    public static boolean isEqualDistributionDynamic(int[] a){
        int sum = IntStream.of(a).sum();
        if (sum %2 != 0)
            return false;
        boolean[][] dynamic = new boolean[sum/2 + 1][a.length+1];
        for (int i = 0; i < dynamic.length; i++){
            for (int j = 0; j < dynamic[0].length; j++){
                if (i == 0)
                    dynamic[i][j] = true;
                else if (j == 0)
                    dynamic[i][j] = false;
                else if (i < a[j-1])
                    dynamic[i][j] = dynamic[i][j-1];
                else
                    dynamic[i][j] = dynamic[i][j] || dynamic[i - a[j-1]][j-1];
            }

        }

        return dynamic[dynamic.length - 1][dynamic[0].length -1];
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get the number of test cases
        int numberOfTestCases = Integer.parseInt(scanner.nextLine());
        int[] a;
        String readLine;
        String[] parsedLine;
        String result;


        // iterate for each test case
        for (int i = 0; i < numberOfTestCases; i++){
            // read the entire set at once
            readLine = scanner.nextLine();
            parsedLine = readLine.split(" ");

            // parse the suitcase wts array from the line
            a = new int[parsedLine.length];
            int j = 0;
            for (String readNum: parsedLine){
                a[j] = Integer.parseInt(readNum);
                j++;
            }
            result = isEqualDistributionDynamic(a)?"YES":"NO";

            System.out.println(result);
        }
    }
}
