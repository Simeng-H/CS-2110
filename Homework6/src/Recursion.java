/**
 * Homework 6 Implement the following methods on recursion as defined in the
 * homework 6 document.
 * 
 * @author sh4aj
 */
public class Recursion {

	/**
	 * tests if a string is a palindrome i.e. reads the same forwards and backwards
	 * 
	 * @param s a string to be tested
	 * @return true if {@code s}is a palindrome
	 */
	public static boolean palindrome(String s) {
		if (s.length() <= 1) {
			return true;
		}
		boolean firstMatchesLast = (s.charAt(0) == s.charAt(s.length() - 1));
		return firstMatchesLast && palindrome(s.substring(1, s.length() - 1));
	}

	/**
	 * returns the reverse of a string
	 * 
	 * @param s a string to be reversed
	 * @return A string which is s reversed
	 */
	public static String reverseString(String s) {
		if (s.length() <= 1) {
			return s;
		}
		String firstLetter = s.substring(0, 1);
		String remainderString = s.substring(1);
		return reverseString(remainderString) + firstLetter;
	}

	/**
	 * returns the minimum number of handshakes needed for {@code n} people such
	 * that eveyone have shaken everyone else's hand, mathematically equivalent to
	 * nC2;
	 * 
	 * @param n the number of people
	 * @return the minimum number of handshakes
	 */
	public static int handshakes(int n) {
		if (n == 0) {
			return 0;
		}
		return n - 1 + handshakes(n - 1);
	}

	/**
	 * returns the ackermann function A(m,n) defined as such: <br>
	 * <p>
	 * A(m.n) = <br>
	 * <ul>
	 * <li>{@code n+1}, if {@code m = 0}</li>
	 * <li>{@code A(m-1,1)}, if {@code m > 0} and {@code n = 0}</li>
	 * <li>{@code A(m-1, A(m,n-1))} if {@code m > 0} and {@code n > 0}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static long ackermann(long m, long n) {
		if (m == 0) {
			return n + 1;
		} else if (m > 0) {
			if (n == 0) {
				return ackermann(m - 1, 1);
			} else if (n > 0) {
				return ackermann(m - 1, ackermann(m, n - 1));
			}
		}
		return 0;
	}

	/**
	 * for main method testing, expecting all true
	 * 
	 */
	public static void main(String[] args) {

		// Test palindrome()
		System.out.println(palindrome(""));
		System.out.println(palindrome("s"));
		System.out.println(!palindrome("si"));
		System.out.println(palindrome("asdfgfdsa"));

		// Test reverseString()
		System.out.println(reverseString("s") == "s");
		System.out.println(reverseString("apple").equals("elppa"));

		// test handshake()
		System.out.println(handshakes(0) == handshakes(1));
		System.out.println(handshakes(4) == 6);

		// test ackermann()
		System.out.println(ackermann(0, 0) == 1l);
		System.out.println(ackermann(3, 4) == 125l);

	}
}
