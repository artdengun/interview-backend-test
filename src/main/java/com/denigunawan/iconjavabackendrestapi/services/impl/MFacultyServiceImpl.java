package com.denigunawan.iconjavabackendrestapi.services.impl;

import com.denigunawan.iconjavabackendrestapi.dtos.MFacultyRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MFacultyResponse;
import com.denigunawan.iconjavabackendrestapi.models.MFaculty;
import com.denigunawan.iconjavabackendrestapi.repositories.MFacultyRepository;
import com.denigunawan.iconjavabackendrestapi.services.MFacultyService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor // bisa untuk inject data juga  seperti @Autowired
public class MFacultyServiceImpl  implements MFacultyService {

    private MFacultyRepository facultyRepository;

    @Transactional
    @Override
    public MFacultyResponse postDataFaculty(MFacultyRequest facultyRequest) {

        MFaculty requestData = new MFaculty();
        requestData.setNameFaculty(facultyRequest.getNameFaculty());

        MFaculty saveData = facultyRepository.save(requestData);

        MFacultyResponse responseData = new MFacultyResponse();
        responseData.setId(saveData.getId());
        responseData.setNameFaculty(saveData.getNameFaculty());

        return  responseData;
    }

    @SneakyThrows
    @Transactional
    @Override
    public MFacultyResponse putDataFaculty(MFacultyRequest facultyRequest, String id) {

        Optional<MFaculty> searchId = facultyRepository.findById(id);

        if(searchId.isEmpty()){ throw new Exception("Opsss... Data Not Founds"); }

        searchId.get().setNameFaculty(facultyRequest.getNameFaculty());

        MFaculty saveData = facultyRepository.save(searchId.get());

        MFacultyResponse responseData = new MFacultyResponse();
        responseData.setId(saveData.getId());
        responseData.setNameFaculty(saveData.getNameFaculty());

        return responseData;

    }

    @Override
    public List<MFacultyResponse> listDataFaculty(){

      List<MFaculty> searchData = facultyRepository.findAll();
      List<MFacultyResponse> responseData = new ArrayList<>();

      searchData.forEach(search -> {

          MFacultyResponse facultyResponse = new MFacultyResponse();

          facultyResponse.setId(search.getId());
          facultyResponse.setNameFaculty(search.getNameFaculty());

          responseData.add(facultyResponse);

      });

        return responseData;

    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteFacultyById(String id) {
        Optional<MFaculty> searchId = facultyRepository.findById(id);
        log.info("Search Id ");

        if(searchId.isEmpty()){
            throw new Exception("Opsss... Id Not Founds");
        }
        log.info("Id Founds, delete data in action now. ");
        facultyRepository.deleteById(id);
    }

}
