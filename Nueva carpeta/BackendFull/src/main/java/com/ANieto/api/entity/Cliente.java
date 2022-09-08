package com.ANieto.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;

@Data
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 4,max = 12)
    private String nombre;
    @NotEmpty
    @Size(min = 4,max = 12)
    private String apellido;
    @NotEmpty
    @NumberFormat
    private String telefono;
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String foto;
    @NotNull(message = "El campo no puede estar vacio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;
    private static final long serialVersionUID = 1L;
}
