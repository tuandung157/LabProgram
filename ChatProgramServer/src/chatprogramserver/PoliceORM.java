/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramserver;


import connection.HibernateUtil;
import connection.Policedb;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.imageio.spi.ServiceRegistry;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistryBuilder;
/**
 *
 * @author tuandung
 */
public class PoliceORM {
    private static Session session;
    private static Transaction ts;
    public void addElement(Policedb p){
        
        HibernateUtil.getSessionFactory().getCurrentSession();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Policedb> getAll() {
//        HibernateUtil.getSessionFactory().getCurrentSession();         
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        Query q = session.createQuery("from Policedb");
//        List<Policedb> results = (List<Policedb>) q.list();  
        List<Policedb> list = session.createCriteria(Policedb.class).list();
        return list;        
    }
    public void deleteElement(Policedb p){
        
        HibernateUtil.getSessionFactory().getCurrentSession();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();        
        session.close();
    }
    public void editElement(Policedb p) {
        HibernateUtil.getSessionFactory().getCurrentSession();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.update(p);
        session.getTransaction().commit();
        session.close();
    }
    public static void create(Policedb h){
        try  
        { 
        session = HibernateUtil.getSessionFactory().openSession();
        ts = session.beginTransaction();
        session.save(h);
        ts.commit();
        System.out.println("NewUser saved " +  
                h.toString());
        session.close();
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        } 
    }
    
   
    public static void delete(Policedb h)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.delete(h);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public static void update(Policedb h)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(h);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    
    public static Integer getNextId() 
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(id) from Humandb";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close(); 
        //System.out.println(userId);
        return userId;  
    }
    
    
    
     public List < Policedb > read() 
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "from Polices";  
        Query query = session.createQuery(hql);  
        List < Policedb > results = (List < Policedb > ) query.list();  

        session.flush();  
        session.close(); 
        //System.out.println( results.get(0).getName());
        return results;  
    }
   
//    public static void main(String arg[]){
//        Date in = new Date();
//        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
//        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	LocalDate localDate = LocalDate.now();
//        Humandb h = new Humandb(getNextId(), "Lola","black", 50, 89,150, localDate.toString(), LocalDateTime.now());
//        Polices h = new Polices(0, "PA", null, 5, 5, 5, 30, 0, LocalDateTime.of(1998, Month.FEBRUARY, 27,0 , 0));
//        //create(h);
//        delete(h);
//        //read();   
//    }
}
