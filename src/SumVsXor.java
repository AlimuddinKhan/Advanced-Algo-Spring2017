import java.math.BigInteger;
import java.util.BitSet;
import java.util.Scanner;

/**
 * Problem Statement: "Sum vs XOR"
 *
 * input: 1099511627776
 * output: 1099511627776
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/20/17 at 10:31 AM
 */
public class SumVsXor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        if (n == 0) {
            System.out.println(1);
            return;
        }

        int totalbits = (int)( Math.log(n)/ Math.log(2)) + 1;
        BitSet bitset = BitSet.valueOf(new long[]{n});
        System.out.println(new BigInteger("2").
                pow(totalbits - bitset.cardinality()));
    }

}
