package org.imperativeFiction.utils;

import java.io.*;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.imperativeFiction.engine.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/29/15.
 */
public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	private static int buffersize = 1024;

	public static File decompressFile(String fileName) throws IOException, GameException {
		logger.debug("decompressing file:" + fileName);
		if (fileName.endsWith(".ifg")) {
			File f = gunzip(fileName);
			File resf = new File(f.getName() + ".xml");
			resf.deleteOnExit();
			f.renameTo(resf);
			return resf;
		} else
			throw new GameException("Game not Valid" + fileName);
	}

	public static File compressFile(String fileName) throws IOException, GameException {
		if (fileName.endsWith(".xml")) {
			File f = gzip(fileName);
			File resf = new File(f.getName() + ".ifg");
			resf.deleteOnExit();
			f.renameTo(resf);
			return resf;
		} else
			throw new GameException("Game not Valid" + fileName);
	}

	public static File gzip(String fileName) throws IOException {
		FileInputStream fin = new FileInputStream(fileName);
		BufferedInputStream in = new BufferedInputStream(fin);
		File res = File.createTempFile("imperativeFiction", ".tmp");
		GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(new FileOutputStream(res));
		final byte[] buffer = new byte[buffersize];
		int n = 0;
		while (-1 != (n = in.read(buffer))) {
			gzOut.write(buffer, 0, n);
		}
		gzOut.close();
		in.close();
		return res;
	}

	public static File gunzip(String fileName) throws IOException {
		FileInputStream fin = new FileInputStream(fileName);
		BufferedInputStream in = new BufferedInputStream(fin);
		File res = File.createTempFile("imperativeFiction", ".tmp");
		res.createNewFile();
		res.deleteOnExit();
		FileOutputStream out = new FileOutputStream(res);
		GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
		final byte[] buffer = new byte[buffersize];
		int n = 0;
		while (-1 != (n = gzIn.read(buffer))) {
			out.write(buffer, 0, n);
		}
		out.close();
		gzIn.close();
		return res;
	}

	public static String getCurrentDir() {
		File file = new File(".");
		return file.getAbsolutePath();
	}

	public static File getGameFile(String fileName) throws GameException {
		File gameFile = null;
		if (fileName == null || (fileName != null && fileName.equals(""))) {
			throw new GameException("Game not found" + fileName);
		} else {
			if (fileName != null && fileName.endsWith(".xml")) {
				gameFile = new File(fileName);
			} else if (fileName != null && fileName.endsWith(".ifg")) {
				try {
					gameFile = FileUtils.decompressFile(fileName);
				} catch (IOException e) {
					throw new GameException(e);
				}
			}
		}
		return gameFile;
	}
}
