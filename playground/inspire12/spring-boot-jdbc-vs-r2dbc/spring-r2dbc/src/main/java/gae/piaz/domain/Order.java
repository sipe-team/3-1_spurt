package gae.piaz.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
@Getter
@Setter
public class Order {

    @Id
    @Column("order_id")
    private Long orderId;

    @Column("quantity")
    private Integer quantity;

    // r2dbc does not support relationships:
    // https://github.com/spring-projects/spring-data-r2dbc/issues/356
    @Column("book_id")
    private Long bookId;

    @Column("user_id")
    private Long userId;

}