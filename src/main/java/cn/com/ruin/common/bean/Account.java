package cn.com.ruin.common.bean;

import java.sql.Timestamp;

public class Account {
	private long accountNo;
	private long userId;
	private int roleId;
	private String loginName;
	private String password;
	private int level;
	private long xp;
	private Timestamp createTime;
	private Timestamp lastUpdateTime;
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getXp() {
		return xp;
	}
	public void setXp(long xp) {
		this.xp = xp;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public String toJSONString() {
		return "{\"accountNo\":\"" + accountNo + "\", userId\":\"" + userId
				+ "\", roleId\":\"" + roleId + "\", loginName\":\"" + loginName
				+ "\", password\":\"" + password + "\", level\":\"" + level
				+ "\", xp\":\"" + xp + "\", createTime\":\"" + createTime
				+ "\", lastUpdateTime\":\"" + lastUpdateTime + "}";
	}
	
}
