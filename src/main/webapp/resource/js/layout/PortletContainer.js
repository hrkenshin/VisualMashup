/**
 * @class VM.layout.PortletContainer
 * @extends Ext.panel.Panel
 *
 * 포틀릿 레이아웃 컨테이너 메인 클래스로 레이아웃을 설정하고 포틀릿을 추가한다.
 * 하나의 포틀릿 컨테이너 인스턴스는 하나의 페이지를 표현한다.
 *
 * @author 이승백
 */
Ext.define('VM.layout.PortletContainer', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.portletcontainer',

    requires: [
        'Ext.app.Portlet',
        'Ext.app.PortalColumn',
        'Ext.app.PortalPanel',
        'Ext.app.PortalDropZone'
    ],

    uses: [
        'VM.layout.*'
    ],

    /**
     * @config
     * 페이지 타이틀
     */
    title: '',

    /**
     * @config
     * 레이아웃타입 (layout_1_2_1_columns, layout_1_2_columns_1, layout_1_2_columns_2, layout_1_column,
     * layout_2_2_columns, layout_2_columns_1, layout_2_columns_2, layout_2_columns_3, layout_3_columns)
     */
    layoutXType: 'layout_3_columns',

    /**
     * @config
     * 설명
     */
    description: '',

    /**
     * @config
     * 레이아웃 수정 모드 여부
     */
    editable: true,

    /**
     * 파이프라인 수정 모드 여부
     */
    pipelinable: false,

    /**
     * 생성자
     * @param {JsonObject} config
     */
    constructor: function(config) {
        var me = this;
        var panelId = me.getId();

        /**
         * 레이아웃을 변경한다. 체크버튼클릭시 실행
         *
         * @param {Ext.menu.CheckItem} item
         * @param {boolean} checked
         */
        var changeLayout = function(item, checked) {
            if (checked) {
                me.changeLayout(item.value);
            }
        };

        /**
         * 메뉴
         */
        var contextMenu = Ext.create('Ext.menu.Menu', {
            items: [
                {
                    id: panelId + '_ADDGADGET',
                    text: me.getXType() == 'gadgetcontainer' ? 'Add Gadget' : 'Add Portlet',
                    menu: {
                        items: [
                            {
                                text: me.getXType() == 'gadgetcontainer' ? 'Gadget List' : 'Portlet List',
                                handler: function(item) {
                                    me.openPortletList();
                                }
                            },
                            {
                                text: 'Input XML URL',
                                hidden: me.getXType() != 'gadgetcontainer',
                                handler: function(item) {
                                    me.openInputUrl();
                                }
                            }
                        ]
                    }
                },
                {
                    id: panelId + '_PAGELAYOUT',
                    text: 'Change Layout',
                    menu: {
                        items: [
                            {
                                text: '1 Column',
                                checked: me.layoutXType == 'layout_1_column',
                                value: 'layout_1_column',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '2 Columns (50:50)',
                                checked: me.layoutXType == 'layout_2_columns_1',
                                value: 'layout_2_columns_1',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '2 Columns (30:70)',
                                checked: me.layoutXType == 'layout_2_columns_2',
                                value: 'layout_2_columns_2',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '2 Columns (70:30)',
                                checked: me.layoutXType == 'layout_2_columns_3',
                                value: 'layout_2_columns_3',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '3 Columns',
                                checked: me.layoutXType == 'layout_3_columns',
                                value: 'layout_3_columns',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '1-2 Columns (30:70)',
                                checked: me.layoutXType == 'layout_1_2_columns_1',
                                value: 'layout_1_2_columns_1',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '1-2 Columns (70:30)',
                                checked: me.layoutXType == 'layout_1_2_columns_2',
                                value: 'layout_1_2_columns_2',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '1-2-1 Columns',
                                checked: me.layoutXType == 'layout_1_2_1_columns',
                                value: 'layout_1_2_1_columns',
                                group: 'theme',
                                checkHandler: changeLayout
                            },
                            {
                                text: '2-2 Columns',
                                checked: me.layoutXType == 'layout_2_2_columns',
                                value: 'layout_2_2_columns',
                                group: 'theme',
                                checkHandler: changeLayout
                            }
                        ]
                    }
                },
                {
                    id: panelId + '_EDITMODE',
                    text: 'Edit Mode',
                    checked: me.editable,
                    checkHandler: function(item, checked) {
                        me.setEditable(checked);
                    }
                },
                {
                    id: panelId + '_PIPELINING',
                    text: 'Pipelining',
                    checked: me.pipelinable,
                    hidden: me.getXType() != 'gadgetcontainer',
                    checkHandler: function(item, checked) {
                        me.setPipelinable(checked);
                    }
                },
                '-',
                {
                    text: 'Upload Gadget',
                    hidden: me.getXType() != 'gadgetcontainer',
                    handler: function(item) {
                        me.openImportPortlet();
                    }
                },
                {
                    text: 'Reload Page',
                    handler: function(item) {
                        me.loadPage(me.getPageMetaData());
                        Ext.getCmp(panelId + '_ADDGADGET').setDisabled(false);
                        Ext.getCmp(panelId + '_PAGELAYOUT').setDisabled(false);
                        Ext.getCmp(panelId + '_EDITMODE').setDisabled(false);
                        Ext.getCmp(panelId + '_EDITMODE').setChecked(true, true);
                        Ext.getCmp(panelId + '_PIPELINING').setChecked(false, true);
                    }
                },
                {
                    text: 'Get PageMetaData',
                    handler: function(item) {
                        if (typeof console !== 'undefined') {
                            console.log(Ext.JSON.encode(me.getPageMetaData()));
                        } else {
                            alert(Ext.JSON.encode(me.getPageMetaData()));
                        }
                    }
                }
            ]
        });

        Ext.apply(this, config);

        Ext.apply(this, {
            layout: {
                type: 'fit'
            },
            items: [
                {
                    id: panelId + '_LAYOUT',
                    xtype: me.layoutXType
                }
            ]
        });

        this.callParent(arguments);

        this.on('afterrender', function(panel, option) {
            // window.document 마우스 우클릭 막기
            Ext.EventManager.on(window.document, 'contextmenu', function(event, element, object) {
                event.stopEvent();
            });

            // 마우스 우클릭 메뉴
            Ext.EventManager.on(this.getId(), 'contextmenu', function(event, element, object) {
                contextMenu.showAt(event.getXY());
            });
        }, this);
    },

    /**
     * @private
     * @override
     */
    initComponent : function() {
        this.callParent();
    },

    /**
     * @abstract
     * 추가할 포틀릿 리스트를 검색하는 윈도우를 오픈한다.
     */
    openPortletList: Ext.emptyFn,

    /**
     * @abstract
     * 추가할 포틀릿 URL을 입력하는 윈도우를 오픈한다.
     */
    openInputUrl: Ext.emptyFn,

    /**
     * @abstract
     * 포틀릿을 임포트(업로드)하는 윈도우를 오픈한다.
     */
    openImportPortlet: Ext.emptyFn,
    
    /**
     * @abstract
     * 페이지를 저장하는 윈도우를 오픈한다.
     */
    openSavePage: Ext.emptyFn,

    /**
     * 페이지에 포틀릿을 추가한다.
     *
     * @param {Ext.app.Portlet} portlet 포틀릿
     */
    addPortlet: function(portlet) {
        var layout = Ext.getCmp(this.getId() + '_LAYOUT');
        layout.addPortlet(portlet);
    },

    /**
     * 레이아웃을 수정 가능 모드 여부를 변경한다.
     *
     * @param {boolean} editable 레이아웃 수정 가능 모드 여부
     */
    setEditable: function(editable) {
        // 1. min-height 조절
        var portalPanels = Ext.ComponentQuery.query('portalpanel', this);
        for (var i = 0; i < portalPanels.length; i++) {
            if (editable) {
                portalPanels[i].body.setStyle('min-height', '100px');
            } else {
                portalPanels[i].body.setStyle('min-height', '0');
            }
        }

        // 2. 이동 불가 토글 및 툴바 감춤 토글
        var portlets = Ext.ComponentQuery.query('portlet', this);
        for (var i = 0; i < portlets.length; i++) {
            if (editable) {
                portlets[i].unpin();
                portlets[i].setPinnable(true);
                portlets[i].setClosable(true);
            } else {
                portlets[i].pin();
                portlets[i].setPinnable(false);
                portlets[i].setClosable(false);
            }
        }

        // 3. 메뉴버튼 활성화 토글
        Ext.getCmp(this.getId() + '_ADDGADGET').setDisabled(!editable);
        Ext.getCmp(this.getId() + '_PAGELAYOUT').setDisabled(!editable);
        Ext.getCmp(this.getId() + '_PIPELINING').setDisabled(!editable);

        this.editable = editable;
    },

    /**
     * 페이지 레이아웃을 변경한다.
     * 레이아웃타입 (layout_1_2_1_columns, layout_1_2_columns_1, layout_1_2_columns_2, layout_1_column,
     * layout_2_2_columns, layout_2_columns_1, layout_2_columns_2, layout_2_columns_3, layout_3_columns)
     *
     * @param {String} layoutXType 레이아웃 XType
     */
    changeLayout: function(layoutXType) {
        this.loadPage(this.getPageMetaData(), layoutXType);
    },

    /**
     * 파이프라인 수정 모드 여부를 변경한다.
     *
     * @param {boolean} pipelinable 파이프라인 수정 모드 여부
     */
    setPipelinable: function(pipelinable) {
        Ext.getCmp(this.getId() + '_ADDGADGET').setDisabled(pipelinable);
        Ext.getCmp(this.getId() + '_EDITMODE').setDisabled(pipelinable);
        Ext.getCmp(this.getId() + '_PAGELAYOUT').setDisabled(pipelinable);

        this.pipelinable = pipelinable;
    },

    /**
     * 페이지를 구성하는 포틀릿과 포틀릿 배치 정보의 메타데이타를 반환한다.
     * 저장된 페이지를 다시 로드 하기 위한 정보를 제공한다.
     *
     * @return {JSONObject} 페이지 메타데이타
     */
    getPageMetaData: function() {
        var me = this;
        var layout = Ext.getCmp(me.getId() + '_LAYOUT');

        return {
            '@id' : me.getId(),
            '@title': me.title,
            '@layoutXType' : me.layoutXType,
            '@description' : me.description,
            layout: layout.getLayoutMetaData()
        };
    },

    /**
     * 주어진 페이지 메타데이타로 페이지를 새로 구성한다.
     *
     * @param {JSONObject} pageMetaData 페이지 메타데이타
     * @param {String} layoutXType 레이아웃타입(Optional)
     */
    loadPage: function(pageMetaData, layoutXType) {
        var me = this;
        me.pageMetaData = pageMetaData;
        me.pageMetaData['@layoutXType'] = layoutXType || pageMetaData['@layoutXType'];
        me.layoutXType = layoutXType || pageMetaData['@layoutXType'];

        var layout = Ext.getCmp(me.getId() + '_LAYOUT');

        // 0. 기존 레이아웃 삭제
        layout.destroy();

        // 1. 레이아웃 추가
        me.add({
            id: me.getId() + '_LAYOUT',
            xtype: me.layoutXType
        });

        // 2. 레이아웃에 포틀릿 추가
        layout = Ext.getCmp(me.getId() + '_LAYOUT');
        layout.loadLayout(pageMetaData.layout);
    }
});