package com.test.demo.attachment.dao;

import com.test.demo.attachment.entity.AttachmentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 附件上传持久层
 * 
 * @author Tian yunfeng
 * @date 2018年10月27日
 */
@Repository
public interface AttachmentsDao {
	
	/**
	 * 保存附件数据vo
	 * 
	 * @param voList 数据vo集合
	 */
    void insertAttachments(List<AttachmentVO> voList);
	
	/**
	 * 根据外键、主键将附件数据逻辑删除
	 * 
	 * @param outid 外键
	 */
    void deleteAttachments(@Param("outid") Integer outid, @Param("idList") List<Integer> idList);
	
	/**
	 * 将附件与外键绑定
	 * 
	 * @param outid 外键
	 * @param idList 主键集合
	 */
    void attachmentBinding(@Param("outid") Integer outid, @Param("idList") List<Integer> idList);
	
	/**
	 * 根据外键集合查询附件vo集合
	 * 
	 * @param outidList 外键集合
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @return
	 */
    List<AttachmentVO> queryAttachmentVOsByOutids(List<Integer> outidList, @Param("sourceType") String sourceType);
	
	/**
	 * 根据外键查询附件vo集合
	 * 
	 * @param outid 外键
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @return
	 */
    List<AttachmentVO> queryAttachmentVOsByOutid(@Param("outid") Integer outid, @Param("sourcetype") String sourceType);
}
