package com.kelsoncm.libs.lote;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import com.kelsoncm.libs.lote.campo.LoteCampo;

public class LoteLeiauteFormatadorEscritor {

	public static void imprimir(@SuppressWarnings("rawtypes") ILoteLeiaute leiaute, OutputStream outputStream) throws IOException {
		PrintStream out = new PrintStream(outputStream);
		imprimirSecao(leiaute, out, "CABE�ALHO", leiaute.getCamposCabecalho());
		imprimirSecao(leiaute, out, "DETALHE 2", leiaute.getCamposDetalhe2());
		if (!leiaute.getCamposDetalhe3().isEmpty()) {
			imprimirSecao(leiaute, out, "DETALHE 3", leiaute.getCamposDetalhe3());
		}
		imprimirSecao(leiaute, out, "RODAP�", leiaute.getCamposRodape());
		out.flush();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void imprimirSecao(ILoteLeiaute leiaute, PrintStream out, String titulo, List campos) throws IOException {
		out.println();
		out.println(titulo);
		out.println("De\tAt�\tTamanho\tFormato\tDefault\tNome\tDescricao");
		imprimirLista(campos, leiaute, out);
	}
	
	@SuppressWarnings("rawtypes") 
	private static void imprimirLista(List<LoteCampo> lista, ILoteLeiaute leiaute, PrintStream out) throws IOException {
		for (LoteCampo campo : lista) {
			out.print(campo.getInicio());
			out.print('\t');
			out.print(campo.getFim());
			out.print('\t');
			out.print(campo.getTamanho());
			out.print('\t');
			out.print(campo.getTipo().name());
			out.print('\t');
			out.print(campo.getFixo() == null ? "" : campo.getFixo());
			out.print('\t');
			out.print(campo.getNome());
			out.print('\t');
			out.println(campo.getDescricao());
		};
	}
}
