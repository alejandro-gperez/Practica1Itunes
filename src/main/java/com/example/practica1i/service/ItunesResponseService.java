package com.example.practica1i.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.example.practica1i.dto.ItunesResponseDTO;
import com.example.practica1i.entity.CabeceraConsulta;
import com.example.practica1i.entity.ResultadoConsulta;
import com.example.practica1i.repository.CabeceraConsultaRepository;
import com.example.practica1i.repository.ResultadoConsultaRepository;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/itunes")
public class ItunesResponseService {

    ObjectMapper objectMapper = new ObjectMapper();

    CabeceraConsultaRepository cabeceraConsultaRepository;
    ResultadoConsultaRepository resultadoConsultaRepository;

    public ItunesResponseService(CabeceraConsultaRepository cabeceraConsultaRepository,
            ResultadoConsultaRepository resultadoConsultaRepository) {
        this.resultadoConsultaRepository = resultadoConsultaRepository;
        this.cabeceraConsultaRepository = cabeceraConsultaRepository;
    }

    private static final String ITUNES_API_BASE_URL = "https://itunes.apple.com/";

    RestClient restClient = RestClient.create(ITUNES_API_BASE_URL);

    @GetMapping("/find/search")
    public ResponseEntity<?> getMethodName(@RequestParam String text) {

        String respuesta;

        respuesta = restClient.get()
                .uri("/search?term={text}", text)
                .retrieve()
                .body(String.class);

        JsonNode respuestaJson = objectMapper.readTree(respuesta);

        JsonNode results = respuestaJson.get("results");

        List<ItunesResponseDTO> responseDTOs = new ArrayList<>();

        CabeceraConsulta cabeceraConsulta = new CabeceraConsulta();

        cabeceraConsulta.setFechaConsulta(OffsetDateTime.now());
        cabeceraConsulta.setUrlConsultada(ITUNES_API_BASE_URL + "search?term=" + text);
        cabeceraConsulta.setRespuestaAPI(respuesta);

        cabeceraConsultaRepository.save(cabeceraConsulta);

        for (JsonNode r : results) {

            ResultadoConsulta resultadoConsulta = new ResultadoConsulta();
            resultadoConsulta.setCabeceraConsulta(cabeceraConsulta);

            String wrapperType = null;
            String artistName = null;
            String trackName = null;
            String longDescription = null;
            OffsetDateTime releaseDate = null;

            JsonNode checkWrapperType = r.get("wrapperType");
            if (checkWrapperType != null && !checkWrapperType.isNull()) {
               wrapperType = checkWrapperType.asString();
            }

            JsonNode checkArtistName = r.get("artistName");
            if (checkArtistName != null && !checkArtistName.isNull()) {
                artistName = checkArtistName.asString();
            } 

            JsonNode checkTrackName = r.get("trackName");
            if (checkTrackName != null && !checkTrackName.isNull()) {
                trackName = checkTrackName.asString();
            }

            JsonNode checkLongDescription = r.get("longDescription");
            if (checkLongDescription != null && !checkLongDescription.isNull()) {
                longDescription = checkLongDescription.asString();
            } 

            JsonNode checkReleaseDate = r.get("releaseDate");
            if (checkReleaseDate != null && !checkReleaseDate.isNull()) {
                releaseDate = OffsetDateTime.parse(checkReleaseDate.asString());
            } 

            resultadoConsulta.setWrapperType(wrapperType);
            resultadoConsulta.setArtistName(artistName);
            resultadoConsulta.setTrackName(trackName);
            resultadoConsulta.setLongDescription(longDescription);
            if (releaseDate == null) {
                resultadoConsulta.setReleaseDate(null);
            } else {
                resultadoConsulta.setReleaseDate(releaseDate.toLocalDateTime());
            }

            resultadoConsultaRepository.save(resultadoConsulta);

            ItunesResponseDTO itunesResponseDTO = new ItunesResponseDTO();
            itunesResponseDTO.setWrapperType(wrapperType);
            itunesResponseDTO.setArtistName(artistName);
            itunesResponseDTO.setTrackName(trackName);
            itunesResponseDTO.setLongDescription(longDescription);
            itunesResponseDTO.setReleaseDate(releaseDate);

            responseDTOs.add(itunesResponseDTO);
        }

        return ResponseEntity.ok(responseDTOs);

    }

}
