package com.example.demo.domain.postIt.category.service;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.user.User;

public interface CategoryReader {

    Category getCategoryWithName(User user, String categoryName);

}
