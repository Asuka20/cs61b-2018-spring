import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('k', 'p'));
        assertFalse(offByN.equalChars('a', 'a'));

        assertFalse(offByN.equalChars('⑨', 'a'));
        assertFalse(offByN.equalChars('林', 'a'));

    }
}
