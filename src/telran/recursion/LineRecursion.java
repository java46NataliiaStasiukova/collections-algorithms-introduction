package telran.recursion;

public class LineRecursion {

	public static long factirial(int n) {
		
		if(n == 0) {
			return 1;
		}
		 return n*factirial(n - 1);
	}
	/**
	 * 
	 * @param a either negative or positive
	 * @param b positive
	 * @return a ^ b
	 */
	//TODO
	//no * / allowed
	//no cycles
	//no standard Math methods
	
	//Not working with negative "a".
	public static long pow(int a, int b) {
		if(b == 0) {
			return 1;
		}

		return multiply(a, (int) pow(a, b - 1)) ;
		
	}
	private static long multiply(int a, int b) {
		if(b == 0) {
			return 0;
		} 
		if(a < 0) {
			a = -a;
		}

		return a + multiply(a,b - 1);
	}
	/**
	 * 
	 * @param x
	 * @return x ^ 2
	 */
	public static int square(int x) {
		//TODO
		//no cycles
		//no * / allowed
		//no call any additional function
		//no static fields
		if(x == 0) {
			return 1;
		} else if(x == 1) {
			return x;
		} 
		if(x < 0) {
			x = -x;
		}	
		return x + x -1 + square(x - 1);
		//5^2 = 9 + 7 + 5 + 3 + 1;
	}
	/**
	 * 
	 * @param ar - array integer numbers
	 * @return sum of all numbers
	 */
	public static int sum(int ar[]) {
		//TODO
		//no cycles
		return sum(0, ar);
	}
	private static int sum(int firstIndex, int[] ar) {
		if(firstIndex == ar.length) {
			return 0;
		}
		return ar[firstIndex] + sum(firstIndex + 1, ar);
	}
}
