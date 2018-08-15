export const id = ()=>{
    this.user.id;
}

export default class User {
    constructor(user){
        if(user!=null && user!=undefined){
            if(user.hasOwnProperty('id')){
                this.id = user.id;
            }
        }
        if(user!=null && user!=undefined){
            if(user.hasOwnProperty('username')){
                this.username = user.username;
            }
        }
        if(user!=null && user!=undefined){
            if(user.hasOwnProperty('password')){
                this.password = user.password;
            }
        }
    }
}