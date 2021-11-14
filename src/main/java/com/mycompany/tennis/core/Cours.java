package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;


public class Cours {
    public static void main(String... args){
        /*JoueurService joueurService = new JoueurService();
        /*List<Joueur> joueurList = joueurRepository.list();
        for(Joueur j : joueurList){
            System.out.println(j.getPrenom()+" "+j.getNom()+" "+j.getSexe()+" "+j.getId());
        }
        Joueur joueur = new Joueur();
        joueur.setPrenom("Yannick");
        joueur.setNom("Noah");
        joueur.setSexe('H');
        joueurService.createJoueur(joueur);*/
        MatchService matchService = new MatchService();
        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);
        match.setScore(score);
        score.setMatch(match);

        Joueur feder = new Joueur();
        feder.setId(32l);
        Joueur murray = new Joueur();
        murray.setId(34l);

        match.setVainqueur(feder);
        match.setFinaliste(murray);
        Epreuve epreuve = new Epreuve();
        epreuve.setId(10l);
        match.setEpreuve(epreuve);

        /*matchService.enregistrerNouveauMatch(match);
        System.out.println("l'Id du match crée est : "+match.getId());*/

    }
}
