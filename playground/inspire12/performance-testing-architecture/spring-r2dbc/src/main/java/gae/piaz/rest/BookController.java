package gae.piaz.rest;

import gae.piaz.domain.Book;
import gae.piaz.domain.dto.BookDTO;
import gae.piaz.repository.BookRepository;
import gae.piaz.service.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<BookDTO> getAll() {
        return Flux.just(UUID.randomUUID()).flatMap(uuid -> {
            log.info("getAll() {} running", uuid);
            return this.bookRepository.findAll().
                    map(mapper::toDto).doFinally((a) -> {
                        log.info("getAll() {} executed", uuid);
                    });
        });
    }

    @GetMapping(value = "/simple", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getAllSimple() {
        return this.bookRepository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Mono<BookDTO> saveBook(@RequestBody BookDTO dto) {
        return Mono.just(UUID.randomUUID()).flatMap(uuid -> {
            log.info("saveBook() {} running", uuid);
            return this.bookRepository.save(mapper.toEntity(dto))
                    .map(mapper::toDto).doFinally((s) -> {
                        log.info("saveBook() {} executed", uuid);
                    });
        });
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Mono<BookDTO> updateBook(@RequestBody BookDTO dto) {
        return Mono.just(UUID.randomUUID()).flatMap(uuid -> {
            log.info("updateBook() {} running", uuid);
            return this.bookRepository.findByIsbn(dto.getIsbn()).flatMap(b -> {
                b.setAuthor(dto.getAuthor());
                return this.bookRepository.save(b).map(mapper::toDto).doFinally((s) -> {
                    log.info("updateBook() {} executed", uuid);
                });
            });
        });
    }

}
