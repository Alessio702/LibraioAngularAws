//package com.sviluppatoredisuccesso.webapp.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.springframework.stereotype.Repository;
//
//@Repository
//public abstract class AbstractRepository<E, ID> {
//	
//	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
//	
//	private Class<E> e;
//	
//	// Costruttore
//	public AbstractRepository(Class<E> e) {
//		this.e = e;
//	}
//	
//	
//	
//	
//	
//	public E search(ID id) {
//		EntityManager em = emf.createEntityManager();
//		
//		return em.find(e, id);
//	}
//	
//	public E findBy(String column, Object item) {
//	    EntityManager em = emf.createEntityManager();
//	    
//	    CriteriaBuilder cb = em.getCriteriaBuilder();
//	    CriteriaQuery<E> cq = cb.createQuery(e);
//	    
//	    Root<E> root = cq.from(e);
//	    cq.select(root);
//	    cq.where(cb.equal(root.get(column), item));
//	    
//	    return em.createQuery(cq).setMaxResults(1).getSingleResult();
//	}
//	
//	public List<E> findAll() {
//		EntityManager em = emf.createEntityManager();
//		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<E> cq = cb.createQuery(e);
//		
//		Root<E> root = cq.from(e);
//		cq.select(root);
//		
//		return em.createQuery(cq).getResultList();
//	}
//	
//	public E update(E entity) {
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		E result = em.merge(entity);
//		
//		em.getTransaction().commit();
//		em.close();
//		
//		return result;
//	}
//	
//	
//	public void save(E entity) {
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		em.persist(entity);
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	public void delete(E entity) {
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		em.remove(em.merge(entity));
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	
//	
//	
//	
//}
