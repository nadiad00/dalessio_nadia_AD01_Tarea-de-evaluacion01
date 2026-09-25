package ej04;

import java.io.*;

public class Ejercicio4A {

	public static void main(String[] args) {

		// Arrays con los datos proporcionados
		int[] ids = {1, 2, 3, 4, 5, 6, 7, 8};
		String[] codigos = {
		    "EQ0001", "EQ0002", "EQ0003", "EQ0004",
		    "EQ0005", "EQ0006", "EQ0007", "EQ0008"
		};
		String[] nombres = {
		    "Portatil Lenovo",
		    "Monitor Dell 24",
		    "Teclado Logitech",
		    "Raton Inalambrico",
		    "Webcam Logitech",
		    "Proyector Epson",
		    "Dock USB-C",
		    "Auriculares Jabra"
		};
		String[] categorias = {
		    "portatil",
		    "monitor",
		    "periferico",
		    "periferico",
		    "periferico",
		    "proyector",
		    "accesorio",
		    "audio"
		};
		int[] stocks = {6, 12, 18, 25, 9, 4, 14, 11};
		double[] precios = {899.90, 189.95, 49.90, 24.50, 79.00, 549.99, 129.00, 159.90};

		// Calcula la longitud del registro.
		int tamId = Integer.BYTES;
		int tamCodigo = 8 * Character.BYTES;
		int tamNombre = 20 * Character.BYTES;
		int tamCategoria = 12 * Character.BYTES;
		int tamStock = Integer.BYTES;
		int tamPrecio = Double.BYTES;
		int tamRegistro = tamId
		        + tamCodigo
		        + tamNombre
		        + tamCategoria
		        + tamStock
		        + tamPrecio;
		
		File fichero = new File("."+ File.separator + "src" + File.separator + "ej04" + File.separator + "inventario.dat");
		// Si ya existe se borra
		if(fichero.exists()){
			fichero.delete();
		}
		
		// Try-catch para la apertura del RandomAccessFile
		try(RandomAccessFile file = new RandomAccessFile(fichero, "rw"))
		{
			int cant_registros = 0;
			// Bucle que escribe para cada registro
			for(int i = 0; i < ids.length; i++) {
				String codigo = ajustarCadena(codigos[i], 8);
				String nombre = ajustarCadena(nombres[i], 20);
				String categoria = ajustarCadena(categorias[i], 12);
				
				file.writeInt(ids[i]);
				file.writeChars(codigo);
				file.writeChars(nombre);
				file.writeChars(categoria);
				file.writeInt(stocks[i]);
				file.writeDouble(precios[i]);
				cant_registros++;
			}
			
			System.out.println("Inventario creado correctamente.");
			System.out.println("Registros guardados: " + cant_registros);
			System.out.println("Tamaño de cada registro: " + tamRegistro + " bytes");
			
			file.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException - ha ocurrido un error durante la lectura o escritura");
		} catch (IOException e) {
			System.out.println("IOException - ha ocurrido un error durante la lectura o escritura");
		}
	}
	
	// Metodo para ajustar el tamaño de una cadena al requerido
	public static String ajustarCadena(String cadena, int longitud) {
		String resultado = cadena;
		int longCadena = cadena.length();
		if(longCadena > longitud) {
			resultado = cadena.substring(0, longitud);
		} else if(longCadena < longitud) {
			int espacios = longitud - longCadena;
			for(int i = 0; i < espacios; i++) {
				resultado = resultado + " ";
			}
		}
		
		return resultado;
	}

}
