package com.example.demo.service;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.response.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostService {

    PostRepository repository;
    UserRepository repositoryUser;
    PostMapper mapper;

    public PostResponse create(PostRequest request) {
        Post post = mapper.toPost(request);
        var context = SecurityContextHolder.getContext().getAuthentication();
        User user = repositoryUser.findByUsername(context.getName()).orElseThrow(()->new AppException(ErrorApp.USER_NOT_FOUND));
        post.setUser(user);
        return mapper.toPostResponse(repository.save(post));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<PostResponse> getAll() {
        List<PostResponse> s = repository.findAll().stream().map(post -> mapper.toPostResponse(post)).toList();
        return s;
    }

//
//    List<PostResponse> getAllPostUser() {
//        return repository.findAll().stream().map(mapper::toPostResponse).toList();
//    }
//
//    PostResponse edit(String idPost) {
//        Post post = repository.findById(idPost).orElseThrow(() -> new AppException(ErrorApp.POST_NOT_FOUND));
//        return mapper.toPostResponse(post);
//    }
//
//    void delete(String idPost) {
//        try {
//            repository.deleteById(idPost);
//        } catch (Exception e) {
//            throw new AppException(ErrorApp.USER_NOT_FOUND);
//        }
//    }
}