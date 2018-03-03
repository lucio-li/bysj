package service;

import model.Moments;

import java.io.UnsupportedEncodingException;

public interface UploadService {
	//上传照片前先上传数据，动态内容，时间等
	String addContent(Moments moments) throws UnsupportedEncodingException;
	void uploadImage();
}
