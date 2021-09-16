package com.denigunawan.iconjavabackendrestapi.controllers;

import com.denigunawan.iconjavabackendrestapi.dtos.MStudentRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MStudentResponse;
import com.denigunawan.iconjavabackendrestapi.services.MStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.denigunawan.iconjavabackendrestapi.components.constant.MStudentConstant.pathId;
import static com.denigunawan.iconjavabackendrestapi.components.constant.MStudentConstant.pathURL;
import static com.denigunawan.iconjavabackendrestapi.components.constant.MStudentConstant.pathRequest;

@RestController
@RequestMapping (value = pathRequest)
public class MStudentController {

    @Autowired
    private MStudentService studentService;

    @PostMapping
    private MStudentResponse postDataStudent(@RequestBody MStudentRequest request){
        return studentService.postDataStudent(request);
    }

    @PutMapping ( value = pathURL)
    public MStudentResponse updateDataStudent(@PathVariable(value = pathId) String id, @RequestBody MStudentRequest request){
        return  studentService.putDataStudent(request, id);
    }

    @GetMapping
    public List<MStudentResponse> listDataStudent(){
        return  studentService.listDataStudent();
    }

    @DeleteMapping( value = pathURL)
    public String deleteDataStudentById(@PathVariable(pathId) String id){
        studentService.deleteStudentById(id);
        return "Delete Data Successfully";
    }

}
