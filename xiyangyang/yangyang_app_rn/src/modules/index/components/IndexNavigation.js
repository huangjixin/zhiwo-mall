import createMaterialTopTabNavigator from "react-navigation-tabs/dist/navigators/createMaterialTopTabNavigator";
import { Test1Screen } from "../../user/components/Test1Screen";
import { Test2Screen } from "../../user/components/Test2Screen";

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
          allowFontScaling:false,
          // pressOpacity:1,//按压标签的透明度变化（安卓版本需要小于5.0）。
          // pressColor:'#ffffff',
          // tabBarComponent: BottomTabBar,
          iconStyle:{
            // flexGrow: 0,
            // marginTop: -20
          },
          labelStyle: {
            fontSize: 16,
            textAlign:'center',
            // paddingBottom:10,
          },
          tabStyle:{
            padding: 0,
            width:50,
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
        paddingTop:0,
            height: 38,
            // alignItems:'center',
            // width : 200,
            // lineHeight: 30,
        },
        indicatorStyle: {
            // position:'relative',
            // paddingLeft:20,
            left:8,
            width:34,
            height: 2,
            // paddingBottom:-1,
            //width:this.state.screenWidth,
            backgroundColor: 'red'
        }
    }
  }
  const IndexNavigation =  createMaterialTopTabNavigator(
   RouteConfigs,TabNavigatorConfig);

   export default IndexNavigation;