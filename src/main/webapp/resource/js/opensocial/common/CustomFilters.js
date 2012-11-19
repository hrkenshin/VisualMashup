Ext.define('VM.opensocial.common.CustomFilters', {

    extend : 'Ext.ux.grid.FiltersFeature',
    alias: 'feature.customFilters',

    /**
     * @Override
     * @param filters
     */
    buildQuery : function (filters) {

        var p = {}, i, f, root, dataPrefix, key, tmp,
            len = filters.length;

        if (!this.encode) {
//            for (i = 0; i < len; i++) {
//                f = filters[i];
//                root = [this.paramPrefix, '[', i, ']'].join('');
//                p[root + '[field]'] = f.field;
//
//                dataPrefix = root + '[data]';
//                for (key in f.data) {
//                    p[[dataPrefix, '[', key, ']'].join('')] = f.data[key];
//                }
//            }
            //변경된 부분.
            p['filters'] = this.convertQuery(filters);
        } else {
            tmp = [];
            for (i = 0; i < len; i++) {
                f = filters[i];
                tmp.push(Ext.apply(
                    {},
                    {field: f.field},
                    f.data
                ));
            }
            // only build if there is active filter
            if (tmp.length > 0) {
                p[this.paramPrefix] = Ext.JSON.encode(tmp);
            }
        }

        return p;
    },

    /**
     * @private
     * Rest에 맞는 조건으로 변환하는 작업을 한다.
     */
    convertQuery:function(filters) {
        var p = {}, i, f, root, key, len = filters.length;
        var queryString = '';
        for (i = 0; i < len; i++) {
            f = filters[i];

            root = [this.paramPrefix, '[', i, ']'].join('');
            p['@fieldName'] = f.field;

            for (key in f.data) {
                if (key == 'type') {
                    p['@fieldType'] = 'String';
                    if (f.data[key] == 'list') {
                        p['@operator'] = 'In';
                    } else if (f.data[key] == 'string') {
//                        p['@operator'] = 'Equal';
                        p['@operator'] = 'Like';
                    } else if (f.data[key] == 'date') {
                        p['@fieldType'] = 'Date';
                    } else if (f.data[key] == 'boolean') {
                        p['@fieldType'] = 'Boolean';
                    } else if (f.data[key] == 'numeric') {
                        p['@fieldType'] = 'Int';
                    }
                } else if (key == 'comparison') {
                    if (f.data[key] == 'lt') {
                        p['@operator'] = 'LessThan';
                    } else if (f.data[key] == 'gt') {
                        p['@operator'] = 'GreaterThan';
                    } else if (f.data[key] == 'eq') {
                        p['@operator'] = 'Equal';
                    }
                } else if (key == 'value') {
                    if (p['@fieldType'] == 'Date') {
                        p['$'] = Ext.Date.format(Ext.Date.parseDate(f.data[key], 'm/d/Y'), 'Y-m-d H:i:s');
                    } else if (p['@fieldType'] == 'String') {
                        // Like 검색 처리
                        p['$'] = '%' + f.data[key].toString() + '%';
                    } else {
                        p['$'] = f.data[key].toString();
                    }
                }
            }

            if (queryString != '') queryString += ',';
            queryString += Ext.JSON.encode(p);
        }

        return queryString != '' ? '[' + queryString + ']' : undefined;
    }
});