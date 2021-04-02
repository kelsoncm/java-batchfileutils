package com.kelsoncm.libs.lote;

public interface ILoteDocumentoLinha extends ILoteDocumentoElemento  {
	
	public long getNumeroLinha();
	public void setNumeroLinha(long numeroLinha);

	public int getTipoLinha();
	public void setTipoLinha(int tipoLinha);
}
