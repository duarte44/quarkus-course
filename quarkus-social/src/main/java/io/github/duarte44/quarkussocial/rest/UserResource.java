package io.github.duarte44.quarkussocial.rest;

import io.github.duarte44.quarkussocial.domain.model.User;
import io.github.duarte44.quarkussocial.domain.repository.UserRepository;
import io.github.duarte44.quarkussocial.rest.dto.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON) //TIPO DE OBJETOS QUE VAI CONSUMIR NA REQUISIÇÃO
@Produces(MediaType.APPLICATION_JSON)  // VAI RETORNAR JSON
public class UserResource {


    private UserRepository repository;

    @Inject
    public UserResource(UserRepository repository){
        this.repository = repository;
    }

    @POST
    @Transactional //TRANSAÇÃO COM O BANCO
    public Response createUser( CreateUserRequest userRequest ){

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user); //salva a entidade no banco

        return Response.ok(user).build();

    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        User user = repository.findById(id);

        if(user != null) {
            repository.delete(user);
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }

    @Transactional
    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id , CreateUserRequest userData){
        User user = repository.findById(id);

        if(user != null) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
