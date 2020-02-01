package com.springcourse.repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void AsaveTest() {
        User owner = new User();
        owner.setId(1L);
        Request request = new Request(null, "Novo notebook", "Pretendo obter um laptop HD", new Date(), RequestState.OPEN, owner, null);

        Request result = requestRepository.save(request);
        assertThat(result.getId()).isEqualTo(5L);
    }

    @Test
    public void updateTeste() {
        User owner = new User();
        owner.setId(1L);
        Request request = new Request(5L, "Novo notebook", "Pretendo obter um laptop HD, de ram 16 GB", null, RequestState.OPEN, owner, null);

        Request result = requestRepository.save(request);
        assertThat(result.getDescription()).isEqualTo("Pretendo obter um laptop HD, de ram 16 GB");
    }

    @Test
    public void getByIdTest() {
        Optional<Request> request = requestRepository.findById(5L);
        Request result = request.get();

        assertThat(result.getSubject()).isEqualTo("Novo notebook");
    }

    @Test
    public void listTest() {
        List<Request> result = requestRepository.findAll();

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void listByOwnerId() {
        List<Request> result = requestRepository.findAllByOwnerId(1L);

        assertThat(result.size()).isEqualTo(4);
    }
}
