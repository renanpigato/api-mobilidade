package com.apimobilidade.provider.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apimobilidade.entity.Linha;

public interface LinhaRepository extends PagingAndSortingRepository<Linha, Integer> {
	
	Optional<Linha> findByIdLinha(Long idLinha);
	
	List<Linha> findAllByNome(String nome);
}
