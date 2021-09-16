package com.denigunawan.iconjavabackendrestapi.services;

import com.denigunawan.iconjavabackendrestapi.dtos.MStudyRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MStudyResponse;

import java.util.List;

public interface MStudyService {

    MStudyResponse postDataStudy(MStudyRequest studyRequest);

    MStudyResponse putDataStudy(MStudyRequest studyRequest, String id);

    List<MStudyResponse> listDataStudy();

    void deleteDataStudyById(String id);
}
