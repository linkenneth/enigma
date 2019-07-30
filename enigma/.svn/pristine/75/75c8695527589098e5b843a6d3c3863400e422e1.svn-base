package enigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class MachineTest {

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MachineTest.class));
    }

    @Test public void testConvertSingleLetter() throws IOException {
        Main.buildEnigma();

        Main.configure(Main.m3, "* B III IV I AXLE");
        assertEquals("single letter encoding failed", "H",
                     Main.m3.convert("F"));
        Main.configure(Main.m3, "* B III IV I AXDP");
        assertEquals("single letter encoding failed", "U",
                     Main.m3.convert("G"));
        Main.configure(Main.m3, "* B III IV I AXDQ");
        assertEquals("single letter encoding failed", "M",
                     Main.m3.convert("G"));
    }

    @Test public void testConvertSmallWordNoNotch() throws IOException {
        Main.buildEnigma();

        Main.configure(Main.m3, "* B III IV I AXLE");
        assertEquals("small word encoding failed", "HYIH",
                     Main.m3.convert("FROM"));
        Main.configure(Main.m3, "* B III IV I AXLE");
        assertEquals("small word encoding failed", "FROM",
                     Main.m3.convert("HYIH"));
        Main.configure(Main.m3, "* B III IV I AXLE");
        assertEquals("small word encoding failed", "FAVXPRECT",
                     Main.m3.convert("HOLYFUDGE"));
        Main.configure(Main.m3, "* B III IV I AXLE");
        assertEquals("small word encoding failed", "HOLYFUDGE",
                     Main.m3.convert("FAVXPRECT"));
    }

    @Test public void testConvertWithOneNotch() throws IOException {
        Main.buildEnigma();

        Main.configure(Main.m3, "* B III IV I AUEP");
        assertEquals("small word encoding with notch failed", "IWXY",
                     Main.m3.convert("HAHA"));
        Main.configure(Main.m3, "* B III IV I AUEP");
        assertEquals("small word encoding with notch failed", "HAHA",
                     Main.m3.convert("IWXY"));
    }

    @Test public void testConvertWithDoubleStepping() throws IOException {
        Main.buildEnigma();

        Main.configure(Main.m3, "* B III IV I AUIP");
        assertEquals("small word encoding with notch failed", "RXD",
                     Main.m3.convert("LOL"));
        Main.configure(Main.m3, "* B III IV I AUIP");
        assertEquals("small word encoding with notch failed", "LOL",
                     Main.m3.convert("RXD"));
        Main.configure(Main.m3, "* B V III I AZVM");
        assertEquals("small word encoding with notch failed", "ERROR",
                     Main.m3.convert("VMUZE"));
        Main.configure(Main.m3, "* B V III I AZVM");
        assertEquals("small word encoding with notch failed", "VMUZE",
                     Main.m3.convert("ERROR"));
        Main.configure(Main.m3, "* B I II III AAAA");
        assertEquals("small word encoding with notch failed", "HELLOWORLD",
                     Main.m3.convert("ILBDAAMTAZ"));
        Main.configure(Main.m3, "* B I II III AAAA");
        assertEquals("small word encoding with notch failed", "ILBDAAMTAZ",
                     Main.m3.convert("HELLOWORLD"));
    }

}

