package pl.mateusz.redosz.shoppinglistservice.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mateusz.redosz.shoppinglistservice.entities.ShoppingList;


public interface ShoppingListRepository extends MongoRepository<ShoppingList, String> {
    Slice<ShoppingList> findByParticipantUsernamesContains(String username, Pageable pageable);
}
