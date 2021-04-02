package com.kelsoncm.libs.lote;

import java.util.ArrayList;
import java.util.List;

import com.kelsoncm.libs.lote.campo.ILoteCampo;

public class LoteLeiaute
	<Cabecalho extends ILoteDocumentoCabecalho, Detalhe2 extends ILoteDocumentoDetalhe, Rodape extends ILoteDocumentoRodape>
		implements ILoteLeiaute<Cabecalho, Detalhe2, Rodape> {
	
	private String nomeClasseCabecalho;
	private String nomeClasseDetalhe2;
	private String nomeClasseDetalhe3;
	private String nomeClasseRodape;
	
	private Class<Cabecalho> classeCabecalho;
	private Class<Detalhe2> classeDetalhe2;
	private Class<ILoteDocumentoDetalhe> classeDetalhe3;
	private Class<Rodape> classeRodape;

	private int tamanhoLinha;
	private int tamanhoLinhaCorrigido;

	private List<ILoteCampo> camposCabecalho = new ArrayList<ILoteCampo>();
	private List<ILoteCampo> camposDetalhe2 = new ArrayList<ILoteCampo>();
	private List<ILoteCampo> camposDetalhe3 = new ArrayList<ILoteCampo>();
	private List<ILoteCampo> camposRodape = new ArrayList<ILoteCampo>();

	@Override
	public String getNomeClasseCabecalho() {
		return this.nomeClasseCabecalho;
	}

	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public void setNomeClasseCabecalho(String nomeClasseCabecalho) {
		try {
			this.classeCabecalho = (Class<Cabecalho>) this.getClass().forName(nomeClasseCabecalho);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("N�o foi poss�vel carregar a classe '" + nomeClasseCabecalho+ "'.");
		}
		this.nomeClasseCabecalho = nomeClasseCabecalho;
	}

	@Override
	public String getNomeClasseDetalhe2() {
		return this.nomeClasseDetalhe2;
	}

	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public void setNomeClasseDetalhe2(String nomeClasseDetalhe2) {
		try {
			this.classeDetalhe2 = (Class<Detalhe2>) this.getClass().forName(nomeClasseDetalhe2);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("N�o foi poss�vel carregar a classe '" + nomeClasseDetalhe2 + "'.");
		}
		this.nomeClasseDetalhe2 = nomeClasseDetalhe2;
	}

	@Override
	public String getNomeClasseDetalhe3() {
		return this.nomeClasseDetalhe3;
	}

	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public void setNomeClasseDetalhe3(String nomeClasseDetalhe3) {
		try {
			this.classeDetalhe3 = (Class<ILoteDocumentoDetalhe>)this.getClass().forName(nomeClasseDetalhe3);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("N�o foi poss�vel carregar a classe '" + nomeClasseDetalhe3 + "'.");
		}
		this.nomeClasseDetalhe3 = nomeClasseDetalhe3;
	}

	@Override
	public String getNomeClasseRodape() {
		return this.nomeClasseRodape;
	}

	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public void setNomeClasseRodape(String nomeClasseRodape) {
		try {
			this.classeRodape = (Class<Rodape>) this.getClass().forName(nomeClasseRodape);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("N�o foi poss�vel carregar a classe '" + nomeClasseRodape + "'.");
		}
		this.nomeClasseRodape = nomeClasseRodape;
	}

	@Override
	public int getTamanhoLinha() {
		return this.tamanhoLinha;
	}

	@Override
	public void setTamanhoLinha(int tamanhoLinha) {
		this.tamanhoLinha = tamanhoLinha;
		this.tamanhoLinhaCorrigido = tamanhoLinha + 2;
	}

	@Override
	public int getTamanhoLinhaCorrigido() {
		return this.tamanhoLinhaCorrigido;
	}

	@Override
	public Class<Cabecalho> getClasseCabecalho() {
		return this.classeCabecalho;
	}

	@Override
	public Class<Detalhe2> getClasseDetalhe2() {
		return this.classeDetalhe2;
	}

	@Override
	public Class<ILoteDocumentoDetalhe> getClasseDetalhe3() {
		return this.classeDetalhe3;
	}
	
	@Override
	public Class<Rodape> getClasseRodape() {
		return this.classeRodape;
	}

	@Override
	public List<ILoteCampo> getCamposCabecalho() {
		return this.camposCabecalho;
	}

	@Override
	public void adicionarCampoCabecalho(ILoteCampo campo) {
		adicionaCampo(campo, this.camposCabecalho);
	}

	@Override
	public List<ILoteCampo> getCamposDetalhe2() {
		return this.camposDetalhe2;
	}

	@Override
	public void adicionarCampoDetalhe2(ILoteCampo campo) {
		adicionaCampo(campo, this.camposDetalhe2);
	}

	@Override
	public List<ILoteCampo> getCamposDetalhe3() {
		return this.camposDetalhe3;
	}

	@Override
	public void adicionarCampoDetalhe3(ILoteCampo campo) {
		adicionaCampo(campo, this.camposDetalhe3);
	}

	private void adicionaCampo(ILoteCampo campo, List<ILoteCampo> lista) {
		int indiceUltimo = lista.size()-1;
		if (indiceUltimo==-1) {
			campo.setInicio(1);
		} else {
			campo.setInicio(lista.get(indiceUltimo).getFim()+1);
		}
		campo.setFim(campo.getInicio() + campo.getTamanho()-1);
		lista.add(campo);
	}

	@Override
	public List<ILoteCampo> getCamposRodape() {
		return this.camposRodape;
	}

	@Override
	public void adicionarCampoRodape(ILoteCampo campo) {
		adicionaCampo(campo, this.camposRodape);
	}

	@Override
	public boolean isValid() {
		int tamanhoLinhaCabecalho = calcularTamanhoListaCampos(this.camposCabecalho);
		int tamanhoLinhaDetalhe2 = calcularTamanhoListaCampos(this.camposDetalhe2);
		int tamanhoLinhaDetalhe3 = tamanhoLinhaDetalhe2; 
		if (!this.camposDetalhe3.isEmpty()) {
			tamanhoLinhaDetalhe3 = calcularTamanhoListaCampos(this.camposDetalhe3);
		}
		int tamanhoLinhaRodape = calcularTamanhoListaCampos(this.camposRodape);
		return  (tamanhoLinhaCabecalho == tamanhoLinha) &&
				(tamanhoLinhaDetalhe2 == tamanhoLinha) &&
				(tamanhoLinhaDetalhe3 == tamanhoLinha) &&
				(tamanhoLinhaRodape == tamanhoLinha);
	}
	
	private int calcularTamanhoListaCampos(List<ILoteCampo> campos) {
		int tamanho = 0;
		for (ILoteCampo campo : campos)
			tamanho += campo.getTamanho();
		return tamanho;
	}
	
}