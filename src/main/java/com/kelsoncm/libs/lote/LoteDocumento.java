package com.kelsoncm.libs.lote;

import java.util.ArrayList;
import java.util.List;

public class LoteDocumento 
	<Cabecalho extends ILoteDocumentoCabecalho, Detalhe extends ILoteDocumentoDetalhe, Rodape extends ILoteDocumentoRodape>
	extends LoteDocumentoElemento
	implements ILoteDocumento<Cabecalho, Detalhe, Rodape> {
	
	private Cabecalho cabecalho;
	private List<Detalhe> detalhes;
	private Rodape rodape;
	
	public LoteDocumento() {
		super();
		this.detalhes = new ArrayList<Detalhe>();
	}

	@Override
	public Cabecalho getCabecalho() {
		return this.cabecalho;
	}

	@Override
	public void setCabecalho(Cabecalho cabecalho) {
		this.cabecalho = cabecalho;
	}

	@Override
	public List<Detalhe> getDetalhes() {
		return this.detalhes;
	}

	@Override
	public void adicionarDetalhe(Detalhe detalhe) {
		this.detalhes.add(detalhe);
	}

	@Override
	public Rodape getRodape() {
		return this.rodape;
	}

	@Override
	public void setRodape(Rodape rodape) {
		this.rodape = rodape;
		
	}

	@Override
	public boolean isValid() {
		if (!isValidoCabecalho() || !isValidoRodape() || !isValidoPreDetalhe())
			return false;
		return isValidoDetalhes();
	}

	private boolean isValidoDetalhes() {
		for (Detalhe detalhe : this.detalhes)
			 if (!detalhe.isValid())
				 return false;
		return true;
	}

	private boolean isValidoPreDetalhe() {
		return this.getDetalhes() != null && this.detalhes.size() > 0;
	}

	private boolean isValidoRodape() {
		return this.getRodape() != null && this.getRodape().isValid();
	}

	private boolean isValidoCabecalho() {
		return this.getCabecalho() != null && this.getCabecalho().isValid();
	}

	@Override
	public ResultadoValidacao getResultadoValidacao() {
		if (isValid())
			return null;
		ResultadoValidacao resultado = new ResultadoValidacaoImpl();
		if (!isValidoCabecalho())
			resultado.adicionarResultadoValidacao(getCabecalho().getResultadoValidacao());
		if (!isValidoDetalhes())
			for (Detalhe detalhe : getDetalhes())
				resultado.adicionarResultadoValidacao(detalhe.getResultadoValidacao());
		if (!isValidoRodape())
			resultado.adicionarResultadoValidacao(getRodape().getResultadoValidacao());
		return resultado;
	}

}