import React from "react";
import {StyleSheet, ScrollView, Text,View,Image} from 'react-native';
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
export class CustomerPolicyList  extends React.Component{
    render() {
        return (
                <View>
                    <View>
                        <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                        <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                            <Text style={{fontWeight:'bold',paddingLeft:15}}>花开富贵两全保险（分红型）</Text>
                            <Text style={{textAlign:'right',paddingRight:15,color:'#FF9300'}}>新单回执</Text>
                        </View>
                        <Text style={{textAlign:'right',paddingRight:15,fontSize:12,paddingTop:5}}>2017-12-30 核保通过</Text>
                        <View style={{flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img001.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>保单号： 01000000012345</Text>
                        </View>
                        <View style={{paddingTop:5,flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img002.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>投保人： Evan</Text>
                        </View>
                        <View style={{paddingTop:5,flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img003.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>被保人： 张宝宝</Text>
                        </View>
                        <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    </View>
                    <View>
                        <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC',height:4}}/>
                        <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                            <Text style={{fontWeight:'bold',paddingLeft:15}}>花开富贵两全保险（分红型）</Text>
                            <Text style={{textAlign:'right',paddingRight:15,color:'#FF9300'}}>新单回执</Text>
                        </View>
                        <Text style={{textAlign:'right',paddingRight:15,fontSize:12,paddingTop:5}}>2017-12-30 核保通过</Text>
                        <View style={{flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img001.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>保单号： 01000000012345</Text>
                        </View>
                        <View style={{paddingTop:5,flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img002.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>投保人： Evan</Text>
                        </View>
                        <View style={{paddingTop:5,flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img003.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>被保人： 张宝宝</Text>
                        </View>
                        <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    </View>
                    <View>
                        <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC',height:4}}/>
                        <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                            <Text style={{fontWeight:'bold',paddingLeft:15}}>花开富贵两全保险（分红型）</Text>
                            <Text style={{textAlign:'right',paddingRight:15,color:'#FF9300'}}>照会中</Text>
                        </View>
                        <Text style={{textAlign:'right',paddingRight:15,fontSize:12,paddingTop:5}}>2017-12-30 人核触发</Text>
                        <View style={{flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img001.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>保单号： 01000000012345</Text>
                        </View>
                        <View style={{paddingTop:5,flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img002.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>投保人： Evan</Text>
                        </View>
                        <View style={{paddingTop:5,flexDirection: 'row'}}>
                            <Image  source={require('../../../img/Customer/Policy_img003.gif')} style={{marginLeft:15}}/>
                            <Text style={{paddingLeft:5,marginTop: 5}}>被保人： 张宝宝</Text>
                        </View>
                        <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>
                    </View>
                    <View style={{height:5}}></View>
                </View>
        );
    }
}