package com.kelsoncm.libs.lote;

import java.io.IOException;

public class LoteValidadorArquivo
	implements ILoteValidadorArquivo {

	@Override
	public void validarArquivo(long tamanhoArquivoCorrigido, long tamanhoLinhaCorrigido) throws IOException {
		validarTamanhoArquivo(tamanhoArquivoCorrigido, tamanhoLinhaCorrigido);
		validarMinimoDeLinhas(tamanhoArquivoCorrigido, tamanhoLinhaCorrigido);
	}
	
	/**
	 * Valida o tamanho o arquivo.
	 * Se todas as linhas t�m o mesmo tamanho, ent�o o tamanho do arquivo deve ser um m�ltiplo 
	 * do tamanho das linhas. Lembrando que a �ltima linha n�o tem \r\n e que ao final de 
	 * todas as linhas tem \r\n.
	 * 
	 * O tamanho dividido pelo tamanho de cada linha d� o n�mero de linhas
	 * mas, se alguma linha estiver com o tamanho diferente o resultado ser� 
	 * uma fra��o, assim, se o m�dulo for 0 ent�o temos uma divis�o inteira e 
	 * se o m�dulo n�o for 0 temos uma situa��o inv�lida
	 * 
	 * @throws ArquivoException
	 */
	protected void validarTamanhoArquivo(long tamanhoArquivoCorrigido, long tamanhoLinhaCorrigido) throws IOException {
		long modFile = tamanhoArquivoCorrigido % tamanhoLinhaCorrigido;
		if ( modFile != 0 )
			throw new IOException("Tamanho do arquivo inv�lido. Verifique se todas as linhas est�o com o tamanho correto.");
	}

	/**
	 * O arquivo deve ter no m�nimo um HEADER, um DETAIL e um TRAILER.
	 * 
	 * Assim, para saber se tem os tr�s o tamanho do arquivo deve maior ou igual a 3 x o tamanho de cada linha
	 * levando-se em conta o tamanho os caracteres de nova linha. 
	 * 
	 * @throws ArquivoException
	 */
	protected void validarMinimoDeLinhas(long tamanhoArquivoCorrigido, long tamanhoLinhaCorrigido) throws IOException {
		long byteEsperadosEmTresLinhasMinimas = tamanhoLinhaCorrigido * 3;
		if (tamanhoArquivoCorrigido < byteEsperadosEmTresLinhasMinimas)
			throw new IOException("Estrutura m�nima do arquivo inv�lido. Verifique se existe ao menos um HEADER, um DETAIL e um TRAILER, nesta ordem.");
	}

}
