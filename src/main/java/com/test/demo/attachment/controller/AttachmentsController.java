package com.test.demo.attachment.controller;

import com.test.demo.attachment.entity.AttachmentVO;
import com.test.demo.attachment.service.AttachmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 附件控制类
 * 
 * @author Tian yunfeng
 * @date 2018年10月26日
 */

@Api(tags="attachments接口")
@RestController
@RequestMapping("/rs/attachmentsUpload")
public class AttachmentsController {
	private Logger log = LoggerFactory.getLogger(AttachmentsController.class);
	@Autowired
	private AttachmentsService service;
	
	/**
	 * 附件批量上传
	 * 
	 * @param files 附件
	 * @param sourceType 附件所属单据类型 {@link com.test.demo.attachment.billtype.SourceType}
	 * @param outid 附件所属外键
	 * @return
	 */
	@ApiOperation(value="上传附件", notes = "sourceType固定值：USER_WECHATQRCODE|顾问微信二维码，COUPON_IMG|优惠券")
	@PostMapping("/upload")
	public Map<String, Object> upload (@RequestParam("files") List<MultipartFile> files,
									   @RequestParam("sourceType") String sourceType,
									   @RequestParam(value = "outid", required = false) Integer outid) {
		log.info("=====================================>upload welcome");
		Map<String, Object> returnMap = new HashMap<>();
		try {
			List<AttachmentVO> voList = service.upload(files, sourceType, outid);
			returnMap.put("resultCode", 200);
			returnMap.put("data", voList);
		} catch (Exception e) {
			log.error("附件批量上传异常：", e);
			returnMap.put("resultCode", 999);
			returnMap.put("message", "附件批量上传异常："+e.getMessage());
		}
		return returnMap;
	}
	
	/**
	 * 将附件与外键绑定
	 * 
	 * @param outid 附件所属外键
	 * @param idList 附件主键集合
	 * @return
	 */
	@ApiOperation(value="将附件与外键绑定",notes="sourceType固定值：USER_WECHATQRCODE|微信二维码，USER_HEAD_IMG|用户头像")
	@PostMapping(value="/binding")
	public Map<String, Object> attachmentBinding (@RequestParam("outid") Integer outid,
                                                  @RequestParam("idList") List<Integer> idList,
                                                  @RequestParam("sourceType") String sourceType) {
		
		Map<String, Object> returnMap = new HashMap<>();
		try {
			service.attachmentBinding(outid, idList, sourceType);
			returnMap.put("resultCode", 200);
		} catch (Exception e) {
			log.error("将附件与外键绑定：", e);
			returnMap.put("resultCode", 999);
			returnMap.put("message", "将附件与外键绑定："+e.getMessage());
		}
		return returnMap;
	}
}


















