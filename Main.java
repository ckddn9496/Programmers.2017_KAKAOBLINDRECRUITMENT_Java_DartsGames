import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		String dartResult = "1S2D*3T";	//	return 37
//		String dartResult = "1D2S#10S";	//	return 9
//		String dartResult = "1D2S0T";	//	return 3
//		String dartResult = "1S*2T*3S";	//	return 23
//		String dartResult = "1D#2S*3S";	//	return 5
//		String dartResult = "1T2D3D#";	//	return -4
//		String dartResult = "1D2S3T*";	//	return 59
		
		System.out.println(new Solution().solution(dartResult));
	}

}
class Solution {
	  public int solution(String dartResult) {
	      int answer = 0;
	      String numStr = "";
		  Stack<Integer> stack = new Stack<>();
		  for (int i = 0; i < dartResult.length(); i++) {
			  char tok = dartResult.charAt(i);
//			  Character.isDigit(tok); 을 사용했으면 더 좋았다...
			  if ('0' <= tok && tok <= '9') {
				  numStr += tok;
				  continue;
			  }
			  if (!numStr.isEmpty())
				  stack.push(Integer.parseInt(numStr));
			  numStr = "";
			  
			  if (tok == 'S') {
				  continue;
			  } else if (tok == 'D') {
				  stack.push((int)Math.pow(stack.pop(), 2));
			  } else if (tok == 'T') {
				  stack.push((int)Math.pow(stack.pop(), 3));
			  } else if (tok == '*') {
				  if (stack.size() > 1) {
					  int num2 = stack.pop(), num1 = stack.pop();
					  stack.push(num1*2); stack.push(num2*2);
				  } else {
					  int num = stack.pop(); stack.push(num*2);
				  }
			  } else if (tok == '#') {
				  stack.push(stack.pop()*(-1));
			  }
		  }
		  
		  while (!stack.isEmpty()) {
			  answer += stack.pop();
		  }
	      return answer;
	  }
	}