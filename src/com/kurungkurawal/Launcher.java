package com.kurungkurawal;

import com.kurungkurawal.autosuggest.AutoSuggestDemo;

import javax.swing.*;

/**
 * Created by konglie on 3/19/16.
 */
public class Launcher {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e){
                    e.printStackTrace();
                }
                new AutoSuggestDemo();

                // demikian video demo ini,
                // informasi download source code, kunjungi kurungkurawal.com
                // TERIMA KASIH.
            }
        });
    }
}
