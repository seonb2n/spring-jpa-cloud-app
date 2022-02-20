package com.example.demo.infrastructure.postIt.category;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryWithName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName)
                .orElse(categoryRepository.save(new Category(categoryName)));
    }
}
