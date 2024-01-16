package com.enigma.wmb.controller;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.entity.TableM;
import com.enigma.wmb.model.*;
import com.enigma.wmb.service.TableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TableController {
    private final TableService tableService;

    @PostMapping(path = "/api/tables")
    public ResponseEntity<?> create(@RequestBody TableM tableM){
        TableM menu = tableService.create(tableM);
        WebResponse<TableM> response = WebResponse.<TableM>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new table")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(path = "/api/tables")
    public ResponseEntity<?> update(@RequestBody TableM tableM){
        TableM menu = tableService.update(tableM);
        WebResponse<TableM> response = WebResponse.<TableM>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("update new table")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(path = "/api/tables")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) Long minPrice,
                                    @RequestParam(required = false) Long maxPrice){
        SesrchTableRequest searchTableRequest = SesrchTableRequest.builder()
                .name(name)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
        Page<TableM> allMenu = tableService.getAll(searchTableRequest);
        WebResponse<Page<TableM>> response = WebResponse.<Page<TableM>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("get all table")
                .data(allMenu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

    @GetMapping(path = "/api/tables/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        TableM menu = tableService.getById(id);
        WebResponse<TableM> response = WebResponse.<TableM>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("create new menu")
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(path = "/api/tables/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        log.info(id);
        String menu = tableService.deleteId(id);

        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("create new menu")
                .data("ok")
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
