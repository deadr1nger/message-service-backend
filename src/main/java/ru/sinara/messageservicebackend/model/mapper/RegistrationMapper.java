package ru.sinara.messageservicebackend.model.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;
import ru.sinara.messageservicebackend.model.entity.RegistrationEntity;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegistrationMapper {
    /**
     * Метод маппинга ДТО в Entity
     * @param dto - тело запроса регистрации
     * @return возвращает RegistrationEntity
     */
    RegistrationEntity registrationRequestDtoToRegistrationEntity(RegistrationRequestDto dto);

    RegistrationResponseDto registrationEntityToRegistrationResponseDto (RegistrationEntity entity);

}
