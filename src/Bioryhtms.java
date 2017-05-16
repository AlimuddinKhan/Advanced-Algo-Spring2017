import java.util.Scanner;

/**
 * Problem Statement: "Biorythms (756)"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 4/6/17
 */
public class Bioryhtms {
    static final int P = 23, E = 28, M = 33, LCM = 21252;


    /**
     * Returns the first peak from the start of the year
     * Time Complexity : O(N)
     * @param i
     * @param j
     * @param k
     * @return
     */
    public static int getFirstPeakFromStartOfYear
                                    (int i, int j, int k){
        i = i%P;
        j = j%E;
        k = k%M;

        int x = 1;
        // kep doing till al of the are same
        while (  i != j || j != k || k != i){
            i += P;
            if(i > j){
                j += E;
            }
            if (j > k) {
                k += M;
            }
            x++;
        }
        return i;
    }


    /**
     * Returns the first peak from the given day
     * @param p
     * @param e
     * @param m
     * @param d
     * @return
     */
    public static int getFirstPeakFromGivenDay
                                (int p, int e, int m, int d){
        int firstPeak = getFirstPeakFromStartOfYear(p,e,m);
        if (firstPeak > d)
            return firstPeak - d;

        // get the next peak
        firstPeak += LCM;
        return firstPeak - d;
    }


    // solves the Biorythms problem
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line;
        int p, e, m, d, peak, testNumber = 1;

        while (true){
            line = scanner.nextLine().split(" ");
            p = Integer.parseInt(line[0]);
            e = Integer.parseInt(line[1]);
            m = Integer.parseInt(line[2]);
            d = Integer.parseInt(line[3]);

            if (p < 0)
                break;
            peak = getFirstPeakFromGivenDay(p, e, m, d);
            System.out.println("Case " + testNumber +
                    ": the next triple peak occurs in "
                    + peak +" days.");
            testNumber++;

        }


        scanner.close();

    }

}
