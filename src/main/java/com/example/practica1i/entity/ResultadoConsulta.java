package com.example.practica1i.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table (name = "RESULTADOS_CONSULTAS")
public class ResultadoConsulta implements Serializable {

    private static final long serialVersionUID = 4862072475L;

    @SequenceGenerator (
        name = "ResultadoConsulta",
        sequenceName = "SEQ_RESULTADOS_CONSULTAS",
        allocationSize = 1
    )

    @Id 
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "ResultadoConsulta"
    )

    //Mapeo Columnas

    @Column (name = "id")
    private Long id;

    @ManyToOne 
    @JoinColumn (name = "cabecera_id")
    private CabeceraConsulta cabeceraConsulta;
    
    @Column (name = "wrapper_type")
    private String wrapperType;
    
    @Column (name = "artist_name")
    private String artistName;

    @Column (name = "track_name")
    private String trackName;
    
    @JdbcTypeCode(SqlTypes.CLOB)
    @Column (name = "long_description")
    private String longDescription;

    @Column (name = "release_date")
    private LocalDateTime releaseDate;
    
    //Getters & Settes

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CabeceraConsulta getCabeceraConsulta() {
        return this.cabeceraConsulta;
    }

    public void setCabeceraConsulta(CabeceraConsulta cabeceraConsulta) {
        this.cabeceraConsulta = cabeceraConsulta;
    }

    public String getWrapperType() {
        return this.wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDateTime getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
