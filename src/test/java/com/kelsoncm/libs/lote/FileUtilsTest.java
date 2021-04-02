package br.jus.tse.sepel2.arquitetura.arquivo.util;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kelsoncm.libs.util.FileUtils;

import br.jus.tse.sepel2.arquitetura.api.lang.Sepel2Exception;

public class FileUtilsTest {
	private static final String CONTEUDO_DO_ARQUIVO = "Acentua��o";

	@Test
	public void testLerArquivoParaStringComCharsetCorreto() throws Sepel2Exception{
		Assert.assertEquals(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-windows-1252.txt")), CONTEUDO_DO_ARQUIVO);
		Assert.assertEquals(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-windows-1252.txt"), FileUtils.CHARSET_WINDOWS_1252), CONTEUDO_DO_ARQUIVO);
		
		Assert.assertEquals(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-iso-8859-1.txt"), FileUtils.CHARSET_ISO_8859_1), CONTEUDO_DO_ARQUIVO);
		Assert.assertEquals(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-UTF-8.txt"), FileUtils.CHARSET_UTF_8), CONTEUDO_DO_ARQUIVO);
	}
	
	@Test
	public void testLerArquivoParaStringComCharsetERRADO() throws Sepel2Exception{
		//tentar ler UTF-8 como windows-1252 (charset padr�o):
		Assert.assertFalse(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-UTF-8.txt")).equals(CONTEUDO_DO_ARQUIVO));
		Assert.assertFalse(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-UTF-8.txt"), FileUtils.CHARSET_WINDOWS_1252).equals(CONTEUDO_DO_ARQUIVO));
		Assert.assertFalse(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-UTF-8.txt"), FileUtils.CHARSET_ISO_8859_1).equals(CONTEUDO_DO_ARQUIVO));
		
		//tentar ler iso8859-1 como UTF-8
		Assert.assertFalse(FileUtils.lerArquivoParaString(arquivo("arquivo-encoding-iso-8859-1.txt"), FileUtils.CHARSET_UTF_8).equals(CONTEUDO_DO_ARQUIVO));
		
		//windows-1252 � muito parecido com ISO-8859-1 vide: en.wikipedia.org/wiki/Windows-1252
		//portanto o teste abaixo n�o � eficiente...
	}

	private File arquivo(String nomeArquivo) {
		String diretorioBase = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		String caminhoArquivo = new StringBuilder(diretorioBase)
										.append(separador)
										.append("target")
										.append(separador)
										.append("test-classes")
										.append(separador)
										.append(nomeArquivo).toString();
		return new File(caminhoArquivo);
	}
	
	@Test
	public void testGetExtensao(){
		Assert.assertEquals(FileUtils.getExtensaoArquivo("teste.txt"), "txt");
		Assert.assertEquals(FileUtils.getExtensaoArquivo("/temp/meu_arquivo"), null);
		Assert.assertEquals(FileUtils.getExtensaoArquivo("/temp/meu_arquivo.3388383883"), "3388383883");
		Assert.assertEquals(FileUtils.getExtensaoArquivo("/temp/meu_arquivo.cif.83696488293"), "83696488293");
	}
}
