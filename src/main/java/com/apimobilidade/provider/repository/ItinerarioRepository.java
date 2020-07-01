package com.apimobilidade.provider.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apimobilidade.entity.Itinerario;

public interface ItinerarioRepository extends PagingAndSortingRepository<Itinerario, Integer> {

	List<Itinerario> findAllByLinhaId(long linha_id);
	
	List<Itinerario> findAllByLatitudeAndLongitude(double latitude, double longitude);
	
	Optional<Itinerario> findByLatitudeAndLongitudeAndLinhaId(Double latitude, Double longitude, Long linha_id);
}
