function _fetch(fetch_promise, timeout) {
    var abort_fn = null;

    //这是一个可以被reject的promise
    var abort_promise = new Promise(function(resolve, reject) {
        abort_fn = function() {
            reject('abort promise');
        };
    });

    //这里使用Promise.race，以最快 resolve 或 reject 的结果来传入后续绑定的回调
    var abortable_promise = Promise.race([
        fetch_promise,
        abort_promise
    ]);

    setTimeout(function() {
        abort_fn();
    }, timeout);

    return abortable_promise;
}

FETCH_TIMEOUT = 5000;

export async function Get({url,params,success,error}){
    console.log('*******************request start**********************');
    console.log('url:' + url);

    if(url==null){
        return ;
    }

    if(params!=null && typeof(params) == 'object'){
        let paramStr = '';
        Object.keys(params).forEach(key =>{
            paramStr += key + '=' + params[key] + '&';
        });
        paramStr = paramStr.substring(0,paramStr.length-1);

        url += '?'+paramStr;

        console.log('request url:' + url);
    }

    await _fetch(
        fetch(url,{method: 'GET',}),
        FETCH_TIMEOUT)
        .then(response =>response.json())
        .then(respData => {
            console.log(respData);
            if(respData.code!=null&&respData.code==0){
                if(typeof error === 'function'){
                    error(respData);
                }
                return;
            }

            if(respData.code!=null
                && respData.code==1
                && typeof success === 'function'){
                success(respData);
            }

        }).catch((errResp) => {
        if(typeof error === 'function'){
            error(errResp);
        }
        console.log(errResp.toString());
    });

    console.log('*******************request end**********************');
}


export async function Post({url,params,success,error}){
    console.log('*******************request start**********************');
    console.log('url:' + url);

    if(url==null){
        return ;
    }

    await fetch(url,{
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(params)
    })
        .then(response =>response.json())
        .then(respData => {
            console.log(respData);
            if(respData.code!=null&&respData.code==0){
                if(typeof error === 'function'){
                    error(respData);
                }
                return;
            }

            if(respData.code!=null
                && respData.code==1
                && typeof success === 'function'){
                success(respData);
            }

        }).catch((errResp) => {
            if(typeof error === 'function'){
                error(errResp);
            }
            console.log(errResp.toString());
        });

    console.log('*******************request end**********************');
}


