import React from "react";
import {StyleSheet, ScrollView, Button, Text, View, Image, DeviceEventEmitter} from 'react-native';
import * as Progress from 'react-native-progress';
import {TabBarBottom, TabNavigator} from "react-navigation";
import {HomeInfo} from "./HomeInfo";
import {Pending} from "./pending";
import {Router} from "../../common/Router";

const PendingTabr = TabNavigator({
        Pending: {
            screen: Pending,
            navigationOptions: {
                tabBarLabel: '照会',
            }
        }, CustomerInfo: {
            screen: Pending,
            navigationOptions: {
            tabBarLabel: '客户经营',
            }
        }, YidongShenPi: {
            screen: Pending,
            navigationOptions: {
                tabBarLabel: '移动审批',
            }
        }, Huifang: {
            screen: Pending,
            navigationOptions: {
                tabBarLabel: '回执回访',
            }
        }
    },{
        tabBarOptions: {
            activeTintColor: '#FFFFFF',
            inactiveTintColor: '#000000',
            activeBackgroundColor: '#FF9300',
            inactiveBackgroundColor: '#FFFFFF',
            labelStyle: {
                fontSize: 16, // 文字大小
                paddingVertical:5,
                //padding:10
            },
            style: {
                height:32,
                textAlign:'center',
                paddingLeft:0,
                paddingRight:0,
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

export class HomeAchievement  extends React.Component{
    navigateTest=() =>{
        DeviceEventEmitter.emit('navigateToNewsDetail');
    }
    
    render() {
        return (
            <ScrollView style={{}}>
                <View style={{backgroundColor:'#F2F2F2',height:10}}></View>
                <View style={{paddingTop:5,paddingBottom:5,paddingLeft:10,paddingRight:10}}>
                    <Text onPress={this.navigateTest}>
                       个人概况
                    </Text>
                    <View style={{flex:1,flexDirection:'row',justifyContent: 'center',
                        alignItems: 'flex-end',marginTop:5}}>
                        <View style={{flex:1,}} >
                            <Image source={{uri: 'http://omsproductionimg.yangkeduo.com/images/2017-11-16/164402b57a195acee7e3144fd88ce46c.jpeg'}}
                                   style={{width: 70, height: 70,borderRadius:35}} />
                        </View>
                        <View style={{flex:3,fontSize:16}} >
                            <View>
                                <Text style={{fontSize:16,color:'orange'}}>Tony黄记新</Text>
                                <Text style={{marginTop:10}}>
                                    SAM资深业务经理  广州|建业营管处
                                </Text>
                            </View>
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View style={{flex:1,flexDirection:'row',justifyContent: 'center',
                    alignItems: 'flex-end',marginTop:5,paddingLeft:10,paddingRight:10}}>
                    <View style={{flex:1}}>
                        <Text style={{fontSize:20,color:'orange'}}>123,456K</Text>
                        <Text style={{fontSize:16,color:'#858585'}}> FYC</Text>
                        <Progress.Bar progress={0.3} width={100} borderRadius={0} color={'#FF9900'}/>
                    </View>
                    <View style={{flex:1,marginLeft:10}}>
                        <Text style={{fontSize:20,color:'orange'}}>123,456</Text>
                        <Text style={{fontSize:16,color:'#858585'}}> FYP</Text>
                        <Progress.Bar progress={0.3} width={100} borderRadius={0} color={'#FF9900'}/>
                    </View>
                    <View style={{flex:1,marginLeft:10}}>
                        <Text style={{fontSize:20,color:'orange'}}>10</Text>
                        <Text style={{fontSize:16,color:'#858585'}}> CASE</Text>
                        <Progress.Bar progress={0.3} width={100} borderRadius={0} color={'#FF9900'}/>
                    </View>
                </View>
                <View style={{paddingLeft:10,paddingRight:10,marginTop:10}}>
                    <View style={{backgroundColor:'#F2F2F2',height:5}}></View>
                    <PendingTabr/>
                </View>
            </ScrollView>

        );
    }
}