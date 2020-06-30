package com.apimobilidade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apimobilidade.collection.Itinerarios;
import com.apimobilidade.exception.ResourceNotFoundException;
import com.apimobilidade.provider.ItinerariosProvider;
import com.apimobilidade.provider.integration.PoaTransporte;
import com.apimobilidade.provider.repository.ItinerarioRepository;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.service.ItinerariosService;

@RestController
public class ItinerarioController {
	
	private PoaTransporte integrationPoaTransporte = new PoaTransporte();
	
	@Autowired
	private ItinerarioRepository itinerarioRepository;
	
	@Autowired
	private LinhaRepository linhaRepository;

	@GetMapping("/itinerarios")
	public Itinerarios itinerario(
		@RequestParam(defaultValue = "", value = "lat") Double latitude,
		@RequestParam(defaultValue = "", value = "lng") Double longitude,
		@RequestParam(required = true, value = "id_linha") Long idLinha
	) {
		ItinerariosService itinerariosService = new ItinerariosService(
			this.integrationPoaTransporte.getItinerarios(idLinha),
			this.itinerarioRepository
		);
		itinerariosService.refreshLinhaRepository();
				
		ItinerariosProvider providerItinerarios = new ItinerariosProvider(
			this.linhaRepository,
			this.itinerarioRepository,
			idLinha
		);
		
		if(latitude != null) {
			providerItinerarios.setLatitude(latitude);
		}
		
		if(longitude != null) {
			providerItinerarios.setLongitude(longitude);
		}
		
		providerItinerarios.run();
			
		if (providerItinerarios.isEmpty()) {
			throw new ResourceNotFoundException("Itinerário não encontrado!");
		}
		
		return providerItinerarios.getItinerarios();
	}
}
