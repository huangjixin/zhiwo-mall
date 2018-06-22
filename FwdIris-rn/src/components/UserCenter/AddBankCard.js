import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class AddBankCard extends React.Component {
    BankCardBindingDetailsPage=()=>{
        DeviceEventEmitter.emit('BankCardBindingDetailsPage'); //用户中心跳转到消息通知
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FFFFFF'}}>
                <View>
                    <ApplyCommonHeader title={'变更银行卡'} onReturn={() => this.props.navigation.goBack()}/>
                    <View style={{backgroundColor:'#FFDD00',height:160,marginTop:20,marginLeft:20,marginRight:20,borderRadius:5}}>
                        <Text style={{marginLeft:30,color:'#000000',fontSize:18,marginTop:30}}>请选择  发卡行/卡类型  > </Text>
                        <Text  style={{marginLeft:30,color:'#000000',fontSize:18,marginTop:15}}>XXXX  XXXX XXXX XXXX</Text>
                        <View style={{marginLeft:30,flexDirection: 'row',justifyContent:'space-between',marginTop:15}}>
                            <Text style={{color:'#000000',fontSize:18}}>持卡人  XXX</Text>
                            <Text style={{color:'#A28C00',fontSize:18,marginRight:20}}>有效期  XX/XX</Text>
                        </View>
                    </View>
                    <View style={{flexDirection: 'row',justifyContent:'space-between',marginLeft:20,marginTop:40}}>
                        <TextInput
                            placeholder='请输入卡号'
                            underlineColorAndroid='transparent'
                            style={{flex:8,height: 50,marginLeft:10,backgroundColor:"#FFFFFF",fontSize:16}}></TextInput>
                        <View style={{flex:1,flexDirection: 'column',justifyContent: 'center'}}>
                            <Image style={{width:25,height:25,}}source={require('../../../img/UserCenter/camera.png')}/>
                        </View>
                    </View>
                    <Text style={{marginLeft:20,borderBottomWidth:2,borderBottomColor:'#F8F8F8',backgroundColor:'#ffffff'}}/>
                </View>
            </ScrollView>
        );
    }
}

