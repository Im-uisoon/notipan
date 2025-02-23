package com.project.backend.Repository;

import com.project.backend.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findByBoardOrderByIdDesc(String board, Pageable pageable);
    List<PostEntity> findTop3ByOrderByIdDesc();
}
