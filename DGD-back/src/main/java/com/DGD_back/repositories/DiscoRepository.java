package com.DGD_back.repositories;

import com.DGD_back.entities.Disco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {
}
