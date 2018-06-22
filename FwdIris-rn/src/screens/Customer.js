import React from 'react';
import {StyleSheet, ScrollView, Text ,View,Image,TextInput,Button,ToastAndroid} from 'react-native';
import CustomerHead from "../components/Customer/CustomerHead";
import CustomerTabNavigator from "../components/Customer/CustomerTabNavigator";

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class Customer extends React.Component {
    render() {
        return (
            <View style={{top:10}}>
                <CustomerHead/>
                <View style={{top:10}}>
                   <CustomerTabNavigator />
                </View>
            </View>
        );
    }
}




