package ru.sinara.messageservicebackend.service.registration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinara.messageservicebackend.model.entity.RegistrationEntity;
import ru.sinara.messageservicebackend.model.mapper.RegistrationMapper;
import ru.sinara.messageservicebackend.repository.RegistrationRepository;
import ru.sinara.messageservicebackend.service.kafka.KafkaProducerServiceImpl;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationRepositoryTest {
    @Mock
    RegistrationRepository registrationRepository;

    @Test
    void repositoryTest() {
        RegistrationEntity entity = new RegistrationEntity();
        entity.setId(UUID.randomUUID());
        entity.setEmail("test");
        entity.setLogin("test");
        entity.setPassword("test");
        entity.setLastName("test");
        entity.setFirstName("test");
        entity.setMiddleName("test");
        when(registrationRepository.save(any(RegistrationEntity.class))).thenReturn(entity);
        RegistrationEntity savedEntity = registrationRepository.save(entity);
        Assertions.assertNotNull(savedEntity);
        Assertions.assertEquals(entity.getId(), savedEntity.getId());
        Assertions.assertEquals(entity.getEmail(), savedEntity.getEmail());
        Assertions.assertEquals(entity.getLogin(), savedEntity.getLogin());
        Assertions.assertEquals(entity.getPassword(), savedEntity.getPassword());
        Assertions.assertEquals(entity.getLastName(), savedEntity.getLastName());
        Assertions.assertEquals(entity.getFirstName(), savedEntity.getFirstName());
        Assertions.assertEquals(entity.getMiddleName(), savedEntity.getMiddleName());

    }


}
