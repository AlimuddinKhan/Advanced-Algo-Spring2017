import java.math.BigInteger;
import java.util.Scanner;

/**
 * Problem Statement: "Numerically Speaking"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class NumericallySpeaking {

    // static constants to be used in the program
    final static BigInteger BASE = new BigInteger("26");
    final static char[] letters =
            {'a','b','c','d','e','f','g','h','i','j','k',
    'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    /**
     * Maps the numbers to characters
     * @param n Given bigger number
     * @return Base 26 character representation of the number
     */
    public static String mapNumberToCharacters(BigInteger n){
        StringBuilder num = new StringBuilder();
        int  r;

        while (n.compareTo(new BigInteger("0"))> 0){
            r = n.mod(BASE).intValue();
            n = n.divide(BASE);
            num.insert(0, letters[r - 1]);
        }
        return num.toString();
    }

    /**
     * Maps the characters to the number
     * @param characters
     * @return
     */
    public static String mapCharactersToNumber
                                    (String characters){
        int l = characters.length();
        char[] ch = characters.toCharArray();
        BigInteger n = new BigInteger("0");
        int indexValue;

        for (int  i =0; i < l; i++){
            indexValue = ch[i] - 'a' + 1;
            n = n.add(BASE.pow(l - i - 1).
                    multiply(new BigInteger
                            (String.valueOf(indexValue))));
        }

        return formatNumber(n.toString());
    }


    /**
     * Formats the number 12444555 in 12,444,555
     * @param number
     * @return Formatted number
     */
    public static String formatNumber(String number){
        StringBuilder sb = new StringBuilder(number);
        int l = number.length();
        for (int i = l - 3; i > 0; i-= 3){
            sb.insert(i, ",");
        }
        return sb.toString();
    }


    /**
     * Returns the operation to be performed
     * depending on the input provided
     * @param string
     * @return
     */
    public static int getOperationType(String string){
        if ( Character.isDigit(string.charAt(0)) )
            return 0;
        return (string.matches("\\*"))?2:1;
    }

/**
 * Example test Case:
29697684282993
transcendental
28011622636823854456520
computationally
zzzzzzzzzzzzzzzzzzzz
*
**/
    /**
     * This is the main  method
     * @param args null
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";



        while (!input.matches("\\*")){
            input = scanner.nextLine();

            // get the case
            int caseNumber = getOperationType(input);

            switch (caseNumber){
                case 0:
                    //System.out.println("Number");
                    System.out.printf("%-22s%s\n",
                            mapNumberToCharacters(
                                    new BigInteger(input)),
                            formatNumber(input));
                    break;
                case 1:
                    System.out.printf("%-22s%s\n", input,
                            mapCharactersToNumber(input));
                    break;
                case 2:
                    break;
                default:
                    break;

            }

        }

        scanner.close();

    }
}
