import utilities.MyFunctions;

import java.util.Scanner;

/**
 * Problem Statement: "Making Change: Minimize
 * number of coins that change hands"
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/8/17 at 3:13 PM
 */
public class MakingChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //int get_dp[2001] = {};
        int[] get_dp = new int[11];
        int[] coin= {5,10,20,50,100,200};
        int i, j, k;
        get_dp[0] = 0;


        for(i = 1; i <= 10; i++) {
            int mi = 0xffff;
            for(j = 0; j < 6; j++) {
                for(k = 1; ; k++) {
                    if(i-coin[j]*k < 0)   break;
                    if(get_dp[i-coin[j]*k]+k < mi)
                        mi = get_dp[i-coin[j]*k]+k;
                }
            }
            get_dp[i] = mi;
        }

        //utilities.MyFunctions.pr
        //MyFunctions.print(get_dp);

        while(true) {
            int[] A = new int[6];
            int sum = 0, a, b;
            String[] amount;
            for(i = 0; i < 6; i++) {
                A[i] = scanner.nextInt();
                sum += A[i] * coin[i];
            }

            if(sum == 0)    break;

            amount = scanner.next().split("\\.");
            a = Integer.parseInt(amount[0]);
            b = Integer.parseInt(amount[1]);
            //scanf("%d.%d", &a, &b);
            a = 100*a + b;


            int[] pay_dp = new int[sum+1];


            // initialize all to max values
            for(i = 0; i <= sum; i++)
                pay_dp[i] = 0xffff;


            pay_dp[0] = 0;

            for(i = 0; i < 6; i++) {
                for(k = sum; k >= 0; k--) {
                    for(j = 1; j <= A[i]; j++) {
                        if(k-coin[i]*j < 0) break;
                        if(pay_dp[k] > pay_dp[k-coin[i]*j]+j)
                            pay_dp[k] = pay_dp[k-coin[i]*j]+j;
                    }
                }
            }
            int ans = 0xffff;
            for(i = a; i <= sum; i++) {
                if(ans <= pay_dp[i])    continue;
                if(ans > pay_dp[i]+get_dp[i-a])
                    ans = pay_dp[i]+get_dp[i-a];
            }
            System.out.printf("%3d\n", ans);
        }
    }
}
