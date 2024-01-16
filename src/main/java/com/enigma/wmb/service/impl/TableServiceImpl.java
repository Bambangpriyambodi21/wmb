package com.enigma.wmb.service.impl;

import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.entity.TableM;
import com.enigma.wmb.model.MenuResponse;
import com.enigma.wmb.model.SearchMenuRequest;
import com.enigma.wmb.model.SesrchTableRequest;
import com.enigma.wmb.repository.TableRepository;
import com.enigma.wmb.service.TableService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public TableM create(TableM tableM) {
        TableM menu = TableM.builder()
                .table_name(tableM.getTable_name())
                .price(tableM.getPrice())
                .build();
        TableM save = tableRepository.save(menu);
        TableM response = TableM.builder()
                .id(save.getId())
                .table_name(save.getTable_name())
                .price(save.getPrice())
                .build();
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public TableM update(TableM tableM) {
        log.info(tableM.getTable_name());

        Optional<TableM> byId = tableRepository.findById(tableM.getId());

        log.info(byId.get().getTable_name());
        TableM customer = TableM.builder()
                .id(byId.get().getId())
                .table_name(tableM.getTable_name())
                .price(tableM.getPrice())
                .build();
        TableM save = tableRepository.save(customer);

        TableM response = TableM.builder()
                .id(save.getId())
                .table_name(save.getTable_name())
                .price(save.getPrice())
                .build();
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<TableM> getAll(SesrchTableRequest request) {
        PageRequest pageable = PageRequest.of(0, 5);
        Specification<TableM> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(root.get("table_name"), "%" + request.getName() + "%");
                predicates.add(namePredicate);
            }
            if (request.getMinPrice() != null) {
                Predicate namePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), request.getMinPrice());
                predicates.add(namePredicate);
            }
            if (request.getMaxPrice() != null) {
                Predicate namePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), request.getMaxPrice());
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        return tableRepository.findAll(specification, pageable);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TableM getById(String request) {
        Optional<TableM> byId = tableRepository.findById(request);

        TableM response = TableM.builder()
                .id(byId.get().getId())
                .table_name(byId.get().getTable_name())
                .price(byId.get().getPrice())
                .build();

        return response;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public String deleteId(String id) {
        log.info(id);
        tableRepository.deleteById(id);
        return "ok";
    }

}
