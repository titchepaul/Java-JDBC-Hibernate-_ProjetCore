package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
//import com.mycompany.tennis.core.dto.EpreuveDto;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {

    EpreuveRepositoryImpl epreuveRepository = null;

    public  EpreuveService(){
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getByIdWithTournoi(Long id){

        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDto epreuveFullDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            //Hibernate.initialize(epreuve.getTournoi()); //l'objet de type proxy

            epreuveFullDto  = new EpreuveFullDto();
            epreuveFullDto.setId(epreuve.getId());
            epreuveFullDto.setAnnee(epreuve.getAnnee());
            epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());
            //tournoiDto
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            epreuveFullDto.setParticipants(new HashSet<>());

            for(Joueur joueur : epreuve.getParticipants()){
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                epreuveFullDto.getParticipants().add(joueurDto);
            }
            tx.commit();
            System.out.println("Epreuve lu");

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
        return epreuveFullDto;
    }
    public EpreuveLightDto getByIdSansTournoi(Long id){

        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLightDto epreuveLightDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            epreuveLightDto = new EpreuveLightDto();
            epreuveLightDto.setId(epreuve.getId());
            epreuveLightDto.setAnnee(epreuve.getAnnee());
            epreuveLightDto.setTypeEpreuve(epreuve.getTypeEpreuve());
            tx.commit();

            System.out.println("Epreuve lu");

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
        return epreuveLightDto;
    }
    public List<EpreuveFullDto> getListEpreuve(String codeTournoi){

        //Session session = null;
        //Transaction tx = null;
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        List<EpreuveFullDto> epreuveFullDtos = new ArrayList<>();

        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            entityManager = EntityManagerHolder.getCurrentEntityManager(); //jpa
            //tx = session.beginTransaction();  //jpa
            tx = entityManager.getTransaction();
            tx.begin();
            List<Epreuve> epreuves = epreuveRepository.getListEpreuve(codeTournoi);

            for(Epreuve epreuve : epreuves){
                final EpreuveFullDto dto = new EpreuveFullDto();
                dto.setId(epreuve.getId());
                dto.setAnnee(epreuve.getAnnee());
                dto.setTypeEpreuve(epreuve.getTypeEpreuve());

                TournoiDto tournoiDto = new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                dto.setTournoi(tournoiDto);
                epreuveFullDtos.add(dto);
            }
            tx.commit();
            System.out.println("Epreuve lu");

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
        return epreuveFullDtos;
    }
}
