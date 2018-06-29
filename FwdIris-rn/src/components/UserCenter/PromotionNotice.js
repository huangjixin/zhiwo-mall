import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter} from 'react-native';
import CheckBox from 'react-native-checkbox';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class PromotionNotice extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            isSe1:false
            //  dataSource:[]
        }
        // this.fetchData = this.fetchData.bind(this);
    }
    PromotionNoticeAutographPage=()=>{
        DeviceEventEmitter.emit('PromotionNoticeAutographPage'); //晋级通知书页面-》晋级签字页面
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader title={'晋级通知书'} onReturn={() => this.props.navigation.goBack()}/>
                <View style={{paddingTop:30,paddingLeft:40,paddingRight:40,height:ScreenHeight-75,}}>
                    <Text style={{fontSize:16,}}>XXX业务同仁： </Text>
                    <Text style={{marginTop:10,fontSize:16,}}>       辛勤付出与不懈的坚持终将收获成功！ </Text>
                    <Text style={{marginTop:5,fontSize:16,lineHeight:25,}}>       鉴于你一贯的优异的表现，经公司审批,同意你晋级至XXX(此处不显示级别)。新的级别生效日为XXXX年XX月XX日。 </Text>
                    <Text style={{marginTop:5,fontSize:16,lineHeight:25,}}>       冀望你在追求卓越的道路上一往无前,勇攀高峰！ </Text>
                    <Text style={{marginTop:30,fontSize:16,textAlign:'right',}}>(DOA电子签名图片)</Text>
                    <Text style={{fontSize:16,textAlign:'right',}}>DOA级别+姓名</Text>
                    <Text style={{marginTop:10,fontSize:16,textAlign:'right',}}>富卫人寿保险有限公司</Text>
                </View>
                <TouchableWithoutFeedback onPress={this.PromotionNoticeAutographPage}>
                    <View style={{width:ScreenWidth,position:'absolute',height:46,backgroundColor:'#FFDD00',bottom:0
                        ,justifyContent: 'center',alignItems: 'center',}}>{/*//borderRadius:5,*/}
                        <Text style={{fontSize:20,color:'#000000'}}>签字确认</Text>
                    </View>
                </TouchableWithoutFeedback>
            </ScrollView>
        );
    }
}

