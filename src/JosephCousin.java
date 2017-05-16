import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem Statement: "Joseph’s Cousin"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 4/6/17
 */
public class JosephCousin {
    static ArrayList<Integer> primeNumbers =
            new ArrayList<>(3500);

    /**
     * static initializer to start the prime numbers list :)
     */
    static {
        // 33000 is just a range because maximum
        // number of people is 3500 and
        // 3500th prime number is less than 33000.
        // Found by just changing the range
        boolean[] primeHashes = new boolean[33000];

        primeNumbers.add(2);

        // get the prime numbers in the given
        // range using Sieve of Eratosthenes algorithm
        for (int i = 3; i <  primeHashes.length; i+=2){
            if (primeHashes[i] == false){
                primeNumbers.add(i);
                for (int j = i*2; j < primeHashes.length; j+=i){
                    primeHashes[j] = true;
                }
            }
        }
    }


    /**
     * This method returns the last person
     * alive in the given list of people
     * Time Complexity : O(N)
     * @param n
     * @return
     */
    public static int getLastPersonAlive(int n){
        int alive = 0;
        ArrayList<Integer> people = new ArrayList<>(n);
        //O(n)
        for (int i = 1; i <= n; i++){
            people.add(i);
        }


        int j = 0;
        int currentIndex = -1 ;

        for (int i = 0; i < n - 1; i++){
            currentIndex += primeNumbers.get(i);

            // circularly rotate along the array
            currentIndex = currentIndex%people.size();

            // remove the person at the index got
            people.remove(currentIndex);
            currentIndex -= 1;
        }

        // last and the only person remaining
        return people.get(0);

    }


    /**
     * This is the main method and solves Joseph’s Cousin
     * problem 10015
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // keep taking input until user types 0
        while (n != 0){
            System.out.println(getLastPersonAlive(n));
            n = scanner.nextInt();
        }
        scanner.close();
    }
}





















