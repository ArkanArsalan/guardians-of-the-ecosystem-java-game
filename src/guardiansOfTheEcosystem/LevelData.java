package guardiansOfTheEcosystem;

import java.io.*;
import java.util.logging.Level;

public class LevelData {

    public static String LEVEL_NUMBER = "1";
    public static String[][] LEVEL_CONTENT = {{"SawMan"}, {"SawMan", "Sawman"}};
    public static int[][][] LEVEL_VALUE = {{{0, 99}}, {{0, 49}, {50, 99}}};

    public LevelData() {
        try {
            File f = new File("LEVEL_CONTENT.vbhv");

            if (!f.exists()) {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
                bwr.write("1");
                bwr.close();
                LEVEL_NUMBER = "1";
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                LEVEL_NUMBER = br.readLine();
            }
        } catch (Exception ex) {
            // Handle exceptions appropriately
        }
    }

    public static void write(String lvl) {
        File f = new File("LEVEL_CONTENT.vbhv");
        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
            bwr.write(lvl);
            bwr.close();
            LEVEL_NUMBER = lvl;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LevelData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
