package com.test.demo.attachment.service;

import com.test.demo.attachment.entity.AttachmentVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 附件业务层接口
 * 
 * @author Tian yunfeng
 * @date 2018年10月26日
 */
public interface AttachmentsService {
	
	/**
	 * 批量上传文件
	 * 
	 * @param files 文件集合
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @outid outid 外键
	 * @throws Exception
	 */
    List<AttachmentVO> upload(List<MultipartFile> files, String sourceType, Integer outid) throws Exception;
	
	/**
	 * 将附件与外键绑定
	 * 
	 * @param outid 附件所属外键
	 * @param idList 附件主键集合
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @throws Exception
	 */
    void attachmentBinding(Integer outid, List<Integer> idList, String sourceType)  throws Exception;
	
	/**
	 * 根据外键集合查询附件vo集合
	 * 
	 * @param outidList 外键集合
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @return
	 * @throws Exception
	 */
    List<AttachmentVO> queryAttachmentVOsByOutids(List<Integer> outidList, String sourceType) throws Exception;
	
	/**
	 * 根据外查询附件vo集合
	 * 
	 * @param outid 外键
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @return
	 * @throws Exception
	 */
    List<AttachmentVO> queryAttachmentVOsByOutid(Integer outid, String sourceType) throws Exception;
	
	/**
	 * 插入一个附件vo
	 * @param attachmentName 文件名
	 * @param sourceType 文件所属单据类型
	 * @param wb java excel XSSFWorkbook流
	 * @return
	 * @throws Exception
	 * @author zhu yuanhong 
	 * @date 2018年12月11日
	 */
    List<AttachmentVO> insertAttachmentVO(String attachmentName, String sourceType, XSSFWorkbook wb)throws Exception;
	
}
