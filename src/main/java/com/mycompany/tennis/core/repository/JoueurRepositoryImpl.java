package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create (Joueur joueur){
        /*Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR(NOM,PRENOM,SEXE) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2,joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            conn.commit();

            if(rs.next()){
                joueur.setId(rs.getLong(1));
            }
            System.out.println("Joueur Créé");
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if(conn != null){
                    conn.rollback();
                }
            }catch(SQLException exception){
                exception.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        System.out.println("Joueur crée");
    }
    /*public void update (Joueur joeur){
        Connection conn = null;
        try {
            //MysqlDataSource dataSource = new MysqlDataSource();
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?,SEXE=? WHERE ID=?");

            preparedStatement.setString(1,joeur.getNom());
            preparedStatement.setString(2,joeur.getPrenom());
            preparedStatement.setString(3,joeur.getSexe().toString());
            preparedStatement.setLong(4,joeur.getId());
            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Joueur modifié");
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if(conn != null){
                    conn.rollback();
                }
            }catch(SQLException exception){
                exception.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
    public void delete (Long id){
        /*Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Joueur supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if(conn != null){
                    conn.rollback();
                }
            }catch(SQLException exception){
                exception.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        Joueur joueur = new Joueur();
        joueur.setId(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur Supprimé");
    }
    public Joueur getById (Long id){
        //Connection conn = null;
        Session session = null;
        Joueur joueur = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class,id);
        System.out.println("Joueur lu");
        return joueur;
        /*try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM,PRENOM,SEXE FROM JOUEUR WHERE ID=?");
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }

            conn.commit();
            session = HibernateUtil.getSessionFactory().openSession();
            joueur = session.get(Joueur.class,id);
            System.out.println("Joueur lu");

        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }*/

    }
    public List<Joueur> getListJoueurs(char sexe){

       /* Connection conn = null;
        Joueur joueur = null;
        List<Joueur> joueurs = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createQuery("select j from Joueur j", Joueur.class); //requête HQL ou JPQL
        List<Joueur> joueurList = query.getResultList();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM JOUEUR");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                joueur = new Joueur();
                joueur.setId(rs.getLong(1));
                joueur.setNom(rs.getString(2));
                joueur.setPrenom(rs.getString(3));
                joueur.setSexe(rs.getString(4).charAt(0));
                joueurs.add(joueur);
            }

            conn.commit();
            System.out.println("Joueur lus");
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if(conn != null){
                    conn.rollback();
                }
            }catch(SQLException exception){
                exception.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;*/
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //Query<Joueur> query = session.createQuery("select j from Joueur j where j.sexe = ?0", Joueur.class); //requête HQL ou JPQL
        //Query<Joueur> query = session.createNamedQuery("given_sexe", Joueur.class); //requête HQL ou JPQL
        TypedQuery<Joueur> query = entityManager.createNamedQuery("given_sexe", Joueur.class); //JPA
        query.setParameter(0,sexe);
        List<Joueur> joueurs = query.getResultList();
        return  joueurs;
    }
}
