package ru.sinara.messageservicebackend.service.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.entity.RegistrationEntity;
import ru.sinara.messageservicebackend.model.mapper.RegistrationMapper;
import ru.sinara.messageservicebackend.repository.RegistrationRepository;
import ru.sinara.messageservicebackend.service.kafka.KafkaProducerServiceImpl;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final KafkaProducerServiceImpl producerService;
    private final RegistrationMapper mapper;

    /**
     * Метод сохранения запроса на регистрацию, а также отправления на сервис подтверждения
     * @param dto - тело запроса на регистрацию
     * @return - возвращает ID-запроса
     */
    @Override
    public UUID createRegistration(RegistrationRequestDto dto) {
        producerService.send(dto);
        RegistrationEntity registrationEntity = mapper.RegistrationRequestDtoToRegistrationEntity(dto);
        registrationRepository.save(registrationEntity);
        return registrationEntity.getId();
    }

}
