

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

    await fetch(url,{
        method: 'GET',
    })
        .then(response =>response.json())
        .then(respData => {
            console.log(respData);
            if(respData.code!=null&&respData.code==0){
                if(typeof error === 'function'){
                    error(respData);
                }
                // alert(respData.msg);
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
            // alert('network error!');
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
                // alert(respData.msg);
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
            // alert('network error!');
            console.log(errResp.toString());
        });

    console.log('*******************request end**********************');
}


