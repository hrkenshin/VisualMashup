/**
 * @class VM.opensocial.SavePage
 * @extends Ext.form.Panel
 *
 * 占썬끋逾귨옙��� 揶쏉옙��占쎄쑵猷뤄옙占쏙옙�낆쨮占쏙옙 占쏙옙占썩뫀瑗�占쎈��믭옙占�*
 * @author 占쎈똻�낁쳸占�*/
Ext.define('VM.opensocial.SavePage', {
    extend: 'Ext.form.Panel',

    requires: [
        'VM.opensocial.common.Properties'
    ],

    /**
     * 占쎌빘苑�옙占�    * @param {JsonObject} config
     */
    constructor: function(config) {
        Ext.apply(this, config);

        Ext.apply(this, {
            frame: true,
            width: 400,
            defaults: {
                anchor: '100%',
                allowBlank: false,
                msgTarget: 'side',
                labelWidth: 70,
                labelSeparator: ' '
            },
            items: [
                {
                    xtype: 'textfield',
                    name: 'title',
                    fieldLabel: 'Title',
                    maxLength: 30
                },
                {
                    xtype: 'textareafield',
                    name: 'description',
                    fieldLabel: 'Description',
                    allowBlank: true,
                    maxLength: 200
                }
            ],
            buttonAlign: 'center',
            buttons: [
                {
                    text: 'Upload',
                    handler: function() {
                        var form = this.up('form').getForm();

                        if (form.isValid()) {
                        	var jsonobj = eval("("+$("input[name=pageMetaData]").val()+")");
                        	jsonobj.title=form.findField('title').getValue();
                        	jsonobj.description=form.findField('description').getValue();
                        	//alert(jsonobj)
                        	$.ajax({
                                type: 'post'
                                , async: true
                                , url: '/uengine-web/saveVMPage.jsp'
                                , data: {"title":form.findField('title').getValue(), "description":form.findField('description').getValue(), "vmmeta":$("input[name=pageMetaData]").val()}
                                , success: function(data) {
                                	alert('저장되었습니다.');
                                  }
                                , error: function(data, status, err) {
                                	console.log("error forward : "+data);
                                    alert('서버와의 통신이 실패했습니다.');
                                  }
                            });
                        	

                        }
                    }
                },
                {
                    text: 'Cancel',
                    handler: function() {
                        var window = this.findParentByType('window');
                        if (window != null) window.destroy();
                    }
                }
            ]
        });

        this.callParent(arguments);
    }
});