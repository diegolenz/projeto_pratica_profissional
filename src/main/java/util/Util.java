package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}

	public static String builDataSimples(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}

}
