/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilmico.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
/**
 *
 * @author CRacaza
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void beginTransaction(){
        getEntityManager().getTransaction().begin();
    }
    public void commitTransaction(){
        getEntityManager().getTransaction().commit();
    }
    public void create(T entity) throws Exception {
        try{
           
            getEntityManager().persist(entity);
            
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        
    }

    
    
    public void edit(T entity) throws Exception {
        try{
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    


    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<T> findAllByFilters(String filters, String query) {
        if(filters==null || filters.isEmpty()){
            return null;
        }
        String[] pairs = filters.split("&");
        boolean first=true;
        boolean fishy=false;
        boolean useNative=false; //use native if there are dates in parameters.
        String orderby="";
        
        SimpleDateFormat dftime = DateFormatters.timestampFormatTime;
        SimpleDateFormat df = DateFormatters.timestampFormat;
            if(pairs.length>0){
               int i=0;
               boolean valueIsNumber =true; //if not number, to lower all
               boolean valueIsDate =false; //if date, format value, 
                for(String pa:pairs){
                    
                     
                    //check sql injection
                    String[] namevalue=pa.split("=");
                    if(namevalue[1].toLowerCase().contains("=") ) {
                            fishy=true;
                            break;
                    }
                    
                    
                    
                    if(namevalue[0].equals("field")){
                        try{
                             String[] nv=pairs[i+2].split("="); //get value
                             Integer.parseInt(nv[1]); //check if integer
                        }catch(Exception e){
                            valueIsNumber=false;
                            try{
                                String[] nv=pairs[i+2].split("="); //get value
                                Double.parseDouble(nv[1]); //check if double
                            }catch(Exception ex){
                                valueIsNumber=false;
                                
                                try{
                                    String[] nv=pairs[i+2].split("="); //get value 
                                    dftime.parse(nv[1]);   //check if date with time
                                    valueIsDate=true;
                                    useNative=true;
                               }catch(Exception exe){
                                   valueIsDate=false;
                                   
                                   try{
                                        String[] nv=pairs[i+2].split("="); //get value 
                                        df.parse(nv[1]);   //check if date only
                                        valueIsDate=true;
                                        useNative=true;
                                    }catch(Exception exee){
                                        valueIsDate=false;

                                    }
                                   
                               }
                            }
                        }
                        
                        
                        
                        
                    }
                    switch (namevalue[0]) {
                        case "field":
                            if(first && !query.contains(" where ")){
                                //check first if date
                                if(valueIsNumber || valueIsDate){
                                     query+= " where c." + namevalue[1] + "";
                                }else{
                                    query+= " where lower(c." + namevalue[1] + ")";
                                }
                                first=false;
                            }else{
                                if(valueIsNumber || valueIsDate){
                                     query+= " and c." + namevalue[1] + "";
                                }else{
                                    query+= " and lower(c." + namevalue[1] + ")";
                                }
                            }
                            break;
                        case "type":
                            switch (namevalue[1]) {
                                case "equal":
                                    query+= "=";
                                    break;
                                case "notequal":
                                    query+= "!=";
                                    break;
                                case "like":
                                    query+= " like ";
                                    break;
                                case "greaterthan":
                                    query+= ">";
                                    break;
                                case "lesserthan":
                                    query+= "<";
                                    break;
                                case "greaterthanequal":
                                    query+= ">=";
                                    break;
                                case "lesserthanequal":
                                    query+= "<=";
                                    break;
                                case "in":
                                    query+= " in ";
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "value":
                            if(valueIsNumber && !valueIsDate){
                               //query+= "'" + namevalue[1] +"'";
                               
                               String like = query.substring(query.length()-5, query.length()-1 );
                                if(like.equals("like")){
                                    query+= "'%"+namevalue[1].toLowerCase()+"%'";
                                }else{
                                    query+= "" + namevalue[1] +"";
                                }
                               
                           }else if(valueIsDate){
//                            try {
                                //query+= "to_date('" + namevalue[1] +"','"+DateFormatters.timestampFormatTimeStr+"')";
                                   //query+= "'01/01/2018'";
                                query+= "'"+namevalue[1]+"'";
//                            } catch (ParseException ex) {
//                                Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                            
                           }else{
                                String likein = query.substring(query.length()-5, query.length()-1 );
                                //String in = query.substring(query.length()-3, query.length()-1 );
                                if(likein.equals("like")){
                                    query+= "'%"+namevalue[1].toLowerCase()+"%'";
                                }else if(likein.equals(") in")) { //need ) para alam na condition to...haha
                                    query+= "("+namevalue[1].toLowerCase()+")";
                                }else {
                                    query+= "'"+namevalue[1].toLowerCase()+"'";
                                }
                           }
                            break;
                        case "orderby":
                            String[] orderbys = namevalue[1].split(",");
                            for(String o : orderbys){
                                if(orderby.equals("")){
                                    orderby = " order by c."+o;
                                }else{
                                    orderby += ", c."+o;
                                }
                            }
                        default:
                            break;
                    }
                    
                    //reset valuIsNumber, check last pair if value
                    if(namevalue[0].equals("value")){
                        valueIsNumber=true;
                    }
                    i++;
                }
            }else{
                return null;
            }
        if(!fishy){
            javax.persistence.Query q = getEntityManager().createQuery(query+orderby);
            return q.getResultList();
        }else{
            return null;
        }
    }
    
    
    public List<T> findAllTest(String filters, String query) {
        javax.persistence.Query q = getEntityManager().createQuery(query);
        return q.getResultList();
    }
    
    
    public int getCustomerSequence(){
        int returnValue=0;
        try{
//            OpenJPAEntityManager oem = em.unwrap(OpenJPAEntityManager.class);
//            Connection jdbcConnection = (Connection) oem.getConnection();
//                     
//                     
//            //Connection conn =getEntityManager().unwrap(java.sql.Connection.class);
//            String sql = "SELECT APPS.AP_INVOICES_INTERFACE_S.NEXTVAL as value from DUAL";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//            if(rs.next()) {
//                returnValue = rs.getInt("value");
//             }

            returnValue=1; 
        }catch(Exception ex){
            return returnValue;
        }


        return returnValue;
    }
    
    
    //pagination
    
    public List<T> findAllByFilters(String filters, String query, int[] range) {
        if(filters==null || filters.isEmpty()){
            return null;
        }
        String[] pairs = filters.split("&");
        boolean first=true;
        boolean fishy=false;
        boolean useNative=false; //use native if there are dates in parameters.
        String orderby="";
        
        SimpleDateFormat dftime = DateFormatters.timestampFormatTime;
        SimpleDateFormat df = DateFormatters.timestampFormat;
            if(pairs.length>0){
               int i=0;
               boolean valueIsNumber =true; //if not number, to lower all
               boolean valueIsDate =false; //if date, format value, 
                for(String pa:pairs){
                    
                     
                    //check sql injection
                    String[] namevalue=pa.split("=");
                    if(namevalue[1].toLowerCase().contains("=") ) {
                            fishy=true;
                            break;
                    }
                    
                    
                    
                    if(namevalue[0].equals("field")){
                        try{
                             String[] nv=pairs[i+2].split("="); //get value
                             Integer.parseInt(nv[1]); //check if integer
                        }catch(Exception e){
                            valueIsNumber=false;
                            try{
                                String[] nv=pairs[i+2].split("="); //get value
                                Double.parseDouble(nv[1]); //check if double
                            }catch(Exception ex){
                                valueIsNumber=false;
                                
                                try{
                                    String[] nv=pairs[i+2].split("="); //get value 
                                    dftime.parse(nv[1]);   //check if date with time
                                    valueIsDate=true;
                                    useNative=true;
                               }catch(Exception exe){
                                   valueIsDate=false;
                                   
                                   try{
                                        String[] nv=pairs[i+2].split("="); //get value 
                                        df.parse(nv[1]);   //check if date only
                                        valueIsDate=true;
                                        useNative=true;
                                    }catch(Exception exee){
                                        valueIsDate=false;

                                    }
                                   
                               }
                            }
                        }
                        
                        
                        
                        
                    }
                    switch (namevalue[0]) {
                        case "field":
                            if(first && !query.contains(" where ")){
                                //check first if date
                                if(valueIsNumber || valueIsDate){
                                     query+= " where c." + namevalue[1] + "";
                                }else{
                                    query+= " where lower(c." + namevalue[1] + ")";
                                }
                                first=false;
                            }else{
                                if(valueIsNumber || valueIsDate){
                                     query+= " and c." + namevalue[1] + "";
                                }else{
                                    query+= " and lower(c." + namevalue[1] + ")";
                                }
                            }
                            break;
                        case "type":
                            switch (namevalue[1]) {
                                case "equal":
                                    query+= "=";
                                    break;
                                case "notequal":
                                    query+= "!=";
                                    break;
                                case "like":
                                    query+= " like ";
                                    break;
                                case "greaterthan":
                                    query+= ">";
                                    break;
                                case "lesserthan":
                                    query+= "<";
                                    break;
                                case "greaterthanequal":
                                    query+= ">=";
                                    break;
                                case "lesserthanequal":
                                    query+= "<=";
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "value":
                            if(valueIsNumber && !valueIsDate){
                               //query+= "'" + namevalue[1] +"'";
                               query+= "" + namevalue[1] +"";
                           }else if(valueIsDate){
//                            try {
                                //query+= "to_date('" + namevalue[1] +"','"+DateFormatters.timestampFormatTimeStr+"')";
                                   //query+= "'01/01/2018'";
                                query+= "'"+namevalue[1]+"'";
//                            } catch (ParseException ex) {
//                                Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                            
                           }else{
                                String like = query.substring(query.length()-5, query.length()-1 );
                                if(like.equals("like")){
                                    query+= "'%"+namevalue[1].toLowerCase()+"%'";
                                }else{
                                    query+= "'"+namevalue[1].toLowerCase()+"'";
                                }
                           }
                            break;
                        case "orderby":
                            String[] orderbys = namevalue[1].split(",");
                            for(String o : orderbys){
                                if(orderby.equals("")){
                                    orderby = " order by c."+o;
                                }else{
                                    orderby += ", c."+o;
                                }
                            }
                        default:
                            break;
                    }
                    
                    //reset valuIsNumber, check last pair if value
                    if(namevalue[0].equals("value")){
                        valueIsNumber=true;
                    }
                    i++;
                }
            }else{
                return null;
            }
        if(!fishy){
            javax.persistence.Query q = getEntityManager().createQuery(query+orderby);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        }else{
            return null;
        }
    }
    
}
