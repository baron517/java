package com.minsheng.common;

import java.util.List;

/**
 * @author malongbo
 * @date 2015/1/17
 * @package com.pet.project.bean
 */
public class DataResponse{
	
    private Integer code;
	    
    private String msg;
	
    private List<?> data;

	public DataResponse(Integer code2, String msg2, List<?> data2) {
		
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

    
}
