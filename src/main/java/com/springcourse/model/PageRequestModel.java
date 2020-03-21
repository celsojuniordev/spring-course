package com.springcourse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestModel {

    private int page = 0;
    private int size = 10;

    public PageRequestModel (Map<String, String> params) {
        if (params.containsKey("page")) page = Integer.parseInt(params.get("page"));
        if (params.containsKey("size")) size = Integer.parseInt(params.get("size"));

    }
}
