import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback} from 'react-native';
import CheckBox from 'react-native-checkbox';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class PromotionNoticeAutograph extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            isSe1:false
            //  dataSource:[]
        }
        // this.fetchData = this.fetchData.bind(this);
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#CBCBCB'}}>
                <ApplyCommonHeader title={'晋级通知书'} onReturn={() => this.props.navigation.goBack()}/>
                <View style={{paddingTop:30,paddingLeft:40}}>
                    <Text style={{fontSize:16,flex:1}}>XXX业务同仁： </Text>
                </View>
                <View style={{backgroundColor:'#FFFFFF',marginLeft:30,marginRight:30,height:430,marginTop:10,
                    flexDirection: 'column',justifyContent: 'center'}}>
                        <Text style={{fontSize:40,fontWeight:'600',color:'#ECECEC',textAlign:'center'}}>签字区</Text>
                </View>
                <View style={{marginLeft:30,marginRight:30,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{flex:5,fontSize:18,color:'#858585',textAlign:'center',backgroundColor:'#EBEBEB',height:50,lineHeight:50}}
                          onPress={() => this.props.navigation.goBack()}
                    >取消</Text>
                    <Text style={{flex:5,fontSize:18,color:'#000000',textAlign:'center',backgroundColor:'#FFDD00',height:50,lineHeight:50}}>提交</Text>
                </View>
            </ScrollView>
        );
    }
}

