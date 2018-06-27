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

import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.service.PizarraService;

@RestController
public class CarteleraController{

    @Autowired
    PizarraService pizarraService;

    @RequestMapping(value = "/pizarra/", method = RequestMethod.GET)
    public ResponseEntity<List<PizarraImpl>> listAllPizarras() {
        List<PizarraImpl> pizarras = pizarraService.find();
        if(pizarras.isEmpty()){
            return new ResponseEntity<List<PizarraImpl>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PizarraImpl>>(pizarras, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizarra/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PizarraImpl> getPizarra(@PathVariable("id") long id) {
        System.out.println("Fetching Pizarra with id " + id);
        PizarraImpl pizarra = pizarraService.find(id);
        if (pizarra == null) {
            System.out.println("Pizarra with id " + id + " not found");
            return new ResponseEntity<PizarraImpl>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PizarraImpl>(pizarra, HttpStatus.OK);
    }

    // Agregar accesos visualizacion a todos los usuarios cuando se agrega una pizarra
    @RequestMapping(value = "/administrador/pizarra/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPizarra(@RequestBody PizarraImpl pizarra, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Pizarra " + pizarra.getNombre());

        if (pizarraService.isExist(pizarra)) {
            System.out.println("La pizarra con nombre " + pizarra.getNombre() + " ya existe!");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        pizarraService.create(pizarra);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pizarra/{id}").buildAndExpand(pizarra.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/administrador/pizarra/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PizarraImpl> updatePizarra(@PathVariable("id") long id, @RequestBody PizarraImpl pizarra) {
        System.out.println("Actualizando pizarra " + id);

        PizarraImpl currentP = pizarraService.find(id);

        if (currentP==null) {
            System.out.println("Pizarra con id " + id + " no funciona");
            return new ResponseEntity<PizarraImpl>(HttpStatus.NOT_FOUND);
        }

        currentP.actualizarAtributos(pizarra);

        pizarraService.modify(currentP);
        return new ResponseEntity<PizarraImpl>(currentP, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/pizarra/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PizarraImpl> deletePizarra(@PathVariable("id") long id) {
        System.out.println("Buscando & borrando Pizarra con id " + id);

        PizarraImpl pizarra = pizarraService.find(id);
        if (pizarra == null) {
            System.out.println("No se puede borrar. Pizarra con id " + id + " not funciona");
            return new ResponseEntity<PizarraImpl>(HttpStatus.NOT_FOUND);
        }

        pizarraService.delete(id);
        return new ResponseEntity<PizarraImpl>(HttpStatus.NO_CONTENT);
    }

    /**
     * Borra todas las pizarras
     */
    @RequestMapping(value = "/administrador/pizarra/", method = RequestMethod.DELETE)
    public ResponseEntity<PizarraImpl> deleteAllPizarras() {
        System.out.println("Borrando todas las Pizarras");

        pizarraService.deleteAll();
        return new ResponseEntity<PizarraImpl>(HttpStatus.NO_CONTENT);
    }
}