package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dao.MatchDaoImpl;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService(){
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    /*public  void enregistrerNouveauMatch(Match match) {
        matchDao.createMatchWithScore(match);
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }*/
    public  void createMatch(MatchDto matchDto){

        Session session = null;
        Transaction tx =null;
        Match match = null;
        try{

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = new Match();
            match.setEpreuve(epreuveRepository.getById(matchDto.getEpreuveFullDto().getId()));
            match.setVainqueur(joueurRepository.getById(matchDto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(matchDto.getFinaliste().getId()));

            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(matchDto.getScoreFullDto().getSet1());
            score.setSet2(matchDto.getScoreFullDto().getSet2());
            score.setSet3(matchDto.getScoreFullDto().getSet3());
            score.setSet4(matchDto.getScoreFullDto().getSet4());
            score.setSet5(matchDto.getScoreFullDto().getSet5());

            matchRepository.create(match);
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
    public MatchDto getMatch(Long id){

        Session session = null;
        Transaction tx = null;
        MatchDto matchDto = null;
        JoueurDto joueurDto =null;
        Match match = null;
        EpreuveFullDto epreuveFullDto = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            matchDto = new MatchDto();
            JoueurDto finalisteDto = new JoueurDto();
            JoueurDto vainqueurDto = new JoueurDto();

            matchDto.setId(match.getId());
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setSexe(match.getFinaliste().getSexe());
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());
            matchDto.setFinaliste(finalisteDto);
            matchDto.setVainqueur(vainqueurDto);

            epreuveFullDto  = new EpreuveFullDto();
            epreuveFullDto.setId(match.getEpreuve().getId());
            epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            //tournoiDto
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            matchDto.setEpreuveFullDto(epreuveFullDto);

            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            scoreFullDto.setSet4(match.getScore().getSet4());
            scoreFullDto.setSet5(match.getScore().getSet5());

            matchDto.setScoreFullDto(scoreFullDto);
            scoreFullDto.setMatchDto(matchDto);
            tx.commit();

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
        return  matchDto;
    }
    public void tapisVert(Long id){

        Session session = null;
        Transaction tx =null;
        Match match = null;
        try{

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);

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
    public void deleteMatch(Long id){

        Session session = null;
        Transaction tx =null;
        try{

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            matchRepository.deleteMatch(id);
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
