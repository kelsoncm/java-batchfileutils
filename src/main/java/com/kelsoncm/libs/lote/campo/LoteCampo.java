package com.kelsoncm.libs.lote.campo;

import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.IOConteudoException;
import com.kelsoncm.libs.lote.ILoteDocumentoLinha;
import com.kelsoncm.libs.lote.ILoteMensagemValidacao;

public abstract class LoteCampo implements ILoteCampo {

	public String propriedade;
	public String nome;
	public ILoteCampoTipo tipo;
	public int inicio;
	public int tamanho;
	public int fim;
	public String descricao;
	public String fixo;
	public int ordem;
	public char[] fixoChars;
	
	protected LoteCampo(
			LoteCampoTipoEnum tipo,
			int tamanho, 
			String propriedade, 
			String nome, 
			String descricao, 
			String fixo) {
		this(tipo, tamanho, propriedade, nome, descricao);
		this.setFixo(fixo);
	}
	
	protected LoteCampo(
			LoteCampoTipoEnum tipo,
			int tamanho, 
			String propriedade, 
			String nome, 
			String descricao) {
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.propriedade = propriedade;
		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public String getPropriedade() {
		return propriedade;
	}

	@Override
	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public ILoteCampoTipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(ILoteCampoTipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int getInicio() {
		return inicio;
	}

	@Override
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	@Override
	public int getTamanho() {
		return tamanho;
	}

	@Override
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public int getFim() {
		return fim;
	}

	@Override
	public void setFim(int fim) {
		this.fim = fim;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getFixo() {
		return fixo;
	}

	@Override
	public void setFixo(String fixo) {
		this.fixo = fixo;
		this.fixoChars = fixo.toCharArray();
	}

	@Override
	public char[] getFixoChars() {
		return fixoChars;
	}

	@Override
	public int getOrdem() {
		return ordem;
	}

	@Override
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	@Override
	public void deserializar(char[] linha, ILoteDocumentoLinha documentoElemento) throws IOException {
		tipo.deserializar(linha, documentoElemento, this);
	}
	
	@Override
	public List<ILoteMensagemValidacao> validar(char[] linha, int numeroLinha) throws IOConteudoException {
		return tipo.validarConteudo(linha, numeroLinha, this);
	}
	
}