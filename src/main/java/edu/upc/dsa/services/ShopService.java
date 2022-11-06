package edu.upc.dsa.services;


import edu.upc.dsa.ShopManager;
import edu.upc.dsa.ShopManagerImpl;

import edu.upc.dsa.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")
public class ShopService {

    private ShopManager tm;

    public ShopService() {
        this.tm = ShopManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addProduct("Shirt", "Green shirt", 74.1);
            this.tm.addProduct("Trousers", "Green trousers", 54.2);
            this.tm.addProduct("Shoes", "Nice Shoes", 21.5);
        }


    }

    @GET
    @ApiOperation(value = "get all Products", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Object.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        List<Product> objects = this.tm.findAll();

        GenericEntity<List<Object>> entity = new GenericEntity<List<Product>>(objects) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Object.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Object t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Object t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteObject(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Object object) {

        Object t = this.tm.updateObject(object);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Object.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Object object) {

        if (object.getSinger()==null || object.getTitle()==null)  return Response.status(500).entity(object).build();
        this.tm.createObject(object);
        return Response.status(201).entity(object).build();
    }

}
*/
