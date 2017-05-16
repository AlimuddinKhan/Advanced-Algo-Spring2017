import java.util.Scanner;

/**
 * Problem Statement: "BackPacking - 907"
 * Make sure to change class, objects and constructor names to Main
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class BackPacking {

    // a 2D array to store the dynamically calculated results
    static int[][] dynamicStoredTimes;

    // distances to a camp from the previous camps
    static int[] campDistances;

    // number of camps
    int numberOfCamps;

    // number of nights
    int numberOfNights;


    static {
        // since maximum camps are 600 and maximum nights are 300
        dynamicStoredTimes = new int[601][301];

        // since maximum camps are 600
        campDistances = new int[601];
    }
    /**
     * A constructor to initialize all stufss
     * @param numberOfCamps
     * @param numberOfNights
     */
    public BackPacking(int numberOfCamps, int numberOfNights) {
        this.numberOfCamps = numberOfCamps;
        this.numberOfNights = numberOfNights;
//        this.campDistances = new int[this.numberOfCamps + 1];
//        this.dynamicStoredTimes = new int[this.numberOfCamps + 1][this.numberOfNights + 1];

        // initialize the dynamic array to minimum possible value
        for (int i = 0; i <= this.numberOfCamps; i++){
            for (int j = 0; j <= this.numberOfNights; j++){
                dynamicStoredTimes[i][j] = Integer.MIN_VALUE;
            }
        }

        //scanner.close();
    }


    public int getMinimumWalkingCostUtil(int nightsRemainingSoFar, int currentCampSite)
    {
        // BASE CASE 1: We have reached the end of the camp and no more distance to cover
        if (currentCampSite == this.numberOfCamps + 1)
            return 0;

        // We do not have any nights remaining
        if (nightsRemainingSoFar == 0)
        {
            int remainingDistances = 0;
            for (int i = currentCampSite; i <= this.numberOfCamps; i++) {
                remainingDistances += campDistances[i];
            }
            return remainingDistances;
        }

        // we still have nights and camps to cover
        int minimumWalkingDistanceSofar = dynamicStoredTimes[currentCampSite][nightsRemainingSoFar];


        // we are calculating this combination for the first time
        if (minimumWalkingDistanceSofar < 0)
        {
            minimumWalkingDistanceSofar = Integer.MAX_VALUE;
            int distance = campDistances[currentCampSite];
            for (int i = currentCampSite + 1; minimumWalkingDistanceSofar > distance
                    && i <= this.numberOfCamps  ;  i++)
            {
                minimumWalkingDistanceSofar = Math.min(minimumWalkingDistanceSofar,
                        Math.max(distance, getMinimumWalkingCostUtil(nightsRemainingSoFar - 1, i)));

                distance += campDistances[i];
            }
        }

        //System.out.println(minimumWalkingDistanceSofar);
        return minimumWalkingDistanceSofar;
    }


    public int getMinimumWalkingCost(){
        return getMinimumWalkingCostUtil(this.numberOfNights, 0);
    }
/**
4 3
7
2
6
4
5
4 3
7
2
6
4
5

**/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BackPacking backPacking;
        String line;
        String[] parsedLine;
        int numberOfNights, numberOfCamps;
        while (!(line = scanner.nextLine()).matches("")){
            //System.out.println("HERE!!");
            //line = scanner.nextLine();
            parsedLine = line.split(" ");
//            if (line.matches("") || parsedLine.length != 2){
//                System.out.println("Ending !!!!");
//                break;
//            }

            numberOfCamps = Integer.parseInt(parsedLine[0]);
            numberOfNights = Integer.parseInt(parsedLine[1]);
            //if (String line = scanner.next)
//            numberOfCamps = scanner.nextInt();
//            numberOfNights = scanner.nextInt();

            // start the new Object for the given configuration
            backPacking = new BackPacking(numberOfCamps, numberOfNights);

            // read the camp distances

            // read the distances to camps from user
            for (int i = 0; i <= backPacking.numberOfCamps; i++){
                backPacking.campDistances[i] = Integer.parseInt(scanner.nextLine());
            }
            System.out.println(backPacking.getMinimumWalkingCost());
            //System.out.println("ENDED HERE!!");
        }

        scanner.close();
    }


}
