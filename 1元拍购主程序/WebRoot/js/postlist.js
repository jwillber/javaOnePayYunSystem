$(function(){var e=$("#topid").val();if(e=="new20"){$("#new").attr("class","SortCur")}else{if(e=="hot20"){$("#popularity").attr("class","SortCur")}else{if(e=="reply20"){$("#comment").attr("class","SortCur")}}}var b=function(){$("div.share_list_content","#loadingPicBlock").hover(function(){$(this).addClass("hover")},function(){$(this).removeClass("hover")}).each(function(){var f=$(this).find("span.smile");var a=false;var n=f.find("em");var j=n.html();var m=n.attr("num");if(c("UP_"+m)=="1"){a=true;f.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155("+j+")")}else{f.unbind("click").bind("click",function(){if(a){return false}$.ajax({url:"/shareShow/upShareInfo.action?shareId="+m,type:"post",data:"string"});d("UP_"+m,"1",1);a=true;f.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155("+(parseInt(j)+1)+")");var g=$("<div class='ts'>\u7fa1\u6155+1</div>");f.append(g);g.fadeTo(3000,0,function(){g.remove()})})}f.parent().show()})};b();function d(f,h,a){var g=a;var i=new Date();i.setTime(i.getTime()+g*24*60*60*1000);document.cookie=f+"="+escape(h)+";id=1ypg;path=/;expires="+i.toGMTString()+";domain="+$domain}function c(f){var a=document.cookie.match(new RegExp("(^| )"+f+"=([^;]*)(;|$)"));if(a!=null){return unescape(a[2])}return null}$(".scrollLoading").scrollLoading()});
