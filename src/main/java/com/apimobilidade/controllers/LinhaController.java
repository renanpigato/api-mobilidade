package com.apimobilidade.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.exception.ResourceNotFoundException;
import com.apimobilidade.provider.LinhasOnibusProvider;
import com.apimobilidade.provider.integration.PoaTransporte;
import com.apimobilidade.provider.repository.ItinerarioRepository;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.LinhaOnibus;
import com.apimobilidade.service.LinhasOnibusService;

@RestController
public class LinhaController {
	
	private PoaTransporte integrationPoaTransporte = new PoaTransporte();
	
	@Autowired
	private LinhaRepository linhaRepository;
	
	@Autowired
	private ItinerarioRepository itinerarioRepository;
	
	@PostMapping("/onibus")
	public LinhaOnibus salvarLinhaOnibus(
		@Validated @RequestBody() LinhaOnibus linhaOnibus
	) {
		LinhasOnibusService linhasService = new LinhasOnibusService(this.linhaRepository);
		return linhasService.refreshLinhaOnibus(linhaOnibus);
	}
	
	@GetMapping("/onibus")
	public Collection<LinhaOnibus> linhasOnibus(
		@RequestParam(defaultValue = "", value = "nome") String nome
	) {
		LinhasOnibusService linhasService = new LinhasOnibusService(this.linhaRepository);
		linhasService.refreshLinhaRepository(this.integrationPoaTransporte.getLinhasOnibus());
		
		LinhasOnibusProvider providerLinhasOnibus = new LinhasOnibusProvider(
			this.linhaRepository,
			this.itinerarioRepository
		);
		
		if(!nome.equals("")) {
			providerLinhasOnibus.setFiltroNome(nome.toUpperCase());
		}
		
		providerLinhasOnibus.run();
			
		if (providerLinhasOnibus.isEmpty()) {
			throw new ResourceNotFoundException("Linha não encontrada!");
		}
		
		return providerLinhasOnibus.getLinhasOnibus();
	}
	
	@GetMapping("/onibus/itinerarios")
	public Collection<LinhaOnibus> linhasOnibusRaio(
		@RequestParam(value = "latitude") double latitude,
		@RequestParam(value = "longitude") double longitude,
		@RequestParam(value = "raio") double raio
	) {
		LinhasOnibusService linhasService = new LinhasOnibusService(this.linhaRepository);
		linhasService.refreshLinhaRepository(this.integrationPoaTransporte.getLinhasOnibus());
		
		LinhasOnibusProvider providerLinhasOnibus = new LinhasOnibusProvider(
			this.linhaRepository,
			this.itinerarioRepository
		);
		
		providerLinhasOnibus.setFiltroRaio(raio);
		providerLinhasOnibus.setFiltroLocalizacao(new Localizacao(latitude, longitude));
		
		providerLinhasOnibus.run();
			
		if (providerLinhasOnibus.isEmpty()) {
			throw new ResourceNotFoundException("Linhas não encontradas!");
		}
		
		return providerLinhasOnibus.getLinhasOnibus();
	}
}
