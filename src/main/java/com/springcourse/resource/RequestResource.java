package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestSaveDTO;
import com.springcourse.dto.RequestUpdateDTO;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired
    private RequestService service;

    @Autowired
    private RequestStageService stageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDTO requestSaveDTO) {
        Request request = requestSaveDTO.transformToRequest();
        Request result = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable("id") Long id, @RequestBody @Valid RequestUpdateDTO requestUpdateDTO) {
        Request request = requestUpdateDTO.transformToRequest();
        request.setId(id);
        Request result = service.update(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<Request> pm = service.findAll(pr);
        return ResponseEntity.ok(pm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable("id") Long id) {
        Request result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{requestId}/stages")
    public ResponseEntity<PageModel<RequestStage>> findAllStagesById(
            @PathVariable("requestId") Long requestId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<RequestStage> pm = stageService.findAllByRequestId(requestId, pr);

        return ResponseEntity.ok(pm);
    }
}
