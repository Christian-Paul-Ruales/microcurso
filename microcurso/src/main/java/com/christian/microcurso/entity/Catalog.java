package com.christian.microcurso.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "cat_code")
    private String catCode;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "cat_description")
    private String catDescription;

    @Column(name = "cat_registration_date")
    private Date catRegistrationDate;

    @Column(name = "cat_end_date")
    private Date catEndDate;

    @Column(name = "cat_status")
    private Short catStatus;

}
