package com.weapon.repository;

import com.weapon.model.TearGasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TearGasRepository extends JpaRepository<TearGasModel, Long> {
}