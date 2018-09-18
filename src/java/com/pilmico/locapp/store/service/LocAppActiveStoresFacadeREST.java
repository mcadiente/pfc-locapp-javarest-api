/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilmico.locapp.store.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.pilmico.locapp.store.LocAppActiveStores;
import com.pilmico.util.AbstractFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MCadiente
 */

public class LocAppActiveStoresFacadeREST extends AbstractFacade<LocAppActiveStores> {

    @PersistenceContext(unitName = "LocAppWebIntegrationPU")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    public LocAppActiveStoresFacadeREST() {
        super(LocAppActiveStores.class);
    }

//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void create(LocAppActiveStores entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") String id, LocAppActiveStores entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") String id) {
//        super.remove(super.find(id));
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public LocAppActiveStores find(@PathParam("id") String id) {
//        return super.find(id);
//    }
//
//    @GET
//    @Override
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<LocAppActiveStores> findAll() {
//        return super.findAll();
//    }
//
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<LocAppActiveStores> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        if(entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("LocAppWebIntegrationPU");
        }
        if (em == null) {
            em = entityManagerFactory.createEntityManager();
        }

        return em;
    }
    
    
    
}
