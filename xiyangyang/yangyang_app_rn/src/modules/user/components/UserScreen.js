import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {
  ActivityIndicator,
  AsyncStorage,
  View,Text,Button,
  Dimensions,
  ScrollView,Image,TouchableWithoutFeedback
} from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

import { BaseScreenComponent } from '../../../core/BaseScreenComponent';
import NavigationService from '../../../navigation/NavigationService';
import NavigationConstants from '../../../navigation/NavigationConstants';
import CacheUtils from '../../../utils/CacheUtils';
import HomeConstants from '../../home/constants/HomeConstants';
import SignInAction from '../../home/actions/SignInAction';
import ScreenUtil from '../../../utils/ScreenUtil';
import UserScreen_css from './UserScreen_css';


export class UserScreen extends BaseScreenComponent {
  
  constructor(props) {
    super(props);
  }
  
  /**
   * 退出函数，清除用户缓存信息。
   */
  _signOut = () => {
    const userToken = HomeConstants.User_TOKEN_CACHE;
    ScreenUtil.removeAsyncStorage(userToken,this.dispatchSignOut,this.dispatchSignOut);
  };

  dispatchSignOut = ()=>{
    global.user = null;
    this.props.actions.signOut();
  }

  // Render any loading content that you like here
  render() {
    let username = global.user ?(global.user.username?global.user.username:'') :'';
    return (
      <ScrollView style={UserScreen_css.UserScreenStyle.container}>
        <View style={UserScreen_css.UserScreenStyle.header}>
          <View style={UserScreen_css.UserScreenStyle.headerContent}>
              <Image style={UserScreen_css.UserScreenStyle.headerContentIcon}
               source={{uri:'http://omsproductionimg.yangkeduo.com/images/2017-11-16/164402b57a195acee7e3144fd88ce46c.jpeg'}} />
               <View>
                 <View style={{flexDirection:'row',alignItems:'center'}}>
                  <Text style={UserScreen_css.UserScreenStyle.userLabel}>茱莉罗伯茨</Text>
                  <Icon size={ScreenUtil.setSpText2(16)} name="edit" color="#ffffff"/>
                 </View>   
                 <Text style={[UserScreen_css.UserScreenStyle.userLabel,{fontSize:ScreenUtil.setSpText2(12)}]}>已绑定微信</Text>
               </View>
          </View>
          {/* <Text style={{borderTopColor:'gray'}}>我是首页,欢迎{username}</Text>
           */}
        </View>
        <View style={{height:10,backgroundColor:'#F5F5F5'}}>
        
        </View>
        <View style={UserScreen_css.UserScreenStyle.menu}>
          <TouchableWithoutFeedback>
            <View style={UserScreen_css.UserScreenStyle.menuItem}>
              <View style={UserScreen_css.UserScreenStyle.menuItemLeft}>
                <Icon name="cog" color="gray" size={ScreenUtil.setSpText2(16)}/>
                <View style={{width:ScreenUtil.scaleSize(10)}}></View>
                <Text style={UserScreen_css.UserScreenStyle.menuItemText} size={ScreenUtil.scaleSize(16)}>设置</Text>
              </View>
              <View style={UserScreen_css.UserScreenStyle.menuItemRight}>
                <Icon name="angle-right" color="gray" size={ScreenUtil.setSpText2(20)}/>
              </View>
            </View>
          </TouchableWithoutFeedback>
          <TouchableWithoutFeedback>
            <View style={UserScreen_css.UserScreenStyle.menuItem}>
              <View style={UserScreen_css.UserScreenStyle.menuItemLeft}>
                <Icon name="sign-out" color="gray" size={ScreenUtil.setSpText2(16)}/>
                <View style={{width:ScreenUtil.scaleSize(10)}}></View>
                <Text style={UserScreen_css.UserScreenStyle.menuItemText} size={ScreenUtil.scaleSize(16)}>退出</Text>
              </View>
              <View style={UserScreen_css.UserScreenStyle.menuItemRight}>
                <Icon name="angle-right" color="gray" size={ScreenUtil.setSpText2(20)}/>
              </View>
            </View>
          </TouchableWithoutFeedback>
          <View style={{height:ScreenUtil.scaleSize(10)}}></View>
          <TouchableWithoutFeedback onPress={this._signOut}>
            <View style={UserScreen_css.UserScreenStyle.loginOutBtnBg}>
                <Text style={UserScreen_css.UserScreenStyle.loginOutBtn}>退出</Text>
            </View>
          </TouchableWithoutFeedback>
        </View>
      </ScrollView>
    );
  }
}



    //根据全局state返回当前页面所需要的信息,（注意以props的形式传递给Login）
    const mapStateToProps = (state)=>{
      const {status} = state.signInReducer;
        return{
            status
        }
    }
    
    //返回可以操作store.state的actions,(其实就是我们可以通过actions来调用我们绑定好的一系列方法)
    const mapDispatchToProps = (dispatch)=>{
        return {
            actions: bindActionCreators(SignInAction, dispatch)
        };
    }  
    
export default connect(mapStateToProps,mapDispatchToProps)(UserScreen);