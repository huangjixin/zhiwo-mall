import React from 'react';
import {StyleSheet, ScrollView, Text, View} from 'react-native';
import {TabNavigator, TabBarTop, TabBarBottom} from 'react-navigation';
import {CustomerList} from "./CustomerList";
import {CustomerPolicyList} from "./CustomerPolicyList";

const CustomerTabr = TabNavigator({
        客户: {
            screen: CustomerList
        }, 保单: {
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

class CustomerTabNavigator extends React.Component {
    render() {
        return (
            <ScrollView style={{paddingTop:5}}>
                <CustomerTabr/>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});

export default CustomerTabNavigator;
