/**
 *
 */
package string;

/**
 * @author j.yadav
 *
 */
public class PrintReverseOfString {

	public static void main(String[] args) {
		reverse("ABCD");
	}

	static void reverse(String input1) {
		if (input1.length() !=  0) {
			reverse(input1.substring(4));
			System.out.print(input1.charAt(0));
		}

	}
}