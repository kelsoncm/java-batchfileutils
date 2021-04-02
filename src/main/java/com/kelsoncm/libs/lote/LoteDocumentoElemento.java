package com.kelsoncm.libs.lote;


public abstract class LoteDocumentoElemento implements ILoteDocumentoElemento {

	private ILoteDocumentoElemento elementoPai;

	/**
	 * Resultado de falhas nas validações. Se esta propriedade estiver
	 * preenchida é porque houve ao menos uma falha ao validar esta linha. Se
	 * estiver nulo não houve falha na validação.
	 */
	private ResultadoValidacao resultadoValidacao;

	@Override
	public ILoteDocumentoElemento getElementoPai() {
		return this.elementoPai;
	}

	@Override
	public void setElementoPai(ILoteDocumentoElemento elementoPai) {
		this.elementoPai = elementoPai;
	}

	@Override
	public ResultadoValidacao getResultadoValidacao() {
		return this.resultadoValidacao;
	}

	protected void setResultadoValidacao(ResultadoValidacao resultadoValidacao) {
		this.resultadoValidacao = resultadoValidacao;
	}

	@Override
	public boolean isValid() {
		return this.resultadoValidacao != null && resultadoValidacao.isValid();
	}

	protected void adicionarMensagemValidacao(ILoteMensagemValidacao mensagemValidacao) {
		if (mensagemValidacao != null) {
			if (resultadoValidacao == null) {
				resultadoValidacao = new ResultadoValidacaoImpl();
			}
			resultadoValidacao.adicionarMensagemValidacao(mensagemValidacao);
		}
	}

}
