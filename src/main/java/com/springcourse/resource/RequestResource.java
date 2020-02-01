package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired
    private RequestService service;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody Request request) {
        Request result = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Request>> findAll() {
        List<Request> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<List<Request>> findAllByOwnerId(@PathVariable("ownerId") Long ownerId) {
        List<Request> result = service.findAllByOwnerId(ownerId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@RequestBody Request request) {
        Request result = service.update(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable("id") Long id) {
        Request result = service.findById(id);
        return ResponseEntity.ok(result);
    }
}
