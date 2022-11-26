package app.facade;

import app.persistence.entity.provider.Provider;
import app.web.dto.request.ProviderRequestDto;
import app.web.dto.response.ProviderResponseDto;

import java.util.List;

public interface ProviderFacade extends CrudFacade<ProviderRequestDto, ProviderResponseDto> {

    List<Provider> findAll();
}
