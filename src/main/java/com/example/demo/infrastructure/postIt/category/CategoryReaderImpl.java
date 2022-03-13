package com.example.demo.infrastructure.postIt.category;

import com.example.demo.common.exception.EntityNotFoundException;
import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryReader;
import com.example.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryWithName(User user, String categoryName) {
        return categoryRepository.findCategoryByCategoryNameAndUserToken(categoryName, user.getUserToken())
                .orElseThrow(EntityNotFoundException::new);
    }
}
