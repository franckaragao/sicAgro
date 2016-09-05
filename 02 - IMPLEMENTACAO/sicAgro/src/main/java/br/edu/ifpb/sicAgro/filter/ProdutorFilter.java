package br.edu.ifpb.sicAgro.filter;

import java.util.Date;

public class ProdutorFilter {

	private static ProdutorFilter filter;

	private String name;
	private String cpf;
	private String rg;
	private Date dateNasc;
	private Integer cod;
	private String apelido;
	private String nDap;

	public static ProdutorFilter getInstance() {
		filter = new ProdutorFilter();
		return filter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDateNasc() {
		return dateNasc;
	}

	public void setDateNasc(Date dateNasc) {
		this.dateNasc = dateNasc;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getnDap() {
		return nDap;
	}

	public void setnDap(String nDap) {
		this.nDap = nDap;
	}

	public static ProdutorFilter getFilter() {
		return filter;
	}

	public static void setFilter(ProdutorFilter filter) {
		ProdutorFilter.filter = filter;
	}

}
