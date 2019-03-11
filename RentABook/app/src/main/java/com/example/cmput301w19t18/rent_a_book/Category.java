package com.example.cmput301w19t18.rent_a_book;

// Category is the vertical model

import java.util.ArrayList;

/**
 * The type Category.
 */
public class Category {
    /**
     * The Category title.
     */
    String categoryTitle;
    /**
     * The Array list.
     */
    ArrayList<HorizontalModel> arrayList;

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
    public ArrayList<HorizontalModel> getArrayList() {
        return arrayList;
    }

    /**
     * Sets array list.
     *
     * @param arrayList the array list
     */
    public void setArrayList(ArrayList<HorizontalModel> arrayList) {
        this.arrayList = arrayList;
    }
}