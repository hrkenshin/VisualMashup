<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<%String pageId = request.getParameter("pageId"); %>
<head>
    <title>Visual Mashup</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

    <!-- jQuery -->
    <script type="text/javascript" src="/resource/lib/jquery1.6.2/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="/resource/lib/jquery1.6.2/plugin/jquery.ba-resize.min.js"></script>

    <!-- WIreIt : dependency (YUI, Excanvas)  -->
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/lib/yui/utilities/utilities.js"></script>
    <!--[if IE]>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/lib/excanvas.js"></script><![endif]-->
    <link rel="stylesheet" type="text/css" href="/resource/lib/WireIt-0.6.0a/assets/WireIt.css"/>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/WireIt.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/CanvasElement.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/Wire.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/TerminalProxy.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/Terminal.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/Scissors.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/BezierWire.js"></script>
    <script type="text/javascript" src="/resource/lib/WireIt-0.6.0a/js/BezierArrowWire.js"></script>

    <!-- ExtJS -->
    <link rel="stylesheet" type="text/css" href="/resource/lib/ext-4.0.7-gpl/resources/css/ext-all.css"/>
    <script type="text/javascript" src="/resource/lib/ext-4.0.7-gpl/bootstrap.js"></script>
    <script type="text/javascript" src="/resource/lib/ext-4.0.7-gpl/locale/ext-lang-ko.js"></script>

    <!-- ExtJS : UX -->
    <link rel="stylesheet" type="text/css" href="/resource/lib/ext-4.0.7-gpl/examples/ux/grid/css/GridFilters.css"/>
    <link rel="stylesheet" type="text/css" href="/resource/lib/ext-4.0.7-gpl/examples/ux/grid/css/RangeMenu.css"/>

    <!-- Shindig -->
    <link rel="stylesheet" type="text/css" href="/resource/css/gadgets.css"/>
    <script type="text/javascript" src="/gadgets/js/shindig-container:pubsub-2.js?c=1&debug=1"></script>
    <!--<script type="text/javascript" src="/resource/js/shindig-container_pubsub-2.js"></script>-->
    <script type="text/javascript" src="/resource/js/opensocial/shindig_override.js"></script>

    <!-- ExtJS : Portal -->
    <link rel="stylesheet" type="text/css" href="/resource/css/portal.css"/>

    <script type="text/javascript">
        var VM_SESSION = {
            BASE_CONTEXT: ''
        };

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
            'VM.opensocial.UploadForm',
            'VM.opensocial.SavePage'
        ]);

        // 페이지 메타데이터 예)
        
        var pageJSON1 = {
            "@id":"gadgetcontainer-1018",
            "@title":"테스트페이지",
            "@layoutXType":"layout_3_columns",
            "@description":"",
            "layout":[
                {
                    "portlet":[
                        {
                            "@id":"gadget-1051",
                            "@xtype":"gadget",
                            "initConfig":"{\"xtype\":\"gadget\",\"specUrl\":\"http://127.0.0.1:8080/opensocial/gadgets/20111029231237360001/pubsub-2-publisher.xml\",\"view\":\"home\"}"
                        }
                    ]
                },
                {
                    "portlet":[
                        {
                            "@id":"gadget-1073",
                            "@xtype":"gadget",
                            "initConfig":"{\"xtype\":\"gadget\",\"specUrl\":\"http://127.0.0.1:8080/opensocial/gadgets/20111030001648833000/pubsub-2-subscriber.xml\",\"view\":\"home\"}"
                        }
                    ]
                },
                {
                    "portlet":[]
                }
            ],
            "pipeline":{
               "wire":[
                    {
                        "from":{
                            "@gadgetId":"gadget-1051",
                            "@topicName":"org.apache.shindig.out.message.1051"
                        },
                        "to":{
                            "@gadgetId":"gadget-1073",
                            "@topicName":"org.apache.shindig.in.message2.1073"
                        },
                        "transformer":{
                            "type":"string",
                            "operator":"bypass",
                            "param":null
                        }
                    }
                ]
            }
        };
        
        var pageJSON2 = {"@id":"gadgetcontainer-1017","@title":"\ud14c\uc2a4\ud2b8\ud398\uc774\uc9c0","@layoutXType":"layout_3_columns","@description":"","layout":[{"portlet":[{"@id":"gadget-1085","@xtype":"gadget","initConfig":"{\"xtype\":\"gadget\",\"specUrl\":\"http://localhost:8080/opensocial/gadgets/20111109194000269000/_transformer.xml\",\"view\":\"home\"}"}]},{"portlet":[{"@id":"gadget-1096","@xtype":"gadget","initConfig":"{\"xtype\":\"gadget\",\"specUrl\":\"http://localhost:8080/opensocial/gadgets/20111205170356546000/helloworld.xml\",\"view\":\"home\"}"}]},{"portlet":[]}],"pipeline":{"wire":[]}};

        Ext.onReady(function() {
            Ext.QuickTips.init();
            var pageId = '<%=pageId%>';

            var gadgetContainer = Ext.create('VM.opensocial.GadgetContainer', {
                title: '테스트페이지',
                flex: 1,
                listeners: {
                    afterrender: function(panel, option) {
                        // 서버에서 page json data 생성
                        $.ajax({
                                type: 'post'
                                , dataType: 'json'
                                , async: true
                                , url: '/uengine-web/readVMPageJson.jsp'
                                , data: {"pageId":pageId}                        		
                                , success: function(data) {
                                	var pageJSON = data;
                                	gadgetContainer.loadPage(pageJSON);
                                  }
                                , error: function(data, status, err) {
                                	console.log("error forward : "+data);
                                	alert(pageId);
                                    alert('서버와의 통신이 실패했습니다.');
                                  }
                            });
                    }
                }
            });

            Ext.create('Ext.container.Viewport', {
                layout: {
                    type: 'fit'
                },
                items: gadgetContainer
            });
        });
    </script>
</head>
<body>
<input name="pageMetaData" id="pageMetaData" type="hidden" value=""/>
</body>
</html>