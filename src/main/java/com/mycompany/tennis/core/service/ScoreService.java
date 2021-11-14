package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    ScoreRepositoryImpl scoreRepository = null;

    public ScoreService(){
        scoreRepository = new ScoreRepositoryImpl();
    }

    public ScoreFullDto getScore(Long id){

        Session session = null;
        Transaction tx = null;
        Score score = null;
        EpreuveFullDto epreuveFullDto = null;
        MatchDto matchDto = null;
        ScoreFullDto scoreFullDto = null;

        try{

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getById(id);

            scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());

            matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());

            scoreFullDto.setMatchDto(matchDto);

            epreuveFullDto  = new EpreuveFullDto();
            epreuveFullDto.setId(score.getMatch().getEpreuve().getId());
            epreuveFullDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            //tournoiDto
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            matchDto.setEpreuveFullDto(epreuveFullDto);

            tx.commit();

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
        return  scoreFullDto;
    }
    public void deleteScore(Long id){

        Session session = null;
        Transaction tx =null;
        try{

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreRepository.deleteScore(id);
            tx.commit();

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
}
