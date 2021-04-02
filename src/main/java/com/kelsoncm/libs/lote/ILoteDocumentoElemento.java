package com.kelsoncm.libs.lote;

public interface ILoteDocumentoElemento {
	
	public abstract boolean isValid();

	public ResultadoValidacao getResultadoValidacao();

	public abstract ILoteDocumentoElemento getElementoPai();
	
	public abstract void setElementoPai(ILoteDocumentoElemento elementoPai);

	
}
