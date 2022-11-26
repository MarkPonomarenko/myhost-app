package app.facade;

import app.web.dto.request.register.AuthDto;

public interface RegistrationFacade {

    void registration(AuthDto dto);
}
