package com.denigunawan.iconjavabackendrestapi.controllers;

import com.denigunawan.iconjavabackendrestapi.dtos.MStudyRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MStudyResponse;
import com.denigunawan.iconjavabackendrestapi.services.MStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.denigunawan.iconjavabackendrestapi.components.constant.MStudyConstant.pathId;
import static com.denigunawan.iconjavabackendrestapi.components.constant.MStudyConstant.pathURL;
import static com.denigunawan.iconjavabackendrestapi.components.constant.MStudyConstant.pathRequest;

@RestController
@RequestMapping (value = pathRequest)
public class MStudyController {

    @Autowired
    private MStudyService studyService;

    @PostMapping
    private MStudyResponse postDataStudy(@RequestBody MStudyRequest request){
        return studyService.postDataStudy(request);
    }

    @PutMapping ( value = pathURL)
    public MStudyResponse updateDataStudy(@PathVariable(value = pathId) String id, @RequestBody MStudyRequest request){
        return  studyService.putDataStudy(request, id);
    }

    @GetMapping
    public List<MStudyResponse> listDataStudy(){
        return  studyService.listDataStudy();
    }

    @DeleteMapping( value = pathURL)
    public String deleteDataStudyById(@PathVariable(pathId) String id){
        studyService.deleteDataStudyById(id);
        return "Delete Data Successfully";
    }

}
