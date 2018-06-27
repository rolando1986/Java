/**
 *
 * @author Israel
 *
 */

package ttps.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.media.Archivo;
import ttps.persistence.service.ArchivoService;
import ttps.persistence.service.PublicacionService;

@RestController
public class ArchivoController{
	final String UPLOAD_DIRECTORY ="publicacion";
    @Autowired
    ArchivoService archivoService;
    @Autowired
    PublicacionService publicacionService;
    @Autowired
    ServletContext context;


    @RequestMapping(value = "/publicacion/{idPub}/archivo/", method = RequestMethod.GET)
    public ResponseEntity<List<Archivo>> listAllArchivos(@PathVariable("idPub") long id) {
        List<Archivo> arch = archivoService.findByPublicacionId(id);
        if(arch.isEmpty()){
            return new ResponseEntity<List<Archivo>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Archivo>>(arch, HttpStatus.OK);
    }

    @RequestMapping(value = "/publicacion/{idPub}/archivo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Archivo> listArchivo(@PathVariable("idPub") long idPub, @PathVariable("id") long id) {
        Archivo arch = archivoService.findByPublicacionId(idPub, id);
        if(arch == null){
            return new ResponseEntity<Archivo>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Archivo>(arch, HttpStatus.OK);
    }

	@RequestMapping(value = { "/administrador/{idUser}/publicacion/{idPub}/archivo/", "/publicador/{idUser}/publicacion/{idPub}/archivo/",
			"/docente/{idUser}/publicacion/{idPub}/archivo/" }, method = RequestMethod.POST)
    public ResponseEntity<Void> createArchivo(@PathVariable("idUser") long userId, @PathVariable("idPub") long publicacionId, @RequestParam("file") MultipartFile file) {
		Publicacion publicacion = publicacionService.find(publicacionId);
		if (publicacion != null && !file.isEmpty()) {
			try {
				Archivo arch = new Archivo(file.getOriginalFilename(),
						UPLOAD_DIRECTORY + File.separator + publicacion.getId() + File.separator, publicacion);
				if(publicacion.add(arch) && publicacion.getPublicador().getId() == userId)
				{
					System.out.println("Creando Archivo " + file.getName());
					byte[] bytes = file.getBytes();
					File dir = new File(context.getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + publicacion.getId());
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					archivoService.create(arch);

					return new ResponseEntity<Void>(HttpStatus.CREATED);
				}
				else
					throw new Exception();
			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }

	@RequestMapping(value = { "/administrador/{idUser}/publicacion/{idPub}/archivo/{id}", "/publicador/{idUser}/publicacion/{idPub}/archivo/{id}",
	"/docente/{idUser}/publicacion/{idPub}/archivo/{id}" }, method = RequestMethod.DELETE)
    public ResponseEntity<Archivo> deleteArchivo(@PathVariable("idUser") long userId, @PathVariable("idPub") long publicacionId, @PathVariable("id") long id) {
		System.out.println("Buscando & borrando Archivo con id " + id);
        Publicacion publicacion = publicacionService.find(publicacionId);
        if(publicacion == null){
        	System.out.println("No se puede borrar. La publicacion con id " + id + " not funciona");
            return new ResponseEntity<Archivo>(HttpStatus.NOT_FOUND);
        }
        else{
        	if(publicacion.getPublicador().getId() != userId)
        		return new ResponseEntity<Archivo>(HttpStatus.UNAUTHORIZED);
        }
        Archivo fileReg = null;
        for(Archivo arch: publicacion.getArchivos()){
        	if(arch.getId() == id)
        		fileReg = arch;
        }

        if(fileReg == null){
        	System.out.println("No se puede borrar. Archivo con id " + id + " no funciona");
        	return new ResponseEntity<Archivo>(HttpStatus.NOT_FOUND);
        }
        File file = new File (context.getRealPath("") + fileReg.getUri() + File.separator + fileReg.getNombre());

		if(!file.delete()){
            return new ResponseEntity<Archivo>(HttpStatus.NOT_FOUND);
        }

        archivoService.delete(id);

        return new ResponseEntity<Archivo>(HttpStatus.NO_CONTENT);
    }
}