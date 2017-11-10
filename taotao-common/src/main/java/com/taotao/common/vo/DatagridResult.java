package com.taotao.common.vo;

import java.io.Serializable;
import java.util.List;

public class DatagridResult implements Serializable {

	private Long total;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	private List<?> rows;
	
}
