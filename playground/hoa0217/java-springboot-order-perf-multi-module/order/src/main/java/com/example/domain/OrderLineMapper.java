package com.example.domain;

import com.example.application.dto.OrderSheetItem;
import java.util.List;

public interface OrderLineMapper {

    List<OrderLine> map(List<OrderSheetItem> orderSheetItems);
}
