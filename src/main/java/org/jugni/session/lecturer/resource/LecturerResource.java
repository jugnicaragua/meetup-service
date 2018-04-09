package org.jugni.session.lecturer.resource;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.jugni.session.lecturer.controller.LecturerMapper;
import org.jugni.session.lecturer.model.Lecturer;
import org.jugni.session.rest.validation.HeaderViolation;
import org.jugni.session.util.StringUtils;

/**
 *
 * @author aalaniz
 */
@ApplicationScoped
@Path("lecturers")
public class LecturerResource {

    private Map<String, Lecturer> lecturers;

    @Context
    UriInfo uriInfo;

    @PostConstruct
    public void init() {
        this.lecturers = new ConcurrentHashMap<>();
    }

    private Collection<Lecturer> filterLecturer(String name) {
        if (StringUtils.isEmpty(name)) {
            return lecturers.values();
        }

        return lecturers.values().stream()
                .filter(l -> l.getFullName().toUpperCase().contains(name.toUpperCase()))
                .collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Lecturer> allLecturer(@QueryParam("name") String name) {
        return filterLecturer(name);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public LecturerHolder allLecturerToXml() {
//        LecturerHolder holder = new LecturerHolder(lecturers.values());
//        return holder;
//    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String allLecturerToPlainXml(@QueryParam("name") String name) {
        return LecturerMapper.mapToXml(filterLecturer(name));
    }

    @GET
    @Path("{id}")
    public Response lecturer(@PathParam("id") String id) {
        Lecturer lecturer = lecturers.get(id);

        if (lecturer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(lecturer).build();
        }
    }

    @POST
    public Response add(@Valid Lecturer lecturer) {
        lecturer.generateId();
        String lecturerId = lecturer.getJugId();

        if (lecturers.containsKey(lecturerId)) {
            LecturerConstraintViolation violation = LecturerConstraintViolation.LECTURER_ALREADY_EXISTS;

            return HeaderViolation.badRequest(violation, lecturerId);
        }

        lecturers.put(lecturerId, lecturer);
        final URI id = UriBuilder.fromPath(uriInfo.getPath() + "/{id}").build(lecturerId);
        return Response.created(id).build();
    }

    @PUT
    public Response update(Lecturer lecturer) {
        String lecturerId = lecturer.getJugId();

        if (!lecturers.containsKey(lecturerId)) {
            LecturerConstraintViolation violation = LecturerConstraintViolation.LECTURER_DOESNOT_EXIST;

            return HeaderViolation.badRequest(violation, lecturerId);
        }

        lecturers.put(lecturerId, lecturer);
        return Response.ok(lecturer).build();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        lecturers.remove(id);
    }
}
