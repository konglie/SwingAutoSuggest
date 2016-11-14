import com.kurungkurawal.autosuggest.AutoSuggestDemo;
import httpPost.HttpPostDemo;
import printing.JavaWorldPrintExample4;

import javax.swing.*;

/**
 * Created by konglie on 3/19/16.
 */
public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //new HttpPostDemo();

                Double d = 900000000d;
                System.out.println("Application started: ");
                System.out.println(d);
                System.out.println(String.format("%.0f", d));
                System.out.println(String.format("%1$,.2f", d));

                System.out.println("http://kurungkurawal.com");

                //JavaWorldPrintExample4 example = new JavaWorldPrintExample4();
                System.exit(0);
            }
        });
    }
}
