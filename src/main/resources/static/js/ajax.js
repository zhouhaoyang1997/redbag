let ajax=function(url, param, type = 'GET'){
    const promise = new Promise(function(resolve, reject){
        $.ajax({
            type: type,
            url: url,
            data: param,
            dataType: 'json',
            success(res) {
                resolve(res)
            },
            error(res) {
                reject('响应错误')
                // reject(res.statusText)
            }
        })
    })
    return promise
}
