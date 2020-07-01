package com.apimobilidade.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.ItinerarioRepository;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.Itinerario;

public class ItinerariosService {
	
	private LinhaRepository linhaRepository;
	private ItinerarioRepository itinerarioRepository;

	public ItinerariosService(
		LinhaRepository linhaRepository,
		ItinerarioRepository itinerarioRepository
	) {
		this.linhaRepository = linhaRepository;
		this.itinerarioRepository = itinerarioRepository;
	}

	public void refreshItinerariosWithIntegration(String itinerarios) {
		System.out.println(itinerarios);
	}
	
	public Itinerario refreshItinerario(Itinerario itinerario) {
		
		Optional<Linha> linha = this.linhaRepository.findByIdLinha(itinerario.getLinha().getIdLinha());
		Linha linhaEntidade = new Linha();
		
		if (linha.equals(Optional.empty())) {
			
			Linha l = new Linha(
				itinerario.getLinha().getIdLinha(),
				itinerario.getLinha().getCodigo(),
				itinerario.getLinha().getNome(),
				"O"
			);
			
			linhaEntidade = this.linhaRepository.save(l);
		} else {
			linhaEntidade = linha.get();
		}
		Long linha_id = linhaEntidade.getId(); 
		
		List<Localizacao> localizacoes = itinerario.getLocalizacoes();
		Iterator<Localizacao> iteratorLocalizacoes = localizacoes.iterator();
		
		itinerario.getLinha().setId(linhaEntidade.getId());
		
		while (iteratorLocalizacoes.hasNext()) {
			
			Localizacao localizacao = (Localizacao) iteratorLocalizacoes.next();
			
			Optional<com.apimobilidade.entity.Itinerario> itinerarioEntity = this.itinerarioRepository
				.findByLatitudeAndLongitudeAndLinhaId(
					localizacao.getLatitude(),
					localizacao.getLongitude(),
					linha_id
				);
			
			if(itinerarioEntity.equals(Optional.empty())) {
				
				com.apimobilidade.entity.Itinerario itiEnt = new com.apimobilidade.entity.Itinerario(
					linhaEntidade,
					localizacao.getLatitude(),
					localizacao.getLongitude()
				);
				
				this.itinerarioRepository.save(itiEnt);
			}
		}
		
		return itinerario;
	}
}
