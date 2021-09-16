package com.denigunawan.iconjavabackendrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "m_study")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MStudy {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) # bisa make ini tapi saya pakai uuid agar uniq idnya
    @GeneratedValue (generator = "study-system")
    @GenericGenerator (name = "study-system", strategy = "uuid2")
    private String id;

    private String nameStudy;

}
