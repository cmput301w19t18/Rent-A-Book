package com.example.cmput301w19t18.rent_a_book;

// Category is the vertical model

import java.util.ArrayList;

public class Category {
    String categoryTitle;
    ArrayList<HorizontalModel> arrayList;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public ArrayList<HorizontalModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<HorizontalModel> arrayList) {
        this.arrayList = arrayList;
    }
}