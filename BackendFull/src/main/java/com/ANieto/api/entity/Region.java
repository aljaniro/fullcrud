package com.ANieto.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "region")
public class Region implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long region_id;
    private String nombre;
    private static final long serialVersionUID = 1L;
}
