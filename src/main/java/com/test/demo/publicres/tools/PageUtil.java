package com.test.demo.publicres.tools;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页工具类
 * 
 * @author Tian yunfeng
 * @date 2018年9月24日
 */
public class PageUtil {
	
	/**
	 * 获取分页后的总页数
	 */
	public static <T> String getPageAmount(List<T> voList) {
		PageInfo<T> pageInfo = new PageInfo<>(voList);
		int pageAmount = pageInfo.getPages();
		
		return String.valueOf(pageAmount);
	}
}
