package enigma;

/** Class that represents a reflector in the enigma.
 *  @author Kenneth Lin (cs61b-ga)
 */
public class Reflector extends Rotor {

    /** An array of all the possible Reflectors. */
    protected static Reflector[] allReflectors =
        new Reflector[Main.REFLECTOR_NAMES.length];

    /** Constructs a Reflector named NAM at an initial position of
     *  'A'. This Reflector will henceforth have a permanent mappings of
     *  FMAP (forwards mapping) and BMAP (backwards mapping). */
    public Reflector(String nam, int[] fMap, int[] bMap) {
        super(nam, fMap, bMap, null);
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

    /** Reflectors do not advance. */
    @Override
    void advance() {
    }

    /** Returns the Reflector named NAM. */
    public static Reflector getReflector(String nam) {
        for (Reflector r : allReflectors) {
            if (r.name.equals(nam)) {
                return r;
            }
        }
        return null;
    }

}
