<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta name="decorator" content="index_template" />
	<title>晒单分享_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<meta content="app-id=518966501" name="apple-itunes-app" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
	<meta content="yes" name="apple-mobile-web-app-capable" /><meta content="black" name="apple-mobile-web-app-status-bar-style" />
	<meta content="telephone=no" name="format-detection" />
	<link href="/css/single.css" rel="stylesheet" type="text/css" />
	<script src="/js/detail.js" language="javascript" type="text/javascript"></script>
</head>
<body>
   <div class="h5-1yyg-v1">
        
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>晒单详情</h2>
        <div class="head-r">
	        <a href="/" class="z-Home"></a>
        </div>
    </header>
		<c:choose>
			<c:when test="${user!=null}">
				<input name="userState" type="hidden" id="userState" value="${userId }" />
			</c:when>
			<c:otherwise>
				<input name="userState" type="hidden" id="userState" value="" />
			</c:otherwise>
		</c:choose>
        <input name="hidPostID" type="hidden" id="hidPostID" value="${id }" />
        <input name="hidIsLogin" type="hidden" id="hidIsLogin" />
        <input name="codeID" type="hidden" id="codeID" value="${shareInfoPro.spellbuyProductId }" />
        <section class="clearfix g-share-lucky">
	        
	            <span class='z-Reward'><em>1000</em></span>
	            <a class="fl u-lucky-img" href="/u/${shareInfoPro.userId }.html"><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl() %>${shareInfoPro.winUserFace }" /> </a>
	            <div class="u-lucky-r">
	                <p>幸运获得者：<a href="/u/${shareInfoPro.userId }.html" class="z-user blue">${shareInfoPro.winUserName }</a></p>
                    <p>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<em class="orange">${shareInfoPro.winRandomNumber }</em></p>
                    <p>本期<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<em class="orange">${shareInfoPro.buyNumberCount }</em>人次</p>
                    <p>揭晓时间：<em class="arial">${shareInfoPro.announcedTime }</em></p> 
	            </div> 
                
        </section>
        <!-- 热门推荐 -->
        <section class="clearfix g-share-ct">		
	        <b class="z-aw-es z-arrow"></b>	
	        <article class="m-share-con">
		        <h2>${shareInfoPro.shareTitle }</h2>
		        <em class="arial">${shareInfoPro.shareDate }</em>
		        <p class="z-share-pad" id="shareContent">${shareInfoPro.shareContent }</p>
		        <s:iterator value="shareimageList" var="shareImages">
		        <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${shareImages.images }" border="0" alt="" /></p>
		        </s:iterator>
		        
	        </article>
	                <aside class="clearfix m-share-goods" onclick="location.href='/lotteryDetail/${shareInfoPro.productId }.html'">
	                    <h3 class="fl">获得的商品</h3>
                        <a class="fl u-goods-img" href="javascript:;"><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${shareInfoPro.productImg }"></a>
                        <div class="u-goods-r">
	                        <p class="z-goods-tt"><a href="javascript:;" class="gray6">(第${shareInfoPro.productPeriod }期)${shareInfoPro.productName }</a></p>
	                        <p>价值：<em class="arial">￥${shareInfoPro.productPrice }.00</em></p>
                        </div>
                        <a href="/lotteryDetail/${shareInfoPro.productId }.html" class="u-rs-m1"><b class="z-arrow"></b></a>
	                </aside>
	            
	       
            <div class="m-share-fixed">
                <div id="CommentNav"> 
                    <div class="m-share-btn">
                        <div id="divtest" class="u-btn-w"><a id="emHits" class="z-btn-mood"><s></s>羡慕嫉妒恨(<em>${shareInfoPro.upCount }</em>)</a></div>
                        <div class="u-btn-w"><a id="btnComment" href="javascript:;" class="z-btn-comment"><s></s>我要评论</a></div>
                        <div class="u-btn-w"><a id="btnShare" href="javascript:;" class="z-btn-Share"><s></s>分享</a></div>
                    </div>
                    <div class="m-comment" style="display:none;">
                        <div class="u-comment ">
                            <textarea name="comment" id="comment" rows="3" class="z-comment-txt" placeholder="说点什么吧！">说点什么吧！</textarea>
                        </div>
                        <div class="u-Btn">
                            <div class="u-Btn-li"><a id="btnCancel" href="javascript:;" class="z-CloseBtn">取 消</a></div>
                            <div class="u-Btn-li"><a id="btnPublish" href="javascript:;" class="z-DefineBtn">发表评论</a></div>
                        </div>
                    </div>
                    <div class="m-shareT-round"></div>
                </div>
                <div id="fillDiv" style="display:none;"></div>
            </div>
	        
            <article class="m-share-comment m-round">
                <h3>共<span id="ReplyCount"  class="z-user orange">${resultCount }</span>条评论</h3>
                <ul id="replyList"></ul>
                <a id="btnLoadMore" class="loading" href="javascript:;" style="display:none;">点击加载更多</a>
                <div id="divLoading" class="loading"style="display:none;"><b></b>正在加载</div>
            </article>
        </section>
                
   </div>
</body>
</html>
