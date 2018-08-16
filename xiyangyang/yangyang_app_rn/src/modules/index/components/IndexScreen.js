import React from 'react';
import {
  StyleSheet,
  SafeAreaView,Platform
} from 'react-native';
import { BaseScreenComponent } from '../../../core/BaseScreenComponent';
import createMaterialTopTabNavigator from 'react-navigation-tabs/dist/navigators/createMaterialTopTabNavigator';
import TabBarTop from 'react-navigation-tabs/dist/views/MaterialTopTabBar';
import BottomTabBar from 'react-navigation-tabs/dist/views/BottomTabBar';
import Test1Screen from '../../user/components/Test1Screen';
import { Test2Screen } from '../../user/components/Test2Screen';
import ScreenUtil from '../../../utils/ScreenUtil';
// import IndexNavigation from './IndexNavigation';
/**
 * 首页。
 */
export class IndexScreen extends BaseScreenComponent {
  
  constructor(props) {
    super(props); 
  }

  render() {
        
    const RouteConfigs =  {
      Test1:{
        screen:  Test1Screen,
        navigationOptions: {
            tabBarLabel: '热门',
        }
      },
      Test2:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '男装',
        }
      },
      Test3:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '鞋包', 
        }
      },Test4:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '手机', 
        }
      },Test5:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '男装', 
        }
      },Test6:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '鞋包', 
        }
      },Test7:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '女装', 
        }
      },Test8:{
        screen:  Test2Screen,
        navigationOptions: {
            tabBarLabel: '男装', 
        }
      },
    };

    const TabNavigatorConfig= {
          animationEnabled: true,//是否可以滑动切换
          swipeEnabled: true,//切换是否有动画
          animationEnabled: true,//进入App的首页面
          initialRouteName :'Test1',
          tabBarPosition: 'top',
          lazy:true,
          backBehavior:'none',
          tabBarOptions: {
            inactiveOpacity:0,
            activeOpacity:0,
            activeTintColor: 'red',
            inactiveTintColor: 'black',
            activeBackgroundColor: '#FFFFFF',
            inactiveBackgroundColor: '#FFFFFF',
            scrollEnabled:true,
            showLabel:true,
            // showIcon: true,
            showIcon:false,
            // pressOpacity:1,//按压标签的透明度变化（安卓版本需要小于5.0）。
            // pressColor:'#ffffff',
            // tabBarComponent: BottomTabBar,
            // iconStyle:{
            //   // flexGrow: 0,
            //   // marginTop: -20
            // },
            labelStyle: {
              fontSize: ScreenUtil.setSpText2(14),
              textAlign:'center',
              // paddingBottom:10,
            },
            tabStyle:{
              padding: 0,
              width:ScreenUtil.scaleSize(50),
              // paddingBottom:10,
              // paddingLeft:10,paddingRight:10,
              // paddingTop:10,
              // borderTopWidth:1,
              // borderTopColor:'#D3D3D3'
            },
            style: {
              elevation: 0,
              shadowColor: 'black',
              shadowOpacity: 0.0,
              // textAlign:'center',
              // paddingTop:-5,
              // paddingBottom:5, 
          // paddingLeft:60,
          // paddingRight:60,
              borderBottomWidth:1,
              borderBottomColor:'#D3D3D3',
              // paddingBottom:25,
              backgroundColor: '#FFFFFF', // TabBar 背景色
          // borderTopWidth:0,
          paddingTop:Platform.OS == 'android'?0:(ScreenUtil.isIphoneX===true?0:ScreenUtil.scaleSize(12)),
          height: Platform.OS == 'android'?ScreenUtil.scaleSize(38):(ScreenUtil.isIphoneX===true?38:ScreenUtil.scaleSize(42)),
              //paddingTop:0,
              //height: ScreenUtil.scaleSize(38),
              // alignItems:'center',
              // width : 200,
              // lineHeight: 30,
          },
          indicatorStyle: {
              left:ScreenUtil.scaleSize(8),
              width:ScreenUtil.scaleSize(34),
              height: ScreenUtil.scaleSize(2),
              backgroundColor: 'red'
          }
      }
    }
    const IndexNavigation =  createMaterialTopTabNavigator(
    RouteConfigs,TabNavigatorConfig);
    return (
      <SafeAreaView  style={styles.container}>
        <IndexNavigation></IndexNavigation>
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      // alignItems: 'center',
      // justifyContent: 'center',
    },
  });
