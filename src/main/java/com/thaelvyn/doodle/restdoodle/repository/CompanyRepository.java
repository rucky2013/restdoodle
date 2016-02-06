package com.thaelvyn.doodle.restdoodle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {


}
