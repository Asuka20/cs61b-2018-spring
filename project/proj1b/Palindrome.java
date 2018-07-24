public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));

        /* Iterative:
        Deque<Character> words = wordToDeque(word);
        while (!words.isEmpty()) {
            if (words.size() == 1) {
                return true;
            }
            if (words.removeFirst() != words.removeLast()) {
                return false;
            }
        }
        return true;
    }
    */
    }

    private boolean isPalindromeHelper(Deque<Character> words) {
        if (words.size() < 2) {
            return true;
        } else if (words.removeFirst() != words.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(words);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> words = wordToDeque(word);
        while (!words.isEmpty()) {
            if (words.size() == 1) {
                return true;
            }
            if (!cc.equalChars(words.removeFirst(), words.removeLast())) {
                return false;
            }
        }
        return true;
    }

}
