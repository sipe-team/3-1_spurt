package gae.piaz.performance.service;

import gae.piaz.performance.domain.dto.OrderDTO;
import gae.piaz.performance.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO dto);

    @Mappings({
            @Mapping(source = "entity.book.bookId", target = "bookId"),
            @Mapping(source = "entity.user.userId", target = "userId")
    })
    OrderDTO toDto(Order entity);

}
