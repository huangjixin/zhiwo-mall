import React from "react";
import {Image, Text, View, ScrollView, TouchableWithoutFeedback, DeviceEventEmitter} from "react-native";

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class WholeCustomer  extends React.Component{
    render() {
        return (
                <View>
                    <View style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#FFFFFF',height:45}}>
                        <Text style={{textAlign:'center',marginTop:10,marginLeft:10,lineHeight:25,borderRadius:25,width:70,height:25,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>标签1</Text>
                        <Text style={{textAlign:'center',marginTop:10,lineHeight:25,borderRadius:25,width:70,height:25,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>标签2</Text>
                        <Text style={{textAlign:'center',marginTop:10,lineHeight:25,borderRadius:25,width:70,height:25,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>标签3</Text>
                        <Text style={{textAlign:'center',marginTop:10,lineHeight:25,borderRadius:25,width:70,height:25,color:'#FF9900',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#FF9900'}}>标签4</Text>
                        <Text style={{textAlign:'center',marginTop:10,marginRight:10,lineHeight:25,borderRadius:25,width:70,height:25,color:'#ADADAD',fontWeight:'600',borderStyle: 'solid',borderWidth: 2,borderColor: '#ADADAD'}}>+标签</Text>
                    </View>
                    <Text style={{backgroundColor: '#E4E4E4',fontWeight:'bold',color: '#000000',fontSize:18,paddingLeft:10}}>J</Text>
                    <TouchableWithoutFeedback onPress={()=>{DeviceEventEmitter.emit('CustomerDetails');}}>
                        <View style={{backgroundColor:'white',flexDirection: 'row',justifyContent:'center',height:75}}>
                                    <View style={{flex:1,justifyContent:'center',alignItems: 'center'}}>
                                        <Image  source={require('../../../img/Customer/u2462.png')}
                                                style={{width: 60, height:60}} />
                                    </View>
                                    <View style={{flexDirection: 'column',flex:1,justifyContent:'center',alignItems: 'flex-start'}}>
                                            <Text style={{fontSize:18}}>贾珍</Text>
                                            <Text style={{paddingTop:5,color:'#FF9300',fontSize:12}}>老客户 | VIP等级</Text>
                                    </View>
                                    <View style={{flex:2,flexDirection: 'row'}}>
                                        <Text style={{marginLeft:25,lineHeight:75}}>15073922456</Text>
                                        <Image  source={require('../../../img/Customer/Telephone.gif')}
                                                style={{width: 60, height:60,marginTop:10,marginLeft:10}} />
                                    </View>
                        </View>
                    </TouchableWithoutFeedback>
                    <Text style={{backgroundColor: '#E4E4E4',fontWeight:'bold',color: '#000000',fontSize:18,paddingLeft:10}}>L</Text>

                    <TouchableWithoutFeedback onPress={()=>{DeviceEventEmitter.emit('CustomerDetails');}}>
                        <View style={{backgroundColor:'white',flexDirection: 'row',justifyContent:'center',height:75}}>
                            <View style={{flex:1,justifyContent:'center',alignItems: 'center'}}>
                                <Image  source={require('../../../img/Customer/u2462.png')}
                                        style={{width: 60, height:60}} />
                            </View>
                            <View style={{flexDirection: 'column',flex:1,justifyContent:'center',alignItems: 'flex-start'}}>
                                <Text style={{fontSize:18}}>罗宁</Text>
                                <Text style={{paddingTop:5,color:'#FF9300',fontSize:12}}>老客户 | VIP等级</Text>
                            </View>
                            <View style={{flex:2,flexDirection: 'row'}}>
                                <Text style={{marginLeft:25,lineHeight:75}}>15073922456</Text>
                                <Image  source={require('../../../img/Customer/Telephone.gif')}
                                        style={{width: 60, height:60,marginTop:10,marginLeft:10}} />
                            </View>
                        </View>
                    </TouchableWithoutFeedback>

                    <Text style={{backgroundColor: '#E4E4E4',fontWeight:'bold',color: '#000000',fontSize:18,paddingLeft:10}}>Z</Text>

                    <TouchableWithoutFeedback onPress={()=>{DeviceEventEmitter.emit('CustomerDetails');}}>
                        <View style={{backgroundColor:'white',flexDirection: 'row',justifyContent:'center',height:75}}>
                            <View style={{flex:1,justifyContent:'center',alignItems: 'center'}}>
                                <Image  source={require('../../../img/Customer/u2462.png')}
                                        style={{width: 60, height:60}} />
                            </View>
                            <View style={{flexDirection: 'column',flex:1,justifyContent:'center',alignItems: 'flex-start'}}>
                                <Text style={{fontSize:18}}>张三丰</Text>
                                <Text style={{paddingTop:5,color:'#FF9300',fontSize:12}}>老客户 | VIP等级</Text>
                            </View>
                            <View style={{flex:2,flexDirection: 'row'}}>
                                <Text style={{marginLeft:25,lineHeight:75}}>15073922456</Text>
                                <Image  source={require('../../../img/Customer/Telephone.gif')}
                                        style={{width: 60, height:60,marginTop:10,marginLeft:10}} />
                            </View>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
        );
    }
}