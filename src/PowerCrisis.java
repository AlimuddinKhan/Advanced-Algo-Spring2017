import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

/**
 * Problem Statement: "Power Crisis - 151"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class PowerCrisis {

    // May be we could use prime strides instead of trying each one
//    public static ArrayList<Integer> primeStrides;

    // last city to cut the power which is WELLINGTON in our case
    public static final int WELLINGTON = 13;


    /**
     * Returns bitset showing prime numbers up to N
     * Time Complexity : O(N)
     * @param N
     * @return
     */
//    public static BitSet getPrimeStrides(int N){
//        BitSet primeBitSet = new BitSet(N);
//        primeBitSet.set(1,N);
//
//        for (int i = 2; i*i <= N; i++){
//            if (primeBitSet.get(i) ==  true){
//                for (int j = i *2; j <= N; j += i){
//                    //System.out.println("Clearing " + j + "th Bit");
//                    primeBitSet.clear(j);
//                }
//            }
//
//        }
//
//        return primeBitSet;
//
//    }


    /**
     * returns the next available bit which has ne=ot been set in a circular fashion
     * It will return -1 if all the bits are used or consumeed
     * Time Complexity : O(N)
     * @param bs Bitset showing the towns
     * @param i Current town position
     * @return
     */
    public static int getNextAvailableTown(BitSet bs, int i){

        // base case
        if (i == 0) {
            return bs.nextSetBit(0);
        }

        else{
            int nextSetBit = bs.nextSetBit(i);
            return (nextSetBit != -1)? nextSetBit: bs.nextSetBit(0);
        }

    }


    /**
     * Return true if for the given stride size we get the WELLINGTON as the last town
     * Time Complexity : O(N^2)
     * @param N Total towns
     * @param m stride size
     * @return true if WELLINGTON islast else false
     */
    public static boolean getMinimumStrideUtil(int N, int m){
        // keep track of all the towns
        BitSet towns = new BitSet(N);
        towns.set(0,N);

        //int k = 0;

        // initialize
        towns.clear(0);
        int currentVisitedTown = 0;
        int j = 1;


        while (j < N ){

            // get the next mth unvisited town
            for (int i = 0; i < m; i++){
                currentVisitedTown = getNextAvailableTown(towns,currentVisitedTown+1);
            }

            // if we visit WELLINGTON before the end then break
            if (currentVisitedTown == WELLINGTON -1) {
                j++;
                break;
            }

            // mark the visited town and increase counter of visited towns
            if (currentVisitedTown != -1) {
                towns.clear(currentVisitedTown);
                j++;
            }else {
                break;
            }
        }

        // make sure we have visited all the towns
       return j == N ;
    }


    /**
     * Returns the minimum strides to make WELLINGTON as the last town
     * @param N Total number of towns
     * @return Minimu strides to make WELLINGTON as the last town to power cut
     */
    public static int getMinimumStride(int N){

        //BitSet primeStrides = getPrimeStrides(N);

        //for (int i = primeStrides.nextSetBit(0); i != -1; i = primeStrides.nextSetBit(i+1)){
        for (int i = 1; i <=N ; i++){
            if (getMinimumStrideUtil(N, i) == true) {
                //System.out.println("Returning true for " + i);
                return i;
            }
        }
        return N;

    }
/**
 * Test Cases:
 * 1.
17
0

 2.
20
30
17
0
 **/
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = -1;
        n = scanner.nextInt();

        // keep taking the input until user types 0
        while (n != 0){
            System.out.println(getMinimumStride(n));
            n = scanner.nextInt();
        }
        scanner.close();

    }
}
