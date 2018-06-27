package ttps.persistence.model.access;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ttps.persistence.model.board.Pizarra;
import ttps.persistence.model.board.Publicacion;

@Entity
@DiscriminatorValue("ESCRITURA")
public class Escritura extends Visualizacion{

	/**
	 *
	 */
	private static final long serialVersionUID = -7242532540134490395L;

	public Escritura() {
		super();
	}

	public Escritura(Pizarra pizarra) {
		super(pizarra);
	}

	public Escritura(long posicion, Pizarra pizarra) {
		super(posicion,pizarra);
	}

	@Override
	public Boolean escribir(Publicacion publicacion) {
		publicacion.setPizarra(getPizarra());
		return this.pizarra.add(publicacion);
	}

	@Override
	public String toString() {
		return "Acceso [id=" + id + " posicion=" + getPosicion() + " Acceso=Escritura" + " visible="+ getVisible() + " pizarra=" + pizarra.toString() + "]";
	}

	public Boolean hasEscritura() {
		return true;
	}

	public String getTipo() {
		return "ESCRITURA";
	}
}
