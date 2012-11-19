Ext.define('VM.opensocial.common.RESTProxy', {
    extend: 'Ext.data.proxy.Ajax',

    alias: 'proxy.vmrest',
    alternateClassName: ['VM.RESTProxy'],

    requires: [
        'VM.opensocial.common.Properties',
        'VM.opensocial.common.CustomFilters'
    ],

    /**
     * @property actionMethods
     * Mapping of action name to HTTP request method. In the basic AjaxProxy these are set to 'GET' for 'read' actions and 'POST'
     * for 'create', 'update' and 'destroy' actions. The {@link Ext.data.proxy.Rest} maps these to the correct RESTful methods.
     */
    actionMethods: {
        create : 'POST',
        read   : 'POST',
        update : 'PUT',
        destroy: 'DELETE'
    },

    /**
     * @Override
     */
    constructor: function(config) {
        var me = this;

        config = config || {};
        me.callParent([config]);

        var vmModel = Ext.create(config.model, {});

        me.recordType = vmModel.persistenceProperty;

        me.api = config.api || {
            create  : VM.Properties.BASE_REST_URL + '/' + me.recordType + '/create',
            read    : VM.Properties.BASE_REST_URL + '/' + me.recordType + '/search',
            update  : VM.Properties.BASE_REST_URL + '/' + me.recordType + '/update',
            destroy : VM.Properties.BASE_REST_URL + '/' + me.recordType + '/delete'
        };

        me.jsonData = {
            '@orderBy': {},
            '@size': VM.Properties.ROW_COUNT_PER_PAGE,
            '@offset': 0
        };

        me.accept = config.accept || 'xml';

        if (me.accept == 'xml') {
            me.reader = config.reader || {
                type: 'xml',
                model: config.model,
                record: me.recordType || 'data',
                root: '/',
                totalProperty  : '@totalCnt',
                successProperty: '@isSuccess'
            };
        } else {
            me.reader = config.reader || {
                type: 'json',
                model: config.model,
                root: me.recordType || 'data',
                totalProperty  : '@totalCnt',
                successProperty: '@isSuccess'
            };
        }
        me.writer = config.writer || {
            type: 'json',
            writeAllFields: true,
            root: me.recordType || 'data'
        };
        me.success = config.success || function(response, options) {
        };
        me.failure = config.failure || function(response, options) {
            if (typeof console !== 'undefined') console.log(response.responseText);
            try {
                var errorMessage = response.responseText;
                if (response.isTimeout) {
                    errorMessage = "요청대기 타임아웃이 발생하였습니다. 잠시후에 시도해 주세요.";
                } else if (response.responseXML != null &&
                    response.responseXML.getElementsByTagName('message') != null) {
                    errorMessage = response.responseXML.getElementsByTagName('message')[0].childNodes[0].nodeValue;
                }

                Ext.MessageBox.show({
                    title: Ext.String.capitalize(options.action) + ' Failed!',
                    msg: errorMessage,
                    buttons: Ext.MessageBox.OK,
                    icon: Ext.MessageBox.ERROR
                });
            } catch(exception) {
                if (typeof console !== 'undefined') console.log(exception);
            }
        };
    },

    /**
     * @Override
     */
    doRequest: function(operation, callback, scope) {

        var writer = this.getWriter(),
            request = this.buildRequest(operation, callback, scope);

        if (operation.allowWrite()) {
            request = writer.write(request);
            // 요청용 JSON 데이타를 설정
            this.setJsonData(request);
        } else {
            // 요청용 JSON 데이타를 설정
            this.setJsonData(operation);
        }

        Ext.apply(request, {
            headers       : this.headers || {'Accept': 'application/' + this.accept},
            params        : {},
            jsonData      : this.jsonData,
            timeout       : this.timeout || VM.Properties.TIME_OUT,
            reader        : this.reader,
            success       : this.success,
            failure       : this.failure,
            scope         : this,
            callback      : this.createRequestCallback(request, operation, callback, scope),
            method        : this.getMethod(request),
            disableCaching: false // explicitly set it to false, ServerProxy handles caching
        });

        Ext.Ajax.request(request);

        return request;
    },

    /**
     * @private
     * 요청용 JSON 데이타를 설정한다.
     * @param {Object} operation
     */
    setJsonData: function(operation) {
        switch (operation.action) {
            case 'read' :
                this.jsonData = {
                    '@orderBy': {},
                    '@size': VM.Properties.ROW_COUNT_PER_PAGE,
                    '@offset': 0
                };

                if (operation.sorters) {
                    this.jsonData['@orderBy'] = operation.sorters[0].property + ' ' + operation.sorters[0].direction;
                }
                if (operation.limit) {
                    this.jsonData['@size'] = operation.limit;
                }
                if (operation.start) {
                    this.jsonData['@offset'] = operation.start;
                }

                if (operation.condition) {
                    if (Ext.isArray(operation.condition)) {
                        this.queryCondition = operation.condition;
                    } else {
                        this.queryCondition = undefined;
                    }
                }

                if (operation.params && operation.params.filters) {
                    this.filterCondition = Ext.JSON.decode(operation.params.filters);
                } else {
                    this.filterCondition = undefined;
                }

                if (Ext.isArray(this.queryCondition) || Ext.isArray(this.filterCondition)) {
                    this.jsonData['condition'] = new Array();
                    if (Ext.isArray(this.queryCondition)) {
                        this.jsonData['condition'] = Ext.Array.merge(this.jsonData['condition'], this.queryCondition);
                    }
                    if (Ext.isArray(this.filterCondition)) {
                        this.jsonData['condition'] = Ext.Array.merge(this.jsonData['condition'], this.filterCondition);
                    }
                }
                break;
            case 'destroy' :
                var idArray = new Array();
                for (i = 0; i < operation.records.length; i++) {
                    idArray.push(String(operation.records[i].getId()));
                }
                this.jsonData = {
                    'id': idArray
                };
                break;
            case 'create' :
                var dataArray = new Array();
                for (var i = 0; i < operation.records.length; i++) {
                    dataArray.push(operation.records[i][this.recordType]);
                }

                this.jsonData = {};
                this.jsonData[this.recordType] = dataArray;
                break;
            case 'update' :
                var dataArray = new Array();

                if (Ext.isArray(operation.jsonData[this.recordType])) {
                    for (var i = 0; i < operation.jsonData[this.recordType].length; i++) {
                        dataArray.push(operation.jsonData[this.recordType][i]);
                    }
                } else {
                    dataArray.push(operation.jsonData[this.recordType]);
                }

                this.jsonData = {};
                this.jsonData[this.recordType] = dataArray;
                break;
            default:
                break;
        }
    }
});