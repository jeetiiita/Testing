/**
 * 
 */
package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;

/**
 * @author j.yadav
 *
 */
public class RemoveAllDuplicateFromString {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		RemoveAllDuplicateFromString rd = new RemoveAllDuplicateFromString();
		while (!input.equals("\n")) {
			String result = rd.removeDuplicateAndMaintainOrder(input);
			System.out.println(result);
			input = scan.nextLine();
		}
	}

	String removeDuplicate(String input) {
		Map<Character, Integer> map = new HashMap<>();
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			map.put(input.charAt(i), 1);
		}
		Iterator<Character> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Character c = itr.next();
			result = result + c.toString();
		}
		return result;
	}
	
	String removeDuplicateAndMaintainOrder(String input){
		// here it is written to maintain the Capital and lower case presence removal 
		String input2 = input.toLowerCase();
		String result ="";
		int[] charArray = new int[256]; 
		for(int i=0;i<input.length();i++){
			Character c = input2.charAt(i);
			Character realC = input.charAt(i);
			if(charArray[c] == 0){
				result = result + realC.toString();
				charArray[c] = 1;
			}
		}
		
		return result;
	}
}
