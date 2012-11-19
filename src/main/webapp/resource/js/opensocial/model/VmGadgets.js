Ext.define('VM.opensocial.model.VmGadgets', {
    extend: 'Ext.data.Model',

    fields: [
        { name: 'seq_no',           type: 'int' },
        { name: 'title',            type: 'string' },
        { name: 'title_url',        type: 'string' },
        { name: 'description',      type: 'string' },
        { name: 'author',           type: 'string' },
        { name: 'author_email',     type: 'string' },
        { name: 'screenshot',       type: 'string' },
        { name: 'thumbnail',        type: 'string' },
        { name: 'type',             type: 'string' },
        { name: 'path',             type: 'string' },
        { name: 'url',              type: 'string' },
        { name: 'xml_contents',     type: 'string' }
    ],

    idProperty: 'seq_no',

    persistenceProperty: 'vm_gadgets'
});