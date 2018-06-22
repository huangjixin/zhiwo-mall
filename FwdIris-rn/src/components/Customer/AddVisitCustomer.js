import React from "react";
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback} from 'react-native';


export class AddVisitCustomer  extends React.Component {
    render() {
        return (
            <View>
                <View style={{backgroundColor:'#BCBCBC', flexDirection: 'row',justifyContent: 'space-between',height:40}}>
                    <TouchableWithoutFeedback  onPress={() => this.props.navigation.goBack()}>
                        <Image
                            style={{width:28,height:28,textAlign:'left',marginTop:6,marginLeft:5}}
                            source={require('../../../img/Customer/u1693.png')}
                        />
                    </TouchableWithoutFeedback>
                    <Text style={{textAlign:'center',fontSize:20,marginTop:8,color:'#FFFFFF'}}>客户拜访</Text>
                    <Text style={{textAlign:'right',fontSize:20,marginTop:8,marginRight:10,color:'#FFFFFF'}}>保存</Text>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:60}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:60}}>拜访客户</Text>
                        <View style={{borderColor: 'gray', borderWidth: 1,flexDirection: 'row',height:45,marginTop:10,width:305,marginLeft:15}}>
                            <TextInput
                                underlineColorAndroid='transparent'
                                style={{width:260,height:40}}></TextInput>
                            <Image
                                style={{width:30,height:30,marginTop:5,marginLeft:5}}
                                source={require('../../../img/Customer/u7923.png'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:55,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:60}}>拜访阶段</Text>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',textAlign :'right'}}>
                            <Text style={{fontSize:16,lineHeight:60}}>请选择</Text>
                            <Image
                                style={{width:48,height:48,marginTop:6}}
                                source={require('../../../img/Customer/u1693.gif'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:55,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:60}}>拜访地址</Text>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',textAlign :'right'}}>
                            <Text style={{fontSize:16,lineHeight:60}}>请选择</Text>
                            <Image
                                style={{width:48,height:48,marginTop:6}}
                                source={require('../../../img/Customer/u1693.gif'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:55,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:60}}>拜访时间</Text>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',textAlign :'right'}}>
                            <Text style={{fontSize:16,lineHeight:60}}>2017年9月5日 15:00</Text>
                            <Image
                                style={{width:48,height:48,marginTop:6}}
                                source={require('../../../img/Customer/u1693.gif'
                                )}
                            />
                        </View>
                    </View>
                    <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                </View>
                <View>
                    <View style={{ flexDirection: 'row',height:55,justifyContent: 'space-between'}}>
                        <Text style={{fontSize:16,paddingLeft:15,lineHeight:60}}>拜访记录</Text>
                        <Image
                            style={{width:35,height:35,marginTop:12,textAlign :'right',marginRight:20}}
                            source={require('../../../img/Customer/u1723.png'
                            )}
                        />
                    </View>
                    <TextInput
                        underlineColorAndroid='transparent'
                        style={{width:380,height:150,borderColor: 'gray', borderWidth: 1,marginLeft:15}}></TextInput>
                </View>
            </View>
        );
    }
}