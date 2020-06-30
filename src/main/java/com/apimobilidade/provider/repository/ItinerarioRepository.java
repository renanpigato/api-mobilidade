package com.apimobilidade.provider.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apimobilidade.entity.Itinerario;

public interface ItinerarioRepository extends PagingAndSortingRepository<Itinerario, Integer> {

	List<Itinerario> findAllByLinhaId(long linha_id);
}
