package br.edu.udc.sistemas.pwm2018.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author fdami
 * Anota��o que faz o mapeamento de um atributo em uma coluna do banco
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	public String name() default "";
	public DataType type() default DataType.STRING;
	public int length() default 255;
	
	public boolean orderBy() default false;
	public boolean unique() default false;
	public boolean nullable() default true;
	public boolean insertable() default true;
	public boolean updatable() default true;
}
