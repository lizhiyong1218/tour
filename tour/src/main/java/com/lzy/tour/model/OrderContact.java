package com.lzy.tour.model;

import java.io.Serializable;

public class OrderContact implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer orderId;

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