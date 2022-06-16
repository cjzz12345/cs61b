public class OffByN implements CharacterComparator{
    int off;
    public OffByN(int N) {
        off = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == off || y - x == off) {
            return true;
        }
        return false;
    }
}
