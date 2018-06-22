import React from "react";
import {Image, Text, View,ScrollView} from "react-native";

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class CustomerLabel  extends React.Component{
    render() {
        return (
            <View>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',justifyContent: 'space-between',height:40}}>
                    <Image
                        style={{width:28,height:28,textAlign:'left',marginTop:6,marginLeft:5}}
                        source={require('../../../img/Customer/u1693.png')}
                    />
                    <Text style={{textAlign:'center',fontSize:20,marginTop:8,color:'#FFFFFF'}}>标签管理</Text>
                    <Text></Text>
                </View>
                <Text style={{height:40,lineHeight:40,backgroundColor: '#FFFFFF',fontWeight:'bold',color: '#000000',fontSize:18,paddingLeft:15}}>固定标签</Text>
                <View style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#FFFFFF'}}>
                    <Text style={{textAlign:'center',marginTop:10,marginLeft:15,lineHeight:40,width:100,height:40,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>全部客户</Text>
                    <Text style={{textAlign:'center',marginTop:10,lineHeight:40,width:100,height:40,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>老客户</Text>
                    <Text style={{textAlign:'center',marginRight:15,marginTop:10,lineHeight:40,width:100,height:40,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>准客户</Text>
                </View>
                <View style={{backgroundColor:'#FFFFFF',height:60}}>
                    <Text style={{textAlign:'center',marginTop:10,marginLeft:15,lineHeight:40,width:100,height:40,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>线上客户</Text>
                </View>
                <View style={{backgroundColor:'#FFFFFF',height:45,flexDirection: 'row',justifyContent: 'space-between',marginTop:10}}>
                    <Text style={{height:45,lineHeight:45,backgroundColor: '#FFFFFF',fontWeight:'bold',color: '#000000',fontSize:18,paddingLeft:15}}>自定义标签</Text>
                    <View style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#FFFFFF',height:45,paddingRight:15}}>
                        <Text style={{textAlign:'center',marginTop:10,lineHeight:25,borderRadius:25,width:60,height:25,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>添加</Text>
                        <Text style={{textAlign:'center',marginTop:10,marginLeft:10,lineHeight:25,borderRadius:25,width:60,height:25,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>删除</Text>
                    </View>
                </View>
                <View style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#FFFFFF'}}>
                    <Text style={{textAlign:'center',marginTop:10,marginLeft:15,lineHeight:40,width:100,height:40,color:'#BCBCBC',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#BCBCBC'}}>铂金级（10）</Text>
                    <Text style={{textAlign:'center',marginTop:10,lineHeight:40,width:100,height:40,color:'#BCBCBC',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#BCBCBC'}}>钻石级（10）</Text>
                    <Text style={{textAlign:'center',marginRight:15,marginTop:10,lineHeight:40,width:100,height:40,color:'#BCBCBC',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#BCBCBC'}}>黄金级（10）</Text>
                </View>
                <View style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#FFFFFF',height:80,paddingTop:10}}>
                    <Text style={{textAlign:'center',marginTop:10,marginLeft:15,lineHeight:40,width:100,height:40,color:'#BCBCBC',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#BCBCBC'}}>黑卡级（10）</Text>
                    <Text style={{textAlign:'center',marginTop:10,lineHeight:40,width:100,height:40,color:'#BCBCBC',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#BCBCBC'}}>贵宾级（10）</Text>
                    <Text style={{textAlign:'center',marginRight:15,marginTop:10,lineHeight:40,width:100,height:40,color:'#BCBCBC',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#BCBCBC'}}>普通级（10）</Text>
                </View>
            </View>
        );
    }
}