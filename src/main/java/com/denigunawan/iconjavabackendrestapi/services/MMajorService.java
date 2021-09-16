package com.denigunawan.iconjavabackendrestapi.services;

import com.denigunawan.iconjavabackendrestapi.dtos.MMajorRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MMajorResponse;

import java.util.List;

public interface MMajorService {

    MMajorResponse postDataMajor(MMajorRequest majorRequest);

    MMajorResponse putDataMajor(MMajorRequest majorRequest, String id);

    List<MMajorResponse> listDataMajor();

    void deleteDataMajorById(String id);


}
