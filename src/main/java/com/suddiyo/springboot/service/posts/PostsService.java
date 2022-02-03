package com.suddiyo.springboot.service.posts;

import com.suddiyo.springboot.domain.posts.Posts;
import com.suddiyo.springboot.domain.posts.PostsRepository;
import com.suddiyo.springboot.web.dto.PostsResponseDto;
import com.suddiyo.springboot.web.dto.PostsSaveRequestDto;
import com.suddiyo.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }
}
// 쿼리를 날린다는 것??? 뭘 말하는 것? update 부분에서는 쿼리를 안 날린다는데.