package com.denigunawan.iconjavabackendrestapi.services;

import com.denigunawan.iconjavabackendrestapi.dtos.MStudentRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MStudentResponse;

import java.util.List;

public interface MStudentService {

    MStudentResponse postDataStudent(MStudentRequest studentRequest);

    MStudentResponse putDataStudent(MStudentRequest studentRequest, String id);

    List<MStudentResponse> listDataStudent();

    void deleteStudentById(String id);
}
