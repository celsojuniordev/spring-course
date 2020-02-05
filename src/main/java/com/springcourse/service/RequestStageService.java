package com.springcourse.service;

import com.springcourse.domain.RequestStage;
import com.springcourse.enums.RequestState;
import com.springcourse.esception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return result.orElseThrow(() -> new NotFoundException("There are not stage with id -> " + id));
    }

    public PageModel<RequestStage> findAllByRequestId(Long requestId, PageRequestModel pr) {
        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
        Page<RequestStage> page = requestStageRepository.findAllByRequestId(requestId, pageable);

        return new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    }
}
