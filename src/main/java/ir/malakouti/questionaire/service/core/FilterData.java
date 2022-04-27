package ir.malakouti.questionaire.service.core;

import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.Sort;
import ir.malakouti.questionaire.utils.filter.SortWrapper;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class FilterData<T> {


    protected abstract Class<T> getClazz();
    protected abstract EntityManager getEntityManager();


    public List<T> findAllByFilter(FilterWrapper filterWrapper,
                                   SortWrapper sortWrapper,
                                   Integer start,
                                   Integer limit) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getClazz());
        Root<T> root = criteriaQuery.from(getClazz());
        root.alias("v");
        criteriaQuery.where(getPredicates(filterWrapper, root, criteriaBuilder).toArray(new Predicate[]{}));
        addOrderByToQuery(criteriaQuery, sortWrapper, root, criteriaBuilder);
        criteriaQuery.select(root);
        return getResultListByRange(criteriaQuery, start, limit);
    }

    public Long count(FilterWrapper filterWrapper) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getClazz());
        Root<T> root = criteriaQuery.from(getClazz());
        root.alias("v");
        criteriaQuery.where(getPredicates(filterWrapper, root, criteriaBuilder).toArray(new Predicate[]{}));
        CriteriaQuery<Long> cqCount = criteriaBuilder.createQuery(Long.class);
        Predicate restriction = criteriaQuery.getRestriction();
        if (restriction != null) {
            cqCount.where(restriction);
        }
        cqCount.where(getPredicates(filterWrapper, root, criteriaBuilder).toArray(new Predicate[]{}));
        cqCount.select(criteriaBuilder.count(root));
        TypedQuery<Long> query = getEntityManager().createQuery(cqCount);
        return query.getSingleResult();
    }

    private List<T> getResultListByRange(CriteriaQuery<T> criteriaQuery, Integer start, Integer limit) {
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        if(start != null) {
            query.setFirstResult(start);
        }
        if(limit != null) {
            query.setMaxResults(limit);
        }
        return query.getResultList();
    }

    private void addOrderByToQuery(CriteriaQuery<T> criteriaQuery, SortWrapper sortWrapper, Root<T> basicCalculationDataRoot, CriteriaBuilder criteriaBuilder) {
        List<Order> orders = new ArrayList<>();
        if(sortWrapper != null) {
            for (Sort sort : sortWrapper.getSortSet()) {
                Order order;
                String[] f = sort.getProperty().split("\\.");
                Path<T> path = basicCalculationDataRoot.get(f[0]);
                for (int j = 1; j < f.length; j++) {
                    path = path.get(f[j]);
                }
                if (sort.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                    order = criteriaBuilder.desc(path);
                } else {
                    order = criteriaBuilder.asc(path);
                }
                orders.add(order);
            }
        }
        criteriaQuery.orderBy(orders);
    }

    protected List<Predicate> getPredicates(FilterWrapper filterWrapper, Root<T> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (filterWrapper != null && filterWrapper.getFilters() != null) {
            for (ir.malakouti.questionaire.utils.filter.Filter filter : filterWrapper.getFilters()) {
                Object value = filter.getValue();
                String field = filter.getProperty();
                ir.malakouti.questionaire.utils.filter.Filter.Operator operator = filter.getOperator();
                String[] f = field.split("\\.");
                switch (operator) {
                    case LIKE:
                        predicates.add(criteriaBuilder.like(root.get(
                                        getEntityManager()
                                                .getMetamodel()
                                                .entity(getClazz())
                                                .getDeclaredSingularAttribute(field, String.class))
                                , "%" + value + "%"));
                        break;
                    case EQUAL:
                        Path<T> path = root.get(f[0]);
                        for (int j = 1; j < f.length; j++) {
                            path = path.get(f[j]);
                        }
                        Predicate predicate = criteriaBuilder.equal(path, value);
                        predicates.add(predicate);
                        break;
                }
            }
        }
        return predicates;
    }


}
