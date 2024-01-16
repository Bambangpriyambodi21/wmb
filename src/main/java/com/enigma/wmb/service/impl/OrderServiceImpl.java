package com.enigma.wmb.service.impl;

import com.enigma.wmb.entity.*;
import com.enigma.wmb.model.*;
import com.enigma.wmb.repository.OrderRepository;
import com.enigma.wmb.repository.TransTypeRepository;
import com.enigma.wmb.service.CustomerService;
import com.enigma.wmb.service.OrderService;
import com.enigma.wmb.service.TableService;
import com.enigma.wmb.service.TransTypeService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final TableService tableService;
    private final TransTypeService transTypeService;
    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        log.info(orderRequest.getTrans_type());
        Customer customer = customerService.findById(orderRequest.getCustomer_id());
        TableM byId = tableService.getById(orderRequest.getTable_id());
        TransType transType = transTypeService.getById(orderRequest.getTrans_type());

        Order order = Order.builder()
                .trans_date(new Date())
                .customer_id(customer)
                .table_id(byId)
                .trans_type(transType)
                .build();

        Order save = orderRepository.save(order);

        OrderResponse orderResponse = OrderResponse.builder()
                .id(save.getId())
                .customer_id(save.getCustomer_id().getId())
                .table_id(save.getTable_id().getId())
                .trans_date(save.getTrans_date())
                .build();

        return orderResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Order> getAll(SearchOrderRequest request) {
        PageRequest pageable = PageRequest.of(0,5);
        Specification<Order> productSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Order, TransType> orderTransTypeJoin = root.join("m_trans_type");

            if (request.getTransType()!= null){
                Predicate namePredicate = criteriaBuilder.like(orderTransTypeJoin.get("trans_type_id"), request.getTransType());
                predicates.add(namePredicate);
            }
            if (request.getHari()!= null){
                Predicate namePredicate = criteriaBuilder.like(root.get("trans_date"), "%"+request.getHari());
                predicates.add(namePredicate);
            }
            if (request.getBulan()!= null){
                Predicate namePredicate = criteriaBuilder.like(root.get("trans_date"), request.getBulan());
                predicates.add(namePredicate);
            }
            if (request.getTahun()!= null){
                Predicate namePredicate = criteriaBuilder.like(root.get("trans_date"), request.getTahun());
                predicates.add(namePredicate);
            }
            if (request.getStartDate()!= null){
                Predicate namePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("trans_date"), request.getStartDate());
                predicates.add(namePredicate);
            }
            if (request.getEndDate()!= null){
                Predicate namePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("trans_date"), request.getEndDate());
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        return orderRepository.findAll(productSpecification, pageable);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderResponse getById(String id) {
        Optional<Order> byId = orderRepository.findById(id);

        OrderResponse response = OrderResponse.builder()
                .id(byId.get().getId())
                .trans_date(byId.get().getTrans_date())
                .customer_id(byId.get().getCustomer_id().getId())
                .table_id(byId.get().getTable_id().getId())
                .trans_type(byId.get().getTrans_type().getId())
                .build();

        return response;
    }
}
