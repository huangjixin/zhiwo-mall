import React from "react";
import {Achievement} from "./Achievement";
import {UserCenter} from "./UserCenter";
import {Customer} from "./Customer";
import {Calendar} from "./Calendar";
import {EchartsScreen} from "./EchartsScreen";
import {Home} from "./Home";
import {
    Image,
    StyleSheet,
    Button,
    BackHandler,
    DeviceEventEmitter,
    Platform,
    ToastAndroid
} from 'react-native';
import {TabNavigator, TabBarBottom, StackNavigator} from 'react-navigation';
import { QuitApply } from "../components/UserCenter/QuitApply";

export const MainScreen = TabNavigator({

    //Home: { screen: Home,label: '首页' },
    OaApplyFormDetail: {
        screen: QuitApply,
        navigationOptions: {
            tabBarLabel: '详情',
            tabBarIcon: ({tintColor, focused}) => (<Image
                source={require('../../img/homePage.png')}
                style={styles.resizeMode}
                resizeMode={Image.resizeMode.contain}/>)
        }
    },
    Home: {
        screen: Home,
        navigationOptions: {
            tabBarLabel: '首页',
            tabBarIcon: ({tintColor, focused}) => (<Image
                source={require('../../img/homePage.png')}
                style={styles.resizeMode}
                resizeMode={Image.resizeMode.contain}/>)
        }
    },
    Calendar: {
        screen: Calendar,
        navigationOptions: {
            tabBarLabel: '日历',
            tabBarIcon: ({tintColor, focused}) => (<Image
                source={require('../../img/Calendar.png')}
                style={styles.resizeMode}
                resizeMode={Image.resizeMode.contain}/>)
        }
    },
    Achievement: {
        screen: Achievement,
        navigationOptions: {
            tabBarLabel: '业绩',
            tabBarIcon: ({tintColor, focused}) => (<Image
                source={require('../../img/achievement.png')}
                style={styles.resizeMode}
                resizeMode={Image.resizeMode.contain}/>)
        }
    },
    Customer: {
        screen: Customer,
        navigationOptions: {
            tabBarLabel: '客户',
            tabBarIcon: ({tintColor, focused}) => (<Image
                source={require('../../img/Customer.png')}
                style={styles.resizeMode}
                resizeMode={Image.resizeMode.contain}/>)
        }
    },
    UserCenter: {
        screen: UserCenter,
        navigationOptions: {
            tabBarLabel: '我的',
            tabBarIcon: ({tintColor, focused}) => (<Image
                source={require('../../img/userCenter.png')}
                style={styles.resizeMode}
                resizeMode={Image.resizeMode.contain}/>)
        }
    }
}, {
    tabBarOptions: {
        activeTintColor: '#666666',
        activeBackgroundColor: '#E7E7E7',
        inactiveTintColor: '#666666',
        labelStyle: {
            fontSize: 12
        }
    },
    tabBarComponent: TabBarBottom,
    tabBarPosition: 'bottom',
    animationEnabled: false,
    swipeEnabled: true,
    backBehavior: 'none',
    initialRouteName:'UserCenter',
});

var styles = StyleSheet.create({
    resizeMode: {
        width: 60,
        height: 30
    }
})

export class MainUI extends React.Component {

    constructor(props) {
        super(props);
        //这一句不能省略，照抄即可
        this.state = {
            lastBackTime: new Date()
        }
    }

    navigateToNewsDetail = () => {
        this
            .props
            .navigation
            .navigate('SettingsScreen');
    }

    componentWillMount() {
        DeviceEventEmitter.addListener('navigateToNewsDetail', this.navigateToNewsDetail);
        let _this = this;
        BackHandler.addEventListener('hardwareBackPress', () => {
            // let date =_this.state.lastBackTime.getTime();
            // let currentDate = new Date();
            // let currentDateTime = currentDate.getTime();
            // if ((date + 3000) >= currentDateTime ) {
            //     return false;
            // } else {
            //     ToastAndroid.show('再按返回退出应用', 3000);
            //     _this.state.lastBackTime = currentDate;
            //     return true;
            // }
            if (this.props.navigation) {
                // if (this.props.navigation.state.routeName === 'Home') {     let date =
                // this.state.lastBackTime;     if((date +2000) >= Date.now()){         return
                // false;     }else{         this.state.lastBackTime = new Date();
                // ToastAndroid.show('再按返回退出应用', ToastAndroid.SHORT);         return true;     }
                // } return false;   let routes = this.props.navigation.getCurrentRoutes();
                // let lastRoute = routes[routes.length - 1]; // 当前页面对应的route对象   if
                // (lastRoute.onHardwareBackPress) {// 先执行route注册的事件       let flag =
                // lastRoute.onHardwareBackPress();       if (flag === false) {//
                // 返回值为false就终止后续操作           return true;       }   }   if (routes.length ===
                // 1) {// 在第一页了,2秒之内点击两次返回键，退出应用     if(this.lastBackPressed &&
                // (lastBackTime+2000) >= Date.now()){         return false;     }     //
                // 此处可以根据情况实现 点2次就退出应用，或者弹出rn视图等     //记录点击返回键的时间     lastBackTime = Date.now();
                //     ToastAndroid.show('再按返回退出应用', ToastAndroid.SHORT);   } else {
                // this.props.navigation.pop();   }
            }
            return false;
        });
    }

    componentWillUnmount() {
        DeviceEventEmitter.removeListener('navigateToNewsDetail', this.navigateToNewsDetail);

        if (Platform.OS === 'android') {
            BackHandler.removeEventListener('hardwareBackPress', () => {});
        }
    }

    render() {
        return (<MainScreen/>)
    }
}
