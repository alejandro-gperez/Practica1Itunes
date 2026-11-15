package com.example.practica1i.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table (name = "CABECERAS_CONSULTAS")
public class CabeceraConsulta implements Serializable {

    private static final long serialVersionUID = -2500157843L;

    @SequenceGenerator (
        name = "CabeceraConsulta",
        sequenceName = "SEQ_CABECERAS_CONSULTAS",
        allocationSize = 1
    )

    @Id 
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "CabeceraConsulta"
    )

    //Mapeo columnas

    @Column (name = "id")
    private Long id;

    @Column (name = "fecha_consulta")
    private OffsetDateTime fechaConsulta;

    @Column (name = "url_consultada")
    private String urlConsultada;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column (name = "respuesta_api")
    private String respuestaAPI;

    //Getters & Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getFechaConsulta() {
        return this.fechaConsulta;
    }

    public void setFechaConsulta(OffsetDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getUrlConsultada() {
        return this.urlConsultada;
    }

    public void setUrlConsultada(String urlConsultada) {
        this.urlConsultada = urlConsultada;
    }

    public String getRespuestaAPI() {
        return this.respuestaAPI;
    }

    public void setRespuestaAPI(String respuestaAPI) {
        this.respuestaAPI = respuestaAPI;
    }
}
