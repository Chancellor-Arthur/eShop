package ru.svitkin.eshopserver.entities.basket;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.user.User;

@Service
@Transactional
@RequiredArgsConstructor
public class BasketService {
	private final BasketRepository basketRepository;

	public Basket create(User user) {
		Basket basket = new Basket(user, new ArrayList<>());
		return basketRepository.save(basket);
	}
}
