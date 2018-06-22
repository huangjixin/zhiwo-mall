import React from 'react';
import {StyleSheet, ScrollView,Button, Text, View, DeviceEventEmitter,BackHandler} from 'react-native';
import  {Pending} from '../components/home/pending';
import {TabNavigator, TabBarTop, TabBarBottom} from 'react-navigation';
import {HomeAchievement} from "../components/home/HomeAchievement";
import {HomeInfo} from "../components/home/HomeInfo";
import Swiper from 'react-native-swiper';

const HomeTabr = TabNavigator({
    HomeAchievement: {
        screen: HomeAchievement,
        navigationOptions: {
            tabBarLabel: '业绩',
        }
    }, HomeInfo: {
        screen: HomeInfo,
        navigationOptions: {
            tabBarLabel: '资讯',
        }
    }
    },{
        tabBarOptions: {
            activeTintColor: '#FF9300',
            inactiveTintColor: '#000000',
            labelStyle: {
                fontSize: 16, // 文字大小
                paddingVertical:5,
                //padding:10
            },
            style: {
                height:32,
                textAlign:'center',
                paddingLeft:60,
                paddingRight:60,
                backgroundColor: '#FFFFFF', // TabBar 背景色
                borderTopWidth:0,
            },
        },
        tabBarComponent: TabBarBottom,
        tabBarPosition: 'top',
        animationEnabled: false,
        swipeEnabled: false,
        lazy:true,
        backBehavior:'none'
    }
);

export class Home extends React.Component {

    // componentDidMount() {
    //     DeviceEventEmitter.addListener('navigateToNewsDetail', this.navigateToNewsDetail);
    //     BackHandler.addEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
    // }
 
    // componentWillUnmount() {
    //     DeviceEventEmitter.removeListener('navigateToNewsDetail', this.navigateToNewsDetail);
    //     BackHandler.removeEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
    // }

    // navigateToNewsDetail = ()=>{
    //     console.log('received message to navigateToNewsDetail');
    //     this.props.navigation.navigate('SettingsScreen');
    // }

    // onBackButtonPressAndroid = () => {
    //     // return true;
    //     // this.props.navigation.
    //     // this.props.navigation.goBack(null);
    //     return false;
    //  };

    constructor(){
        super();
    }

  render() {
    return (
        <ScrollView style={{paddingTop:5}}>
           <HomeTabr/>
        </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

