/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entities.Grant;
import Entities.GrantsSimilarity;
import java.util.List;
import javax.ejb.Stateless;
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

/**
 *
 * @author hmetaxa
 */
@Stateless
@Path("entities.grant")
public class GrantFacadeREST extends AbstractFacade<Grant> {

    @PersistenceContext(unitName = "OpenAireWebPU")
    private EntityManager em;

    public GrantFacadeREST() {
        super(Grant.class);
    }

    /*
     @POST
     @Override
     @Consumes({"application/xml", "application/json"})
     public void create(Grant entity) {
     super.create(entity);
     }

     @PUT
     @Override
     @Consumes({"application/xml", "application/json"})
     public void edit(Grant entity) {
     super.edit(entity);
     }

     @DELETE
     @Path("{id}")
     public void remove(@PathParam("id") String id) {
     super.remove(super.find(id));
     }

     @GET
     @Path("{id}")
     @Produces({"application/xml", "application/json"})
     public Grant find(@PathParam("id") String id) {
     return super.find(id);
     }

     @GET
     @Override
     @Produces({"application/xml", "application/json"})
     public List<Grant> findAll() {
     return super.findAll();
     }

     @GET
     @Path("{from}/{to}")
     @Produces({"application/xml", "application/json"})
     public List<Grant> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
     return super.findRange(new int[]{from, to});
     }
     */
    @GET
    @Path("{similarity}/{experimentId}")
    @Produces({"application/xml", "application/json"})
    public List<Grant> findSimilar(@PathParam("similarity") double similarity, @PathParam("experimentId") String experimentId) {

        List<Grant> grantPairs = (List<Grant>) em.createNamedQuery("Grant.findAll")
                .getResultList();

        return grantPairs;
        /*(List<GrantsSimilarity>) em.createNamedQuery("GrantsSimilarity.findSimilar")
         .setParameter("similarity", similarity)
         .setParameter("experimentId", experimentId)
         .getResultList();*/
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
