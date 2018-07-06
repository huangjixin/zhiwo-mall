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
        fetch(url,{
            method: 'GET',
            headers: {
                // token: isNull(global.token)?'':global.token
            }}),
        FETCH_TIMEOUT)
        .then(response =>response.json())
        .then(respData => {
            console.log(respData);

            if(respData.code!=null
                && typeof success === 'function'){
                success(respData);
            }else {
                if(typeof error === 'function'){
                    error(respData);
                }
            }

        }).catch((errResp) => {
        if(typeof error === 'function'){
            error(errResp);
        }
        console.log(errResp.toString());
    });

    console.log('*******************request end**********************');
}


function toForm(data) {
    let formData = new FormData();
    let keyArr = Object.keys(data);
    if(keyArr.length<1){return {}}
    keyArr.map((item)=>{
        formData.append(item, data[item]);
    });
    return formData;
}

export async function Post({url,params,success,error,headers}){
    console.log('*******************request start**********************');
    console.log('url:' + url);

    if(url==null){
        return ;
    }

    if(headers==null){
        headers = {'Content-Type': 'application/json'}
    }
    const requestHeader = Object.assign({
        // token: isNull(global.token)?'':global.token
    }, headers);


    const ct = requestHeader["Content-Type"]
    let requestBody = (ct!=null && ct.indexOf('json')>0)?
                            JSON.stringify(params)
                            :toForm(params);

    await _fetch(
        fetch(url,{
            method: 'POST',
            headers:requestHeader,
            body: requestBody
        }),
        FETCH_TIMEOUT
    ).then(response =>response.json())
        .then(respData => {
            console.log(respData);

            if(respData.code!=null
                && typeof success === 'function'){
                success(respData);
            }else {
                if(typeof error === 'function'){
                    error(respData);
                }
            }

        }).catch((errResp) => {
            if(typeof error === 'function'){
                error(errResp);
            }
            console.log(errResp.toString());
        });

    console.log('*******************request end**********************');
}


