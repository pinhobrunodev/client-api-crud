package com.pinhobrunodev.clientcrudapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pinhobrunodev.clientcrudapi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByNameAndDate(String name, LocalDate date);
	
	@Query("SELECT client FROM Client client WHERE client.email = :email")
	Optional<Client> findByEmail(String email);
	
	@Query("SELECT client FROM Client client WHERE client.name = :name AND client.date = :date AND client.id <> :id")
	Optional<Client> findByClientUpdate(String name, LocalDate date, Long id);

}
