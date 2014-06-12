package piascik;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import piascik.domain.Product;
import piascik.repository.CartRepository;
import piascik.repository.ProductRepository;

@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        productRepository.save(new Product("cup", "A cup for your best ale", 1000));
        productRepository.save(new Product("plate", "It doubles as a flying disk", 1500));
        productRepository.save(new Product("spoon", "Great for soup", 250));
        productRepository.save(new Product("fork", "It's forking nice", 250));
        productRepository.save(new Product("knife", "Now that's a knife", 250));
    }
}