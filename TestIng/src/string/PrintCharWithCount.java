/**
 * 
 */
package string;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author j.yadav
 *
 */
public class PrintCharWithCount {

public String frequency(String input) {

	Map<Character,Integer> map = new TreeMap<>();
	String result = "";
	int size = input.length();
	for(int i=0;i<size;i++) {
		if(map.containsKey(input.charAt(i))){
			map.put(input.charAt(i), map.get(input.charAt(i)) + 1);
		} else {
			map.put(input.charAt(i), 1);
		}
	}
		  Iterator<Character> itr = map.keySet().iterator();
		
		 while(itr.hasNext()) {
			 Character c = itr.next();
			 result = result + c.toString() + map.get(c).toString();
		 }
	
	return result;
}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		PrintCharWithCount printCharWithCount = new PrintCharWithCount();
		String input = s.nextLine();
		while(!input.equals("exit")) {
		System.out.print(printCharWithCount.frequency(input));
		input = s.nextLine();
		}
		s.close();
		
	}
}