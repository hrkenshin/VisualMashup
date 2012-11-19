/*

 This file is part of Ext JS 4

 Copyright (c) 2011 Sencha Inc

 Contact:  http://www.sencha.com/contact

 GNU General Public License Usage
 This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

 If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

 */
/**
 * @class Ext.app.Portlet
 * @extends Ext.Panel
 * A {@link Ext.Panel Panel} class that is managed by {@link Ext.app.PortalPanel}.
 */
Ext.define('Ext.app.Portlet', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.portlet',
    layout: 'fit',
    anchor: '100%',
    frame: true,
    closable: true,
    collapsible: true,
    animCollapse: true,
    draggable: true,
    cls: 'x-portlet',

    // TODO : ADDED By 이승백 {
    autoHeight: true,

    /**
     * @config
     * maximize 툴 활성여부 (default : true)
     */
    maximizable: true,

    /**
     * @config
     * gear 툴 활성여부 (default : true)
     */
    settable: true,

    /**
     * @config
     * pin 툴 활성여부 (default : true)
     */
    pinable: true,

    /**
     * @config
     * help 툴 활성여부 (default : true)
     */
    helpable: true,

    /**
     * @private
     * @override
     */
    initComponent : function() {
        this.callParent();

        this.addEvents(
            /**
             * @event maximize
             * 포틀릿창이 최대화 되었을때 발생
             *
             * @param {Ext.app.Portlet} this
             */
            'maximize',

            /**
             * @event restore
             * 최대화 되었던 포틀릿이 원래 크기로 복원되었을때 발생
             *
             * @param {Ext.app.Portlet} this
             */
            'restore',

            /**
             * @event pin
             * 포틀릿이 이동 잠금 되었을때 발생
             *
             * @param {Ext.app.Portlet} this
             */
            'pin',

            /**
             * @event unpin
             * 포틀릿이 이동 잠금 해제 되었을때 발생
             *
             * @param {Ext.app.Portlet} this
             */
            'unpin',

            /**
             * @event gear
             * gear 툴을 클릭하였을때 발생
             *
             * @param {Ext.app.Portlet} this
             */
            'gear',

            /**
             * @event help
             * help 툴을 클릭하였을때 발생
             *
             * @param {Ext.app.Portlet} this
             */
            'help',

            /**
             * @event dragover
             * 포틀릿이 Drag Over 되었을때 발생
             *
             * @param {JSONObject} event
             */
            'dragover',

            /**
             * @event dragenter
             * 포틀릿이 Drag Enter 되었을때 발생
             *
             * @param {JSONObject} event
             */
            'dragenter',

            /**
             * @event dragout
             * 포틀릿이 Drag Out 되었을때 발생
             *
             * @param {JSONObject} event
             */
            'dragout',

            /**
             * @event drop
             * 포틀릿이 Drop 되었을때 발생
             *
             * @param {JSONObject} event
             */
            'drop'
        );
    },

    /**
     * @private
     * @override
     */
    onRender: function(ct, positi, on) {
        var me = this;
        me.callParent(arguments);
        me.focusEl = me.el;

        // Double clicking a header will toggleMaximize
        if (me.maximizable) {
            me.header.on({
                dblclick: {
                    fn: me.toggleMaximize,
                    element: 'el',
                    scope: me
                }
            });
        }

        // 포틀릿 내부 Body 컨텐츠의 사이즈가 변경된 경우 포틀릿 컨테이너에 적용(jQuery Resize Event 플러그인 사용)
        $(me.body.dom).resize(function(event, width, height) {
            var element = $(this);
            var portalPanel = me.findParentByType('portalpanel');
            if (portalPanel) {
                portalPanel.doLayout();
            }

            var windowPanel = me.findParentByType('window');
            if (windowPanel) {
                // Suspend Events
                me.suspendEvents();
                
                me.setHeight('');
                me.body.setHeight('');

                // Resume Events
                me.resumeEvents();

                windowPanel.doLayout();
            }
        });
    },

    /**
     * @private
     * @override
     * Contribute class-specific tools to the header.
     * Called by Panel's initTools.
     */
    addTools: function() {
        var me = this;

        // Call Panel's initTools
        me.callParent();

        if (me.draggable && me.pinable) {
            me.addTool({
                type: 'pin',
                handler: Ext.Function.bind(me.pin, me, [])
            });
            me.addTool({
                type: 'unpin',
                handler: Ext.Function.bind(me.unpin, me, []),
                hidden: true
            });
        }

        if (me.maximizable) {
            me.addTool({
                type: 'maximize',
                handler: Ext.Function.bind(me.maximize, me, [])
            });
            me.addTool({
                type: 'restore',
                handler: Ext.Function.bind(me.restore, me, []),
                hidden: true
            });
        }

        if (me.settable) {
            me.addTool({
                type: 'gear',
                handler: Ext.Function.bind(me.onGear, me, [])
            });
        }

        if (me.helpable) {
            me.addTool({
                type: 'help',
                handler: Ext.Function.bind(me.onHelp, me, [])
            });
        }
    },

    /**
     * @private
     * 현재 포틀릿을 제외한 모든 포틀릿을 show 한다.(showOther 와 hideOther 의 토글)
     */
    showOther: function() {
        var me = this;

        // 1. 현재 portalcolumn 내 다른 포틀릿 hide
        var portalColumn = me.findParentByType('portalcolumn');
        if (portalColumn) {
            for (var i = 0; i < portalColumn.items.getCount(); i++) {
                if (me != portalColumn.items.getAt(i)) {
                    portalColumn.items.getAt(i).show();
                }
            }

            // 2. 현재 portalpanel 내 다른 portalcolumn hide
            var portalPanel = portalColumn.findParentByType('portalpanel');
            if (portalPanel) {
                for (var i = 0; i < portalPanel.items.getCount(); i++) {
                    if (portalColumn != portalPanel.items.getAt(i)) {
                        portalPanel.items.getAt(i).show();
                    }
                }

                // 3. 다른 portalpanel hide
                var panel = portalPanel.findParentByType('panel');
                if (panel) {
                    for (var i = 0; i < panel.items.getCount(); i++) {
                        if (portalPanel != panel.items.getAt(i)) {
                            panel.items.getAt(i).show();
                        }
                    }
                }
            }
        }
    },

    /**
     * @private
     * 현재 포틀릿을 제외한 모든 포틀릿을 hide 한다.(showOther 와 hideOther 의 토글)
     */
    hideOther: function() {
        var me = this;

        // 1. 현재 portalcolumn 내 다른 포틀릿 hide
        var portalColumn = me.findParentByType('portalcolumn');
        if (portalColumn) {
            for (var i = 0; i < portalColumn.items.getCount(); i++) {
                if (me != portalColumn.items.getAt(i)) {
                    portalColumn.items.getAt(i).hide();
                }
            }

            // 2. 현재 portalpanel 내 다른 portalcolumn hide
            var portalPanel = portalColumn.findParentByType('portalpanel');
            if (portalPanel) {
                for (var i = 0; i < portalPanel.items.getCount(); i++) {
                    if (portalColumn != portalPanel.items.getAt(i)) {
                        portalPanel.items.getAt(i).hide();
                    }
                }

                // 3. 다른 portalpanel hide
                var panel = portalPanel.findParentByType('panel');
                if (panel) {
                    for (var i = 0; i < panel.items.getCount(); i++) {
                        if (portalPanel != panel.items.getAt(i)) {
                            panel.items.getAt(i).hide();
                        }
                    }
                }
            }
        }
    },

    /**
     * 툴바의 maximize 버튼 처리 핸들러. 포틀릿을 최대 사이즈로 확장한다.(maximize 와 restore 의 토글)
     *
     * @return {Ext.app.Portlet} this
     */
    maximize: function() {
        var me = this;

        if (me.maximizable && !me.maximized) {
            // Suspend Events
            me.suspendEvents();

            // 0. 패널 확장
            me.expand(false);

            // 1. 복원시 필요한 정보 저장
            me.restoreColumnWidth = me.findParentByType('portalcolumn').columnWidth;

            // 2. 컬럼을 최대로 확장
            me.findParentByType('portalcolumn').columnWidth = 1;

            // 3. 드래그 잠금
            this.setDraggable(false);

            // 4. 툴바 show/hide 처리
            if (me.maximizable) {
                me.tools.maximize.hide();
                me.tools.restore.show();
            }
            this.hideTools([me.tools.maximize, me.tools.restore, me.tools.gear, me.tools.help]);

            // 5. 다른 포틀릿 hide
            me.hideOther();

            me.maximized = true;
            me.findParentByType('portalpanel').doLayout();

            // Resume Events
            me.resumeEvents();

            me.fireEvent('maximize', me);
        }
        return me;
    },

    /**
     * 툴바의 restore 버튼 처리 핸들러. 포틀릿을 원래 사이즈로 되돌린다.(maximize 와 restore 의 토글)
     *
     * @return {Ext.app.Portlet} this
     */
    restore: function() {
        var me = this,
            tools = me.tools;

        if (me.maximizable && me.maximized) {
            // Suspend Events
            me.suspendEvents();

            // 1. 포틀릿 사이즈 복원
            me.findParentByType('portalcolumn').columnWidth = me.restoreColumnWidth;

            // 2. 툴바 show/hide 처리
            if (me.maximizable) {
                me.tools.maximize.show();
                me.tools.restore.hide();
            }
            this.showTools();

            // 3. 드래그 잠금 해제
            if (!me.pinned) {
                this.setDraggable(true);
            }

            // 4. 다른 포틀릿 show
            me.showOther();

            // 5. Unset old columnWidth/sizing
            delete me.restoreColumnWidth;

            me.maximized = false;
            me.findParentByType('portalpanel').doLayout();

            // Resume Events
            me.resumeEvents();

            me.fireEvent('restore', me);
        }
        return me;
    },

    /**
     * maximize 와 restore 의 토글 메소드
     *
     * @return {Ext.app.Portlet} this
     */
    toggleMaximize: function() {
        return this[this.maximized ? 'restore' : 'maximize']();
    },

    /**
     * 툴바의 pin 버튼 처리 핸들러. 포틀릿을 이동 불가하도록 한다.(pin 과 unpin 의 토글)
     *
     * @return {Ext.app.Portlet} this
     */
    pin: function() {
        var me = this;

        if (!me.pinned) {
            if (me.pinable) {
                me.tools.pin.hide();
                me.tools.unpin.show();
            }

            me.pinned = true;

            this.setDraggable(false);

            me.fireEvent('pin', me);
        }
        return me;
    },

    /**
     * 툴바의 unpin 버튼 처리 핸들러. 포틀릿을 이동가능하도록 한다.(pin 과 unpin 의 토글)
     *
     * @return {Ext.app.Portlet} this
     */
    unpin: function() {
        var me = this;

        if (me.pinned) {
            if (me.pinable) {
                me.tools.pin.show();
                me.tools.unpin.hide();
            }
            me.pinned = false;

            this.setDraggable(true);

            me.fireEvent('unpin', me);
        }
        return me;
    },

    /**
     * pin 과 unpin 의 토글 메소드
     *
     * @return {Ext.app.Portlet} this
     */
    togglePin: function() {
        return this[this.pinned ? 'unpin' : 'pin']();
    },

    /**
     * 모든 툴을 hide 한다.
     *
     * @param {Array<Ext.panel.Tool>}  excepts 감춤 예외 툴
     */
    hideTools: function(excepts) {
        this.savedTools = new Array();

        var tools = Ext.ComponentQuery.query('tool', this);

        if (!Ext.isDefined(excepts)) excepts = new Array();

        /**
         * 툴 Array 에서 해당 툴이 존재하는지 체크
         */
        var isExists = function(srcTool, toolArray) {
            for (var i = 0; i < toolArray.length; i++) {
                if (srcTool.getId() == toolArray[i].getId()) return true;
            }
            return false;
        }

        for (var i = 0; i < tools.length; i++) {
            if (!isExists(tools[i], excepts)) {
                if (!tools[i].hidden) {
                    tools[i].hide();
                    this.savedTools.push(tools[i]);
                }
            }
        }
    },

    /**
     * hideTools 에 의해 감춰진 툴을 show 한다.
     */
    showTools: function() {
        if (this.savedTools) {
            for (var i = 0; i < this.savedTools.length; i++) {
                this.savedTools[i].show();
            }
        }

        delete this.savedTools;
    },

    /**
     * hideTools 와 showTools 의 토글 메소드
     *
     * @param {Array<Ext.panel.Tool>} excepts 감춤 예외 툴
     */
    toggleTools: function(excepts) {
        return this[this.savedTools ? 'showTools' : 'hideTools'](except);
    },

    /**
     * 포틀릿 이동 가능여부를 변경한다.
     *
     * @param {boolean} draggable 이동 가능 여부
     */
    setDraggable: function(draggable) {
        if (draggable) {
            if (this.dd) {
                this.dd.unlock();
                this.header.getEl().setStyle('cursor', 'move');
            }
        } else {
            if (this.dd) {
                this.dd.lock();
                this.header.getEl().setStyle('cursor', 'default');
            }
        }
        this.draggable = draggable;
    },

    /**
     * 포틀릿 Pin 가능여부를 변경한다.
     *
     * @param {boolean} pinnable Pin 가능 여부
     */
    setPinnable: function(pinnable) {
        if (pinnable) {
            if (this.pinned) {
                this.tools.pin.hide();
                this.tools.unpin.show();
            } else {
                this.tools.pin.show();
                this.tools.unpin.hide();
            }
        } else {
            if (this.pinned) {
                this.tools.unpin.hide();
            } else {
                this.tools.pin.hide();
            }
        }
        this.pinnable = pinnable;
    },

    /**
     * 포틀릿 Setting 가능여부를 변경한다.
     *
     * @param {boolean} settable Setting 가능 여부
     */
    setSettable: function(settable) {
        if (settable) {
            this.tools.gear.show();
        } else {
            this.tools.gear.hide();
        }
        this.settable = settable;
    },

    /**
     * 포틀릿 Close 가능여부를 변경한다.
     *
     * @param {boolean} closable Close 가능 여부
     */
    setClosable: function(closable) {
        if (closable) {
            this.tools.close.show();
        } else {
            this.tools.close.hide();
        }
        this.closable = closable;
    },

    /**
     * 포틀릿 Maximize 가능여부를 변경한다.
     *
     * @param {boolean} maximizable Maximize 가능 여부
     */
    setMaximizable: function(maximizable) {
        if (maximizable) {
            this.tools.maximize.show();
            this.tools.restore.hide();
        } else {
            this.tools.maximize.hide();
            this.tools.restore.hide();
        }
        this.maximizable = maximizable;
    },

    /**
     * 툴바의 gear 버튼 처리 핸들러. (setting 이벤트 throw)
     *
     * @return {Ext.app.Portlet} this
     */
    onGear: function() {
        var me = this;

        me.fireEvent('gear', me);
        return me;
    },

    /**
     * 툴바의 help 버튼 처리 핸들러. (help 이벤트 throw)
     *
     * @return {Ext.app.Portlet} this
     */
    onHelp: function() {
        var me = this;

        me.fireEvent('help', me);
        return me;
    },

    // TODO : }

    // Override Panel's default doClose to provide a custom fade out effect
    // when a portlet is removed from the portal
    doClose: function() {
        this.el.animate({
            opacity: 0,
            callback: function() {
                this.fireEvent('close', this);
                this[this.closeAction]();
            },
            scope: this
        });
    }
});