import React from "react";
import {StyleSheet, ScrollView, Text, Image, View} from 'react-native';
import {TabBarBottom, TabNavigator} from "react-navigation";
import {HomeAchievement} from "./HomeAchievement";
import {ComponyNews} from "./ComponyNews";
import Swiper from 'react-native-swiper';

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
const NewsInfo = TabNavigator({
        ComponyNews: {
            screen: ComponyNews,
            navigationOptions: {
                tabBarLabel: '公司动态',
            }
        }, HotActivity: {
            screen: ComponyNews,
            navigationOptions: {
                tabBarLabel: '热门活动',
            }
        }, Notice: {
            screen: ComponyNews,
            navigationOptions: {
                tabBarLabel: '政策公告',
            }
        }, ProductOnline: {
            screen: ComponyNews,
            navigationOptions: {
                tabBarLabel: '产品上线',
            }
        }
    },{
        tabBarOptions: {
            activeTintColor: '#FF9300',
            inactiveTintColor: '#000000',
           // activeBackgroundColor: '#FF9300',
           // inactiveBackgroundColor: '#FFFFFF',
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
        lazy:false,
        backBehavior:'none'
    }
);

export class HomeInfo  extends React.Component{
    render() {
        return (
            <ScrollView>
                <View style={{flex:1, flexDirection: 'row',justifyContent: 'center',paddingTop:10,paddingBottom:10,width: ScreenWidth, height: 200}}>
                    <Swiper showsButtons={true} autoplay={true} autoplayTimeout={2}>
                        <View>
                            <Image source={require('../../../img/Home/banner1.jpg')}
                                   style={{width: ScreenWidth, height: 200}} />
                        </View>
                        <View>
                            <Image source={require('../../../img/Home/banner2.jpg')}
                                   style={{width: ScreenWidth, height: 200}} />
                        </View>
                        <View>
                            <Image source={require('../../../img/Home/banner3.jpg')}
                                   style={{width: ScreenWidth, height: 200}} />
                        </View>
                        <View>
                            <Image source={require('../../../img/Home/banner4.jpg')}
                                   style={{width: ScreenWidth, height: 200}} />
                        </View>
                    </Swiper>
                </View>
                <View style={{flex:1, flexDirection: 'row',justifyContent: 'center',paddingLeft:10,paddingRight:10,paddingTop:5}}>
                    <NewsInfo/>
                </View>
            </ScrollView>
        //     <ScrollView flex={1} style={{paddingTop:10,height:300}}>
           
        //     <Swiper flex={1} style={{height:300}} showsButtons={false} index={this.index}
        //             autoplay={false} 
        //             loop={false} 
        //             onIndexChanged={this.indexChangeHandler}
        //             showsPagination={true}>

        //         <View flex={1} style={{height:300}} >
        //             <ComponyNews/>
        //         </View>
        //         <View flex={1} style={{height:300}} >
        //             <ComponyNews/>
        //         </View>
        //     </Swiper>
        // </ScrollView>


        
        // <View flex={1} >
        // <Swiper flex={1} showsButtons={true} autoplay={true} autoplayTimeout={2}>
        //                 <View>
        //                      <Image source={require('../../../img/Home/banner1.jpg')}
        //                            style={{width: ScreenWidth, height: 200}} />
        //                  </View>
        //                  <View>
        //                     <Image source={require('../../../img/Home/banner2.jpg')}
        //                            style={{width: ScreenWidth, height: 200}} />
        //                  </View>
        //                  <View>
        //                      <Image source={require('../../../img/Home/banner3.jpg')}
        //                             style={{width: ScreenWidth, height: 200}} />
        //                  </View>
           
        //  </Swiper>
        //     <Swiper flex={1} autoplay={false} 
        //             loop={false}  >
        //     <ComponyNews/>
        //     <ComponyNews/>
        //     <ComponyNews/>
        //     <ComponyNews/>     
        //     </Swiper>
        // </View>
        
        
        );
    }
}