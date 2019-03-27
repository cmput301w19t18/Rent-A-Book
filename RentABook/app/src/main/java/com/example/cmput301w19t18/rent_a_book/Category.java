package com.example.cmput301w19t18.rent_a_book;

import java.util.ArrayList;

/**
 * The type Category.
 * Creates Category object to fit in the nested recyclerview (the vertical component)
 */
public class Category {
    /**
     * The Category title.
     */
    String categoryTitle;
    /**
     * The Array list.
     */
    ArrayList<Book> arrayList;

    /**
     * Gets category title.
     *
     * @return the category title
     */
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * Sets category title.
     *
     * @param categoryTitle the category title
     */
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    /**
     * Gets array list.
     *
     * @return the array list
     */
    public ArrayList<Book> getArrayList() {
        return arrayList;
    }

    /**
     * Sets array list.
     *
     * @param arrayList the array list
     */
    public void setArrayList(ArrayList<Book> arrayList) {
        this.arrayList = arrayList;
    }
}