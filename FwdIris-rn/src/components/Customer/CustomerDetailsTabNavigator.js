import React from 'react';
import {StyleSheet, ScrollView, Text, View} from 'react-native';
import {TabNavigator, TabBarTop, TabBarBottom} from 'react-navigation';
import {CustomerList} from "./CustomerList";
import {CustomerPolicyList} from "./CustomerPolicyList";


const CustomerDetailsTabr = TabNavigator({
        全部客户: {
            screen: CustomerList
        }, 老客户: {
            screen: CustomerList
        }, 准客户: {
        screen: CustomerPolicyList
        }, 线上客户: {
            screen: CustomerPolicyList
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
                paddingLeft:60,
                paddingRight:60,
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
        backBehavior:'none',
    }
);

class CustomerDetailsTabNavigator extends React.Component {
    render() {
        return (
            <ScrollView style={{paddingTop:5}}>
                <CustomerDetailsTabr/>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});

export default CustomerDetailsTabNavigator;
