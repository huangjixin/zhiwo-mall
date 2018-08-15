import { createBottomTabNavigator, BottomTabBar} from 'react-navigation-tabs';
import IndexScreen from '../../index/components/IndexScreen';
import { UserScreen } from '../../user/components/UserScreen';
import NavigationConstants from '../../../navigation/NavigationConstants';

const Index = NavigationConstants.Index;

export const MainNavigator =  createBottomTabNavigator(
    {
      Index:{
        screen:  IndexScreen,
        navigationOptions: {
            tabBarLabel: '首页', 
            tabBarPosition: 'bottom',
        }
      },
      User:{
        screen:  UserScreen,
        navigationOptions: {
            tabBarLabel: '我的', 
            tabBarPosition: 'bottom',
        }
      } ,
    },{
      tabBarOptions: {
        showLabel:true,
        activeTintColor: 'red',
        activeBackgroundColor:'#ffffff',
        inactiveTintColor:'gray',
        inactiveBackgroundColor:'#ffffff',
        labelStyle: {
          fontSize: 18,
        },
        tabStyle:{
          borderTopWidth:1,
          borderTopColor:'#D3D3D3'
        }
      }
  },{
    animationEnabled: true,
    //是否可以滑动切换
    swipeEnabled: true,
    //切换是否有动画
    animationEnabled: true,
    //进入App的首页面
    initialRouteName :'Index',
    tabBarPosition: 'bottom',
    lazy:true,
    backBehavior:'none',
    
  });