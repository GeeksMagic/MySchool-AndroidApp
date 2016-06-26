package com.gmt.myschool.ui.wizardpager.model;

import java.util.ArrayList;

/**
 * Represents a list of wizard pages.
 */
public class PageList extends ArrayList<Page> implements PageTreeNode {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2829971774651385639L;

	public PageList(Page... pages) {
        for (Page page : pages) {
            add(page);
        }
    }

    @Override
    public Page findByKey(String key) {
        for (Page childPage : this) {
            Page found = childPage.findByKey(key);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public void flattenCurrentPageSequence(ArrayList<Page> dest) {
        for (Page childPage : this) {
            childPage.flattenCurrentPageSequence(dest);
        }
    }
}
