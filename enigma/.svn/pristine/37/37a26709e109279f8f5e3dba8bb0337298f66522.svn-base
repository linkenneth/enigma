package enigma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

import static enigma.Rotor.toIndex;

/** Enigma simulator.
 *  @author Kenneth Lin (cs61b-ga)
 */
public final class Main {

    /** File that describes the mapping for each Rotor and Reflector. */
    public static final File ROTOR_MAP_FILE =
        new File("data/RotorMap.dat");
    /** File that describes the position of the notches for each Rotor. */
    public static final File ROTOR_NOTCH_FILE =
        new File("data/RotorNotch.dat");
    /** Describes the names of each Rotor. */
    public static final String[] ROTOR_NAMES = {
        "I", "II", "III", "IV", "V", "VI", "VII", "VIII"
    };
    /** Describes the names of each Reflector. */
    public static final String[] REFLECTOR_NAMES = {
        "B", "C"
    };
    /** The length of the alphabet. */
    public static final int A_LEN = 26;

    /** The machine used to encode/decode messages. */
    protected static Machine m3;

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) {
        try {
            buildEnigma();
        } catch (FileNotFoundException excp) {
            System.err.printf("Rotor configuration files not found: %s%n",
                              excp.getMessage());
            System.exit(1);
        }

        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));
        boolean isConfigured = false;

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    configure(m3, line);
                    isConfigured = true;
                } else if (isConfigured) {
                    printMessageLine(m3.convert(standardize(line)));
                } else {
                    throw new IOException("first line is not a config line");
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    static boolean isConfigurationLine(String line) {
        return line.startsWith("* ");
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    static void configure(Machine M, String config) throws IOException {
        String[] args = config.split(" ");
        if (args.length != 6) {
            throw new IOException("wrong # of args for config line");
        }
        Reflector ref = Reflector.getReflector(args[1]);
        if (ref == null) {
            throw new IOException("invalid Reflector option");
        }
        Rotor l = Rotor.getRotor(args[2]);
        if (l == null) {
            throw new IOException("invalid left Rotor option");
        }
        Rotor m = Rotor.getRotor(args[3]);
        if (m == null || m == l) {
            throw new IOException("invalid middle Rotor option");
        }
        Rotor r = Rotor.getRotor(args[4]);
        if (r == null || r == l || r == m) {
            throw new IOException("invalid right Rotor option");
        }
        M.setRotors(ref, l, m, r);
        if (!args[5].matches("^[A-Z]{4}$")) {
            throw new IOException("invalid initial position format");
        }
        M.setPositions(args[5]);
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    static String standardize(String line) throws IOException {
        if (!line.matches("^[a-zA-Z \\s]*$")) {
            throw new IOException("input text contains characters other"
                                  + "than letters and blanks");
        }
        return line.replaceAll("\\s", "").toUpperCase(Locale.ENGLISH);
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    static void printMessageLine(String msg) {
        String sub;
        int i;
        for (i = 0; i + 5 < msg.length(); i = i + 5) {
            sub = msg.substring(i, i + 5);
            System.out.printf("%s ", sub);
        }
        System.out.printf("%s\n", msg.substring(i, msg.length()));
    }

    /** Creates all the parts to an Enigma encryption machine. */
    static void buildEnigma() throws FileNotFoundException {
        m3 = new Machine();
        int[] fMap;
        int[] bMap;

        BufferedReader bf =
            new BufferedReader(new FileReader(ROTOR_MAP_FILE));
        BufferedReader nc =
            new BufferedReader(new FileReader(ROTOR_NOTCH_FILE));

        try {

            for (int i = 0; i < ROTOR_NAMES.length + REFLECTOR_NAMES.length;
                 i++) {
                fMap = new int[A_LEN];
                bMap = new int[A_LEN];

                for (int orig = 0; orig < A_LEN; orig++) {
                    int permuted = toIndex((char) bf.read());
                    fMap[orig] = permuted;
                    bMap[permuted] = orig;
                }

                int l = ROTOR_NAMES.length;

                if (i < l) {

                    char[] chArr = nc.readLine().toCharArray();
                    ArrayList<Integer> notch = new ArrayList<Integer>();
                    for (char c : chArr) {
                        notch.add(toIndex(c));
                    }

                    Rotor.allRotors[i] =
                        new Rotor(ROTOR_NAMES[i], fMap, bMap, notch);
                } else {
                    Reflector.allReflectors[i - l] =
                        new Reflector(REFLECTOR_NAMES[i - l],
                                      fMap, bMap);
                }
                bf.skip(1);
            }

        } catch (IOException excp) {
            System.err.printf("ROTOR_MAP_FILE bad format: %s%n",
                              excp.getMessage());
            System.exit(1);
        }

    }

}

