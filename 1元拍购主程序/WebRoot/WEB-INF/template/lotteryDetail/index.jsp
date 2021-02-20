<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${latestlottery.productName }${latestlottery.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/lotterydetail.css" />
  </head>
<body id="loadingPicBlock" rf="2">
<input name="hidGoodsID" type="hidden" id="hidGoodsID" value="${latestlottery.spellbuyProductId }" />
<input name="hidCodeID" type="hidden" id="hidCodeID" value="${latestlottery.spellbuyProductId }" />
<input name="hidLogined" type="hidden" id="hidLogined" value="0" />
<input name="hidIsEnd" type="hidden" id="hidIsEnd" value="0" />
<input name="hidIsStart" type="hidden" id="hidIsStart" value="1" />
<input type="hidden" id="productId" value="${latestlottery.productId }"/>
<input type="hidden" id="resultCount" value="${resultCount }"/>
<input type="hidden" id="pageCount" value="${pageCount }"/>
 <div class="g-width Current_nav">
    <a href="/">首页</a> <span>&gt;</span>  揭晓详情  </div>
   <!-- 商品图片 -->
<div class="show_content">
        <!-- 商品期数 -->
	    <c:choose>
	    	<c:when test="${fn:length(productPeriodList)>27}">
			    <div class="show_Period" id="divPeriodList" style="height: 99px;">
	    		<div class="period_Open"><a href="javascript:;" id="btnOpenPeriod" class="gray02">展开<i></i></a></div>
	    	</c:when>
	    	<c:otherwise>
	    		<div class="show_Period" id="divPeriodList">
	    	</c:otherwise>
	    </c:choose>
		<ul class="Period_list">
	    <c:forEach items="${productPeriodList}" var="entry" varStatus="i">
	    	<c:choose>
	    		<c:when test="${entry.key==latestlottery.productPeriod}">
		    		<li><b class="period_ArrowCur">第${latestlottery.productPeriod }期</b></li>
	    		</c:when>
	    		<c:otherwise>
	    			<c:choose>
			    		<c:when test="${i.index==0 }">
					    	<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html"><b class="period_Ongoing">第${entry.key }期<i></i></b></a></li>
			    		</c:when>
			    		<c:otherwise>
			    			<li><a class="gray02" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${entry.value}.html">第${entry.key }期</a></li>
			    		</c:otherwise>
			    	</c:choose>
	    		</c:otherwise>
	    	</c:choose>
    		<c:if test="${(i.index+1)%10==0 }">
		    	</ul>
		    	<ul class="Period_list">
    		</c:if>
		</c:forEach> 
	    </div>
	    <!-- 商品信息 -->
	<div class="Pro_Details">
			<h1><span>(第${latestlottery.productPeriod }期)</span>${latestlottery.productName } </h1>
			<div class="Pro_Detleft">
				<c:choose>
					<c:when test="${productJSON!=null}">
						<div class="Pro_Detimg">
							<div class="Pro_pic"><a title="${latestlottery.productName  }" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productJSON.productId }.html">
							<c:if test="${fn:length(productimageList)!=0}">
		                    	<s:iterator value="productimageList" var="productimages" begin="0" end="0">
		                    		<img  width="398" height="398" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }"/>
		                    	</s:iterator>
		                   	</c:if>
							</a>
							<c:if test="${latestlottery.announcedType==1}">
								<span>限时揭晓</span>
							</c:if>
							</div>
						</div>
						<div class="Result_LConduct">
							<dl><dt><span>第${productJSON.productPeriod }期</span>正在进行</dt><dd><div class="Result_Progress-bar"><p title="已完成${fn:replace( fn:substring((productJSON.currentBuyCount/productJSON.productPrice)*100, 0, 2),'.','' )}%"><span style="width:${fn:replace( fn:substring((productJSON.currentBuyCount/productJSON.productPrice)*100, 0, 2),'.','' )}%;"></span></p><ul class="Pro-bar-li"><li class="P-bar01"><em>${productJSON.currentBuyCount }</em>已参与人次</li><li class="P-bar02"><em>${productJSON.productPrice }</em>总需人次</li><li class="P-bar03"><em>${productJSON.productPrice -  productJSON.currentBuyCount}</em>剩余人次</li></ul></div><p><a class="Result_LConductBut" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productJSON.productId }.html">查看详情</a></p></dd></dl>
						</div>
					</c:when>
					<c:otherwise>
						<div class="Pro_Detimg">
							<div class="Pro_pic"><a title="${latestlottery.productName  }" >
							<c:if test="${fn:length(productimageList)!=0}">
		                    	<s:iterator value="productimageList" var="productimages" begin="0" end="0">
		                    		<img  width="398" height="398" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }"/>
		                    	</s:iterator>
		                   	</c:if>
							</a>
							<c:if test="${latestlottery.announcedType==1}">
								<span>限时揭晓</span>
							</c:if>
							</div>
						</div>
						<div class="Result_LConduct">
							<dl><dt style="width: 398px; font-size: 18px;">该商品已经下架</dt></dl>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="Pro_Detright">
				<p class="Det_money">价值：<span class="rmbgray">${latestlottery.productPrice }.00</span></p>
<!--		        <div class="Progress-bar" id="divProcess">-->
<!--			        <p title="已完成100.00%"><span style="width:100%;"></span></p>-->
<!--			        <ul class="Pro-bar-li">-->
<!--				        <li class="P-bar01"><em>${latestlottery.productPrice }</em>已参与人次</li>-->
<!--				        <li class="P-bar02"><em id="CodeQuantity">${latestlottery.productPrice }</em>总需人次</li>-->
<!--				        <li class="P-bar03"><em id="CodeLift">${latestlottery.productPrice - latestlottery.productPrice }</em>剩余人次</li>-->
<!--			        </ul>-->
<!--		        </div>-->
				<div class="Announced_Frame">
					<div class="Announced_FrameT">揭晓结果</div>
					<div class="Announced_FrameCode">
						<ul class="Announced_FrameCodeMal">
						    ${winNumber }
						</ul>
					</div>
					<div class="Announced_FrameGet">
						<dl>
							<dt><a title="${latestlottery.userName }" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlottery.userId }.html" rel="nofollow"><img width="60" height="60" src="${latestlottery.userFace }" /></a></dt>
							<dd class="gray02">
								<p>恭喜<a title="${latestlottery.userName }" class="blue" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlottery.userId }.html">${latestlottery.userName }</a>(${latestlottery.location })获得</p>
								<p>揭晓时间：${latestlottery.announcedTime }</p>
								<p><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间：${latestlottery.buyTime }</p>
							</dd>
						</dl>
					</div>
					<div class="Announced_FrameBut">
						<a class="Announced_But" href="javascript:;">本期详细计算结果</a>
						<a class="Announced_But" href="javascript:;">看看有谁参与了</a>
						<a class="Announced_But" href="javascript:;">看看有谁晒单</a>
					</div>
					<div class="Announced_FrameBm"></div>
				</div>
				<div class="MaCenter">
					<p>商品获得者本期总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>了<em class="orange">${buyerCount }</em>人次</p>
					<div class="MaCenterH" id="userRnoId">
					<s:iterator value="randomNumberJSONList" var="randomNumberJSONS">
						<dl><dt>${randomNumberJSONS.buyDate }</dt>
						<dd>
							${randomNumberJSONS.randomNumbers }
						</dd></dl>
					</s:iterator>
					</div>
				</div>
				<div id="divOpen" class="MaOff" style="display: block;"><span>展开查看全部 <s></s></span></div>
			</div>
		</div>
		</div>
    <!-- 商品图片结束 -->
    <div class="ProductTabNav">
	    <div class="DetailsT_Tit" id="divProductNav">
	        <div class="DetailsT_TitP">
		        <ul>
			        <li class="Product_DetT DetailsTCur"><span class="DetailsTCur">计算结果</span></li>
			        <li class="All_RecordT" id="liUserBuyAll"><span class="">所有参与记录</span></li>
			        <li class="Single_ConT"><span class="">晒单</span></li>
		        </ul>
		    </div>
	    </div>
	</div>
	<!-- 商品详细介绍 -->
	<div class="Product_Content" id="divContent" style="display: block;">
		<div style="" class="ygRecord m-detail-main-rule">
			<p style="color: #999;font-family: 'Microsoft Yahei';font-size: 24px;font-weight: bold;line-height: 34px;padding: 15px;">我们有严谨的幸运号码计算规则，保证公平公正公开 </p>
			<c:if test="${latestlottery.sscNumber==null }">
				<p style="color: red;font-family: 'Microsoft Yahei';font-size: 24px;font-weight: bold;line-height: 34px;padding: 15px;">【该商品开奖日期在时时彩开奖规则之前，请以开奖号码为准】</p>
			</c:if>
			<ul class="txt">
                <li>
                    <span class="title">计算公式</span>
                    <div class="formula">
                        <div class="box red-box"><b class="txt-red">${latestlottery.randomNumber}</b><br /><b class="txt-red">本期幸运号码</b></div><div class="operator">=(</div><div class="box gray-box"><b class="txt-red">${DateSUM}</b><br />
                        50个时间求和
                        </div><div title="相加" class="operator">+</div><div class="box gray-box"><b class="txt-red">${latestlottery.sscNumber }</b><br />“时时彩”开奖号码
                        </div><div title="取余" class="operator">)%</div><div class="box"><b class="txt-red">${latestlottery.productPrice }</b><br />该奖品总需人次</div><div title="相加" class="operator">+</div><div class="box"><b class="txt-red">10000001</b><br />原始数</div>
                    </div>
                </li>
            </ul>
            <div class="yghelp">
                1、取该商品最后购买时间前网站所有商品的最后50条购买时间记录
                <br/>
                2、每个时间记录按时、分、秒、毫秒依次排列取数值，如20:15:25.362则为201525362）将这50个时间的数值进行求和（得出数值A）
                <br/>
                3、为保证公平公正公开，系统还会等待一小段时间，取最近下一期中国福利彩票“时时彩”的开奖结果（一个五位数值B）；
                <br/>
                4、（数值A+数值B）除以该商品总参与人次后取余数 + 原始数 10000001，得到最终幸运号码，拥有该幸运号码者，直接获得该商品。
                <br />
                	注：最后一个号码分配时间距离“时时彩”最近下一期开奖大于24小时，默认“时时彩”开奖结果为最后一期开奖号码。
            </div>
            <ul class="Record_title">
                <li class="time"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间</li>
                <li class="nem">会员账号</li>
                <li class="name">商品名称</li>
                <li class="much"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</li>
            </ul>
		 <div class="RecordOnehundred">
		 <h4>截止该商品最后购买时间【${startDate }】最后50条全站购买时间记录</h4>
		 <div class="FloatBox"></div>
		 <s:iterator value="lotteryDetailJSONList" var="lotteryDetailJSONs">
			 <ul class="Record_content"><li class="time"><b>${lotteryDetailJSONs.buyDate }</b>${lotteryDetailJSONs.buyTime }</li><li class="timeVal">${lotteryDetailJSONs.dateSum }</li><li class="nem"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${lotteryDetailJSONs.userId }.html" target="_blank" class="gray02">${lotteryDetailJSONs.userName }</a></li>
			 <li class="name"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${lotteryDetailJSONs.productId }.html" class="gray02">（第${lotteryDetailJSONs.productPeriod }期）${lotteryDetailJSONs.productName }</a></li><li class="much">${lotteryDetailJSONs.buyCount }人次</li></ul>
		 </s:iterator>
		 <div class="ResultBox"><h2>计算结果</h2><p class="num4">求和：<span class="Fb">${DateSUM}</span> (上面50条<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录时间取值相加之和)<br />
		 第 <span class="Fb">${latestlottery.sscPeriod}</span>  期 “时时彩”开奖号码：<span class="Fb"><em>${latestlottery.sscNumber }</em></span> <a href="http://caipiao.163.com/award/cqssc/" target="_blank">开奖查询>></a><br />
		 取余：<span class="Fb">(${DateSUM} + ${latestlottery.sscNumber}) % ${latestlottery.productPrice }</span> (本商品总需参与人次) = <span class="Fb">${latestlottery.randomNumber-10000001}</span> <br/>
		 结果：<span class="Fb">${latestlottery.randomNumber-10000001}</span> (余数) + <span class="Fb">10000001</span> = <span class="Fb"><em>${latestlottery.randomNumber}</em></span><br />
<!--		 取余：<span class="Fb">${DateSUM}</span>(100条时间记录之和)<span class="Fb"> % ${latestlottery.productPrice}</span>(本商品总需参与人次)<span class="Fb"> = ${latestlottery.randomNumber-10000001}</span>(余数)<br />-->
<!--		 结果：<span class="Fb">${latestlottery.randomNumber-10000001}</span>(余数)<span class="Fb"> + 10000001 = <em>${latestlottery.randomNumber }</em></span></p>-->
			<b>最终结果：${latestlottery.randomNumber}</b></div>
		 </div>
	</div>
	</div>
	<!-- 商品详细介绍结束 -->
	<!-- 所有参与记录 -->
	<div class="AllRecord_Content" id="divBuyRecord" style="display: none;">
	    <div class="goods_loding" style="display: none;"><img border="0" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif" alt="" />正在加载......</div>
	<div id="userByListDIV">
	</div>
	<div class="pages" name="bitem">
	<ul class="pageULEx" id="pagination">
	</ul></div></div>
	<!-- 所有参与记录结束 -->
	<!-- 晒单开始 -->
	<div id="divPost" class="Single_Content">
		<div class="NoConMsg" style="display:none;"><span>暂无晒单记录~！</span></div>
	    <div class="goods_loding" style="display:none;"><img border="0" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif"/>正在加载......</div>
	    <div id="shareByListDIV"></div>
	    <div class="pages" name="bitem">
		<ul class="pageULEx2" id="pagination2"></ul>
		</div>
    </div>
    <!-- 晒单结束 -->
	<!-- 人气推荐 -->
	<div class="list_goodsTj" id="divGoodsRec">
    <div class="list_goodsTjTit">人气推荐</div>
    <div class="list_goodsTjCon" id="divRecommend">
	    <div class="Roll_Left"><a href="javascript:;" name="left" class="Roll_enabled">&lt;</a></div>
	    <div class="Roll_Con">
	        <ul id="ulRecommend">
	        </ul>
	    </div>
	    <div class="Roll_Right"><a href="javascript:;" name="right" class="Roll_enabled">&gt;</a></div>
    </div>
</div>
	<!-- 人气推荐结束 -->
    <script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lotterydetail.js"></script>
</body>
</html>