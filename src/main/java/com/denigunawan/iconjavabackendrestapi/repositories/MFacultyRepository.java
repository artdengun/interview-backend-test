package com.denigunawan.iconjavabackendrestapi.repositories;

import com.denigunawan.iconjavabackendrestapi.models.MFaculty;
import org.springframework.data.jpa.repository.JpaRepository;

/* @Repository , # kita bisa menggunakan annotation ini  bisa juga tidak, bersifat optional
 saya memilih untuk tidak menggunakanya .*/ 
public interface MFacultyRepository extends JpaRepository<MFaculty, String> {

    // disini saya bisa menulis query. hql, specification. bawaan jpa ataupul psql
}
