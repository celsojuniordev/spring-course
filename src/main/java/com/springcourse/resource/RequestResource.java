package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestFile;
import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestSaveDTO;
import com.springcourse.dto.RequestUpdateDTO;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestFileService;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired
    private RequestService service;

    @Autowired
    private RequestStageService stageService;

    @Autowired
    private RequestFileService fileService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDTO requestSaveDTO) {
        Request request = requestSaveDTO.transformToRequest();
        Request result = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PreAuthorize("@accessManager.isRequestOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable("id") Long id, @RequestBody @Valid RequestUpdateDTO requestUpdateDTO) {
        Request request = requestUpdateDTO.transformToRequest();
        request.setId(id);
        Request result = service.update(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> findAll(
            @RequestParam Map<String, String> params) {
        PageRequestModel pr = new PageRequestModel(params);
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
            @RequestParam Map<String, String> params) {
        PageRequestModel pr = new PageRequestModel(params);
        PageModel<RequestStage> pm = stageService.findAllByRequestId(requestId, pr);

        return ResponseEntity.ok(pm);
    }

    @GetMapping("/{requestId}/files")
    public ResponseEntity<PageModel<RequestFile>> findAllFilesById(
            @PathVariable("requestId") Long requestId,
            @RequestParam Map<String, String> params) {
        PageRequestModel pr = new PageRequestModel(params);
        PageModel<RequestFile> pm = fileService.listAllByRequestId(requestId, pr);

        return ResponseEntity.ok(pm);
    }

    @PostMapping("/{requestId}/files")
    public ResponseEntity<List<RequestFile>> upload(
            @PathVariable("requestId") Long requestId,
            @RequestParam(value = "files")MultipartFile[] files) {
        List<RequestFile> requestFiles = fileService.upload(requestId, files);

        return ResponseEntity.status(HttpStatus.CREATED).body(requestFiles);
    }
}
