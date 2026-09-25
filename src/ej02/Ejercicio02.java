package ej02;

import java.io.*;

public class Ejercicio02 {

	public static void main(String[] args) {
		File entrada = new File ("."+ File.separator + "src" + File.separator + "ej02" + File.separator + "accesos.log");
		File salida = new File ("."+ File.separator + "src" + File.separator + "ej02" + File.separator + "errores.log");
		
		// Try-catch para la creacion del fichero errores.log
		try {
			// Si ya existe lo borra
			if(salida.exists()) {
				salida.delete();
			}
			
			if(salida.createNewFile()) {
				System.out.println("errores.log creado correctamente.");
			} else {
				System.out.println("No se ha podido crear errores.log.");
			}
		} catch(IOException e) {
			System.out.println("IOException - ha ocurrido un error al crear el fichero errores.log");
		}

		// Try-catch para la lectura y escritura
		try {
			BufferedReader reader = new BufferedReader(new FileReader(entrada));
			BufferedWriter writer = new BufferedWriter(new FileWriter(salida));
			
			System.out.println("Empieza la lectura y escritura.");
			String linea;
			int errores = 0;
			while((linea = reader.readLine()) != null) {
				// Si la linea leida contiene la palabra ERROR la escribe en errores.log y sube el contador de errores
				if(linea.contains("ERROR")) {
					errores++;
					writer.write(linea);
					writer.newLine();
				}
			}
			
			// Escribe el total de lineas con errores
			writer.write("Total de lineas con errores: " + errores);
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
