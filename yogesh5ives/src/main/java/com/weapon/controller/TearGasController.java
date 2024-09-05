package com.weapon.controller;

import com.weapon.dto.TearGasDTO;
import com.weapon.service.TearGasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teargas")
public class TearGasController {

    @Autowired
    private TearGasService tearGasService;

    @PostMapping("/add")
    public ResponseEntity<String> createTearGas(@RequestBody TearGasDTO tearGasDTO) {
    	try {
    	tearGasService.saveTearGas(tearGasDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully...Id=0\"}");
    	 } catch (IllegalStateException e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
         } catch (Exception ex) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data saved successfully...Id=0\"}" + ex.getMessage());
         }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TearGasDTO> updateTearGas(@PathVariable Long id, @RequestBody TearGasDTO tearGasDTO) {
        TearGasDTO updated = tearGasService.updateTearGas(id, tearGasDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTearGas(@PathVariable Long id) {
        tearGasService.deleteTearGas(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TearGasDTO>> getAllTearGas() {
        List<TearGasDTO> tearGasList = tearGasService.getAllTearGas();
        return ResponseEntity.ok(tearGasList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TearGasDTO> getTearGasById(@PathVariable Long id) {
        TearGasDTO tearGas = tearGasService.getTearGasById(id);
        return ResponseEntity.ok(tearGas);
    }
}