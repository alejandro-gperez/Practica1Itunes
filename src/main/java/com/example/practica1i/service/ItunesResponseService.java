package com.example.practica1i.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.example.practica1i.repository.CabeceraConsultaRepository;
import com.example.practica1i.repository.ResultadoConsultaRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/itunes")
public class ItunesResponseService {

    CabeceraConsultaRepository cabeceraConsultaRepository;
    ResultadoConsultaRepository resultadoConsultaRepository;
    

    public ItunesResponseService(CabeceraConsultaRepository cabeceraConsultaRepository, ResultadoConsultaRepository resultadoConsultaRepository) {
        this.resultadoConsultaRepository = resultadoConsultaRepository;
        this.cabeceraConsultaRepository = cabeceraConsultaRepository;
    }

    private static final String ITUNES_API_BASE_URL = "https://itunes.apple.com/";

    RestClient restClient = RestClient.create(ITUNES_API_BASE_URL);

    @GetMapping("/find/search")
    public String getMethodName(@RequestParam String text) {

        String respuesta;

        respuesta = restClient.get()
            .uri("/search?term={text}", text)
            .retrieve()
            .body(String.class);






        return respuesta;
    }
    

}
