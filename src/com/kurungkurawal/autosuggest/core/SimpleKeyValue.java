package com.kurungkurawal.autosuggest.core;

/**
 * Created by Konglie on 10/14/2014.
 */
public class SimpleKeyValue {
    public final String KEY, VALUE;
    public SimpleKeyValue(String key, String value){
        this.KEY = key;
        this.VALUE = value;
    }

    public String toString(){
        return VALUE;
    }
}
