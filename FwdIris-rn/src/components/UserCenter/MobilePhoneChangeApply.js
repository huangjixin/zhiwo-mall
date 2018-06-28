import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,DeviceEventEmitter,TouchableWithoutFeedback} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import {ApplyVerificationCode} from "./ApplyVerificationCode";
import * as FetchUtils from "../../common/FetchUtils";
import * as RequestURL from "../../common/RequestURL";
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

const g_agentCode = '888999';   //暂时用于显示当前职级
const g_mobileNo = '13764723865'
export class MobilePhoneChangeApply extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            filledIndicator:false,
            verificationCode:'',
            mobileNo:g_mobileNo,
        };
    }

    verificationCodeCheck = (text)=>{
        this.setState({
            verificationCode:text,
            filledIndicator : text.length>=4?true:false
        });
    }
    sendVerificationCode = ()=>{
        const {mobileNo} = this.state;
        const params = {
            agentCode:g_agentCode,
            mobileNo:mobileNo,
        }
        FetchUtils.Post({
            url:RequestURL.SEND_VERIFICATION_CODE,
            params:params,
            success:(respData)=>{},
            error:()=>{}
        })
    }

    NewMobilePhoneChangeApplyPage=()=>{
        const {
            mobileNo,
            filledIndicator,
            verificationCode
        } = this.state;

        if(!filledIndicator){
            return ;
        }

        const params = {
            agentCode:g_agentCode,
            mobileNo:mobileNo,
            verificationCode:verificationCode
        }
        FetchUtils.Post({
            url:RequestURL.VERIFY_VERIFICATION_CODE,
            params:params,
            success:(respData)=>{
                DeviceEventEmitter.emit('NewMobilePhoneChangeApplyPage'); //旧手机码申请-》新手机码页面
            },
            error:()=>{}
        })

    }
    render() {
        const {
            mobileNo,
            filledIndicator,
        } = this.state;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <View >
                    <ApplyCommonHeader title={'手机变更'} onReturn={() => this.props.navigation.goBack()}/>

                    <View>
                        <View  style={{flexDirection: 'row',justifyContent:'flex-start',
                            alignItems: 'center',backgroundColor:'#ffffff',height:50}}>
                            <Text  style={{flex:2.5,lineHeight:50,height:50,marginLeft:20,textAlign:'left',fontSize:16,color:'#333333'}}>旧手机码</Text>
                            <View style={{flex:8,textAlign:'left',flexDirection: 'row',alignItems: 'center'}}>
                                <Text  style={{flex:5,lineHeight:50,height:50,fontSize:16,color:'#9B9B9B'}}>{mobileNo}</Text>
                                <ApplyVerificationCode style={{flex:2.5}} onClick={this.sendVerificationCode}/>
                                <Text  style={{flex:0.5}}></Text>
                            </View>
                        </View>
                        <Text style={{borderBottomWidth:2,borderBottomColor:'#ffffff',backgroundColor:'#ffffff'}}/>
                        <View  style={{flexDirection: 'row',justifyContent: 'flex-start',backgroundColor:'#ffffff',height:50,marginTop:1}}>
                            <Text  style={{flex:2.5,lineHeight:50,height:50,marginLeft:20,textAlign:'left',fontSize:16,color:'#333333'}}>验证码</Text>

                            <View style={{flex:8,textAlign:'left'}}>
                                <TextInput
                                    placeholder='请输入验证码'
                                    underlineColorAndroid='transparent'
                                    style={{height: 50,backgroundColor:"#ffffff",fontSize:16}}
                                    onChangeText={(text) =>{this.verificationCodeCheck(text)}}
                                ></TextInput>
                            </View>
                        </View>
                        <View style={{flexDirection: 'row',marginTop:80}}>
                            <Text style={{flex:0.5}}></Text>
                            <Text style={[{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center'
                                },
                                filledIndicator?{backgroundColor:'#FFDD00',color:'#000000'}:{backgroundColor:'#FFF9D2',color:'#D5D5D2'}
                                ]}
                                  onPress={this.NewMobilePhoneChangeApplyPage}>下一步</Text>
                            <Text style={{flex:0.5}}></Text>
                        </View>
                    </View>
                </View>
            </ScrollView>
        );
    }
}

