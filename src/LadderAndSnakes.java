import java.util.*;

/**
 * Problem Statement: "Ladder and Snakes"
 *
 * Link : "http://www.geeksforgeeks.org/snake-ladder-problem-2/
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/20/17 at 4:28 PM
 */
public class LadderAndSnakes {

    // size of the board
    public static final int N = 100;

    /**
     * This method returns the array containing the initialized board
     * @param ladders   HashMap for ladders
     * @param snakes    HashMap for snakes
     * @return Returns the array containing the board configuration
     */
    public static int[] getInitializedBoard(HashMap<Integer, Integer> ladders,
                                          HashMap<Integer, Integer> snakes){
        int[] board = new int[N];

        // initialize all the elements with -1
        Arrays.fill(board, -1);

        // fill destination of ladders
        for (int cell: ladders.keySet()){
            board[cell - 1] = ladders.get(cell) - 1;
        }

        // fill the destination for the snakes
        for (int cell: snakes.keySet()){
            board[cell - 1] = snakes.get(cell) - 1;
        }

        return board;
    }



    public static int getMinimumSteps(HashMap<Integer, Integer> ladders,
                                      HashMap<Integer, Integer> snakes){

        // Array showing minimum steps required to reach the ith cell
        int[] steps = new int[N];
        Arrays.fill(steps, Integer.MAX_VALUE);

        // Array to store the board
        int[] board = getInitializedBoard(ladders, snakes);

        // boolean array to show if we have visited that cell
        boolean[] seen = new boolean[N];

        // Queue used to perfromt he BFS
        Queue<Integer> bfsQueue = new LinkedList<>();

        // put the initial position in the queue
        bfsQueue.add(0);

        // set the initial step to 0
        steps[0] = 0;

        // make the oth cell as seen
        seen[0] = true;

        // variable to store
        int currentCell = 0;

        while (!bfsQueue.isEmpty()){
            currentCell = bfsQueue.peek();

            if (currentCell == N -1)
                return steps[currentCell];
                //return steps[currentCell];

            // remove it from the queue
            bfsQueue.poll();

            // look for all the six possible nbrs
            for (int j = 1; j <= 6 && (currentCell+j) < N; j++){

                // make sure you haven't visited the cell before
                if (seen[currentCell + j] == false){
                    seen[currentCell + j] = true;
                    steps[currentCell + j] = steps[currentCell] + 1;

                    if (board[currentCell + j] != -1) {
                        bfsQueue.add(board[currentCell + j]);
                        steps[board[currentCell + j]] = steps[currentCell] + 1;
                    }
                    else {
                        bfsQueue.add(currentCell + j);
                        steps[currentCell + j] = steps[currentCell] + 1;
                    }
                }
            }
//            System.out.println("QUEUE : " + bfsQueue);
//            System.out.println(steps);
        }

        return -1;

        // return getMinimumStepsUtil(ladders, snakes, 1, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        int numberOfLadders, numberOfSnakes;
        HashMap<Integer, Integer> ladders;
        HashMap<Integer, Integer> snakes;
        for (int i = 0; i < numberOfTestCases; i++){
            numberOfLadders = scanner.nextInt();

            // take the ladders info
            ladders = new HashMap<>();
            for (int j = 0; j < numberOfLadders; j++){
                ladders.put(scanner.nextInt(), scanner.nextInt());
            }
            numberOfSnakes = scanner.nextInt();

            // take the snakes info
            snakes = new HashMap<>();
            for (int j = 0; j < numberOfSnakes; j++){
                snakes.put(scanner.nextInt(), scanner.nextInt());
            }

            // get the minimum number of stages to reach the destination
            System.out.println(getMinimumSteps(ladders, snakes));

        }
        scanner.close();
    }
}
