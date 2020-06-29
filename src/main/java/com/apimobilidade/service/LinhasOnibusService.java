package com.apimobilidade.service;

import java.util.Iterator;
import java.util.Optional;

import com.apimobilidade.collection.LinhasOnibus;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.LinhaOnibus;

public class LinhasOnibusService {
	
	LinhasOnibus linhasOnibus;
	LinhaRepository linhaRepository;

	/**
	 * @param linhasIntegration
	 * @param linhaRepository
	 */
	public LinhasOnibusService(
		LinhasOnibus linhasIntegration,
		LinhaRepository linhaRepository
	) {
		super();
		this.linhasOnibus = linhasIntegration;
		this.linhaRepository = linhaRepository;
	}

	/**
	 * @return the linhasIntegration
	 */
	public LinhasOnibus getLinhasIntegration() {
		return linhasOnibus;
	}

	/**
	 * @return the linhaRepository
	 */
	public LinhaRepository getLinhaRepository() {
		return linhaRepository;
	}

	public void refreshLinhaRepository() {
		
		if (this.linhasOnibus.size() == 0) {
			return;
		}
		
		if ((int)this.linhaRepository.count() == this.linhasOnibus.size()) {	
			return;
		}

		Iterator<LinhaOnibus> linhasIntegrationIt = this.linhasOnibus.iterator();
		
		while(linhasIntegrationIt.hasNext()) {
			
			LinhaOnibus linhaIntegration = (LinhaOnibus) linhasIntegrationIt.next();
			Optional<Linha> linhaEncontrada = this.linhaRepository.findByIdLinha(linhaIntegration.getId());
			
			if (linhaEncontrada.equals(Optional.empty())) {
			
				Linha l = new Linha(
					linhaIntegration.getId(),
					linhaIntegration.getCodigo(),
					linhaIntegration.getNome(),
					new String("O")
				);
				
				this.linhaRepository.save(l);
			}
		}
	}

}
