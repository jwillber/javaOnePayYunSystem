package com.eypg.util;
import java.util.List;

import com.shcm.bean.BalanceResultBean;
import com.shcm.bean.ReplyBean;
import com.shcm.bean.SendResultBean;
import com.shcm.bean.SendStateBean;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;

/**
 * @author Chinafish
 *
 */
public class Sampler
{
	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";
	
	// 接口帐号
	private static final String account = ApplicationListenerImpl.sysConfigureJson.getMessagePartner();
	// 接口密钥
	private static final String authkey = ApplicationListenerImpl.sysConfigureJson.getMessageKey();
	// 通道组编号
	private static final int cgid = Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageChannel());
	// 默认使用的签名编号(未指定签名编号时传此值到服务器)
	private static final int csid = Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageSign());
	
	public static List<SendResultBean> sendOnce(String mobile, String content) throws Exception
	{
		OpenApi.initialzeAccount(sOpenUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey(), Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageChannel()), Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageSign()));
		// 状态及回复参数
		DataApi.initialzeAccount(sDataUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey());
		
		// 发送短信
//		return OpenApi.sendOnce(mobile, content, 0, 0, null);
		return OpenApi.sendOnce(new String[]{mobile}, content, Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageChannel()), Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageSign()), null);
//		return OpenApi.sendOnce(new String[]{"15201558450","15102110086"}, "测试内容", 0, 0, null);
		//return OpenApi.sendBatch("18297974783,15102110086", "测试内容{|}测试内容", 0, 0, null);
		//return OpenApi.sendBatch(new String[]{"18297974783","15102110086"}, new String[]{"测试内容","测试内容"}, 0, 0, null);
		//return OpenApi.sendParam("18297974783,15102110086", "测试内容{p1}", new String[]{"a{|}b"}, 0, 0, null);
		//return OpenApi.sendParam(new String[]{"18297974783","15102110086"}, "测试内容{p1}", new String[]{"a{|}b"}, 0, 0, null);
	}
	
	public static String getMesBalance(){
		String balance = "";
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey(), Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageChannel()), Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageSign()));
		// 状态及回复参数
		DataApi.initialzeAccount(sDataUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey());
		// 取帐户余额
		BalanceResultBean br = OpenApi.getBalance();
		if(br.getResult() < 1){
			balance = "获取可用余额失败"+br.getErrMsg();
//			System.out.println("获取可用余额失败: " + br.getErrMsg());
		}else{
			balance = "可用条数: " + br.getRemain();
//			System.out.println("可用条数: " + br.getRemain());
		}
		
		return balance;
	}
	
	public static void main(String[] args) throws Exception
	{
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, "1001@500987080001", "EF8289967F47931C0B3A299D2BF7708F", 2186, 1983);
		// 状态及回复参数
		DataApi.initialzeAccount(sDataUrl, "1001@500987080001", "EF8289967F47931C0B3A299D2BF7708F");
		// 取帐户余额
		BalanceResultBean br = OpenApi.getBalance();
		if(br.getResult() < 1)
		{
			System.out.println("获取可用余额失败: " + br.getErrMsg());
			return;
		}
		System.out.println("可用条数: " + br.getRemain());
//		
//		// 更新接口密钥
//		//String sAuthKey = OpenApi.updateKey();
//		//if(!sAuthKey.isEmpty())
//		//{
//		//	System.out.println("已成功更新密钥,新接口密钥为: " + sAuthKey);
//		//}
//		
//		List<SendResultBean> listItem = sendOnce("18201565794,15201558450", "Java接口测试内容");
//		if(listItem != null)
//		{
//			for(SendResultBean t:listItem)
//			{
//				if(t.getResult() < 1)
//				{
//					System.out.println("发送提交失败: " + t.getErrMsg());
//					return;
//				}
//				System.out.println("发送成功: 消息编号<" + t.getMsgId() + "> 总数<" + t.getTotal() + "> 余额<" + t.getRemain() + ">");
//			}
//		}
//		
//		// 循环获取状态报告和回复
//		while(true)
//		{
//			List<SendStateBean> listSendState = DataApi.getSendState();
//			if(listSendState != null)
//			{
//				for(SendStateBean t:listSendState)
//				{
//					System.out.println("状态报告 => 序号<" + t.getId() + "> 消息编号<" + t.getMsgId() + "> 手机号码<" + t.getMobile() + "> 结果<" + (t.getStatus() > 1 ? "发送成功" : t.getStatus() > 0 ? "发送失败" : "未知状态") + "> 运营商返回<" + t.getDetail() + ">");
//				}
//			}
//			
//			// 取回复
//			List<ReplyBean> listReply = DataApi.getReply();
//			if(listReply != null)
//			{
//				for(ReplyBean t:listReply)
//				{
//					System.out.println("回复信息 => 序号<" + t.getId() + "> 回复时间<" + t.getReplyTime() + "> 手机号码<" + t.getMobile() + "> 回复内容<" + t.getContent() + ">");
//				}
//			}
//			Thread.sleep(6000);
//		}
	}
}
