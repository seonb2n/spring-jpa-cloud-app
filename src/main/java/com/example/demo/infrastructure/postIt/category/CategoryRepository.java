package com.example.demo.infrastructure.postIt.category;

import com.example.demo.domain.postIt.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByCategoryName(String categoryName);

}
