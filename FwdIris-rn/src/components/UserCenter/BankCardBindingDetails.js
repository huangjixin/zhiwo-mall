import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,ImageBackground,Dimensions} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";


var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class BankCardBindingDetails extends React.Component {
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FFFFFF'}}>
                <View>
                    {/* <View style={{flexDirection: 'row',backgroundColor:"#F9F9F9"}}>
                        <TouchableWithoutFeedback  onPress={() => this.props.navigation.goBack()}>
                            <Image style={{width: 50, height: 50,marginLeft:10,marginTop:5}} source={require('../../../img/UserCenter/Return1.gif')} />
                        </TouchableWithoutFeedback>
                        <Text style={{fontSize:20,color:'#5A5A5A',flex:3,height: 50,marginTop:8,marginLeft:20,lineHeight:50,fontWeight:'900'}}>银行卡详情</Text>
                    </View>
                    <View style={{flexDirection: 'row',justifyContent: 'space-between',marginTop:20}}>
                        <Text style={{flex:0.5}}></Text>
                        <ImageBackground style={{ flex:9,height:111}} source={require('../../../img/UserCenter/u12748.png')}>

                        </ImageBackground>
                        <Text style={{flex:0.5}}></Text>
                    </View> */}
                    <ApplyCommonHeader title={'银行卡详情'} onReturn={() => this.props.navigation.goBack()}/>

                    <Text style={{borderBottomWidth:2,borderBottomColor:'#EEEEEE',backgroundColor:'#ffffff',marginTop:50}}/>
                    <Text style={{height:60,lineHeight:60,textAlign:'center',fontWeight:'900',fontSize:16}}>绑定</Text>
                    <Text style={{backgroundColor:'#F2F2F2',height:10}}></Text>
                    <Text style={{height:60,lineHeight:60,textAlign:'center',fontWeight:'900',fontSize:16}}
                           onPress={() => this.props.navigation.goBack()}
                    >取消</Text>
                </View>
            </ScrollView>
        );
    }
}

