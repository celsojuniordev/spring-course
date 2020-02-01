package com.springcourse.service;

import com.springcourse.domain.Request;
import com.springcourse.enums.RequestState;
import com.springcourse.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
        return result.get();
    }

    public List<Request> findAll() {
        List<Request> result = requestRepository.findAll();
        return result;
    }

    public List<Request> findAllByOwnerId(Long id) {
        List<Request> result = requestRepository.findAllByOwnerId(id);

        return result;
    }
}
