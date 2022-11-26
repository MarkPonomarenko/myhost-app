package app.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import app.facade.SearchServerFacade;
import app.facade.ServerFacade;
import app.persistence.datatable.DataTableRequest;
import app.persistence.datatable.DataTableResponse;
import app.persistence.entity.server.Server;
import app.persistence.repository.server.ServerRepository;
import app.service.ProviderService;
import app.service.ServerService;
import app.persistence.datatable.util.WebUtil;
import app.web.dto.request.ServerRequestDto;
import app.web.dto.response.PageData;
import app.web.dto.response.ServerResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServerFacadeImpl implements ServerFacade {

    private final ServerService serverService;
    private final ProviderService providerService;
    private final SearchServerFacade serverFacade;
    private final ServerRepository serverRepository;


    public ServerFacadeImpl(ServerService serverService, ProviderService providerService, SearchServerFacade serverFacade, ServerRepository serverRepository) {
        this.serverService = serverService;
        this.providerService = providerService;
        this.serverFacade = serverFacade;
        this.serverRepository = serverRepository;
    }

    @Override
    public void create(ServerRequestDto serverRequestDto) {
        Server server = new Server();
        server.setServerName(serverRequestDto.getServerName());
        server.setRam(serverRequestDto.getRam());
        server.setCpuModel(serverRequestDto.getCpuModel());
        server.setCpuSeries(serverRequestDto.getCpuSeries());
        server.setPrice(serverRequestDto.getPrice());
        server.setProvider(providerService.findById(serverRequestDto.getProviderId()).get());
        serverService.create(server);
    }

    @Override
    public void update(ServerRequestDto serverRequestDto, Long id) {
        Optional<Server> optionalServer = serverService.findById(id);
        if (optionalServer.isPresent()) {
            Server server = optionalServer.get();
            server.setServerName(serverRequestDto.getServerName());
            server.setRam(serverRequestDto.getRam());
            server.setCpuModel(serverRequestDto.getCpuModel());
            server.setCpuSeries(serverRequestDto.getCpuSeries());
            server.setPrice(serverRequestDto.getPrice());
            //server.setProvider(serverRequestDto.getProvider());
            serverService.update(server);
        }
    }

    @Override
    public void delete(Long id) {
        serverService.delete(id);
    }

    @Override
    public ServerResponseDto findById(Long id) {
        Server server = serverService.findById(id).get();
        return new ServerResponseDto(server);
    }

    @Override
    public PageData<ServerResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Server> tableResponse = serverService.findAll(dataTableRequest);
        List<ServerResponseDto> servers = tableResponse.getItems().stream().
                map(ServerResponseDto::new).
                collect(Collectors.toList());

        PageData<ServerResponseDto> pageData = (PageData<ServerResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(servers);
        return pageData;
    }
//
//    @Override
//    public List<ServerResponseDto> search(WebRequest request) {
//        Map<String, Object> queryMap = new HashMap<>();
//        if (request.getParameterMap().get(WebUtil.SERVER_SEARCH_PARAM) != null) {
//            String[] params = request.getParameterMap().get(WebUtil.SERVER_SEARCH_PARAM);
//            String doctorName = params[0];
//            queryMap.put(WebUtil.SERVER_SEARCH_PARAM, doctorName);
//        }
//        if (queryMap.get(WebUtil.SERVER_SEARCH_PARAM) != null) {
//            String cpuModel = (String) queryMap.get(WebUtil.SERVER_SEARCH_PARAM);
//        }
//        List<DoctorResponseDto> doctorPLPDtos = doctors.stream().map(DoctorResponseDto::new).toList();
//        return doctorPLPDtos;
//    }
}
