/**
 *
 */
package string;

import java.util.Scanner;

/**
 * @author j.yadav
 *
 */
public class Atoi {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int result = Atoi.atoi(input.nextLine());
		System.out.println(result);
		input.close();
	}

	static int atoi(String input) {
		char[] inputArray = input.toCharArray();
		int length = inputArray.length;
		int result = 0;
		for (int i = 0; i < length; i++) {
			result = result * 10 + inputArray[i] - '0';
		}

		System.out.println("Value" + Integer.rotateRight(123, 2));
		return result;
	}
}
