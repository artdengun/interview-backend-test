package com.denigunawan.iconjavabackendrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MStudent  {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) # bisa make ini tapi saya pakai uuid agar uniq idnya
    @GeneratedValue(generator = "student-system")
    @GenericGenerator (name = "student-system", strategy = "uuid2")
    private String id;

    private String name;

    private String address;

    @OneToOne
    @JoinColumn(name = "id_faculty", nullable = false)
    private MFaculty faculty;

    @OneToOne
    @JoinColumn(name = "id_major", nullable = false)
    private MMajor major;

    @ManyToOne
    @JoinColumn(name = "id_study", nullable = false)
    private MStudy study;
}
