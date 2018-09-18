/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilmico.locapp.store.service;

import com.pilmico.util.PfcConstant;
import com.pilmico.locapp.store.LocAppStores;
import com.pilmico.util.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ejb.Stateless;
/**
 *
 * @author MCadiente
 */

@Stateless
public class LocAppStoresAllFacadeREST extends AbstractFacade<LocAppStores> {

    @PersistenceContext(unitName = "LocAppWebIntegrationPU")
    
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    
    public LocAppStoresAllFacadeREST() {
        super(LocAppStores.class);
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
