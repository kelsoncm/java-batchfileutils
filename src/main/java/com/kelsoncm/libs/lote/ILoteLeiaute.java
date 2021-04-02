package com.kelsoncm.libs.lote;

import java.util.List;

import com.kelsoncm.libs.lote.campo.ILoteCampo;

public interface ILoteLeiaute
	<Cabecalho extends ILoteDocumentoCabecalho, Detalhe extends ILoteDocumentoDetalhe, Rodape extends ILoteDocumentoRodape> {
	
	public String getNomeClasseCabecalho();

	public void setNomeClasseCabecalho(String nomeClasseCabecalho);

	public String getNomeClasseDetalhe2();

	public void setNomeClasseDetalhe2(String nomeClasseDetalhe2);

	public String getNomeClasseDetalhe3();

	public void setNomeClasseDetalhe3(String nomeClasseDetalhe3);

	public String getNomeClasseRodape();

	public void setNomeClasseRodape(String nomeClasseRodape);

	public int getTamanhoLinha();

	public void setTamanhoLinha(int tamanhoLinha);

	public int getTamanhoLinhaCorrigido();

	public Class<Cabecalho> getClasseCabecalho();

	public Class<Detalhe> getClasseDetalhe2();

	public Class<ILoteDocumentoDetalhe> getClasseDetalhe3();

	public Class<Rodape> getClasseRodape();

	public List<ILoteCampo> getCamposCabecalho();

	public void adicionarCampoCabecalho(ILoteCampo campo);

	public List<ILoteCampo> getCamposDetalhe2();

	public void adicionarCampoDetalhe2(ILoteCampo campo);

	public List<ILoteCampo> getCamposDetalhe3();

	public void adicionarCampoDetalhe3(ILoteCampo campo);

	List<ILoteCampo> getCamposRodape();

	void adicionarCampoRodape(ILoteCampo campo);

	boolean isValid();

}
