package com.example.demo.infrastructure.postIt.category;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryStoreImpl implements CategoryStore {

    private final CategoryRepository categoryRepository;

    @Override
    public Category store(Category initCategory) {
        return categoryRepository.save(initCategory);
    }
}
