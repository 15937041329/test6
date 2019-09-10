package com.test.demo.attachment.impl;

import com.aliyuncs.utils.StringUtils;
import com.test.demo.attachment.billtype.SourceType;
import com.test.demo.attachment.dao.AttachmentsDao;
import com.test.demo.attachment.entity.AttachmentVO;
import com.test.demo.attachment.service.AttachmentsService;
import com.test.demo.publicres.exception.BusinessException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 附件业务层接口实现类
 * 
 * @author Tian yunfeng
 * @date 2018年10月26日
 */
@Service(value = "attachmentsService")
public class AttachmentsServiceImpl implements AttachmentsService {

	@Autowired
	private AttachmentsDao dao;

	@Value("${attachmentsUpload.rootDirectory}")
	private String attachmentsUploadRootDirectory; // 附件储存根路径

	@Value("${attachmentsUpload.ipAddress}")
	private String ipAddress; // ip地址

	@Override
	public List<AttachmentVO> upload(List<MultipartFile> attachments, String sourceType, Integer outid)
			throws Exception {
		if (!SourceType.isSourceTypeExist(sourceType)) {
			throw new BusinessException("附件所属类型（sourceType）有误");
		}

		// 检查附件
		checkAttachments(attachments);

		// 附件vo集合
		List<AttachmentVO> voList = new ArrayList<>();

		// 生成上传附件全路径 --- 生成规则： attachmentsSavePath\sourceType\年月日\
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String datePath = sdf.format(new Date());
		String sourcePath = sourceType.toLowerCase() + "/" + datePath + "/";
		String uploadFullPath = attachmentsUploadRootDirectory + sourcePath;

		for (MultipartFile attachment : attachments) {

			// 原始附件名
			String attachmentName = attachment.getOriginalFilename();
			// 构建新的附件名
			String newName = generateNewName(attachmentName);
			// 附件后缀
			String suffixName = attachmentName.substring(attachmentName.lastIndexOf("."));

			// 构建附件vo并放入集合
			AttachmentVO vo = initAttachmentVO(sourceType, sourcePath, attachmentName, newName, suffixName, outid);
			voList.add(vo);

			File dest = new File(uploadFullPath + newName);
			// 若不存在目录则创建目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}

			try {
				attachment.transferTo(dest);
			} catch (Exception e) {
				throw new BusinessException("上传附件异常：" + e.getMessage());
			}
		}

		// 保存附件VO
		if (voList != null && voList.size() > 0) {
			dao.insertAttachments(voList);
		}

		return voList;
	}

	/**
	 * 初始化附件vo
	 * 
	 * @param sourceType     附件所属单据类型
	 * @param sourcePath     资源路径
	 * @param attachmentName 附件原始名称
	 * @param newName        附件新名称
	 * @param suffixName     附件后缀名
	 * @param outid          外键
	 */
	private AttachmentVO initAttachmentVO(String sourceType, String sourcePath, String attachmentName, String newName,
			String suffixName, Integer outid) {

		AttachmentVO vo = new AttachmentVO();
		if (outid != null) {
			vo.setOutid(outid);
		}
		vo.setSourcetype(sourceType);
		vo.setUrl(ipAddress + "/uploadAttachments/" + sourcePath + newName);
		vo.setAttachmenttype(suffixName);
		vo.setName(attachmentName);

		return vo;
	}

	/**
	 * 生成附件名
	 * <p>
	 * 生成规则：时间戳long值 + UUID + 上传的附件名
	 * 
	 * @param attachmentName 附件的原始文件名
	 * @return
	 */
	private String generateNewName(String attachmentName) {
		long timeMillis = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String newName = timeMillis + uuid + attachmentName;
		return newName;
	}

	/**
	 * 校验附件是否非空及类型是否合法
	 * 
	 * @param attachments 附件集合
	 * @throws BusinessException
	 */
	private void checkAttachments(List<MultipartFile> attachments) throws BusinessException {

		if (attachments == null || attachments.isEmpty()) {
			throw new BusinessException("上传附件为空");
		}

		Long size = 0L;

		for (MultipartFile attachment : attachments) {
			if (attachment.isEmpty()) {
				throw new BusinessException("上传附件为空");
			}

			Long attachmentSize = attachment.getSize();
			if (attachmentSize > 1048576) {
				throw new BusinessException("单个附件超过1MB");
			} else {
				size += attachmentSize;
			}

			String attachmentName = attachment.getOriginalFilename();

			if (StringUtils.isEmpty(attachmentName)) {
				throw new BusinessException("附件名为空");
			}

			// 获取附件名后缀
			String suffixName = attachmentName.substring(attachmentName.lastIndexOf("."));

			List<String> allowedFileTypeList = new ArrayList<>();

			allowedFileTypeList.add("1.Word类型：");
			allowedFileTypeList.add(".doc");
			allowedFileTypeList.add(".docx");

			// Excel
			allowedFileTypeList.add("2.Excel类型：");
			allowedFileTypeList.add(".xlsx");
			allowedFileTypeList.add(".xls");

			// 文本文件
			allowedFileTypeList.add("3.文本类型：");
			allowedFileTypeList.add(".txt");

			// 图片
			allowedFileTypeList.add("4.图片类型：");
			allowedFileTypeList.add(".png");
			allowedFileTypeList.add(".jpg");
			allowedFileTypeList.add(".jpeg");
			allowedFileTypeList.add(".webp");
			allowedFileTypeList.add(".bmp");
			allowedFileTypeList.add(".tif");
			allowedFileTypeList.add(".gif");

			if (StringUtils.isEmpty(suffixName)) {
				throw new BusinessException("无附件后缀名");
			}

			if (!allowedFileTypeList.contains(suffixName)) {
				String errmsg = "附件类型有误，仅支持：1.Word类型：doc、docx 2.Excel类型：xlsx、xls 3.文本类型：txt 4.图片类型：png、jpg";
				throw new BusinessException(errmsg);
			}
		}

		if (size > 5242880) {
			throw new BusinessException("附件超过5MB");
		}
	}

	@Override
	public void attachmentBinding(Integer outid, List<Integer> idList, String sourceType) throws Exception {

		if (outid == null || idList == null || idList.isEmpty()) {
			throw new BusinessException("请求参数为空");
		}

		if (!SourceType.isSourceTypeExist(sourceType)) {
			throw new BusinessException("附件类型无效");
		}

		List<AttachmentVO> voList = queryAttachmentVOsByOutid(outid, sourceType);
		if (voList != null && voList.size() > 0) {

			List<Integer> delIdList = new ArrayList<>();

			for (AttachmentVO vo : voList) {
				Integer id = vo.getId();

				if (!idList.contains(id)) {
					delIdList.add(id);
				}
			}

			if (delIdList != null && delIdList.size() > 0) {
				// 将不要的附件数据删除
				dao.deleteAttachments(outid, delIdList);
			}
		}

		// 绑定新的附件数据
		dao.attachmentBinding(outid, idList);
	}

	@Override
	public List<AttachmentVO> queryAttachmentVOsByOutids(List<Integer> outidList, String sourceType) throws Exception {

		if (outidList == null || outidList.isEmpty()) {
			throw new BusinessException("参数为空");
		}

		if (StringUtils.isEmpty(sourceType)) {
			throw new BusinessException("附件类型为空");
		} else if (!SourceType.isSourceTypeExist(sourceType)) {
			throw new BusinessException("附件类型无效");
		}

		List<AttachmentVO> voList = dao.queryAttachmentVOsByOutids(outidList, sourceType);

		return voList;
	}

	@Override
	public List<AttachmentVO> queryAttachmentVOsByOutid(Integer outid, String sourceType) throws Exception {

		if (outid == null) {
			throw new BusinessException("参数为空");
		}

		if (StringUtils.isEmpty(sourceType)) {
			throw new BusinessException("附件类型为空");
		} else if (!SourceType.isSourceTypeExist(sourceType)) {
			throw new BusinessException("附件类型无效");
		}

		List<AttachmentVO> voList = dao.queryAttachmentVOsByOutid(outid, sourceType);
		return voList;
	}

	/**
	 * 插入一个附件vo
	 * @return
	 * @throws Exception
	 * @author zhu yuanhong
	 * @date 2018年12月11日
	 */
	@Override
    public List<AttachmentVO> insertAttachmentVO(String attachmentName, String sourceType, XSSFWorkbook wb) throws Exception {
		// 生成上传附件全路径 --- 生成规则： attachmentsSavePath\sourceType\年月日\
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String datePath = sdf.format(new Date());
		String sourcePath = sourceType.toLowerCase() + "/" + datePath + "/";
		String uploadFullPath = attachmentsUploadRootDirectory + sourcePath;
		String newName = this.generateNewName(attachmentName);
		AttachmentVO vo = initAttachmentVO(sourceType, sourcePath, attachmentName, newName, ".xslx", null);
		File file = new File(uploadFullPath + newName);
		// 若不存在目录则创建目录
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 将生成的Excel文件保存到本地
		FileOutputStream out = new FileOutputStream(file);
		// 将工作薄写入文件输出流中
		wb.write(out);
		List<AttachmentVO> voList = new ArrayList<>();
		voList.add(vo);
		dao.insertAttachments(voList);
		return voList;
	}

}
