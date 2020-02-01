package com.springcourse.repository;

import com.springcourse.domain.Request;
import com.springcourse.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByOwnerId(Long id);

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE request SET state = ?2 WHERE id = ?1")
    int updateStatus(Long id, RequestState requestState);
}