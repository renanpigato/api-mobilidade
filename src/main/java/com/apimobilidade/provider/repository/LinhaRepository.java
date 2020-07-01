package com.apimobilidade.provider.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apimobilidade.entity.Linha;

public interface LinhaRepository extends PagingAndSortingRepository<Linha, Long> {
	
	Optional<Linha> findByIdLinha(Long idLinha);
	
	Iterable<Linha> findAllByNome(String nome);
}
