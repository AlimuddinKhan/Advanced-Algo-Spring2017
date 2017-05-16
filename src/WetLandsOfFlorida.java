import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem Statement: "Wet Lands of Florida"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class WetLandsOfFlorida {
    // co-ordinate arrays to determine 8 neighbors
    public static int[] xcor = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] ycor = {-1, 0, 1, -1, 1, -1, 0, 1};


    /**
     * This method tells weather the given cell
     * (x,y) is valid to visit to not
     * @param wetLands      matrix showing lands and waters
     * @param alreadyFound  A flag matrix showing
     *                      whether we have visited tht block
     *                      or not
     * @param x             row number of block
     * @param y             column number of block
     * @return      true if it is a valid block else false
     */
    public static boolean isValidBlock(int[][] wetLands,
                                       boolean[][] alreadyFound,
                                       int x, int y){
        return (x >= 0 && x < wetLands.length
                && y >= 0 && y < wetLands[0].length
                && !alreadyFound[x][y] && wetLands[x][y] == 1);

    }


    /**
     * This method calculates the lake size
     * for the given cell (x,y)
     * @param wetLands      matrix showing lands and waters
     * @param alreadyFound  A flag matrix showing
     *                       whether we have visited that
     *                       block or not
     * @param x             row number of block
     * @param y             column number of block
     * @return   total size of the lake
     */
    public static int getLakeSizeUtil(int[][] wetLands,
                                      boolean[][] alreadyFound,
                                      int x, int y){
        if (isValidBlock(wetLands, alreadyFound, x, y)){
            alreadyFound[x][y] = true;
            int n = 1;
            for (int i = 0; i < xcor.length; i++){
                n += getLakeSizeUtil(wetLands, alreadyFound,
                        x + xcor[i], y + ycor[i]);
            }
            return n;

        }else{
            return 0;
        }
    }


    /**
     * This method calculates the lake size for the
     * given cell (x,y)
     * @param wetLands      matrix showing lands and waters
     * @param x             row number of block
     * @param y             column number of block
     * @return   total size of the lake
     */
    public static int getLakeSize(int[][] wetLands, int x, int y){
        boolean[][] alreadyFound = new
                boolean[wetLands.length][wetLands[0].length];
        return getLakeSizeUtil(
                wetLands, alreadyFound, x - 1, y -1);
    }


    /**
     * Returns the binary representation of the wetland input
     * @param wetLandStringMatrix
     * read Array List of string representation rows of
     *                            the wet land
     * @return  Binary representation of the wetland
     */
    public static int[][] getWetLandsBinaryMatrix
            (ArrayList<String> wetLandStringMatrix){
        int[][] binaryWetLands =
                new int[wetLandStringMatrix.size()]
                        [wetLandStringMatrix.get(0).length()];
        char[] currentRowLands;

        for (int i = 0; i < wetLandStringMatrix.size(); i++){
            currentRowLands =
                    wetLandStringMatrix.get(i).toCharArray();

            // for L -> 0 and otherwise it would be 1
            for (int j = 0; j < currentRowLands.length; j++){
                binaryWetLands[i][j] =
                        (currentRowLands[j] == 'L')?0:1;
            }
        }

        return binaryWetLands;
    }

    /**
     * Decides type of operation to be
     * performed depending on the input read
     * @param input
     * @return
     */
    public static int getOperation(String input){
        if (input.matches(""))
            return 0;
        if (input.charAt(0) == 'L' || input.charAt(0) == 'W')
            return 1;

        return 2;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();

        // read the blank line
        String readLine;
        scanner.nextLine();
        scanner.nextLine();
        for (int i = 0; i < numberOfTestCases; i++){
            //System.out.println("Starting a new Test case!");
            // build the matrix;
            ArrayList<String> wetLandStringMatrix = new ArrayList<>();

            // flag to make sure if we have created the binary matrix
            boolean hasMatrixCreated = false;

            // default size
            int[][] wetLands = new int[9][9];
            int caseType = -1;
            while (caseType != 0){
                readLine = scanner.nextLine();
                caseType = getOperation(readLine);

                switch (caseType){
                    case 0:
                        System.out.println();
                        //System.out.println("Test Case ended");
                        break;
                    case 1:
                        //System.out.println("Part of Matrix");
                        wetLandStringMatrix.add(readLine);
                        break;
                    case 2:
                        if (hasMatrixCreated == false){
                            wetLands =
                                    getWetLandsBinaryMatrix
                                            (wetLandStringMatrix);
                            hasMatrixCreated = true;
                            //MyFunctions.print(wetLands);
                        }
                        //System.out.println("Checking lake size");
                        String[] parsedInput =
                                readLine.split(" ");
                        int x = Integer.parseInt(parsedInput[0]);
                        int y = Integer.parseInt(parsedInput[1]);

                        // get the lake size
                        System.out.println(
                                getLakeSize(wetLands, x, y));
                        break;
                }
            }


        }
        //System.out.println();

        scanner.close();



    }

}
