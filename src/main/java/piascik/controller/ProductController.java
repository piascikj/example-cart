package piascik.controller;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import piascik.domain.Cart;
import piascik.domain.Product;
import piascik.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/product")
    public @ResponseBody List<Product> getProducts() {
        return repository.findAll();
    }

}
