package com.denigunawan.iconjavabackendrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table (name = "m_faculty")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MFaculty {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) # bisa make ini tapi saya pakai uuid agar uniq idnya
    @GeneratedValue(generator = "faculty-system")
    @GenericGenerator(name = "faculty-system", strategy = "uuid2")
    private String id;

    private String nameFaculty;
}
