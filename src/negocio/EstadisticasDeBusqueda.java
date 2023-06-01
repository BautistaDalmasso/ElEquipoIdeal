package negocio;

public class EstadisticasDeBusqueda {
	private int casosConsiderados;
	private int casosDescartados;
	
	public EstadisticasDeBusqueda() {
		casosConsiderados = 0;
		casosDescartados = 0;
	}
	
	private EstadisticasDeBusqueda(int casosConsiderados, int casosDescartados) {
		this.casosConsiderados = casosConsiderados;
		this.casosDescartados = casosDescartados;
	}
	
	public EstadisticasDeBusqueda copiar() {
		return new EstadisticasDeBusqueda(casosConsiderados, casosDescartados);
	}
	
	public EstadisticasDeBusqueda obtenerSuma(EstadisticasDeBusqueda that) {
		return new EstadisticasDeBusqueda(casosConsiderados+that.casosConsiderados, casosDescartados+that.casosDescartados);
	}
	
	public int getCasosConsiderados() {
		return casosConsiderados;
	}
	public void casoConsiderado() {
		this.casosConsiderados++;
	}
	public int getCasosDescartados() {
		return casosDescartados;
	}
	public void casoDescartado() {
		this.casosDescartados++;
	}
}
