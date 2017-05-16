import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem Statement: ""
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 4/7/17
 */
public class ChocolateChips {
    public static ArrayList<Point> chips = new ArrayList<>();
    public static final double DIAMETER = 6;

    /**
     * Returns number of chips inside the circle with the given center
     * @param center
     * @return
     */
    public static int pointsInsideCircle(Point center){
        int n = 0;
        for (Point chip : chips){
            // see if chip is inside the circle
            if (center.getDistance(chip) <= DIAMETER/2)
                n++;
        }
        return n;
    }
/*
1

4.0 4.0
4.0 5.0
5.0 6.0
1.0 20.0
1.0 21.0
1.0 22.0
1.0 25.0
1.0 26.0
*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = Integer.parseInt(scanner.nextLine());
        String line;
        String[] parsedLine;
        double x, y;
        scanner.nextLine();
        Point p;
        Point c;

        for (int i = 0; i < numberOfTestCases; i++){
            // read and empty line

            ;
            chips.clear();
            while (!(line = scanner.nextLine()).equals("")) {
                parsedLine = line.split(" ");
                x = Double.parseDouble(parsedLine[0]);
                y = Double.parseDouble(parsedLine[1]);
                p = new Point(x, y);
                System.out.println(p);
                chips.add(p);
            }
            System.out.println("TOTAL CHIPS : " + chips.size());
            System.out.println(chips);

            int count = 0;
            int insideChips = 0;
            for (float m = 0; m <= 50; m+=0.1 ){
                for (float n = 0; n <= 50; n+=0.1 ){
                    c = new Point(m,n);
//                    System.out.println("center : " + c);
                    insideChips = pointsInsideCircle(c);
//                    System.out.println("######## inside : " + insideChips);
                    if (insideChips > count)
                        count = insideChips;

                }
            }
            System.out.println("COUNT : " + count);


        }


        scanner.close();
    }

}

class Point{
    double x,y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String point = String.format("Point(%.1f,%.1f)",x,y);

        return point;
    }

    public double getDistance(Point otherPoint){
        double distance = 0;
        distance = Math.sqrt(Math.pow((this.x - otherPoint.x), 2)
                + Math.pow((this.y - otherPoint.y), 2));
        return distance;
    }
}
