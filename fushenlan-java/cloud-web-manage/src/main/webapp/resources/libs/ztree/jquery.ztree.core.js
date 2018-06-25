/*
 * JQuery zTree core v3.5.32
 * http://treejs.cn/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2018-01-06
 */
(function(q){var H,I,J,K,L,M,u,s={},v={},w={},N={treeId:"",treeObj:null,view:{addDiyDom:null,autoCancelSelected:!0,dblClickExpand:!0,expandSpeed:"fast",fontCss:{},nameIsHTML:!1,selectedMulti:!0,showIcon:!0,showLine:!0,showTitle:!0,txtSelectedEnable:!1},data:{key:{isParent:"isParent",children:"children",name:"name",title:"",url:"url",icon:"icon"},simpleData:{enable:!1,idKey:"id",pIdKey:"pId",rootPId:null},keep:{parent:!1,leaf:!1}},async:{enable:!1,contentType:"application/x-www-form-urlencoded",type:"post",
dataType:"text",url:"",autoParam:[],otherParam:[],dataFilter:null},callback:{beforeAsync:null,beforeClick:null,beforeDblClick:null,beforeRightClick:null,beforeMouseDown:null,beforeMouseUp:null,beforeExpand:null,beforeCollapse:null,beforeRemove:null,onAsyncError:null,onAsyncSuccess:null,onNodeCreated:null,onClick:null,onDblClick:null,onRightClick:null,onMouseDown:null,onMouseUp:null,onExpand:null,onCollapse:null,onRemove:null}},x=[function(a){var b=a.treeObj,c=f.event;b.bind(c.NODECREATED,function(b,
c,h){j.apply(a.callback.onNodeCreated,[b,c,h])});b.bind(c.CLICK,function(b,c,h,e,m){j.apply(a.callback.onClick,[c,h,e,m])});b.bind(c.EXPAND,function(b,c,h){j.apply(a.callback.onExpand,[b,c,h])});b.bind(c.COLLAPSE,function(b,c,h){j.apply(a.callback.onCollapse,[b,c,h])});b.bind(c.ASYNC_SUCCESS,function(b,c,h,e){j.apply(a.callback.onAsyncSuccess,[b,c,h,e])});b.bind(c.ASYNC_ERROR,function(b,c,h,e,m,f){j.apply(a.callback.onAsyncError,[b,c,h,e,m,f])});b.bind(c.REMOVE,function(b,c,h){j.apply(a.callback.onRemove,
[b,c,h])});b.bind(c.SELECTED,function(b,c,h){j.apply(a.callback.onSelected,[c,h])});b.bind(c.UNSELECTED,function(b,c,h){j.apply(a.callback.onUnSelected,[c,h])})}],y=[function(a){var b=f.event;a.treeObj.unbind(b.NODECREATED).unbind(b.CLICK).unbind(b.EXPAND).unbind(b.COLLAPSE).unbind(b.ASYNC_SUCCESS).unbind(b.ASYNC_ERROR).unbind(b.REMOVE).unbind(b.SELECTED).unbind(b.UNSELECTED)}],z=[function(a){var b=e.getCache(a);b||(b={},e.setCache(a,b));b.nodes=[];b.doms=[]}],A=[function(a,b,c,d,g,h){if(c){var k=
e.getRoot(a),m=e.nodeChildren(a,c);c.level=b;c.tId=a.treeId+"_"+ ++k.zId;c.parentTId=d?d.tId:null;c.open=typeof c.open=="string"?j.eqs(c.open,"true"):!!c.open;b=e.nodeIsParent(a,c);j.isArray(m)&&!(b===!1||typeof b=="string"&&j.eqs(b,"false"))?(e.nodeIsParent(a,c,!0),c.zAsync=!0):(b=e.nodeIsParent(a,c,b),c.open=b&&!a.async.enable?c.open:!1,c.zAsync=!b);c.isFirstNode=g;c.isLastNode=h;c.getParentNode=function(){return e.getNodeCache(a,c.parentTId)};c.getPreNode=function(){return e.getPreNode(a,c)};c.getNextNode=
function(){return e.getNextNode(a,c)};c.getIndex=function(){return e.getNodeIndex(a,c)};c.getPath=function(){return e.getNodePath(a,c)};c.isAjaxing=!1;e.fixPIdKeyValue(a,c)}}],t=[function(a){var b=a.target,c=e.getSetting(a.data.treeId),d="",g=null,h="",k="",m=null,i=null,o=null;if(j.eqs(a.type,"mousedown"))k="mousedown";else if(j.eqs(a.type,"mouseup"))k="mouseup";else if(j.eqs(a.type,"contextmenu"))k="contextmenu";else if(j.eqs(a.type,"click"))if(j.eqs(b.tagName,"span")&&b.getAttribute("treeNode"+
f.id.SWITCH)!==null)d=j.getNodeMainDom(b).id,h="switchNode";else{if(o=j.getMDom(c,b,[{tagName:"a",attrName:"treeNode"+f.id.A}]))d=j.getNodeMainDom(o).id,h="clickNode"}else if(j.eqs(a.type,"dblclick")&&(k="dblclick",o=j.getMDom(c,b,[{tagName:"a",attrName:"treeNode"+f.id.A}])))d=j.getNodeMainDom(o).id,h="switchNode";if(k.length>0&&d.length==0&&(o=j.getMDom(c,b,[{tagName:"a",attrName:"treeNode"+f.id.A}])))d=j.getNodeMainDom(o).id;if(d.length>0)switch(g=e.getNodeCache(c,d),h){case "switchNode":e.nodeIsParent(c,
g)?j.eqs(a.type,"click")||j.eqs(a.type,"dblclick")&&j.apply(c.view.dblClickExpand,[c.treeId,g],c.view.dblClickExpand)?m=H:h="":h="";break;case "clickNode":m=I}switch(k){case "mousedown":i=J;break;case "mouseup":i=K;break;case "dblclick":i=L;break;case "contextmenu":i=M}return{stop:!1,node:g,nodeEventType:h,nodeEventCallback:m,treeEventType:k,treeEventCallback:i}}],B=[function(a){var b=e.getRoot(a);b||(b={},e.setRoot(a,b));e.nodeChildren(a,b,[]);b.expandTriggerFlag=!1;b.curSelectedList=[];b.noSelection=
!0;b.createdNodes=[];b.zId=0;b._ver=(new Date).getTime()}],C=[],D=[],E=[],F=[],G=[],e={addNodeCache:function(a,b){e.getCache(a).nodes[e.getNodeCacheId(b.tId)]=b},getNodeCacheId:function(a){return a.substring(a.lastIndexOf("_")+1)},addAfterA:function(a){D.push(a)},addBeforeA:function(a){C.push(a)},addInnerAfterA:function(a){F.push(a)},addInnerBeforeA:function(a){E.push(a)},addInitBind:function(a){x.push(a)},addInitUnBind:function(a){y.push(a)},addInitCache:function(a){z.push(a)},addInitNode:function(a){A.push(a)},
addInitProxy:function(a,b){b?t.splice(0,0,a):t.push(a)},addInitRoot:function(a){B.push(a)},addNodesData:function(a,b,c,d){var g=e.nodeChildren(a,b);g?c>=g.length&&(c=-1):(g=e.nodeChildren(a,b,[]),c=-1);if(g.length>0&&c===0)g[0].isFirstNode=!1,i.setNodeLineIcos(a,g[0]);else if(g.length>0&&c<0)g[g.length-1].isLastNode=!1,i.setNodeLineIcos(a,g[g.length-1]);e.nodeIsParent(a,b,!0);c<0?e.nodeChildren(a,b,g.concat(d)):(a=[c,0].concat(d),g.splice.apply(g,a))},addSelectedNode:function(a,b){var c=e.getRoot(a);
e.isSelectedNode(a,b)||c.curSelectedList.push(b)},addCreatedNode:function(a,b){(a.callback.onNodeCreated||a.view.addDiyDom)&&e.getRoot(a).createdNodes.push(b)},addZTreeTools:function(a){G.push(a)},exSetting:function(a){q.extend(!0,N,a)},fixPIdKeyValue:function(a,b){a.data.simpleData.enable&&(b[a.data.simpleData.pIdKey]=b.parentTId?b.getParentNode()[a.data.simpleData.idKey]:a.data.simpleData.rootPId)},getAfterA:function(a,b,c){for(var d=0,e=D.length;d<e;d++)D[d].apply(this,arguments)},getBeforeA:function(a,
b,c){for(var d=0,e=C.length;d<e;d++)C[d].apply(this,arguments)},getInnerAfterA:function(a,b,c){for(var d=0,e=F.length;d<e;d++)F[d].apply(this,arguments)},getInnerBeforeA:function(a,b,c){for(var d=0,e=E.length;d<e;d++)E[d].apply(this,arguments)},getCache:function(a){return w[a.treeId]},getNodeIndex:function(a,b){if(!b)return null;for(var c=b.parentTId?b.getParentNode():e.getRoot(a),c=e.nodeChildren(a,c),d=0,g=c.length-1;d<=g;d++)if(c[d]===b)return d;return-1},getNextNode:function(a,b){if(!b)return null;
for(var c=b.parentTId?b.getParentNode():e.getRoot(a),c=e.nodeChildren(a,c),d=0,g=c.length-1;d<=g;d++)if(c[d]===b)return d==g?null:c[d+1];return null},getNodeByParam:function(a,b,c,d){if(!b||!c)return null;for(var g=0,h=b.length;g<h;g++){var k=b[g];if(k[c]==d)return b[g];k=e.nodeChildren(a,k);if(k=e.getNodeByParam(a,k,c,d))return k}return null},getNodeCache:function(a,b){if(!b)return null;var c=w[a.treeId].nodes[e.getNodeCacheId(b)];return c?c:null},getNodePath:function(a,b){if(!b)return null;var c;
(c=b.parentTId?b.getParentNode().getPath():[])&&c.push(b);return c},getNodes:function(a){return e.nodeChildren(a,e.getRoot(a))},getNodesByParam:function(a,b,c,d){if(!b||!c)return[];for(var g=[],h=0,k=b.length;h<k;h++){var m=b[h];m[c]==d&&g.push(m);m=e.nodeChildren(a,m);g=g.concat(e.getNodesByParam(a,m,c,d))}return g},getNodesByParamFuzzy:function(a,b,c,d){if(!b||!c)return[];for(var g=[],d=d.toLowerCase(),h=0,k=b.length;h<k;h++){var m=b[h];typeof m[c]=="string"&&b[h][c].toLowerCase().indexOf(d)>-1&&
g.push(m);m=e.nodeChildren(a,m);g=g.concat(e.getNodesByParamFuzzy(a,m,c,d))}return g},getNodesByFilter:function(a,b,c,d,g){if(!b)return d?null:[];for(var h=d?null:[],k=0,m=b.length;k<m;k++){var f=b[k];if(j.apply(c,[f,g],!1)){if(d)return f;h.push(f)}f=e.nodeChildren(a,f);f=e.getNodesByFilter(a,f,c,d,g);if(d&&f)return f;h=d?f:h.concat(f)}return h},getPreNode:function(a,b){if(!b)return null;for(var c=b.parentTId?b.getParentNode():e.getRoot(a),c=e.nodeChildren(a,c),d=0,g=c.length;d<g;d++)if(c[d]===b)return d==
0?null:c[d-1];return null},getRoot:function(a){return a?v[a.treeId]:null},getRoots:function(){return v},getSetting:function(a){return s[a]},getSettings:function(){return s},getZTreeTools:function(a){return(a=this.getRoot(this.getSetting(a)))?a.treeTools:null},initCache:function(a){for(var b=0,c=z.length;b<c;b++)z[b].apply(this,arguments)},initNode:function(a,b,c,d,e,h){for(var k=0,f=A.length;k<f;k++)A[k].apply(this,arguments)},initRoot:function(a){for(var b=0,c=B.length;b<c;b++)B[b].apply(this,arguments)},
isSelectedNode:function(a,b){for(var c=e.getRoot(a),d=0,g=c.curSelectedList.length;d<g;d++)if(b===c.curSelectedList[d])return!0;return!1},nodeChildren:function(a,b,c){if(!b)return null;a=a.data.key.children;typeof c!=="undefined"&&(b[a]=c);return b[a]},nodeIsParent:function(a,b,c){if(!b)return!1;a=a.data.key.isParent;typeof c!=="undefined"&&(typeof c==="string"&&(c=j.eqs(checked,"true")),b[a]=!!c);return b[a]},nodeName:function(a,b,c){a=a.data.key.name;typeof c!=="undefined"&&(b[a]=c);return""+b[a]},
nodeTitle:function(a,b){return""+b[a.data.key.title===""?a.data.key.name:a.data.key.title]},removeNodeCache:function(a,b){var c=e.nodeChildren(a,b);if(c)for(var d=0,g=c.length;d<g;d++)e.removeNodeCache(a,c[d]);e.getCache(a).nodes[e.getNodeCacheId(b.tId)]=null},removeSelectedNode:function(a,b){for(var c=e.getRoot(a),d=0,g=c.curSelectedList.length;d<g;d++)if(b===c.curSelectedList[d]||!e.getNodeCache(a,c.curSelectedList[d].tId))c.curSelectedList.splice(d,1),a.treeObj.trigger(f.event.UNSELECTED,[a.treeId,
b]),d--,g--},setCache:function(a,b){w[a.treeId]=b},setRoot:function(a,b){v[a.treeId]=b},setZTreeTools:function(a,b){for(var c=0,d=G.length;c<d;c++)G[c].apply(this,arguments)},transformToArrayFormat:function(a,b){function c(b){d.push(b);(b=e.nodeChildren(a,b))&&(d=d.concat(e.transformToArrayFormat(a,b)))}if(!b)return[];var d=[];if(j.isArray(b))for(var g=0,h=b.length;g<h;g++)c(b[g]);else c(b);return d},transformTozTreeFormat:function(a,b){var c,d,g=a.data.simpleData.idKey,h=a.data.simpleData.pIdKey;
if(!g||g==""||!b)return[];if(j.isArray(b)){var k=[],f={};for(c=0,d=b.length;c<d;c++)f[b[c][g]]=b[c];for(c=0,d=b.length;c<d;c++){var i=f[b[c][h]];if(i&&b[c][g]!=b[c][h]){var o=e.nodeChildren(a,i);o||(o=e.nodeChildren(a,i,[]));o.push(b[c])}else k.push(b[c])}return k}else return[b]}},n={bindEvent:function(a){for(var b=0,c=x.length;b<c;b++)x[b].apply(this,arguments)},unbindEvent:function(a){for(var b=0,c=y.length;b<c;b++)y[b].apply(this,arguments)},bindTree:function(a){var b={treeId:a.treeId},c=a.treeObj;
a.view.txtSelectedEnable||c.bind("selectstart",u).css({"-moz-user-select":"-moz-none"});c.bind("click",b,n.proxy);c.bind("dblclick",b,n.proxy);c.bind("mouseover",b,n.proxy);c.bind("mouseout",b,n.proxy);c.bind("mousedown",b,n.proxy);c.bind("mouseup",b,n.proxy);c.bind("contextmenu",b,n.proxy)},unbindTree:function(a){a.treeObj.unbind("selectstart",u).unbind("click",n.proxy).unbind("dblclick",n.proxy).unbind("mouseover",n.proxy).unbind("mouseout",n.proxy).unbind("mousedown",n.proxy).unbind("mouseup",
n.proxy).unbind("contextmenu",n.proxy)},doProxy:function(a){for(var b=[],c=0,d=t.length;c<d;c++){var e=t[c].apply(this,arguments);b.push(e);if(e.stop)break}return b},proxy:function(a){var b=e.getSetting(a.data.treeId);if(!j.uCanDo(b,a))return!0;for(var b=n.doProxy(a),c=!0,d=0,g=b.length;d<g;d++){var h=b[d];h.nodeEventCallback&&(c=h.nodeEventCallback.apply(h,[a,h.node])&&c);h.treeEventCallback&&(c=h.treeEventCallback.apply(h,[a,h.node])&&c)}return c}};H=function(a,b){var c=e.getSetting(a.data.treeId);
if(b.open){if(j.apply(c.callback.beforeCollapse,[c.treeId,b],!0)==!1)return!0}else if(j.apply(c.callback.beforeExpand,[c.treeId,b],!0)==!1)return!0;e.getRoot(c).expandTriggerFlag=!0;i.switchNode(c,b);return!0};I=function(a,b){var c=e.getSetting(a.data.treeId),d=c.view.autoCancelSelected&&(a.ctrlKey||a.metaKey)&&e.isSelectedNode(c,b)?0:c.view.autoCancelSelected&&(a.ctrlKey||a.metaKey)&&c.view.selectedMulti?2:1;if(j.apply(c.callback.beforeClick,[c.treeId,b,d],!0)==!1)return!0;d===0?i.cancelPreSelectedNode(c,
b):i.selectNode(c,b,d===2);c.treeObj.trigger(f.event.CLICK,[a,c.treeId,b,d]);return!0};J=function(a,b){var c=e.getSetting(a.data.treeId);j.apply(c.callback.beforeMouseDown,[c.treeId,b],!0)&&j.apply(c.callback.onMouseDown,[a,c.treeId,b]);return!0};K=function(a,b){var c=e.getSetting(a.data.treeId);j.apply(c.callback.beforeMouseUp,[c.treeId,b],!0)&&j.apply(c.callback.onMouseUp,[a,c.treeId,b]);return!0};L=function(a,b){var c=e.getSetting(a.data.treeId);j.apply(c.callback.beforeDblClick,[c.treeId,b],!0)&&
j.apply(c.callback.onDblClick,[a,c.treeId,b]);return!0};M=function(a,b){var c=e.getSetting(a.data.treeId);j.apply(c.callback.beforeRightClick,[c.treeId,b],!0)&&j.apply(c.callback.onRightClick,[a,c.treeId,b]);return typeof c.callback.onRightClick!="function"};u=function(a){a=a.originalEvent.srcElement.nodeName.toLowerCase();return a==="input"||a==="textarea"};var j={apply:function(a,b,c){return typeof a=="function"?a.apply(O,b?b:[]):c},canAsync:function(a,b){var c=e.nodeChildren(a,b),d=e.nodeIsParent(a,
b);return a.async.enable&&b&&d&&!(b.zAsync||c&&c.length>0)},clone:function(a){if(a===null)return null;var b=j.isArray(a)?[]:{},c;for(c in a)b[c]=a[c]instanceof Date?new Date(a[c].getTime()):typeof a[c]==="object"?j.clone(a[c]):a[c];return b},eqs:function(a,b){return a.toLowerCase()===b.toLowerCase()},isArray:function(a){return Object.prototype.toString.apply(a)==="[object Array]"},isElement:function(a){return typeof HTMLElement==="object"?a instanceof HTMLElement:a&&typeof a==="object"&&a!==null&&
a.nodeType===1&&typeof a.nodeName==="string"},$:function(a,b,c){b&&typeof b!="string"&&(c=b,b="");return typeof a=="string"?q(a,c?c.treeObj.get(0).ownerDocument:null):q("#"+a.tId+b,c?c.treeObj:null)},getMDom:function(a,b,c){if(!b)return null;for(;b&&b.id!==a.treeId;){for(var d=0,e=c.length;b.tagName&&d<e;d++)if(j.eqs(b.tagName,c[d].tagName)&&b.getAttribute(c[d].attrName)!==null)return b;b=b.parentNode}return null},getNodeMainDom:function(a){return q(a).parent("li").get(0)||q(a).parentsUntil("li").parent().get(0)},
isChildOrSelf:function(a,b){return q(a).closest("#"+b).length>0},uCanDo:function(){return!0}},i={addNodes:function(a,b,c,d,g){var h=e.nodeIsParent(a,b);if(!a.data.keep.leaf||!b||h)if(j.isArray(d)||(d=[d]),a.data.simpleData.enable&&(d=e.transformTozTreeFormat(a,d)),b){var h=l(b,f.id.SWITCH,a),k=l(b,f.id.ICON,a),m=l(b,f.id.UL,a);if(!b.open)i.replaceSwitchClass(b,h,f.folder.CLOSE),i.replaceIcoClass(b,k,f.folder.CLOSE),b.open=!1,m.css({display:"none"});e.addNodesData(a,b,c,d);i.createNodes(a,b.level+
1,d,b,c);g||i.expandCollapseParentNode(a,b,!0)}else e.addNodesData(a,e.getRoot(a),c,d),i.createNodes(a,0,d,null,c)},appendNodes:function(a,b,c,d,g,h,k){if(!c)return[];var f=[],j=d?d:e.getRoot(a),j=e.nodeChildren(a,j),o,l;if(!j||g>=j.length-c.length)g=-1;for(var n=0,Q=c.length;n<Q;n++){var p=c[n];h&&(o=(g===0||j.length==c.length)&&n==0,l=g<0&&n==c.length-1,e.initNode(a,b,p,d,o,l,k),e.addNodeCache(a,p));o=e.nodeIsParent(a,p);l=[];var q=e.nodeChildren(a,p);q&&q.length>0&&(l=i.appendNodes(a,b+1,q,p,-1,
h,k&&p.open));k&&(i.makeDOMNodeMainBefore(f,a,p),i.makeDOMNodeLine(f,a,p),e.getBeforeA(a,p,f),i.makeDOMNodeNameBefore(f,a,p),e.getInnerBeforeA(a,p,f),i.makeDOMNodeIcon(f,a,p),e.getInnerAfterA(a,p,f),i.makeDOMNodeNameAfter(f,a,p),e.getAfterA(a,p,f),o&&p.open&&i.makeUlHtml(a,p,f,l.join("")),i.makeDOMNodeMainAfter(f,a,p),e.addCreatedNode(a,p))}return f},appendParentULDom:function(a,b){var c=[],d=l(b,a);!d.get(0)&&b.parentTId&&(i.appendParentULDom(a,b.getParentNode()),d=l(b,a));var g=l(b,f.id.UL,a);g.get(0)&&
g.remove();g=e.nodeChildren(a,b);g=i.appendNodes(a,b.level+1,g,b,-1,!1,!0);i.makeUlHtml(a,b,c,g.join(""));d.append(c.join(""))},asyncNode:function(a,b,c,d){var g,h;g=e.nodeIsParent(a,b);if(b&&!g)return j.apply(d),!1;else if(b&&b.isAjaxing)return!1;else if(j.apply(a.callback.beforeAsync,[a.treeId,b],!0)==!1)return j.apply(d),!1;if(b)b.isAjaxing=!0,l(b,f.id.ICON,a).attr({style:"","class":f.className.BUTTON+" "+f.className.ICO_LOADING});var k={},m=j.apply(a.async.autoParam,[a.treeId,b],a.async.autoParam);
for(g=0,h=m.length;b&&g<h;g++){var r=m[g].split("="),o=r;r.length>1&&(o=r[1],r=r[0]);k[o]=b[r]}m=j.apply(a.async.otherParam,[a.treeId,b],a.async.otherParam);if(j.isArray(m))for(g=0,h=m.length;g<h;g+=2)k[m[g]]=m[g+1];else for(var n in m)k[n]=m[n];var P=e.getRoot(a)._ver;q.ajax({contentType:a.async.contentType,cache:!1,type:a.async.type,url:j.apply(a.async.url,[a.treeId,b],a.async.url),data:a.async.contentType.indexOf("application/json")>-1?JSON.stringify(k):k,dataType:a.async.dataType,success:function(h){if(P==
e.getRoot(a)._ver){var k=[];try{k=!h||h.length==0?[]:typeof h=="string"?eval("("+h+")"):h}catch(g){k=h}if(b)b.isAjaxing=null,b.zAsync=!0;i.setNodeLineIcos(a,b);k&&k!==""?(k=j.apply(a.async.dataFilter,[a.treeId,b,k],k),i.addNodes(a,b,-1,k?j.clone(k):[],!!c)):i.addNodes(a,b,-1,[],!!c);a.treeObj.trigger(f.event.ASYNC_SUCCESS,[a.treeId,b,h]);j.apply(d)}},error:function(c,d,h){if(P==e.getRoot(a)._ver){if(b)b.isAjaxing=null;i.setNodeLineIcos(a,b);a.treeObj.trigger(f.event.ASYNC_ERROR,[a.treeId,b,c,d,h])}}});
return!0},cancelPreSelectedNode:function(a,b,c){var d=e.getRoot(a).curSelectedList,g,h;for(g=d.length-1;g>=0;g--)if(h=d[g],b===h||!b&&(!c||c!==h))if(l(h,f.id.A,a).removeClass(f.node.CURSELECTED),b){e.removeSelectedNode(a,b);break}else d.splice(g,1),a.treeObj.trigger(f.event.UNSELECTED,[a.treeId,h])},createNodeCallback:function(a){if(a.callback.onNodeCreated||a.view.addDiyDom)for(var b=e.getRoot(a);b.createdNodes.length>0;){var c=b.createdNodes.shift();j.apply(a.view.addDiyDom,[a.treeId,c]);a.callback.onNodeCreated&&
a.treeObj.trigger(f.event.NODECREATED,[a.treeId,c])}},createNodes:function(a,b,c,d,g){if(c&&c.length!=0){var h=e.getRoot(a),k=!d||d.open||!!l(e.nodeChildren(a,d)[0],a).get(0);h.createdNodes=[];var b=i.appendNodes(a,b,c,d,g,!0,k),m,j;d?(d=l(d,f.id.UL,a),d.get(0)&&(m=d)):m=a.treeObj;m&&(g>=0&&(j=m.children()[g]),g>=0&&j?q(j).before(b.join("")):m.append(b.join("")));i.createNodeCallback(a)}},destroy:function(a){a&&(e.initCache(a),e.initRoot(a),n.unbindTree(a),n.unbindEvent(a),a.treeObj.empty(),delete s[a.treeId])},
expandCollapseNode:function(a,b,c,d,g){var h=e.getRoot(a),k;if(b){var m=e.nodeChildren(a,b),r=e.nodeIsParent(a,b);if(h.expandTriggerFlag)k=g,g=function(){k&&k();b.open?a.treeObj.trigger(f.event.EXPAND,[a.treeId,b]):a.treeObj.trigger(f.event.COLLAPSE,[a.treeId,b])},h.expandTriggerFlag=!1;if(!b.open&&r&&(!l(b,f.id.UL,a).get(0)||m&&m.length>0&&!l(m[0],a).get(0)))i.appendParentULDom(a,b),i.createNodeCallback(a);if(b.open==c)j.apply(g,[]);else{var c=l(b,f.id.UL,a),h=l(b,f.id.SWITCH,a),o=l(b,f.id.ICON,
a);r?(b.open=!b.open,b.iconOpen&&b.iconClose&&o.attr("style",i.makeNodeIcoStyle(a,b)),b.open?(i.replaceSwitchClass(b,h,f.folder.OPEN),i.replaceIcoClass(b,o,f.folder.OPEN),d==!1||a.view.expandSpeed==""?(c.show(),j.apply(g,[])):m&&m.length>0?c.slideDown(a.view.expandSpeed,g):(c.show(),j.apply(g,[]))):(i.replaceSwitchClass(b,h,f.folder.CLOSE),i.replaceIcoClass(b,o,f.folder.CLOSE),d==!1||a.view.expandSpeed==""||!(m&&m.length>0)?(c.hide(),j.apply(g,[])):c.slideUp(a.view.expandSpeed,g))):j.apply(g,[])}}else j.apply(g,
[])},expandCollapseParentNode:function(a,b,c,d,e){b&&(b.parentTId?(i.expandCollapseNode(a,b,c,d),b.parentTId&&i.expandCollapseParentNode(a,b.getParentNode(),c,d,e)):i.expandCollapseNode(a,b,c,d,e))},expandCollapseSonNode:function(a,b,c,d,g){var h=e.getRoot(a),h=b?e.nodeChildren(a,b):e.nodeChildren(a,h),k=b?!1:d,f=e.getRoot(a).expandTriggerFlag;e.getRoot(a).expandTriggerFlag=!1;if(h)for(var j=0,l=h.length;j<l;j++)h[j]&&i.expandCollapseSonNode(a,h[j],c,k);e.getRoot(a).expandTriggerFlag=f;i.expandCollapseNode(a,
b,c,d,g)},isSelectedNode:function(a,b){if(!b)return!1;var c=e.getRoot(a).curSelectedList,d;for(d=c.length-1;d>=0;d--)if(b===c[d])return!0;return!1},makeDOMNodeIcon:function(a,b,c){var d=e.nodeName(b,c),d=b.view.nameIsHTML?d:d.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");a.push("<span id='",c.tId,f.id.ICON,"' title='' treeNode",f.id.ICON," class='",i.makeNodeIcoClass(b,c),"' style='",i.makeNodeIcoStyle(b,c),"'></span><span id='",c.tId,f.id.SPAN,"' class='",f.className.NAME,"'>",
d,"</span>")},makeDOMNodeLine:function(a,b,c){a.push("<span id='",c.tId,f.id.SWITCH,"' title='' class='",i.makeNodeLineClass(b,c),"' treeNode",f.id.SWITCH,"></span>")},makeDOMNodeMainAfter:function(a){a.push("</li>")},makeDOMNodeMainBefore:function(a,b,c){a.push("<li id='",c.tId,"' class='",f.className.LEVEL,c.level,"' tabindex='0' hidefocus='true' treenode>")},makeDOMNodeNameAfter:function(a){a.push("</a>")},makeDOMNodeNameBefore:function(a,b,c){var d=e.nodeTitle(b,c),g=i.makeNodeUrl(b,c),h=i.makeNodeFontCss(b,
c),k=[],m;for(m in h)k.push(m,":",h[m],";");a.push("<a id='",c.tId,f.id.A,"' class='",f.className.LEVEL,c.level,"' treeNode",f.id.A,' onclick="',c.click||"",'" ',g!=null&&g.length>0?"href='"+g+"'":""," target='",i.makeNodeTarget(c),"' style='",k.join(""),"'");j.apply(b.view.showTitle,[b.treeId,c],b.view.showTitle)&&d&&a.push("title='",d.replace(/'/g,"&#39;").replace(/</g,"&lt;").replace(/>/g,"&gt;"),"'");a.push(">")},makeNodeFontCss:function(a,b){var c=j.apply(a.view.fontCss,[a.treeId,b],a.view.fontCss);
return c&&typeof c!="function"?c:{}},makeNodeIcoClass:function(a,b){var c=["ico"];if(!b.isAjaxing){var d=e.nodeIsParent(a,b);c[0]=(b.iconSkin?b.iconSkin+"_":"")+c[0];d?c.push(b.open?f.folder.OPEN:f.folder.CLOSE):c.push(f.folder.DOCU)}return f.className.BUTTON+" "+c.join("_")},makeNodeIcoStyle:function(a,b){var c=[];if(!b.isAjaxing){var d=e.nodeIsParent(a,b)&&b.iconOpen&&b.iconClose?b.open?b.iconOpen:b.iconClose:b[a.data.key.icon];d&&c.push("background:url(",d,") 0 0 no-repeat;");(a.view.showIcon==
!1||!j.apply(a.view.showIcon,[a.treeId,b],!0))&&c.push("width:0px;height:0px;")}return c.join("")},makeNodeLineClass:function(a,b){var c=[];a.view.showLine?b.level==0&&b.isFirstNode&&b.isLastNode?c.push(f.line.ROOT):b.level==0&&b.isFirstNode?c.push(f.line.ROOTS):b.isLastNode?c.push(f.line.BOTTOM):c.push(f.line.CENTER):c.push(f.line.NOLINE);e.nodeIsParent(a,b)?c.push(b.open?f.folder.OPEN:f.folder.CLOSE):c.push(f.folder.DOCU);return i.makeNodeLineClassEx(b)+c.join("_")},makeNodeLineClassEx:function(a){return f.className.BUTTON+
" "+f.className.LEVEL+a.level+" "+f.className.SWITCH+" "},makeNodeTarget:function(a){return a.target||"_blank"},makeNodeUrl:function(a,b){var c=a.data.key.url;return b[c]?b[c]:null},makeUlHtml:function(a,b,c,d){c.push("<ul id='",b.tId,f.id.UL,"' class='",f.className.LEVEL,b.level," ",i.makeUlLineClass(a,b),"' style='display:",b.open?"block":"none","'>");c.push(d);c.push("</ul>")},makeUlLineClass:function(a,b){return a.view.showLine&&!b.isLastNode?f.line.LINE:""},removeChildNodes:function(a,b){if(b){var c=
e.nodeChildren(a,b);if(c){for(var d=0,g=c.length;d<g;d++)e.removeNodeCache(a,c[d]);e.removeSelectedNode(a);delete b[a.data.key.children];a.data.keep.parent?l(b,f.id.UL,a).empty():(e.nodeIsParent(a,b,!1),b.open=!1,c=l(b,f.id.SWITCH,a),d=l(b,f.id.ICON,a),i.replaceSwitchClass(b,c,f.folder.DOCU),i.replaceIcoClass(b,d,f.folder.DOCU),l(b,f.id.UL,a).remove())}}},scrollIntoView:function(a,b){if(b)if(typeof Element==="undefined"){var c=a.treeObj.get(0).getBoundingClientRect(),d=b.getBoundingClientRect();(d.top<
c.top||d.bottom>c.bottom||d.right>c.right||d.left<c.left)&&b.scrollIntoView()}else{if(!Element.prototype.scrollIntoViewIfNeeded)Element.prototype.scrollIntoViewIfNeeded=function(a){function b(a,c,d,f){return{left:a,top:c,width:d,height:f,right:a+d,bottom:c+f,translate:function(e,g){return b(e+a,g+c,d,f)},relativeFromTo:function(g,k){var i=a,j=c,g=g.offsetParent,k=k.offsetParent;if(g===k)return e;for(;g;g=g.offsetParent)i+=g.offsetLeft+g.clientLeft,j+=g.offsetTop+g.clientTop;for(;k;k=k.offsetParent)i-=
k.offsetLeft+k.clientLeft,j-=k.offsetTop+k.clientTop;return b(i,j,d,f)}}}for(var c,d=this,e=b(this.offsetLeft,this.offsetTop,this.offsetWidth,this.offsetHeight);j.isElement(c=d.parentNode);){var f=c.offsetLeft+c.clientLeft,i=c.offsetTop+c.clientTop,e=e.relativeFromTo(d,c).translate(-f,-i);c.scrollLeft=!1===a||e.left<=c.scrollLeft+c.clientWidth&&c.scrollLeft<=e.right-c.clientWidth+c.clientWidth?Math.min(e.left,Math.max(e.right-c.clientWidth,c.scrollLeft)):(e.right-c.clientWidth+e.left)/2;c.scrollTop=
!1===a||e.top<=c.scrollTop+c.clientHeight&&c.scrollTop<=e.bottom-c.clientHeight+c.clientHeight?Math.min(e.top,Math.max(e.bottom-c.clientHeight,c.scrollTop)):(e.bottom-c.clientHeight+e.top)/2;e=e.translate(f-c.scrollLeft,i-c.scrollTop);d=c}};b.scrollIntoViewIfNeeded()}},setFirstNode:function(a,b){var c=e.nodeChildren(a,b);if(c.length>0)c[0].isFirstNode=!0},setLastNode:function(a,b){var c=e.nodeChildren(a,b);if(c.length>0)c[c.length-1].isLastNode=!0},removeNode:function(a,b){var c=e.getRoot(a),d=b.parentTId?
b.getParentNode():c;b.isFirstNode=!1;b.isLastNode=!1;b.getPreNode=function(){return null};b.getNextNode=function(){return null};if(e.getNodeCache(a,b.tId)){l(b,a).remove();e.removeNodeCache(a,b);e.removeSelectedNode(a,b);for(var g=e.nodeChildren(a,d),h=0,k=g.length;h<k;h++)if(g[h].tId==b.tId){g.splice(h,1);break}i.setFirstNode(a,d);i.setLastNode(a,d);var j,h=g.length;if(!a.data.keep.parent&&h==0)e.nodeIsParent(a,d,!1),d.open=!1,delete d[a.data.key.children],h=l(d,f.id.UL,a),k=l(d,f.id.SWITCH,a),j=
l(d,f.id.ICON,a),i.replaceSwitchClass(d,k,f.folder.DOCU),i.replaceIcoClass(d,j,f.folder.DOCU),h.css("display","none");else if(a.view.showLine&&h>0){var r=g[h-1],h=l(r,f.id.UL,a),k=l(r,f.id.SWITCH,a);j=l(r,f.id.ICON,a);d==c?g.length==1?i.replaceSwitchClass(r,k,f.line.ROOT):(c=l(g[0],f.id.SWITCH,a),i.replaceSwitchClass(g[0],c,f.line.ROOTS),i.replaceSwitchClass(r,k,f.line.BOTTOM)):i.replaceSwitchClass(r,k,f.line.BOTTOM);h.removeClass(f.line.LINE)}}},replaceIcoClass:function(a,b,c){if(b&&!a.isAjaxing&&
(a=b.attr("class"),a!=void 0)){a=a.split("_");switch(c){case f.folder.OPEN:case f.folder.CLOSE:case f.folder.DOCU:a[a.length-1]=c}b.attr("class",a.join("_"))}},replaceSwitchClass:function(a,b,c){if(b){var d=b.attr("class");if(d!=void 0){d=d.split("_");switch(c){case f.line.ROOT:case f.line.ROOTS:case f.line.CENTER:case f.line.BOTTOM:case f.line.NOLINE:d[0]=i.makeNodeLineClassEx(a)+c;break;case f.folder.OPEN:case f.folder.CLOSE:case f.folder.DOCU:d[1]=c}b.attr("class",d.join("_"));c!==f.folder.DOCU?
b.removeAttr("disabled"):b.attr("disabled","disabled")}}},selectNode:function(a,b,c){c||i.cancelPreSelectedNode(a,null,b);l(b,f.id.A,a).addClass(f.node.CURSELECTED);e.addSelectedNode(a,b);a.treeObj.trigger(f.event.SELECTED,[a.treeId,b])},setNodeFontCss:function(a,b){var c=l(b,f.id.A,a),d=i.makeNodeFontCss(a,b);d&&c.css(d)},setNodeLineIcos:function(a,b){if(b){var c=l(b,f.id.SWITCH,a),d=l(b,f.id.UL,a),g=l(b,f.id.ICON,a),h=i.makeUlLineClass(a,b);h.length==0?d.removeClass(f.line.LINE):d.addClass(h);c.attr("class",
i.makeNodeLineClass(a,b));e.nodeIsParent(a,b)?c.removeAttr("disabled"):c.attr("disabled","disabled");g.removeAttr("style");g.attr("style",i.makeNodeIcoStyle(a,b));g.attr("class",i.makeNodeIcoClass(a,b))}},setNodeName:function(a,b){var c=e.nodeTitle(a,b),d=l(b,f.id.SPAN,a);d.empty();a.view.nameIsHTML?d.html(e.nodeName(a,b)):d.text(e.nodeName(a,b));j.apply(a.view.showTitle,[a.treeId,b],a.view.showTitle)&&l(b,f.id.A,a).attr("title",!c?"":c)},setNodeTarget:function(a,b){l(b,f.id.A,a).attr("target",i.makeNodeTarget(b))},
setNodeUrl:function(a,b){var c=l(b,f.id.A,a),d=i.makeNodeUrl(a,b);d==null||d.length==0?c.removeAttr("href"):c.attr("href",d)},switchNode:function(a,b){b.open||!j.canAsync(a,b)?i.expandCollapseNode(a,b,!b.open):a.async.enable?i.asyncNode(a,b)||i.expandCollapseNode(a,b,!b.open):b&&i.expandCollapseNode(a,b,!b.open)}};q.fn.zTree={consts:{className:{BUTTON:"button",LEVEL:"level",ICO_LOADING:"ico_loading",SWITCH:"switch",NAME:"node_name"},event:{NODECREATED:"ztree_nodeCreated",CLICK:"ztree_click",EXPAND:"ztree_expand",
COLLAPSE:"ztree_collapse",ASYNC_SUCCESS:"ztree_async_success",ASYNC_ERROR:"ztree_async_error",REMOVE:"ztree_remove",SELECTED:"ztree_selected",UNSELECTED:"ztree_unselected"},id:{A:"_a",ICON:"_ico",SPAN:"_span",SWITCH:"_switch",UL:"_ul"},line:{ROOT:"root",ROOTS:"roots",CENTER:"center",BOTTOM:"bottom",NOLINE:"noline",LINE:"line"},folder:{OPEN:"open",CLOSE:"close",DOCU:"docu"},node:{CURSELECTED:"curSelectedNode"}},_z:{tools:j,view:i,event:n,data:e},getZTreeObj:function(a){return(a=e.getZTreeTools(a))?
a:null},destroy:function(a){if(a&&a.length>0)i.destroy(e.getSetting(a));else for(var b in s)i.destroy(s[b])},init:function(a,b,c){var d=j.clone(N);q.extend(!0,d,b);d.treeId=a.attr("id");d.treeObj=a;d.treeObj.empty();s[d.treeId]=d;if(typeof document.body.style.maxHeight==="undefined")d.view.expandSpeed="";e.initRoot(d);a=e.getRoot(d);c=c?j.clone(j.isArray(c)?c:[c]):[];d.data.simpleData.enable?e.nodeChildren(d,a,e.transformTozTreeFormat(d,c)):e.nodeChildren(d,a,c);e.initCache(d);n.unbindTree(d);n.bindTree(d);
n.unbindEvent(d);n.bindEvent(d);var g={setting:d,addNodes:function(a,b,c,g){function f(){i.addNodes(d,a,b,n,g==!0)}a||(a=null);var l=e.nodeIsParent(d,a);if(a&&!l&&d.data.keep.leaf)return null;l=parseInt(b,10);isNaN(l)?(g=!!c,c=b,b=-1):b=l;if(!c)return null;var n=j.clone(j.isArray(c)?c:[c]);j.canAsync(d,a)?i.asyncNode(d,a,g,f):f();return n},cancelSelectedNode:function(a){i.cancelPreSelectedNode(d,a)},destroy:function(){i.destroy(d)},expandAll:function(a){a=!!a;i.expandCollapseSonNode(d,null,a,!0);
return a},expandNode:function(a,b,c,g,f){function n(){var b=l(a,d).get(0);b&&g!==!1&&i.scrollIntoView(d,b)}if(!a||!e.nodeIsParent(d,a))return null;b!==!0&&b!==!1&&(b=!a.open);if((f=!!f)&&b&&j.apply(d.callback.beforeExpand,[d.treeId,a],!0)==!1)return null;else if(f&&!b&&j.apply(d.callback.beforeCollapse,[d.treeId,a],!0)==!1)return null;b&&a.parentTId&&i.expandCollapseParentNode(d,a.getParentNode(),b,!1);if(b===a.open&&!c)return null;e.getRoot(d).expandTriggerFlag=f;!j.canAsync(d,a)&&c?i.expandCollapseSonNode(d,
a,b,!0,n):(a.open=!b,i.switchNode(this.setting,a),n());return b},getNodes:function(){return e.getNodes(d)},getNodeByParam:function(a,b,c){return!a?null:e.getNodeByParam(d,c?e.nodeChildren(d,c):e.getNodes(d),a,b)},getNodeByTId:function(a){return e.getNodeCache(d,a)},getNodesByParam:function(a,b,c){return!a?null:e.getNodesByParam(d,c?e.nodeChildren(d,c):e.getNodes(d),a,b)},getNodesByParamFuzzy:function(a,b,c){return!a?null:e.getNodesByParamFuzzy(d,c?e.nodeChildren(d,c):e.getNodes(d),a,b)},getNodesByFilter:function(a,
b,c,f){b=!!b;return!a||typeof a!="function"?b?null:[]:e.getNodesByFilter(d,c?e.nodeChildren(d,c):e.getNodes(d),a,b,f)},getNodeIndex:function(a){if(!a)return null;for(var b=a.parentTId?a.getParentNode():e.getRoot(d),b=e.nodeChildren(d,b),c=0,f=b.length;c<f;c++)if(b[c]==a)return c;return-1},getSelectedNodes:function(){for(var a=[],b=e.getRoot(d).curSelectedList,c=0,f=b.length;c<f;c++)a.push(b[c]);return a},isSelectedNode:function(a){return e.isSelectedNode(d,a)},reAsyncChildNodesPromise:function(a,
b,c){return new Promise(function(d,e){try{g.reAsyncChildNodes(a,b,c,function(){d(a)})}catch(f){e(f)}})},reAsyncChildNodes:function(a,b,c,g){if(this.setting.async.enable){var j=!a;j&&(a=e.getRoot(d));if(b=="refresh"){for(var b=e.nodeChildren(d,a),n=0,q=b?b.length:0;n<q;n++)e.removeNodeCache(d,b[n]);e.removeSelectedNode(d);e.nodeChildren(d,a,[]);j?this.setting.treeObj.empty():l(a,f.id.UL,d).empty()}i.asyncNode(this.setting,j?null:a,!!c,g)}},refresh:function(){this.setting.treeObj.empty();var a=e.getRoot(d),
b=e.nodeChildren(d,a);e.initRoot(d);e.nodeChildren(d,a,b);e.initCache(d);i.createNodes(d,0,e.nodeChildren(d,a),null,-1)},removeChildNodes:function(a){if(!a)return null;var b=e.nodeChildren(d,a);i.removeChildNodes(d,a);return b?b:null},removeNode:function(a,b){a&&(b=!!b,b&&j.apply(d.callback.beforeRemove,[d.treeId,a],!0)==!1||(i.removeNode(d,a),b&&this.setting.treeObj.trigger(f.event.REMOVE,[d.treeId,a])))},selectNode:function(a,b,c){function e(){if(!c){var b=l(a,d).get(0);i.scrollIntoView(d,b)}}if(a&&
j.uCanDo(d)){b=d.view.selectedMulti&&b;if(a.parentTId)i.expandCollapseParentNode(d,a.getParentNode(),!0,!1,e);else if(!c)try{l(a,d).focus().blur()}catch(f){}i.selectNode(d,a,b)}},transformTozTreeNodes:function(a){return e.transformTozTreeFormat(d,a)},transformToArray:function(a){return e.transformToArrayFormat(d,a)},updateNode:function(a){a&&l(a,d).get(0)&&j.uCanDo(d)&&(i.setNodeName(d,a),i.setNodeTarget(d,a),i.setNodeUrl(d,a),i.setNodeLineIcos(d,a),i.setNodeFontCss(d,a))}};a.treeTools=g;e.setZTreeTools(d,
g);(c=e.nodeChildren(d,a))&&c.length>0?i.createNodes(d,0,c,null,-1):d.async.enable&&d.async.url&&d.async.url!==""&&i.asyncNode(d);return g}};var O=q.fn.zTree,l=j.$,f=O.consts})(jQuery);