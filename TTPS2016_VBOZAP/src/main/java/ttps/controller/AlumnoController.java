/**
 *
 * @author Israel
 *
 */

package ttps.controller;

import java.util.ArrayList;
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

import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.notification.Medio;
import ttps.persistence.model.notification.NotificacionCorreo;
import ttps.persistence.model.notification.NotificacionFacebook;
import ttps.persistence.model.user.impl.Alumno;
import ttps.persistence.service.AlumnoService;
import ttps.persistence.service.MedioService;
import ttps.persistence.service.PizarraService;


@RestController
public class AlumnoController{

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    MedioService medioService;

    @Autowired
    PizarraService pizarraService;

    @RequestMapping(value = "/alumno/", method = RequestMethod.GET)
    public ResponseEntity<List<Alumno>> listAllusers() {
        List<Alumno> users = alumnoService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Alumno>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/alumno/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> getUser(@PathVariable("id") long id) {
        System.out.println("Buscando Alumno con id " + id);
        Alumno user = alumnoService.find(id);
        if (user == null) {
            System.out.println("Alumno with id " + id + " not found");
            return new ResponseEntity<Alumno>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Alumno>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/alumno/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Alumno user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creando Alumno " + user.getNickname());

        if (alumnoService.isUserExist(user)) {
            System.out.println("El Alumno con nombre " + user.getNickname() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        alumnoService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/alumno/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/alumno/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Alumno> updateUser(@PathVariable("id") long id, @RequestBody Alumno user) {
        System.out.println("Actualizando Alumno " + id);

        Alumno currentUser = alumnoService.find(id);

        if (currentUser==null) {
            System.out.println("Alumno con id " + id + " no funciona");
            return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
        }

        currentUser.actualizarAtributos(user);

        alumnoService.modify(currentUser);
        return new ResponseEntity<Alumno>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/alumno/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Alumno> deleteUser(@PathVariable("id") long id) {
        System.out.println("Buscando y borrando Alumno con id " + id);

        Alumno user = alumnoService.find(id);
        if (user == null) {
            System.out.println("No se puede borrar. Alumno con id " + id + " no funciona");
            return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
        }

        alumnoService.delete(id);
        return new ResponseEntity<Alumno>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/administrador/alumno/", method = RequestMethod.DELETE)
    public ResponseEntity<Alumno> deleteAllUsers() {
        System.out.println("Borrando todos los Alumnos");

        alumnoService.deleteAll();
        return new ResponseEntity<Alumno>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/alumno/{idAlumno}/medio/facebook/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMedioFacebook(@PathVariable("idAlumno") long idAlumno, @RequestBody NotificacionFacebook medio, UriComponentsBuilder ucBuilder) {
        return createMedio(idAlumno, medio, ucBuilder);
    }

    @RequestMapping(value = "/alumno/{idAlumno}/medio/correo/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMedioCorreo(@PathVariable("idAlumno") long idAlumno, @RequestBody NotificacionCorreo medio, UriComponentsBuilder ucBuilder) {
    	 return createMedio(idAlumno, medio, ucBuilder);
    }

    private ResponseEntity<Void> createMedio(long idAlumno, Medio medio, UriComponentsBuilder ucBuilder){
      System.out.println("Creando Medio " + medio);

      Alumno user = alumnoService.find(idAlumno);
      if (user == null) {
          System.out.println("El Alumno con id " + idAlumno + " no funciona");
          return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
      }
      medio.setAlumno(user);
      user.addMedio(medio);
      medioService.create(medio);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(ucBuilder.path("/alumno/{idAlumno}/medio/{id}").buildAndExpand(idAlumno,medio.getId()).toUri());
      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = { "/alumno/{idAlumno}/medio/{id}" }, method = RequestMethod.PUT)
    public ResponseEntity<Medio> updateMedio(@PathVariable("idAlumno") long idAlumno, @PathVariable("id") long id, @RequestBody Medio medio) {
        System.out.println("Actualizando Medio " + medio);

        Alumno alu = alumnoService.find(idAlumno);

        if (alu==null) {
            System.out.println("El alumno con id " + id + " no funciona");
            return new ResponseEntity<Medio>(HttpStatus.NOT_FOUND);
        }

        Medio currentM = medioService.find(id);

        if (currentM==null) {
            System.out.println("Medio con id " + id + " no funciona");
            return new ResponseEntity<Medio>(HttpStatus.NOT_FOUND);
        }

        currentM.actualizarAtributos(medio);

        medioService.modify(currentM);
        return new ResponseEntity<Medio>(currentM, HttpStatus.OK);
    }

    @RequestMapping(value = "/alumno/{idAlumno}/medio/", method = RequestMethod.GET)
    public ResponseEntity<List<Medio>> listAllMedios(@PathVariable("idAlumno") long idAlumno) {
        Alumno user = alumnoService.find(idAlumno);
        if(user == null){
            return new ResponseEntity<List<Medio>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Medio>>(user.getMedios(), HttpStatus.OK);
    }

	/**
	 * Borra el medio de notificacion con id del Alumno
	 * @param idAlumno
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/alumno/{idAlumno}/medio/{id}" }, method = RequestMethod.DELETE)
    public ResponseEntity<Medio> deleteMedio(@PathVariable("idAlumno") long idAlumno, @PathVariable("id") long id) {
        System.out.println("Buscando & borrando Medio con id " + id);

        Alumno alu = alumnoService.find(idAlumno);

        if (alu==null) {
            System.out.println("El alumno con id " + id + " no funciona");
            return new ResponseEntity<Medio>(HttpStatus.NOT_FOUND);
        }

        Medio medio = medioService.find(id);

        if (medio == null && alu.getMedios().contains(medio)) {
            System.out.println("No se puede borrar. Medio con id " + id + " no funciona");
            return new ResponseEntity<Medio>(HttpStatus.NOT_FOUND);
        }

        medioService.delete(id);
        return new ResponseEntity<Medio>(HttpStatus.NO_CONTENT);
    }

	/**
	 * Borra todos los medios de notificacion del Alumno
	 * @param idAlumno
	 * @return
	 */
	@RequestMapping(value = { "/alumno/{idAlumno}/medio/" }, method = RequestMethod.DELETE)
    public ResponseEntity<Medio> deleteAllMedios(@PathVariable("idAlumno") long idAlumno) {
        System.out.println("Borrando todos los Medios");
        Alumno alu = alumnoService.find(idAlumno);

        if (alu==null) {
            System.out.println("El alumno con id " + idAlumno + " no funciona");
            return new ResponseEntity<Medio>(HttpStatus.NOT_FOUND);
        }

        alu.setMedios(new ArrayList<Medio>());

        alumnoService.modify(alu);
        return new ResponseEntity<Medio>(HttpStatus.NO_CONTENT);
    }

    /**
     * Agrega un Alumno a la lista de observadores en la Pizarra
     * @param idAlumno
     * @param idObserver
     * @return
     */
    @RequestMapping(value = "/alumno/{idAlumno}/pizarra/{id}/seguir", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getUser(@PathVariable("idAlumno") long idAlumno, @PathVariable("id") long idObserver) {
        Alumno user = alumnoService.find(idAlumno);
        if (user == null) {
            System.out.println("Alumno con id " + idAlumno + " no funciona");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        PizarraImpl observado = pizarraService.find(idObserver);
        if (observado == null) {
            System.out.println("Observado con id " + idObserver + " no funciona");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        if (!observado.add(user)) {
            System.out.println("Ya esta siguiendo la cartelera");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        pizarraService.modify(observado);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}