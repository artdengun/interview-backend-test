package com.denigunawan.iconjavabackendrestapi.services.impl;

import com.denigunawan.iconjavabackendrestapi.dtos.MMajorRequest;
import com.denigunawan.iconjavabackendrestapi.dtos.MMajorResponse;
import com.denigunawan.iconjavabackendrestapi.models.MMajor;
import com.denigunawan.iconjavabackendrestapi.repositories.MMajorRepository;
import com.denigunawan.iconjavabackendrestapi.services.MMajorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor // bisa untuk inject data juga  seperti @Autowired
public class MMajorServiceImpl  implements MMajorService {

    private MMajorRepository majorRepository;

    @Transactional
    @Override
    public MMajorResponse postDataMajor(MMajorRequest majorRequest) {

        MMajor requestData = new MMajor();
        requestData.setNameMajor(majorRequest.getNameMajor());

        MMajor saveData = majorRepository.save(requestData);

        MMajorResponse responseData = new MMajorResponse();
        responseData.setId(saveData.getId());
        responseData.setNameMajor(saveData.getNameMajor());

        return responseData;
    }

    @SneakyThrows
    @Transactional
    @Override
    public MMajorResponse putDataMajor(MMajorRequest majorRequest, String id) {

        Optional<MMajor> searchId = majorRepository.findById(id);

        if(searchId.isEmpty()){ throw new Exception("Opss.. Id Not Founds"); }

        searchId.get().setNameMajor(majorRequest.getNameMajor());

        MMajor saveData = majorRepository.save(searchId.get());

        MMajorResponse responseData = new MMajorResponse();
        responseData.setId(saveData.getId());
        responseData.setNameMajor(saveData.getNameMajor());

        return responseData;
    }

    @Override
    public List<MMajorResponse> listDataMajor() {

        List<MMajor>  searchData = majorRepository.findAll();
        List<MMajorResponse> responseData = new ArrayList<>();

        searchData.forEach(search -> {
            MMajorResponse searchResponse = new MMajorResponse();
            searchResponse.setId(search.getId());
            searchResponse.setNameMajor(search.getNameMajor());

            responseData.add(searchResponse);
        });

        return responseData;
    }

    @Transactional
    @SneakyThrows
    @Override
    public void deleteDataMajorById(String id) {

        Optional<MMajor> searchId = majorRepository.findById(id);
        log.info("search id ");
        if(searchId.isEmpty()){throw new Exception("Opps.. Id Not Founds");}
        log.info("search id found, execution id");
        majorRepository.deleteById(id);

        }

}
