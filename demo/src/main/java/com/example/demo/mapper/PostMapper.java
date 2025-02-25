package com.example.demo.mapper;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.response.PostResponse;
import com.example.demo.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "user",ignore = true)
    Post toPost(PostRequest request);

    PostResponse toPostResponse(Post post);


}
