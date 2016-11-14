package com.kurungkurawal.autosuggest;

import com.kurungkurawal.autosuggest.core.AutoSuggest;
import com.kurungkurawal.autosuggest.core.AutoSuggestListener;

/**
 * Created by konglie on 3/19/16.
 */
public class SimpleListSuggest extends AutoSuggest implements AutoSuggestListener {
    private String[] fruits;

    public SimpleListSuggest() {
        super();
        fruits = "Jeruk,Pisang,Mangga,Anggur,Pepaya,Apel,Jambu,Manggis,Duku,Durian".split(",");

        setListener(this);
    }

    // simplelist ini hanya menyediakan data statis
    // berdasarkan nama dari data yang telah disediakan
    @Override
    public void filter(AutoSuggest c, String s) {
        c.removeAllItems();
        String fruit;
        for (int i = 0; i < fruits.length; i++) {
            fruit = fruits[i];
            // kita hanya memeriksa apakah kata yang diketik adalah awal kata dari buah
            if (fruit.toString().trim().toLowerCase().startsWith(s.toLowerCase())) {
                c.addItem(fruit);
            }
        }
    }

    @Override
    public Object getSelectedObject(Object c) {
        return c;
    }
}
