package com.example.demo.domain.postIt.category.service;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.user.User;

public interface CategoryService {

    Category findCategoryWithName(User user, String categoryName);

    Category addNewCategory(Category initCategory);

}
