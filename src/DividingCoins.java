import java.util.Scanner;
/**
 * Problem Statement: "Dividing Coins : 562"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/14/17 at 10:46 PM
 */

public class DividingCoins {

    public static int minSplitCoinUtil(int[] a, int index, int sum1, int sum2){
        // base case nor more coins left
        if (index == 0) {
            return (int) Math.abs(sum1 - sum2);
        }
        return Math.min(minSplitCoinUtil(a, index-1, sum1 + a[index - 1], sum2),
                minSplitCoinUtil(a, index-1, sum1, sum2 + a[index - 1]));
    }

    public static int minSplit(int[] coins){
        return minSplitCoinUtil(coins, coins.length, 0, 0);
    }

/**
2
3
2 3 5
4
1 2 4 6
**/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        int[] outputArray = new int[numberOfTestCases];
        int[] coins;
        int numberOfCoins;
        for (int i = 0; i < numberOfTestCases; i++){
            numberOfCoins = scanner.nextInt();
            coins = new int[numberOfCoins];
            for (int j = 0;j < numberOfCoins; j++){
                coins[j] = scanner.nextInt();
            }

            // calculate the min difference
            outputArray[i] = minSplit(coins);
        }

        // print the output
        for(int result: outputArray){
            System.out.println(result);
        }

    }
}

