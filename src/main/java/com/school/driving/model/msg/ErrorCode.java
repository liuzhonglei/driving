/*
 * Copyright @ 2016 com.szdtoo
 * easypro 上午11:47:48
 * All right reserved.
 *
 */
package com.school.driving.model.msg;

/**
 * @desc: 错误码枚举（code<1400为成功；code>=1400为失败）
 * @author: jc
 * @createTime:
 * @version: 1.0
 */
public enum ErrorCode {
	SUCCESS(1200, "操作成功"),
	SUCCESS_QD(1201, "签到成功"),
	SUCCESS_QJ(1202, "请假成功"),
	SUCCESS_LOGGED(1203,"已登录"),
	SUCCESS_CLASS_OPEN(1204,"该班课课表已清除，需重新排课"),
	SUCCESS_CLASS_CLOSE(1205,"关闭班课会清除班课课表"),
	ERROR(1500, "操作失败"),
	ERROR_QD(1501, "签到失败"),
	ERROR_LOGIN(1600, "登录失败"),
	ERROR_TOKEN(1602,"TOKEN失效或者错误"),
	ERROR_ARG(1601, "参数错误"),
	ERROR_AUTH(1606, "认证失败"), 
	ERROR_QUERY(1608, "数据丢失"),
	ERROR_USERNAME(1612, "员工登录名已存在"),
	ERROR_PAY_OVER(1613, "订单已完成或已关闭"),
	ERROR_NOPAY(1614, "订单未支付或已关闭"),
	ERROR_DOMAIN(1615,"学校域名已存在"),
	ERROR_SCHOOLNAME(1616,"学校账号已存在"),
	ERROR_BINDNAME(1818,"学员已经绑定"),
	ERROR_NOORDER(1919,"学员尚未报名"),
	ERROR_FAIL(2100,"结算失败"),
	ERROR_NOSTU(2200,"没有主绑定学员"),
	ERROR_CANNOTQD(2201,"有学员课已上完,不能再签到~"),
	ERROR_QJ(2202,"请假失败~"),
	ERROR_ORDERALLCHANGE(1800,"该学生已换班或换老师，无法签到"),
	ERROR_ORDEROVER(1900,"该订单已消耗完，不能换班！"),
	SAME(1700, "已存在重复数据"),
	ERROR_USERNUM(1703, "人数已达上限，无法录入"),
	ERROR_PASSTIME_VCODE(1900,"验证码已经过期"),
	ERROR_SCHOOLCANNOTDEL(1901,"学校名下有班级或老师或学生,不能删除！"),
	ERROR_VCODE(1902,"验证码不正确"),
	ERROR_PWD(1903,"密码和确认密码不一致"),
	ERROR_ROTATE_LIMIT(1904,"最多只可上传五张！"),
	ERROR_NO_ROLE(1905,"无权限操作！"),
	ERROR_NO_PUSH_STOCK(1906,"补充库存不能小于0！"),
	ERROR_NO_CLASS(1907,"课时不能等于0"),
	ERROR_MORE_THAN_SEVEN(1907,"打印小票不能大于7！"),
	ERROR_NOCOURSE(1909,"无课表可删！"),
	ERROR_MORE_THAN_ONE(1910, "班课类型设置必须至少设置一个类型显示"),
	ERROR_NO_NEWS(2000,"资讯id不存在"),
	ERROR_NO_CLASSES(2001,"学校下无该班课一对多id"),
	ERROR_NO_SPCLASS(2002,"学校下无该专属班课id"),
	ERROR_REPEAT_TYPE(1911, "类型不能有重复！"),
	ERROR_CLASS_NAME(1912, "班课名称不能相同！");

	private final int code;
	private final String msg;

	/**
	 * @param code
	 * @param msg
	 */
	private ErrorCode(final int code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

}
