package com.springcourse.service;

import com.springcourse.domain.RequestStage;
import com.springcourse.enums.RequestState;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStageService {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Autowired
    private RequestRepository requestRepository;
    public RequestStage save(RequestStage requestStage) {
        requestStage.setRealizationDate(new Date());
        RequestStage stage = requestStageRepository.save(requestStage);

        Long requestId = requestStage.getRequest().getId();
        RequestState state = stage.getState();

        requestRepository.updateStatus(requestId, state);
        return stage;
    }

    public RequestStage findById(Long id) {
        Optional<RequestStage> result = requestStageRepository.findById(id);
        return result.get();
    }

    public List<RequestStage> findAllByRequestId(Long requestId) {
        List<RequestStage> result = requestStageRepository.findAllByRequestId(requestId);
        return result;
    }
}
