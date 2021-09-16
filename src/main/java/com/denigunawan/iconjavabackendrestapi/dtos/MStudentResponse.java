package com.denigunawan.iconjavabackendrestapi.dtos;

import lombok.Data;

@Data
public class MStudentResponse {

    private String id;
    private String name;
    private String address;
    private MFacultyResponse faculty;
    private MMajorResponse major;
    private MStudyResponse study;
}
