package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QnARepository extends JpaRepository<QnA, Long> {
    Optional<List<QnA>> findByProductGroupId(Long productGroupId);
}
