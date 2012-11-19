/**
 * VM Property 정의 클래스
 * @class VM.opensocial.common.Properties
 * @singleton
 * @author 이승백
 */
Ext.define('VM.opensocial.common.Properties', {
    alternateClassName: ['VM.Properties'],

    statics: {
        BASE_CONTEXT : VM_SESSION.BASE_CONTEXT,
        BASE_REST_URL : VM_SESSION.BASE_CONTEXT + '/vm',
        STATUSBAR_ID : 'ID_statusbar',
        TABPANEL_ID : 'ID_tabpanel',
        ROW_COUNT_PER_PAGE : 100,
        VALUE_DELIMITER : ',',
        SERVICE_VERSION: '1.0.0',
        TIME_OUT: 30000 // 30sec
    }
});