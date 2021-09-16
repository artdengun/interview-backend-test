package com.denigunawan.iconjavabackendrestapi.controllers;

import com.denigunawan.iconjavabackendrestapi.dtos.MMajorRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MMajorResponse;
import com.denigunawan.iconjavabackendrestapi.services.MMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.denigunawan.iconjavabackendrestapi.components.constant.MMajorConstant.pathId;
import static com.denigunawan.iconjavabackendrestapi.components.constant.MMajorConstant.pathURL;
import static com.denigunawan.iconjavabackendrestapi.components.constant.MMajorConstant.pathRequest;

@RestController
@RequestMapping (value = pathRequest)
public class MMajorController {

    @Autowired
    private MMajorService majorService;

    @PostMapping
    private MMajorResponse postDataMajor(@RequestBody MMajorRequest request){
        return majorService.postDataMajor(request);
    }

    @PutMapping ( value = pathURL)
    public MMajorResponse updateDataMajor(@PathVariable(value = pathId) String id, @RequestBody MMajorRequest request){
        return  majorService.putDataMajor(request, id);
    }

    @GetMapping
    public List<MMajorResponse> listDataMajor(){
        return  majorService.listDataMajor();
    }

    @DeleteMapping( value = pathURL)
    public String deleteDataMajorById(@PathVariable(pathId) String id){
        majorService.deleteDataMajorById(id);
        return "Delete Data Successfully";
    }

}
