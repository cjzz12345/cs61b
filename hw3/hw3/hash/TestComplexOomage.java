package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(5);     a.add(1);     a.add(2);     a.add(3);   a.add(4);
        List<Integer> b = new ArrayList<>();
        b.add(22);     b.add(19);     b.add(17);     b.add(12);   b.add(11);
        List<Integer> c = new ArrayList<>();
        c.add(59);     c.add(19);     c.add(26);     c.add(23);   c.add(43);
        List<Integer> d = new ArrayList<>();
        d.add(15);     d.add(71);     d.add(62);     d.add(31);   d.add(44);
        List<Integer> e = new ArrayList<>();
        e.add(90);     e.add(17);     e.add(99);     e.add(89);   e.add(78);
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            if (i<2000) {
                deadlyList.add(new ComplexOomage(a));
            }
            if (i<4000 && i>=2000) {
                deadlyList.add(new ComplexOomage(b));
            }
            if (i<6000 && i>=4000) {
                deadlyList.add(new ComplexOomage(c));
            }
            if (i<8000 && i>=6000) {
                deadlyList.add(new ComplexOomage(d));
            }
            if (i<10000 && i>=8000) {
                deadlyList.add(new ComplexOomage(e));
            }
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
