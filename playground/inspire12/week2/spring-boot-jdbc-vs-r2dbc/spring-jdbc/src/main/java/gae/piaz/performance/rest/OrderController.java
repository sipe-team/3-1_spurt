package gae.piaz.performance.rest;

import gae.piaz.performance.domain.dto.OrderDTO;
import gae.piaz.performance.domain.Book;
import gae.piaz.performance.domain.Order;
import gae.piaz.performance.domain.User;
import gae.piaz.performance.repository.BookRepository;
import gae.piaz.performance.repository.OrderRepository;
import gae.piaz.performance.repository.UserRepository;
import gae.piaz.performance.service.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<OrderDTO> addOrder(@RequestParam("bookIsbn") String bookIsbn, @RequestParam("firstName") String firstName) {

        UUID uuid = UUID.randomUUID();
        log.info("addOrder() {} running", uuid);

        User user = userRepository.findByFirstName(firstName);
        Book book = bookRepository.findByIsbn(bookIsbn);
        log.info("addOrder() {} I've got user and book", uuid);

        Order order = new Order();
        order.setUser(user);
        order.setQuantity(1);
        order.setBook(book);
        order = orderRepository.save(order);

        OrderDTO orderDTO = orderMapper.toDto(order);
        log.info("addOrder() {} executed", uuid);
        return ResponseEntity.ok(orderDTO);

    }

}
