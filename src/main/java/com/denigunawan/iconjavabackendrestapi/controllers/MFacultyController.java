package com.denigunawan.iconjavabackendrestapi.controllers;

import com.denigunawan.iconjavabackendrestapi.dtos.MFacultyRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MFacultyResponse;
import com.denigunawan.iconjavabackendrestapi.services.MFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static  com.denigunawan.iconjavabackendrestapi.components.constant.MFacultyConstant.pathRequest;
import static  com.denigunawan.iconjavabackendrestapi.components.constant.MFacultyConstant.pathId;
import static  com.denigunawan.iconjavabackendrestapi.components.constant.MFacultyConstant.pathURL;


@RestController
@RequestMapping(value = pathRequest)
public class MFacultyController {

    @Autowired
    private MFacultyService facultyService;

    @PostMapping
    private MFacultyResponse postDataFaculty(@RequestBody MFacultyRequest request){
        return facultyService.postDataFaculty(request);
    }

    @PutMapping ( value = pathURL)
    public MFacultyResponse updateDataFaculty(@PathVariable(value = pathId) String id, @RequestBody MFacultyRequest request){
        return  facultyService.putDataFaculty(request, id);
    }

    @GetMapping
    public List<MFacultyResponse> listDataFaculty(){
        return  facultyService.listDataFaculty();
    }

    @DeleteMapping( value = pathURL)
    public String deleteDataFacultyByID(@PathVariable(pathId) String id){
        facultyService.deleteFacultyById(id);
        return "Delete Data Successfully";
    }

}
