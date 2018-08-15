import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import { StyleSheet, Text, View,Button,AsyncStorage,TouchableWithoutFeedback } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

import { BaseScreenComponent } from '../../../core/BaseScreenComponent';
import NavigationService from '../../../navigation/NavigationService';
import NavigationConstants from '../../../navigation/NavigationConstants';
import SignInAction from '../actions/SignInAction';
import HomeConstants from '../constants/HomeConstants';
import RNAsyncStorage from '../../../utils/RNAsyncStorage';
import CacheUtils from '../../../utils/CacheUtils';
import ScreenUtil from '../../../utils/ScreenUtil';
import SignInScreen_css from './SignInScreen_css';

export class SignInScreen extends BaseScreenComponent {

  constructor(props) {
    super(props);
  }

  _signInAsync = () => {
    if(this.props.status== 'SIGN_IN_DOING')
      return;
    
    this.props.actions.signIn();
    // this.props.navigation.navigate('App');
  };

  // shouldComponentUpdate(nextProps,nextState){
  //   return true;
  // }

  navigateToApp = ()=>{
    let userToken = HomeConstants.User_TOKEN_CACHE;
    if(this.props.user){
      global.user = this.props.user;
      let userStr = JSON.stringify(global.user);
      // ScreenUtil.saveAsyncStorage(userToken,(data)=>{
      // },()=>{
      // });
    }

    const App = NavigationConstants.App;
    NavigationService.navigate(App);
  }

  // 更新完状态之后跳转；
  componentDidUpdate(nextProps,nextState){
    if( this.props.status === 'SIGN_IN_DONE'){
      this.navigateToApp();
    }
  }

  // Render any loading content that you like here
  render() {
    return ( 
      
      <View style = {SignInScreen_css.SignInScreenStyle.container}>
        <Icon name='apple' size={ScreenUtil.scaleSize(100)}/>
        <View style={{height:30}}></View>
        <TouchableWithoutFeedback onPress={this._signInAsync}>
            <View style={SignInScreen_css.SignInScreenStyle.loginBtnBg}>
                <Text style={SignInScreen_css.SignInScreenStyle.loginBtn}>点击登录</Text>
            </View>
          </TouchableWithoutFeedback>
        {/* <Button title = "点击登录" color = "gray" 
                fontSize = "20" elevation="0"
                onPress={this._signInAsync}>
        </Button> */}
        <View style={{height:20}}></View>
        {this.props.status== 'SIGN_IN_DOING'?<Text style={{color:'red'}}>正在登录……</Text>:<Text></Text>}
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
});

  //根据全局state返回当前页面所需要的信息,（注意以props的形式传递给Login）
const mapStateToProps = (state)=>{
  const {status,user} = state.signInReducer;
  // const {nav} = state.nav;
    return{
        status,
        user
    }
}

//返回可以操作store.state的actions,(其实就是我们可以通过actions来调用我们绑定好的一系列方法)
const mapDispatchToProps = (dispatch)=>{
    return {
        actions: bindActionCreators(SignInAction, dispatch)
    };
}  

export default connect(mapStateToProps,mapDispatchToProps)(SignInScreen);


