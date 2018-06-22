import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class NewMobilePhoneChangeApply extends React.Component {
    constructor(props) {
        super(props);
        let begin = 0;
        let press = this.props.press;
        let timeLeft = this.props.timeLeft > 0 ? this.props.timeLeft : 60;

        this.afterEnd = this.props.afterEnd || this._afterEnd;
        this.style = this.props.style;

        this.state = {
            timeLeft: timeLeft,
            begin: begin
        };
    }

    countdownfn(timeLeft, callback, begin) {
        if (timeLeft > 0) {
            this.state.begin = 1;
            console.log("===lin===>");

            let that = this;
            let interval = setInterval(function () {
                if (that.state.timeLeft < 1) {
                    clearInterval(interval);
                    callback(that)
                } else {
                    let totalTime = that.state.timeLeft;
                    that.setState({
                        timeLeft: totalTime - 1
                    })
                }
            }, 1000)
        }
    }
    _afterEnd(that) {
        console.log('------------time over');
        that.setState({
            begin : 0,
            timeLeft : 5,
        })
    }
    _beginCountDown() {
        if (this.state.begin === 1){
            return;
        }
        let time = this.state.timeLeft;
        console.log("===lin===> time " + time);
        let afterEnd = this.afterEnd;
        let begin = this.state.begin;
        console.log("===lin===> start " + begin);
        this.countdownfn(time, afterEnd, begin)
    }

    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <View >
                    <ApplyCommonHeader title={'手机变更'} onReturn={() => this.props.navigation.goBack()}/>
                    <View>
                        <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:50}}>
                            <Text  style={{flex:2,lineHeight:50,height:50,textAlign:'right',fontSize:16,marginTop:5}}>新手机码</Text>
                            <Text  style={{flex:5,lineHeight:50,height:50,marginLeft:10,fontSize:16,marginTop:5}}>150****2456</Text>
                            <Text  style={{flex:2.5,lineHeight:35,height:35,fontSize:16,borderStyle: 'solid',borderWidth: 1,borderColor: '#E87722'
                                ,textAlign:'center',borderRadius:20,marginTop:10,color:'#E87722',fontSize:16}}
                                   onPress={this._beginCountDown.bind(this)}
                            >{ this.state.begin === 0 ? '获取验证码' : this.state.timeLeft}</Text>
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
                            <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',
                                backgroundColor:'#FFDD00',color:'#000000'}}
                            >完成</Text>
                            <Text style={{flex:0.5}}></Text>
                        </View>
                    </View>
                </View>
            </ScrollView>
        );
    }
}

