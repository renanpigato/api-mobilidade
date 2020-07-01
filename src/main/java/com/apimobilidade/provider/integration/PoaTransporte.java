package com.apimobilidade.provider.integration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.apimobilidade.classes.ListaItinerarioPoaTransporte;
import com.apimobilidade.collection.LinhasOnibus;
import com.apimobilidade.resources.LinhaOnibus;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PoaTransporte {
	
	private final String URL_LINHAS = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%";
	private final String URL_ITINERARIOS = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=%s";
	private final String URL_LINHAS_ONIBUS = URL_LINHAS + "&t=o";
	private final String URL_LINHAS_LOTACAO = URL_LINHAS + "&t=l";
	
	private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
	private LinhaOnibus linhaOnibus;
	
	public LinhasOnibus getLinhasOnibus() {
		
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		HttpEntity<String> response = restTemplate.exchange(
			URL_LINHAS_ONIBUS,
			HttpMethod.GET,
			null,
			String.class
		);
	
		String linhasOnibusString = response.getBody();
		
		try {
			
			ObjectMapper objMapper = new ObjectMapper();
			LinhasOnibus linhas = objMapper.readValue(
				linhasOnibusString,
				LinhasOnibus.class
			);
			
			return linhas;
			
		} catch (Exception e) {
			return new LinhasOnibus();
		}
	}
	
	public ListaItinerarioPoaTransporte getItinerarios(Long idLinha) {
		
		String url = String.format(URL_ITINERARIOS, idLinha.toString());
		
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		HttpEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				String.class
		);
		
		ObjectMapper objMapper = new ObjectMapper();
	
		String itinerariosString = new String("[");
		itinerariosString += response.getBody()
			.replaceAll(".*(\"0\":.*)", "$1")
			.replaceAll("\"\\d+\":", "")
			.replaceAll("}$", "");
		itinerariosString += "]";
		
		ListaItinerarioPoaTransporte itinerarios;
		
		try {
			String linhaString = "{";
			linhaString += response.getBody().replace("idlinha", "idLinha").replaceAll("\\{(.*),\"0\":.*", "$1");
			linhaString += "}";
			
			this.linhaOnibus = objMapper.readValue(
				linhaString,
				LinhaOnibus.class
			);
			
			itinerarios = objMapper.readValue(
				itinerariosString,
				ListaItinerarioPoaTransporte.class
			);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ListaItinerarioPoaTransporte();
		}
		
		return itinerarios;
	}

	public LinhaOnibus getLinha() {
		return this.linhaOnibus;
	}
}
