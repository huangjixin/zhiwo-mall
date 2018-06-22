import React from 'react';
import {StyleSheet,Dimensions, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,ImageBackground} from 'react-native';

import {ApplyCommonHeader} from "./ApplyCommonHeader";
import {ApplyVerificationCode} from "./ApplyVerificationCode";

var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class BankCardReleaseBindingDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            verificationCode : '',
            nextIndicator : false,
        };
    }

    verificationCodeCheck = (text)=>{
        this.setState({
            verificationCode:text,
            nextIndicator : text.length>=4?true:false
        });
    }

    onSubmit = ()=>{
        const {nextIndicator} = this.state;
        if(nextIndicator)
            alert(JSON.stringify(this.state));

    }

    render() {
        const nextIndicator = this.state.nextIndicator;

        return (
            <ScrollView style={{backgroundColor:'#FFFFFF',height:ScreenHeight}}>
                <View>
                    <ApplyCommonHeader title={'变更银行卡'} onReturn={() => this.props.navigation.goBack()}/>
                    <View style={{backgroundColor:'#ffffff',height:120}}>
                        <View style={{flexDirection: 'row',backgroundColor:'#37B5FF',height:80,marginTop:20,marginLeft:20,marginRight:20,borderRadius:10}}>
                            <View style={{height:80,flexDirection: 'column',justifyContent: 'center',marginLeft:30}}>
                                <Image style={{width:50,height:50,}}source={require('../../../img/UserCenter/BOCOM.gif')}/>
                            </View>
                            <View style={{height:80,flexDirection: 'column',justifyContent: 'center',marginLeft:10}}>
                                <Text style={{fontSize:20,color:'#FFFFFF'}}>交行储蓄卡</Text>
                                <Text style={{color:'#B6E3FF',fontWeight:'300'}}>尾号4579</Text>
                            </View>
                        </View>
                    </View>
                    <View  style={{flexDirection: 'row',justifyContent:'flex-start',
                        alignItems: 'center',backgroundColor:'#ffffff',height:50}}>
                        <Text  style={{flex:2,lineHeight:50,height:50,marginLeft:10,textAlign:'center',fontSize:16,}}>手机码</Text>
                        <Text  style={{flex:5,lineHeight:50,height:50,marginLeft:10,fontSize:16}}>13764723865</Text>

                        <ApplyVerificationCode style={{flex:2.5}} onClick={()=>{}}/>


                        <Text  style={{flex:0.5}}></Text>
                    </View>
                    <Text style={{borderBottomWidth:2,borderBottomColor:'#F8F8F8',justifyContent:'center',backgroundColor:'#ffffff'}}/>
                    <View  style={{flexDirection: 'row',justifyContent: 'flex-start',backgroundColor:'#ffffff',height:50,marginTop:1}}>
                        <Text  style={{flex:2,lineHeight:50,height:50,marginLeft:10,textAlign:'center',fontSize:16}}>验证码</Text>
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
                                nextIndicator?{backgroundColor:'#FFDD00',color:'#000000'}:{backgroundColor:'#FFF9D2',color:'#D5D5D2'}
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

