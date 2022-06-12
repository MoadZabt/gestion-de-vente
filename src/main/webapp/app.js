window.history.replaceState({}, '', ''+window.location.pathname);
var shoppingCart = (function() {
    cart = [];

    // Constructor
    function Item(id, name, price, count, min, max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.min = min;
        this.max = max;
    }

    // Save cart
    function saveCart() {
        sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
    }

    // Load cart
    function loadCart() {
        cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
    }
    if (sessionStorage.getItem("shoppingCart") != null) {
        loadCart();
    }

    var obj = {};

    // Add to cart
    obj.addItemToCart = function(id, name, price, count, min, max) {
        for(var item in cart) {
            if(cart[item].id === id) {
                if(cart[item].count < cart[item].max) {
                    cart[item].count++;
                }
                saveCart();
                return;
            }
        }
        var item = new Item(id, name, price, count, min, max);
        cart.push(item);
        saveCart();
    }
    // Set count from item
    obj.setCountForItem = function(id, count) {
        for(var i in cart) {
            if (cart[i].id === id) {
                cart[i].count = count;
                break;
            }
        }
    };

    obj.getCountForItem = function(id, count) {
        for(var i in cart) {
            if (cart[i].id === id) {
                return cart[i].count;
            }
        }
    };
    // Remove item from cart
    obj.removeItemFromCart = function(id) {
        for(var item in cart) {
            if(cart[item].id === id) {
                cart[item].count --;
                if(cart[item].count === 0) {
                    cart.splice(item, 1);
                }
                break;
            }
        }
        saveCart();
    }

    // Remove all items from cart
    obj.removeItemFromCartAll = function(id) {
        for(var item in cart) {
            if(cart[item].id === id) {
                cart.splice(item, 1);
                break;
            }
        }
        saveCart();
    }

    // Clear cart
    obj.clearCart = function() {
        cart = {};
        saveCart();
    }

    // Count cart
    obj.totalCount = function() {
        var totalCount = 0;
        for(var item in cart) {
            totalCount += cart[item].count;
        }
        return totalCount;
    }

    // Total cart
    obj.totalCart = function() {
        var totalCart = 0;
        for(var item in cart) {
            totalCart += cart[item].price * cart[item].count;
        }
        return Number(totalCart.toFixed(2));
    }

    // List cart
    obj.listCart = function() {
        var cartCopy = [];
        for(i in cart) {
            item = cart[i];
            itemCopy = {};
            for(p in item) {
                itemCopy[p] = item[p];

            }
            itemCopy.total = Number(item.price * item.count).toFixed(2);
            cartCopy.push(itemCopy)
        }
        return cartCopy;
    }

    // cart : Array
    // Item : Object/Class
    // addItemToCart : Function
    // removeItemFromCart : Function
    // removeItemFromCartAll : Function
    // clearCart : Function
    // countCart : Function
    // totalCart : Function
    // listCart : Function
    // saveCart : Function
    // loadCart : Function
    return obj;
})();


$('.add-to-cart').click(function(event) {
    event.preventDefault();
    var id = $(this).data('id');
    var name = $(this).data('name');
    var price = Number($(this).data('price'));
    var min = Number($(this).data('min'));
    var max = Number($(this).data('max'));
    if(max !== 0) {
        shoppingCart.addItemToCart(id, name, price, 1, min, max);
    }
    displayCart();
});

// Clear items
$('.clear-cart').click(function() {
    shoppingCart.clearCart();
    displayCart();
});


function displayCart() {
    var cartArray = shoppingCart.listCart();
    var output = "";
    for(var i in cartArray) {
        output += "<tr><div style='margin-bottom: 5px'>"
            + "<td>" + cartArray[i].id + "</td>"
            + "<td>" + cartArray[i].name + "</td>"
            + "<td>" + cartArray[i].price + "</td>"
            + "<td><div class='input-group' style='display: flex'><button class='minus-item input-group-addon btn btn-primary' data-id=" + cartArray[i].id + ">-</button>"
            + "<input type='number' class='item-count form-control' data-name='" + cartArray[i].name + "' value='" + cartArray[i].count + "'></input>"
            + "<button class='plus-item btn btn-primary input-group-addon' data-id='" + cartArray[i].id + "' data-max='"+ cartArray[i].max +"'>+</button></div></td>"
            + "<td style='display: flex; justify-content: center'><button class='delete-item btn btn-danger' data-id=" + cartArray[i].id + ">X</button></td>"
            + " = "
            + "<td>" + cartArray[i].total + "</td>"
            + "</div></tr>";
    }
    $('.show-cart').html(output);
    $('.total-cart').html(shoppingCart.totalCart());
    $('.total-count').html(shoppingCart.totalCount());
}

// Delete item button

$('.show-cart').on("click", ".delete-item", function(event) {
    var id = $(this).data('id')
    shoppingCart.removeItemFromCartAll(id);
    displayCart();
})


// -1
$('.show-cart').on("click", ".minus-item", function(event) {
    var id = $(this).data('id')
    shoppingCart.removeItemFromCart(id);
    displayCart();
})
// +1
$('.show-cart').on("click", ".plus-item", function(event) {
    var id = $(this).data('id')
    var max = $(this).data('max');
    shoppingCart.addItemToCart(id);
    displayCart();
})

// Item count input
$('.show-cart').on("change", ".item-count", function(event) {
    var id = $(this).data('id')
    var count = Number($(this).val());
    shoppingCart.setCountForItem(id, count);
    displayCart();
});

displayCart();

function orderProducts(event, element) {
    event.preventDefault();
    // alert(encodeURI(element.href + s));
    window.location.href = encodeURI(element.href + JSON.stringify(shoppingCart.listCart()));
}