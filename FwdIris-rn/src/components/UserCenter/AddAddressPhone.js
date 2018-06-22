import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class AddAddressPhone extends React.Component {
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <View >
                    <ApplyCommonHeader title={'地址编辑'} onReturn={() => this.props.navigation.goBack()}/>
                    <View>
                        <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:50}}>
                            <Text  style={{flex:2,lineHeight:50,height:50,textAlign:'right',fontSize:14,marginTop:5,fontSize:16}}>手机码</Text>
                            <Text  style={{flex:5,lineHeight:50,height:50,marginLeft:10,fontSize:14,marginTop:5,fontSize:16}}>150****2456</Text>
                            <Text  style={{flex:2.5,lineHeight:35,height:35,fontSize:16,borderStyle: 'solid',borderWidth: 1,borderColor: '#E87722'
                                ,textAlign:'center',borderRadius:20,marginTop:10,color:'#E87722'}}>获取验证码</Text>
                            <Text  style={{flex:0.5}}></Text>
                        </View>
                        <Text style={{borderBottomWidth:2,borderBottomColor:'#ffffff',backgroundColor:'#ffffff'}}/>
                        <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:50,marginTop:1}}>
                            <Text  style={{flex:2,lineHeight:50,height:50,textAlign:'right',fontSize:16}}>验证码</Text>
                            <TextInput
                                placeholder='请输入验证码'
                                underlineColorAndroid='transparent'
                                style={{flex:7,height: 50,marginLeft:10,backgroundColor:"#FFFFFF",fontSize:16}}></TextInput>
                            <Text  style={{flex:1}}></Text>
                        </View>
                        <View style={{flexDirection: 'row',marginTop:80}}>
                            <Text style={{flex:0.5}}></Text>
                            <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',backgroundColor:'#FFDD00',color:'#000000'}}
                            >确认</Text>
                            <Text style={{flex:0.5}}></Text>
                        </View>
                    </View>
                </View>
            </ScrollView>
        );
    }
}

