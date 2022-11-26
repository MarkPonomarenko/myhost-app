package app.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import app.config.annotations.InjectLog;
import app.facade.PLPFacade;
import app.logger.LoggerLevel;
import app.logger.LoggerService;
import app.persistence.entity.server.Server;
import app.service.PLPService;
import app.persistence.datatable.util.WebUtil;
import app.web.dto.response.ServerPLPDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PLPFacadeImpl implements PLPFacade {

    @InjectLog
    private LoggerService loggerService;

    private final PLPService plpService;

    public PLPFacadeImpl(PLPService plpService) {
        this.plpService = plpService;
    }

    @Override
    public List<ServerPLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        if (webRequest.getParameterMap().get(WebUtil.PROVIDER_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.PROVIDER_PARAM);
            Long providerId = Long.parseLong(params[0]);
            queryMap.put(WebUtil.PROVIDER_PARAM, providerId);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.PROVIDER_PARAM + ": " + providerId);
        }
        if (webRequest.getParameterMap().get(WebUtil.SERVER_SEARCH_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.SERVER_SEARCH_PARAM);
            String serverName = params[0];
            queryMap.put(WebUtil.SERVER_SEARCH_PARAM, serverName);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.SERVER_SEARCH_PARAM + ": " + serverName);
        }
        List<Server> servers = plpService.search(queryMap);
        List<ServerPLPDto> serverPLPDtos = servers.stream().map(ServerPLPDto::new).toList();
        return serverPLPDtos;
    }
}
