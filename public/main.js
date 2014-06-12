$(document).ready(function() {
  var products = [];

  // Get products and show the cart
  getProducts(function(products) {
    showProducts(products);
    getCart(showCart);
  });

  // Listen for add click
  $('#products').on('click', '.js-add-btn', function(evt) {
    var $product = $(this).closest('.js-product');
    var product = $product.attr('data-id');
    var quantity = $product.find('.js-quantity option:selected').val();

    getCart(function(cart) {
      cart.addItem(product, quantity, showCart);
    });
  });

  // Listen for remove click
  $('#cart').on('click', '.js-remove-btn', function(evt) {
    var index = $(this).attr('data-item-index');
    getCart(function(cart) {
      cart.removeItem(index, showCart);
    });
  });

  // Listen for update click
  $('#cart').on('click', '.js-update-btn', function(evt) {
    var index = $(this).attr('data-item-index');
    var $product = $(this).closest('.js-product');
    var quantity = $product.find('.js-quantity option:selected').val();
    getCart(function(cart) {
      cart.updateItem(index, quantity, showCart);
    });
  });

  // Helpers ----------------------------------------------------------------------------------------------------------
  function showProducts(products) {
    var template = _.template( $( ".products-template" ).html() );
    $('#products').html(template({products:products}));
    Holder.add_theme("bright").run();
  }

  function showCart(cart) {
    var template = _.template( $( ".cart-template" ).html() );
    $('#cart').html(template({cart:cart}))
  }

  function getProducts(cb) {
    $.getJSON('product', function(data) {
      products = data;
      if (_.isFunction(cb)) cb(data);
    });
  }

  function getCart(cb) {
    var url = 'cart/' + getSession();
    $.getJSON(url, function(cart) {
      extendCart(cart);
      if (_.isFunction(cb)) cb(cart);
    })
    .fail(function() {
      $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        success: function(cart) {
          extendCart(cart)
          if (_.isFunction(cb)) cb(cart);
        }
      });
    });
  }

  function extendCart(cart) {
    cart.itemTotal = function(item) {
      var product = this.itemProduct(item);
      return (product.price*.01*item.quantity);
    };

    cart.itemProduct = function(item) {
      return _.find(products, {id:item.product});
    };

    cart.total = function() {
      var self = this;
      var total = 0;
      _.each(this.items, function(item) {
        total += self.itemTotal(item);
      });

      return total;
    };

    cart.removeItem = function(index, cb) {
      var url = 'cart/' + getSession() + '/' + index;
      $.ajax({
        url: url,
        type: 'DELETE',
        dataType: 'json',
        success: function(cart) {
          extendCart(cart)
          if (_.isFunction(cb)) cb(cart);
        }
      });
    };

    cart.updateItem = function(index, quantity, cb) {
      var url = 'cart/' + getSession() + '/' + index + '/' + quantity;
      $.ajax({
        url: url,
        type: 'PATCH',
        dataType: 'json',
        success: function(cart) {
          extendCart(cart)
          if (_.isFunction(cb)) cb(cart);
        }
      });
    }

    cart.addItem = function(product, quantity, cb) {
       var url = 'cart/' + getSession() + '/' + product + '/' + quantity;
       $.ajax({
         url: url,
         type: 'PUT',
         dataType: 'json',
         success: function(cart) {
           extendCart(cart);
           if (_.isFunction(cb)) cb(cart);
         }
       });
     };

    return cart;
  }

  function getSession() {
    var key = 'cart-session';
    var session = Cookies.get(key);
    return session || Cookies.set(key, random_string(12)).get(key);
  }

  function random_string(size){
    var str = "";
    for (var i = 0; i < size; i++){
      str += random_character();
    }
    return str;
  }

  function random_character() {
    var chars = "0123456789abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ";
    return chars.substr( Math.floor(Math.random() * 62), 1);
  }

});

