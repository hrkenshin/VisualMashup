<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<title>Visual Mashup</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

	<!-- Include VM Common javascript(Ext-JS) -->
	<jsp:include page="/WEB-INF/jsp/common/_vm_session.jsp" flush="true"/>
	<jsp:include page="/WEB-INF/jsp/common/_vm_script.jsp" flush="true"/>

	<script type="text/javascript">
		Ext.Loader.setConfig({enabled: true, disableCaching: true});
		Ext.Loader.setPath('Ext.ux', '/resource/lib/ext-4.0.7-gpl/examples/ux');
		Ext.Loader.setPath('Ext.app', '/resource/js/portal');
		Ext.Loader.setPath('VM.layout', '/resource/js/layout');
		Ext.Loader.setPath('VM.opensocial', '/resource/js/opensocial');

		Ext.require([
			'Ext.ux.grid.FiltersFeature',
			'Ext.ux.form.SearchField',
			'Ext.app.*',
			'VM.layout._Layout',
			'VM.layout.LY_1_2_1_Columns',
			'VM.layout.LY_1_2_Columns_1',
			'VM.layout.LY_1_2_Columns_2',
			'VM.layout.LY_1_Column',
			'VM.layout.LY_2_2_Columns',
			'VM.layout.LY_2_Columns_1',
			'VM.layout.LY_2_Columns_2',
			'VM.layout.LY_2_Columns_3',
			'VM.layout.LY_3_Columns',
			'VM.layout.PortletContainer',
			'VM.opensocial.common.Properties',
			'VM.opensocial.common.CustomFilters',
			'VM.opensocial.common.CustomSearchField',
			'VM.opensocial.common.RESTProxy',
			'VM.opensocial.model.VmGadgets',
			'VM.opensocial.Gadget',
			'VM.opensocial.GadgetContainer',
			'VM.opensocial.GadgetListGrid',
			'VM.opensocial.UploadForm'
		]);

		Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.create('Ext.container.Viewport', {
				layout: {
					type: 'fit'
				},
				items: [
					{
						xtype: 'gadgetcontainer',
						title: '테스트 페이지',
						flex: 1
					}
				]
			});

			//            Ext.create('VM.opensocial.GadgetContainer', {
			//                title: '테스트페이지',
			//                renderTo: 'page',
			//                border: true
			//            });
		});
	</script>
</head>
<body>
<div id="page"></div>
</body>
</html>