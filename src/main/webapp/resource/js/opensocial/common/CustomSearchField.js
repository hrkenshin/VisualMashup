Ext.define('VM.opensocial.common.CustomSearchField', {

    extend: 'Ext.ux.form.SearchField',
    alias: 'widget.customSearchField',

    propertyFieldId: null,

    onTrigger1Click : function() {

        var me = this,
            store = me.store,
            proxy = store.getProxy();

        me.setValue('');
        store.currentPage = 1;
        store.load({
            condition: {}
        });
        me.triggerEl.item(0).setDisplayed('none');
        me.doComponentLayout();
    },

    onTrigger2Click : function() {

        var me = this,
            store = me.store,
            proxy = store.getProxy(),
            value = me.getValue();

        if (value.length < 1) {
            me.onTrigger1Click();
            return;
        }

        store.currentPage = 1;
        store.load({
            condition: [
                {
                    '@fieldName': this.propertyFieldId ? Ext.getCmp(this.propertyFieldId).getValue() : '',
                    '@fieldType' : 'String',
                    '@operator' : 'Equal',
                    '$': value
                }
            ]
        });

        me.triggerEl.item(0).setDisplayed('block');
        me.doComponentLayout();
    }
});
