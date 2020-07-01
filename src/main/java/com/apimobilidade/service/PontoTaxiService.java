package com.apimobilidade.service;

import java.util.Date;
import java.util.Optional;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.provider.PontoTaxiProvider;
import com.apimobilidade.resources.PontoTaxi;

public class PontoTaxiService {
	
	private PontoTaxiProvider providerPtoTaxi;

	/**
	 * @param providerPtoTaxi
	 */
	public PontoTaxiService(PontoTaxiProvider providerPtoTaxi) {
		super();
		this.providerPtoTaxi = providerPtoTaxi;
	}

	public PontoTaxi refreshProvider(PontoTaxi pontoTaxiRecebido) {
		
		Optional<PontoTaxi> ptoTaxiEncontrado = this.providerPtoTaxi.getPontoByNome(
			pontoTaxiRecebido.getNomePonto()
		);
		
		PontoTaxi ptoTaxi = new PontoTaxi(
			pontoTaxiRecebido.getNomePonto(),
			new Localizacao(
				pontoTaxiRecebido.getLocalizacao().getLatitude(),
				pontoTaxiRecebido.getLocalizacao().getLongitude()
			),
			new Date()
		);
		
		if (ptoTaxiEncontrado.equals(Optional.empty())) {
			ptoTaxi.setId(this.providerPtoTaxi.getNextId());
		} else {
			ptoTaxi.setId(ptoTaxiEncontrado.get().getId());
		}
		
		return this.providerPtoTaxi.save(ptoTaxi);
	}

}
