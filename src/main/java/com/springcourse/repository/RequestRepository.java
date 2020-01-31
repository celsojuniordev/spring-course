package com.springcourse.repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<User> findAllByOwnerId(Long id);

    @Query("UPDATE request SET state = ?2 WHERE id = ?1")
    Request updateStatus(Long id, RequestState requestState);
}