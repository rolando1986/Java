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

import ttps.persistence.model.user.impl.Administrador;
import ttps.persistence.service.AdministradorService;
import ttps.persistence.service.UserService;


@RestController
public class AdministradorController{

    @Autowired
    AdministradorService adminService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/administrador/", method = RequestMethod.GET)
    public ResponseEntity<List<Administrador>> listAllUsers() {
        List<Administrador> users = adminService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Administrador>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Administrador>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> getUser(@PathVariable("id") long id) {
        System.out.println("Buscando Administrador con id " + id);
        Administrador user = adminService.find(id);
        if (user == null) {
            System.out.println("Administrador con id " + id + " no funciona");
            return new ResponseEntity<Administrador>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Administrador>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Administrador user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creando Administrador " + user.getNickname());

        if (adminService.isUserExist(user)) {
            System.out.println("El Administrador con nombre " + user.getNickname() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        adminService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrador/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/administrador/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Administrador> updateUser(@PathVariable("id") long id, @RequestBody Administrador user) {
        System.out.println("Actualizando Administrador " + id);

        Administrador currentUser = adminService.find(id);

        if (currentUser==null) {
            System.out.println("El Administrador con id " + id + " no funciona");
            return new ResponseEntity<Administrador>(HttpStatus.NO_CONTENT);
        }

        currentUser.actualizarAtributos(user);

        adminService.modify(currentUser);
        return new ResponseEntity<Administrador>(currentUser, HttpStatus.OK);
    }
}