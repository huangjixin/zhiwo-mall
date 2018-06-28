import React from 'react';
import {StyleSheet,Dimensions, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,ImageBackground} from 'react-native';

import {ApplyCommonHeader} from "./ApplyCommonHeader";
import {ApplyVerificationCode} from "./ApplyVerificationCode";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";
import * as NumberUtils from "../../common/NumberUtils";

var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
const g_agentCode = '888999';   //暂时用于显示当前职级
const g_mobileNo = '13764723865'
export class BankCardReleaseBindingDetails extends React.Component {
    constructor(props) {
        super(props);

        const params = this.props.navigation.state.params;

        const cardNo = params.cardNo;
        const tailNo = cardNo.substring(cardNo.length-4);

        this.state={
            mobileNo:g_mobileNo,//TODO
            verificationCode:'',
            filledIndicator : false,
            cardId:params.id,
            cardIcon:params.icon,
            cardBgColor:params.backgroundColor,
            cardTailNo:tailNo,
            bankName:params.description
        };
    }

    verificationCodeCheck = (text)=>{
        this.setState({
            verificationCode:text,
            filledIndicator : text.length>=4?true:false
        });
    }

    sendVerificationCode = ()=>{
        const {cardId,mobileNo} = this.state;
        const params = {
            agentCode:g_agentCode,
            cardId:cardId,
            mobileNo:mobileNo,
        }
        FetchUtils.Post({
            url:RequestURL.SEND_VERIFICATION_CODE,
            params:params,
            success:(respData)=>{},
            error:()=>{}
        })
    }

    onSubmit = ()=>{
        const {filledIndicator,verificationCode,cardId,mobileNo} = this.state;
        if(!filledIndicator)
            return;

        const params = {
            agentCode:g_agentCode,
            cardId:cardId,
            mobileNo:mobileNo,
            verificationCode:verificationCode
        }
        FetchUtils.Post({
            url:RequestURL.TO_DEFAULT,
            params:params,
            success:(respData)=>{
                this.props.navigation.pop(3)
            },
            error:()=>{}
        })

    }

    render() {
        const {
            mobileNo,
            filledIndicator,
            cardIcon,
            cardBgColor,
            cardTailNo,
            bankName,
        } = this.state;

        return (
            <ScrollView style={{backgroundColor:'#FFFFFF',height:ScreenHeight}}>
                <View>
                    <ApplyCommonHeader title={'变更银行卡'} onReturn={() => this.props.navigation.goBack()}/>
                    <View style={{backgroundColor:'#ffffff',height:120}}>
                        <View style={[cardBgColor,{flexDirection: 'row',height:80,marginTop:20,marginLeft:20,marginRight:20,borderRadius:10}]}>
                            <View style={{height:80,flexDirection: 'column',justifyContent: 'center',marginLeft:30}}>
                                <Image style={{width:50,height:50,}} source={cardIcon}/>
                            </View>
                            <View style={{height:80,flexDirection: 'column',justifyContent: 'center',marginLeft:10}}>
                                <Text style={{fontSize:20,color:'#FFFFFF'}}>{bankName}</Text>
                                <Text style={{color:'#FFFFFF',fontWeight:'300'}}>尾号{cardTailNo}</Text>
                            </View>
                        </View>
                    </View>
                    <View  style={{flexDirection: 'row',justifyContent:'flex-start',
                        alignItems: 'center',backgroundColor:'#ffffff',height:50}}>
                        <Text  style={{flex:2,lineHeight:50,height:50,marginLeft:10,textAlign:'center',fontSize:16,color:'#333333'}}>手机码</Text>
                        <Text  style={{flex:5,lineHeight:50,height:50,marginLeft:10,fontSize:16,color:'#9B9B9B '}}>{mobileNo}</Text>

                        <ApplyVerificationCode style={{flex:2.5}} onClick={this.sendVerificationCode}/>


                        <Text  style={{flex:0.5}}></Text>
                    </View>
                    <Text style={{borderBottomWidth:2,borderBottomColor:'#F8F8F8',justifyContent:'center',backgroundColor:'#ffffff'}}/>
                    <View  style={{flexDirection: 'row',justifyContent: 'flex-start',backgroundColor:'#ffffff',height:50,marginTop:1}}>
                        <Text  style={{flex:2,lineHeight:50,height:50,marginLeft:10,textAlign:'center',fontSize:16,color:'#333333'}}>验证码</Text>
                        <TextInput
                            placeholder='请输入验证码'
                            underlineColorAndroid='transparent'
                            style={{flex:7,height: 50,backgroundColor:"#FFFFFF",fontSize:16}}
                            onChangeText={(text) =>{this.verificationCodeCheck(text)}}
                        ></TextInput>
                        <Text  style={{flex:1}}></Text>
                    </View>
                    <Text style={{borderBottomWidth:2,borderBottomColor:'#F8F8F8',backgroundColor:'#ffffff'}}/>
                    <View style={{flexDirection: 'row',paddingTop:20,}}>
                        <Text style={{flex:0.5}}></Text>
                        <TouchableWithoutFeedback onPress={()=>{this.onSubmit()}}>
                            <Text style={[{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,
                                textAlign:'center',backgroundColor:'#FFF9D2'},
                                filledIndicator?{backgroundColor:'#FFDD00',color:'#000000'}:{backgroundColor:'#FFF9D2',color:'#D5D5D2'}
                                ]}
                            >下一步</Text>
                        </TouchableWithoutFeedback>
                        <Text style={{flex:0.5}}></Text>
                    </View>
                </View>
            </ScrollView>
        );
    }
}

