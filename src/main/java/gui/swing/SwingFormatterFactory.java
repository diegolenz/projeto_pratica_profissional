/**
 * (C) 2015-2018 JL INFORMATICA LTDA ME
 * CNPJ 14.928.793/0001-18
 * www.jlinformatica.net.br
 * (45) 3559-1534
 */

package gui.swing;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import util.RegexFormatter;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * @author Lucas Dillmann
 */
public class SwingFormatterFactory {

    private static class InternalNumberFormatter extends NumberFormatter {

        @Override
        public void install(JFormattedTextField ftf) {
            if (ftf.getValue() == null) {
                ftf.setValue(0D);
            }

            super.install(ftf);
        }
    }

    //private static final Logger logger = LoggerFactory.getLogger(SwingFormatterFactory.class);

    private SwingFormatterFactory() {
    }

    public static AbstractFormatterFactory buildAbstractFormatter(String mascara) {
        return buildAbstractFormatter(mascara, false);
    }

    public static AbstractFormatterFactory buildAbstractFormatter(String mascara, boolean allowInvalid) {
        try {
            MaskFormatter formatter = new MaskFormatter(mascara);
            formatter.setPlaceholderCharacter('_');
            formatter.setCommitsOnValidEdit(true);
            formatter.setOverwriteMode(true);
            formatter.setAllowsInvalid(allowInvalid);
            return new DefaultFormatterFactory(formatter);
        } catch (Throwable ex) {
            //logger.error("Falha ao instanciar", ex);
            return null;
        }
    }

    public static AbstractFormatterFactory buildAbstractPatternFormatter(String patternString) {
        try {
            Pattern pattern = Pattern.compile(patternString);
            RegexFormatter formatter = new RegexFormatter(pattern);
            formatter.setOverwriteMode(true);
            formatter.setCommitsOnValidEdit(true);
            return new DefaultFormatterFactory(formatter);
        } catch (Throwable ex) {
         //   logger.error("Falha ao instanciar", ex);
            return null;
        }
    }

    public static AbstractFormatterFactory buildEnderecoIpV4() {
        return buildAbstractPatternFormatter("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    }

    public static AbstractFormatterFactory buildCnpj() {
        return buildCnpj(false);
    }

    public static AbstractFormatterFactory buildCnpj(boolean allowInvalid) {
        return buildAbstractFormatter("##.###.###/####-##", allowInvalid);
    }

    public static AbstractFormatterFactory buildCep() {
        return buildCep(false);
    }

    public static AbstractFormatterFactory buildCep(boolean allowInvalid) {
        return buildAbstractFormatter("#####-###", allowInvalid);
    }

    public static AbstractFormatterFactory buildCpf() {
        return buildCpf(false);
    }

    public static AbstractFormatterFactory buildCpf(boolean allowInvalid) {
        return buildAbstractFormatter("###.###.###-##", allowInvalid);
    }

    public static AbstractFormatterFactory buildEnderecoMac() {
        return buildEnderecoMac(false);
    }

    public static AbstractFormatterFactory buildEnderecoMac(boolean allowInvalid) {
        return buildAbstractFormatter("HH-HH-HH-HH-HH-HH", allowInvalid);
    }

    public static AbstractFormatterFactory buildData() {
        return buildData(false);
    }

    public static AbstractFormatterFactory buildData(boolean allowInvalid) {
        return buildAbstractFormatter("##/##/####", allowInvalid);
    }

    /**
     * Método para criação de máscara de hora e minuto
     * @return Máscara pronta para uso
     * @author Cesar Both
     * @since 1.5.4
     */
    public static AbstractFormatterFactory buildHoraMinuto() {
        return buildAbstractFormatter("##:##", false);
    }

    public static AbstractFormatterFactory buildDataHora() {
        return buildDataHora(false);
    }

    public static AbstractFormatterFactory buildDataHora(boolean allowInvalid) {
        return buildAbstractFormatter("##/##/#### ##:##:##", allowInvalid);
    }

    /**
     * Método de construção de formatador abstrato
     *
     * @param formato Formato desejado
     * @param minimo  Valor mínimo
     * @param maximo  Valor máximo
     * @return Instância do formatador
     * @author Lucas Dillmann
     * @since 1.0
     */
    private static AbstractFormatterFactory buildAbstractNumberFormatter(NumberFormat formato, Double minimo, Double maximo) {
        return buildAbstractNumberFormatter(formato, minimo, maximo, 2);
    }

    /**
     * Método de construção de formatador abstrato
     *
     * @param formato       Formato desejado
     * @param minimo        Valor mínimo
     * @param maximo        Valor máximo
     * @param casasDecimais Quantidade de casas decimais
     * @return Instância do formatador
     * @author Lucas Dillmann
     * @since 1.3.1
     */
    private static AbstractFormatterFactory buildAbstractNumberFormatter(NumberFormat formato, Double minimo, Double maximo, Integer casasDecimais) {

        formato.setMaximumFractionDigits(casasDecimais);
        formato.setMinimumFractionDigits(casasDecimais);

        InternalNumberFormatter formatter = new InternalNumberFormatter();
        formatter.setFormat(formato);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        formatter.setCommitsOnValidEdit(true);

        if (maximo != null) {
            formatter.setMaximum(maximo);
        }

        if (minimo != null) {
            formatter.setMinimum(minimo);
        }

        return new DefaultFormatterFactory(formatter);
    }

    /**
     * Método de construção do formatador para moedas com quantidade de casas decimais personalizada
     *
     * @param minimo Valor mínimo aceito
     * @param maximo Valor máximo aceito
     * @param casasDecimais Quantidade de casas decimais
     * @return Instância do formatador
     * @author Lucas Dillmann
     * @since 1.5.2
     */
    public static AbstractFormatterFactory buildMoeda(Double minimo, Double maximo, Integer casasDecimais) {
        return buildAbstractNumberFormatter(NumberFormat.getCurrencyInstance(), minimo, maximo, casasDecimais);
    }

    public static AbstractFormatterFactory buildMoeda(Double minimo, Double maximo) {
        return buildAbstractNumberFormatter(NumberFormat.getCurrencyInstance(), minimo, maximo);
    }

    public static AbstractFormatterFactory buildNumeroReal(Double minimo, Double maximo) {
        return buildAbstractNumberFormatter(NumberFormat.getInstance(), minimo, maximo);
    }

    /**
     * Método para criar um formatador de números reais
     *
     * @param minimo        Valor mínimo permitido
     * @param maximo        Valor máximo permitido
     * @param casasDecimais Quantidade de casas decimais
     * @return Formatador pronto para uso
     * @author Cesar Both
     * @since 1.4.1
     */
    public static AbstractFormatterFactory buildNumeroReal(Double minimo, Double maximo, Integer casasDecimais) {
        return buildAbstractNumberFormatter(new DecimalFormat("0.00##"), minimo, maximo, casasDecimais);
    }

    public static NumberFormatter buildNumeroInteiro(Integer minimo, Integer maximo) {
        DecimalFormat format = new DecimalFormat("#0");
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setCommitsOnValidEdit(true);

        if (maximo != null) {
            formatter.setMaximum(maximo);
        }

        if (minimo != null) {
            formatter.setMinimum(minimo);
        }

        return formatter;
    }

    public static NumberFormatter buildNumeroInteiroGrande(BigInteger minimo, BigInteger maximo) {
        DecimalFormat format = new DecimalFormat("#0");
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(BigInteger.class);
        formatter.setCommitsOnValidEdit(true);

        if (maximo != null) {
            formatter.setMaximum(maximo);
        }

        if (minimo != null) {
            formatter.setMinimum(minimo);
        }

        return formatter;
    }

    /**
     * Método de contrução de formatador percentual
     *
     * @param minimo Valor mínimo
     * @param maximo Valor máximo
     * @return Instância do formatador
     * @author Lucas Dillmann
     * @since 1.0
     */
    public static AbstractFormatterFactory buildPorcentagem(Double minimo, Double maximo) {
        return buildPorcentagem(minimo, maximo, 2);
    }

    /**
     * Método de contrução de formatador percentual
     *
     * @param minimo        Valor mínimo
     * @param maximo        Valor máximo
     * @param casasDecimais Quantidade de casas decimais
     * @return Instância do formatador
     * @author Lucas Dillmann
     * @since 1.3.1
     */
    public static AbstractFormatterFactory buildPorcentagem(Double minimo, Double maximo, Integer casasDecimais) {
        return buildAbstractNumberFormatter(NumberFormat.getPercentInstance(), minimo, maximo, casasDecimais);
    }

}
