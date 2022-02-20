package com.example.demo.domain.postIt.category.service;

import com.example.demo.domain.postIt.category.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private CategoryReader categoryReader;
    private CategoryStore categoryStore;

    @Override
    public Category findCategoryWithName(String categoryName) {
        return categoryReader.getCategoryWithName(categoryName);
    }

    @Override
    public Category addNewCategory(Category initCategory) {
        return categoryStore.store(initCategory);
    }

}
