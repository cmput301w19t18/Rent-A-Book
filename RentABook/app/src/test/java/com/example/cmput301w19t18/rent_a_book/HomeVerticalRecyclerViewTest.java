package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class HomeVerticalRecyclerViewTest {

    ArrayList<Category> arrayList = new ArrayList<Category>();
    Category testCat1 = new Category();
    Category testCat2 = new Category();


    @Test
    public void getItemCountTest() {
        arrayList.add(testCat1);
        arrayList.add(testCat2);

        String mevar = "me";
        assertEquals(arrayList.size(), 2);

    }
}
