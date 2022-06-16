import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("c"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("chenjingze"));
        assertTrue(palindrome.isPalindrome("cattac"));
        assertTrue(palindrome.isPalindrome("chenjingzeezgnijnehc"));
        assertFalse(palindrome.isPalindrome("cattAc"));
        assertFalse(palindrome.isPalindrome("chenjIngzeezgnijnehc"));
    }

    @Test
    public void testisPOffByOne() {
        OffByOne cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("cattAc",cc));
        assertFalse(palindrome.isPalindrome("chenjIngzeezgnijnehc",cc));
        assertTrue(palindrome.isPalindrome("flake",cc));
        assertTrue(palindrome.isPalindrome("HcdI",cc));
    }
}
