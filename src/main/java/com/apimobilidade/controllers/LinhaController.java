package com.apimobilidade.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.apimobilidade.poatransporte.resources.linha.OnibusCollection;
import com.apimobilidade.resources.linha.Onibus;

@RestController
public class LinhaController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/onibus")
	public Onibus onibus(
		@RequestParam(defaultValue = "", value = "nome") String nome
	) {
		try {
			if(!nome.equals("")) {
				
				if (nome.equals(new String("linha2"))) {
					return new Onibus(counter.incrementAndGet(), 2, "Linha 2");
				}
				
				throw new Exception("Linha não encontrada!");
			}
		} catch (Exception e) {
		}
		
		return new Onibus(counter.incrementAndGet(), 1, "Linha 1");
	}
	
	@GetMapping("/onibus-poa")
	public OnibusCollection onibusPoa() throws RestClientException {
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		HttpEntity<String> response = restTemplate.exchange(
				"http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o",
				HttpMethod.GET,
				null,
				String.class
		);

		String linhasString = response.getBody();
		String[] linhas = linhasString.split("\\},\\{");
		
		OnibusCollection linhasOnibusPoa = new OnibusCollection();
		
		for (int i = 0; i < linhas.length; i++) {
			
			String linha = linhas[i];
			String[] atributos = linha.split(",");
			
			com.apimobilidade.poatransporte.resources.linha.Onibus onibusPoa = new com.apimobilidade.poatransporte.resources.linha.Onibus();
			
			for (int ii = 0; ii < atributos.length; ii++) {
				
				String atributo = atributos[ii];
				String[] chaveValor = atributo.split(":");
				String chave = chaveValor[0].replace("\"", "").replace("[{", "");
				String valor = chaveValor[1].replace("\"", "");
				
				if (chave.equals("id") || chave.equals("[{id")) {
					onibusPoa.setId(Long.valueOf(valor));
				}
				
				if (chave.equals("codigo")) {
					onibusPoa.setCodigo(valor);
				}
				
				if (chave.equals("nome")) {
					onibusPoa.setNome(valor);
				}
			}
			
			linhasOnibusPoa.add(onibusPoa);
		}
		
		return linhasOnibusPoa;
		
//		com.apimobilidade.poatransporte.resources.linha.Onibus onibus = restTemplate.getForObject(
//			"http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o", 
//			com.apimobilidade.poatransporte.resources.linha.Onibus.class
//		);
	}

}
