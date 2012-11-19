/**
 * @class VM.layout._Layout
 * @extends Ext.panel.Panel
 *
 * 포틀릿 레이아웃 타입 정의 추상 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout._Layout', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.layout',

    bodyStyle: 'overflow-x:hidden; overflow-y:auto',

    requires: [
        'Ext.app.Portlet',
        'Ext.app.PortalColumn',
        'Ext.app.PortalPanel',
        'Ext.app.PortalDropZone'
    ],

    /**
     * @private
     * @override
     */
    initComponent : function() {
        this.callParent();

        this.on('add', function(panel, item, index, option) {
            if (panel.getXType() == 'portalcolumn') {
                this.adjustMarginBottomAll();
            }
        }, this);
        this.on('remove', function(panel, item, index, option) {
            if (panel.getXType() == 'portalcolumn') {
                this.adjustMarginBottomAll();
            }
        }, this);
    },

    /**
     * @abstract
     * PortalPanel 의 모든 PortalColumn 내에 있는, 맨 아래 포틀릿의 margin-bottom 을 0으로 한다.
     */
    adjustMarginBottomAll: Ext.emptyFn,

    /**
     * @protected
     * PortalPanel 을 조합한 Layout 인 경우 각 PortalColumn 의 맨 아래 포틀릿의 margin-bottom 을 0으로 한다.
     * 포틀릿간의 간격을 일정하게 조절 하기 위해 사용한다.
     *
     * @param {Ext.app.PortalColumn} portalColumn 포털컬럼
     */
    adjustMarginBottom: function(portalColumn) {
        var portlets = portalColumn.items;

        for (var i = 0; i < portlets.getCount(); i++) {
            var portlet = portlets.getAt(i);
            if (portlet.getEl()) {
                if (i < portlets.getCount() - 1) {
                    portlet.getEl().setStyle('margin-bottom', '10px');
                } else {
                    portlet.getEl().setStyle('margin-bottom', 0);
                }
            }
        }
    },

    /**
     * @protected
     * 조절된 포틀릿의 margin-bottom 을 원래값으로 한다.
     * 포틀릿을 PortalPanel에 dragover 했을때 실행한다.
     *
     * @param {Ext.app.PortalColumn} portalColumn 포털컬럼
     */
    resetMarginBottom: function(portalColumn) {
        var portlets = portalColumn.items;

        for (var i = 0; i < portlets.getCount(); i++) {
            var portlet = portlets.getAt(i);
            portlet.getEl().setStyle('margin-bottom', '10px');
        }
    },

    /**
     * @protected
     * 포틀릿을 추가할 대상이 되는 PortalColumn 을 반환한다.
     * 가장 적게 포틀릿을 가지고 있는 컬럼을 반환한다.
     *
     * @return {Ext.app.PortalColumn} portalColumn 포털컬럼
     */
    getTargetColumn: function() {
        var portalColumns = Ext.ComponentQuery.query('portalcolumn', this);

        var targetColumn = null;
        for (var i = 0; i < portalColumns.length; i++) {
            if (targetColumn) {
                if (portalColumns[i].items.length < targetColumn.items.length) {
                    targetColumn = portalColumns[i];
                }
            } else {
                targetColumn = portalColumns[i];
            }
        }

        return targetColumn;
    },

    /**
     * 레이아웃 컨테이너에 포틀릿을 추가한다.
     *
     * @param {Ext.app.Portlet} portlet 포틀릿
     */
    addPortlet: function(portlet) {
        this.getTargetColumn().add(portlet);
    },

    /**
     * 레이아웃 컨테이너에 있는 포틀릿 목록을 반환한다.
     *
     * @return {Array<Ext.app.Portlet>} 포틀릿목록
     */
    getPortlets: function() {
        return Ext.ComponentQuery.query('portlet', this);
    },

    /**
     * 레이아웃을 구성하는 포틀릿과 포틀릿 배치 정보의 메타데이타를 반환한다.
     * 저장된 레이아웃을 다시 로드 하기 위한 정보를 제공한다.
     *
     * @return {JSONObject} layoutMetaData
     */
    getLayoutMetaData: function() {
        var portalColumns = Ext.ComponentQuery.query('portalcolumn', this);
        var COLUMN_CNT = portalColumns.length;

        var layoutMetaData = new Array();
        var portlets, portletArray;
        for (var i = 0; i < COLUMN_CNT; i++) {
            portlets = Ext.ComponentQuery.query('portlet', portalColumns[i]);

            portletArray = new Array();
            for (var j = 0; j < portlets.length; j++) {
                var obj = portlets[j];
                portletArray.push({
                    '@id': obj.getId(),
                    '@xtype' : obj.getXType(),
                    initConfig: Ext.JSON.encode(obj.initialConfig)
                });
            }

            layoutMetaData.push({
                portlet: portletArray
            });
        }

        return layoutMetaData;
    },

    /**
     * 주어진 레이아웃 메타데이타로 레이아웃을 새로 구성한다.
     *
     * @param {JSONObject} layoutMetaData 레이아웃 메타데이타
     */
    loadLayout: function(layoutMetaData) {
        var portalColumns = Ext.ComponentQuery.query('portalcolumn', this);
        var COLUMN_CNT = portalColumns.length;
        var layout, portlet;

        this.suspendEvents();
        if (layoutMetaData.length == COLUMN_CNT) {
            for (var i = 0; i < layoutMetaData.length; i++) {
                layout = layoutMetaData[i];
                for (var j = 0; j < layout.portlet.length; j++) {
                    portlet = layout.portlet[j];
                    portalColumns[i].add(
                        Ext.apply(Ext.JSON.decode(portlet.initConfig), {id:portlet['@id'], xtype: portlet['@xtype']})
                    );
                }
            }
        } else {
            var count = 0;
            for (var i = 0; i < layoutMetaData.length; i++) {
                layout = layoutMetaData[i];
                for (var j = 0; j < layout.portlet.length; j++) {
                    portlet = layout.portlet[j];
                    portalColumns[count - Math.floor(count / COLUMN_CNT) * COLUMN_CNT].add(
                        Ext.apply(Ext.JSON.decode(portlet.initConfig), {id:portlet['@id'], xtype: portlet['@xtype']})
                    );

                    count++;
                }
            }
        }
        this.resumeEvents();

        this.adjustMarginBottomAll();
    }
});