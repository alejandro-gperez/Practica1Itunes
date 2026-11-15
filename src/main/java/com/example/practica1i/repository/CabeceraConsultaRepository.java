package com.example.practica1i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practica1i.entity.CabeceraConsulta;

@Repository("cabeceraConsultaRepository")
public interface CabeceraConsultaRepository extends JpaRepository<CabeceraConsulta, Long> {

}
