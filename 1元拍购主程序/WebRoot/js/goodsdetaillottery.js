function startTimeOut(){if(!isLoaded){setTimeout("startTimeOut()",1000);return false}var d=$("#hidCodeID").val();var e=$("#divLotteryTimer");var f=$("#divLotteryTiming");$.ajax({url:"/products/isLottery.action",data:"id="+d,success:function(Q){var I=Q.spStatus;var H=parseInt(Q.date);var O=function(){e.hide();f.show();var g=function(){$.ajax({url:"/lottery/lotteryUtil.action",data:"id="+d,success:function(h){if(h=="true"){location.href=$www+"/lotteryDetail/"+d+".html"}}})};setInterval(g,2000)};if(I==2){var P=new Date();P.setSeconds(P.getSeconds()+H);var D=0;var c=0;var y=0;var N=$("#imgFunny");var E=function(){var g=new Date();if(P>g){var i=(P.getTime()-g.getTime())/1000;var h=i%60;D=parseInt(i/60);c=parseInt(h);if(h>=c){y=parseInt((h-c)*10)}else{y=0}var j=parseInt(i/15)+1;if(j<11){N.attr("src",$img+"/Images/"+j+".gif")}setTimeout(E,3000)}};var M=$("#liMinute1");var K=$("#liMinute2");var B=$("#liSecond1");var A=$("#liSecond2");var C=$("#liMilliSecond1");var a=$("#last");var J=9;var z=function(){J--;if(J<0){J=9}a.attr("class","Code_"+J).html(J+"<b></b>");setTimeout(z,10)};var F=function(){y--;if(y<1){if(c<1){if(D<1){O();return}else{D--}c=59}else{c--}y=9}setTimeout(F,100)};var L=-1,b=-1;var G=function(){C.attr("class","Code_"+y).html(y+"<b></b>");var g=0;if(L!=c){if(c<10){B.attr("class","Code_0").html("0<b></b>");A.attr("class","Code_"+c).html(c+"<b></b>")}else{g=parseInt(c/10);B.attr("class","Code_"+g).html(g+"<b></b>");g=parseInt(c%10);A.attr("class","Code_"+g).html(g+"<b></b>")}L=c}if(b!=D){if(D<10){M.attr("class","Code_0").html("0<b></b>");K.attr("class","Code_"+D).html(D+"<b></b>")}else{g=parseInt(D/10);M.attr("class","Code_"+g).html(g+"<b></b>");g=parseInt(D%10);K.attr("class","Code_"+g).html(g+"<b></b>")}b=D}setTimeout(G,100)};E();F();z();G();e.show()}else{O()}}})};
