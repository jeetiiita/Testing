/**
 * 
 */

/**
 * @author j.yadav
 *
 */
class Groovy {

	def reverse(word) {
		if(word=="")
		return word;
		else {
			subProblem = word[1];
			subSolution = reverse(subProblem);
			solution  = subSolution + word[0];
			return solution;
		}
	}
	
	public static main() {
		
		String stds = reverse("str");
		println(stds);
		
	}

	
		
	
}
