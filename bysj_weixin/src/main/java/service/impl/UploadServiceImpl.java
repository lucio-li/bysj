package service.impl;

import dao.MomentsMapper;
import model.human.Moments;
import org.springframework.stereotype.Service;
import service.UploadService;
import utils.AppConfig;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service(value="UploadService")
public class UploadServiceImpl implements UploadService {
	@Resource
    private MomentsMapper momentsMapper;

	/**
	 * 上传照片前的数据插入数据库
	 * @param moments
	 * @return 文件夹路径
	 */
	public String addContent(Moments moments) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		//String time = moments.getTime();
		File file = new File(this.getClass().getResource("/").getPath());
		String destDirectory = file.getParentFile().getParentFile().getParent() + "/images/" + moments.numberDataTime();
		File directory = new File(destDirectory);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		moments.setDirectory(destDirectory);
		momentsMapper.insertOne(moments);
		return destDirectory;
	}

	public void uploadImage() {
		// TODO Auto-generated method stub
		
	}

	public void downloadImage(String media_id, String directory) {
		System.out.println("上传照片" + media_id);
		System.out.println("目录" + directory);
//
		try {
			String token = AppConfig.access_token;
			String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + media_id;


			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			conn.connect();
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			String content_disposition = conn.getHeaderField("content-disposition");
			//微信服务器生成的文件名称
//			String file_name ="";
//			String[] content_arr = content_disposition.split(";");
//			if(content_arr.length  == 2){
//				String tmp = content_arr[1];
//				int index = tmp.indexOf("\"");
//				file_name =tmp.substring(index+1, tmp.length()-1);
//			}
			//生成不同文件名称
			File file = new File(directory + "/" + media_id + ".jpg");
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			byte[] buf = new byte[2048];
			int length = bis.read(buf);
			while(length != -1){
				bos.write(buf, 0, length);
				length = bis.read(buf);
			}
			bos.close();
			bis.close();
			System.out.println("文件" + directory + "/" + media_id);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
