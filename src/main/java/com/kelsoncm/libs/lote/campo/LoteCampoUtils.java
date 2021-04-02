package com.kelsoncm.libs.lote.campo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;


public class LoteCampoUtils {

	public static boolean isApenasNumeros(char[] linha, ILoteCampo campo) {
		for (int i = campo.getInicio()-1; i < campo.getFim(); i++) {
			char c = linha[i];
			if (c<'0'||c>'9')
				return false;
		}
		return true;
	}

	public static String subCharArrayToString(char[] linha, int inicio, int fim) {
		StringBuilder sb = new StringBuilder();
		for (int i = inicio; i <= fim; i++) {
			sb.append(linha[i]);
		}
		return sb.toString();
	}

	public static byte subCharArrayToByte(char[] linha, int inicio, int fim) {
		StringBuilder sb = new StringBuilder();
		for (int i = inicio; i <= fim; i++) {
			sb.append(linha[i]);
		}
		String string = sb.toString();
		return Byte.valueOf(string);
	}

	public static int subCharArrayToInt(char[] linha, int inicio, int fim) {
		StringBuilder sb = new StringBuilder();
		for (int i = inicio; i <= fim; i++) {
			sb.append(linha[i]);
		}
		return Integer.valueOf(sb.toString());
	}

	public static long subCharArrayToLong(char[] linha, int inicio, int fim) {
		StringBuilder sb = new StringBuilder();
		for (int i = inicio; i <= fim; i++) {
			sb.append(linha[i]);
		}
		return Long.valueOf(sb.toString());
	}

	public static boolean subCharArrayToBoolean(char[] linha, int inicio) {
		return linha[inicio] == 'S';
	}

	public static Date subCharArrayToData(char[] linha, int inicio) {
		int pos = inicio-1;
		int dia = subCharArrayToInt(linha, pos, pos+=1);
		int mes = subCharArrayToInt(linha, pos+=1, pos+=1);
		int ano = subCharArrayToInt(linha, pos+=1, pos+=3);
		Calendar calendar = new GregorianCalendar();
		calendar.set(ano, mes-1, dia);
		return calendar.getTime();
	}

	public static Date subCharArrayToDataHora(char[] linha, int inicio) {
		int pos = inicio-1;
		int dia = subCharArrayToInt(linha, pos, pos+=1);
		int mes = subCharArrayToInt(linha, pos+=1, pos+=1);
		int ano = subCharArrayToInt(linha, pos+=1, pos+=3);
		int hora = subCharArrayToInt(linha, pos+=1, pos+=1);
		int minuto = subCharArrayToInt(linha, pos+=1, pos+=1);
		Calendar calendar = new GregorianCalendar();
		calendar.set(ano, mes-1, dia, hora, minuto);
		return calendar.getTime();
	}

	public static Date subCharArrayToDataHoraSegundos(char[] linha, int inicio) {
		int pos = inicio-1;
		int dia = subCharArrayToInt(linha, pos, pos+=1);
		int mes = subCharArrayToInt(linha, pos+=1, pos+=1);
		int ano = subCharArrayToInt(linha, pos+=1, pos+=3);
		int hora = subCharArrayToInt(linha, pos+=1, pos+=1);
		int minuto = subCharArrayToInt(linha, pos+=1, pos+=1);
		int segundos = subCharArrayToInt(linha, pos+=1, pos+=1);
		Calendar calendar = new GregorianCalendar();
		calendar.set(ano, mes-1, dia, hora, minuto, segundos);
		return calendar.getTime();
	}

	public static Date subCharArrayToHora(char[] linha, int inicio) {
		int pos = inicio-1;
		int hora = subCharArrayToInt(linha, pos, pos+=1);
		int minuto = subCharArrayToInt(linha, pos+=1, pos+=1);
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minuto);
		return calendar.getTime();
	}

	public static Object subCharArrayToBigDecimal(char[] linha, int inicio, int fim) {
		char[] agoraComPonto = new char[fim-inicio+2];
		int j = 0;
		for (int i = inicio; i <= fim; i++) {
			if (i==fim-1) {
				agoraComPonto[j] = '.';
				j++;
			}
			agoraComPonto[j] = linha[i];
			j++;
		}
		return new BigDecimal(agoraComPonto);
	}

}
