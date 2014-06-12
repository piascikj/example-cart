$(document).ready(function() {
  var products = [];
  getProducts(showProducts);

  // Listen for add click
  $('#products').on('click', '.js-add-btn', function(evt) {
    var $product = $(this).closest('.js-product');
    var product = $product.attr('data-id');
    var quantity = $product.find('.js-quantity option:selected').val();

    getCart(function(cart) {
      addToCart(product, quantity, function(cart) {
        debugger;
      });
    });
  });

  function showProducts() {
    var template = _.template( $( ".products-template" ).html() );
    $('#products').html(template({products:products}));
    Holder.add_theme("bright").run();
  }

  function getProducts(cb) {
    $.getJSON('product', function(data) {
      products = data;
      if (_.isFunction(cb)) cb(data);
    })
  }

  function getCart(cb) {
    var url = 'cart/' + getSession();
    $.getJSON(url, function(cart) {
      if (_.isFunction(cb)) cb(cart);
    })
    .fail(function() {
      $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        success: function(cart) {
          if (_.isFunction(cb)) cb(cart);
        }
      });
    });
  }

  function addToCart(product, quantity, cb) {
    var url = 'cart/' + getSession() + '/' + product + '/' + quantity;
    $.ajax({
      url: url,
      type: 'PUT',
      dataType: 'json',
      success: function(cart) {
        if (_.isFunction(cb)) cb(cart);
      }
    });
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

