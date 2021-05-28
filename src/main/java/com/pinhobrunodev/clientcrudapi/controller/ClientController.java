package com.pinhobrunodev.clientcrudapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinhobrunodev.clientcrudapi.model.dto.ClientDTO;
import com.pinhobrunodev.clientcrudapi.service.ClientService;

@CrossOrigin
@RestController
@RequestMapping(value =  "/client")
public class ClientController {

	
	@Autowired 
	private ClientService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO dto){
		return ResponseEntity.ok(service.save(dto));
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto){
		return ResponseEntity.ok(service.update(dto));
	}
	
	@DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> remove(@PathVariable Long id){
		return ResponseEntity.ok(service.remove(id));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientDTO>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	
	
}
