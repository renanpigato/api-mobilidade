package com.apimobilidade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apimobilidade.collection.LinhasOnibus;
import com.apimobilidade.provider.LinhasOnibusProvider;
import com.apimobilidade.provider.integration.PoaTransporte;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.service.LinhasOnibusService;
import com.apimobilidade.exception.ResourceNotFoundException;

@RestController
public class LinhaController {
	
	private PoaTransporte integrationPoaTransporte = new PoaTransporte();
	
	@Autowired
	private LinhaRepository linhaRepository;
	
	@GetMapping("/onibus")
	public LinhasOnibus linhasOnibus(
		@RequestParam(defaultValue = "", value = "nome") String nome
	) {
		LinhasOnibusService linhasService = new LinhasOnibusService(
			this.integrationPoaTransporte.getLinhasOnibus(),
			this.linhaRepository
		);
		linhasService.refreshLinhaRepository();
		
		LinhasOnibusProvider providerLinhasOnibus = new LinhasOnibusProvider(this.linhaRepository);
		
		if(!nome.equals("")) {
			providerLinhasOnibus.setFiltroNome(nome.toUpperCase());
		}
		
		providerLinhasOnibus.run();
			
		if (providerLinhasOnibus.isEmpty()) {
			throw new ResourceNotFoundException("Linha não encontrada!");
		}
		
		return providerLinhasOnibus.getLinhasOnibus();
	}
}
