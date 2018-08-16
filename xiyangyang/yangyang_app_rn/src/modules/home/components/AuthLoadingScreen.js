import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {
  AsyncStorage,
  ActivityIndicator,
  StyleSheet,
  View,Text,Alert
} from 'react-native';

import CacheUtils from '../../../utils/CacheUtils';
import HomeConstants from '../constants/HomeConstants';
import NavigationService from '../../../navigation/NavigationService';
import NavigationConstants from '../../../navigation/NavigationConstants';
import RNAsyncStorage from '../../../utils/RNAsyncStorage';
import SignInAction from '../actions/SignInAction';
import ScreenUtil from '../../../utils/ScreenUtil';

/**
 * 黄记新（Tony）
 * 2018.07.20
 * 分屏鉴权。
 */
export class AuthLoadingScreen extends React.Component {
  constructor(props) {
    super(props);
    this.signIn();
  }

  /**
   * 鉴权
   */
  _bootstrapAsync =  () => {
    // const userToken = await CacheUtils.getCache(HomeConstants.TOKEN_CACHE);
    // const target = (userToken ? NavigationConstants.App : NavigationConstants.SignIn);
    // NavigationService.navigate(NavigationConstants.App);
    
    // try {
    //   const userToken =  AsyncStorage.getItem(HomeConstants.User_TOKEN_CACHE);
    //   if (userToken !== null) {
    //     JSON.stringify(userToken)
    //   }
    //   this.props.navigation.navigate(userToken ? 'App' : 'Auth');
    //  } catch (error) {
    //     console.log("AuthLoadingScreen获取用户缓存数据出错");
    //     this.props.navigation.navigate('Auth');
    //  }
    // this.props.navigation.navigate('App');
  };

  // Fetch the token from storage then navigate to our appropriate place
  signIn = () => {
    let this2 = this;
    const userToken = HomeConstants.User_TOKEN_CACHE;
    // CacheUtils.getCache(HomeConstants.TOKEN_CACHE).then((data)=>{
    //   if(data!=null){
    //     this2.props.navigation.navigate('App');
    //   }else{
    //     this2.props.navigation.navigate('Auth');
    //   }
      
    // }).catch((error)=>{
    //   this2.props.navigation.navigate('Auth');
    // });
    // this2.props.navigation.navigate('Auth');
    // ScreenUtil.getAsyncStorage(userToken,()=>{
    //   console.log("获取缓存成功");
    // },()=>{
    //   console.log("获取缓存失败");
    // });
    this.props.navigation.navigate('Auth');
  };

  navigateToApp = ()=>{
    console.log("获取缓存成功");
    //this.props.navigation.navigate('App');

    // if(ScreenUtil.isEmpty(data)){
    //   this.navigateToAuth();
    // }else{
    //   let userInfo = JSON.parse(data);
    //   this.props.navigation.navigate('App');
    // }
  }

  navigateToAuth = ()=>{
    console.log("获取缓存失败");
    //this.props.navigation.navigate('Auth');
  }

  /**
   * 侦听更新状态，确定是否要退出主界面。
   * @param {} nextProps 
   * @param {*} nextState 
   */
  componentDidUpdate(nextProps,nextState){
    // if( this.props.userInfo !== null){
    //   this.navigateToAuth();
    //   // this.props.actions.navigateToAction('App');
    // }
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.copyright}>黄记新 版权所有</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  copyright :{
    fontSize: 20,
    color: 'red',

  }
});

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
  
export default connect(mapStateToProps,mapDispatchToProps)(AuthLoadingScreen);