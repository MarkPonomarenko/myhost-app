package app.facade;

import org.springframework.web.context.request.WebRequest;
import app.web.dto.request.RequestDto;
import app.web.dto.response.PageData;
import app.web.dto.response.ResponseDto;

public interface CrudFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RES findById(Long id);

    PageData<RES> findAll(WebRequest request);
}
