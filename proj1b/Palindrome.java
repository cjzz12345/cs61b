public class Palindrome {
    public Deque< Character > wordToDeque(String word) {
        Deque< Character > deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindrome(Deque deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        if(deque.removeFirst() == deque.removeLast()) {
           return isPalindrome(deque);
        }else {
            return false;
        }

    }

    public boolean isPalindrome(String word) {
        Deque deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    private boolean isPalindrome(Deque deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        if(cc.equalChars((char) deque.removeFirst(), (char) deque.removeLast())) {
            return isPalindrome(deque,cc);
        }else {
            return false;
        }

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque deque = wordToDeque(word);
        return isPalindrome(deque,cc);
    }
}
