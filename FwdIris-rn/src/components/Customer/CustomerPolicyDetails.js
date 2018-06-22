import React from "react";
import {StyleSheet, ScrollView, Text,View,Image,TextInput,br} from 'react-native';

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
export class CustomerPolicyDetails  extends React.Component {
    render() {
        return (
            <View>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',justifyContent: 'space-between',height:40}}>
                    <Image
                        style={{width:28,height:28,textAlign:'left',marginTop:6,marginLeft:5}}
                        source={require('../../../img/Customer/u1693.png')}
                    />
                    <Text style={{textAlign:'center',fontSize:20,marginTop:8,color:'#FFFFFF'}}>保单详情</Text>
                    <Text></Text>
                </View>
                <View style={{flexDirection: 'row',justifyContent: 'space-between'}}>
                    <View>
                        <Text style={{fontWeight:'bold',paddingLeft:15,paddingTop:15}}>花开富贵两全保险（分红型）</Text>
                        <Text  style={{fontSize:14,color:'#BCBCBC',paddingLeft:15,paddingTop:15}}>NO.601021020201701175826055203</Text>
                        <Text style={{fontSize:14,color:'#BCBCBC',paddingLeft:15,paddingTop:5}}>2016/01/01 00:00起 ~ 2017/01/01 00:00止</Text>
                    </View>
                    <View>
                        <Text style={{textAlign:'center',marginTop:10,marginRight:20,lineHeight:80,borderRadius:80,width:80,height:80,color:'#FF9900',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>保障中</Text>
                    </View>
                </View>
                <Text style={{borderBottomWidth:2,borderBottomColor:'#FF9900',paddingTop:5}}/>
                <View style={{backgroundColor:'#F2F2F2', flexDirection: 'row',justifyContent: 'space-between',height:50}}>
                    <Text style={{fontSize:20,marginTop:12,color:'#000000',marginLeft:10,fontWeight:'400'}}>|  客户信息</Text>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:40,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:50,colo:'#000000',fontWeight:'400'}}>投保人</Text>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',textAlign :'right',marginRight:10}}>
                            <Text style={{fontSize:16,lineHeight:50,colo:'#F2F2F2',fontWeight:'400'}}>张三</Text>
                            <Image
                                style={{width:35,height:35,marginTop:6}}
                                source={require('../../../img/Customer/u10014.gif'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:40,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:40,colo:'#000000',fontWeight:'400'}}>被保人</Text>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',textAlign :'right',marginRight:10}}>
                            <Text style={{fontSize:16,lineHeight:50,colo:'#F2F2F2',fontWeight:'400'}}>张三三</Text>
                            <Image
                                style={{width:35,height:35,marginTop:6}}
                                source={require('../../../img/Customer/u10014.gif'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:40,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:40,colo:'#000000',fontWeight:'400'}}>受益人</Text>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',textAlign :'right',marginRight:10}}>
                            <Text style={{fontSize:16,lineHeight:50,colo:'#F2F2F2',fontWeight:'400'}}>法定</Text>
                            <Image
                                style={{width:35,height:35,marginTop:6}}
                                source={require('../../../img/Customer/u10014.gif'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View style={{backgroundColor:'#F2F2F2', flexDirection: 'row',justifyContent: 'space-between',height:40,marginTop:10}}>
                    <Text style={{fontSize:20,marginTop:6,color:'#000000',marginLeft:10,fontWeight:'400'}}>|  产品信息</Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,height:40,lineHeight:40,marginLeft:15,fontWeight:'900'}}>产品信息</Text>
                    <Text  style={{width:ScreenWidth-150,height:40,lineHeight:40,fontWeight:'900'}}>保障利益</Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15}}>全佑一生（倍呵护）</Text>
                    <Text  style={{width:ScreenWidth-150}}>
                        首次第二类重大疾病基础保障
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15,marginTop:3}}>重大疾病保险</Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        第二次第二类重大疾病基础保障
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15}}></Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        第三次第二类重大疾病基础保障
                    </Text>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC',marginTop:5}}/>
                <View style={{ flexDirection: 'row',marginTop:10}}>
                    <Text style={{width:150,marginLeft:15}}>“五合一”儿童豁免保</Text>
                    <Text  style={{width:ScreenWidth-150}}>
                        身故豁免保险费
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15,marginTop:3}}>险费疾病保险</Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        全残豁免保险费
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15}}></Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        重大疾病豁免保险费
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15}}></Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        重大疾病豁免保险费
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15}}></Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        疾病终末期状态豁免保险费
                    </Text>
                </View>
                <View style={{ flexDirection: 'row'}}>
                    <Text style={{width:150,marginLeft:15}}></Text>
                    <Text  style={{width:ScreenWidth-150,marginTop:3}}>
                        老年长期护理豁免保险费
                    </Text>
                </View>
                <View style={{backgroundColor:'#F2F2F2', flexDirection: 'row',justifyContent: 'space-between',height:40,marginTop:10}}>
                    <Text style={{fontSize:20,marginTop:6,color:'#000000',marginLeft:10,fontWeight:'400'}}>|  缴费信息</Text>
                </View>
                <View>
                    <Image
                        style={{width:200,height:100,marginTop:10,marginLeft:15}}
                        source={require('../../../img/Customer/u9955.png'
                        )}
                    />
                </View>
                <View style={{backgroundColor:'#F2F2F2', flexDirection: 'row',justifyContent: 'space-between',height:40,marginTop:10}}>
                    <Text style={{fontSize:20,marginTop:6,color:'#000000',marginLeft:10,fontWeight:'400'}}>|  利益给付信息</Text>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',justifyContent: 'space-between'}}>
                        <Text style={{marginTop:15,marginLeft:20}}>身故保险金</Text>
                        <Text style={{marginTop:15}}>全残保险金</Text>
                        <Text style={{marginTop:15,marginRight:20}}>累计红利</Text>
                    </View>
                    <View  style={{ flexDirection: 'row',justifyContent: 'space-between'}}>
                        <Text  style={{marginTop:5,marginLeft:35,color:'#993300',fontSize:18,fontWeight:'700'}}>20W</Text>
                        <Text  style={{marginTop:5,marginLeft:5,color:'#993300',fontSize:18,fontWeight:'700'}}>20W</Text>
                        <Text style={{marginTop:5,marginRight:30,color:'#993300',fontSize:18,fontWeight:'700'}}>20W</Text>
                    </View>
                </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#ffffff',marginTop:5,marginBottom:100}}/>
            </View>
        );
    }
}