package com.kurungkurawal.autosuggest.core;

/**
 * Created with IntelliJ IDEA.
 * Author: Lee
 * Date: 5/16/14
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AutoSuggestListener {
    public abstract void filter(AutoSuggest c, String s);

    public abstract Object getSelectedObject(Object c);
}
