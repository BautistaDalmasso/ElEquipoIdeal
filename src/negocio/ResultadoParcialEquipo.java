package negocio;

public class ResultadoParcialEquipo {
	private EstadisticasDeBusqueda estadisticas;
	private Equipo equipoEncontrado;
	
	public ResultadoParcialEquipo(EstadisticasDeBusqueda estadisticas, Equipo equipoEncontrado) {
		this.estadisticas = estadisticas.copiar();
		this.equipoEncontrado = equipoEncontrado.copiar();
	}
	
	public int getCasosConsiderados() {
		return estadisticas.getCasosConsiderados();
	}
	public int getCasosDescartados() {
		return estadisticas.getCasosDescartados();
	}
	
	public Equipo getEquipoEncontrado() {
		return equipoEncontrado;
	}
	public int getCalificacionTotal() {
		return equipoEncontrado.getCalificacionTotal();
	}
}
