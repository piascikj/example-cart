package piascik.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import piascik.domain.Product;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */
public interface ProductRepository extends MongoRepository<Product, String> {}
