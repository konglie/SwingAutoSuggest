package com.kurungkurawal.autosuggest;

import com.kurungkurawal.autosuggest.db.DB;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by konglie on 3/19/16.
 */
public class AutoSuggestDemo extends JFrame {
    public AutoSuggestDemo() {
        setTitle("Swing Auto Suggest");
        setResizable(false);
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildGUI();

        JLabel info = new JLabel("<html><center>" +
                "<br/><p>Visit</p><p>http://www.kurungkurawal.com</p><br/>" +
                "</center></html>");
        info.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(info, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel body;

    private void buildGUI() {
        body = new JPanel(new GridBagLayout());
        body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        body.add(createLabel("Buah-buahan"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        body.add(new SimpleListSuggest(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        body.add(createLabel("From Database"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        body.add(new DBListSuggest(getDatabase()), gbc);

        gbc.weightx = 1;
        gbc.weighty = 1;
        body.add(createLabel(""), gbc);

        getContentPane().add(body, BorderLayout.CENTER);
    }

    private JLabel createLabel(String s) {
        JLabel lbl = new JLabel(s);

        return lbl;
    }

    private DB database = null;

    public DB getDatabase() {
        // inisialisasi class DB (database)
        if (database == null) {
            HashMap<String, String> conf = new HashMap<String, String>();

            // nama database
            conf.put("dbName", "mkkop");

            // username
            conf.put("dbUser", "usermk");

            // password
            conf.put("dbPass", "12345");

            // alamat server
            conf.put("dbHost", "localhost");

            // port server
            conf.put("dbPort", "5432");

            database = new DB(conf);
            database.connectDB();
        }

        return database;
    }
}
