package com.apimobilidade.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimobilidade.entity.GeoPosition;
import com.apimobilidade.resources.Itinerario;
import com.apimobilidade.resources.linha.Onibus;

@RestController
public class ItinerarioController {
	
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/itinerario")
	public Itinerario itinerario() {
		
		Onibus linha1 = new Onibus(counter.incrementAndGet(), 1, "Linha 1");
		GeoPosition local0 = new GeoPosition(-30.12419057422600000, -51.22378313620700000);
		GeoPosition local1 = new GeoPosition(-30.12410057422600000, -51.22352313620700000);
		
		List<GeoPosition> localizacoes = new ArrayList<GeoPosition>();
		
		localizacoes.add(local0);
		localizacoes.add(local1);
		
		return new Itinerario(linha1, localizacoes);
	}
}
