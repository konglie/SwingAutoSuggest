package com.kurungkurawal.autosuggest;

import com.kurungkurawal.autosuggest.core.AutoSuggest;
import com.kurungkurawal.autosuggest.core.AutoSuggestListener;
import com.kurungkurawal.autosuggest.core.SimpleKeyValue;
import com.kurungkurawal.autosuggest.db.DB;
import com.kurungkurawal.autosuggest.db.Utils;
import com.kurungkurawal.autosuggest.db.dbList;

import java.util.Map;

/**
 * Created by konglie on 3/19/16.
 */
public class DBListSuggest extends AutoSuggest implements AutoSuggestListener {

    private DB databaseConnection;
    public DBListSuggest(DB database){
        super();

        databaseConnection = database;
        setListener(this);
    }

    // DBListSuggest ini contoh menggunakan database sebagai filter datanya
    // tentu saja, implementasi koneksi ke database bebas menggunakan metode apa saja
    // bahkan, tidak harus ke database.
    @Override
    public void filter(AutoSuggest c, String s) {
        c.removeAllItems();
        String sql = String.format("SELECT nik, nama FROM member WHERE nama ilike '%s%%' LIMIT 20", Utils.pgEscape(s));
        dbList items;
        try {
            // class dbList adalah Map (java.util.Map)
            items = databaseConnection.getList(sql);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        Map<String, String> item;

        // iterasi setiap item dalam map
        // dan tampilkan sebagai item dari autosuggest
        for(int index : items.keySet()){
            item = items.get(index);

            // tampilan data dapat diubah dengan mengubah VALUE dari SimpleKeyValue
            //SimpleKeyValue skv = new SimpleKeyValue(item.get("nik"), item.get("nama"));
            SimpleKeyValue skv = new SimpleKeyValue(item.get("nik"), String.format("NIK %s, NAMA %s", item.get("nik"), item.get("nama")));

            addItem(skv);
        }
    }

    @Override
    public Object getSelectedObject(Object c) {
        return c;
    }
}
