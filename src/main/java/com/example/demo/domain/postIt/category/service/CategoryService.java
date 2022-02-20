package com.example.demo.domain.postIt.category.service;

import com.example.demo.domain.postIt.category.Category;

public interface CategoryService {

    Category findCategoryWithName(String categoryName);

    Category addNewCategory(Category initCategory);

}
