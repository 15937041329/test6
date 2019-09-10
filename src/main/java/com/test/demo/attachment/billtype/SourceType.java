package com.test.demo.attachment.billtype;

import com.test.demo.publicres.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ban shifeng
 * @date 2019-1-21
 */
public class SourceType {
	private static List<String> list;
	public static final String HEAD_IMG = "HEAD_IMG";				//头像
	public static Boolean isSourceTypeExist (String sourceType) throws BusinessException {
		if (list == null || list.isEmpty()) {
			list = new ArrayList<>();
			list.add(HEAD_IMG);
		}
        return list.contains(sourceType);
    }
}
