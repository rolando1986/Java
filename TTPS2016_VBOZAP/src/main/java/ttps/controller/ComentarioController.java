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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ttps.persistence.model.board.Comentario;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.service.ComentarioService;
import ttps.persistence.service.PublicacionService;

@RestController
public class ComentarioController{

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    PublicacionService publicacionService;

    @RequestMapping(value = "/publicacion/{idPub}/comentario/", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> listAllComentarios(@PathVariable("idPub") long id) {
        List<Comentario> comentario = comentarioService.findByPublicacionId(id);
        if(comentario.isEmpty()){
            return new ResponseEntity<List<Comentario>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Comentario>>(comentario, HttpStatus.OK);
    }

	@RequestMapping(value = { "/publicacion/{idPub}/comentario/" }, method = RequestMethod.POST)
    public ResponseEntity<Void> createComentario(@PathVariable("idPub") long publicacionId, @RequestBody Comentario comentario, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Comentario " + comentario.getTexto());
        Publicacion  pub = publicacionService.find(publicacionId);
        boolean addComentario = false;
        if(pub != null){
        	addComentario = pub.add(comentario);
        	if(addComentario){
        		comentarioService.create(comentario);
        	}
        }

        if(!addComentario)
        	return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/publicacion/{idPub}/comentario/{id}").buildAndExpand(publicacionId,comentario.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = { "/publicacion/{idPub}/comentario/{id}" }, method = RequestMethod.PUT)
    public ResponseEntity<Comentario> updateComentario(@PathVariable("idPub") long publicacionId, @PathVariable("id") long id, @RequestBody Comentario comentario) {
        System.out.println("Actualizando comentario " + id);

        Comentario currentC = comentarioService.findByPublicacionId(publicacionId,id);

        if (currentC==null) {
            System.out.println("Comentario con id " + id + " no funciona");
            return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
        }

        currentC.actualizarAtributos(comentario);

        comentarioService.modify(currentC);
        return new ResponseEntity<Comentario>(currentC, HttpStatus.OK);
    }

	@RequestMapping(value = { "/administrador/publicacion/{idPub}/comentario/{id}" }, method = RequestMethod.DELETE)
    public ResponseEntity<Comentario> deleteComentario(@PathVariable("idPub") long publicacionId, @PathVariable("id") long id) {
        System.out.println("Buscando & borrando Comentario con id " + id);

        Comentario comentario = comentarioService.findByPublicacionId(publicacionId,id);
        if (comentario == null) {
            System.out.println("No se puede borrar. Comentario con id " + id + " not funciona");
            return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
        }

        comentarioService.delete(id);
        return new ResponseEntity<Comentario>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = { "/administrador/comentario/" }, method = RequestMethod.DELETE)
    public ResponseEntity<Comentario> deleteAllComentarios() {
        System.out.println("Borrando todos los Comentarios");

        comentarioService.deleteAll();
        return new ResponseEntity<Comentario>(HttpStatus.NO_CONTENT);
    }
}