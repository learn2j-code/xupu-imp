package com.dlm.imp.bam_basemanagement.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.dlm.imp.bam_basemanagement.service.ClanPictureService;
import com.dlm.imp.bam_basemanagement.vo.ResponseEntity;
import com.dlm.imp.constant.CommonConstant;
import com.dlm.imp.util.ZipUtil;

//告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("upload")
public class UploadController {
	
	@Autowired
	ClanPictureService clanPictureService;
	
	private Log logger = LogFactory.getLog(this.getClass());
	private static final String [] extensionPermit = {"rar", "zip"};
	
	private static final String uploadPackageLocation = "/uploadPackage/";//zip、rar上传压缩包存放地
	private static final String uploadClanPicturesLocation = "/uploadClanPictures/";//族谱上传地址
	private static final String uploadJiaFengPicturesLocation = "/uploadJiaFengPictures/";//家风上传地址
	private static final String uploadJCTXPicturesLocation = "/uploadJCTXPicturesLocation/";//家传天下图片上传地址
	private static final String headimage = "/headimage/";//家风家庭成员头像的图片
	private static final String others = "others";//其他除族谱之外的图片集合
	private static final String uploadBDPImage = "/uploadBDPImage/";//基础数据平台的图片
	private static final String uploadWAPImage = "/uploadWAPImage/";//手机端的图片
	private static final String uploadStoreImage = "/uploadStoreImage/";//宗祠网商城的图片
	private static final String uploadWAPErrorInfoImage = "/uploadWAPErrorInfoImage/";//手机端上传的错误信息图片
	
	private static final String zip = "zip";//zip上传压缩包存放地
	private static final String rar = "rar";//rar上传压缩包存放地
	
	@RequestMapping("/uploadZip")
    public @ResponseBody ResponseEntity uploadZip(HttpServletRequest request, @RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		
		String curProjectPath = request.getServletContext().getRealPath("/");
		String surname = request.getParameter("surname");
		String familyIdAndFamilyName = request.getParameter("familyIdAndFamilyName");
		String volumeId = request.getParameter("volumeId");
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		//上传压缩文件地址
		String savePackagePath = curProjectPath + uploadPackageLocation;
		/**
		 * 没有则新建目录
		 */
		File savePackageDirectory = new File(savePackagePath);
		if (!savePackageDirectory.exists()) {
			savePackageDirectory.mkdirs();
		}
		
		//解压后的图片文件地址
		String saveClanPicturePath = curProjectPath + uploadClanPicturesLocation 
				+ surname + "/" + familyIdAndFamilyName + "/" +volumeId+"-"+ strDate+strDateTime;
		
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
			
			String fileExtension = FilenameUtils.getExtension(fileName);
			if(!ArrayUtils.contains(extensionPermit, fileExtension)) {
				//throw new NoSupportExtensionException("No Support extension.");
				return null;
			}
			//下面的方法用于上传文件
			File packageFile = new File(savePackageDirectory, newFileName);
			if (!packageFile.isDirectory())
				packageFile.createNewFile();
			//解压缩,上传的压缩包存放在saveClanPicturePath目录下，解压后的文件存在saveClanPicturePath目录下
			try {
				file.transferTo(packageFile);
				List<String> uploadImagePathList = new ArrayList<String>();
				if(fileExtension.equalsIgnoreCase(zip)) {
					uploadImagePathList = ZipUtil.unZipAndReturnPath(savePackagePath + newFileName,saveClanPicturePath);
				}
				if(fileExtension.equalsIgnoreCase(rar)) {
					uploadImagePathList = ZipUtil.unRarAndReturnPath(savePackagePath + newFileName,saveClanPicturePath);
				}
				// 将返回的图片地址列表保存
				clanPictureService.addClanPictureByVolumeIdAndAddress(Integer.valueOf(volumeId), uploadImagePathList);

			} catch (Exception e) {
				responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
				responseEntity.setErrorMsg(e.getMessage());
				e.printStackTrace();
			} finally {
//				packageFile.deleteOnExit();
				if(fileExtension.equalsIgnoreCase(zip)) {
					ZipUtil.deleteFile(packageFile);
				}
				if(fileExtension.equalsIgnoreCase(rar)) {
					
				}
			}
		}
		logger.info("UploadController#uploadZip() end");
    	return responseEntity;
	}
	
	@RequestMapping("/uploadFamilyImage")
    public @ResponseBody ResponseEntity uploadFamilyImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
//		String originalLocation = request.getParameter("originalLocation");
		String surname = request.getParameter("surname");
		String familyIdAndFamilyName = request.getParameter("familyIdAndFamilyName");
		
		
		String uploadFileAddress = curProjectPath  + uploadClanPicturesLocation 
				+ surname + "/" + familyIdAndFamilyName + "/" +others;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//			newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadClanPicture")
    public @ResponseBody ResponseEntity uploadClanPicture(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
		String originalLocation = request.getParameter("originalLocation");
		
		String uploadFileAddress = curProjectPath.substring(0, curProjectPath.indexOf("imp")) + 
				originalLocation.substring(0, originalLocation.lastIndexOf("\\"));
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadBDPImage")
    public @ResponseBody ResponseEntity uploadBDPImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
		String moduleName = request.getParameter("moduleName");
		
		String uploadFileAddress = curProjectPath  + uploadBDPImage 
				+ moduleName;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//			newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadWAPImage")
    public @ResponseBody ResponseEntity uploadWAPImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
		String householdId = request.getParameter("householdId");
		
		String uploadFileAddress = curProjectPath  + uploadWAPImage 
				+ householdId;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//			newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadWAPErrorInfoImage")
    public @ResponseBody ResponseEntity uploadWAPErrorInfoImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
		String userPhone = request.getParameter("phone");
		
		String uploadFileAddress = curProjectPath  + uploadWAPErrorInfoImage 
				+ userPhone;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//			newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	//宗祠网商城的图片管理
	@RequestMapping("/uploadStoreImage")
    public @ResponseBody ResponseEntity uploadStoreImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
		String productId = request.getParameter("productId");
		
		String uploadFileAddress = curProjectPath  + uploadStoreImage 
				+ productId;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//			newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadJiaFengHouseholdImage")
    public @ResponseBody ResponseEntity uploadJiaFengHouseholdImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
//			String originalLocation = request.getParameter("originalLocation");
		String householdId = request.getParameter("householdId");
		String albumId = request.getParameter("albumId");//相册
		
		
		String uploadFileAddress = curProjectPath  + uploadJiaFengPicturesLocation 
				+ householdId + "/" + albumId + "/";
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//				newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadJiaFengHouseholdImageList")
    public @ResponseBody ResponseEntity uploadJiaFengHouseholdImageList(HttpServletRequest request, List<MultipartFile> fileList)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
//			String originalLocation = request.getParameter("originalLocation");
		String householdId = request.getParameter("householdId");
		String albumId = request.getParameter("albumId");//相册
		
		
		String uploadFileAddress = curProjectPath  + uploadJiaFengPicturesLocation 
				+ householdId + "/" + albumId + "/";
		
		Map<String, Object> data = new HashMap<String, Object>();
		List<String> uploadImagePathList = new ArrayList<>();
		for(MultipartFile file:fileList) {
			String fileName = file.getOriginalFilename();
			
			String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
			
			try {
				File newFile = new File(uploadFileAddress, newFileName);
				/**
				 * 没有则新建目录
				 */
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
//				newFile.getParentFile().mkdirs();
				file.transferTo(newFile);
				String uploadImagePath = newFile.getAbsolutePath();
				uploadImagePathList.add(uploadImagePath.substring(uploadImagePath.indexOf("imp")));
			} catch (Exception e) {
				responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
				responseEntity.setErrorMsg(e.getMessage());
				e.printStackTrace();
			} 
		}
		data.put("uploadImagePathList",uploadImagePathList);
		responseEntity.setData(data);
		return responseEntity;
    }
	
	@RequestMapping("/uploadJiaFengHeadImage")
    public @ResponseBody ResponseEntity uploadJiaFengHeadImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
//			String originalLocation = request.getParameter("originalLocation");
		String householdId = request.getParameter("householdId");
		
		String uploadFileAddress = curProjectPath  + uploadJiaFengPicturesLocation 
				+ householdId + headimage;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//				newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
	
	@RequestMapping("/uploadJCTXImage")
    public @ResponseBody ResponseEntity uploadJCTXImage(HttpServletRequest request, MultipartFile file)
            throws IllegalStateException, IOException {
		ResponseEntity responseEntity = new ResponseEntity();
		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String strDateTime = new SimpleDateFormat("HHmmss").format(date);
		
		String curProjectPath = request.getServletContext().getRealPath("/");
//			String originalLocation = request.getParameter("originalLocation");
		
		String uploadFileAddress = curProjectPath  + uploadJCTXPicturesLocation;
		
		String fileName = file.getOriginalFilename();
		
		String newFileName = FilenameUtils.getBaseName(fileName)+"-"+strDate+strDateTime+"."+FilenameUtils.getExtension(fileName);
		
		try {
			File newFile = new File(uploadFileAddress, newFileName);
			/**
			 * 没有则新建目录
			 */
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
//				newFile.getParentFile().mkdirs();
	        file.transferTo(newFile);
	        Map<String, Object> data = new HashMap<String, Object>();
	        String uploadImagePath = newFile.getAbsolutePath();
	        data.put("uploadImagePath",uploadImagePath.substring(uploadImagePath.indexOf("imp")));
	        responseEntity.setData(data);
		} catch (Exception e) {
			responseEntity.setSuccess(CommonConstant.RESPONSE_FAIL);
			responseEntity.setErrorMsg(e.getMessage());
			e.printStackTrace();
		} 
		return responseEntity;
    }
//	@RequestMapping("/uploadImage")
//    public ModelAndView uploadImage(HttpServletRequest request, UploadedImageFile file)
//            throws IllegalStateException, IOException {
//        String name = RandomStringUtils.randomAlphanumeric(10);
//        String newFileName = name + ".jpg";
//        File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
//        newFile.getParentFile().mkdirs();
//        file.getImage().transferTo(newFile);
// 
//        ModelAndView mav = new ModelAndView("showUploadedFile");
//        mav.addObject("imageName", newFileName);
//        return mav;
//    }
	
}
