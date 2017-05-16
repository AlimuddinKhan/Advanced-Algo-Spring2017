import java.util.*;

/**
 * Problem Statement: "Finding Rectangles"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class FindingRectangles {
    // Time Complexity : O(N^3)
    public static void getRectangles(ArrayList<Point> points){

        Point p1, p2, p3, p4, temp;
        int count = 0;
        int xlarge, xmin, ylarge, ymin;
        Point upper, lower;
        String rectangle;
        HashSet<String> rectangles = new HashSet<>();
        for (int i = 0; i < points.size(); i++){
            p1 = points.get(i);
            for (int j = 0; j < points.size(); j++) {

                if (i != j) {

                    p2 = points.get(j);



                    upper = p1;
                    lower = p2;
                    // if point 2 is above then swap
                    if (p1.y < p2.y){
                        upper = p2;
                        lower = p1;
                    }

                    // take O(n) to gert the index
                    int index1 = points.indexOf(new Point(upper.x, lower.y));
                    int index2 = points.indexOf(new Point(lower.x, upper.y));

                if (index1 != -1 && index2 != -1
                        && index1 != i && index1 != j
                        && index2 != i && index2 != j){
                        count++;

                        p3 = points.get(index1);
                        p4 = points.get(index2);


                    rectangle = getRectangleName(points, p1, p2);

                        rectangles.add(rectangle);
                        //System.out.println(p1.label + p2.label + p3.label + p4.label);
                    }
                }
            }



        }


        if (rectangles.size() == 0){
            System.out.println(" No rectangles");
            return;
        }

        ArrayList<String> sortedRecteangles = new ArrayList<>(rectangles);
        Collections.sort(sortedRecteangles);
        System.out.println();
        for (String r: sortedRecteangles){
            System.out.print(" " + r);
        }
    }

    /**
     * This method returns the rectangle name in a specific order as a string
     * @param points
     * @param p1
     * @param p2
     * @return
     */
    public static String getRectangleName(ArrayList<Point> points,
                                        Point p1, Point p2){
        int xmin, xmax, ymin, ymax;
        xmin = p2.x;
        xmax = p1.x;
        ymin = p2.y;
        ymax = p1.y;
        if (p1.x < p2.x) {
            xmin = p1.x;
            xmax = p2.x;
        }

        if (p1.y < p2.y){
            ymin = p1.y;
            ymax = p2.y;
        }

        Point leftTop = points.get(points.indexOf(new Point(xmin, ymax)));
        Point rightTop = points.get(points.indexOf(new Point(xmax, ymax)));
        Point rightBottom = points.get(points.indexOf(new Point(xmax, ymin)));
        Point leftBottom = points.get(points.indexOf(new Point(xmin, ymin)));

        return leftTop.label + rightTop.label + rightBottom.label + leftBottom.label;


    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String readLine, label;
        String[] parsedLine;
        int x, y;
        ArrayList<Point> points;
        Point currentPoint;
        int k = 1;
        while (n != 0){

            points = new ArrayList<>();
            // read n points
            for (int i = 0; i < n; i++){
                readLine = scanner.nextLine();
                parsedLine = readLine.split(" ");
                label = parsedLine[0];
                x = Integer.parseInt(parsedLine[1]);
                y = Integer.parseInt(parsedLine[2]);
                currentPoint = new Point(label, x, y);
                points.add(currentPoint);

            }

            System.out.printf("Point set " + k +":");
            getRectangles(points);
            n = scanner.nextInt();
            scanner.nextLine();
            k++;
        }
        scanner.close();
    }
}

/**
 * A class to encapsulate the point
 */
class Point{
    String label;
    public int x;
    public int y;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Point(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Compares two Points
     * @param otherPoint
     * @return
     */
    public boolean equals(Object otherPoint) {
        if (otherPoint instanceof Point){
            return ((Point) otherPoint).x == this.x && ((Point) otherPoint).y == this.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "label='" + label + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }


}
