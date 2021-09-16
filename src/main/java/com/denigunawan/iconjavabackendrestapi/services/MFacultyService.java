package com.denigunawan.iconjavabackendrestapi.services;

import com.denigunawan.iconjavabackendrestapi.dtos.MFacultyRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MFacultyResponse;

import java.util.List;

public interface MFacultyService {

    MFacultyResponse postDataFaculty(MFacultyRequest facultyRequest);

    MFacultyResponse putDataFaculty(MFacultyRequest facultyRequest, String id);

    List<MFacultyResponse> listDataFaculty();

    void deleteFacultyById(String id);
}
