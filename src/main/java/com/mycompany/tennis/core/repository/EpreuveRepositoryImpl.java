package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {

    public Epreuve getById(Long id){
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //Epreuve epreuve = session.get(Epreuve.class,id);
        Epreuve epreuve = entityManager.find(Epreuve.class,id);
        System.out.println("Epreuve lu");
        return epreuve;
    }
    public List<Epreuve> getListEpreuve(String codeTournoi){
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code = ?0", Epreuve.class); //requête HQL ou JPQL
        TypedQuery<Epreuve> query = entityManager.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code = ?0", Epreuve.class); //JPA
        query.setParameter(0,codeTournoi);
        List<Epreuve> epreuves= query.getResultList();
        return  epreuves;
    }
}
