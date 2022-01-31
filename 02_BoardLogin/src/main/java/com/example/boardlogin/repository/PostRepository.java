
package com.example.boardlogin.repository;

import com.example.boardlogin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //수정일자 기준으로 찾아라 Desc : 역순으로
    List<Post> findAllByOrderByModifiedAtDesc();
}
