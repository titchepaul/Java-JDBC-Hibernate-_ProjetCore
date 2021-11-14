package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService(){
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    public List<JoueurDto> getListJoueurs(char sexe){

        //Session session = null;
        EntityManager entityManager = null; //jpa
        //Transaction tx = null;
        EntityTransaction tx = null;
        List<JoueurDto> joueurDtoList = new ArrayList<>();

        try {

            //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tennis-unit");
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            //tx = session.beginTransaction();
            tx = entityManager.getTransaction();
            tx.begin();
            List<Joueur> joueurs = joueurRepository.getListJoueurs(sexe);

            for(Joueur j : joueurs){
                final JoueurDto dto = new JoueurDto();
                dto.setId(j.getId());
                dto.setPrenom(j.getPrenom());
                dto.setSexe(j.getSexe());
                dto.setNom(j.getNom());
                joueurDtoList.add(dto);
            }
            tx.commit();
            System.out.println("Joueur lu");

        }catch(Exception e){
            /*if(tx != null){ //problème pour la JavaEE
                tx.rollback();
            }*/
            e.printStackTrace();
        }
        /*finally {
            /*if(session != null){
                session.close();
            }*/
            /*if(entityManager != null){
                entityManager.close();
            }
        }*/
        return joueurDtoList;
    }
    public void renomerJoueur(Long id, String nouveauNom){

        Joueur joueur = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id);

            joueur.setNom(nouveauNom);
            //session.persist(joueur);
            tx.commit();
            System.out.println("Joueur modifié avec succès");

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
    public  void createJoueur(Joueur joueur){
        //joueurRepository.create(joueur);
        Session session = null;
        Transaction tx = null;
        try{
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                joueurRepository.create(joueur);
                tx.commit();
                System.out.println("Joueur ajouté");

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
    public Joueur getJoueur(long id){
       //return  joueurRepository.getById(id);
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;

        try {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                joueur = joueurRepository.getById(id);
                tx.commit();
                System.out.println("Joueur lu");

        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return joueur;
    }
    public void deleteJoueur(long id){

        Session session = null;
        Transaction tx = null;
        try{
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                joueurRepository.delete(id);
                tx.commit();
                System.out.println("Joueur Supprimé");

        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
    public void renomerSexeJoueur(Long id, Character sexe){

        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id);
            joueur.setSexe(sexe);
            tx.commit();
            System.out.println("Sexe du Joueur a été modifié");

        }catch(Exception e){
            e.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
}
