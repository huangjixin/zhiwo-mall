import React from 'react';
import {StyleSheet, ScrollView, Text ,View,Image,TextInput,DeviceEventEmitter,TouchableWithoutFeedback   } from 'react-native';

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
class CustomerHead extends React.Component {
    AddCustomer=() =>{
        DeviceEventEmitter.emit('AddCustomer');
    }
    render() {
        return (
            <View  style={styles.customerLine}>
                <Image
                    style={styles.customerImg_u7877}
                    source={require('../../../img/Customer/u7877.png')}
                />
                <View style={styles.customerLineOne}>
                    <Image
                        style={styles.customerImg_u7923}
                        source={require('../../../img/Customer/u7923.png')}
                    />
                    <TextInput style={styles.customerSearchInput}
                               underlineColorAndroid='transparent'
                               placeholder='请输入关键字搜索' />
                </View>
                <TouchableWithoutFeedback   onPress={this.AddCustomer}>
                    <Image
                        style={styles.customerImg_u7875}
                        source={require('../../../img/Customer/u7875.png')}
                    />
                </TouchableWithoutFeedback    >
            </View>
        );
    }
}

export default CustomerHead;

var styles = StyleSheet.create({
    customerImg_u7877: {
        left: 10,
        right: 10,
        top: 5,
        width: 25,
        height: 25,
    },
    customerImg_u7923: {
        top: 8,
        left: 10,
        width: 15,
        height: 15,
    },
    customerImg_u7875: {
        left: 30,
        right: 10,
        top: 5,
        width: 28,
        height: 28,
    },
    customerSearchInput: {
        width: ScreenWidth - 15 - 25 - 30 - 45,
        height: 35,
        left: 20,
    },
    customerLine: {
        flexDirection: 'row',
    },
    customerLineOne: {
        flexDirection: 'row',
        left: 20,
        backgroundColor: '#F4F4F4',
        borderRadius: 10,
    }
});
