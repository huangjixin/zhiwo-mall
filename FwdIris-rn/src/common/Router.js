import {DeviceEventEmitter} from "react-native";


export  class Router {

    constructor(navigation){this.navigation=navigation}

    static toScreen(pageName,param){
        DeviceEventEmitter.emit(pageName,param);
    }


    registerPageToRouter(pageName){
        return DeviceEventEmitter.addListener(pageName,(param)=>{
            this.navigation.navigate(pageName,param);
        });
    }

}