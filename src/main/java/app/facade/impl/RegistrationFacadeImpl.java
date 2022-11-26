package app.facade.impl;

import org.springframework.stereotype.Service;
import app.facade.RegistrationFacade;
import app.persistence.entity.user.Personal;
import app.service.PersonalCrudService;
import app.web.dto.request.register.AuthDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalCrudService personalService;

    public RegistrationFacadeImpl(PersonalCrudService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        Personal personal = new Personal();
        System.out.println(dto.getFirstName());
        personal.setFirstName(dto.getFirstName());
        personal.setLastName(dto.getLastName());
        personal.setEmail(dto.getEmail());
        personal.setPassword(dto.getPassword());
        personalService.create(personal);
    }
}
