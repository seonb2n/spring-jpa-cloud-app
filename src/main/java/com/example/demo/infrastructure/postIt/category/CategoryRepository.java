package com.example.demo.infrastructure.postIt.category;

import com.example.demo.domain.postIt.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByCategoryNameAndUserToken(String categoryName, String userToken);

}
