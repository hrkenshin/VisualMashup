/**
 * @class VM.layout.GadgetContainer
 * @extends Ext.panel.Panel
 *
 * 오픈소셜 가젯 레이아웃 컨테이너 메인 클래스로 레이아웃을 설정하고 오픈소셜 가젯을 추가한다.
 * 하나의 가젯 컨테이너 인스턴스는 하나의 페이지를 표현한다.
 *
 * @author 이승백
 */
Ext.define('VM.opensocial.GadgetContainer', {
    extend: 'VM.layout.PortletContainer',
    alias: 'widget.gadgetcontainer',

    /**
     * Pipeline 시 Publish, Subscribe 중개 기능을 하는 Empty 가젯
     */
    pipelineGadget: null,

    /**
     * 컨테이너에 있는 모든 WireIt.Wire 인스턴스
     */
    wires: new Array(),

    /**
     * @private
     * @override
     */
    initComponent : function() {
        this.callParent();

        this.pipelineGadget = Ext.create("VM.opensocial.Gadget", {
            specUrl: 'http://' + window.location.host + '/opensocial/gadgets/_transformer.xml',
            disabled: true,
            hidden: true,
            gadgetHeight: 0
        });
        this.add(this.pipelineGadget);

        // pubsub-2 초기화
        gadgets.pubsub2router.init({
            onSubscribe: function(topic, container) {
                if (typeof console !== 'undefined') console.log(container.getClientID() +
                    " subscribes to topic '" + topic + "'");
                return true; // return false to reject the request.
            },
            onUnsubscribe: function(topic, container) {
                if (typeof console !== 'undefined') console.log(container.getClientID() +
                    " unsubscribes from topic '" + topic + "'");
            },
            onPublish: function(topic, data, pcont, scont) {
                if (typeof console !== 'undefined') console.log(pcont.getClientID() +
                    " publishes '" + data + "' to topic '" + topic + "' subscribed by " + scont.getClientID());
                return true; // return false to reject the request.
            }
        });

        this.on('afterrender', function(panel, option) {
            this.setEditable(this.editable);
        }, this);
    },

    /**
     * @override
     * 추가할 포틀릿 리스트를 검색하는 윈도우를 오픈한다.
     */
    openPortletList: function() {
        Ext.create('Ext.Window', {
            title: 'Gadget List',
            resizable: false,
            closable: true,
            border: false,
            modal: true,
            layout: 'fit',
            items: Ext.create('VM.opensocial.GadgetListGrid', {page: this})
        }).show();
    },

    /**
     * @override
     * 추가할 포틀릿 URL을 입력하는 윈도우를 오픈한다.
     */
    openInputUrl: function() {
        Ext.MessageBox.prompt('Gadget XML URL', 'Please Enter Gadget XML URL:', function(btn, text) {
            if (btn == 'ok') {
                this.addPortlet({
                    xtype: 'gadget',
                    specUrl: text,
                    view: 'home'
                });
            }
        }, this, true);
    },

    /**
     * @override
     * 포틀릿을 임포트(업로드)하는 윈도우를 오픈한다.
     */
    openImportPortlet: function() {
        Ext.create('Ext.Window', {
            title: 'Upload Gadget',
            resizable: false,
            closable: true,
            border: false,
            modal: true,
            layout: 'fit',
            items: Ext.create('VM.opensocial.UploadForm', {})
        }).show();
    },

    /**
     * @override
     * 파이프라인 수정 모드 여부를 변경한다.
     *
     * @param {boolean} pipelinable 파이프라인 수정 모드 여부
     */
    setPipelinable: function(pipelinable) {
        this.callParent(arguments);

        var gadgets = Ext.ComponentQuery.query('gadget', this);
        for (var i = 0; i < gadgets.length; i++) {
            if (pipelinable) gadgets[i].expand();
            gadgets[i].setVisiblePubSub(pipelinable);
        }
    },

    /**
     * @override
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
            layout: layout.getLayoutMetaData(),
            pipeline: me.getPipelineMetaData()
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

        // JSP로 즉시 페이지 로딩시 딜레이 필요. onReady
        setTimeout(function() {
            me.pageMetaData = pageMetaData;
            me.pageMetaData['@layoutXType'] = layoutXType || pageMetaData['@layoutXType'];
            me.layoutXType = layoutXType || pageMetaData['@layoutXType'];

            var layout = Ext.getCmp(me.getId() + '_LAYOUT');
            
            // 0. 파이프라인 가젯(히든) 삭제
            me.pipelineGadget.destroy();

            // 1. 기존 레이아웃 삭제
            layout.destroy();

            // 2. 레이아웃 추가
            me.add({
                id: me.getId() + '_LAYOUT',
                xtype: me.layoutXType
            });

            // 3. shindig.container 초기화
            shindig.container = new shindig.IfrContainer();

            // 4.파이프라인 가젯(히든) 추가
            me.pipelineGadget = Ext.create("VM.opensocial.Gadget", {
                specUrl: 'http://' + window.location.host + '/opensocial/gadgets/_transformer.xml',
                disabled: true,
                hidden: true,
                gadgetHeight: 0
            });
            me.add(me.pipelineGadget);

            // 5. 레이아웃에 포틀릿 추가
            layout = Ext.getCmp(me.getId() + '_LAYOUT');
            layout.loadLayout(pageMetaData.layout);

            // 6. 파이프라인 추가(가젯 로딩 완료이후 호출되도록 딜레이 필요)
            setTimeout(function() {
                me.loadPipeline(pageMetaData.pipeline);
            }, 2000);
        }, 1000);
    },

    /**
     * @private
     * 레이아웃을 구성하는 가젯의 파이프라이닝 메타정보를 반환한다.
     * 저장된 파이프라이닝 정보를 다시 로드 하기 위한 정보를 제공한다.
     *
     * @return {JSONObject} pipelineMetaData
     */
    getPipelineMetaData: function() {
        var me = this;
        var gadgets = Ext.ComponentQuery.query('gadget', me);

        var wireArray = new Array();
        var terminalArray, terminal, wire;
        for (var i = 0; i < gadgets.length; i++) {
            terminalArray = gadgets[i].subTerminal.getValues();
            for (var j = 0; j < terminalArray.length; j++) {
                terminal = terminalArray[j];
                for (var k = 0; k < terminal.wires.length; k++) {
                    wire = terminal.wires[k];
                    wireArray.push({
                        from: {
                            '@gadgetId': wire.terminal1.parentEl.id,
                            '@topicName': wire.terminal1.topic.name
                        },
                        to: {
                            '@gadgetId': wire.terminal2.parentEl.id,
                            '@topicName': wire.terminal2.topic.name
                        },
                        transformer: wire.transformer
                    });
                }
            }
        }

        return {wire: wireArray};
    },

    /**
     * @private
     * 주어진 파이프라이닝 메타데이타로 파이프라이닝을 새로 구성한다.
     *
     * @param {JSONObject} pipelineMetaData 파이프라이닝 메타정보
     */
    loadPipeline: function(pipelineMetaData) {
        var wireArray = pipelineMetaData.wire;
        var wire, fromTerminal, toTerminal;
        for (var i = 0; i < wireArray.length; i++) {
            fromTerminal = Ext.getCmp(wireArray[i].from['@gadgetId']).pubTerminal.get(wireArray[i].from['@topicName']);
            toTerminal = Ext.getCmp(wireArray[i].to['@gadgetId']).subTerminal.get(wireArray[i].to['@topicName']);

            wire = new WireIt.BezierArrowWire(fromTerminal, toTerminal,
                Ext.getCmp(wireArray[i].from['@gadgetId']).findParentByType('portalcolumn').getEl().dom);
            wire.redraw();
            wire.element.style.display = "none";

            wire.transformer = wireArray[i].transformer;
        }
    },

    /**
     * 모든 WireIt.Wire 를 반환한다.
     *
     * @return {Array<WireIt.Wire>}
     */
    getWires: function() {
        return this.wires;
    },

    /**
     * this.wires 변수에 모든 WireIt.Wire 를 셋팅한 후 반환한다.
     *
     * @return {Array<WireIt.Wire>}
     */
    syncWires: function() {
        var me = this;
        var gadgets = Ext.ComponentQuery.query('gadget', me);

        var wireArray = new Array();
        var terminalArray, terminal, wire;
        for (var i = 0; i < gadgets.length; i++) {
            terminalArray = gadgets[i].subTerminal.getValues();
            for (var j = 0; j < terminalArray.length; j++) {
                terminal = terminalArray[j];
                for (var k = 0; k < terminal.wires.length; k++) {
                    wire = terminal.wires[k];
                    wireArray.push(wire);
                }
            }
        }
        this.wires = wireArray;
        return this.wires;
    }
});