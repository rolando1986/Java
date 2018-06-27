/**
 *
 * @author Israel
 * Solo el Administrador deberia utilizar el controller de Pizarra.
 * Los Otros tipos de Usuarios utilizan sus Accesos donde esta configurado la posicion
 * en la pantalla y si le interesa verla al usuario
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

import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.Usuario;
import ttps.persistence.service.PublicacionService;
import ttps.persistence.service.UserService;

@RestController
public class PublicacionController{

    @Autowired
    PublicacionService publicacionService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/pizarra/{idPizarra}/publicacion/", method = RequestMethod.GET)
    public ResponseEntity<List<Publicacion>> listAllPublicaciones(@PathVariable("idPizarra") long id) {
        List<Publicacion> publicacion = publicacionService.findByPizarraId(id);
        if(publicacion.isEmpty()){
            return new ResponseEntity<List<Publicacion>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Publicacion>>(publicacion, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizarra/{idPizarra}/publicacion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Publicacion> getPublicacion(@PathVariable("idPizarra") long pizarraId, @PathVariable("id") long id) {
        System.out.println("Fetching Publicacion with id " + id);
        Publicacion publicacion = publicacionService.findByPizarraId(pizarraId,id);
        if (publicacion == null) {
            System.out.println("Publicacion con id " + id + " no existe");
            return new ResponseEntity<Publicacion>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
    }

	@RequestMapping(value = { "/administrador/{idUser}/pizarra/{idPizarra}/publicacion/", "/docente/{idUser}/pizarra/{idPizarra}/publicacion/",
			"/publicador/{idUser}/pizarra/{idPizarra}/publicacion/" }, method = RequestMethod.POST)
    public ResponseEntity<Void> createPublicacion(@PathVariable("idUser") long userId, @PathVariable("idPizarra") long pizarraId, @RequestBody Publicacion publicacion, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Publicacion " + publicacion.getTitulo());

        // Buscar el usuario y valida que tenga acceso de escritura a la publicacion
        Usuario user = userService.find(userId);
		if(user.escribir(pizarraId, publicacion))
			publicacionService.create(publicacion);
		else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{pizarraId}/publicacion/{id}").buildAndExpand(pizarraId,publicacion.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = { "/administrador/{idUser}/pizarra/{idPizarra}/publicacion/{id}", "/docente/{idUser}/pizarra/{idPizarra}/publicacion/{id}",
    		"/publicador/{idUser}/pizarra/{idPizarra}/publicacion/{id}" }, method = RequestMethod.PUT)
    public ResponseEntity<Publicacion> updatePublicacion(@PathVariable("idUser") long userId, @PathVariable("idPizarra") long pizarraId, @PathVariable("id") long id, @RequestBody Publicacion publicacion) {
        System.out.println("Actualizando publicacion " + id);

        Publicacion currentP = publicacionService.findByPizarraId(pizarraId,id);

        if (currentP==null) {
            System.out.println("Publicacion con id " + id + " no funciona");
            return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
        }
        else
        {
			if(currentP.getPublicador().getId() == userId && currentP.getPublicador().accesoEscritura(pizarraId))
			{
				currentP.actualizarAtributos(publicacion);
				publicacionService.modify(currentP);
			}
			else
				return new ResponseEntity<Publicacion>(currentP, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Publicacion>(currentP, HttpStatus.OK);
    }

	@RequestMapping(value = { "/administrador/{idUser}/pizarra/{idPizarra}/publicacion/{id}", "/docente/{idUser}/pizarra/{idPizarra}/publicacion/{id}",
			"/publicador/{idUser}/pizarra/{idPizarra}/publicacion/{id}" }, method = RequestMethod.DELETE)
    public ResponseEntity<Publicacion> deletePublicacion(@PathVariable("idUser") long userId, @PathVariable("idPizarra") long pizarraId, @PathVariable("id") long id) {
        System.out.println("Buscando & borrando Publicacion con id " + id);

        Publicacion publicacion = publicacionService.findByPizarraId(pizarraId,id);
        if (publicacion == null) {
            System.out.println("No se puede borrar. Publicacion con id " + id + " not funciona");
            return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
        }
        else
        {
			if (publicacion.getPublicador().getTipo().equals("ADMINISTRADOR")
					|| publicacion.getPublicador().getId() == userId
							&& publicacion.getPublicador().accesoEscritura(pizarraId))
				publicacionService.delete(id);
			else
				return new ResponseEntity<Publicacion>(publicacion, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Publicacion>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = "/administrador/pizarra/{idPizarra}/publicacion/", method = RequestMethod.DELETE)
    public ResponseEntity<Publicacion> deleteAllPublicaciones() {
        System.out.println("Borrando todas las Publicaciones");

        publicacionService.deleteAll();
        return new ResponseEntity<Publicacion>(HttpStatus.NO_CONTENT);
    }
}