package app.facade;

import app.web.dto.request.ServerRequestDto;
import app.web.dto.response.ServerResponseDto;

public interface ServerFacade extends CrudFacade<ServerRequestDto, ServerResponseDto> {

//    List<ServerResponseDto> search(WebRequest request);
}
