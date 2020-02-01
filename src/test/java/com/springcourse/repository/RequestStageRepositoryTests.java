package com.springcourse.repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
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
public class RequestStageRepositoryTests {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Test
    public void AsaveTest() {
        User owner = new User();
        Request request = new Request();
        request.setId(2L);
        owner.setId(1L);
        RequestStage requestStage = new RequestStage(null, "Comprou novo notebook HD", new Date(), RequestState.OPEN, request, owner);
        RequestStage result = requestStageRepository.save(requestStage);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    public void getByIdTest() {
        Optional<RequestStage> requestStage = requestStageRepository.findById(1L);
        RequestStage result = requestStage.get();

        assertThat(result.getDescription()).isEqualTo("Comprou novo notebook HD");
    }

    @Test
    public void listByRequestIdTest() {
        List<RequestStage> result = requestStageRepository.findAllByRequestId(2L);

        assertThat(result.size()).isEqualTo(1);
    }
}
