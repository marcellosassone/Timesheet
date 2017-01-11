package it.begear.models;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="dipendente")
public class Dipendente implements Cloneable {
	@Id
	@Column(name="cf")
	private String cf;
	@Column(name="nome")
	private String nome;
	@Column(name="cognome")
	private String cognome;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="admin")
	private int admin;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dip")
	@OrderBy("data DESC")
	private Set<Documento> documenti;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dip")
	@OrderBy("data DESC")
	private Set<Notifica> notifiche;
	
	public Dipendente() {
		//default
	}
	
	public Dipendente(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Dipendente(String cf, String nome, String cognome, String username, String password, int admin) {
		super();
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public Set<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(Set<Documento> documenti) {
		this.documenti = documenti;
	}

	@Override
	public String toString() {
		return "Dipendente [cf=" + cf + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", admin=" + admin + ", documenti=" + documenti + ", notifiche="
				+ notifiche + "]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Dipendente cloned = (Dipendente) super.clone();
		cloned.setDocumenti(new TreeSet<Documento>(this.getDocumenti()));
		return cloned;
	}
	
	
}
