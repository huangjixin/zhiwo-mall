let FETCH_TIMEOUT = 5000

export async function Get ({ url, params, success, error }) {
  console.log('*******************request start**********************')
  console.log('url:' + url)

  if (url == null) {
    return
  }

  if (params != null && typeof params === 'object') {
    let paramStr = ''
    Object.keys(params).forEach(key => {
      paramStr += key + '=' + params[key] + '&'
    })
    paramStr = paramStr.substring(0, paramStr.length - 1)

    url += '?' + paramStr

    console.log('request url:' + url)
  }

  new Promise((resolve, reject) => {
    var timeout = setTimeout(() => {
      reject(new Error('Request timed out'))
    }, FETCH_TIMEOUT)

    fetch(url, {
      method: 'GET'
    })
      .then(response => {
        clearTimeout(timeout)
        if (response && response.ok) return response.json()
        else reject(new Error('Response error'))
      })
      .then(responseObject => resolve(responseObject))
      .catch(err => reject(err))
  })
    .then(respData => {
      console.log(respData)
      if (respData.code != null && respData.code == 0) {
        if (typeof error === 'function') {
          error(respData)
        }
        // alert(respData.msg)
        console.log(respData.msg)
        return
      }

      if (respData.code != null && respData.code == 1 && typeof success === 'function') {
        success(respData)
      }
    })
    .catch(errResp => {
      if (typeof error === 'function') {
        error(errResp)
      }
      console.log(errResp)
    })

  console.log('*******************request end**********************')
}

export async function Post ({ url, params, success, error }) {
  console.log('*******************request start**********************')
  console.log('url:' + url)

  if (url == null) {
    return
  }

  new Promise((resolve, reject) => {
    var timeout = setTimeout(() => {
      reject(new Error('Request timed out'))
    }, FETCH_TIMEOUT)

    fetch(url, {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(params)
    })
      .then(response => {
        clearTimeout(timeout)
        if (response && response.ok) return response.json()
        else reject(new Error('Response error'))
      })
      .then(responseObject => resolve(responseObject))
      .catch(err => reject(err))
  })
    .then(respData => {
      console.log(respData)
      if (respData.code != null && respData.code == 0) {
        if (typeof error === 'function') {
          error(respData)
        }
        // alert(respData.msg)
        console.log(respData.msg)
        return
      }

      if (respData.code != null && respData.code == 1 && typeof success === 'function') {
        success(respData)
      }
    })
    .catch(errResp => {
      if (typeof error === 'function') {
        error(errResp)
      }
      console.log(errResp)
    })

  console.log('*******************request end**********************')
}
