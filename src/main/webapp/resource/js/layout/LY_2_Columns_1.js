/**
 * @class VM.layout.LY_2_Columns_1
 * @extends VM.layout._Layout
 *
 * 포틀릿 레이아웃 타입(2 Columns(50:50)) 정의 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout.LY_2_Columns_1', {
    extend: 'VM.layout._Layout',
    alias: 'widget.layout_2_columns_1',

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
                            columnWidth: .5
                        },
                        {
                            id: layoutId + '_2',
                            columnWidth: .5
                        }
                    ]
                }
            ]
        });

        this.callParent();
    }
});