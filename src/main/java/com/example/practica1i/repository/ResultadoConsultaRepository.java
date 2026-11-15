package com.example.practica1i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practica1i.entity.ResultadoConsulta;

@Repository("resultadoConsultaRepository")
public interface ResultadoConsultaRepository extends JpaRepository<ResultadoConsulta, Long> {

}
