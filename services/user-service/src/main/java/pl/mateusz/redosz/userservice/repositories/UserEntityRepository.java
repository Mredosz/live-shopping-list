package pl.mateusz.redosz.userservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mateusz.redosz.userservice.entities.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
