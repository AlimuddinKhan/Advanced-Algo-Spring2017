import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Problem Statement: "Message Decoding"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */
public class MessageDecoding {

    // this will store the keys of specific upto sepecific length
    public static ArrayList<String> keys = new ArrayList<>();

    // this will store the key string to header character mapping
    public static HashMap<String, Character> keyToHeaderMap =
            new LinkedHashMap<>();

    // change the names and order
    public static boolean hasMessageEnded;

    // current length of the key in the segment
    public static int currentSegmentKeyLength;


    /**
     * Makes sure that the given key is valid that means
     * it has at least one 0 in it
     * @param key
     * @return
     */
    public static boolean isValidKey(char[] key){
        for (char ch: key)
            if (ch == '0')
                return true;

        return false;
    }


    /**
     * Checks if the given ,sg string is a valid
     * encrypted message string
     * with all 1s and 0s
     * @param msg
     * @return
     */
    public static boolean isValidMsg(char[] msg){
        for (char ch : msg){
            if (ch != '0' && ch != '1'){
                return false;
            }
        }

        return true;
    }


    /**
     * This method recursively generates key for the
     * @param length
     * @param key
     */
    public static void createKeysUtil(int length, String key) {
        if (key.length() == length) {
            if (isValidKey(key.toCharArray())) {
                keys.add(key);
            }

            return;
        }

        createKeysUtil(length, key + '0');
        createKeysUtil(length, key + '1');
    }


    /**
     * This method generates keys up to given length
     * @param maxLength
     */
    public static void createKeys(int maxLength) {
        for (int i = 1; i <= maxLength; i++) {
            createKeysUtil(i, "");
        }
    }


    /**
     * This method maps each header character
     * with the key string we generated in the
     * previous steps
     * @param header
     */
    public static void generateKeysToHeaderMapping(char[] header){
        // clear previous mappings
        keyToHeaderMap.clear();
        for (int i = 0; i < header.length; i++){
            keyToHeaderMap.put(keys.get(i), header[i]);
        }
    }


    /**
     * This method decodes the given message
     * @param msg
     * @return
     */
    public static void decodeEncryptedMessage(String msg) {
        String currentSegmentKey;
        int index = 0;
        int length = msg.length();

        //System.out.println("Input MSG : " + msg);
        while (true) {
            if (currentSegmentKeyLength == -1) {
                if (length - index < 3) {
                    break;
                }
                //System.out.println("Substring : "  + msg.substring(index, index+3));
                currentSegmentKeyLength =
                        Integer.parseInt(msg.substring(index, index+3), 2);
                index += 3;

                if (currentSegmentKeyLength == 0) {
                    hasMessageEnded = true;
                    System.out.println();;
                    break;
                }
            } else {
                if (length - index < currentSegmentKeyLength) {
                    break;
                }

                currentSegmentKey = msg.substring
                        (index,index+ currentSegmentKeyLength);

                if (isValidKey(currentSegmentKey.toCharArray())) {
                    System.out.print
                            (keyToHeaderMap.get(currentSegmentKey));
                    index += currentSegmentKeyLength;
                } else {
                    index += currentSegmentKeyLength;
                    currentSegmentKeyLength = -1;
                }

            }
        }

        // return the string part which has not been yet decoded
        //return msg.substring(index);
    }





    /**
     * This method prints the keys we have
     * generated for the
     * given length
     */
    public static void printKeys(){
        for (String key: keys){
            System.out.print(key + " ");
        }
    }


    /**
     * Prints the key to header character mappings
     */
    public static void printKeyToHeaderMapping(){
        for (String key: keyToHeaderMap.keySet()){
            System.out.printf
                    ("%6s:%4c\n", key, keyToHeaderMap.get(key));
        }
    }


    /**
     * This is the main method which takes the input and converts the
     * encoded message into the decoded one
     * @param args
     */
    public static void main(String[] args) {
        createKeys(7);

        Scanner scanner = new Scanner(System.in);
        String header, temp = "",message;
        boolean firstLine = true;

        while (scanner.hasNextLine()){
            //System.out.println("START OUT");
            if (firstLine) {
                header = scanner.nextLine();
                firstLine = false;
            } else{
                header = temp;
            }
            message = "";
            hasMessageEnded = false;
            //System.out.println("HEADER: " + header);

            // create the header to key mapping
            generateKeysToHeaderMapping(header.toCharArray());

            // get the message and
            while (scanner.hasNextLine()){
                //System.out.println("START");
                temp = scanner.nextLine();

                // check if the read ine is valid
                if (!isValidMsg(temp.toCharArray()) ){
                    break;
                }

                // append the message
                message += temp;
                //System.out.println("END");

                // see if it was the last message
                if (hasMessageEnded)
                    break;
            }

            //System.out.println("Generated msg : " + message);
            // decode the generated message above
            decodeEncryptedMessage(message);

            //System.out.println("END OUT");

        }

    }



}
