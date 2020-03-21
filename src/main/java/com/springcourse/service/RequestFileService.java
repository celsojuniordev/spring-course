package com.springcourse.service;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestFile;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.model.UploadedFileModule;
import com.springcourse.repository.RequestFileRepository;
import com.springcourse.service.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class RequestFileService {

    @Autowired
    private RequestFileRepository requestFileRepository;

    @Autowired
    private S3Service s3Service;

    public List<RequestFile> upload(Long requestId, MultipartFile[] files) {
        List<UploadedFileModule> uploadedFiles = s3Service.upload(files);
        List<RequestFile> requestFiles = new ArrayList<>();
        uploadedFiles.forEach( uploadedFile -> {
            RequestFile file = new RequestFile();
            file.setName(uploadedFile.getName());
            file.setLocation(uploadedFile.getLocation());

            Request request = new Request();
            request.setId(requestId);

            file.setRequest(request);

            requestFiles.add(file);
        });
        return requestFileRepository.saveAll(requestFiles);
    }

    public PageModel<RequestFile> listAllByRequestId(Long id, PageRequestModel prm) {
        Pageable pageable = prm.toSpringPageRequest();
        Page<RequestFile> page = requestFileRepository.findAllByRequestId(id, pageable);

        return new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    }
}
