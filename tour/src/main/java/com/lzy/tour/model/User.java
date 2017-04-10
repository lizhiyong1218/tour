
/**     
 * @FileName: Media.java   
 * @Package:com.test.entity   
 * @author: ZhiYong.Li    
 * @date:2014年10月13日   
 */

package com.lzy.tour.model;

import java.io.Serializable;
import java.util.Date;

import com.lzy.tour.enums.StatusEnum;

 
/**
 * 
* @ClassName: User
* @Description: 用户 
* @author 李志勇
* @date 2014年11月18日 上午11:54:11
*
 */
public class User implements Serializable{
	private static final long serialVersionUID = 2734339282011057392L;
	
	private Integer id;
	/*微信公众号生成的id*/
	private String openId;
	/*用户名*/
    private String userName;
    /*密码盐*/
    private String salt;
    /*密码*/
    private String pwd;
    /*昵称*/
    private String nickName;
    /*状态*/
    private StatusEnum status;
    /*角色*/
    private String role;
    /*头像*/
    private String picUrl;
    /*性别*/
    private String sex;
    /*联系电话*/
    private String phone;
    /*邮箱*/
    private String email;
    /*注册时间*/
    private Date registerTime;
    /*背景图*/
    private String backgroundUrl;
    /*行业工作*/
    private String work;
    /*兴趣爱好*/
    private String hobby;
    /*精通路线*/
    private String proficientRoute;
    /*去过的地方*/
    private String beanPalace;
    /*个人说明*/
    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getProficientRoute() {
		return proficientRoute;
	}

	public void setProficientRoute(String proficientRoute) {
		this.proficientRoute = proficientRoute;
	}

	public String getBeanPalace() {
		return beanPalace;
	}

	public void setBeanPalace(String beanPalace) {
		this.beanPalace = beanPalace;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", openId=" + openId + ", userName="
				+ userName + ", salt=" + salt + ", pwd=" + pwd + ", nickName="
				+ nickName + ", status=" + status + ", role=" + role
				+ ", picUrl=" + picUrl + ", sex=" + sex + ", phone=" + phone
				+ ", email=" + email + ", registerTime=" + registerTime
				+ ", backgroundUrl=" + backgroundUrl + ", work=" + work
				+ ", hobby=" + hobby + "]";
	}
}
