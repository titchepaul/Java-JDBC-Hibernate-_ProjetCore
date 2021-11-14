package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.List;

public class TravauxPratiques {

    public static  void  main(String[] args){

        TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();
        /*List<Tournoi> tournoiList = tournoiRepository.list();
        for(Tournoi t : tournoiList ){
            System.out.println(t.getId()+" "+t.getNom()+" "+t.getCode());
        }*/
        /*JoueurService joueurService = new JoueurService();
        Joueur j = joueurService.getJoueur(53);
        System.out.println(j.getPrenom()+" "+j.getId()+" "+j.getNom());*/
        Tournoi tournoi = tournoiRepository.getById(3l);
        System.out.println(tournoi.getId() + " "+tournoi.getNom()+" "+tournoi.getCode());
    }
}
