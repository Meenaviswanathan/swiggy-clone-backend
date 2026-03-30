package com.example.demowithswiggy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demowithswiggy.dao.FoodItemRepo;
import com.example.demowithswiggy.dao.FoodOrderRepo;
import com.example.demowithswiggy.dao.RestaurantRepo;
import com.example.demowithswiggy.dao.UserRepo;
import com.example.demowithswiggy.model.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RestaurantRepo rr;

    @Autowired
    UserRepo ur;

    @Autowired
    FoodItemRepo fir;

    @Autowired
    FoodOrderRepo foor;

    @PostMapping("/restaurant")
    public Restaurant get1(@RequestBody Restaurant r) {
        return rr.save(r);
    }

    @PostMapping("/food")
    public FoodItem addFood(@RequestBody FoodItem f) {

        // ✅ DBல இருந்து full restaurant fetch
        Restaurant r = rr.findById(f.getRestaurant().getId()).get();

        // ✅ set full object
        f.setRestaurant(r);

        return fir.save(f);
    }

    @PutMapping("/assign/{orderId}/{partnerId}")
    public FoodOrder assignDelivery(
            @PathVariable int orderId,
            @PathVariable int partnerId) {

        User partner = ur.findById(partnerId).get();
        FoodOrder order = foor.findById(orderId).get();

        order.setDeliveryPartner(partner);
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);

        return foor.save(order);
    }
}
