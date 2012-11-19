/**
 * @class VM.layout.LY_2_Columns_2
 * @extends VM.layout._Layout
 *
 * 포틀릿 레이아웃 타입(2 Columns(30:70)) 정의 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout.LY_2_Columns_2', {
    extend: 'VM.layout._Layout',
    alias: 'widget.layout_2_columns_2',

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
                            columnWidth: .3
                        },
                        {
                            id: layoutId + '_2',
                            columnWidth: .7
                        }
                    ]
                }
            ]
        });

        this.callParent();
    }
});