import utilities.MyFunctions;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;

/**
 * Problem Statement: "Closest Difference"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/20/17 at 2:41 PM
 */
public class ClosestDiffernece {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> closestElementsMap
                = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];

        // read the input
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }

        // sort the array
        Arrays.sort(a);

        int minDiff = Integer.MAX_VALUE;
        ArrayList<Integer> currList;
        int count = 0;
        for (int i = 0; i < n - 1; i++){
            if ((a[i+1] - a[i]) <= minDiff){
                minDiff = (a[i+1] - a[i]);
                if (closestElementsMap.containsKey(minDiff)){
                    currList = closestElementsMap.get(minDiff);
                    currList.add(a[i]);
                    currList.add(a[i+1]);
                }else{
                    currList = new ArrayList<>();
                    currList.add(a[i]);
                    currList.add(a[i+1]);
                    closestElementsMap.put(minDiff, currList);
                }
            }
        }
        //System.out.println(closestElementsMap.get(minDiff));

        //MyFunctions.print(a);
        currList = closestElementsMap.get(minDiff);
        int size = currList.size();
        for (int i = 0; i < size -1 ; i++ ){
            System.out.print(currList.get(i) + " ");
        }
        System.out.println(currList.get(size -1));
        scanner.close();
    }
}
