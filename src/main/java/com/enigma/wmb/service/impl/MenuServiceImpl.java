package com.enigma.wmb.service.impl;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.model.MenuRequest;
import com.enigma.wmb.model.MenuResponse;
import com.enigma.wmb.model.SearchMenuRequest;
import com.enigma.wmb.repository.MenuRepository;
import com.enigma.wmb.service.MenuService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = Menu.builder()
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .build();
        Menu save = menuRepository.save(menu);
        MenuResponse response = MenuResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .price(save.getPrice())
                .build();
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Menu> getAllMenu(SearchMenuRequest request) {
        PageRequest pageable = PageRequest.of(0,5);
        Specification<Menu> productSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName()!= null){
                Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%"+request.getName()+"%");
                predicates.add(namePredicate);
            }
            if (request.getMinPrice()!= null){
                Predicate namePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), request.getMinPrice());
                predicates.add(namePredicate);
            }
            if (request.getMaxPrice()!= null){
                Predicate namePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), request.getMaxPrice());
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        return menuRepository.findAll(productSpecification, pageable);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MenuResponse getById(String menuRequest) {
        Optional<Menu> byId = menuRepository.findById(menuRequest);

        MenuResponse response = MenuResponse.builder()
                .id(byId.get().getId())
                .name(byId.get().getName())
                .price(byId.get().getPrice())
                .build();

        return response;
    }

    @Override
    public String deleteById(String id) {
        menuRepository.deleteById(id);
        return "ok";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MenuResponse updateById(MenuRequest menuRequest) {
        Optional<Menu> byId = menuRepository.findById(menuRequest.getId());

        Menu menu = Menu.builder()
                .id(byId.get().getId())
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .build();
        Menu save = menuRepository.save(menu);

        MenuResponse response = MenuResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .price(save.getPrice())
                .build();
        return response;
    }


}
