package com.apimobilidade.provider;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Pattern;

import com.apimobilidade.classes.Localizacao;
import com.apimobilidade.collection.PontosTaxi;
import com.apimobilidade.resources.PontoTaxi;

public class PontoTaxiProvider {
	
	private final String PATH_FILE = "/tmp/taxi";
	private Long LAST_ID = new Long(0);
	private PontosTaxi ptosTaxi = new PontosTaxi();

	/**
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * 
	 */
	public PontoTaxiProvider() {
		super();
		this.init();
	}

	private void init() {
		
		ArrayList<String> content = new ArrayList<String>();
		
		try {
			
			BufferedReader bfReader = new BufferedReader(new FileReader(PATH_FILE));
			
			while (bfReader.ready()) {
				content.add(bfReader.readLine());
			}
			bfReader.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Iterator<String> it = content.iterator();
		String regex = "LAST_ID=(\\d+)";
		
		while (it.hasNext()) {
			
			String linha = (String) it.next();
			
			if (linha.equals(PontoTaxi.HEADER)) {
				continue;
			}
			
			if (Pattern.compile(regex).matcher(linha).matches()) {
				LAST_ID = Long.getLong(linha.replaceAll(regex, "$1"));
				continue;
			}
			
			String atributos[] = linha.split("#");
			SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
			
			try {
				
				PontoTaxi pontoTaxi = new PontoTaxi(
					atributos[0],
					new Localizacao(
						new Double(atributos[1]),
						new Double(atributos[2])
					),
					formatData.parse(atributos[3])
				);
				pontoTaxi.setId(++LAST_ID);
				this.ptosTaxi.add(pontoTaxi);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public PontosTaxi getPontosTaxi() {
		return this.ptosTaxi;
	}

	public Optional<PontoTaxi> getPontoByNome(String nomePonto) {
		
		Iterator<PontoTaxi> iterator = this.ptosTaxi.iterator();
		
		while (iterator.hasNext()) {
			
			PontoTaxi pontoTaxi = (PontoTaxi) iterator.next();
			
			if (pontoTaxi.getNomePonto().equals(nomePonto)) {
				return Optional.of(pontoTaxi);
			}
		}		
		
		return Optional.empty();
	}

	public PontoTaxi save(PontoTaxi pontoTaxiRecebido) {
		
		this.ptosTaxi.add(pontoTaxiRecebido);
		
		this.persist();
		
		return pontoTaxiRecebido;
	}

	public Long getNextId() {
		return ++LAST_ID;
	}
	
	private void persist() {
		
		String text = new String(PontoTaxi.HEADER);
		text += "\n";
		
		Iterator<PontoTaxi> it = this.ptosTaxi.iterator();
		
		while (it.hasNext()) {
			PontoTaxi pontoTaxi = (PontoTaxi) it.next();
			text += pontoTaxi.toString();
			text += "\n";
		}
		
		text += "LAST_ID=" + LAST_ID;
		text += "\n";
		
		String filename = new String(PATH_FILE);
		
		try {
			
			FileOutputStream f = new FileOutputStream(filename, true);
			
			byte[] bts = text.getBytes();
			
			f.write(bts);
			f.flush();
			f.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
