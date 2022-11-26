package app.facade;

import org.springframework.web.context.request.WebRequest;
import app.web.dto.response.ServerPLPDto;

import java.util.List;

public interface PLPFacade {

    List<ServerPLPDto> search(WebRequest webRequest);
}
