package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 13.14. Implementando consulta com dados agregados de vendas diárias<p>
 * 13.16. Tratando time offset na agregação de vendas diárias por data<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
              @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
        return vendaQueryService.consultarVendasDiarias(filter, timeOffSet);
    }
}
