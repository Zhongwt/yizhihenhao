package com.yzhh.backstage.api.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PackWord {

	public static final String packPath = "/home/yzhh/file/简历.rar";

	// 文件打包下载
	public static InputStream downLoadFiles(List<File> files) throws Exception {
		InputStream is = null;
		try {
			// 这个集合就是你想要打包的所有文件， 这里假设已经准备好了所要打包的文件
			// List<File> files = new ArrayList<File>();
			// 创建一个临时压缩文件， 我们会把文件流全部注入到这个文件中 这里的文件你可以自定义是.rar还是.zip
			File file = new File(packPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 创建文件输出流
			FileOutputStream fous = new FileOutputStream(file);
			// 打包的方法我们会用到ZipOutputStream这样一个输出流, 所以这里我们把输出流转换一下
			ZipOutputStream zipOut = new ZipOutputStream(fous);
			// 这个方法接受的就是一个所要打包文件的集合， 还有一个ZipOutputStream
			zipFile(files, zipOut);
			zipOut.close();
			fous.close();
			return new FileInputStream(file);
			// return downloadZip(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 把接受的全部文件打成压缩包
	 * 
	 * @param List<File>;
	 * @param org.apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(@SuppressWarnings("rawtypes") List files, ZipOutputStream outputStream) {
		int size = files.size();
		for (int i = 0; i < size; i++) {
			File file = (File) files.get(i);
			zipFile(file, outputStream);
		}
	}

	/**
	 * 根据输入的文件与输出流对文件进行打包
	 * 
	 * @param File
	 * @param org.apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {

					FileInputStream in = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(in, 512);
					String fileName = inputFile.getName();
					fileName = fileName.substring(0, fileName.indexOf("."))+"_"+new Date().getTime()+".pdf";
					ZipEntry entry = new ZipEntry(fileName);
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象
					bins.close();
					in.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
