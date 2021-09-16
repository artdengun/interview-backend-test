package com.denigunawan.iconjavabackendrestapi.services.impl;

import com.denigunawan.iconjavabackendrestapi.dtos.MStudyRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MStudyResponse;
import com.denigunawan.iconjavabackendrestapi.models.MStudy;
import com.denigunawan.iconjavabackendrestapi.repositories.MStudyRepository;
import com.denigunawan.iconjavabackendrestapi.services.MStudyService;
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
public class MStudyServiceImpl implements MStudyService {

    private MStudyRepository studyRepository;

    @Transactional
    @Override
    public MStudyResponse postDataStudy(MStudyRequest studyRequest) {
        MStudy requestData = new MStudy();
        requestData.setNameStudy(studyRequest.getNameStudy());

        MStudy saveData = studyRepository.save(requestData);

        MStudyResponse responseData = new MStudyResponse();
        responseData.setId(saveData.getId());
        responseData.setNameStudy(saveData.getNameStudy());

        return responseData;
    }

    @Transactional
    @SneakyThrows
    @Override
    public MStudyResponse putDataStudy(MStudyRequest studyRequest, String id) {
        Optional<MStudy> searchId = studyRepository.findById(id);
        log.info("search id ");
        if(searchId.isEmpty()){throw new Exception("Opss... id not founds");}

        searchId.get().setNameStudy(studyRequest.getNameStudy());

        MStudy savedMStudy  = studyRepository.save(searchId.get());

        MStudyResponse response = new MStudyResponse();
        response.setId(savedMStudy.getId());
        response.setNameStudy(savedMStudy.getNameStudy());

        return response;
    }

    @Override
    public List<MStudyResponse> listDataStudy() {

        List<MStudy> searchAll = studyRepository.findAll();

        List<MStudyResponse> scopeDataStudy = new ArrayList<>();

        searchAll.forEach(search->{
            MStudyResponse response = new MStudyResponse();
            response.setId(search.getId());
            response.setNameStudy(search.getNameStudy());

            scopeDataStudy.add(response);
        });

        return scopeDataStudy;
    }

    @Transactional
    @SneakyThrows
    @Override
    public void deleteDataStudyById(String id) {

        Optional<MStudy> searchId = studyRepository.findById(id);
        log.info("Search Id .... ");
        if(searchId.isEmpty()){throw new Exception("Opsss... Id Not Founds");}
        log.info("Search Id Founds, Execution Data .. ");
        studyRepository.deleteById(id);

    }

}
