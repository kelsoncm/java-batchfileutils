package com.kelsoncm.libs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.logging.Logger;

public class FileUtils {
	private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

	public static final String ESPACO = " ";
	public static final String EMPTY = "";
	public static final String VIRGULA = ",";
	public static final String PONTO = ".";

	public static final String CHARSET_ISO_8859_1 = "ISO-8859-1";
	public static final String CHARSET_WINDOWS_1252 = "windows-1252";
	public static final String CHARSET_UTF_8 = "UTF-8";

	private static File createFile(final String nomeArquivo, final URL resource) {
		if (resource == null) {
			logger.warning("Recurso nao encontrado...");
			return null;
		}
		return new File(resource.getFile());
	}

	public static File getFileFromSystemResource(final String nomeArquivo) {
		final URL resource = ClassLoader.getSystemResource(nomeArquivo);
		return createFile(nomeArquivo, resource);
	}

	public static File getFileFromCurretentThreadResource(final String nomeArquivo) {
		final URL resource = Thread.currentThread().getContextClassLoader().getResource(nomeArquivo);
		return createFile(nomeArquivo, resource);
	}

	public static InputStream getStreamFileFromCurretentThreadResource(final String nomeArquivo) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(nomeArquivo);
	}

	public static String getAbsoluteFilePathNameFromResource(final String nomeArquivo) {
		final File absolutePath = getFileFromSystemResource(nomeArquivo);
		final String path = absolutePath == null ? EMPTY : absolutePath.getAbsolutePath();
		return path;
	}

	public static String[] lerArquivoParaVetor(String nomeArquivo) throws IOException {
		final InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));
		try (BufferedReader br = new BufferedReader(isr)) {
			String linha;
			String linhas = EMPTY;
			while ((linha = br.readLine()) != null) {
				linhas += linha + VIRGULA;
			}
			return linhas.substring(0, linhas.length() - 1).split(VIRGULA);
		}
	}

	public static String extrairNomeArquivoSemExtensao(final String nomeArquivo) {
		final String nomeArquivoSemExtensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(File.separator) + 1, nomeArquivo.length() - 4);
		return nomeArquivoSemExtensao;
	}

	public static String getExtensaoArquivo(final String nomeArquivo) {
		File f = new File(nomeArquivo);
		String name = f.getName();
		int k = name.lastIndexOf(".");
		String ext = null;
		if (k != -1)
			ext = name.substring(k + 1, name.length());
		return ext;
	}
	
	/*
	public static boolean compararHash(File arquivoOriginal, File hshDoArquivoOriginal) throws IOException {
		if (arquivoOriginal == null) {
			throw new IOException("Par�mentro arquivoOriginal obrigat�rio!");
		}

		if (hshDoArquivoOriginal == null) {
			throw new IOException("Par�mentro hshDoArquivoOriginal obrigat�rio!");
		}

		if (!arquivoOriginal.exists()) {
			throw new IOException("O arquivo original n�o localizado!");
		}

		if (!hshDoArquivoOriginal.exists()) {
			throw new IOException("HASH do arquivo original n�o localizado!");
		}

		File file = gerarHash(arquivoOriginal, arquivoOriginal.getAbsolutePath() + ".hsh");
		String fileUm = FileUtils.lerArquivoParaString(file);
		String fileDois = FileUtils.lerArquivoParaString(hshDoArquivoOriginal);

		return fileUm.equals(fileDois);
	}
	*/

	public static String lerArquivoParaString(String caminhoArquivo, String charset) throws IOException {
		return lerArquivoParaString(new File(caminhoArquivo), charset);
	}

	public static String lerArquivoParaString(File arquivo) throws IOException {
		return lerArquivoParaString(arquivo, "windows-1252");
	}

	public static String lerArquivoParaString(File arquivo, String charset) throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), charset));
			String s;
			while ((s = in.readLine()) != null) {
				sb.append(s);
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			throw new IOException("Erro ao ler o arquivo [" + arquivo.getAbsolutePath() + "]!", e);
		}
	}

	/*
	public static File gerarHash(File file, String fileNameHash) throws IOException {
		if (file == null) {
			throw new IOException("Par�metro \"file\" obrigat�rio1");
		}
		if (file.exists()) {
			try {
				String replace = fileNameHash == null ? file.getAbsolutePath().replace(".zip", ".hsh") : fileNameHash;
				File fileHsh = new File(replace);
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileHsh));
				bufferedWriter.write(gerarHash(file));
				bufferedWriter.close();
				return fileHsh;
			} catch (Exception e) {
				throw new IOException("Erro ao gerar o arquivo HSH para [" + file.getAbsolutePath() + "]", e);
			}
		} else {
			throw new IOException("Arquivo [" + file.getAbsolutePath() + "] n�o existe!");
		}
	}

	static String gerarHash(File file) throws IOException {
		if (file == null) {
			throw new IOException("Par�metro \"file\" obrigat�rio1");
		}
		if (file.exists()) {
			try {
				return MD5Checksum.getMD5Checksum(file.getAbsolutePath());
			} catch (Exception e) {
				throw new IOException("Erro ao gerar o arquivo HSH para [" + file.getAbsolutePath() + "]", e);
			}
		} else {
			throw new IOException("Arquivo [" + file.getAbsolutePath() + "] n�o existe!");
		}
	}
	*/

	public static File criarArquivoTemporario(String nomeArquivo, String conteudoEsperado) {
		return criarArquivoNoDiretorio(nomeArquivo, conteudoEsperado, criarDiretorioTemporario());
	}

	public static File criarArquivoNoDiretorio(String nomeArquivo, String conteudoEsperado, File diretorio) {
		String nomeDiretorio = diretorio.getAbsolutePath();
		if (!(diretorio.exists())) {
			throw new IllegalArgumentException("Diret�rio de destino n�o existe [" + nomeDiretorio + "]");
		}

		String caminhoArquivoTemporario = nomeDiretorio + System.getProperty("file.separator") + nomeArquivo;
		File f = new File(caminhoArquivoTemporario);

		try {
			f.createNewFile();
			FileWriter fstream = new FileWriter(caminhoArquivoTemporario);
			BufferedWriter out = new BufferedWriter(fstream);

			out.write(conteudoEsperado);
			out.close();
		} catch (IOException ioe) {
			RuntimeException e = new RuntimeException("Nome do arquivo: " + f.getAbsolutePath());
			e.initCause(ioe);
			throw e;
		}

		return f;

	}

	public static File criarDiretorioTemporario() {
		final String PREFIXO_DIRETORIO_TEMPORARIO = "TEMPORARIO";
		StringBuilder sb = new StringBuilder(System.getProperty("java.io.tmpdir"));
		sb.append(System.getProperty("file.separator"));
		sb.append(PREFIXO_DIRETORIO_TEMPORARIO);

		Random r = new Random(System.currentTimeMillis());
		for (int i = 0; i < 10; i++) {
			sb.append(r.nextInt(10));
		}
		File f = new File(sb.toString());
		while (f.exists()) {
			logger.warning("Diret�rio tempor�rio j� existe: " + sb.toString());
			sb.append(r.nextInt());
			f = new File(sb.toString());
		}
		f.mkdir();
		logger.info("Criado diret�rio tempor�rio: " + sb.toString());
		return f;
	}

	public static boolean copyFile(File in, File out) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(in);
			fos = new FileOutputStream(out);
			final byte[] buf = new byte[2048];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharStream(fis);
			fecharStream(fos);
		}
		return true;
	}

	private static void fecharStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.warning(String.format("Erro ao fechar stream [%s]", e.getMessage()));
			}
		}
	}

	public static void copiarArquivo(File in, File out) throws IOException {
		try {
			org.apache.commons.io.FileUtils.copyFile(in, out);
		} catch (IOException e) {
			new IOException(e);
		}
	}

	public static void copiarArquivo(InputStream in, File out) throws IOException {
		try {
			FileOutputStream output = new FileOutputStream(out);
			org.apache.commons.io.IOUtils.copy(in, output);
			output.close();
		} catch (IOException e) {
			new IOException(e);
		}
	}
	
}
