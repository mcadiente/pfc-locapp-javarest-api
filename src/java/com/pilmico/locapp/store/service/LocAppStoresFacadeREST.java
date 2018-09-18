/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilmico.locapp.store.service;

import com.pilmico.email.PilmicoEmail;
import com.pilmico.locapp.store.LocAppActiveStores;
import com.pilmico.util.PfcConstant;
import com.pilmico.locapp.store.LocAppStores;
import com.pilmico.util.AbstractFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import org.json.JSONObject;

/**
 *
 * @author MCadiente
 */
@Path("stores")
public class LocAppStoresFacadeREST extends AbstractFacade<LocAppStores> {

    @PersistenceContext(unitName = "LocAppWebIntegrationPU")

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    public LocAppStoresFacadeREST() {
        super(LocAppStores.class);
    }

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(LocAppStores entity) {
//        super.create(entity);
//    }
    @PUT
    @Path("update/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@QueryParam("userId") String userId, @PathParam("id") Integer id, LocAppStores entity) {

        String api = "locapp/stores";
        String method = "put";
        String url = "https://apiwrapper.pilmico.com/oracrm/webresources/developerAccess?userId=" + userId + "&api=" + api + "&method=" + method;

        try {

            //check dev access
            String response = this.runCall(url);

            if (response != null && !response.equals("")) {
                JSONObject rs = new JSONObject(response);
                if (rs.getBoolean("allowed")) {
                    try {
                        LocAppStoresAllFacadeREST locAppStoresAllFacadeREST = new LocAppStoresAllFacadeREST();
                        LocAppStores existingStore = locAppStoresAllFacadeREST.find(id);

                        Date now = new Date();
                        existingStore.setIntegrationUpdateDate(entity.getIntegrationUpdateDate());
                        existingStore.setIntegrationStatus(entity.getIntegrationStatus());
                        existingStore.setCrmId(entity.getCrmId());
                        //locAppStoresAllFacadeREST.beginTransaction();
                        locAppStoresAllFacadeREST.edit(existingStore);
                        //locAppStoresAllFacadeREST.commitTransaction();
                    } catch (Exception e) {
                        throw new WebApplicationException(new Throwable(PfcConstant.UNAUTHORIZED), 400);
                    }
                } else {
                    throw new WebApplicationException(new Throwable(PfcConstant.UNAUTHORIZED), 401);
                }
            }

        } catch (WebApplicationException e) {
            PilmicoEmail email = new PilmicoEmail();
            String msg = email.sendMessage("Send Email API ERROR-", PfcConstant.TO_ERROR_EMAIL, "", "", "countREST", e.getMessage() + " " + url);
            e.printStackTrace();
            throw e;

        } catch (Exception e) {
            PilmicoEmail email = new PilmicoEmail();
            String msg = email.sendMessage("Send Email API ERROR-", PfcConstant.TO_ERROR_EMAIL, "", "", "countREST ", e.getMessage() + " " + url);
            e.printStackTrace();
            throw new WebApplicationException(new Throwable("Bad Request!"), 400);
        }

        //return Response.noContent().build();
    }

    @GET
    @Path("search/{filters}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LocAppActiveStores> findStores(@QueryParam("userId") String userId, @PathParam("filters") String filters) {

        String api = "locapp/stores";
        String method = "get";
        String url = "https://apiwrapper.pilmico.com/oracrm/webresources/developerAccess?userId=" + userId + "&api=" + api + "&method=" + method;

        try {

            //check dev access
            String response = this.runCall(url);

            if (response != null && !response.equals("")) {
                JSONObject rs = new JSONObject(response);
                if (rs.getBoolean("allowed")) {

                    //List<LocAppActiveStores> locAppActiveStores = new ArrayList<>();
                    try {

                        LocAppActiveStoresFacadeREST locAppActiveStoresFacadeREST = new LocAppActiveStoresFacadeREST();
                        return locAppActiveStoresFacadeREST.findAllByFilters(filters, "SELECT c FROM LocAppActiveStores c");

                    } catch (Exception e) {
                        throw new WebApplicationException(new Throwable(PfcConstant.UNAUTHORIZED), 400);
                    }
                } else {
                    throw new WebApplicationException(new Throwable(PfcConstant.UNAUTHORIZED), 401);
                }
            }

        } catch (WebApplicationException e) {
            PilmicoEmail email = new PilmicoEmail();
            String msg = email.sendMessage("Send Email API ERROR-", PfcConstant.TO_ERROR_EMAIL, "", "", "countREST", e.getMessage() + " " + url);
            e.printStackTrace();
            throw e;

        } catch (Exception e) {
            PilmicoEmail email = new PilmicoEmail();
            String msg = email.sendMessage("Send Email API ERROR-", PfcConstant.TO_ERROR_EMAIL, "", "", "countREST ", e.getMessage() + " " + url);
            e.printStackTrace();
            throw new WebApplicationException(new Throwable("Bad Request!"), 400);
        }

        return null;
    }

    protected EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("LocAppWebIntegrationPU");
        }
        if (em == null) {
        } else {
            em = entityManagerFactory.createEntityManager();
        }

        return em;

    }

    private String runCall(String path) {
        String returnStr = "";

        try {
            URL url = new URL(path); //range

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            if (conn.getResponseCode() == 200) {
                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    returnStr += output;
                }

                conn.disconnect();
            }
        } catch (MalformedURLException ex) {
            PilmicoEmail email = new PilmicoEmail();
            String msg = email.sendMessage("Send Email API ERROR-", PfcConstant.TO_ERROR_EMAIL, "", "", "runCall", ex.getMessage() + " " + path);
            ex.printStackTrace();
        } catch (IOException ex) {

            PilmicoEmail email = new PilmicoEmail();
            String msg = email.sendMessage("Send Email API ERROR-", PfcConstant.TO_ERROR_EMAIL, "", "", "runCall", ex.getMessage() + " " + path);
            ex.printStackTrace();
        }

        return returnStr;
    }

}
