package com.lzy.tour.model;

import java.io.Serializable;

public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	/**/
    private Integer id;
    /*用户id*/
    private Integer userId;
    /*名称*/
    private String nickName;
    /*电话*/
    private String phone;
    /*身份证*/
    private String idCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

	@Override
	public String toString() {
		return "Contact [id=" + id + ", userId=" + userId + ", nickName="
				+ nickName + ", phone=" + phone + ", idCard=" + idCard + "]";
	}
}