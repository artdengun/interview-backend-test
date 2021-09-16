package com.denigunawan.iconjavabackendrestapi.services.impl;

import com.denigunawan.iconjavabackendrestapi.dtos.*;
import com.denigunawan.iconjavabackendrestapi.models.MFaculty;
import com.denigunawan.iconjavabackendrestapi.models.MMajor;
import com.denigunawan.iconjavabackendrestapi.models.MStudent;
import com.denigunawan.iconjavabackendrestapi.models.MStudy;
import com.denigunawan.iconjavabackendrestapi.repositories.MFacultyRepository;
import com.denigunawan.iconjavabackendrestapi.repositories.MMajorRepository;
import com.denigunawan.iconjavabackendrestapi.repositories.MStudentRepository;
import com.denigunawan.iconjavabackendrestapi.repositories.MStudyRepository;
import com.denigunawan.iconjavabackendrestapi.services.MStudentService;
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
public class MStudentServiceImpl implements MStudentService {

    private MFacultyRepository facultyRepository;
    private MMajorRepository majorRepository;
    private MStudyRepository studyRepository;
    private MStudentRepository studentRepository;

    private MStudent requestStudentRequestData(MStudentRequest request, MMajor major, MFaculty faculty, MStudy study){
        MStudent requestData = new MStudent();
        requestData.setName(request.getName());
        requestData.setAddress(request.getAddress());
        requestData.setFaculty(faculty);
        requestData.setMajor(major);
        requestData.setStudy(study);

        return  requestData;
    }

    private  MStudentResponse responseStudentDataDariModel(MStudent student, MFacultyResponse facultyResponse, MMajorResponse majorResponse, MStudyResponse studyResponse){

        MStudentResponse responseData = new MStudentResponse();
        responseData.setId(student.getId());
        responseData.setName(student.getName());
        responseData.setAddress(student.getAddress());
        responseData.setFaculty(facultyResponse);
        responseData.setMajor(majorResponse);
        responseData.setStudy(studyResponse);
        return  responseData;
    }

    private MFacultyResponse responseFromFaculty(MFaculty faculty){
        MFacultyResponse facultyResponse = new MFacultyResponse();
        facultyResponse.setId(faculty.getId());
        facultyResponse.setNameFaculty(faculty.getNameFaculty());
        return  facultyResponse;
    }

    private MMajorResponse responseFromMajor(MMajor major){
        MMajorResponse responseData = new MMajorResponse();
        responseData.setId(major.getId());
        responseData.setNameMajor(major.getNameMajor());
        return responseData;
    }

    private MStudyResponse responseFromStudy(MStudy study){

        MStudyResponse studyResponse = new MStudyResponse();
        studyResponse.setId(study.getId());
        studyResponse.setNameStudy(study.getNameStudy());
        return studyResponse;

    }

    @SneakyThrows
    @Transactional
    @Override
    public MStudentResponse postDataStudent(MStudentRequest studentRequest) {

        Optional<MFaculty> searchIdFaculty = facultyRepository.findById(studentRequest.getFaculty());

        if(searchIdFaculty.isEmpty()){throw  new Exception("Id Faculty Not Founds.... "); }

        MFaculty facultyFound  = searchIdFaculty.get();

        Optional<MMajor> searchIdMajor = majorRepository.findById(studentRequest.getMajor());
        if(searchIdMajor.isEmpty()){throw new Exception("Id Major Not Founds... "); }
        MMajor majorFound = searchIdMajor.get();

        Optional<MStudy> searchStudy = studyRepository.findById(studentRequest.getStudy());
        if(searchStudy.isEmpty()){throw  new Exception("Id Study Not Founds.... "); }

        MStudy studyFound = searchStudy.get();

        MStudent studentModel = requestStudentRequestData(studentRequest,majorFound,facultyFound,studyFound);
        MStudent responseStudent = studentRepository.save(studentModel);

        MMajorResponse responseMajor = responseFromMajor(majorFound);
        MFacultyResponse responseFaculty = responseFromFaculty(facultyFound);
        MStudyResponse responseStudy = responseFromStudy(studyFound);

        return responseStudentDataDariModel(responseStudent,responseFaculty,responseMajor,responseStudy);
    }

    @Transactional
    @SneakyThrows
    @Override
    public MStudentResponse putDataStudent(MStudentRequest studentRequest, String id) {
        Optional<MFaculty> searchIdFaculty = facultyRepository.findById(studentRequest.getFaculty());

        if(searchIdFaculty.isEmpty()){throw  new Exception("Id Faculty Not Founds.... "); }

        MFaculty facultyFound  = searchIdFaculty.get();

        Optional<MMajor> searchIdMajor = majorRepository.findById(studentRequest.getMajor());
        if(searchIdMajor.isEmpty()){throw new Exception("Id Major Not Founds... "); }
        MMajor majorFound = searchIdMajor.get();

        Optional<MStudy> searchStudy = studyRepository.findById(studentRequest.getStudy());
        if(searchStudy.isEmpty()){throw  new Exception("Id Study Not Founds.... "); }

        MStudy studyFound = searchStudy.get();

        Optional<MStudent> searchStudentId = studentRepository.findById(id);
        if(searchStudentId.isEmpty()){throw new Exception("Id Student Not Founds");}

        searchStudentId.get().setName(studentRequest.getName());
        searchStudentId.get().setAddress(studentRequest.getAddress());
        searchStudentId.get().setStudy(studyFound);
        searchStudentId.get().setMajor(majorFound);
        searchStudentId.get().setFaculty(facultyFound);

        MStudent saveData = studentRepository.save(searchStudentId.get());

        MMajorResponse responseMajor = responseFromMajor(majorFound);
        MFacultyResponse responseFaculty = responseFromFaculty(facultyFound);
        MStudyResponse responseStudy = responseFromStudy(studyFound);
        MStudentResponse responseStudent = responseStudentDataDariModel(saveData,responseFaculty,responseMajor,responseStudy);
        return responseStudent;
    }

    @Override
    public List<MStudentResponse> listDataStudent() {
        List<MStudent> findDataStudent = studentRepository.findAll();
        List<MStudentResponse> responseData = new ArrayList<>();
        findDataStudent.forEach( search -> {

            MMajorResponse majorResponse = responseFromMajor(search.getMajor());
            MFacultyResponse facultyResponse = responseFromFaculty(search.getFaculty());
            MStudyResponse studyResponse = responseFromStudy(search.getStudy());
            MStudentResponse studentResponse = responseStudentDataDariModel(search, facultyResponse, majorResponse, studyResponse);

            responseData.add(studentResponse);
        });
        return responseData;
    }

    @Transactional
    @SneakyThrows
    @Override
    public void deleteStudentById(String id) {
        Optional<MStudent> searchStudentId = studentRepository.findById(id);
        if(searchStudentId.isEmpty()){throw new Exception("Opsss.. Id Not Founds"); }
        log.info("id found, exectuion delete data");
        studentRepository.deleteById(id);
    }

}
