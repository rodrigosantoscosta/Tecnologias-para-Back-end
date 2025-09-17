package br.com.iespflix.iespflix.validation;

import br.com.iespflix.iespflix.validation.CPFouCNPJ;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.InputMismatchException;

public class CPFouCNPJValidator implements ConstraintValidator<CPFouCNPJ, String> {
    // Inicialização se necessário
    @Override
    public void initialize(CPFouCNPJ constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // Não é obrigatório, apenas valida se preenchido
        }

        // Remove caracteres não numéricos
        String digits = value.replaceAll("\\D", "");

        if (digits.length() == 11) {
            return isCPFValid(digits);
        } else if (digits.length() == 14) {
            return isCNPJValid(digits);
        }

        return false;
    }

    private boolean isCPFValid(String cpf) {
        // CPF com todos os dígitos iguais é inválido
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            // Cálculo do primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) firstDigit = 0;

            // Cálculo do segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) secondDigit = 0;

            // Verifica se os dígitos calculados conferem com os informados
            return (Character.getNumericValue(cpf.charAt(9)) == firstDigit) &&
                    (Character.getNumericValue(cpf.charAt(10)) == secondDigit);

        } catch (InputMismatchException e) {
            return false;
        }
    }

    private boolean isCNPJValid(String cnpj) {
        // CNPJ com todos os dígitos iguais é inválido
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            // Cálculo do primeiro dígito verificador
            int sum = 0;
            int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 12; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights1[i];
            }
            int firstDigit = sum % 11;
            firstDigit = (firstDigit < 2) ? 0 : 11 - firstDigit;

            // Cálculo do segundo dígito verificador
            sum = 0;
            int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 13; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights2[i];
            }
            int secondDigit = sum % 11;
            secondDigit = (secondDigit < 2) ? 0 : 11 - secondDigit;

            // Verifica se os dígitos calculados conferem com os informados
            return (Character.getNumericValue(cnpj.charAt(12)) == firstDigit) &&
                    (Character.getNumericValue(cnpj.charAt(13)) == secondDigit);

        } catch (InputMismatchException e) {
            return false;
        }
    }
}