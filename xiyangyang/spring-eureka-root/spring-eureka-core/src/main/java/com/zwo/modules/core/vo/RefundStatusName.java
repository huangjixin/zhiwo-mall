package com.zwo.modules.core.vo;

public class RefundStatusName {
	
	
	/**
	 * 
	 * @Description: 获取当前退款订单的状态
	 * @author xieyc
	 * @date 2018年10月10日 下午12:03:30
	 *
	 */
	public static String getRefundStatusName(int refundStatus) {
		String refundStatusName=null;
		switch (refundStatus) {
		case 1:
			refundStatusName="退款审核中";
			break;
		case 2:
			refundStatusName="退款未通过";
			break;
		case 3:
			refundStatusName="退款未通过";
			break;
		case 4:
			refundStatusName="退款审核中";
			break;
		case 5:
			refundStatusName="退款成功";
			break;
		default:
			refundStatusName="退款异常";
			break;
		}
		return refundStatusName;

	}

}
