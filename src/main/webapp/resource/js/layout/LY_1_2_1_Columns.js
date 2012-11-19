/**
 * @class VM.layout.LY_1_2_1_Columns
 * @extends VM.layout._Layout
 *
 * 포틀릿 레이아웃 타입(1-2-1 Columns) 정의 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout.LY_1_2_1_Columns', {
    extend: 'VM.layout._Layout',
    alias: 'widget.layout_1_2_1_columns',

    /**
     * @private
     * @override
     */
    initComponent : function() {
        var layoutId = this.getId();

        Ext.apply(this, {
            border: false,
            items: [
                {
                    xtype: 'portalpanel',
                    items: [
                        {
                            id: layoutId + '_1',
                            columnWidth: 1
                        }
                    ],
                    listeners: {
                        dragenter: function(event) {
                            if (event.column.getXType() == 'portalcolumn') {
                                this.resetMarginBottom(event.column);
                            }
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'portalpanel',
                    items: [
                        {
                            id: layoutId + '_2',
                            columnWidth: .5
                        },
                        {
                            id: layoutId + '_3',
                            columnWidth: .5
                        }
                    ],
                    listeners: {
                        dragenter: function(event) {
                            if (event.column.getXType() == 'portalcolumn') {
                                this.resetMarginBottom(event.column);
                            }
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'portalpanel',
                    items: [
                        {
                            id: layoutId + '_4',
                            columnWidth: 1
                        }
                    ]
                }
            ]
        });

        this.callParent();
    },

    /**
     * @override
     * PortalPanel 의 모든 PortalColumn 내에 있는, 맨 아래 포틀릿의 margin-bottom 을 0으로 한다.
     */
    adjustMarginBottomAll: function() {
        var portalcolumn1 = Ext.getCmp(this.getId() + '_1');
        var portalcolumn2 = Ext.getCmp(this.getId() + '_2');
        var portalcolumn3 = Ext.getCmp(this.getId() + '_3');

        this.adjustMarginBottom(portalcolumn1);
        this.adjustMarginBottom(portalcolumn2);
        this.adjustMarginBottom(portalcolumn3);
    }
});