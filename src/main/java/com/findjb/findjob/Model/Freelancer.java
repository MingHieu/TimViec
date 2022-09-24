package com.findjb.findjob.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "freelancers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "level",nullable = false)
    private Integer level;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private User user;
}
