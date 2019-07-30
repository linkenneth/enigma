package enigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTest {

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MainTest.class));
    }

    @Test public void testBuildEnigmaFMap() throws IOException {

        Main.buildEnigma();

        BufferedReader bf =
            new BufferedReader(new FileReader(Main.ROTOR_MAP_FILE));
        char out;
        char expOut;

        for (Rotor r : Rotor.allRotors) {
            for (int permutation : r.mapForward) {
                out = Rotor.toLetter(permutation);
                expOut = (char) bf.read();
                assertTrue("forward map not equal to config file: Rotor",
                           out == expOut);
            }
            bf.skip(1);
        }

        for (Reflector r : Reflector.allReflectors) {
            for (int permutation : r.mapForward) {
                out = Reflector.toLetter(permutation);
                expOut = (char) bf.read();
                assertTrue("forward map not equal to config file: Reflector",
                           out == expOut);
            }
            bf.skip(1);
        }

    }

    @Test public void testBuildEnigmaFMapBMapInverse() throws IOException {

        Main.buildEnigma();

        for (Rotor r : Rotor.allRotors) {
            for (int i = 0; i < r.mapForward.length; i++) {
                assertTrue("forward map not inverse of backwards map: Rotor",
                           i == r.mapBackward[r.mapForward[i]]);
            }
        }

        for (Reflector r : Reflector.allReflectors) {
            for (int i = 0; i < r.mapForward.length; i++) {
                assertTrue("forward map not inverse of backwards map:"
                           + "Reflector", i == r.mapBackward[r.mapForward[i]]);
            }
        }

    }

    @Test public void testIsConfigurationLine() {
        assertTrue("isConfigurationLine does not detect config line",
                  Main.isConfigurationLine("* B III IV I AXLE"));
        assertTrue("isConfigurationLine does not detect config line",
                  Main.isConfigurationLine("* C VI IV I LOLO"));
        assertTrue("isConfigurationLine does not detect config line",
                  Main.isConfigurationLine("* C VIII I V HELO"));
        assertTrue("isConfigurationLine does detects false config line",
                  !Main.isConfigurationLine("B III IV I AXLE"));
        assertTrue("isConfigurationLine does detects false config line",
                  !Main.isConfigurationLine("YOLO"));
        assertTrue("isConfigurationLine does detects false config line",
                  !Main.isConfigurationLine("OTHER RANDOM TEXT"));
    }

    @Test(expected = IOException.class)
    public void testConfigureArgLengthError() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII I V HELO EXTRA");
    }

    @Test(expected = IOException.class)
    public void testConfigureReflectorError() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* II VIII I V HELO");
    }

    @Test(expected = IOException.class)
    public void testConfigureRotorNotFoundError1() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* B VIIII I V HELO");
    }

    @Test(expected = IOException.class)
    public void testConfigureRotorNotFoundError2() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* B VII O V HELO");
    }

    @Test(expected = IOException.class)
    public void testConfigureSameRotorError1() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C V II V HELO");
    }

    @Test(expected = IOException.class)
    public void testConfigureSameRotorError2() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII I I HELO");
    }

    @Test(expected = IOException.class)
    public void testConfigureBadPositionFormat1() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII V I HEL0");
    }

    @Test(expected = IOException.class)
    public void testConfigureBadPositionFormat2() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII V I HElO");
    }

    @Test(expected = IOException.class)
    public void testConfigureBadPositionFormat3() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII V I AXL");
    }

    @Test(expected = IOException.class)
    public void testConfigureBadPositionFormat4() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII V I A]OP");
    }

    @Test(expected = IOException.class)
    public void testConfigureBadPositionFormat5() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII V I ALOHA");
    }

    @Test public void testConfigureNoError() throws IOException {
        Machine m3 = new Machine();
        Main.configure(m3, "* C VIII V I YOLO");
        Main.configure(m3, "* B VII I II AXLE");
        Main.configure(m3, "* C I II III CSGA");
        Main.configure(m3, "* B III V I ASUC");
        Main.configure(m3, "* C III IV II FIRE");
        Main.configure(m3, "* C VI V I LIFE");
        Main.configure(m3, "* B VII VIII V JKJK");
        Main.configure(m3, "* C V II I HOLA");
        Main.configure(m3, "* B II VIII IV WISE");
    }

    @Test(expected = IOException.class)
    public void testStandardizeErrorBadCharacters1() throws IOException {
        Main.standardize("awori uwior auew0u");
    }

    @Test(expected = IOException.class)
    public void testStandardizeErrorBadCharacters2() throws IOException {
        Main.standardize("awori ;uwior auew u");
    }

    @Test public void testStandardizeBasic() throws IOException {
        String expected = "ONCEUPONATIMETHEREWASAYOUNGBOYWHOCRIEDWOLF";
        String actual = Main.standardize("Once upon a time there was a young"
                                         + "boy who cried wolf");
        assertEquals("standardized testing is an enormous failure",
                     expected, actual);

        expected = "TOBEORNOTTOBETHATISTHEQUESTION";
        actual = Main.standardize("To be or not to be that is the question");
        assertEquals("standardized testing is an enormous failure",
                     expected, actual);

        expected = "WHETHERYOUTHINKYOUCANORTHATYOUCANTYOUAREUSUALLYRIGHT";
        actual = Main.standardize("Whether you think you can or that you cant"
                                  + "you are usually right");
        assertEquals("standardized testing is an enormous failure",
                     expected, actual);
    }

}

