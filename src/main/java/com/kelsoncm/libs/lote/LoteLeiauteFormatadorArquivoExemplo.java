package com.kelsoncm.libs.lote;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import com.kelsoncm.libs.lote.campo.LoteCampo;

public class LoteLeiauteFormatadorArquivoExemplo {

	private static final String FIM_DE_LINHA = "\r\n";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void imprimir(ILoteLeiaute leiaute, OutputStream outputStream) throws IOException {
		PrintStream out = new PrintStream(outputStream);
		imprimirLista(leiaute, out, leiaute.getCamposCabecalho(), FIM_DE_LINHA);
		imprimirLista(leiaute, out, leiaute.getCamposDetalhe2(), FIM_DE_LINHA);
		if (!leiaute.getCamposDetalhe3().isEmpty()) {
			imprimirLista(leiaute, out, leiaute.getCamposDetalhe3(), FIM_DE_LINHA);
		}
		imprimirLista(leiaute, out, leiaute.getCamposRodape(), "");
		out.flush();
	}
	
	@SuppressWarnings("rawtypes") 
	private static void imprimirLista(ILoteLeiaute leiaute, PrintStream out, List<LoteCampo> lista, String fimDeLinha) throws IOException {
		for (LoteCampo campo : lista) {
			if (campo.getFixo()!=null) {
				out.print(campo.getFixo());
			} else {
				out.print(StringUtils.repeat(campo.getTipo().getMascara(), campo.tamanho));
			}
		};
		out.print(fimDeLinha);
	}
}