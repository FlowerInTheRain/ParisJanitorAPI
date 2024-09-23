package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.service.files.FilesManagement;
import fr.jypast.parisjanitorapi.domain.port.in.user.UserDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserDeleterService implements UserDeleterApi {

    private final UserPersistenceSpi spi;
    private final FilesManagement filesManagement;
    @Override
    public void deleteById(UUID id) {
        filesManagement.deleteContainer(id.toString());
        spi.deleteById(id);
    }

    @Override
    public void deleteByToken(UUID token) {
        spi.deleteByToken(token);
    }
}
