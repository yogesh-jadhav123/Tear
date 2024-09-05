package com.weapon.service;

import com.weapon.dto.TearGasDTO;

import java.util.List;

public interface TearGasService {
    TearGasDTO saveTearGas(TearGasDTO tearGasDTO);
    TearGasDTO updateTearGas(Long id, TearGasDTO tearGasDTO);
    void deleteTearGas(Long id);
    List<TearGasDTO> getAllTearGas();
    TearGasDTO getTearGasById(Long id);
}