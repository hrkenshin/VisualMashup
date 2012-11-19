/**
 * @class VM.opensocial.GadgetListGrid
 * @extends Ext.grid.Panel
 *
 * 오픈소셜 가젯 리스트 그리드 패널 클래스
 *
 * @author 이승백
 */
Ext.define('VM.opensocial.GadgetListGrid', {
    extend: 'Ext.grid.Panel',

    requires: [
        'VM.opensocial.common.Properties',
        'VM.opensocial.common.CustomFilters',
        'VM.opensocial.common.CustomSearchField',
        'VM.opensocial.common.RESTProxy',
        'VM.opensocial.model.VmGadgets'
    ],

    constructor: function(config) {
        var PANEL_ID = 'ID_GADGET_LIST';

        var page = config.page;

        var store = Ext.create('Ext.data.Store', {
            model: 'VM.opensocial.model.VmGadgets',
            proxy: Ext.create('VM.opensocial.common.RESTProxy', {
                model: 'VM.opensocial.model.VmGadgets'
            }),
            sorters: [
                { property: 'seq_no', direction: 'ASC' }
            ],
            pageSize: VM.Properties.ROW_COUNT_PER_PAGE,
            remoteSort: true,
            remoteFilter: true,
            autoLoad: true,
            autoSync: true
        });

        Ext.apply(this, config);

        Ext.apply(this, {
            id: PANEL_ID,
            width: 600,
            height: 350,
            store: store,
            viewConfig: {
                stripeRows: true
            },
            columnLines: false,
            forceFit: true,
            features: [
                {
                    ftype: 'customFilters',
                    encode: false,
                    local: false
                }
            ],
            plugins: [Ext.create('Ext.grid.plugin.CellEditing')],
            columns: [
                Ext.create('Ext.grid.RowNumberer'),
                {
                    header: 'ID',
                    dataIndex: 'seq_no',
                    sortable: true,
                    filterable: true,
                    hidden: true,
                    width: 50,
                    maxWidth: 50,
                    align: 'right'
                },
                {
                    header: '제목',
                    dataIndex: 'title',
                    sortable: true,
                    filterable: true,
                    width: 200,
                    editor: {
                        xtype: 'textfield',
                        allowBlank:false,
                        maxLength: 30
                    }
                },
                {
                    header: '설명',
                    dataIndex: 'description',
                    width: 320,
                    editor: {
                        xtype: 'textfield',
                        maxLength: 200
                    }
                },
                {
                    header: '경로',
                    dataIndex: 'path',
                    hidden: true,
                    width: 320
                },
                {
                    xtype: 'actioncolumn',
                    width: 40,
                    maxWidth: 40,
                    items: [
                        {
                            iconCls: 'icon_accept',
                            tooltip: '페이지에 추가',
                            handler: function(grid, rowIndex, colIndex) {
                                var record = store.getAt(rowIndex);
                                page.addPortlet({
                                    xtype: 'gadget',
                                    specUrl: 'http://' + window.location.host + VM.Properties.BASE_CONTEXT + '/' + record.get('path'),
                                    view: 'home'
                                });
                            }
                        },
                        {
                            iconCls: 'icon_delete',
                            tooltip: '가젯 삭제',
                            handler: function(grid, rowIndex, colIndex) {
                                Ext.Msg.confirm('가젯 삭제', '가젯을 삭제하시겠습니까?', function(btn) {
                                    if (btn == 'yes') store.removeAt(rowIndex);
                                });

                            }
                        }
                    ]
                }
            ],
            bbar: Ext.create('Ext.PagingToolbar', {
                store: store,
                displayInfo: true
            })
        });

        this.callParent(arguments);
    }
});