package piascik.repository;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */
import piascik.domain.Cart;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
     public Cart findByOwner(String owner);
}