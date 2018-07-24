public class OffByN implements CharacterComparator{

    private int N;

    public OffByN(int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (!Character.isAlphabetic(x) || !Character.isAlphabetic(y)) {
            return false;
        }
        int diff = Math.abs(x - y);
        if (diff == N) {
            return true;
        }
        return false;
    }
}
