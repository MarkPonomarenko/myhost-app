package app.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import app.facade.ProviderFacade;
import app.persistence.datatable.DataTableRequest;
import app.persistence.datatable.DataTableResponse;
import app.persistence.entity.provider.Provider;
import app.service.ProviderService;
import app.persistence.datatable.util.WebUtil;
import app.web.dto.request.ProviderRequestDto;
import app.web.dto.response.PageData;
import app.web.dto.response.ProviderResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProviderFacadeImpl implements ProviderFacade {

    private final ProviderService providerService;

    public ProviderFacadeImpl(ProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public void create(ProviderRequestDto providerRequestDto) {
        Provider provider = new Provider();
        provider.setName(providerRequestDto.getName());
        provider.setCountry(providerRequestDto.getCountry());
        providerService.create(provider);
    }

    @Override
    public void update(ProviderRequestDto providerRequestDto, Long id) {
        Optional<Provider> optionalProvider = providerService.findById(id);
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            provider.setName(providerRequestDto.getName());
            provider.setCountry(providerRequestDto.getCountry());
            providerService.update(provider);
        }
    }

    @Override
    public void delete(Long id) {
        providerService.delete(id);
    }

    @Override
    public ProviderResponseDto findById(Long id) {
        Provider provider = providerService.findById(id).get();
        return new ProviderResponseDto(provider);
    }

    @Override
    public PageData<ProviderResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Provider> tableResponse = providerService.findAll(dataTableRequest);
        List<ProviderResponseDto> servers = tableResponse.getItems().stream().
                map(ProviderResponseDto::new).
                collect(Collectors.toList());

        PageData<ProviderResponseDto> pageData = (PageData<ProviderResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(servers);
        return pageData;
    }

    @Override
    public List<Provider> findAll() {
        return providerService.findAll();
    }
}
