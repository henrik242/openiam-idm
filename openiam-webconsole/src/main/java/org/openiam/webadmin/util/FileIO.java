package org.openiam.webadmin.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO {

	
	/**
	 * Returns a filename of the form:  name-ID.extension
	 * where ID is set to the largest ID for files of that pattern within the given directory, plus one
	 * 	
	 * @param dir directory in which files exist
	 * @param name name of the new file
	 * @param extension file extension (can be null)
	 * @return filename of the form: name-XX.extension; null if directory or name is invalid
	 */
	public static String getUniqueFilename(File dir, String name, String extension) {
		String retval = null;

		if (dir != null && dir.isDirectory() && name != null && !name.isEmpty()) {
			// Get next default ID
			File[] in_dir = dir.listFiles();
			int max_id = 0;
			if (in_dir != null && in_dir.length > 0) {

				StringBuffer clean_ext = new StringBuffer();
				if (extension != null) {
					clean_ext.append(extension.trim());

					// Check if . has been specified
					if ((clean_ext.length() > 0) && (clean_ext.charAt(0) != '.'))
						clean_ext.insert(0, '.');
				}

				StringBuffer regex_ext = new StringBuffer(clean_ext.toString());
				if (regex_ext.length() > 0)
					regex_ext.insert(0, "\\");
				Pattern pattern = Pattern.compile(name + "\\-([0-9]+)" + regex_ext.toString());
				
				for (File f : in_dir) {
					Matcher mtch = pattern.matcher(f.getName());
					if (mtch.find()) {

						String num = mtch.group(1);
						if (num != null && !num.isEmpty()) {
							try {
								Integer new_i = Integer.parseInt(num);
								if (new_i.intValue() > max_id)
									max_id = new_i.intValue();
							} catch (NumberFormatException nfe) {
							}
						}
					}

					retval = name + "-" + (max_id + 1) + clean_ext.toString();
				}
			}
		}
		return retval;
	}
	
	/**
	 * Equivalent to calling getUniqueFilename(dir,name,null)
	 * @param dir
	 * @param name
	 * @return
	 */
	public static String getUniqueFilename(File dir, String name) {
		return getUniqueFilename(dir, name, null);
	}
	
	
	
	/**
	 * Load text from target file into a StringBuffer
	 * @param f source file
	 * @return populated StringBuffer on successful loading; null on error
	 */
	public static StringBuffer loadTextFile(File f) {
		StringBuffer sb = null;
		
		if (f != null) {
			BufferedReader reader = null;
			try {
				// Load file; will throw an exception on error
				reader = new BufferedReader(new FileReader(f));
				sb = new StringBuffer();
				
				for (String t = null; ((t = reader.readLine()) != null);) {
					sb.append(t);
					sb.append(System.getProperty("line.separator"));
				}
			} catch (Exception e) {
				sb = null;
				e.printStackTrace();
			} finally {
				try {
					if (reader != null)
						reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb;
	}
	
	/**
	 * Save contents of sb to a file specified by dest
	 * @param dest target text file
	 * @param sb contents of file
	 * @return true on commit; false otherwise
	 */
	public static boolean saveTextFile(File dest, String sb) {
		boolean retVal = false;
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(dest));
			out.write(sb);
			out.close();
			retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static StringBuffer saveTextFile() {
		
		return null;
	}
	
	
	
	
	
	
	/**
	 * 
	 * @return Path to scripting folder base
	 */
	public static String getScriptFolderBasePath() {
		ResourceBundle rb = ResourceBundle.getBundle("securityconf");
		return rb.getString("scriptRoot");
	}
	

	
	
	
	
}
