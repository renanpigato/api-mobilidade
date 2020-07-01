package com.apimobilidade.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimobilidade.collection.PontosTaxi;
import com.apimobilidade.provider.PontoTaxiProvider;
import com.apimobilidade.resources.PontoTaxi;
import com.apimobilidade.service.PontoTaxiService;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
	
	PontoTaxiProvider ptoTaxiProvider = new PontoTaxiProvider();

	@PostMapping("pontos")
	public PontoTaxi salvarTaxi(
		@Validated @RequestBody() PontoTaxi pontoTaxi
	) {
		PontoTaxiService ptoTaxiService = new PontoTaxiService(this.ptoTaxiProvider);
		return ptoTaxiService.refreshProvider(pontoTaxi);
	}
	
	@GetMapping("pontos")
	public PontosTaxi taxi() {
		return this.ptoTaxiProvider.getPontosTaxi();
	}
}
