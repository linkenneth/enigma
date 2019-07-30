package enigma;

import java.util.List;

import static enigma.Utils.*;

/** Class that represents a rotor in the enigma machine.
 *  @author Kenneth Lin (cs61b-ga)
 */
public class Rotor {

    /** My current position (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int position;
    /** My designated name. */
    protected String name;
    /** The notch position(s) on this Rotor. */
    private List<Integer> notch;

    /* Both a forwards mapping and a backwards mapping is used to
     * significantly improve the speed of permuting backwards. */

    /** A forwards mapping of my permutations at the 'A' position. */
    protected int[] mapForward;
    /** A backwards mapping of my permutations at the 'A' position. */
    protected int[] mapBackward;

    /** An array of all the possible Rotors. */
    protected static Rotor[] allRotors = new Rotor[Main.ROTOR_NAMES.length];

    /** Constructs a Rotor named NAM at an initial position of 'A'. This
     *  Rotor will henceforth have a permanent mappings of FMAP (forwards
     *  mapping) and BMAP (backwards mapping). This Rotor also has a notch
     *  at NOTC. */
    public Rotor(String nam, int[] fMap, int[] bMap, List<Integer> notc) {
        name = nam;
        setPosition(0);
        mapForward = fMap;
        mapBackward = bMap;
        notch = notc;
    }

    /** Returns the Rotor named NAM. */
    public static Rotor getRotor(String nam) {
        for (Rotor r : allRotors) {
            if (r.name.equals(nam)) {
                return r;
            }
        }
        return null;
    }

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return (int) (c - 'A');
    }

    /** Return my current rotational position as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getPosition() {
        return position;
    }

    /** Set getPosition() to POSN.  */
    void setPosition(int posn) {
        position = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        return cycAdd(mapForward[cycAdd(p, position)], -position);
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        return cycAdd(mapBackward[cycAdd(e, position)], -position);
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        return notch.contains(position);
    }

    /** Advance me one position. */
    void advance() {
        setPosition(cycAdd(position, 1));
    }

}

