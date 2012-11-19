/**
 * @class VM.layout.LY_1_2_Columns_1
 * @extends VM.layout._Layout
 *
 * 포틀릿 레이아웃 타입(1-2 Columns (1-2 Columns (30:70)) 정의 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout.LY_1_2_Columns_1', {
    extend: 'VM.layout._Layout',
    alias: 'widget.layout_1_2_columns_1',

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
                            columnWidth: .3
                        },
                        {
                            id: layoutId + '_3',
                            columnWidth: .7
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

        this.adjustMarginBottom(portalcolumn1);
    }
});