/**
 *
 * @author Israel
 * Los Usuarios utilizan sus Accesos donde esta configurado la posicion
 * en la pantalla y si esta oculta o visible.
 * Tambien se utliza para saber si un usuario tiene permiso de escritura
 * sobre una pizarra
 *
 */

package ttps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import ttps.persistence.model.access.Escritura;
import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.board.Pizarra;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.user.Usuario;
import ttps.persistence.service.AccesoService;
import ttps.persistence.service.PizarraService;
import ttps.persistence.service.UserService;

@RestController
//@SessionAttributes(value = "user", types = {UsuarioImpl.class})
public class AccesoController{

    @Autowired
    AccesoService accesoService;

    @Autowired
    UserService userService;

    @Autowired
    PizarraService pizarraService;

    @RequestMapping(value = "/usuario/{userId}/acceso/", method = RequestMethod.GET)
    public ResponseEntity<List<Visualizacion>> listAllAccesos(@PathVariable("userId") long userId, HttpSession session) {
    	List<Visualizacion> accesos = accesoService.findByUserId(userId);
    	if(accesos.isEmpty()){
            return new ResponseEntity<List<Visualizacion>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Visualizacion>>(accesos, HttpStatus.OK);
    }

    // Crea un acceso de escriitura para el userId y la pizarraId
    @RequestMapping(value = "/administrador/accesoEscritura/usuario/{userId}/pizarra/{pizarraId}", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccesoEscritura(@PathVariable("userId") long userId,@PathVariable("pizarraId") long pizarraId, @RequestBody Escritura acceso, UriComponentsBuilder ucBuilder) {
    	return crearAcceso(userId, pizarraId, acceso, ucBuilder);
    }

    // Crea un acceso de lectura para el userId y la pizarraId
    @RequestMapping(value = "/administrador/accesoVisualizacion/usuario/{userId}/pizarra/{pizarraId}", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccesoVisualizacion(@PathVariable("userId") long userId,@PathVariable("pizarraId") long pizarraId, @RequestBody Visualizacion acceso, UriComponentsBuilder ucBuilder) {
    	return crearAcceso(userId, pizarraId, acceso, ucBuilder);
    }

	private ResponseEntity<Void> crearAcceso(long userId, long pizarraId, Visualizacion acceso,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creando Acceso " + acceso.getId());

    	Usuario user = userService.find(userId);
        if (user == null) {
            System.out.println("Usuario con id " + userId + " no funciona");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else
        {
        	Pizarra pizarra = pizarraService.find(pizarraId);
        	if (pizarra == null) {
                System.out.println("Pizarra con id " + pizarraId + " not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        	else
        	{
        		acceso.setPizarra((PizarraImpl) pizarra);
                if (!user.add(acceso)) {
                    System.out.println("El acceso para la pizarra " + pizarra.getId() + " ya existe o no tiene permisos!");
                    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                else
                	accesoService.create(acceso);
        	}
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/acceso/{id}").buildAndExpand(acceso.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/administrador/{idUser}/acceso/{id}", "/docente/{idUser}/acceso/{id}",
			"/publicador/{idUser}/acceso/{id}" }, method = RequestMethod.PUT)
    public ResponseEntity<Visualizacion> updateAcceso(@PathVariable("idUser") long idUser, @PathVariable("id") long id, @RequestBody Escritura acceso) {
        System.out.println("Actualizando Acceso " + id);

        Visualizacion currentAcceso = accesoService.find(id);

        if (currentAcceso==null) {
            System.out.println("Acceso con id " + id + " no funciona");
            return new ResponseEntity<Visualizacion>(HttpStatus.NOT_FOUND);
        }
        else
        {
        	if(currentAcceso.getUsuario().getId() == idUser){
        		currentAcceso.actualizarAtributos(acceso);
        		accesoService.modify(currentAcceso);
        	}
        	else
        		return new ResponseEntity<Visualizacion>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<Visualizacion>(currentAcceso, HttpStatus.OK);
    }

    @RequestMapping(value = "/administrador/acceso/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Visualizacion> deleteAcceso(@PathVariable("id") long id) {
        System.out.println("Buscando & borrando acceso con id " + id);

        Visualizacion acceso = accesoService.find(id);
        if (acceso == null) {
            System.out.println("No se puede borrar. El Acceso con id " + id + " not funciona");
            return new ResponseEntity<Visualizacion>(HttpStatus.NOT_FOUND);
        }

        accesoService.delete(id);
        return new ResponseEntity<Visualizacion>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/administrador/acceso/", method = RequestMethod.DELETE)
    public ResponseEntity<Visualizacion> deleteAllPizarras() {
        System.out.println("Borrando todas los Accesos");

        accesoService.deleteAll();
        return new ResponseEntity<Visualizacion>(HttpStatus.NO_CONTENT);
    }
}