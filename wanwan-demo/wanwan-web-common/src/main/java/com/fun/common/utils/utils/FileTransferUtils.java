package com.fun.common.utils.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;

/**
 * @author zhanglvxiu
 * @date 2021/9/14
 */

@Log4j2
public class FileTransferUtils {

	public static void main(String[] args) throws IOException {
		//获取类路径-spring自带
		//输出：/E:/Desktop/SpringBlade/blade-demo/web-common-20210720/target/classes/
		//String cp1= ClassUtils.getDefaultClassLoader().getResource("").getPath();
		//获取类路径-hutool
		//输出：E:/Desktop/SpringBlade/blade-demo/web-common-20210720/target/classes/
		//String cp2 = ClassUtil.getClassPath();
//		MultipartFile multipartFile = urlToMultipartFile("http://192.168.30.226:9000/fhdi/upload/20220602/bc826f4eeed058219d4f2aed292e7c4a.png");
//		System.out.println(multipartFile.getName());
	}

	/**
	 * 下载文件
	 * @param response 响应
	 * @param filePath 文件路径（注意带上分隔符）
	 * @param fileName 文件名（注意带上后缀）
	 */
	public static Boolean download(HttpServletResponse response, String filePath, String fileName) {

		//支持中文
		try {
			fileName = URLEncoder.encode(fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		// 设置强制下载不打开
		response.setContentType("application/force-download");
		// 设置文件名
//		response.addHeader("Content-Disposition", "attachment;fileName=" + filePath.substring(filePath.lastIndexOf(File.separator)));
		response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
		//路径是否存在，不存在创建
		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		try (FileInputStream inputStream = new FileInputStream(filePath+fileName);
			 ServletOutputStream outputStream = response.getOutputStream()){
			IOUtils.copy(inputStream, outputStream);
			log.info("文件开始下载...");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			log.info("文件下载失败...");
			return false;
		}
	}

    /**
     * 将url资源转换为MultipartFile
     */
	public static MultipartFile urlToMultipartFile(String url) {
		File file;
		MultipartFile multipartFile = null;
		try {
			HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
			httpUrl.connect();
			file = inputStreamToFile(httpUrl.getInputStream(),"template.png");
			multipartFile = file2MultipartFile(file);
			httpUrl.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return multipartFile;
	}

	/**
	 * 将本地文件转换为MultipartFile
	 */
	public static MultipartFile localToMultipartFile(String dir) {
		File targetFile = new File(dir);
		if (!targetFile.exists()) {
			log.error("未找到文件！");
			return null;
		}
		return file2MultipartFile(targetFile);
	}

	/**
	 * 将File对象转为MultipartFile
	 */
	public static MultipartFile file2MultipartFile(File targetFile) {
		FileItem fileItem;
		try {
			fileItem = new DiskFileItem(targetFile.getName(), Files.probeContentType(targetFile.toPath()),
				false, targetFile.getName(), (int) targetFile.length(), targetFile.getParentFile());
		} catch (IOException e) {
			log.info("转MultipartFile失败,错误信息：", e);
			return null;
		}

		try (InputStream input = new FileInputStream(targetFile);
			 OutputStream os = fileItem.getOutputStream()) {
			MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
			IOUtils.copy(input, os);
			return multipartFile;
		} catch (Exception e) {
			log.info("File转MultipartFile失败,错误信息：",e);
		}
		return null;
	}

	/**
	 * inputStream 转 File
	 */
	public static File inputStreamToFile(InputStream ins, String name) throws Exception{
		File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
		OutputStream os = new FileOutputStream(file);
		int bytesRead;
		int len = 8192;
		byte[] buffer = new byte[len];
		while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
		return file;
	}
}
