package com.algaworks.algafood.infrastructure.repository.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 13.14. Implementando consulta com dados agregados de vendas diárias<p>
 * 13.15. Desafio: adicionando os filtros na consulta de vendas diárias<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {

        /*
            select date(p.data_criacao) as data_criacao,
                   count(p.id)          as total_vendas,
                   sum(p.valor_total)   as valor_total
            from pedido p
            where p.restaurante_id = 1
              and p.data_criacao >= '2019-01-10'
              and p.data_criacao <= '2020-12-10'
              and (p.status in ('CONFIRMADO', 'ENTREGUE'))
            group by date(p.data_criacao);
         */

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<VendaDiaria> query = builder.createQuery(VendaDiaria.class);
        Root<Pedido> root = query.from(Pedido.class);

        Expression<Date> functionDateDataCriacao = builder.function("date",
                Date.class, root.get("dataCriacao"));

        CompoundSelection<VendaDiaria> selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        var predicates = new ArrayList<Predicate>();

        if (filter.getRestauranteId() != null){
            predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
        }
        if (filter.getDataCriacaoInicio() != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
        }
        if (filter.getDataCriacaoFim() != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
        }
        predicates.add(root.get("status").in(
                StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
