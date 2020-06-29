package com.apimobilidade.provider.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apimobilidade.entity.Itinerario;
import com.apimobilidade.entity.Linha;

public interface ItinerarioRepository extends PagingAndSortingRepository<Itinerario, Integer> {

	List<Itinerario> findAllByLinha(Linha linha);
}
