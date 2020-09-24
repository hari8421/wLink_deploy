package com.ecom.service;

import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dao.FileManagerDAO;
import com.ecom.domain.FileMangerRequestDtl;

@Service
public class FileManagerService {

	@Autowired
	FileManagerDAO FM;

	private static final Logger logger = LoggerFactory.getLogger(FileManagerService.class);

	public List<FileSystemResource> getImages(FileMangerRequestDtl FMRequest) {
		List<FileSystemResource> retList = null;

		try {
			retList = FM.getImageSrc(FMRequest.getReferenceId(), FMRequest.getVendorId(), FMRequest.getReferenceCode());

		} catch (Exception e) {
			logger.error("getImageSrc error-->" + e);
		}
		return retList;
	}

	public UrlResource getSingleImage(int id, String vendorId, String fileCatCode) throws MalformedURLException {
		return FM.getSingleImage(id,vendorId,fileCatCode);
	}

	public UrlResource GetAppImageBySeq(int id, String vendorId, String fileCatCode, String sequence) throws MalformedURLException {
		return FM.GetAppImageBySeq(id,vendorId,fileCatCode,sequence);
	}

	public String uploadFile(MultipartFile file, String fName, String psId, String refernceCode, String sequence,
			String vendorid) {
		return FM.uploadFile(file,fName,psId,refernceCode,sequence,vendorid);
	}

	

}
