/**
 * @class VM.opensocial.UploadForm
 * @extends Ext.form.Panel
 *
 * 오픈소셜 가젯 임포트(업로드) 폼 패널 클래스
 *
 * @author 이승백
 */
Ext.define('VM.opensocial.UploadForm', {
	extend: 'Ext.form.Panel',

	requires: [
		'VM.opensocial.common.Properties'
	],

	/**
	 * 생성자
	 * @param {JsonObject} config
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
				},
				{
					xtype: 'filefield',
					name: 'file',
					emptyText: 'Select Gagget XML',
					fieldLabel: 'XML',
					buttonText: '',
					buttonConfig: {
						iconCls: 'icon_browse'
					}
				},
				{
					xtype: 'hidden',
					name: 'fileName'
				}
			],
			buttonAlign: 'center',
			buttons: [
				{
					text: 'Upload',
					handler: function() {
						var form = this.up('form').getForm();
						form.findField('fileName').setValue(form.findField('file').getValue());

						if (form.isValid()) {
							form.submit({
								method:'POST',
								url: VM.Properties.BASE_REST_URL + '/vm_gadgets/uploadGadget',
								timeout: VM.Properties.TIME_OUT,
								waitMsg: 'Uploading...',
								success: function(fp, action) {
									var window = this.findParentByType('window');
									if (window != null) window.destroy();
								},
								failure:function(fp, action) {
									switch (action.failureType) {
									case Ext.form.action.Action.CLIENT_INVALID:
										Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
										break;
									case Ext.form.action.Action.CONNECT_FAILURE:
										Ext.Msg.alert('Failure', 'Ajax communication failed');
										break;
									case Ext.form.action.Action.SERVER_INVALID:
										Ext.MessageBox.show({
											title: 'Failure',
											msg: action.result.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.ERROR
										});
										break;
									}
									var window = this.findParentByType('window');
									if (window != null) window.destroy();
								},
								scope: this
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