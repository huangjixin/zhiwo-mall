import React from 'react';
import {StyleSheet, ScrollView, Text, View, Image, TextInput, DeviceEventEmitter,TouchableWithoutFeedback} from 'react-native';
import {TabBarBottom, TabNavigator} from "react-navigation";
import {CustomerDetailsHistory} from "./CustomerDetailsHistory";

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
export class CustomerDetails extends React.Component {
    render() {
        return (
            <ScrollView>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',justifyContent: 'space-between',height:35}}>
                    <View style={{flex:1}}>
                        <TouchableWithoutFeedback onPress={() => {this.props.navigation.goBack()}}>
                            <Image
                                style={{width:25,height:25,textAlign:'left',marginTop:6,marginLeft:5}}
                                source={require('../../../img/Customer/u1693.png')}
                            />
                        </TouchableWithoutFeedback>
                    </View>
                    <View style={{flex:1}}>
                        <Text style={{textAlign:'center',fontSize:16,marginTop:8,color:'#FFFFFF'}}>客户详情</Text>
                    </View>
                    <View style={{flex:1}}>
                        <Text style={{textAlign:'right',fontSize:16,marginTop:8,marginRight:10,color:'#FFFFFF'}}>添加拜访</Text>
                    </View>
                </View>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',height:110}}>
                    <View style={{flex:2,justifyContent:'center',alignItems: 'center'}}>
                        <Image  source={require('../../../img/Customer/u2462.png')}
                                style={{width: 60, height:60}} />
                    </View>
                    <View style={{flex:5}}>
                        <View style={{ flexDirection: 'row',justifyContent: 'space-between',marginLeft:25,width:110}}>
                            <Text style={{color:'#FFFFFF',fontSize:20,fontWeight:'700',marginTop:10}}>贾衣玫</Text>
                            <Text style={{color:'#FFFFFF',fontSize:32,marginLeft:5,marginRight:5}}>|</Text>
                            <View>
                                <Text style={{color:'#FFFFFF',fontSize:14,marginTop:4}}>25岁</Text>
                                <Text style={{color:'#FFFFFF',fontSize:14}}>准客户</Text>
                            </View>
                        </View>
                        <View style={{marginLeft:10,flexDirection: 'row'}}>
                            <Image
                                style={{width:20,height:20,textAlign:'left',marginTop:6,marginLeft:4}}
                                source={require('../../../img/Customer/u9325.png')}
                            />
                            <Text style={{color:'#FFFFFF',fontSize:14,marginTop:8}}>18721573901</Text>
                        </View>
                        <View style={{marginLeft:10,flexDirection: 'row'}}>
                            <Image
                                style={{width:20,height:20,textAlign:'left',marginTop:6,marginLeft:4}}
                                source={require('../../../img/Customer/u9320.png')}
                            />
                            <Text style={{color:'#FFFFFF',fontSize:14,marginTop:8}}>上海市黄浦区云南南路118号</Text>
                        </View>
                    </View>
                    <View style={{flex:2,justifyContent:'center',alignItems: 'center'}}>
                            <Image  source={require('../../../img/Customer/u9302.png')}
                                    style={{width: 35, height:35,}} />
                            <Text style={{color:'#FFFFFF',fontSize:12}}>铜牌会员</Text>
                    </View>
                </View>
                <View style={{height:25,width:ScreenWidth,backgroundColor:'#EEC7A1',flexDirection: 'row'}}>
                    <Image
                        style={{width:25,height:25,marginLeft:90}}
                        source={require('../../../img/Customer/u9451.png')}
                    />
                    <Text style={{color:'#FFFFFF',fontSize:12,marginTop:5}}>距离升级银牌会员仍需20,000元</Text>
                </View>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',height:60}}>
                    <Text style={{color:'#FFFFFF',fontSize:14,marginLeft:20,marginTop:15}}>总保费</Text>
                    <Text style={{color:'#FFFFFF',fontSize:24,marginTop:15,marginLeft:10}}>25,000元</Text>
                    <Text style={{color:'#FFFFFF',fontSize:38,marginLeft:15,marginTop:3}}>|</Text>
                    <Text style={{color:'#FFFFFF',fontSize:14,marginLeft:30,marginTop:15}}>保单数量</Text>
                    <Text style={{color:'#FFFFFF',fontSize:24,marginTop:15,marginLeft:25}}>2份</Text>
                </View>
                <CustomerDetailsTabr/>
            </ScrollView>
        );
    }
}

const CustomerDetailsTabr = TabNavigator({
        保单: {
            screen:CustomerDetailsHistory
        }, 建议书: {
            screen: CustomerDetailsHistory
        }, 家庭成员: {
            screen: CustomerDetailsHistory
        }, 拜访记录: {
            screen: CustomerDetailsHistory
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
                paddingLeft:10,
                paddingRight:10,
                //borderBottomWidth:1,
                //borderBottomColor:'gray'
                //paddingBottom:25
                backgroundColor: '#FFFFFF', // TabBar 背景色
                borderTopWidth:0,
                //height: 44,
                // width : 200,
                //lineHeight: 44,
            },
        },
        tabBarComponent: TabBarBottom,
        tabBarPosition: 'top',
        animationEnabled: false,
        swipeEnabled: false,
    }
);



