package com.denigunawan.iconjavabackendrestapi.repositories;

import com.denigunawan.iconjavabackendrestapi.models.MStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MStudentRepository  extends JpaRepository<MStudent, String> {
}
