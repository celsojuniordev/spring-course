package com.springcourse.resource;

import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestStageSaveDTO;
import com.springcourse.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "stages")
public class RequestStageResource {

    @Autowired
    private RequestStageService service;

    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDTO requestStageSaveDTO) {
        RequestStage requestStage = requestStageSaveDTO.transformToReqeustStage();
        RequestStage result = service.save(requestStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> findById(@PathVariable("id") Long id) {
        RequestStage result = service.findById(id);
        return ResponseEntity.ok(result);
    }
}
