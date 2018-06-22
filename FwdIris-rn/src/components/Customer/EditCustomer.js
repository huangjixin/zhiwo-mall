import React from "react";
import {StyleSheet, ScrollView, Text,View,Image,TextInput} from 'react-native';

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
export class EditCustomer  extends React.Component {
    render() {
        return (
            <View>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',justifyContent: 'space-between',height:40}}>
                    <Image
                        style={{width:28,height:28,textAlign:'left',marginTop:6,marginLeft:5}}
                        source={require('../../../img/Customer/u1693.png')}
                    />
                    <Text style={{textAlign:'center',fontSize:20,marginTop:8,color:'#FFFFFF'}}>新增客户</Text>
                    <Text style={{textAlign:'right',fontSize:20,marginTop:8,marginRight:10,color:'#FFFFFF'}}>保存</Text>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>姓名</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                placeholder='请输入客户姓名'
                                style={{width:ScreenWidth-150,height:40}}></TextInput>
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    <View style={{ flexDirection: 'row',height:60,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>性别</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305}}>
                            <Image
                                style={{width:35,height:35,marginLeft:5}}
                                source={require('../../../img/Customer/u9597.png'
                                )}
                            />
                            <Image
                                style={{width:35,height:35,marginLeft:5}}
                                source={require('../../../img/Customer/u9599.png'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>出生日期</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                placeholder='yyyy/mm/dd'
                                style={{width:ScreenWidth-150,height:40}}></TextInput>
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>证件类型</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                underlineColorAndroid='transparent'
                                style={{width:ScreenWidth-150,height:40, borderColor: 'gray', borderWidth: 1}}></TextInput>
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>证件号码</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                placeholder='请输入证件号码'
                                style={{width:ScreenWidth-150,height:40}}></TextInput>
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>手机号</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                placeholder='请输入手机号码'
                                style={{width:ScreenWidth-150,height:40}}></TextInput>
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,width:80,height:40,marginTop:20,textAlign:'right'}}>家庭地址</Text>
                        <View style={{flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                underlineColorAndroid='transparent'
                                style={{width:(ScreenWidth-150)/4+10,height:40, borderColor: 'gray', borderWidth: 1}}></TextInput>
                            <TextInput
                                underlineColorAndroid='transparent'
                                style={{width:(ScreenWidth-150)/4+10,height:40, borderColor: 'gray', borderWidth: 1,marginLeft:15}}></TextInput>
                            <TextInput
                                underlineColorAndroid='transparent'
                                style={{width:(ScreenWidth-150)/4+10,height:40, borderColor: 'gray', borderWidth: 1,marginLeft:15}}></TextInput>
                        </View>
                    </View>
                    <View style={{ flexDirection: 'row',height:40}}>
                        <Text style={{fontSize:16,width:80,height:40,textAlign:'right'}}></Text>
                        <View style={{flexDirection: 'row',height:40,width:305,marginLeft:15}}>
                            <TextInput
                                placeholder='请输入家庭详细地址'
                                style={{width:ScreenWidth-150,height:40}}></TextInput>
                        </View>
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#ffffff',paddingTop:5,marginBottom:80}}/>
            </View>
        );
    }
}