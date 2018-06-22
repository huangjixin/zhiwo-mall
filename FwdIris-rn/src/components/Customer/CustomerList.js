import React from "react";
import {StyleSheet, ScrollView, Text,View} from 'react-native';
import {TabBarBottom, TabNavigator} from "react-navigation";
import {WholeCustomer} from "./WholeCustomer";


const CustomerDetailsTabr = TabNavigator({
        全部客户: {
            screen: WholeCustomer
        }, 老客户: {
            screen: WholeCustomer
        }, 准客户: {
            screen: WholeCustomer
        }, 线上客户: {
            screen: WholeCustomer
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

export class CustomerList  extends React.Component{
    render() {
        return (
            <View style={{paddingTop:10}}>
                <CustomerDetailsTabr/>
            </View>
        );
    }
}