$.fn.jcarousel=function(k){var q={itemWidth:150,showNum:5,initIndex:1,initCallback:function(){}};k=k||{};$.extend(q,k);var r=this;var s=0;var o=0;var n=new m(this.parent().prev().find("a"));n.setEnabled();var p=new l(this.parent().next().find("a"));p.setEnabled();var t=$("li",this).length;o=t*q.itemWidth;s=q.itemWidth;r.width(o).css("left","0px");r.show("fast",q.initCallback);function m(a){this.btn=a;this.setDisabled=function(){this.btn.removeClass("Roll_enabled").unbind()};this.setEnabled=function(){this.btn.addClass("Roll_enabled").unbind().bind("click",function(){r.animate({left:"-"+s+"px"},"fast",function(){r.append(r.children().eq(0)).css("left","0px")})})}}function l(a){this.btn=a;this.setDisabled=function(){this.btn.removeClass("Roll_enabled").unbind()};this.setEnabled=function(){this.btn.addClass("Roll_enabled").unbind().bind("click",function(){r.prepend(r.children().eq(9)).css("left","-"+s+"px");r.animate({left:"0px"},"fast",function(){})})}}};$("#ulRecommend").jcarousel({itemWidth:182,initIndex:1,showNum:5});
