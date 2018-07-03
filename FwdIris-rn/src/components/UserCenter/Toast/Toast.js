import React, {
    Component,
} from 'react';
import RootView from './RootView'
import ToastView from './ToastView'

{/*

引入文件
import Toast from './Toast/Toast';

使用方法
Toast.show("测试,我是Toast");

//能设置显示时间的Toast
Toast.show("测试",Toast.LONG);



*/}
class Toast {
    static LONG = 2000;
    static SHORT = 1000;

    static show(msg) {
        RootView.setView(<ToastView
            message={msg}
            onDismiss={() => {
                RootView.setView()
            }}/>)
    }

    static show(msg, time) {
        RootView.setView(<ToastView
            message={msg}
            time={time}
            onDismiss={() => {
                RootView.setView()
            }}/>)
    }
}

export default Toast