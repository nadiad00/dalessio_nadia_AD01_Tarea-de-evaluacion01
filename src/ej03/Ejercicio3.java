package ej03;

import java.io.*;
import java.util.Arrays;

public class Ejercicio3 {

	public static void main(String[] args) {
		File zip = new File ("."+ File.separator + "src" + File.separator + "ej03" + File.separator + "firmas");
		InputStream input = null;
		String archivos[] = zip.list();
		
		try {
			
			for(int i = 0; i < archivos.length; i++) {
				File fichero = new File(zip, archivos[i]);
				input = esZip(fichero);
			}
			
			input.close();
		} catch (IOException e) {
			System.out.println("IOException - ha ocurrido un error durante la lectura del fichero");
		}
		
	}
	
	
	// Metodo para leer los ficheros y ver si son un ZIP
	public static InputStream esZip(File fich) throws IOException {
		// Firma en decimal de un zip
		int firmaZip[] = {80, 75, 3, 4}; 
		
		System.out.println("Leyendo el archivo: " + fich.getName());
		
		InputStream input = new FileInputStream(fich);
			
		int[] cabeceraZip = new int[4];
		boolean menosCuatro = false;
			
		// Guarda en cabeceraZip los bytes en decimal que lee del fichero
		for (int i = 0; i < 4; i++) {
			cabeceraZip[i] = input.read();
			if(cabeceraZip[i] == -1) {
				menosCuatro = true;
			}
        }
		
		if(menosCuatro) {
			System.out.println("El fichero contiene menos de cuatro bytes");
		}
 
        if (Arrays.equals(cabeceraZip, firmaZip)) {
        	System.out.println("La cabecera es compatible con un fichero ZIP");
        } else {
        	System.out.println("La cabecera no corresponde con la firma de un fichero ZIP");
        }
        
        return input;
	}
}
