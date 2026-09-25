package ej01;

import java.io.*;

public class Ejercicio01 {

	public static void main(String[] args) {
		File entrada = new File ("."+ File.separator + "src" + File.separator + "ej01" + File.separator + "entrada.txt");
		File salida = new File ("."+ File.separator + "src" + File.separator + "ej01" + File.separator + "salida.txt");

		// Try-catch para la creacion del fichero salida.txt
		try {
			// Si ya existe lo borra
			if(salida.exists()) {
				salida.delete();
			}
			
			if(salida.createNewFile()) {
				System.out.println("salida.txt creado correctamente.");
			} else {
				System.out.println("No se ha podido crear salida.txt.");
			}
		} catch(IOException e) {
			System.out.println("IOException - ha ocurrido un error al crear el fichero salida.txt");
		}
		
		// Try-catch para la lectura y escritura
		try {
			FileReader reader = new FileReader(entrada);
			FileWriter writer = new FileWriter(salida);
			
			System.out.println("Empieza la lectura y escritura.");
			int i;
			char caracter = 0;
			
			// Bucle para la lectura y escritura
			while((i = reader.read()) != -1) {
				// Si es un numero se escribe un #
				if(i >=48 && i <= 57) {
					writer.write("#");
				} else if(i>=97 && i<=122){
		    		// Si es una letra minuscula se escribe en mayuscula
					i-=32;
					caracter = (char) i;
		    		writer.write(caracter);
		    	} else {
		    		// Cualquier otro caracter se escribe tal cual se lee
		    		caracter = (char) i;
		    		writer.write(caracter);
		    	}
			}
			
			System.out.println("La lectura y escritura han terminado.");
			
			// Cierre
			reader.close();
			writer.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException - ha ocurrido un error durante la lectura o escritura");
		} catch (IOException e) {
			System.out.println("IOException - ha ocurrido un error durante la lectura o escritura");
		}
	}
}
