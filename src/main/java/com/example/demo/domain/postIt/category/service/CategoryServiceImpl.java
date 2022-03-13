package com.example.demo.domain.postIt.category.service;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.user.User;
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
    public Category findCategoryWithName(User user, String categoryName) {
        return categoryReader.getCategoryWithName(user, categoryName);
    }

    @Override
    public Category addNewCategory(Category initCategory) {
        return categoryStore.store(initCategory);
    }

}
