package com.apimobilidade.provider;

import java.util.Iterator;

import com.apimobilidade.collection.LinhasOnibus;
import com.apimobilidade.entity.Linha;
import com.apimobilidade.provider.repository.LinhaRepository;
import com.apimobilidade.resources.LinhaOnibus;

public class LinhasOnibusProvider {

	private LinhasOnibus linhasOnibus = new LinhasOnibus();
	private String filtroNome = new String("");
	private LinhaRepository linhaRepository;
	
	/**
	 * @param linhaRepository
	 */
	public LinhasOnibusProvider(
		LinhaRepository linhaRepository
	) {
		super();
		this.linhaRepository = linhaRepository;
	}

	/**
	 * @return the linhasOnibus
	 */
	public LinhasOnibus getLinhasOnibus() {
		return this.linhasOnibus;
	}

	public boolean isEmpty() {
		return this.linhasOnibus.isEmpty();
	}
	
	public void setFiltroNome(String nome) {
		this.filtroNome = nome;
	}
	
	public void run() {
		
		Iterator<Linha> linhasDatabaseIt;
		
		if (!this.filtroNome.isEmpty()) {
			linhasDatabaseIt = this.linhaRepository.findAllByNome(this.filtroNome).iterator();
		} else {
			linhasDatabaseIt = this.linhaRepository.findAll().iterator();
		}
		
		while (linhasDatabaseIt.hasNext()) {
			
			Linha linhaOnibus = (Linha) linhasDatabaseIt.next();
			
			if (linhaOnibus.getTipo().equals(new String("O"))) {
				this.linhasOnibus.add(
					new LinhaOnibus(
						linhaOnibus.getIdLinha(),
						linhaOnibus.getCodigo(),
						linhaOnibus.getNome()
					)
				);
			}
		}
	}
}
