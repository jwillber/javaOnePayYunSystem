<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.eypg.tenpay.ResponseHandler"%>  
<%@ page import="com.eypg.tenpay.util.TenpayUtil"%> 
<%@ include file = "config.jsp" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  
	<%
	//---------------------------------------------------------
	//�Ƹ�֧ͨ��Ӧ�𣨴���ص���ʾ�����̻����մ��ĵ����п�������
	//---------------------------------------------------------
	
	//����֧��Ӧ�����
	ResponseHandler resHandler = new ResponseHandler(request, response);
	resHandler.setKey(key);
	
	System.out.println("ǰ̨�ص����ز���:"+resHandler.getAllParameters());
	
	//�ж�ǩ��
	if(resHandler.isTenpaySign()) {
	
	    //֪ͨid
		String notify_id = resHandler.getParameter("notify_id");
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
		
		if("1".equals(trade_mode)){       //��ʱ���� 
			if( "0".equals(trade_state)){
		        //------------------------------
				//��ʱ���˴���ҵ��ʼ
				//------------------------------
				
	
				//ע�⽻�׵���Ҫ�ظ�����
				//ע���жϷ��ؽ��
				
				//------------------------------
				//��ʱ���˴���ҵ�����
				//------------------------------
				
				out.println("��ʱ���ʸ���ɹ�");
				System.out.println("��ʱ���ʸ���ɹ�");
			}else{
				out.println("��ʱ���ʸ���ʧ��");
				System.out.println("��ʱ���ʸ���ʧ��");
			}
		}else if("2".equals(trade_mode)){    //�н鵣��
			if( "0".equals(trade_state)){
				//------------------------------
				//�н鵣������ҵ��ʼ
				//------------------------------
				
	
				//ע�⽻�׵���Ҫ�ظ�����
				//ע���жϷ��ؽ��
			
				//------------------------------
				//�н鵣������ҵ�����
				//------------------------------
				
				out.println("�н鵣������ɹ�");
				System.out.println("�н鵣������ɹ�");
			}else{
				out.println("trade_state=" + trade_state);
			}
		}
	} else {
		out.println("��֤ǩ��ʧ��");
	}
	
	//��ȡdebug��Ϣ,�����debug��Ϣд����־�����㶨λ����
	String debuginfo = resHandler.getDebugInfo();
	System.out.println("debuginfo:" + debuginfo);
	//out.print("sign_String:  " + debuginfo + "<br><br>");
	
	%>
	






