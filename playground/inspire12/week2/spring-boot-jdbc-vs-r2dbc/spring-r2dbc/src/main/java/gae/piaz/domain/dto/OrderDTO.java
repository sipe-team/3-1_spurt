package gae.piaz.domain.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private Integer orderId;

    private Integer quantity;

    private Long bookId;

    private Long userId;

}
