import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Problem Statement: "Short palindrome"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/22/17 at 9:34 AM
 */
public class ShortPalindrome {
    /**
     * Finds the short palindromes in Time Complexity : O(n^4)
     * @param str
     * @return
     */
    public static int getNumberOfShortPalindromes(char[] str){
        long count = 0;
        BigInteger bigCount = new BigInteger("0");

        for (int i = 0; i < str.length - 3; i++){
            for (int j = i+1; j < str.length - 2; j++){
                for (int k = j+1; k < str.length -1; k++){
                    for (int l = k+1; l < str.length; l++){
                        if (str[i] == str[l] && str[j] == str[k]){
                            bigCount= bigCount.add(new BigInteger("1"));
                            count++;
                        }
                    }
                }
            }
        }

        return bigCount.mod(new BigInteger("1000000007")).intValue();
//        return bigCount.intValue();
        // return count;

    }

    public static BigInteger getSubSequenceCount(String s1, String s2)
    {
        // create the character arrays from the given inputs
        char[] string1 = s1.toCharArray();
        char[] string2 = s2.toCharArray();

        // get the length of both the strings
        int l1 = string1.length;
        int l2 = string2.length;

        // string1 dynamic 2D array to store already saved results
        int[][] dp = new int[l1+1][l2+1];


        // Fill lookup[][] in bottom up manner
        for (int i = 0; i < l1 + 1; i++)
        {
            for (int j = 0; j < l2 + 1; j++)

            {
                // if both are empty
                if (i == 0 && j == 0){
                    dp[i][j] = 1;
                }
                else if (i == 0 ){
                    // if first string is empty then there are no possible sub-sequences
                    dp[i][j] = 0;
                }
                else if (j == 0){
                    // if the 2nd string is empty then we have at least one sub-sequence
                    dp[i][j] = 1;
                }
                // if characters are same
                else if (string1[i - 1] == string2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }

                else {
                    // if characters are different
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return new BigInteger(String.valueOf(dp[l1][l2]));
    }

    public static int getDynamicShortPalindromes(String string){
        // set to store unique characters in the given string
        HashSet<Character> set = new HashSet<>();

        // ge the two characters from the given string
        for (char ch : string.toCharArray()){
            set.add(ch);
        }

        char[] characters = new char[set.size()];
        int i = 0;

        // store the 2 characters into the character array
        for (char ch: set){
            characters[i] = ch;
            i++;
        }

        BigInteger count = new BigInteger("0");


        for (int m = 0; m < characters.length - 1; m++){
            for (int n = m +1; n < characters.length; n++){
                String s1 = "" + characters[m] + characters[m] + characters[m] + characters[m];
                String s2 = "" + characters[n] + characters[n] + characters[n] + characters[n];
                String s3 = "" + characters[m] + characters[n] + characters[n] + characters[m];
                String s4 = "" + characters[n] + characters[m] + characters[m] + characters[n];
                count = count.add(getSubSequenceCount(string, s1));
                count = count.add(getSubSequenceCount(string, s2));
                count = count.add(getSubSequenceCount(string, s3));
                count = count.add(getSubSequenceCount(string, s4));
            }
        }


        return count.mod(new BigInteger("1000000007")).intValue();

    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(getDynamicShortPalindromes(str));
        scanner.close();

    }
}
