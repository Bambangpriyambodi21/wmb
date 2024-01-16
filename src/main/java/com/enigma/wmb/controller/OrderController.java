package com.enigma.wmb.controller;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.entity.Order;
import com.enigma.wmb.model.*;
import com.enigma.wmb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(path = "/api/orders")
    public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.create(orderRequest);
        WebResponse<OrderResponse> response = WebResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new order")
                .data(orderResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(path = "/api/orders")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String transType,
                                    @RequestParam(required = false) String hari,
                                    @RequestParam(required = false) String bulan,
                                    @RequestParam(required = false) String tahun,
                                    @RequestParam(required = false)Date startDate,
                                    @RequestParam(required = false)Date endDate){
        SearchOrderRequest searchMenuRequest = SearchOrderRequest.builder()
                .transType(transType)
                .hari(hari)
                .bulan(bulan)
                .tahun(tahun)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        Page<Order> all = orderService.getAll(searchMenuRequest);
        WebResponse<Page<Order>> response = WebResponse.<Page<Order>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("get all menu")
                .data(all)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

    @GetMapping(path = "/api/orders/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable String id){
        OrderResponse menu = orderService.getById(id);
        WebResponse<OrderResponse> response = WebResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new menu")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
