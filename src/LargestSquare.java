import java.util.Scanner;

/**
 * Problem Statement: ""
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 4/5/17
 */
public class LargestSquare {
    // n is the a/2 -1 of the square
    public static int[] getXCoOrdinates(int n){
        int index = 0;

        // overall there would 8*n points on the border
        int[] xcor = new int[8*n];

        // STEP 1: TOP ROW (lef to right)
        for (int i = 0; i < 2*n + 1; i++, index++){
            xcor[index] = -n;
        }

        //STEP 2: BOTTOM ROW (left to right)
        for (int i = 0; i < 2*n + 1; i++, index++){
            xcor[index] = n;
        }

        // STEP 3: RIGHT COLUMN (top to bottom)
        for (int i = n-1; i >= 0; i--, index++){
            xcor[index] = - i;
        }

        for (int i = 1; i <= n-1; i++, index++){
            xcor[index] = i;
        }

        // STEP 4: LEFT COLUMN (top to bottom)
        for (int i = n-1; i >= 0; i--, index++){
            xcor[index] = - i;
        }

        for (int i = 1; i <= n-1; i++, index++){
            xcor[index] = i;
        }

        return xcor;
    }


    public static int[] getYCoOrdinates(int n){
        // idex pointer
        int index = 0;

        // y-cordinate array
        int[] ycor = new int[8*n];

        // STEP 1: TOP ROW (lef to right)
        for (int i = n; i >= 0; i--, index++){
            ycor[index] = - i;
        }
        for (int i = 1; i <= n; i++, index++){
            ycor[index] = i;
        }

        //STEP 2: BOTTOM ROW (left to right)
        for (int i = n; i >= 0; i--, index++){
            ycor[index] = - i;
        }
        for (int i = 1; i <= n; i++, index++){
            ycor[index] = i;
        }

        // STEP 3: RIGHT COLUMN (top to bottom)
        for (int i = 0; i < 2*n - 1; i++, index++){
            ycor[index] = n;
        }

        // STEP 4: LEFT COLUMN (top to bottom)
        for (int i = 0; i < 2*n - 1; i++, index++){
            ycor[index] = - n;
        }

        return ycor;

    }

    public static boolean isValidCell(char[][] a, int i, int j, char ch){
        return i >= 0 && i < a.length       // check index bounds row wise
                && j >= 0 && j < a[0].length // check index bounds column wise
                && a[i][j] == ch;           // check the content
    }

    public static boolean isValidSquare(int n, char[][] a, int i, int j){
        int[] xcor = getXCoOrdinates(n);
        int[] ycor = getYCoOrdinates(n);
        char ch = a[i][j];

        int x,y;

        // check all cells for the given square size
        for (int k = 0; k < xcor.length; k++){
            x = i + xcor[k];
            y = j + ycor[k];
            if (!isValidCell(a, x, y, ch)){
                return false;
            }
        }
        return true;
    }

    // pre (i,j) is a valid cell
    public static int getMaxSquare(char[][] a, int i, int j){
        int n = 0;
        while (isValidSquare(n+1, a, i, j)){
            n++;
        }

        return 2*n + 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = Integer.parseInt(scanner.nextLine());

        String line; // read line
        String[] parseLine;
        int m, n, q; // array size and number of test cases
        char[][] a; // array to store the results
        int x,y; // test cell co-ordinates

        for (int i = 0; i < numberOfTestCases; i++){
            line = scanner.nextLine();
            parseLine = line.split(" ");
            m = Integer.parseInt(parseLine[0]);
            n = Integer.parseInt(parseLine[1]);
            q = Integer.parseInt(parseLine[2]);

            // get the array
            a = new char[m][n];
            for (int j = 0; j < m; j++){
                line = scanner.nextLine();
                for (int k = 0; k < n; k++){
                    a[j][k] = line.charAt(k);
                }
            }


            // process each test case
            System.out.println(m + " " + n + " " + q);
            for (int j = 0; j < q; j++){
                line = scanner.nextLine();
                parseLine = line.split(" ");
                x = Integer.parseInt(parseLine[0]);
                y = Integer.parseInt(parseLine[1]);

                // get the max square size
                System.out.println(getMaxSquare(a, x, y));

            }
        }

        scanner.close();
    }
}








































