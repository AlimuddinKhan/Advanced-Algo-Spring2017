import java.util.BitSet;
import java.util.Scanner;

/**
 * Problem Statement: "Gem Elements"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/20/17 at 10:21 AM
 */
public class GemElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        BitSet overAllSet = new BitSet();
        BitSet currentSet;
        char[] currentRock;
        for (int i = 0; i < n; i++){
            currentSet = new BitSet();
            currentRock = scanner.nextLine().toCharArray();

            // set all the bits in the bitset
            for (int j = 0; j < currentRock.length; j++){
                currentSet.set(currentRock[j] - 'a');
            }


            if (i == 0){
                overAllSet = currentSet;
            }else{
                overAllSet.and(currentSet);
            }

        }

        System.out.println(overAllSet.cardinality());
        scanner.close();
    }
}
