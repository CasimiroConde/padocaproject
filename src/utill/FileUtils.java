package utill;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for file handling
 * 
 * @author marcio.barros
 */
public class FileUtils
{
	/**
	 * Loads all contents of a file to a string
	 */
	public static String readAllText(String nomeArquivo) throws IOException
	{
		StringBuffer buffer = new StringBuffer();

		try
		{
			FileReader reader = new FileReader(nomeArquivo);
			BufferedReader breader = new BufferedReader(reader);
			String line;
			
			while ((line = breader.readLine()) != null)
				buffer.append(line + " ");
			
			breader.close();
			reader.close();
			
		} catch(FileNotFoundException fnf)
		{
			throw new IOException(nomeArquivo + ": arquivo nao encontrado");
		} catch (IOException e)
		{
			throw new IOException(nomeArquivo + ": erro de leitura");
		}
		
		return buffer.toString();
	}
}