package com.lzy.tour.model;

import java.io.Serializable;

public class OrderContact implements Serializable{
	private static final long serialVersionUID = 1L;
	/*主键*/
	private Integer id;
	/*订单id*/
    private Integer orderId;
    /*联系人id*/
    private Integer contactId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

	@Override
	public String toString() {
		return "OrderContact [id=" + id + ", orderId=" + orderId
				+ ", contactId=" + contactId + "]";
	}
    
    
    
}