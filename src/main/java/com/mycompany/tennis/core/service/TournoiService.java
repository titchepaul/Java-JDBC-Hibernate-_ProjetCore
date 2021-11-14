package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public  TournoiService(){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }
    public void createTournoi(TournoiDto tournoiDto){
        //Session session = null;
        EntityManager entityManager = null; //JPA
        //Transaction tx = null;
        EntityTransaction tx = null; //JPA
        try {
                //session = HibernateUtil.getSessionFactory().getCurrentSession();
                entityManager = EntityManagerHolder.getCurrentEntityManager();
                //tx = session.beginTransaction();
                tx = entityManager.getTransaction(); //JPA
                tx.begin();

                Tournoi tournoi = new Tournoi();
                tournoi.setId(tournoiDto.getId());
                tournoi.setNom(tournoiDto.getNom());
                tournoi.setCode(tournoiDto.getCode());
                tournoiRepository.create(tournoi);
                tx.commit();

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            /*if(session != null){
                session.close();
            }*/
            if(entityManager != null){
                entityManager.close(); //JPA
            }
        }
    }
    public TournoiDto getById(Long id){

        //Session session = null;
        EntityManager entityManager = null; //JPA
        //Transaction tx = null;
        EntityTransaction tx = null;  //JPA
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;
        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            entityManager = EntityManagerHolder.getCurrentEntityManager();  //JPA
            //tx = session.beginTransaction();
            tx = entityManager.getTransaction();
            tx.begin();
            tournoi = tournoiRepository.getById(id);
            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setNom(tournoi.getNom());
            tournoiDto.setCode(tournoi.getCode());
            tx.commit();

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            /*if(session != null){
                session.close();
            }*/
            if(entityManager != null){
                entityManager.close();
            }
        }
        return  tournoiDto;
    }
    public void deleteTournoi(Long id){

        //Session session = null;
        EntityManager entityManager = null;
        //Transaction tx = null;
        EntityTransaction tx = null;
        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            //tx = session.beginTransaction();
            tx = entityManager.getTransaction();
            tx.begin();
            tournoiRepository.delete(id);
            tx.commit();

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            /*if(session != null){
                session.close();
            }*/
            if(entityManager != null){
                entityManager.close();
            }
        }
    }
}
