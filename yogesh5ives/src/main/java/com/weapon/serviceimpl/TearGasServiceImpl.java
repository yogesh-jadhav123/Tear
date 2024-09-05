package com.weapon.serviceimpl;

import com.weapon.dto.TearGasDTO;
import com.weapon.model.TearGasModel;
import com.weapon.repository.TearGasRepository;
import com.weapon.service.TearGasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TearGasServiceImpl implements TearGasService {

    @Autowired
    private TearGasRepository tearGasRepository;

    @Override
    public TearGasDTO saveTearGas(TearGasDTO tearGasDTO) {
        TearGasModel model = convertToEntity(tearGasDTO);
        model = tearGasRepository.save(model);
        return convertToDTO(model);
    }

    @Override
    public TearGasDTO updateTearGas(Long id, TearGasDTO tearGasDTO) {
        TearGasModel model = tearGasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tear Gas not found"));
        model.setWeaponName(tearGasDTO.getWeaponName());
        model.setDate(tearGasDTO.getDate());
        model.setRemark(tearGasDTO.getRemark());
        model.setWeaponCount(tearGasDTO.getWeaponCount());
        model = tearGasRepository.save(model);
        return convertToDTO(model);
    }

    @Override
    public void deleteTearGas(Long id) {
        tearGasRepository.deleteById(id);
    }

    @Override
    public List<TearGasDTO> getAllTearGas() {
        return tearGasRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TearGasDTO getTearGasById(Long id) {
        TearGasModel model = tearGasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tear Gas not found"));
        return convertToDTO(model);
    }

    private TearGasModel convertToEntity(TearGasDTO tearGasDTO) {
        TearGasModel model = new TearGasModel();
        model.setWeaponName(tearGasDTO.getWeaponName());
        model.setDate(tearGasDTO.getDate());
        model.setRemark(tearGasDTO.getRemark());
        model.setWeaponCount(tearGasDTO.getWeaponCount());
        return model;
    }

    private TearGasDTO convertToDTO(TearGasModel tearGasModel) {
        TearGasDTO dto = new TearGasDTO();
        dto.setId(tearGasModel.getId());
        dto.setWeaponName(tearGasModel.getWeaponName());
        dto.setDate(tearGasModel.getDate());
        dto.setRemark(tearGasModel.getRemark());
        dto.setWeaponCount(tearGasModel.getWeaponCount());
        return dto;
    }
}