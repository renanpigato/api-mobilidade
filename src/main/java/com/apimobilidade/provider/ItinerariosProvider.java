package com.apimobilidade.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.collection.Itinerarios;
import com.apimobilidade.entity.Itinerario;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.ItinerarioRepository;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.LinhaOnibus;

public class ItinerariosProvider {
	
	private LinhaRepository linhaRepository; 
	private ItinerarioRepository itinerarioRepository;
	private Long idLinha;
	
	private Itinerarios itinerarios = new Itinerarios();
	private Double latitude = Double.valueOf(0);
	private Double longitude = Double.valueOf(0);
	
	/**
	 * @param itinerarioRepository
	 * @param idLinha
	 */
	public ItinerariosProvider(
		LinhaRepository linhaRepository,
		ItinerarioRepository itinerarioRepository,
		Long idLinha
	) {
		super();
		this.linhaRepository = linhaRepository;
		this.itinerarioRepository = itinerarioRepository;
		this.idLinha = idLinha;
	}

	/**
	 * @return the itinerarios
	 */
	public Itinerarios getItinerarios() {
		return itinerarios;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public boolean isEmpty() {
		return itinerarios.isEmpty();
	}
	
	public void run() {
			
		Optional<Linha> linhaOnibus = this.linhaRepository.findByIdLinha(this.idLinha);
		
		if (linhaOnibus.equals(Optional.empty())) {
			return;
		}
		
		Long linhaId = linhaOnibus.get().getId();
		List<Itinerario> itinerarios = this.itinerarioRepository.findAllByLinhaId(linhaId);
		
		if (itinerarios.size() == 0) {
			return;
		}
			
		Iterator<Itinerario> itinerariosIt = itinerarios.iterator();
		
		ArrayList<Localizacao> localizacoes = new ArrayList<Localizacao>();
		Itinerario itinerario = new Itinerario();
		
		while (itinerariosIt.hasNext()) {
			
			itinerario = (Itinerario) itinerariosIt.next();
			
			localizacoes.add(
				new Localizacao(
					itinerario.getLatitude(),
					itinerario.getLongitude()
				)
			);
		}
		
		this.itinerarios.add(
			new com.apimobilidade.resources.Itinerario(
				new LinhaOnibus(
					itinerario.getLinha().getIdLinha(),
					itinerario.getLinha().getCodigo(),
					itinerario.getLinha().getNome()
				),
				localizacoes
			)
		);
	}
}
