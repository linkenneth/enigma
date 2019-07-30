package enigma;

import java.io.IOException;

import static enigma.Rotor.toIndex;
import static enigma.Rotor.toLetter;

/** Class that represents a complete enigma machine.
 *  @author Kenneth Lin (cs61b-ga)
 */
public class Machine {

    /** The reflector used. */
    private static Reflector ref;
    /** The left Rotor. */
    private static Rotor l;
    /** The middle Rotor. */
    private static Rotor m;
    /** The right Rotor. */
    private static Rotor r;

    /** Set my rotors to (from left to right), REFLECTOR, LEFT,
     *  MIDDLE, and RIGHT.  Initially, their positions are all 'A'. */
    void setRotors(Reflector reflector,
                   Rotor left, Rotor middle, Rotor right) {
        ref = reflector;
        l = left;
        m = middle;
        r = right;
    }

    /** Set the positions of my rotors according to SETTING, which
     *  must be a string of 4 upper-case letters. The first letter
     *  refers to the reflector position, and the rest to the rotor
     *  positions, left to right. */
    void setPositions(String setting) throws IOException {
        char[] c = setting.toCharArray();
        ref.setPosition(toIndex(c[0]));
        l.setPosition(toIndex(c[1]));
        m.setPosition(toIndex(c[2]));
        r.setPosition(toIndex(c[3]));
    }

    /** Returns the encoding/decoding of MSG, updating the state of the
     *  rotors accordingly. Note: the advancement of the rotors happens
     *  BEFORE the conversion of each letter. */
    String convert(String msg) {
        char[] msgChArr = msg.toCharArray();
        char[] encoded = new char[msgChArr.length];

        for (int j = 0; j < msgChArr.length; j++) {
            int i = toIndex(msgChArr[j]);

            advanceRotors();

            i = r.convertForward(i);
            i = m.convertForward(i);
            i = l.convertForward(i);
            i = ref.convertForward(i);
            i = l.convertBackward(i);
            i = m.convertBackward(i);
            i = r.convertBackward(i);
            encoded[j] = toLetter(i);
        }

        return String.valueOf(encoded);
    }

    /** Advances all the Rotors depending on conditions. */
    static void advanceRotors() {
        if (m.atNotch()) {
            m.advance();
            l.advance();
        } else if (r.atNotch()) {
            m.advance();
        }
        r.advance();
    }

}
