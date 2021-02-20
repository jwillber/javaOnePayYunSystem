<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.eypg.tenpay.util.TenpayUtil" %>
<%@ page import="com.eypg.tenpay.RequestHandler"%>
<%@ include file = "config.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//---------------------------------------------------------
//�Ƹ�ͨ����֧������ʾ�����̻����մ��ĵ����п�������
//---------------------------------------------------------


//---------------���ɶ����� ��ʼ------------------------
//��ǰʱ�� yyyyMMddHHmmss
String currTime = TenpayUtil.getCurrTime();
//8λ����
String strTime = currTime.substring(8, currTime.length());
//��λ�����
String strRandom = TenpayUtil.buildRandom(4) + "";
//10λ���к�,�������е�����
String strReq = strTime + strRandom;
//�����ţ��˴���ʱ�����������ɣ��̻������Լ����������ֻҪ����ȫ��Ψһ����
String out_trade_no = strReq;
//---------------���ɶ����� ����------------------------


//����֧���������
RequestHandler reqHandler = new RequestHandler(request, response);
reqHandler.init();
//������Կ
reqHandler.setKey(key);
//����֧������
reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");


//-----------------------------
//����֧������
//-----------------------------
reqHandler.setParameter("partner", partner);		        //�̻���
reqHandler.setParameter("out_trade_no", out_trade_no);		//�̼Ҷ�����
reqHandler.setParameter("total_fee", "1");			        //��Ʒ���,�Է�Ϊ��λ
reqHandler.setParameter("return_url", return_url);		    //������ɺ���ת��URL
reqHandler.setParameter("notify_url", notify_url);		    //���ղƸ�֪ͨͨ��URL
reqHandler.setParameter("body", "����");	                    //��Ʒ����
reqHandler.setParameter("bank_type", "DEFAULT");		    //��������(�н鵣��ʱ�˲�����Ч)
reqHandler.setParameter("spbill_create_ip",request.getRemoteAddr());   //�û��Ĺ���ip�������̻�������IP
reqHandler.setParameter("fee_type", "1");                    //���֣�1�����
reqHandler.setParameter("subject", "��Ʒ����");              //��Ʒ����(�н齻��ʱ����)

//ϵͳ��ѡ����
reqHandler.setParameter("sign_type", "MD5");                //ǩ������,Ĭ�ϣ�MD5
reqHandler.setParameter("service_version", "1.0");			//�汾�ţ�Ĭ��Ϊ1.0
reqHandler.setParameter("input_charset", "GBK");            //�ַ�����
reqHandler.setParameter("sign_key_index", "1");             //��Կ���


//ҵ���ѡ����
reqHandler.setParameter("attach", "");                      //�������ݣ�ԭ������
reqHandler.setParameter("product_fee", "");                 //��Ʒ���ã����뱣֤transport_fee + product_fee=total_fee
reqHandler.setParameter("transport_fee", "0");               //�������ã����뱣֤transport_fee + product_fee=total_fee
reqHandler.setParameter("time_start", currTime);            //��������ʱ�䣬��ʽΪyyyymmddhhmmss
reqHandler.setParameter("time_expire", "");                 //����ʧЧʱ�䣬��ʽΪyyyymmddhhmmss
reqHandler.setParameter("buyer_id", "");                    //�򷽲Ƹ�ͨ�˺�
reqHandler.setParameter("goods_tag", "");                   //��Ʒ���
reqHandler.setParameter("trade_mode", "1");                 //����ģʽ��1��ʱ����(Ĭ��)��2�н鵣����3��̨ѡ����ҽ�֧�������б�ѡ��
reqHandler.setParameter("transport_desc", "");              //����˵��
reqHandler.setParameter("trans_type", "1");                  //�������ͣ�1ʵ�ｻ�ף�2���⽻��
reqHandler.setParameter("agentid", "");                     //ƽ̨ID
reqHandler.setParameter("agent_type", "");                  //����ģʽ��0�޴���(Ĭ��)��1��ʾ������ģʽ��2��ʾ����ģʽ
reqHandler.setParameter("seller_id", "");                   //�����̻��ţ�Ϊ�����ͬ��partner

//�����url
String requestUrl = reqHandler.getRequestURL();

//��ȡdebug��Ϣ,����������debug��Ϣд����־�����㶨λ����
String debuginfo = reqHandler.getDebugInfo();
//System.out.println("requestUrl:  " + requestUrl);
//System.out.println("sign_String:  " + debuginfo);

out.print("requestUrl:  " + requestUrl + "<br><br>");
out.print("sign_String:  " + debuginfo + "<br><br>");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�Ƹ�ͨ��ʱ���ʳ�����ʾ</title>
</head>
<body>
<br/><a target="_blank" href="<%=requestUrl %>">�Ƹ�֧ͨ��</a>
</body>
</html>
