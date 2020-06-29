package com.apimobilidade.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.collection.Itinerarios;
import com.apimobilidade.entity.Itinerario;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.ItinerarioRepository;
import com.apimobilidade.resources.LinhaOnibus;

public class ItinerariosProvider {
	
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
		ItinerarioRepository itinerarioRepository,
		Long idLinha
	) {
		super();
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
		
		Iterator<Itinerario> itinerariosIt;	
		itinerariosIt = this.itinerarioRepository.findAllByLinha(new Linha(idLinha)).iterator();
		
		List<Localizacao> localizacoes = new ArrayList<Localizacao>();
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
