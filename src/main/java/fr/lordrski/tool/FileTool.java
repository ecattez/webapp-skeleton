/**
 * This file is part of webapp-skeleton.
 *
 * webapp-skeleton is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * webapp-skeleton is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with webapp-skeleton.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.lordrski.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * FileTool offre quelques fonctionnalités pour manipuler les fichiers
 */
public class FileTool {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private FileTool() {}
	
	/**
	 * Copie un fichier dans un emplacement du disque
	 * 
	 * @param tmp le fichier à copier stocké dans la zone temporaire du disque
	 * @param folder le dossier
	 * @param filename le nom du fichier
	 * @return vrai si la copie s'est bien déroulée
	 */
	public static boolean upload(File tmp, String folder, String filename) {
		try (FileInputStream fin = new FileInputStream(tmp);
				FileOutputStream fout = new FileOutputStream(folder + File.separator + filename)) {
			FileChannel fchin = fin.getChannel();
			FileChannel fchout = fout.getChannel();
			fchin.transferTo(0, fchin.size(), fchout);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
