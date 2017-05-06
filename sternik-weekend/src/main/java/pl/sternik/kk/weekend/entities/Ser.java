package pl.sternik.kk.weekend.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@XmlRootElement
public class Ser {

//    @NotNull
//    @Id
    private Long numerKatalogowy;
	
//	@NotNull
	private String opis;
//	@NotEmpty
	private String nazwa;
//	@NotNull
	private BigDecimal cenaNabycia;
//	@NotNull
	private Date dataNabycia;
//	@NotEmpty
	private String krajPochodzenia;
//	@NotNull
	private Status status;

	
	
	public static Ser produceSer(Long numerKatalogowy, String krajPochodzenia, String nazwa, String opis,
			Date dataNabycia, BigDecimal cenaNabycia, Status status) {
		Ser m = new Ser();
		m.numerKatalogowy = numerKatalogowy;
		m.krajPochodzenia = krajPochodzenia;
		m.opis = opis;
		m.nazwa = nazwa;
		m.cenaNabycia = cenaNabycia;
		m.dataNabycia = dataNabycia;
		m.status = status;
		return m;
	}

	public Long getNumerKatalogowy() {
		return numerKatalogowy;
	}


	public String getOpis() {
		return opis;
	}

	public String getNazwa() {
		return nazwa;
	}

	public BigDecimal getCenaNabycia() {
		return cenaNabycia;
	}

	public Date getDataNabycia() {
		return dataNabycia;
	}

	public String getKrajPochodzenia() {
		return krajPochodzenia;
	}

	public Status getStatus() {
		return status;
	}

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.numerKatalogowy = numerKatalogowy;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setCenaNabycia(BigDecimal cenaNabycia) {
		this.cenaNabycia = cenaNabycia;
	}

	public void setDataNabycia(Date dataNabycia) {
		this.dataNabycia = dataNabycia;
	}

	public void setKrajPochodzenia(String krajPochodzenia) {
		this.krajPochodzenia = krajPochodzenia;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((cenaNabycia == null) ? 0 : cenaNabycia.hashCode());
		result = prime * result + ((dataNabycia == null) ? 0 : dataNabycia.hashCode());
		result = prime * result + ((krajPochodzenia == null) ? 0 : krajPochodzenia.hashCode());
		result = prime * result + ((numerKatalogowy == null) ? 0 : numerKatalogowy.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((nazwa == null) ? 0 : nazwa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ser other = (Ser) obj;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (cenaNabycia == null) {
			if (other.cenaNabycia != null)
				return false;
		} else if (!cenaNabycia.equals(other.cenaNabycia))
			return false;
		if (dataNabycia == null) {
			if (other.dataNabycia != null)
				return false;
		} else if (!dataNabycia.equals(other.dataNabycia))
			return false;
		if (krajPochodzenia == null) {
			if (other.krajPochodzenia != null)
				return false;
		} else if (!krajPochodzenia.equals(other.krajPochodzenia))
			return false;
		if (numerKatalogowy == null) {
			if (other.numerKatalogowy != null)
				return false;
		} else if (!numerKatalogowy.equals(other.numerKatalogowy))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (nazwa == null) {
			if (other.nazwa != null)
				return false;
		} else if (!nazwa.equals(other.nazwa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ser [numerKatalogowy=" + numerKatalogowy + ", Opis=" + opis + ", nazwa="
				+ nazwa + ", cenaNabycia=" + cenaNabycia + ", dataNabycia=" + dataNabycia + ", krajPochodzenia="
				+ krajPochodzenia + ", status=" + status + "]";
	}

}
