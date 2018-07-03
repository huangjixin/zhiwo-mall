import React from 'react';
import {StyleSheet, ScrollView, Text, View,Dimensions,Platform} from 'react-native';
import {TabNavigator, TabBarTop, TabBarBottom} from 'react-navigation';

import {AchvTabMyAchievement} from "./AchvTabMyAchievement";
import {AchvTabPromotion} from "./AchvTabPromotion";
import {AchvTabIncome} from "./AchvTabIncome";

export class AchvMainContent extends React.Component {
    constructor(props){
        super(props);
        const screenWidth = Dimensions.get('window').width/3-60;
        const paddingLeft = (Dimensions.get('window').width/3-screenWidth)/2;
        this.state = {
            screenWidth:screenWidth,
            paddingLeft:paddingLeft
        }
    }

    _onLayout=(event)=>{  
        const screenWidth = Dimensions.get('window').width/3-60;
        const paddingLeft = (Dimensions.get('window').width/3-screenWidth)/2;
        this.setState({screenWidth:screenWidth,paddingLeft:paddingLeft});
    }

  render() {
        const AchievementTabr = TabNavigator({
                AchvTabMyAchievement: {
                    screen: AchvTabMyAchievement,
                    navigationOptions: {
                        tabBarLabel: '我的业绩',
                    }
                },
                AchvTabPromotion: {
                    screen: AchvTabPromotion,
                    navigationOptions: {
                        tabBarLabel: '晋升考核',
                    }
                },
                AchvTabIncome: {
                    screen: AchvTabIncome,
                    navigationOptions: {
                        tabBarLabel: '我的收入',
                    }
                },
            },{
                tabBarOptions: {
                    activeTintColor: '#000000',
                    inactiveTintColor: '#AB9300',
                    activeBackgroundColor: '#FFDD00',
                    inactiveBackgroundColor: '#FFDD00',
                    labelStyle: {
                        textAlign:'center',
                        fontSize: 18, // 文字大小
                        //paddingVertical:6,
                        // borderBottomWidth:2,
                        // borderBottomColor:'#000000',
                        // paddingTop:10,
                         paddingBottom:10,
                    },
                    // tabStyle: {
                    //     //  backgroundColor: '#FFDD00',
                    //     //  textAlign:'center',
                    // },

                    style: {
                        height:Platform.OS === "ios" ?68 :50,
                        textAlign:'center',
                        paddingTop:-5,
                    // paddingLeft:60,
                    // paddingRight:60,
                        // borderBottomWidth:3,
                        // borderBottomColor:'#000000',
                        // paddingBottom:25,
                        backgroundColor: '#FFDD00', // TabBar 背景色
                    // borderTopWidth:0,
                        //height: 44,
                        // width : 200,
                        //lineHeight: 44,
                    },
                    indicatorStyle: {
                        // position:'relative',
                        // paddingLeft:20,
                        left:this.state.paddingLeft,
                        height: 3,
                        width:this.state.screenWidth,
                        backgroundColor: '#000000'
                    },
                },
                // tabBarComponent: TabBarTop,
                tabBarPosition: 'top',
                animationEnabled: false,
                swipeEnabled: false,
                lazy:true,
                backBehavior:'none'
            }
        );
    return (
        <View style={{paddingTop:0,flex:1}}  >
            <AchievementTabr/>
        </View>

    );
  }
}