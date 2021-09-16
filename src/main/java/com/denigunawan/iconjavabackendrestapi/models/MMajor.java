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
@Table (name = "m_major")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MMajor {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) # bisa make ini tapi saya pakai uuid agar uniq idnya
    @GeneratedValue (generator = "major-system")
    @GenericGenerator (name = "major-system", strategy = "uuid2")
    private String id;

    private String nameMajor;
}
