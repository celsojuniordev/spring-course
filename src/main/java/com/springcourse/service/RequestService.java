package com.springcourse.service;

import com.springcourse.domain.Request;
import com.springcourse.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request save(Request request) {
        request.setState(RequestState.OPEN);
        request.setCreationDate(new Date());

        return requestRepository.save(request);
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }

    public Request findById(Long id) {

        Optional<Request> result = requestRepository.findById(id);

        return result.orElseThrow(() -> new NotFoundException("There are not request with id -> " + id));
    }

    public PageModel<Request> findAll(PageRequestModel pr) {

        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
        Page<Request> page = requestRepository.findAll(pageable);

        return new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    }

    public PageModel<Request> listAllByOwnerIdOnLazyModel(Long id, PageRequestModel pr) {

        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
        Page<Request> page = requestRepository.findAllByOwnerId(id, pageable);

        return new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    }
}