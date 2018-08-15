import React from 'react';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';

import {
  StyleSheet,
  View,Text,Button,Dimensions,DeviceEventEmitter,Platform,BackHandler,ToastAndroid,AsyncStorage,SafeAreaView
} from 'react-native';

import { BaseScreenComponent } from '../../../core/BaseScreenComponent';
// import { MainNavigator } from './HomeNavigation';
import NavigationService from '../../../navigation/NavigationService';
import SignInAction from '../actions/SignInAction';
import NavigationConstants from '../../../navigation/NavigationConstants';
import HomeConstants from '../constants/HomeConstants';
import createBottomTabNavigator from 'react-navigation-tabs/dist/navigators/createBottomTabNavigator';
// 用了export default的话不需要加大括号，否则必须加。
import { IndexScreen } from '../../index/components/IndexScreen';
import UserScreen from '../../user/components/UserScreen';
import Icon from 'react-native-vector-icons/FontAwesome';
import { SignatureScreen } from './SignatureScreen';
import ScreenUtil from '../../../utils/ScreenUtil';


/**
 * 黄记新（Tony）
 * 2018.07.20
 * 主界面导航。
 */
export class HomeScreen extends BaseScreenComponent {

  constructor(props) {
    super(props);
    this.asyncUserToken();
  }

  asyncUserToken =  ()=>{
    // async const userTokenString = await AsyncStorage.getItem(HomeConstants.User_TOKEN_CACHE,(error) =>{
    //   console.log('SingInScreen缓存用户数据出错');
    // });
    // if(userToken){
    //   global.user = JSON.parse(userTokenString.toString);
    // }
  }

  navigateToAuth = ()=>{
    this.props.navigation.navigate('Auth');
    // const Auth = NavigationConstants.Auth;
    // NavigationService.navigate(Auth);
  }

  // 用于授权页判断。
  navigateToTarget = (target)=>{
    this.props.navigation.navigate(target);
    // const Auth = NavigationConstants.Auth;
    // NavigationService.navigate(Auth);
  }

  /**
   * 侦听更新状态，确定是否要退出主界面。
   * @param {} nextProps 
   * @param {*} nextState 
   */
  componentDidUpdate(nextProps,nextState){
    if( this.props.status === HomeConstants.SIGN_OUT){
      this.navigateToAuth();
    }

    if( this.props.target !== null && this.props.target !== undefined ){
      let target = this.props.target;
      this.navigateToTarget(this.props.target);
    }
  }

  /**
   * 侦听退出。
   */
  componentDidMount(){
      // 如果是安卓平台，在两秒内再次点击将退出APP。
      if(Platform.OS == 'android'){
        BackHandler.addEventListener('hardwareBackPress', this.onBackAndroid);
      }
  }

  /**
   * 侦听退出按钮
   */
  componentWillUnmount(){
    if(Platform.OS == 'android'){
      BackHandler.removeEventListener('hardwareBackPress', this.onBackAndroid);
    }
  }

  onBackAndroid = ()=>{
    if (this.lastBackPressed && this.lastBackPressed + 2000 >= Date.now()) {
      //最近2秒内按过back键，可以退出应用。
      BackHandler.exitApp();
      return true;
    }
    ToastAndroid.show('再按一次退出应用', 2000);
    this.lastBackPressed = Date.now();
    return true;
  }

  render() {
    const RouteConfigs =  {
      Index:{
        screen:  IndexScreen,
        navigationOptions: {
            tabBarLabel: '首页', 
            tabBarPosition: 'bottom',
            tabBarIcon:({ focused, tintColor }) =>{
              return <Icon size={ScreenUtil.setSpText2(24)} name='home' color={tintColor}/>
            } 
        }
      },
      Signature:{
        screen:  SignatureScreen,
        navigationOptions: {
            tabBarLabel: '签名', 
            tabBarPosition: 'bottom',
            tabBarIcon:({ focused, tintColor }) =>{
              return <Icon size={ScreenUtil.setSpText2(24)} name='pencil' color={tintColor}/>
            } 
        }
      },
      User:{
        screen:  UserScreen,
        navigationOptions: {
            tabBarLabel: '我的', 
            tabBarPosition: 'bottom',
            tabBarIcon:({ focused, tintColor }) =>{
              return <Icon size={ScreenUtil.setSpText2(24)} name='user' color={tintColor}/>
            }
        }
      } ,
    };

    const BottomTabNavigatorConfig= {
          animationEnabled: true,//是否可以滑动切换
          swipeEnabled: true,//切换是否有动画
          animationEnabled: true,//进入App的首页面
          initialRouteName :'Index',
          tabBarPosition: 'bottom',
          lazy:true,
          backBehavior:'none',
          tabBarOptions: {
            showLabel:true,
            activeTintColor: 'red',
            activeBackgroundColor:'#ffffff',
            inactiveTintColor:'gray',
            inactiveBackgroundColor:'#ffffff',
            labelStyle: {
              fontSize: ScreenUtil.setSpText2(12),
            },
            tabStyle:{
              // borderTopWidth:1,
              borderTopColor:'#D3D3D3'
            }
          }
    }
    const MainNavigator =  createBottomTabNavigator(
     RouteConfigs,BottomTabNavigatorConfig);

    return (
      // <SafeAreaView style={{flex: 1,paddingBottom:0}}>
        
      // </SafeAreaView>
      <View style={{flex: 1,paddingTop:(ScreenUtil.isIphoneX() == true?30:0)}}>
          <MainNavigator></MainNavigator>
      </View>
      
    );
  }
}


  //根据全局state返回当前页面所需要的信息,（注意以props的形式传递给Login）
  const mapStateToProps = (state)=>{
    const {status,target} = state.signInReducer;
      return{
          status,target
      }
  }
  
  //返回可以操作store.state的actions,(其实就是我们可以通过actions来调用我们绑定好的一系列方法)
  const mapDispatchToProps = (dispatch)=>{
      return {
          actions: bindActionCreators(SignInAction, dispatch)
      };
  }  
  
  export default connect(mapStateToProps,mapDispatchToProps)(HomeScreen);