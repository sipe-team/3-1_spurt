package gae.piaz.rest;

import gae.piaz.domain.Book;
import gae.piaz.domain.Order;
import gae.piaz.domain.User;
import gae.piaz.domain.dto.OrderDTO;
import gae.piaz.repository.BookRepository;
import gae.piaz.repository.OrderRepository;
import gae.piaz.repository.UserRepository;
import gae.piaz.service.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Mono<OrderDTO> addOrder(@RequestParam("bookIsbn") String bookIsbn, @RequestParam("firstName") String firstName) {
        return Mono.just(UUID.randomUUID()).flatMap(uuid -> {
            log.info("addOrder() {} running", uuid);

            Mono<User> user = userRepository.findByFirstName(firstName);
            Mono<Book> book = bookRepository.findByIsbn(bookIsbn);

            return Mono.zip(user, book).flatMap(zipFlux -> {
                        log.info("addOrder() {} I've got user and book", uuid);
                        Order order = new Order();
                        order.setUserId(zipFlux.getT1().getUserId());
                        order.setBookId(zipFlux.getT2().getBookId());
                        order.setQuantity(1);
                        return orderRepository.save(order).map(orderMapper::toDto);
                    }
            ).doFinally((s) -> {
                log.info("addOrder() {} executed", uuid);
            });

        });
    }

}
