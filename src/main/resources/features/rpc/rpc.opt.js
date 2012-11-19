if(!window.gadgets["rpc"]){gadgets.rpc=function(){var M="__cb";
var V="";
var W="__ack";
var f=500;
var G=10;
var b="|";
var u="callback";
var g="origin";
var s="referer";
var q={};
var Z={};
var D={};
var B={};
var z=0;
var l={};
var m={};
var T={};
var d={};
var n={};
var E={};
var e=null;
var p=null;
var A=(window.top!==window.self);
var v=window.name;
var J=function(){};
var r=null;
var Q=0;
var ab=1;
var a=2;
var x=window.console;
var Y=x&&x.log&&function(ah){x.log(ah)
}||function(){};
var U=(function(){function ah(ai){return function(){Y(ai+": call ignored")
}
}return{getCode:function(){return"noop"
},isParentVerifiable:function(){return true
},init:ah("init"),setup:ah("setup"),call:ah("call")}
})();
if(gadgets.util){d=gadgets.util.getUrlParameters()
}function K(){if(d.rpctx=="flash"){return gadgets.rpctx.flash
}if(d.rpctx=="rmr"){return gadgets.rpctx.rmr
}return typeof window.postMessage==="function"?gadgets.rpctx.wpm:typeof window.postMessage==="object"?gadgets.rpctx.wpm:window.ActiveXObject?(gadgets.rpctx.flash?gadgets.rpctx.flash:gadgets.rpctx.nix):navigator.userAgent.indexOf("WebKit")>0?gadgets.rpctx.rmr:navigator.product==="Gecko"?gadgets.rpctx.frameElement:gadgets.rpctx.ifpc
}function k(am,ak){if(n[am]){return
}var ai=H;
if(!ak){ai=U
}n[am]=ai;
var ah=E[am]||[];
for(var aj=0;
aj<ah.length;
++aj){var al=ah[aj];
al.t=F(am);
ai.call(am,al.f,al)
}E[am]=[]
}var I=false,X=false;
function O(){if(X){return
}function ah(){I=true
}if(typeof window.addEventListener!="undefined"){window.addEventListener("unload",ah,false)
}else{if(typeof window.attachEvent!="undefined"){window.attachEvent("onunload",ah)
}}X=true
}function j(ah,al,ai,ak,aj){if(!B[al]||B[al]!==ai){gadgets.error("Invalid auth token. "+B[al]+" vs "+ai);
J(al,a)
}aj.onunload=function(){if(m[al]&&!I){J(al,ab);
gadgets.rpc.removeReceiver(al)
}};
O();
ak=gadgets.json.parse(decodeURIComponent(ak))
}function ac(al,ai){if(al&&typeof al.s==="string"&&typeof al.f==="string"&&al.a instanceof Array){if(typeof arbitrate==="function"&&!arbitrate(al.s,al.f)){return
}if(B[al.f]){if(B[al.f]!==al.t){gadgets.error("Invalid auth token. "+B[al.f]+" vs "+al.t);
J(al.f,a)
}}if(al.s===W){window.setTimeout(function(){k(al.f,true)
},0);
return
}if(al.c){al[u]=function(am){gadgets.rpc.call(al.f,M,null,al.c,am)
}
}if(ai){var aj=t(ai);
al[g]=ai;
var ak=al.r;
if(!ak||t(ak)!=aj){ak=ai
}al[s]=ak
}var ah=(q[al.s]||q[V]).apply(al,al.a);
if(al.c&&typeof ah!=="undefined"){gadgets.rpc.call(al.f,M,null,al.c,ah)
}}}function t(aj){if(!aj){return""
}aj=aj.toLowerCase();
if(aj.indexOf("//")==0){aj=window.location.protocol+aj
}if(aj.indexOf("://")==-1){aj=window.location.protocol+"//"+aj
}var ak=aj.substring(aj.indexOf("://")+3);
var ah=ak.indexOf("/");
if(ah!=-1){ak=ak.substring(0,ah)
}var am=aj.substring(0,aj.indexOf("://"));
var al="";
var an=ak.indexOf(":");
if(an!=-1){var ai=ak.substring(an+1);
ak=ak.substring(0,an);
if((am==="http"&&ai!=="80")||(am==="https"&&ai!=="443")){al=":"+ai
}}return am+"://"+ak+al
}function C(ai,ah){return"/"+ai+(ah?b+ah:"")
}function y(ak){if(ak.charAt(0)=="/"){var ai=ak.indexOf(b);
var aj=ai>0?ak.substring(1,ai):ak.substring(1);
var ah=ai>0?ak.substring(ai+1):null;
return{id:aj,origin:ah}
}else{return null
}}function ag(aj){if(typeof aj==="undefined"||aj===".."){return window.parent
}var ai=y(aj);
if(ai){return window.top.frames[ai.id]
}aj=String(aj);
ah=document.getElementById(aj);
if(ah&&ah.contentWindow){return ah.contentWindow
}var ah=window.frames[aj];
if(ah&&!ah.closed){return ah
}return null
}function L(ak){var aj=null;
var ah=P(ak);
if(ah){aj=ah
}else{var ai=y(ak);
if(ai){aj=ai.origin
}else{if(ak==".."){aj=d.parent
}else{aj=document.getElementById(ak).src
}}}return t(aj)
}var H=K();
q[V]=function(){Y("Unknown RPC service: "+this.s)
};
q[M]=function(ai,ah){var aj=l[ai];
if(aj){delete l[ai];
aj.call(this,ah)
}};
function aa(aj,ah){if(m[aj]===true){return
}if(typeof m[aj]==="undefined"){m[aj]=0
}var ai=ag(aj);
if(aj===".."||ai!=null){if(H.setup(aj,ah)===true){m[aj]=true;
return
}}if(m[aj]!==true&&m[aj]++<G){window.setTimeout(function(){aa(aj,ah)
},f)
}else{n[aj]=U;
m[aj]=true
}}var S={};
function N(ai,al){var ak=ag(ai);
if(!T[ai]||(T[ai]!==S&&ak.Function.prototype!==T[ai].constructor.prototype)){var aj=P(ai);
if(t(aj)!==t(window.location.href)){T[ai]=S;
return false
}try{var am=ak.gadgets;
T[ai]=am.rpc.receiveSameDomain
}catch(ah){T[ai]=S;
return false
}}if(T[ai]&&T[ai]!==S){T[ai](al);
return true
}return false
}function P(ai){var ah=Z[ai];
if(ah&&ah.substring(0,1)==="/"){if(ah.substring(1,2)==="/"){ah=document.location.protocol+ah
}else{ah=document.location.protocol+"//"+document.location.host+ah
}}return ah
}function af(ai,ah,aj){if(!/http(s)?:\/\/.+/.test(ah)){if(ah.indexOf("//")==0){ah=window.location.protocol+ah
}else{if(ah.charAt(0)=="/"){ah=window.location.protocol+"//"+window.location.host+ah
}else{if(ah.indexOf("://")==-1){ah=window.location.protocol+"//"+ah
}}}}Z[ai]=ah;
if(typeof aj!=="undefined"){D[ai]=!!aj
}}function F(ah){return B[ah]
}function c(ah,ai){ai=ai||"";
B[ah]=String(ai);
aa(ah,ai)
}function ae(ai){var ah=ai.passReferrer||"";
var aj=ah.split(":",2);
e=aj[0]||"none";
p=aj[1]||"origin"
}function ad(ah){if(R(ah)){H=gadgets.rpctx.ifpc;
H.init(ac,k)
}}function R(ah){return String(ah.useLegacyProtocol)==="true"
}function h(ai,ah){function aj(am){var al=am?am.rpc:{};
al=al||{};
var ak=al.parentRelayUrl||"";
ak=t(d.parent||ah)+ak;
af("..",ak,R(al));
ad(al);
c("..",ai)
}if(!d.parent&&ah){aj({});
return
}gadgets.config.register("rpc",null,aj)
}function o(ai,am,ao){var al=null;
if(ai.charAt(0)!="/"){if(!gadgets.util){return
}al=document.getElementById(ai);
if(!al){throw new Error("Cannot set up gadgets.rpc receiver with ID: "+ai+", element not found.")
}}var ah=al&&al.src;
var aj=am||gadgets.rpc.getOrigin(ah);
af(ai,aj);
var an=gadgets.util.getUrlParameters(ah);
var ak=ao||an.rpctoken;
c(ai,ak)
}function i(ah,aj,ak){if(ah===".."){var ai=ak||d.rpctoken||d.ifpctok||"";
h(ai,aj)
}else{o(ah,aj,ak)
}}function w(aj){if(e==="bidir"||(e==="c2p"&&aj==="..")||(e==="p2c"&&aj!=="..")){var ai=window.location.href;
var ak="?";
if(p==="query"){ak="#"
}else{if(p==="hash"){return ai
}}var ah=ai.lastIndexOf(ak);
ah=ah===-1?ai.length:ah;
return ai.substring(0,ah)
}return null
}return{config:function(ah){if(typeof ah.securityCallback==="function"){J=ah.securityCallback
}if(typeof ah.arbitrator==="function"){arbitrate=ah.arbitrator
}},register:function(ai,ah){if(ai===M||ai===W){throw new Error("Cannot overwrite callback/ack service")
}if(ai===V){throw new Error("Cannot overwrite default service: use registerDefault")
}q[ai]=ah
},unregister:function(ah){if(ah===M||ah===W){throw new Error("Cannot delete callback/ack service")
}if(ah===V){throw new Error("Cannot delete default service: use unregisterDefault")
}delete q[ah]
},registerDefault:function(ah){q[V]=ah
},unregisterDefault:function(){delete q[V]
},forceParentVerifiable:function(){if(!H.isParentVerifiable()){H=gadgets.rpctx.ifpc
}},call:function(ah,aj,ao,am){ah=ah||"..";
var an="..";
if(ah===".."){an=v
}else{if(ah.charAt(0)=="/"){an=C(v,gadgets.rpc.getOrigin(window.location.href))
}}++z;
if(ao){l[z]=ao
}var al={s:aj,f:an,c:ao?z:0,a:Array.prototype.slice.call(arguments,3),t:B[ah],l:!!D[ah]};
var ai=w(ah);
if(ai){al.r=ai
}if(ah!==".."&&y(ah)==null&&!document.getElementById(ah)){return
}if(N(ah,al)){return
}var ak=n[ah];
if(!ak&&y(ah)!==null){ak=H
}if(!ak){if(!E[ah]){E[ah]=[al]
}else{E[ah].push(al)
}return
}if(D[ah]){ak=gadgets.rpctx.ifpc
}if(ak.call(ah,an,al)===false){n[ah]=U;
H.call(ah,an,al)
}},getRelayUrl:P,setRelayUrl:af,setAuthToken:c,setupReceiver:i,getAuthToken:F,removeReceiver:function(ah){delete Z[ah];
delete D[ah];
delete B[ah];
delete m[ah];
delete T[ah];
delete n[ah]
},getRelayChannel:function(){return H.getCode()
},receive:function(ai,ah){if(ai.length>4){H._receiveMessage(ai,ac)
}else{j.apply(null,ai.concat(ah))
}},receiveSameDomain:function(ah){ah.a=Array.prototype.slice.call(ah.a);
window.setTimeout(function(){ac(ah)
},0)
},getOrigin:t,getTargetOrigin:L,init:function(){if(H.init(ac,k)===false){H=U
}if(A){i("..")
}else{gadgets.config.register("rpc",null,function(ai){var ah=ai.rpc||{};
ah=ah||{};
ad(ah)
})
}},_getTargetWin:ag,_parseSiblingId:y,ACK:W,RPC_ID:v||"..",SEC_ERROR_LOAD_TIMEOUT:Q,SEC_ERROR_FRAME_PHISH:ab,SEC_ERROR_FORGED_MSG:a}
}();
gadgets.rpc.init()
};