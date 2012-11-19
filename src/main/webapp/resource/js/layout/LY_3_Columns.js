/**
 * @class VM.layout.LY_2_Columns
 * @extends VM.layout._Layout
 *
 * 포틀릿 레이아웃 타입(3 Columns) 정의 클래스
 *
 * @author 이승백
 */
Ext.define('VM.layout.LY_3_Columns', {
    extend: 'VM.layout._Layout',
    alias: 'widget.layout_3_columns',

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
                            columnWidth: .33
                        },
                        {
                            id: layoutId + '_2',
                            columnWidth: .34
                        },
                        {
                            id: layoutId + '_3',
                            columnWidth: .33
                        }
                    ]
                }
            ]
        });

        this.callParent();
    }
});