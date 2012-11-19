shindig.BaseIfrGadget.prototype.queryIfrGadgetType_ = function() {
    // Get the gadget metadata and check if the gadget requires the 'pubsub-2'
    // feature.  If so, then we use OpenAjax Hub in order to create and manage
    // the iframe.  Otherwise, we create the iframe ourselves.
    var request = {
        context: {
            country: 'default',
            language: 'default',
            view: 'default',
            container: 'default'
        },
        gadgets: [
            {
                url: this.specUrl,
                // TODO : MODIFIED BY 이승백 (모듈ID 설정)
//    moduleId: 1
                moduleId: this.moduleId || 1
            }
        ]
    };

    var makeRequestParams = {
        'CONTENT_TYPE' : 'JSON',
        'METHOD' : 'POST',
        'POST_DATA' : gadgets.json.stringify(request)
    };

    var url = this.serverBase_ + 'metadata?st=' + this.secureToken;

    gadgets.io.makeNonProxiedRequest(url,
        handleJSONResponse,
        makeRequestParams,
        {'Content-Type':'application/javascript'}
    );

    var gadget = this;

    function handleJSONResponse(obj) {
        // TODO : ADDED BY 이승백 (가젯 메타정보를 가젯 인스턴스에 추가하여 추후 활용)
        gadget.metadata = obj.data.gadgets[0];

        var requiresPubSub2 = false;
        var arr = obj.data.gadgets[0].features;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] === 'pubsub-2') {
                requiresPubSub2 = true;
                break;
            }
        }
        var subClass = requiresPubSub2 ? shindig.OAAIfrGadget : shindig.IfrGadget;
        for (var name in subClass) if (subClass.hasOwnProperty(name)) {
            gadget[name] = subClass[name];
        }
    }
};

// ---------
// IfrGadget

shindig.IfrGadget = {
    getMainContent: function(continuation) {
        var iframeId = this.getIframeId();
        gadgets.rpc.setRelayUrl(iframeId, this.serverBase_ + this.rpcRelay);
        gadgets.rpc.setAuthToken(iframeId, this.rpcToken);
        continuation('<div class="' + this.cssClassGadgetContent + '"><iframe id="' +
            iframeId + '" name="' + iframeId + '" class="' + this.cssClassGadget +
            '" src="about:blank' +
            '" frameborder="no" scrolling="no"' +
            (this.height ? ' height="' + this.height + '"' : '') +
            (this.width ? ' width="' + this.width + '"' : '') +
            '></iframe></div>');
    },

    finishRender: function(chrome) {
        // TODO : MODIFIED BY 이승백(가젯컨테이너에서 가젯 이동시 내용이 사라지는 문제 패치)
        this.refresh();
//    window.frames[this.getIframeId()].location = this.getIframeUrl();
    },

    getIframeUrl: function(random) {
        return this.serverBase_ + 'ifr?' +
            'container=' + this.CONTAINER +
            '&mid=' + this.id +
            '&nocache=' + shindig.container.nocache_ +
            '&country=' + shindig.container.country_ +
            '&lang=' + shindig.container.language_ +
            '&view=' + shindig.container.view_ +
            (this.specVersion ? '&v=' + this.specVersion : '') +
            (shindig.container.parentUrl_ ? '&parent=' + encodeURIComponent(shindig.container.parentUrl_) : '') +
            (this.debug ? '&debug=1' : '') +
            this.getAdditionalParams() +
            this.getUserPrefsParams() +
            (this.secureToken ? '&st=' + this.secureToken : '') +
            '&url=' + encodeURIComponent(this.specUrl) +
            (this.viewParams ?
                '&view-params=' + encodeURIComponent(gadgets.json.stringify(this.viewParams)) : '') +
            (random ? '&r=' + random : '') +
            '#rpctoken=' + this.rpcToken +
            (this.hashData ? '&' + this.hashData : '');
    }
};


// ---------
// OAAIfrGadget

shindig.OAAIfrGadget = {
    getMainContent: function(continuation) {
        continuation('<div id="' + this.cssClassGadgetContent + '-' + this.id +
            '" class="' + this.cssClassGadgetContent + '"></div>');
    },

    finishRender: function(chrome) {
        var iframeAttrs = {
            className: this.cssClassGadget,
            frameborder: 'no',
            scrolling: 'no'
        };
        if (this.height) {
            iframeAttrs.height = this.height;
        }
        if (this.width) {
            iframeAttrs.width = this.width;
        }

        new OpenAjax.hub.IframeContainer(
            gadgets.pubsub2router.hub,
            this.getIframeId(),
            {
                Container: {
                    onSecurityAlert: function(source, alertType) {
                        gadgets.error('Security error for container ' + source.getClientID() + ' : ' + alertType);
                        source.getIframe().src = 'about:blank';
                        // for debugging
                        //          },
                        //          onConnect: function( container ) {
                        //            gadgets.log("++ connected: " + container.getClientID());
                    }
                },
                IframeContainer: {
                    parent: document.getElementById(this.cssClassGadgetContent + '-' + this.id),
                    uri: this.getIframeUrl(),
                    tunnelURI: shindig.uri(this.serverBase_ + this.rpcRelay).resolve(shindig.uri(window.location.href)),
                    iframeAttrs: iframeAttrs
                }
            }
        );
    },

    getIframeUrl: function(random) {
        return this.serverBase_ + 'ifr?' +
            'container=' + this.CONTAINER +
            '&mid=' + this.id +
            '&nocache=' + shindig.container.nocache_ +
            '&country=' + shindig.container.country_ +
            '&lang=' + shindig.container.language_ +
            '&view=' + shindig.container.view_ +
            (this.specVersion ? '&v=' + this.specVersion : '') +
            //      (shindig.container.parentUrl_ ? '&parent=' + encodeURIComponent(shindig.container.parentUrl_) : '') +
            (this.debug ? '&debug=1' : '') +
            this.getAdditionalParams() +
            this.getUserPrefsParams() +
            (this.secureToken ? '&st=' + this.secureToken : '') +
            '&url=' + encodeURIComponent(this.specUrl) +
            //      '#rpctoken=' + this.rpcToken +
            (this.viewParams ?
                '&view-params=' + encodeURIComponent(gadgets.json.stringify(this.viewParams)) : '') +
            (random ? '&r=' + random : '') +
            //      (this.hashData ? '&' + this.hashData : '');
            (this.hashData ? '#' + this.hashData : '');
    }
};

shindig.StaticLayoutManager.prototype.getGadgetChrome = function(gadget) {
    // TODO : MODIFIED BY 이승백(pubsub-2 사용시 가젯크롬ID를 결정 해주어야 함)
    var chromeId = VM.opensocial.Gadget.GADGET_CHROME_PREFIX + gadget.id;
//  var chromeId = this.gadgetChromeIds_[gadget.id];
    return chromeId ? document.getElementById(chromeId) : null;
};

shindig.Container.prototype.addGadget = function(gadget) {
    // TODO : MODIFIED BY 이승백(GadgetId 를 직접 지정하기 위해)
    if (!gadget.id) gadget.id = this.getNextGadgetInstanceId();
//  gadget.id = this.getNextGadgetInstanceId();
    this.gadgets_[this.getGadgetKey_(gadget.id)] = gadget;
};