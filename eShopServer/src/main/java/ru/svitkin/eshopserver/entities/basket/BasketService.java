package ru.svitkin.eshopserver.entities.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.user.User;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;

    public Basket create(User user) {
        Basket basket = new Basket(user, new ArrayList<>());
        return basketRepository.save(basket);
    }
}
