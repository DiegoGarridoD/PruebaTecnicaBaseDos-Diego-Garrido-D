package com.DGD_back.repositories;

import com.DGD_back.entities.Disco;
import com.DGD_back.entities.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {

    Page<Disco> findByGenero(Genero genero, Pageable pageable);
}
