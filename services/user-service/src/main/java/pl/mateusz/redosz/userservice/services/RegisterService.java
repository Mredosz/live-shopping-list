package pl.mateusz.redosz.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mateusz.redosz.userservice.dtos.UserRegisterDto;
import pl.mateusz.redosz.userservice.exceptions.UsernameTakenException;
import pl.mateusz.redosz.userservice.mappers.UserMapper;
import pl.mateusz.redosz.userservice.repositories.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegisterDto userRegisterDto) throws UsernameTakenException {
        var userFromDb = userEntityRepository.findByUsername(userRegisterDto.username());
        if (userFromDb.isPresent() && userFromDb.get().getUsername().equals(userRegisterDto.username())) {
            throw new UsernameTakenException();
        }
        var user = UserMapper.toEntity(userRegisterDto, passwordEncoder);
        userEntityRepository.save(user);
    }
}
