package com.ecom.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ecom.domain.FileMangerLocationDtl;
import com.ecom.rowmapper.FileMLocationDtlRowMapper;

@Repository
@Transactional
public class FileManagerDAO {

	private static final Logger logger = LoggerFactory.getLogger(FileManagerDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public List<FileSystemResource> getImageSrc(String id, String vendorId, String filecatcode) {
		ArrayList<FileSystemResource> returnContList = new ArrayList<FileSystemResource>();
		try {
			List<FileMangerLocationDtl> locationList = null;
			String getFileNameQ = "select LOCATION_URL from file_manager where VENDOR_ID=? and REFERENCE_ID=? and REFERENCE_CODE=?";
			RowMapper<FileMangerLocationDtl> rowMapper = new FileMLocationDtlRowMapper();
			locationList = this.jdbc.query(getFileNameQ, rowMapper, vendorId, id, filecatcode);

			for (int i = 0; i < locationList.size(); i++) {
				returnContList.set(i, new FileSystemResource(new File(locationList.get(i).getLocationId())));
			}

		} catch (Exception ex) {
			logger.error("getImageSrc method exception" + ex);
		}
		return returnContList;
	}

	public UrlResource getSingleImage(int id, String vendorId, String fileCatCode) throws MalformedURLException {
		try {

			String getFileName = "select LOCATION_URL from file_manager where REFERENCE_ID = ? and REFERENCE_CODE= ? and VENDOR_ID=? order by SEQUENCE asc limit 1";
			String fileName = this.jdbc.queryForObject(getFileName, new Object[] { id, fileCatCode, vendorId },
					String.class);
			Regions clientRegion = Regions.AP_SOUTH_1;
			String bucketName = "ecomwlink";
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ4OKPJ76H4ITLGFQ",
					"AOVsXOGgAqapYc6E6F2k/q3JBhIeZrZkmY3dEy/H");
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

			String urlf = s3Client.getUrl(bucketName, fileName).toURI().toString();// toExternalForm().;

			UrlResource file = new UrlResource(urlf);

			return file;

		} catch (Exception ex) {
			logger.error("getImageSrc method exception" + ex);
			return new UrlResource("");
		}
	}

	public UrlResource GetAppImageBySeq(int id, String vendorId, String fileCatCode, String sequence)
			throws MalformedURLException {
		try {

			String getFileName = "select LOCATION_URL from file_manager where REFERENCE_ID = ? and REFERENCE_CODE= ? and VENDOR_ID=? and SEQUENCE=?";
			String fileName = this.jdbc.queryForObject(getFileName,
					new Object[] { id, fileCatCode, vendorId, sequence }, String.class);
			Regions clientRegion = Regions.AP_SOUTH_1;
			String bucketName = "ecomwlink";
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ4OKPJ76H4ITLGFQ",
					"AOVsXOGgAqapYc6E6F2k/q3JBhIeZrZkmY3dEy/H");
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

			String urlf = s3Client.getUrl(bucketName, fileName).toExternalForm();

			UrlResource file = new UrlResource(urlf);

			return file;

		} catch (Exception ex) {
			logger.error("GetAppImageBySeq method exception" + ex);
			return new UrlResource("");
		}
	}

	public String uploadFile(MultipartFile file, String fName, String psId, String refernceCode, String sequence,
			String vendorid) {
		String urlf = "";
		try {

			String getQ = "select count(*) from file_manager where REFERENCE_CODE = ? and REFERENCE_ID=? and SEQUENCE =? and VENDOR_ID =?";
			int cnt = this.jdbc.queryForObject(getQ, new Object[] { refernceCode, psId, sequence, vendorid },
					Integer.class);

			if (cnt > 0) {
				getQ = "select LOCATION_URL from file_manager where REFERENCE_CODE = ? and REFERENCE_ID=? and SEQUENCE =? and VENDOR_ID =?";
				fName = this.jdbc.queryForObject(getQ, new Object[] { refernceCode, psId, sequence, vendorid },
						String.class);

			}

			Regions clientRegion = Regions.AP_SOUTH_1;
			String bucketName = "ecomwlink";
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ4OKPJ76H4ITLGFQ",
					"AOVsXOGgAqapYc6E6F2k/q3JBhIeZrZkmY3dEy/H");
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
			String fileName = file.getOriginalFilename();
			File file1 = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file1);
			fos.write(file.getBytes());
			fos.close();

			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fName, file1);
			// s3client.putObject(new PutObjectRequest(bucketName, keyName,
			// file).withCannedAcl(CannedAccessControlList.PublicRead))
			s3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
			// removing the file created in the server
			file1.delete();
			if (cnt == 0) {
				String insertQ = "INSERT INTO `file_manager` (`REFERENCE_ID`, `REFERENCE_CODE`, `LOCATION_URL`, `SEQUENCE`, `VENDOR_ID`) VALUES (?, ?, ?, ?, ?);";
				this.jdbc.update(insertQ, psId, refernceCode, fName, sequence, vendorid);
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return urlf;
	}

}
