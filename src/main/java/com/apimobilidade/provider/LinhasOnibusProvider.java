package com.apimobilidade.provider;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.collection.LinhasOnibus;
import com.apimobilidade.entity.Itinerario;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.ItinerarioRepository;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.LinhaOnibus;

public class LinhasOnibusProvider {

	private LinhasOnibus linhasOnibus = new LinhasOnibus();
	private String filtroNome = new String("");
	private double raio = 0;
	private Localizacao localizacao = new Localizacao();
	private LinhaRepository linhaRepository;
	private ItinerarioRepository itinerarioRepository;
	
	/**
	 * @param linhaRepository
	 */
	public LinhasOnibusProvider(
		LinhaRepository linhaRepository,
		ItinerarioRepository itinerarioRepository
	) {
		super();
		this.linhaRepository = linhaRepository;
		this.itinerarioRepository = itinerarioRepository;
	}

	/**
	 * @return the linhasOnibus
	 */
	public Collection<LinhaOnibus> getLinhasOnibus() {
		return this.linhasOnibus.values();
	}

	public boolean isEmpty() {
		return this.linhasOnibus.isEmpty();
	}
	
	public void setFiltroNome(String nome) {
		this.filtroNome = nome;
	}
	
	public void setFiltroRaio(double raio) {
		this.raio = raio;
	}

	public void setFiltroLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
	public void run() {
		
		Iterable<Linha> linhasDatabaseIt;
		
		if (!this.filtroNome.isEmpty()) {
			linhasDatabaseIt = this.linhaRepository.findAllByNome(this.filtroNome);
		} else if (this.raio > 0) {
			
			this.localizacao.calcularRaioAtuacao(this.raio * 1000);
			
			
			List<Itinerario> itinerarios = this.itinerarioRepository
				.findAllByLatitudeBetweenOrLongitudeBetween(
					this.localizacao.getMinimaLatitude(),
					this.localizacao.getMaximaLatitude(),
					this.localizacao.getMinimaLongitude(),
					this.localizacao.getMaximaLongitude()
			);
			
			Iterator<Itinerario> iteratorItinerario =  itinerarios.iterator();
			
			while (iteratorItinerario.hasNext()) {
				
				Itinerario itinerario = (Itinerario) iteratorItinerario.next();
				
				if (!this.linhasOnibus.containsKey(itinerario.getLinha().getId())) {
					this.linhasOnibus.put(
						itinerario.getLinha().getId(),
						new LinhaOnibus(
							itinerario.getLinha().getId(),
							itinerario.getLinha().getIdLinha(),
							itinerario.getLinha().getCodigo(),
							itinerario.getLinha().getNome()
						)
					);
				}
			}
			return;
			
		} else {
			linhasDatabaseIt = this.linhaRepository.findAll();
		}
		
		Iterator<Linha> iteratorLinhas = linhasDatabaseIt.iterator();
		
		while (iteratorLinhas.hasNext()) {
			
			Linha linhaOnibus = (Linha) iteratorLinhas.next();
			
			if (linhaOnibus.getTipo().equals(new String("O"))) {
				this.linhasOnibus.put(
					linhaOnibus.getId(),
					new LinhaOnibus(
						linhaOnibus.getId(),
						linhaOnibus.getIdLinha(),
						linhaOnibus.getCodigo(),
						linhaOnibus.getNome()
					)
				);
			}
		}
	}

}
