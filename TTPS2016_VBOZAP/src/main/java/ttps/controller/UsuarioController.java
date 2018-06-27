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

import ttps.persistence.model.user.Usuario;
import ttps.persistence.service.UserService;


@RestController
@RequestMapping("/administrador")
public class UsuarioController{

    @Autowired
    UserService userService;

    @RequestMapping(value = "/usuario/", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listAllUsers() {
        List<Usuario> users = userService.find();
        if(users.isEmpty()){
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUser(@PathVariable("id") long id) {
        System.out.println("Buscando Usuario con id " + id);
        Usuario user = userService.find(id);
        if (user == null) {
            System.out.println("Usuario con id " + id + " no funciona");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Usuario user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creando Usuario " + user.getNickname());

        if (userService.isUserExist(user)) {
            System.out.println("El Usuario con nombre " + user.getNickname() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody Usuario user) {
        System.out.println("Actualizando Usuario " + id);

        Usuario currentUser = userService.find(id);

        if (currentUser==null) {
            System.out.println("Usuario con id " + id + " no funciona");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        currentUser.actualizarAtributos(user);
        userService.modify(currentUser);
        return new ResponseEntity<Usuario>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") long id) {
        System.out.println("Buscando y borrando Usuario con id " + id);

        Usuario user = userService.find(id);
        if (user == null) {
            System.out.println("No se puede borrar. Usuario con id " + id + " no funciona");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }
}