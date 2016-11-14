package com.kurungkurawal.autosuggest.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * Author: Konglie
 */

// autosuggest ini sendiri adalah JComboBox

public class AutoSuggest extends JComboBox {
    private AutoSuggestListener listener = null;
    private Object LockSelectionTo = null;
    private JTextField tf = (JTextField) getEditor().getEditorComponent();
    private JComponent neighbourFocus = null;

    public AutoSuggest() {
        super();
        setDefaultListener();
        init();
    }

    public AutoSuggest(AutoSuggestListener l) {
        super();
        setListener(l);
        init();
    }

    public void setListener(AutoSuggestListener l) {
        this.listener = l;
    }

    public AutoSuggestListener getListener() {
        return this.listener;
    }

    private boolean shouldHide = false;

    private void init() {
        setEditable(true);

        // kuncinya adalah memasang event ketika user mengetik di dalamnya
        // kemudian menjalankan interface AutoSuggestListener
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                int kcode = keyEvent.getKeyCode();
                if (kcode == KeyEvent.VK_UP || kcode == KeyEvent.VK_DOWN) {
                    return;
                }

                if (kcode == KeyEvent.VK_ESCAPE || kcode == KeyEvent.VK_ENTER) {
                    if (kcode == KeyEvent.VK_ENTER) {
                        if (getItemCount() > 0) {
                            try {
                                if (neighbourFocus != null) {
                                    neighbourFocus.grabFocus();
                                    neighbourFocus.requestFocus();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    shouldHide = true;
                    if (defaultValue != null && kcode == KeyEvent.VK_ESCAPE) {
                        setValue(defaultValue);
                    }
                    return;
                }

                shouldHide = false;
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String s = tf.getText();

                        // disinilah proses filter data terjadi
                        // listener ini harus disediakan oleh penyedia data
                        listener.filter(AutoSuggest.this, s);
                        setSuggestModel(s);
                    }
                });
            }
        });
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                tf.selectAll();
            }
        });
    }

    public void setLockSelectionTo(Object o) {
        LockSelectionTo = o;
        if (LockSelectionTo != null) {
            setEditable(false);
            removeAllItems();
            setValue(o);
        } else {
            setEditable(true);
        }
    }

    public void disableSelection(String text) {
        setLockSelectionTo(new SimpleKeyValue("", text));
    }

    private void setSuggestModel(String s) {
        setSelectedIndex(-1);
        hidePopup();
        if (!shouldHide && s.length() > 0 && getItemCount() > 0) {
            showPopup();
        }

        tf.setText(s);
    }

    public Object getValue() {
        if (this.getSelectedIndex() < 0) {
            return "";
        }

        return listener.getSelectedObject(getSelectedItem());
    }

    private Object defaultValue = null;

    public void setValue(Object c) {
        removeAllItems();
        addItem(c);
        setSelectedIndex(0);
        defaultValue = c;
    }

    private void setDefaultListener() {
        setListener(new AutoSuggestListener() {
            @Override
            public void filter(AutoSuggest c, String s) {
            }

            @Override
            public Object getSelectedObject(Object item) {
                return item;
            }
        });
    }

    public void setNeighbourFocus(JComponent c) {
        neighbourFocus = c;
    }
}
