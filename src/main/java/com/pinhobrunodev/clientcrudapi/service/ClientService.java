package com.pinhobrunodev.clientcrudapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinhobrunodev.clientcrudapi.exceptions.BusinessException;
import com.pinhobrunodev.clientcrudapi.exceptions.NotFoundException;
import com.pinhobrunodev.clientcrudapi.mapper.ClientMapper;
import com.pinhobrunodev.clientcrudapi.model.Client;
import com.pinhobrunodev.clientcrudapi.model.dto.ClientDTO;
import com.pinhobrunodev.clientcrudapi.repository.ClientRepository;
import com.pinhobrunodev.clientcrudapi.utils.MessageUtils;


@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	@Autowired
	private ClientMapper mapper;
	
	@Transactional
	public ClientDTO save(@Valid ClientDTO dto) {
		Optional<Client> optional = repository.findByNameAndDate(dto.getName(),dto.getDate());
		Optional<Client> optional2 = repository.findByEmail(dto.getEmail());
		if(optional2.isPresent()) {
			throw new BusinessException(MessageUtils.EMAIL_ALREADY_EXISTS);
		}
		if(optional.isPresent()) {
			throw new BusinessException(MessageUtils.CLIENT_ALREADY_EXISTS); 
		}
		Client client = mapper.toEntity(dto);
		repository.save(client);
		return mapper.toDto(client);
	}

	@Transactional
	public ClientDTO update(@Valid ClientDTO dto) {
		Optional<Client> optional = repository.findByClientUpdate(dto.getName(),dto.getDate(),dto.getId());
		if(optional.isPresent()) {
			throw new BusinessException(MessageUtils.CLIENT_ALREADY_EXISTS); 
		}
		Client client = mapper.toEntity(dto);
		repository.save(client);
		return mapper.toDto(client);
	}
	@Transactional
	public ClientDTO remove(Long id) {
	ClientDTO dto = this.findById(id);
	if(dto == null){
		throw new NotFoundException();
	}
	 repository.deleteById(dto.getId());
	 return dto;
	}
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(){
		return mapper.toDto(repository.findAll());
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
	return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
	}
	


}
