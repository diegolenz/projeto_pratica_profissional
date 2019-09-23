package br.edu.udc.sistemas.pwm2018.infra;

import java.util.Scanner;

public class IOTools {

	public static void clrscr() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public static Character getche() throws Exception {
		Scanner sc = new Scanner(System.in);
		Character result = sc.next().charAt(0);
		sc.close();
		return result;
	}

	public static Character getch() throws Exception {
		return IOTools.getche();
	}
	
	public static String readString() throws Exception {
		Scanner sc = new Scanner(System.in);
		String result = sc.next();
		sc.close();
		return result;
	}

	public static Integer readInteger() throws Exception {
		Scanner sc = new Scanner(System.in);
		Integer result = sc.nextInt();
		sc.close();
		return result;
	}
}