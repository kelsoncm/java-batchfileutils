package com.kelsoncm.libs.lote.campo;

import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.IOConteudoException;
import com.kelsoncm.libs.lote.ILoteDocumentoLinha;
import com.kelsoncm.libs.lote.ILoteMensagemValidacao;

public interface ILoteCampo {

	public String getPropriedade();

	public void setPropriedade(String propriedade);

	public String getNome();

	public void setNome(String nome);

	public ILoteCampoTipo getTipo();

	public void setTipo(ILoteCampoTipo tipo);

	public int getInicio();

	public void setInicio(int inicio);

	public int getTamanho();

	public void setTamanho(int tamanho);

	public int getFim();

	public void setFim(int fim);

	public String getDescricao();

	public void setDescricao(String descricao);

	public String getFixo();

	public void setFixo(String fixo);

	public char[] getFixoChars();

	public int getOrdem();

	public void setOrdem(int ordem);

	public void deserializar(char[] linha, ILoteDocumentoLinha documentoElemento) throws IOException;

	public List<ILoteMensagemValidacao> validar(char[] linha, int numeroLinha) throws IOConteudoException;

}
