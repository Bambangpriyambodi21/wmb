package com.enigma.wmb.controller;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.model.MenuRequest;
import com.enigma.wmb.model.MenuResponse;
import com.enigma.wmb.model.SearchMenuRequest;
import com.enigma.wmb.model.WebResponse;
import com.enigma.wmb.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping(path = "/api/menus")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> create(@RequestBody MenuRequest menuRequest){
        MenuResponse menu = menuService.createMenu(menuRequest);
        WebResponse<MenuResponse> response = WebResponse.<MenuResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new menu")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(path = "/api/menus")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) Long minPrice,
                                    @RequestParam(required = false) Long maxPrice){
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .name(name)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
        Page<Menu> allMenu = menuService.getAllMenu(searchMenuRequest);
        WebResponse<Page<Menu>> response = WebResponse.<Page<Menu>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("get all menu")
                .data(allMenu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }


    @GetMapping(path = "/api/menus/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable String id){
        MenuResponse menu = menuService.getById(id);
        WebResponse<MenuResponse> response = WebResponse.<MenuResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new menu")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(path = "/api/menus/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        String menu = menuService.deleteById(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("create new menu")
                .data("ok")
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(path = "/api/menus")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> update(@RequestBody MenuRequest menuRequest){
        MenuResponse menu = menuService.updateById(menuRequest);
        WebResponse<MenuResponse> response = WebResponse.<MenuResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new menu")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
