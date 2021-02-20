<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.eypg.tenpay.RequestHandler" %>
<%@ page import="com.eypg.tenpay.ResponseHandler" %>   
<%@ page import="com.eypg.tenpay.client.ClientResponseHandler" %>    
<%@ page import="com.eypg.tenpay.client.TenpayHttpClient" %>
<%@ page import="com.eypg.tenpay.util.TenpayUtil"%>
<%@ include file = "config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//---------------------------------------------------------
//�Ƹ�֧ͨ��֪ͨ����̨֪ͨ��ʾ�����̻����մ��ĵ����п�������
//---------------------------------------------------------

//����֧��Ӧ�����
ResponseHandler resHandler = new ResponseHandler(request, response);
resHandler.setKey(key);

System.out.println("��̨�ص����ز���:"+resHandler.getAllParameters());

//�ж�ǩ��
if(resHandler.isTenpaySign()) {
	
	//֪ͨid
	String notify_id = resHandler.getParameter("notify_id");
	
	//�����������
	RequestHandler queryReq = new RequestHandler(null, null);
	//ͨ�Ŷ���
	TenpayHttpClient httpClient = new TenpayHttpClient();
	//Ӧ�����
	ClientResponseHandler queryRes = new ClientResponseHandler();
	
	//ͨ��֪ͨID��ѯ��ȷ��֪ͨ�����Ƹ�ͨ
	queryReq.init();
	queryReq.setKey(key);
	queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
	queryReq.setParameter("partner", partner);
	queryReq.setParameter("notify_id", notify_id);
	
	//ͨ�Ŷ���
	httpClient.setTimeOut(5);
	//������������
	httpClient.setReqContent(queryReq.getRequestURL());
	System.out.println("��֤ID�����ַ���:" + queryReq.getRequestURL());
	
	//��̨����
	if(httpClient.call()) {
		//���ý������
		queryRes.setContent(httpClient.getResContent());
		System.out.println("��֤ID�����ַ���:" + httpClient.getResContent());
		queryRes.setKey(key);
			
			
		//��ȡid��֤����״̬�룬0��ʾ��֪ͨid�ǲƸ�ͨ����
		String retcode = queryRes.getParameter("retcode");
		
		//�̻�������
		String out_trade_no = resHandler.getParameter("out_trade_no");
		//�Ƹ�ͨ������
		String transaction_id = resHandler.getParameter("transaction_id");
		//���,�Է�Ϊ��λ
		String total_fee = resHandler.getParameter("total_fee");
		//�����ʹ���ۿ�ȯ��discount��ֵ��total_fee+discount=ԭ�����total_fee
		String discount = resHandler.getParameter("discount");
		//֧�����
		String trade_state = resHandler.getParameter("trade_state");
		//����ģʽ��1��ʱ���ˣ�2�н鵣��
		String trade_mode = resHandler.getParameter("trade_mode");
			
		//�ж�ǩ�������
		if(queryRes.isTenpaySign()&& "0".equals(retcode)){ 
			System.out.println("id��֤�ɹ�");
			
			if("1".equals(trade_mode)){       //��ʱ���� 
				if( "0".equals(trade_state)){
			        //------------------------------
					//��ʱ���˴���ҵ��ʼ
					//------------------------------
					
					//�������ݿ��߼�
					//ע�⽻�׵���Ҫ�ظ�����
					//ע���жϷ��ؽ��
					
					//------------------------------
					//��ʱ���˴���ҵ�����
					//------------------------------
					
					System.out.println("��ʱ����֧���ɹ�");
					//���Ƹ�ͨϵͳ���ͳɹ���Ϣ���Ƹ�ͨϵͳ�յ��˽�����ٽ��к���֪ͨ
					resHandler.sendToCFT("success");
					
				}else{
					System.out.println("��ʱ����֧��ʧ��");
					resHandler.sendToCFT("fail");
				}
			}else if("2".equals(trade_mode)){    //�н鵣��
				//------------------------------
				//�н鵣������ҵ��ʼ
				//------------------------------
				
				//�������ݿ��߼�
				//ע�⽻�׵���Ҫ�ظ�����
				//ע���жϷ��ؽ��
				
				int iStatus = TenpayUtil.toInt(trade_state);
				switch(iStatus) {
					case 0:		//����ɹ�
					
						break;
					case 1:		//���״���
					
						break;
					case 2:		//�ջ��ַ��д���
					
						break;
					case 4:		//���ҷ����ɹ�
					
						break;
					case 5:		//����ջ�ȷ�ϣ����׳ɹ�
					
						break;
					case 6:		//���׹رգ�δ��ɳ�ʱ�ر�
					
						break;
					case 7:		//�޸Ľ��׼۸�ɹ�
					
						break;
					case 8:		//��ҷ����˿�
					
						break;
					case 9:		//�˿�ɹ�
					
						break;
					case 10:	//�˿�ر�
					
						break;
					default:
				}
				
				//------------------------------
				//�н鵣������ҵ�����
				//------------------------------
				
				System.out.println("trade_state = " + trade_state);
				//���Ƹ�ͨϵͳ���ͳɹ���Ϣ���Ƹ�ͨϵͳ�յ��˽�����ٽ��к���֪ͨ
				resHandler.sendToCFT("success");
			}
		}else{
			//����ʱ�����ؽ��δǩ������¼retcode��retmsg��ʧ�����顣
			System.out.println("��ѯ��֤ǩ��ʧ�ܻ�id��֤ʧ��"+",retcode:" + queryRes.getParameter("retcode"));
		}
	} else {
		System.out.println("��̨����ͨ��ʧ��");
		System.out.println(httpClient.getResponseCode());
		System.out.println(httpClient.getErrInfo());
		//�п�����Ϊ����ԭ�������Ѿ�������δ�յ�Ӧ��
	}
}else{
	System.out.println("֪ͨǩ����֤ʧ��");
}

%>

