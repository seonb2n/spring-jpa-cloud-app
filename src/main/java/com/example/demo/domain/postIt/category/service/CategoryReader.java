package com.example.demo.domain.postIt.category.service;

import com.example.demo.domain.postIt.category.Category;

public interface CategoryReader {

    Category getCategoryWithName(String categoryName);

}
