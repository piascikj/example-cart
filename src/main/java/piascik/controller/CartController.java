package piascik.controller;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import piascik.domain.Cart;
import org.springframework.web.bind.annotation.*;
import piascik.repository.CartRepository;

@Controller
public class CartController {

    @Autowired
    private CartRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/cart/{id}")
    public @ResponseBody Cart getCart(@PathVariable String id) {
        return repository.findByOwner(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cart/{id}")
    public @ResponseBody Cart createCart(@PathVariable String id) {
        return repository.save(new Cart(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cart/{id}/{product}/{quantity}")
    public @ResponseBody Cart addItem(@PathVariable String id, @PathVariable String product,
                                        @PathVariable int quantity) {
        Cart cart = repository.findByOwner(id);
        cart.addItem(product, quantity);
        return repository.save(cart);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/cart/{id}/{index}/{quantity}")
    public @ResponseBody Cart updateItem(@PathVariable String id, @PathVariable int index,
                                         @PathVariable int quantity) {
        Cart cart = repository.findByOwner(id);
        cart.updateItem(index, quantity);
        return repository.save(cart);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cart/{id}/{index}")
    public @ResponseBody Cart deleteItem(@PathVariable String id, @PathVariable int index) {
        Cart cart = repository.findByOwner(id);
        cart.removeItem(index);
        return repository.save(cart);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cart/{id}")
    public @ResponseBody Cart deleteAllItems(@PathVariable String id) {
        Cart cart = repository.findByOwner(id);
        cart.removeAllItems();
        return repository.save(cart);
    }
}
