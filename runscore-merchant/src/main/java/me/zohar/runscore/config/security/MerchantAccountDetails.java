package me.zohar.runscore.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import me.zohar.runscore.merchant.vo.MerchantVO;
import me.zohar.runscore.useraccount.vo.LoginAccountInfoVO;

public class MerchantAccountDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String userName;

	private String loginPwd;

	private String merchantId;

	private String merchantName;

	private String merchantNum;

	public MerchantAccountDetails(LoginAccountInfoVO loginAccountInfo, MerchantVO merchant) {
		if (loginAccountInfo != null) {
			this.id = loginAccountInfo.getId();
			this.userName = loginAccountInfo.getUserName();
			this.loginPwd = loginAccountInfo.getLoginPwd();
		}
		if (merchant != null) {
			this.merchantId = merchant.getId();
			this.merchantName = merchant.getName();
			this.merchantNum = merchant.getMerchantNum();
		}
	}

	/**
	 * 获取登陆用户账号id
	 * 
	 * @return
	 */
	public String getUserAccountId() {
		return this.id;
	}

	/**
	 * 获取登陆用户账号商户id
	 * 
	 * @return
	 */
	public String getMerchantId() {
		return this.merchantId;
	}

	/**
	 * 获取登陆用户账号商户名
	 * 
	 * @return
	 */
	public String getMerchantName() {
		return this.merchantName;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.loginPwd;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
