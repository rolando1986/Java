/**
 *
 * @author Israel
 * Hay una sola instancia en la base de datos de Publicador
 * Este representa al usuario anonimo que tiene permisos para publicar
 * sobre un grupo reducido de pizarras
 *
 */

package ttps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ttps.persistence.model.user.impl.Publicador;
import ttps.persistence.service.PublicadorService;


@RestController
public class PublicadorController{

    @Autowired
    PublicadorService publicadorService;

    @RequestMapping(value = "/publicador/", method = RequestMethod.GET)
    public ResponseEntity<List<Publicador>> listAllPublicadores() {
        List<Publicador> users = publicadorService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Publicador>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Publicador>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/publicador/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Publicador> getPublicador(@PathVariable("id") long id) {
        System.out.println("Buscando Publicador con id " + id);
        Publicador user = publicadorService.find(id);
        if (user == null) {
            System.out.println("Publicador con id " + id + " no funciona");
            return new ResponseEntity<Publicador>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Publicador>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/publicador/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPublicador(@RequestBody Publicador user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creando Publicador " + user.getNickname());

        if (publicadorService.isUserExist(user)) {
            System.out.println("El Publicador con nombre " + user.getNickname() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        publicadorService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/publicador/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/publicador/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Publicador> updatePublicador(@PathVariable("id") long id, @RequestBody Publicador user) {
        System.out.println("Actualizando Publicador " + id);

        Publicador currentUser = publicadorService.find(id);

        if (currentUser==null) {
            System.out.println("Publicador con id " + id + " no funciona");
            return new ResponseEntity<Publicador>(HttpStatus.NOT_FOUND);
        }

        currentUser.actualizarAtributos(user);

        publicadorService.modify(currentUser);
        return new ResponseEntity<Publicador>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/publicador/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Publicador> deletePublicador(@PathVariable("id") long id) {
        System.out.println("Buscando & Borrando Publicador con id " + id);

        Publicador user = publicadorService.find(id);
        if (user == null) {
            System.out.println("No se puede borrar. Publicador con id " + id + " no funciona");
            return new ResponseEntity<Publicador>(HttpStatus.NOT_FOUND);
        }

        publicadorService.delete(id);
        return new ResponseEntity<Publicador>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/administrador/publicador/", method = RequestMethod.DELETE)
    public ResponseEntity<Publicador> deleteAllPublicadores() {
        System.out.println("Borrando todos los Publicadores");

        publicadorService.deleteAll();
        return new ResponseEntity<Publicador>(HttpStatus.NO_CONTENT);
    }
}