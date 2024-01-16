package com.enigma.wmb.service;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.entity.TableM;
import com.enigma.wmb.model.MenuResponse;
import com.enigma.wmb.model.SearchMenuRequest;
import com.enigma.wmb.model.SesrchTableRequest;
import org.springframework.data.domain.Page;

public interface TableService {
    TableM create(TableM tableM);
    TableM update(TableM tableM);
    Page<TableM> getAll(SesrchTableRequest request);
    TableM getById(String id);
    String deleteId(String id);
}
