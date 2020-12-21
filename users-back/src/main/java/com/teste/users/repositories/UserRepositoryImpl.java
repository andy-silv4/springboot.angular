package com.teste.users.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.teste.users.domain.User;

public class UserRepositoryImpl implements UserRepositoryQuery {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Page<User> filter(UserFilter userFilter, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		
		Predicate[] predicates = criarRestricoes(userFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<User> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(userFilter));
	}

	private Predicate[] criarRestricoes(UserFilter userFilter, CriteriaBuilder builder,
			Root<User> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!ObjectUtils.isEmpty(userFilter.getUsername())) {
			predicates.add(builder.like(
					builder.lower(root.get("username")), "%" + userFilter.getUsername().toLowerCase() + "%"));
		}
		
		if (!ObjectUtils.isEmpty(userFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("name")), "%" + userFilter.getName().toLowerCase() + "%"));
		}
		
		if (!ObjectUtils.isEmpty(userFilter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + userFilter.getEmail().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<User> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(UserFilter userFilter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<User> root = criteria.from(User.class);
		
		Predicate[] predicates = criarRestricoes(userFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

}
