package com.ecom.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ecom.domain.FileMangerRequestDtl;
import com.ecom.service.FileManagerService;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileManagerController {
	@Autowired
	FileManagerService FMService;

	@RequestMapping(value = "/GetAppImages", produces = "image/png")
	@PostMapping
	public List<FileSystemResource> getImageSrc(@RequestBody FileMangerRequestDtl fileMangerReq) {

		return FMService.getImages(fileMangerReq);
	}

	@GetMapping(value = "/GetAppImage", produces = "image/png")
	@ResponseBody
	public UrlResource getImageSrc(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "vendorId", required = true) String vendorId,
			@RequestParam(value = "filecatcode", required = true) String fileCatCode) throws MalformedURLException {

		return FMService.getSingleImage(id, vendorId, fileCatCode);
	}

	@GetMapping(value = "/GetAppImageBySeq", produces = "image/png")
	@ResponseBody
	public UrlResource GetAppImageBySeq(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "vendorId", required = true) String vendorId,
			@RequestParam(value = "filecatcode", required = true) String fileCatCode,
			@RequestParam(value = "sequence", required = true) String sequence) throws MalformedURLException {

		return FMService.GetAppImageBySeq(id, vendorId, fileCatCode, sequence);
	}

	@GetMapping(value = "/GetAwsFile")
	@ResponseBody
	public String GetAppImageByS3() {
		String urlf = "";
		try {
			Regions clientRegion = Regions.AP_SOUTH_1;
			String bucketName = "ecomwlink";
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ4OKPJ76H4ITLGFQ",
					"AOVsXOGgAqapYc6E6F2k/q3JBhIeZrZkmY3dEy/H");
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

			urlf = s3Client.getUrl(bucketName, "dhms.png").toExternalForm();

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return urlf;
	}

	@PostMapping(value = "/uploadFileForProd")
	@ResponseBody
	public String uploadFileForProd(@RequestPart(value = "file") MultipartFile file,
			@RequestPart(value = "fName") String fName, @RequestPart(value = "psId") String psId,
			@RequestPart(value = "refernceCode") String refernceCode, @RequestPart(value = "sequence") String sequence,
			@RequestPart(value = "vendorid") String vendorid) {

		return FMService.uploadFile(file, fName, psId, refernceCode, sequence, vendorid);

	}

}