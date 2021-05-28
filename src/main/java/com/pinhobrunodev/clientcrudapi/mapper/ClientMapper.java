package com.pinhobrunodev.clientcrudapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pinhobrunodev.clientcrudapi.model.Client;
import com.pinhobrunodev.clientcrudapi.model.dto.ClientDTO;

@Component
public class ClientMapper {

	public Client toEntity(ClientDTO dto) {
		Client client = new Client();
		client.setId(dto.getId());
		client.setName(dto.getName());
		client.setEmail(dto.getEmail());
		client.setDate(dto.getDate());
		client.setPassword(dto.getPassword());
		return client;
	}

	public ClientDTO toDto(Client client) {
		ClientDTO dto = new ClientDTO();
		dto.setId(client.getId());
		dto.setName(client.getName());
		dto.setEmail(client.getEmail());
		dto.setDate(client.getDate());
		return dto;
	}
	
	public List<ClientDTO> toDto(List<Client> client){
		return client.stream().map(this::toDto).collect(Collectors.toList());
	}

}
