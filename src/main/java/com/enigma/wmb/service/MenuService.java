package com.enigma.wmb.service;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.model.MenuRequest;
import com.enigma.wmb.model.MenuResponse;
import com.enigma.wmb.model.SearchMenuRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {

    MenuResponse createMenu(MenuRequest menuRequest);
    Page<Menu> getAllMenu(SearchMenuRequest searchMenuRequest);
    MenuResponse getById(String id);
    String deleteById(String id);
    MenuResponse updateById(MenuRequest menuRequest);
}
