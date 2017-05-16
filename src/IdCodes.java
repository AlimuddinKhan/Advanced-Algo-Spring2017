/**
 * Problem Statement: "ID codes"
 *
 * @author Alimuddin Khan (aak5031@rit.edu)
 */

/**
This is the C++ code I submitted
 #include <iostream>
 #include <algorithm>
 using namespace std;

 int main()
 {
 string input = "";

 // take the input
 cin >> input;

 // keep reading input untill we type the
 while(input.compare("#") != 0){
 // get the Successor for the given input
 bool found =  next_permutation(input.begin(), input.end());
 if(found == true)
 cout << input << endl;
 else
 cout << "No Successor" << endl;
 cin >> input;
 }
 return 0;
 }


 */
public class IdCodes {
    public static void main(String[] args) {

    }
}
