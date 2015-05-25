package com.projectx.mvc.domain.completeregister;

public class CommodityDTO {
	
	private Long commodityId;
	
	private String commodityName;
	
	private Integer requestedBy;
	
	private Long requestedById;

	
	
	
	public CommodityDTO() {
		super();
	}




	public CommodityDTO(Long commodityId, String commodityName,
			Integer requestedBy, Long requestedById) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}




	public Long getCommodityId() {
		return commodityId;
	}




	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}




	public String getCommodityName() {
		return commodityName;
	}




	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}




	public Integer getRequestedBy() {
		return requestedBy;
	}




	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}




	public Long getRequestedById() {
		return requestedById;
	}




	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}




	@Override
	public String toString() {
		return "CommodityDTO [commodityId=" + commodityId + ", commodityName="
				+ commodityName + ", requestedBy=" + requestedBy
				+ ", requestedById=" + requestedById + "]";
	}
	
	
	

}
