package app.facade;

import app.web.dto.request.register.AuthDto;
import app.web.dto.response.PersonalResponseDto;

public interface PersonalFacade extends CrudFacade<AuthDto, PersonalResponseDto> {

}
