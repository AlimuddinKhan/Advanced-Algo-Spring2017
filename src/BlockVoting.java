import java.util.Scanner;

/**
 * Problem Statement: "Block Voting - 435"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class BlockVoting {


    /**
     * This method finds the Power Index for that Party index
     * and majority given
     * @param partyVotes Array representing votes for each
     * @param partyIndex
     * @param majority
     * @return Power Index for that Party index and majority given
     */
    public static int getPowerIndexUtil(
            int[] partyVotes, int partyIndex, int majority){
        int n = partyVotes.length;

        int currentSetSum, powerIndex = 0;
        boolean isValidSet;


        for (int  i = 0; i < (int)Math.pow( 2, n); i++){

            // parameters related with current set
            currentSetSum = 0;
            isValidSet = true;

            for (int j = 0; j < n; j++){


                // check if jth bit is set
                if (( i & (1 << j) ) != 0 ) {
                    currentSetSum += partyVotes[j];

                    // if sum goes greater than or equal to the
                    // then it is not the minority coalition
                    // make sure current party votes are not
                    // part of the set
                    if (j == partyIndex || currentSetSum >= majority){
                        isValidSet = false;
                    }
                }
            }

            if (isValidSet == true
                    && currentSetSum + partyVotes[partyIndex]
                    >= majority) {
                powerIndex++;
            }

        }

        //return powerSet;
        return powerIndex;

    }
/**
 *
 3
 5 7 4 2 6 6
 6 12 9 7 3 1 1
 3 2 1 1
 */

    /**
     * This method returns the power index of each party
     * @param partyVotes
     */
    public static void getPowerIndex(int[] partyVotes){
        int totalVotes = 0;
        for (int vote: partyVotes)
            totalVotes += vote;

        int majority = totalVotes/2 + 1;

        for (int i = 0; i < partyVotes.length; i++){
            System.out.println("party " +
                    (i+1) + " has power index " +
            getPowerIndexUtil(partyVotes, i, majority));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();

        int[] partyVotes;
        int numberOfParties;
        for (int i = 0; i < numberOfTestCases; i++){

            // get the parties size
            numberOfParties = scanner.nextInt();
            partyVotes = new int[numberOfParties];

            // get the votes per party
            for (int j = 0; j < numberOfParties; j++){
                partyVotes[j] = scanner.nextInt();
            }

            // get the power index
            getPowerIndex(partyVotes);
        }

        scanner.close();

    }



}
