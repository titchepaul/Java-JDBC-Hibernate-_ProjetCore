package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;


import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {


    public void create (Tournoi tournoi){
       /* Connection conn = null;
        try {
            //MysqlDataSource dataSource = new MysqlDataSource();
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO TOURNOI(NOM,CODE) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2,tournoi.getCode());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            conn.commit();

            if(rs.next()){
                tournoi.setId(rs.getLong(1));
            }
            System.out.println("Tournoi Créé");
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
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //session.persist(tournoi);
        entityManager.persist(tournoi);
        System.out.println("Tournoi ajouté");
    }
    public void update (Tournoi tournoi){
        Connection conn = null;
        try {
            //MysqlDataSource dataSource = new MysqlDataSource();
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?, CODE=? WHERE ID=?");

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2,tournoi.getCode());
            preparedStatement.setLong(3,tournoi.getId());
            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Tournoi modifié");
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
    }
    public void delete (Long id){
        /*Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Tournoi supprimé");
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
        //Tournoi tournoi = new Tournoi();
        //tournoi.setId(id);
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //session.delete(tournoi);
        Tournoi tournoi = entityManager.find(Tournoi.class,id); //Tournoi persistant
        entityManager.remove(tournoi);
        System.out.println("Tournoi supprimé");
    }
    public Tournoi getById (Long id){

        /*try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM,CODE FROM TOURNOI WHERE ID=?");
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                tournoi = new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
            }

            conn.commit();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tournoi = session.get(Tournoi.class,id);
            System.out.println("Tournoi lu");

        } catch (Throwable e) {
            e.printStackTrace();
        }*/

        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //Tournoi tournoi = session.get(Tournoi.class,id);
        Tournoi tournoi = entityManager.find(Tournoi.class,id);
        System.out.println("Tournoi lu");

        return tournoi;
    }
    public List<Tournoi> list(){
        Connection conn = null;
        Tournoi tournoi = null;
        List<Tournoi> tournois = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,CODE FROM TOURNOI");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }

            conn.commit();
            System.out.println("Tournois lus");
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
        return tournois;
    }
}
