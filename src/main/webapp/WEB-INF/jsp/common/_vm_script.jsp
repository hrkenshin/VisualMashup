<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/_vm_session.jsp" flush="true"/>
<!-- Shindig -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/gadgets.css"/>"/>
<script type="text/javascript" src="<c:url value="/gadgets/js/shindig-container:pubsub-2.js?c=1&debug=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/opensocial/shindig_override.js"/>"></script>

<!-- jQuery -->
<script type="text/javascript" src="<c:url value="/resource/lib/jquery1.6.2/jquery-1.6.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/jquery1.6.2/plugin/jquery.ba-resize.min.js"/>"></script>

<!-- WIreIt : dependency (YUI, Excanvas) -->
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/lib/yui/utilities/utilities.js"/>"></script>
<!--[if IE]><script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/lib/excanvas.js"/>"></script><![endif]-->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/lib/WireIt-0.6.0a/assets/WireIt.css"/>"/>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/WireIt.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/CanvasElement.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/Wire.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/TerminalProxy.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/Terminal.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/Scissors.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/BezierWire.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/WireIt-0.6.0a/js/BezierArrowWire.js"/>"></script>

<!-- ExtJS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/lib/ext-4.0.7-gpl/resources/css/ext-all.css"/>"/>
<script type="text/javascript" src="<c:url value="/resource/lib/ext-4.0.7-gpl/bootstrap.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/lib/ext-4.0.7-gpl/locale/ext-lang-ko.js"/>"></script>

<!-- ExtJS : UX -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/lib/ext-4.0.7-gpl/examples/ux/grid/css/GridFilters.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/lib/ext-4.0.7-gpl/examples/ux/grid/css/RangeMenu.css"/>"/>

<!-- ExtJS : Portal -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/portal.css"/>"/>