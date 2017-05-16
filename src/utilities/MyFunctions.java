package utilities;

import java.util.ArrayList;

/**
 * Problem Statement: ""
 *
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 * @version on 3/8/17 at 3:37 PM
 */
public class MyFunctions {
    public static void print(int[] a){
        System.out.printf("{ ");
        for (int e : a){
            System.out.printf("%3d ", e);
        }
        System.out.print(" }");
    }

    public static void print(boolean[] a){
        System.out.printf("{ ");
        for (boolean e : a){
            System.out.printf("%3b ", e);
        }
        System.out.print(" }");
    }

    public static void print(int[][] a){
        System.out.println("{");
        for (int i = 0; i < a.length;i++){
            System.out.printf("{ ");
            for (int j = 0; j < a[0].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println("}");
        }
        System.out.println("}");
    }

    public static void print(boolean[][] a){
        System.out.println("{");
        for (int i = 0; i < a.length;i++){
            System.out.printf("{ ");
            for (int j = 0; j < a[0].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println("}");
        }
        System.out.println("}");
    }

    public static void print(char[][] a){
        System.out.println("{");
        for (int i = 0; i < a.length;i++){
            System.out.printf("{ ");
            for (int j = 0; j < a[0].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println("}");
        }
        System.out.println("}");
    }

    public static void print(ArrayList<Integer> a){
        System.out.printf("{ ");
        for (int e : a){
            System.out.printf("%3d ", e);
        }
        System.out.println(" }");
    }

    public static void print(int[][][] a){
        System.out.println("{");
        for (int i = 0; i < a.length;i++){
            System.out.printf("{ ");
            for (int j = 0; j < a[0].length; j++){
                print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println("}");
        }
        System.out.println("}");
    }





    public static void main(String[] args) {
    }
}
