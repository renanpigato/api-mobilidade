package com.apimobilidade.service;

import java.util.Iterator;
import java.util.Optional;

import com.apimobilidade.collection.LinhasOnibus;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.LinhaOnibus;

public class LinhasOnibusService {
	
	private LinhaRepository linhaRepository;

	/**
	 * @param linhasIntegration
	 * @param linhaRepository
	 */
	public LinhasOnibusService(
		LinhaRepository linhaRepository
	) {
		super();
		this.linhaRepository = linhaRepository;
	}

	@SuppressWarnings("unchecked")
	public void refreshLinhaRepository(LinhasOnibus linhasOnibus) {
		
		if (linhasOnibus.size() == 0) {
			return;
		}
		
		if ((int)this.linhaRepository.count() == linhasOnibus.size()) {	
			return;
		}

		Iterator<LinhaOnibus> linhasIntegrationIt = (Iterator<LinhaOnibus>) linhasOnibus.values();
		
		while(linhasIntegrationIt.hasNext()) {
			
			LinhaOnibus linhaIntegration = (LinhaOnibus) linhasIntegrationIt.next();
			Optional<Linha> linhaEncontrada = this.linhaRepository.findByIdLinha(linhaIntegration.getIdLinha());
			
			if (linhaEncontrada.equals(Optional.empty())) {
			
				Linha l = new Linha(
					linhaIntegration.getIdLinha(),
					linhaIntegration.getCodigo(),
					linhaIntegration.getNome(),
					new String("O")
				);
				
				this.linhaRepository.save(l);
			}
		}
	}

	public LinhaOnibus refreshLinhaOnibus(LinhaOnibus linhaOnibus) {

		Optional<Linha> linhaEncontrada = this.linhaRepository.findByIdLinha(linhaOnibus.getIdLinha());
		Linha l = new Linha();
		l.setTipo(new String("O"));
		
		if (!linhaEncontrada.equals(Optional.empty())) {
			l = linhaEncontrada.get();
		}
			
		l.setIdLinha(linhaOnibus.getIdLinha());
		l.setCodigo(linhaOnibus.getCodigo());
		l.setNome(linhaOnibus.getNome());

		l = this.linhaRepository.save(l);
		
		linhaOnibus.setId(l.getId());
		
		return linhaOnibus;
	}

}
