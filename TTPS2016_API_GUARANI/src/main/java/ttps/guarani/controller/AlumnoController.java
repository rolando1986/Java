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

import ttps.guarani.persistence.model.Alumno;
import ttps.guarani.persistence.model.Credencial;
import ttps.guarani.persistence.service.AlumnoService;


@RestController
@RequestMapping(value = "/alumnos")
public class AlumnoController{

    @Autowired
    AlumnoService alumnoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Alumno>> listAllUsers() {
        List<Alumno> users = alumnoService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Alumno>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> getUser(@PathVariable("id") long id) {
        Alumno user = alumnoService.find(id);
        if (user == null) {
            return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Alumno>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/chequearlogin", method = RequestMethod.POST)
    public ResponseEntity<Void> chequearlogin(@RequestBody Credencial credencial) {
        if (!alumnoService.chequearlogin(credencial)) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}