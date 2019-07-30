package enigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static enigma.Utils.*;

public class RotorTest {

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(RotorTest.class));
    }

    @Test public void testToLetterAndToIndex() {
        assertEquals("toLetter() failed", 'A', Rotor.toLetter(0));
        assertEquals("toLetter() failed", 'G', Rotor.toLetter(6));
        assertEquals("toLetter() failed", 'Z', Rotor.toLetter(25));
        assertEquals("toIndex() failed", 0, Rotor.toIndex('A'));
        assertEquals("toIndex() failed", 6, Rotor.toIndex('G'));
        assertEquals("toIndex() failed", 25, Rotor.toIndex('Z'));
        assertEquals("toLetter() not inverse of toIndex()",
                     'A', Rotor.toLetter(Rotor.toIndex('A')));
        assertEquals("toLetter() not inverse of toIndex()",
                     0, Rotor.toIndex(Rotor.toLetter(0)));
    }

    @Test public void testGetPositionAndAdvance() {
        ArrayList<Integer> notch = new ArrayList<Integer>();
        notch.add(1);
        Rotor r = new Rotor("I", new int[0], new int[0], notch);
        for (int i = 0; i < Main.A_LEN; i++) {
            r.advance();
            assertEquals("advance() failed", (i + 1) % Main.A_LEN,
                         r.getPosition());
        }
    }

    @Test public void testConvertForwardBasic() throws IOException {

        Main.buildEnigma();

        BufferedReader bf =
            new BufferedReader(new FileReader(Main.ROTOR_MAP_FILE));

        for (Rotor r : Rotor.allRotors) {
            for (int i = 0; i < Main.A_LEN; i++) {
                int a = Rotor.toIndex((char) bf.read());
                assertEquals("convertForward() does not yield correct output",
                             a, r.convertForward(i));
            }
            bf.skip(1);
        }
    }

    @Test public void testConvertBackwardBasic() throws IOException {

        Main.buildEnigma();

        BufferedReader bf =
            new BufferedReader(new FileReader(Main.ROTOR_MAP_FILE));

        for (Rotor r : Rotor.allRotors) {
            for (int i = 0; i < Main.A_LEN; i++) {
                int a = Rotor.toIndex((char) bf.read());
                assertEquals("convertBackward() does not yield correct output",
                             i, r.convertBackward(a));
            }
            bf.skip(1);
        }
    }

    @Test public void testConvertForwardWithOffsetPosition()
        throws FileNotFoundException {

        Main.buildEnigma();
        Rotor r = Rotor.getRotor("I");

        char[] in = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] permuted = "JLEKFCPUYMSNVXGWTROZHAQBID".toCharArray();
        r.setPosition(1);

        for (int i = 0; i < Main.A_LEN; i++) {
            char expected = permuted[i];
            char actual =
                Rotor.toLetter(r.convertForward(Rotor.toIndex(in[i])));
            assertTrue("convertForward() should work with position shift",
                       expected == actual);
        }
    }

    @Test public void testConvertBackwardWithOffsetPosition()
        throws FileNotFoundException {

        Main.buildEnigma();
        Rotor r = Rotor.getRotor("I");

        char[] in = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] permuted = "VXFZCEOUYADBJLSGWRKQHMPNIT".toCharArray();
        r.setPosition(1);

        for (int i = 0; i < Main.A_LEN; i++) {
            char expected = permuted[i];
            char actual =
                Rotor.toLetter(r.convertBackward(Rotor.toIndex(in[i])));
            assertTrue("convertBackward() should work with position shift",
                       expected == actual);
        }
    }

}

