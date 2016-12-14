/**
 * 
 */
package string;

/**
 * @author j.yadav
 *
 */
public class ReverseWordsInString {

	public static void main(String[] args) {
		System.out.println(ReverseWordsInString.reverseWords("This is Clicktable"));
	}

	public static String reverseWords(String input) {

		String result = "";
		
		int size = input.length();
		char[] arr = input.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			String temp = "";
			if (arr[i] != ' ') {
				temp =  ((Character) arr[i]).toString() + temp; 
				result = temp + result;

			} else {
				result = result + " ";
			}
		}

		return result;
	}

}
