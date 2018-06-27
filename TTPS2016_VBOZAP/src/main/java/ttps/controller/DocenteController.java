/**
 *
 * @author Israel
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

import ttps.persistence.model.user.impl.Alumno;
import ttps.persistence.model.user.impl.Docente;
import ttps.persistence.service.DocenteService;


@RestController
public class DocenteController{

    @Autowired
    DocenteService docenteService;

    @RequestMapping(value = "/docente/", method = RequestMethod.GET)
    public ResponseEntity<List<Docente>> listAllDocentes() {
        List<Docente> users = docenteService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Docente>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Docente>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/docente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Docente> getDocente(@PathVariable("id") long id) {
        System.out.println("Buscando Docente con id " + id);
        Docente user = docenteService.find(id);
        if (user == null) {
            System.out.println("Docente con id " + id + " no funciona");
            return new ResponseEntity<Docente>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Docente>(user, HttpStatus.OK);
    }

    /**
     * Devuelve los interesados en la cartelera que el Docente tiene permisos de escritura
     * @param id
     * @return
     */
	@RequestMapping(value = "/docente/{idDocente}/pizarra/{idPizarra}/interesados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Alumno>> getInteresados(@PathVariable("idDocente") long idDocente, @PathVariable("idPizarra") long idPizarra) {
        Docente user = docenteService.find(idDocente);
        if (user == null) {
            System.out.println("Docente con id " + idDocente + " no funciona");
            return new ResponseEntity<List<Alumno>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Alumno>>(user.getInteresados(idPizarra), HttpStatus.OK);
    }

	@RequestMapping(value = "/docente/{idDocente}/pizarra/interesados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Alumno>> getTodosLosInteresados(@PathVariable("idDocente") long idDocente) {
        Docente user = docenteService.find(idDocente);
        if (user == null) {
            System.out.println("Docente con id " + idDocente + " no funciona");
            return new ResponseEntity<List<Alumno>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Alumno>>(user.getInteresados(), HttpStatus.OK);
    }

    @RequestMapping(value = "/docente/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDocente(@RequestBody Docente user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creando Docente " + user.getNickname());

        if (docenteService.isUserExist(user)) {
            System.out.println("El Docente con nombre " + user.getNickname() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        docenteService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/docente/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/docente/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Docente> updateDocente(@PathVariable("id") long id, @RequestBody Docente user) {
        System.out.println("Actualizando Docente " + id);

        Docente currentUser = docenteService.find(id);

        if (currentUser==null) {
            System.out.println("Docente con id " + id + " no funciona");
            return new ResponseEntity<Docente>(HttpStatus.NOT_FOUND);
        }

        currentUser.actualizarAtributos(user);

        docenteService.modify(currentUser);
        return new ResponseEntity<Docente>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/docente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Docente> deleteDocente(@PathVariable("id") long id) {
        System.out.println("Buscando y borrando Docente con id " + id);

        Docente user = docenteService.find(id);
        if (user == null) {
            System.out.println("No se puede borrar. Docente con id " + id + " no funciona");
            return new ResponseEntity<Docente>(HttpStatus.NOT_FOUND);
        }

        docenteService.delete(id);
        return new ResponseEntity<Docente>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/administrador/docente/", method = RequestMethod.DELETE)
    public ResponseEntity<Docente> deleteAllDocentes() {
        System.out.println("Borrando todos los Docentes");

        docenteService.deleteAll();
        return new ResponseEntity<Docente>(HttpStatus.NO_CONTENT);
    }

}