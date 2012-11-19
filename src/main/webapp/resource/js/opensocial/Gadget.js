/**
 * @class VM.opensocial.Gadget
 * @extends Ext.app.Portlet
 *
 * 오픈소셜 가젯 랜더링 클래스
 *
 * @author 이승백
 */
Ext.define('VM.opensocial.Gadget', {
    extend: 'Ext.app.Portlet',
    alias: 'widget.gadget',

    statics: {
        GADGET_CHROME_PREFIX : 'gadget-chrome-',
        GADGET_PUBLISH_PREFIX : 'gadget-publish-',
        GADGET_SUBSCRIBE_PREFIX : 'gadget-subscribe-'
    },

    /**
     * @config
     * Open Social Gadget Property
     */
    userPrefs: {},

    /**
     * @config
     * Open Social Gadget Property
     */
    view: 'default',

    /**
     * @config
     * Open Social Gadget Property
     */
    language: 'ALL',

    /**
     * @config
     * Open Social Gadget Property
     */
    country: 'ALL',

    /**
     * @config
     * Gadget Height
     */
    gadgetHeight: 200,

    /**
     * OpenSocial Gadget Instance
     * {shindig.IfrGadget}
     */
    gadget: null,

    /**
     * Subscribe Wire Terminal
     * {Ext.util.HashMap<String, WireIt.Terminal>}
     */
    subTerminal: new Ext.util.HashMap(),

    /**
     * Publish Wire Terminal
     * {Ext.util.HashMap<String, WireIt.Terminal>}
     */
    pubTerminal: new Ext.util.HashMap(),

    /**
     * @private
     * @override
     */
    initComponent : function() {
        this.callParent();

        this.addEvents(
            /**
             * @event finishGadgetRender
             * 가젯이 랜더링 완료 되었을때 발생
             *
             * @param {VM.opensocial.Gadget} this
             */
            'finishGadgetRender'
        );

        this.on({
            afterrender: function(panel) {
                this.initRenderGadget();
            },
            maximize: function(panel) {
                this.reRenderGadget('canvas');
            },
            restore: function(panel) {
                this.reRenderGadget();
            },
            gear: function(panel) {
                if (panel.gadget) {
                    panel.gadget.handleOpenUserPrefsDialog();
                }
            },
            drop: function(event) {
            },
            help: function(panel) {
            },
            beforedestroy: function(panel, option) {
                // 1. Remove Wire
                this.subTerminal.each(function(key, value, length) {
                    value.removeAllWires();
                });
                this.pubTerminal.each(function(key, value, length) {
                    value.removeAllWires();
                });

                // 2. Remove OpenAjaxHub Container
                var hubContainer = gadgets.pubsub2router.hub.getContainer(panel.gadget.getIframeId());
                if (hubContainer) gadgets.pubsub2router.hub.removeContainer(hubContainer);
            },
            scope: this
        });
    },

    /**
     * @private
     * 가젯을 Panel Body 에 처음 렌더링한다.
     *
     * @param {String} viewMode 뷰모드 (home, default, profile, canvas)
     */
    initRenderGadget: function(viewMode) {
        var me = this;
        viewMode = viewMode || me.view;
        if (me.specUrl && shindig.container) {
            // 1. shindigcontainer 속성 설정
            shindig.container.setView(viewMode);
            shindig.container.setLanguage(me.language);
            shindig.container.setCountry(me.country);

            // 2. canvas 뷰이면 가젯 높이를 가젯 컨테이너 높이로 조절
            var iframeHeight = me.gadgetHeight;
            if (viewMode == 'canvas') {
                var containerBodyHeight = me.findParentByType('gadgetcontainer').body.getHeight() - 50;
                if (containerBodyHeight > iframeHeight) iframeHeight = containerBodyHeight;
            }

            // 3. 가셋 생성
            var gadgetInstanceId = parseInt(me.getId().replace('gadget-', ''));
            me.gadget = shindig.container.createGadget({
                id: gadgetInstanceId,
                moduleId: gadgetInstanceId,
                specUrl: me.specUrl,
                width: '100%',
                height: iframeHeight,
                userPrefs: me.userPrefs
            });

            // 4. shindigcontainer 에 가젯 추가
            shindig.container.addGadget(me.gadget);

            // 5. 가젯 랜더링
            me.gadget.getContent(function(content) {
                me.setTitle(me.gadget.metadata.title);
                me.body.dom.innerHTML =
                    '<div class="gadgets-pubsub" id="' + VM.opensocial.Gadget.GADGET_SUBSCRIBE_PREFIX + me.gadget.id + '"></div>' +
                        '<div id="' + VM.opensocial.Gadget.GADGET_CHROME_PREFIX + me.gadget.id + '"></div>' +
                        '<div class="gadgets-pubsub" id="' + VM.opensocial.Gadget.GADGET_PUBLISH_PREFIX + me.gadget.id + '"></div>';
                var gadgetChrome = Ext.getDom(VM.opensocial.Gadget.GADGET_CHROME_PREFIX + me.gadget.id);
                gadgetChrome.innerHTML = content;
                me.gadget.finishRender();
                me.makePubSub(me.getPubSubParams(me.gadget.metadata));
                shindig.container.setView(me.view);

                me.fireEvent('finishGadgetRender', me);
            });
        }
    },

    /**
     * @private
     * 초기 가젯 랜더링 이후에 가젯을 재 랜더링할때 사용한다.(동일한 iframeId를 사용하도록)
     *
     * @param {String} viewMode 뷰모드 (home, default, profile, canvas)
     */
    reRenderGadget: function(viewMode) {
        var me = this;

        viewMode = viewMode || me.view;
        if (me.specUrl && shindig.container) {
            // 0. OpenAjaxHub Container 에서 삭제
            var hubContainer = gadgets.pubsub2router.hub.getContainer(me.gadget.getIframeId());
            if (hubContainer) gadgets.pubsub2router.hub.removeContainer(hubContainer);

            // 1. shindigcontainer 속성 설정
            shindig.container.setView(viewMode);

            // 2. canvas 뷰이면 가젯 높이를 가젯 컨테이너 높이로 조절
            var iframeHeight = me.gadgetHeight;

            if (viewMode == 'canvas') {
                var containerBodyHeight = me.findParentByType('gadgetcontainer').body.getHeight() - 50;
                if (containerBodyHeight > iframeHeight) iframeHeight = containerBodyHeight;
            }

            // 3. 가젯 랜더링
            me.gadget.getContent(function(content) {
                var gadgetChrome = Ext.getDom(VM.opensocial.Gadget.GADGET_CHROME_PREFIX + me.gadget.id);
                gadgetChrome.innerHTML = content;

                me.gadget.finishRender();
                if (Ext.get(me.gadget.getIframeId())) Ext.get(me.gadget.getIframeId()).setHeight(iframeHeight);

                shindig.container.setView(me.view);

                me.fireEvent('finishGadgetRender', me);
            });
        }
    },

    /**
     * @private
     * pubsub-2 유형인 경우 Publish, Subscribe 용 파라미터 정보를 추출하여 반환한다.
     *
     * @param {Object} metadata
     * @return {Array<Object>} 파라미터정보 Array
     */
    getPubSubParams: function(metadata) {
        var paramArray = null;
        if (metadata && metadata.featureDetails && metadata.featureDetails['pubsub-2'] &&
            metadata.featureDetails['pubsub-2'].parameters && metadata.featureDetails['pubsub-2'].parameters.topics) {
            paramArray = new Array();
            var topics = metadata.featureDetails['pubsub-2'].parameters.topics;
            for (var j = 0; j < topics.length; j++) {
                var topic = topics[j];
                // Depending on how the gadget metadata was written, the topic metadata may be either a
                // string or an object (parsed from the XML).
                if (typeof(topic) == "string") {
                    var attrs = topic.match(/\w+=(?:"[^"]*"|'[^']*')/g);
                    topic = {};
                    for (var k = 0; k < attrs.length; k++) {
                        var pairs = attrs[k].match(/(\w+)=(?:"([^"]*)"|'([^']*)')/);
                        var attr = pairs[1],
                            value = pairs[2] || pairs[3];
                        topic[attr] = value === "true" ? true :
                            value === "false" ? false :
                                value;
                    }
                }
                paramArray.push(topic);
            }
        }

        return paramArray;
    },

    /**
     * @private
     * pubsub-2 유형인 경우 Publish, Subscribe 용 파라미터 Wire Terminal 을 드로잉한다.
     *
     * @param {Array<Object>}paramArray 파라미터정보 Array
     */
    makePubSub: function(paramArray) {
        var me = this;
        if (Ext.isArray(paramArray)) {
            var gadgetContainer = this.findParentByType('gadgetcontainer');

            this.subTerminal = new Ext.util.HashMap();
            this.pubTerminal = new Ext.util.HashMap();

            var gadgetSub = Ext.get(VM.opensocial.Gadget.GADGET_SUBSCRIBE_PREFIX + this.gadget.id);
            var gadgetPub = Ext.get(VM.opensocial.Gadget.GADGET_PUBLISH_PREFIX + this.gadget.id);

            gadgetSub.setVisibilityMode(Ext.Element.DISPLAY);
            gadgetPub.setVisibilityMode(Ext.Element.DISPLAY);

            gadgetSub.hide();
            gadgetPub.hide();

            var terminal;
            var subCnt = 0, pubCnt = 0;
            for (var i = 0; i < paramArray.length; i++) {
                var topic = paramArray[i];
                if (topic.subscribe) {
                    // Terminal For Subscribe
                    terminal = new WireIt.Terminal(this.getEl().dom, {
                        name: topic.description,
                        title: {
                            value: topic.title + (topic.type ? ' [' + topic.type + ']' : ''),
                            position: {left : 25}
                        },
                        topic: topic,
                        direction: [-1,0],
                        offsetPosition:{left: 0, top: (25 + 30 * subCnt)},
                        ddConfig: {
                            type: 'input',
                            allowedTypes: ['output']
                        },
                        wireConfig: { xtype: 'WireIt.BezierArrowWire'}
                    }, null, this.findParentByType('layout'));
                    subCnt++;
                    terminal.el.style.display = 'none';

                    // addWire 이벤트 리스너 등록
                    terminal.eventAddWire.subscribe(function(e, params) {
                        var wire = params[0];
                        if (wire.terminal1.wires != null && wire.terminal2.wires != null) {
                            var gadget1 = Ext.getCmp(wire.terminal1.parentEl.id);
                            var gadget2 = Ext.getCmp(wire.terminal2.parentEl.id);

                            // default transformer
                            wire.transformer = {
                                type: 'string',
                                operator: 'bypass'
                            };

                            // Subscribe
                            wire.subscribeId = window.frames[gadgetContainer.pipelineGadget.gadget.getIframeId()].gadgets.Hub.subscribe(
                                wire.terminal1.topic.name,
                                function(topic, data) {
                                    var operator = window.frames[gadgetContainer.pipelineGadget.gadget.getIframeId()][wire.transformer.operator];
                                    var param = wire.transformer['param'];

                                    // run transformer
                                    window.frames[gadgetContainer.pipelineGadget.gadget.getIframeId()].gadgets.Hub.publish(
                                        wire.terminal2.topic.name, operator(data, param));
                                });

                            // Wire 의 MouseIn, MouseOut, MouseClick 이벤트를 등록한다.
                            gadgetContainer.syncWires();
                            me.wireEventSubscribe(wire);
                        }
                    });

                    // removeWire 이벤트 리스너 등록
                    terminal.eventRemoveWire.subscribe(function(e, params) {
                        var wire = params[0];
                        if (wire.terminal1.wires != null && wire.terminal2.wires != null) {
                            var gadget1 = Ext.getCmp(wire.terminal1.parentEl.id);
                            var gadget2 = Ext.getCmp(wire.terminal2.parentEl.id);

                            // Unsubscribe
                            window.frames[gadgetContainer.pipelineGadget.gadget.getIframeId()].gadgets.Hub.unsubscribe(wire.subscribeId);
                            gadgetContainer.syncWires();
                        }
                    });

                    this.subTerminal.add(terminal.topic.name, terminal);
                } else if (topic.publish) {
                    // Terminal For Publish
                    terminal = new WireIt.Terminal(this.getEl().dom, {
                        name: topic.description,
                        title: {
                            value: topic.title + (topic.type ? ' [' + topic.type + ']' : ''),
                            position: {right : 25}
                        },
                        topic: topic,
                        direction: [1,0],
                        offsetPosition:{right: 0, bottom: (30 * pubCnt)},
                        alwaysSrc: true,
                        ddConfig: {
                            type: 'output',
                            allowedTypes: ['input']
                        },
                        wireConfig: { xtype: 'WireIt.BezierArrowWire'}
                    }, null, this.findParentByType('layout'));
                    pubCnt++;
                    terminal.el.style.display = 'none';
                    this.pubTerminal.add(terminal.topic.name, terminal);
                }
            }
            gadgetSub.setHeight(30 * subCnt);
            gadgetPub.setHeight(30 * pubCnt);
        }
    },

    /**
     * pubsub-2 유형인 경우 Publish, Subscribe 용 파라미터, Wire Terminal 토글링한다.
     * @param {boolean} isVisible
     */
    setVisiblePubSub: function(isVisible) {
        var gadgetSub = Ext.get(VM.opensocial.Gadget.GADGET_SUBSCRIBE_PREFIX + this.gadget.id);
        var gadgetContent = Ext.get(VM.opensocial.Gadget.GADGET_CHROME_PREFIX + this.gadget.id);
        var gadgetPub = Ext.get(VM.opensocial.Gadget.GADGET_PUBLISH_PREFIX + this.gadget.id);

        if (isVisible) {
            gadgetSub.show(true);
            gadgetPub.show(true);

            // PubSub 영역이 가젯 영역보다 큰 경우 사이즈 조절
            if (gadgetContent.getHeight() < gadgetPub.getHeight()) {
                gadgetContent.setHeight(gadgetPub.getHeight());
            }
            if (gadgetContent.getHeight() < gadgetSub.getHeight()) {
                gadgetContent.setHeight(gadgetSub.getHeight());
            }

            // Show Terminal & Wire
            this.subTerminal.each(function(key, terminal, length) {
                terminal.el.style.display = 'block';
                for (var i = 0; i < terminal.wires.length; i++) {
                    terminal.wires[i].element.style.display = "block";
                }
                terminal.redrawAllWires();
            });

            this.pubTerminal.each(function(key, terminal, length) {
                terminal.el.style.display = 'block';
                for (var i = 0; i < terminal.wires.length; i++) {
                    terminal.wires[i].element.style.display = "block";
                }
                terminal.redrawAllWires();
            });

            // 헤더 영역 Disable
            this.header.setDisabled(true);
            this.setDraggable(false);
            this.setMaximizable(false);

            // 가젯 본문 영역 Disable
            gadgetContent.addCls('x-item-disabled');
            gadgetContent.dom.disabled = true;
            gadgetContent.mask();
        } else {
            gadgetSub.hide(true);
            gadgetPub.hide(true);

            // 가젯 영역 사이즈 복원(AutoHeight)
            gadgetContent.setHeight('');

            // Hide Terminal & Wire
            this.subTerminal.each(function(key, terminal, length) {
                terminal.el.style.display = 'none';
                for (var i = 0; i < terminal.wires.length; i++) {
                    terminal.wires[i].element.style.display = "none";
                }
            });
            this.pubTerminal.each(function(key, terminal, length) {
                terminal.el.style.display = 'none';
                for (var i = 0; i < terminal.wires.length; i++) {
                    terminal.wires[i].element.style.display = "none";
                }
            });

            // 헤더 영역 Enable
            this.header.setDisabled(false);
            this.setDraggable(true);
            this.setMaximizable(true);

            // 가젯 본문 영역 Enable
            gadgetContent.removeCls('x-item-disabled');
            gadgetContent.dom.disabled = false;
            gadgetContent.unmask();
        }
    },

    /**
     * @private
     * Wire 의 MouseIn, MouseOut, MouseClick 이벤트를 등록한다. 와이어 클릭시 트랜스포머 팝업을 띄우기 위해 필요.
     *
     * @param {WireIt.Wire} wire 와이어
     */
    wireEventSubscribe: function(wire) {
        var me = this;

        var gadgetContainer = this.findParentByType('gadgetcontainer');

        /**
         * Returns a position relative to the layer from a mouse event
         * @method _getMouseEvtPos
         * @param {Event} e Mouse event
         * @return {Array} position
         */
        var _getMouseEvtPos = function(e) {
            var tgt = YAHOO.util.Event.getTarget(e);
            var tgtPos = [tgt.offsetLeft, tgt.offsetTop];
            return [tgtPos[0] + e.layerX, tgtPos[1] + e.layerY];
        };

        /**
         * Handles mousemove events on any wire canvas
         * Note: we treat mouse events globally so that wires behind others can still receive the events
         * @method onWireMouseMove
         * @param {Event} e Mouse move event
         */
        var _onWireMouseMove = function(e) {
            var wires = gadgetContainer.getWires();

            var p = _getMouseEvtPos(e);
            var lx = p[0], ly = p[1], w, elx;
            for (var i = 0; i < wires.length; i++) {
                w = wires[i];
                elx = w.element.offsetLeft,ely = w.element.offsetTop;
                // Check if the mouse is within the canvas boundaries
                if (lx >= elx && lx < elx + w.element.width && ly >= ely && ly < ely + w.element.height) {
                    var rx = lx - elx, ry = ly - ely; // relative to the canvas
                    w.onMouseMove(rx, ry);
                }
            }
        };

        /**
         * Handles click on any wire canvas
         * Note: we treat mouse events globally so that wires behind others can still receive the events
         * @method onWireClick
         * @param {Event} e Mouse click event
         */
        var _onWireClick = function(e) {
            var wires = gadgetContainer.getWires();

            var p = _getMouseEvtPos(e);
            var lx = p[0], ly = p[1], w, elx;
            for (var i = 0; i < wires.length; i++) {
                w = wires[i];
                elx = w.element.offsetLeft,ely = w.element.offsetTop;
                // Check if the mouse is within the canvas boundaries
                if (lx >= elx && lx < elx + w.element.width && ly >= ely && ly < ely + w.element.height) {
                    var rx = lx - elx, ry = ly - ely; // relative to the canvas
                    w.onClick(rx, ry);
                }
            }
        };

        // Wire Click 이벤트 처리하기 위한 코드
        YAHOO.util.Event.addListener(wire.element, "mousemove", function(event, element) {
            _onWireMouseMove(event);
        }, wire, true);
        YAHOO.util.Event.addListener(wire.element, "click", function(event, element) {
            _onWireClick(event);
        }, wire, true);

        // Wire 의 MouseIn, MouseOut, MouseClick 이벤트를 등록한다. 
        wire.eventMouseIn.subscribe(function() {
            this.color = 'rgb(255, 0, 0)';
            this.redraw();
        }, wire, true);
        wire.eventMouseOut.subscribe(function() {
            this.color = 'rgb(173, 216, 230)';
            this.redraw();
        }, wire, true);
        wire.eventMouseClick.subscribe(function(e, params) {
            // 클릭 좌표
            var xy = params[1];
            var l = this.element.style.left,
                t = this.element.style.top;
            var li = parseInt(l.substr(0, l.length - 2), 10),
                ti = parseInt(t.substr(0, t.length - 2), 10);
            var left = (li + xy[0]);
            var top = (ti + xy[1]);

            // 와이어 클릭시 트랜스포머 설정 팝업
            me.openTransformer(wire, left, top);
        }, wire, true);
    },

    /**
     * @private
     * 와이어 클릭시 트랜스포머 설정창을 팝업한다.
     *
     * @param {WireIt.Wire} wire 와이어
     * @param x x좌표
     * @param y y좌표
     */
    openTransformer: function(wire, x, y) {
        // 와이어 클릭시 트랜스포머 설정 팝업
        var transGadget = Ext.create("VM.opensocial.Gadget", {
            specUrl: 'http://' + window.location.host + '/opensocial/gadgets/_transformer.xml',
            userPrefs: {'transformer':{value:Ext.JSON.encode(wire.transformer)}},
            maximizable: false,
            settable: false,
            pinable: false,
            helpable: false,
            closable: false,
            draggable: false,
            border: false,
            frame: false,
            collapsible: false,
            width: 230,
            gadgetHeight: 42
        });

        var transGadgetWin = Ext.create('Ext.Window', {
            title: 'Transformer',
            resizable: false,
            closable: true,
            modal: true,
            anchor: '100%',
            layout: 'fit',
            items: transGadget,
            buttonAlign: 'center',
            buttons: [
                {
                    text: 'OK',
                    handler: function() {
                        var transGadgetIframe = window.frames[transGadget.gadget.getIframeId()];
                        transGadgetIframe._applyTransformer(function(type, operator, param) {
                            wire.transformer = {
                                type: type,
                                operator: operator,
                                param: param
                            };
                            transGadgetWin.destroy();
                        });
                    }
                },
                {
                    text: 'Apply',
                    handler: function() {
                        var transGadgetIframe = window.frames[transGadget.gadget.getIframeId()];
                        transGadgetIframe._applyTransformer(function(type, operator, param) {
                            wire.transformer = {
                                type: type,
                                operator: operator,
                                param: param
                            };
                        });
                    }
                }
            ]
        }).show();
    }
});