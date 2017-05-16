import utilities.MyFunctions;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

/**
 * Problem Statement: "Radio Transmitter"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/20/17 at 3:03 PM
 */
public class RadioTransmitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];

        scanner.nextLine();
        String[] inputArrayString = scanner.nextLine().split(" ");
        int i = 0;
        for (String number: inputArrayString){
            a[i] = Integer.parseInt(number);
            i++;

        }
        Arrays.sort(a);

//        MyFunctions.print(a);
//        System.out.println();
        System.out.println(getNumberOfTransmitters(a, k));

        scanner.close();
    }

    public static int getNumberOfTransmitters(int[] a,int k){
        int count = 0;
        int j, l;
        int temp;
        int middleLocation;
        for (int i = 0; i < a.length;){
//            System.out.println("START i : " + i + "->" + a[i]);
            middleLocation = a[i] + k;
            j = i;
            while (j < a.length -1 && a[j+1] <= middleLocation){
                j++;
            }
//            System.out.println("Middle " + a[j]);
            if (j != i){
                // means we have something in the center
                middleLocation = a[j] + k;
                while (j < a.length -1 && a[j+1] <= middleLocation){
                    j++;
                }
//                System.out.println("END " + a[j]);
            }
            i = j+1;
//            if (i < a.length)
//                System.out.println("END i: " + i + "->" + a[i]);
            count++;
        }
        return count;
    }
}
