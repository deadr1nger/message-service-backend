package ru.sinara.messageservicebackend.service.registration;

import liquibase.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.entity.RegistrationEntity;
import ru.sinara.messageservicebackend.model.mapper.RegistrationMapper;
import ru.sinara.messageservicebackend.repository.RegistrationRepository;
import ru.sinara.messageservicebackend.service.kafka.KafkaProducerServiceImpl;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final KafkaProducerServiceImpl producerService;
    private final RegistrationMapper mapper;

    /**
     * Метод сохранения запроса на регистрацию, а также отправления на сервис подтверждения
     * @param dto - тело запроса на регистрацию
     */
    @Override
    @Transactional
    public void createRegistration(RegistrationRequestDto dto) {
        if(registrationRepository.findByLogin(dto.getLogin()).isPresent() || registrationRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already exist");
        }
        dto.setPassword(MD5Util.computeMD5(dto.getPassword()));
        producerService.send(dto);
        RegistrationEntity registrationEntity = mapper.RegistrationRequestDtoToRegistrationEntity(dto);
        registrationRepository.save(registrationEntity);


    }

}
