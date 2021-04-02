package com.kelsoncm.libs.lote;

import java.io.IOException;

public interface ILoteValidadorArquivo {
	
	public void validarArquivo(long tamanhoArquivoCorrigido, long tamanhoLinhaCorrigido) throws IOException;

}
