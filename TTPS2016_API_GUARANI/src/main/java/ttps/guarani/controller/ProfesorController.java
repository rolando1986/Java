/**
 *
 * @author Israel
 *
 */

package ttps.guarani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ttps.guarani.persistence.model.Credencial;
import ttps.guarani.persistence.model.Profesor;
import ttps.guarani.persistence.service.ProfesorService;


@RestController
@RequestMapping(value = "/profesores")
public class ProfesorController{

    @Autowired
    ProfesorService docenteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Profesor>> listAllUsers() {
        List<Profesor> users = docenteService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Profesor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Profesor>>(users, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profesor> getUser(@PathVariable("id") long id) {
        Profesor user = docenteService.find(id);
        if (user == null) {
            return new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Profesor>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/chequearlogin", method = RequestMethod.POST)
    public ResponseEntity<Void> chequearlogin(@RequestBody Credencial credencial) {
        if (!docenteService.chequearlogin(credencial)) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}