/**
 * @class VM.layout.LY_1_Column
 * @extends VM.layout._Layout
 *
 * 포틀릿 레이아웃 타입(1 Column) 정의 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout.LY_1_Column', {
    extend: 'VM.layout._Layout',
    alias: 'widget.layout_1_column',

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
                    ]
                }
            ]
        });

        this.callParent();
    },

    /**
     * @override
     * 자동 순차적 배열하여 포틀릿을 추가한다. 레이아웃 타입 변경시 포틀릿을 자동배치한다.
     *
     * @param {Array<Ext.app.Portlet>} portlets 포틀릿목록
     */
    addPortletsByAutoArrange: function(portlets) {
        var portalcolumn = Ext.getCmp(this.getId() + '_1');

        for (var i = 0; i < portlets.length; i++) {
            portalcolumn.add(Ext.apply(portlets[i].initialConfig, {xtype:portlets[i].getXType()}));
        }
    }
});