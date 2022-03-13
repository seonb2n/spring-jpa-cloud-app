package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.user.User;

import java.util.List;

public interface PostItSeriesUpdateFactory {

    List<PostIt> updatePostItList (User user, PostItCommand.UpdatePostIt updatePostIt);

    Category updateCategory(User user, String categoryToken, String categoryName);
}
