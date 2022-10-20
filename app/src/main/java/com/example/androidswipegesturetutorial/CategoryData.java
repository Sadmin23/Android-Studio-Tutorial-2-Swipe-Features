package com.example.androidswipegesturetutorial;

public class CategoryData {

    private String CategoryName;

    public CategoryData(String title) {
        this.CategoryName = title;
    }
    public String getCategoryName() {
        return CategoryName;
    }
    public void setCategoryName(String title) {
        this.CategoryName = title;
    }
}
