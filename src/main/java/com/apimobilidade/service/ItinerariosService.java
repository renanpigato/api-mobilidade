package com.apimobilidade.service;

import com.apimobilidade.provider.repository.ItinerarioRepository;

public class ItinerariosService {
	
	private String itinerarios;
	private ItinerarioRepository itinerarioRepository;

	public ItinerariosService(
		String itinerarios, 
		ItinerarioRepository itinerarioRepository
	) {
		this.itinerarios = itinerarios;
		this.itinerarioRepository = itinerarioRepository;
	}

	public void refreshLinhaRepository() {
		System.out.println(
			this.itinerarios
			+ " - " +
			this.itinerarioRepository
		);
	}
}
