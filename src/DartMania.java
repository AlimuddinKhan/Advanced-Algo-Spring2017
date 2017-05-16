import utilities.MyFunctions;

import java.util.BitSet;
import java.util.Scanner;

/**
 * Problem Statement: "Dart-a-mania"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class DartMania {

    // possible constants to be used in the program
    // maximum score would be 60 on each dart and hence 180
    // would be the max score
    public static final int MAX_COMBINED_SCORE = 180;

    // mximum score would be 3 times the 20 i.e. 60
    public static final int MAX_SINGLE_SCORE = 60;
    public static final int TOTAL_DARTS = 3;

    // Possible scores with a board 1 to 20
    public static BitSet possibleSingleDartScores;

    // data structure to use combinations
    public static int[][][] possibleCombinations =
            new int[MAX_COMBINED_SCORE + 1]
                    [MAX_SINGLE_SCORE + 1]
                    [TOTAL_DARTS +1 ];
    public static int[][] possiblePermutations =
            new int[MAX_COMBINED_SCORE + 1][TOTAL_DARTS + 1];


    // static initializer to initialize all the data
    // structures we will be suing in this solution
    static {

        // initialize possible scores
        possibleSingleDartScores = new BitSet();

        // add 1 to 20 and their multiple into possible scores
        for (int i = 0; i <= 20; i++){
            possibleSingleDartScores.set(i);
            possibleSingleDartScores.set(2 * i);
            possibleSingleDartScores.set(3 * i);
        }

        // Bulls Eyes score of 50 which can not be multiplied
        possibleSingleDartScores.set(50);

        // initialize all default possible combinations
        // and permutations to -1
        for (int i = 0; i <= MAX_COMBINED_SCORE; i++){
            for (int j = 0; j <= MAX_SINGLE_SCORE; j++){
                for (int k = 0; k <= TOTAL_DARTS; k++){
                    possibleCombinations[i][j][k] = -1;
                    possiblePermutations[i][k] = -1;
                }
            }
        }

    }


    /**
     * This is the utility function being used by the
     * getPermutations method
     * @param dartsLeft     darts Left for rolling
     * @param targetScore   target score to achieve
     * @return returns the possible permutation to reach targetScore
     * with given dartsLeft
     */
    public static int getPermutationsUtil
            (int dartsLeft, int targetScore)
    {

        // Base Case 1: If target score is 0 then
        // only one permutation is possible
        if (targetScore == 0) {
            return 1;
        }

        // Base Case 2: If target score is negative OR
        // we do not have any darts left OR
        // target Score is grater than MAX_COMBINED_SCORE then return 0
        if (targetScore > MAX_COMBINED_SCORE
                || targetScore < 0
                || dartsLeft == 0 ) {
            return 0;
        }


        // get the already stored permutation value
        int permutations =
                possiblePermutations[targetScore][dartsLeft];

        // if this is the forst time we are calculating
        // the permutation for the given target score
        // and given darts remaining
        if (permutations == -1)
        {
            permutations = 0;

            // iterate through all the possible scores
            for (int i =
                    possibleSingleDartScores.nextSetBit(0); i!= -1;
                 i = possibleSingleDartScores.nextSetBit(i+1)){
                permutations +=
                        getPermutationsUtil(dartsLeft - 1,
                                targetScore - i);
            }

        }

        return permutations;
    }


    /**
     * This is the function which calculates the
     * possible permutation to reach targetScore
     * @param targetScore   target score to achieve
     * @return returns the possible permutation to r
     * each targetScore with given dartsLeft
     */
    public static int getPermutations(int targetScore){
        // System.out.println("getPermutations(" + targetScore + ")");
        return getPermutationsUtil(TOTAL_DARTS, targetScore);
    }


    /**
     * This is the utility function being used by the
     * getCombinations method
     * @param dartsLeft     darts Left for rolling
     * @param targetScore   target score to achieve
     * @param maxSoFar      The maximum score obtained so far
     * @return returns the possible combinations to
     *      reach targetScore with given dartsLeft
     */
    public static int getCombinationsUtil
            (int dartsLeft, int targetScore, int maxSoFar)
    {
        // Base case 1: If we have reached
        // the target score as 0 and no darts are left at the same time
        if (targetScore == 0 && dartsLeft == 0)
            return 1;

        // Base Case 2: If target score is negative OR
        // we do not have any darts left OR
        // target Score is grater than MAX_COMBINED_SCORE then return 0
        if (targetScore > MAX_COMBINED_SCORE
                || targetScore <= 0
                || dartsLeft == 0 )

            return 0;

        // get the already calculated combination
        int combinations =
                possibleCombinations[targetScore]
                        [maxSoFar][dartsLeft];

        if (combinations == -1)
        {
            combinations = 0;

            // iterate through all the possible scores
            // may be I can add one more in MaxsoFar + 1
            for (int i =
                 possibleSingleDartScores.nextSetBit(maxSoFar);
                 i!= -1;
                 i = possibleSingleDartScores.nextSetBit(i+1)){
                combinations +=
                        getCombinationsUtil(dartsLeft - 1,
                                targetScore - i,
                                i);
            }

        }

        return combinations;
    }


    /**
     * This is the  function being which calculates the possible
     * combinations to reach targetScore with given dartsLeft
     * @param targetScore   target score to achieve
     * @return returns the possible combinations to
     * reach targetScore
     */
    public static int getCombinations(int targetScore){
        return getCombinationsUtil(TOTAL_DARTS, targetScore, 0);
    }


    /**
     * This is the main method which keeps on calculating the possible permutations and
     * combinations for the given target score and prints them in the desired fashion
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int targetScore = scanner.nextInt();
        int permutations, combinations;

        // keep taking target score till it is less than or equal to zero
        while (targetScore > 0){

            combinations = getCombinations(targetScore);

            // make sure if the target score is possible
            if (combinations > 0){
                permutations = getPermutations(targetScore);
                System.out.println("NUMBER OF COMBINATIONS THAT " +
                        "SCORES " + targetScore + " IS "
                        +  combinations + ".");
                System.out.println("NUMBER OF PERMUTATIONS THAT " +
                        "SCORES " + targetScore + " IS " +
                        permutations + ".");
            } else{
                System.out.println("THE SCORE OF " + targetScore
                        + " CANNOT BE MADE WITH THREE DARTS.");
            }


            for (int i = 0; i < 70; i++){
                System.out.printf("*");
            }
            System.out.println();

            targetScore = scanner.nextInt();
        }

        System.out.println("END OF OUTPUT");
        scanner.close();

    }
}
