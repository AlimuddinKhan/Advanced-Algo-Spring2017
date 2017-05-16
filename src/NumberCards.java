import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Problem Statement: "Number Cards"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/14/17 at 6:57 PM
 */
public class NumberCards {

    // note that number on two cards should be unique
    // same card can have repetitive numbers :)

    /**
     * This methos checks whether the give set has the unique digits
     * on each card or not
     * @param set Set of cards
     * @return
     */
    public static boolean isUnique(HashSet<Integer> set){
        // base case
        if (set.isEmpty())
            return false;
        BitSet digitSet = new BitSet();

        BitSet currentNumberDigits;

        //System.out.println("Given Set : " + set);
        int r;
        for (int n : set){
            currentNumberDigits = new BitSet();
            while (n > 0){

                r = n % 10;
                if (digitSet.get(r) == true)
                    return false;
                currentNumberDigits.set(r);

                n = n/10;
            }

            digitSet.or(currentNumberDigits);
        }

        //System.out.println("Digit set : " + digitSet);
        return true;
    }

    /**
     * Returns the number of unique digits power sets for the given number of cards
     *
     * Time Complexity : O(2^N) since we will be considering each set
     * @param N total number of cards
     */
    public static int getUniqueNumberedCards(int N){
        // will have current set
        HashSet<Integer> currentSet;

        // set for the current number
        HashSet<Integer> currentNumberDigitSet;

        // set for the current set digits
        HashSet<Integer> currentSetDigitSet;


        // count the unique digit sets
        int count = 0;

        int n, r;
        boolean duplicate;
        for (int i = 0; i < Math.pow(2, N); i++){
            //currentSet = new LinkedHashSet<>();
            currentSetDigitSet = new LinkedHashSet<>();
            duplicate = false;
            for (int j = 0; j < N; j++){
                if ((i & (1 << j)) > 0){

                    n = j+1;
                    currentNumberDigitSet = new HashSet<>();
                    while (n > 0){
                        r = n % 10;

                        if (currentSetDigitSet.contains(r)){
                            duplicate = true;
                            break;
                        }

                        currentNumberDigitSet.add(r);
                        n = n/10;
                    }

                    if (duplicate == false)
                        currentSetDigitSet.addAll(currentNumberDigitSet);
                    else
                        break;

                    // add the number into the set
                    //currentSet.add(j+1);

                }
            }

           // check if the given set of cards have unique digits
            //if (isUnique(currentSet))
            if (duplicate == false && !currentSetDigitSet.isEmpty())
                count++;

        }

        return count;

    }

    /**
     * This is the main method
     * @param args null
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = scanner.nextInt();
        int n;

        for (int i = 1; i <= numberOfTestCases; i++){
            n = scanner.nextInt();
            System.out.println("Case " + i + ": " + getUniqueNumberedCards(n));
        }

        scanner.close();
    }
}
