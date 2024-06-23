package ru.sinara.messageservicebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sinara.messageservicebackend.model.entity.RegistrationEntity;

import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, UUID> {
}
