package com.example.demo.infrastructure.postIt;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryReader;
import com.example.demo.domain.postIt.category.service.CategoryStore;
import com.example.demo.domain.postIt.service.PostItReader;
import com.example.demo.domain.postIt.service.PostItSeriesUpdateFactory;
import com.example.demo.domain.postIt.service.PostItStore;
import com.example.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostItSeriesUpdateFactoryImpl implements PostItSeriesUpdateFactory {
    private final CategoryReader categoryReader;
    private final CategoryStore categoryStore;
    private final PostItReader postItReader;
    private final PostItStore postItStore;

    @Override
    public List<PostIt> updatePostItList(User user, PostItCommand.UpdatePostIt updatePostIt) {
        return updatePostIt.getUpdatePostItUnitList().stream().map(updatePostItUnit -> {
            //새로운 포스트잇인 경우
            if (updatePostItUnit.getPostItToken().equals("") || updatePostItUnit.getPostItToken() == null) {
                Category category = updateCategory(user, updatePostItUnit.getCategoryToken(), updatePostItUnit.getCategoryName());
                PostIt initPostIt = updatePostItUnit.toEntity(user, category);
                postItStore.store(initPostIt);
                categoryStore.store(category);
                return initPostIt;
            }
            //이미 존재하는 포스트잇의 경우
            else {
                PostIt postIt = postItReader.getPostIt(updatePostItUnit.getPostItToken());
                Category category = updateCategory(user, updatePostItUnit.getCategoryToken(), updatePostItUnit.getCategoryName());
                postIt.updatePostIt(category, updatePostItUnit);
                postItStore.store(postIt);
                categoryStore.store(category);
                return postIt;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Category updateCategory(User user, String categoryToken, String categoryName) {
        if(categoryToken.equals("")) {
            Category initCategory = Category.builder()
                    .categoryName(categoryName)
                    .user(user)
                    .build();
            return categoryStore.store(initCategory);
        } else {
            Category category = categoryReader.getCategoryWithName(user, categoryName);
            category.updateCategory(categoryName);
            return category;
        }
    }

}
