package br.com.loucademia.application.util;

public class Validacao {
	public static void assertNotEmpty(Object obj) {
		if(obj instanceof String) {
			String value = (String) obj;
			if(StringsUtils.isEmpty(value)) {
				throw new ValidaException("O texto n�o pode ser nulo");
			}
		}
		
		if(obj == null) {
			throw new ValidaException("Valor n�o pode ser nulo");
		}
	}
}
