package negocio;

import java.util.Objects;

public class ParIncompatible {
	private String x;
	private String y;
	
	public ParIncompatible(String x, String y) {
		this.x = x;
		this.y = y;
	}
	
	public String getX() {
		return x;
	}

	public String getY() {
		return y;
	}

	@Override
	public int hashCode() {
		// Bitwise XOR es una operacion conmutativa que nos asegura que el hash code va
		// a ser igual sin importar el orden en el que se agreguen las localidades:
		// es decir: parIncompatibleAB.hashCode() == parIncompatibleBA.hashCode()
		return Objects.hash(x) ^ Objects.hash(y);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ParIncompatible))
			return false;
		ParIncompatible other = (ParIncompatible) obj;
		return (Objects.equals(x, other.x) && Objects.equals(y, other.y))
				|| Objects.equals(x, other.y) && Objects.equals(y, other.x);
	}
}
