import React from 'react';
import {StyleSheet,TouchableWithoutFeedback,ScrollView, Text ,View,Image,TextInput,TouchableOpacity,Modal,TouchableHighlight } from 'react-native';
import Echarts from 'native-echarts';
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
export class CustomerDetailsHistory extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            modalVisible:false,
        };
    }

    render() {
        const modalVisible = this.state.modalVisible;
        const constoption={
            title: {
                text: ''
            },
            animation:false,
            tooltip: {

            },
            legend: {
                data: ['预算分配']
            },
            radar: {
                shape: 'circle',
                name: {
                    textStyle: {
                        color: '#000000',
                        backgroundColor: '#999',
                        borderRadius: 3,
                        padding: [3,
                            5]
                    }
                },
                splitArea : {
                    show : false,
                    areaStyle : {
                        color: ["#2a4a93"]  // 图表背景网格的颜色
                    }
                },
                indicator: [{
                    name: '投资',
                    max: 100
                },
                    {
                        name: '医疗',
                        max: 100
                    },
                    {
                        name: '重疾',
                        max: 100
                    },
                    {
                        name: '教育或养老',
                        max: 100
                    },
                    {
                        name: '意外',
                        max: 100
                    },
                    {
                        name: '身故',
                        max: 100
                    }]
            },
            series: [{
                type: 'radar',
                itemStyle : {
                    normal : {
                        color:'#7CD448',
                        lineStyle:{
                            color:'#7CD448'
                        }
                    }
                },
                data: [{
                        name:'r1',
                        value: [75,75,75,75,75,75],
                }]
    }]
    };
        return (
            <View style={{backgroundColor:'#FFFFFF',marginBottom:50}}>
                <View style={{backgroundColor:'#FFFFFF', flexDirection: 'row',justifyContent: 'space-between',height:50}}>
                    <Text style={{fontSize:16,marginTop:12,color:'#666666',marginLeft:10,fontWeight:'400'}}>|  保单体验</Text>
                </View>
                <View style={{justifyContent:'center', alignItems: 'center'}}>
                    <Echarts
                        option={constoption}
                        height={240}
                        width={240}/>
                </View>


                <View style={{backgroundColor:'#FFFFFF', flexDirection: 'row',justifyContent: 'space-between',height:50}}>
                    <Text style={{fontSize:20,marginTop:12,color:'#666666',marginLeft:10,fontWeight:'400'}}>|  保障分布</Text>
                </View>

                <View style={{flexDirection: 'row',marginTop:5}}>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>保障类型</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 3}}>
                        <Text style={{color:'#999999'}}>保障项目</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>已有保障</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>缺口保障</Text>
                    </View>
                </View>

                <View style={{flexDirection: 'row',marginTop:5}}>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>重疾</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 3}}>
                        <Text style={{color:'#999999'}}>恶性肿瘤保险金</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>200,000</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>0</Text>
                    </View>
                </View>

                <View style={{flexDirection: 'row',marginTop:5}}>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>医疗</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 3}}>
                        <Text style={{color:'#999999'}}>住院津贴保险金</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>200,000</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>0</Text>
                    </View>
                </View>

                <View style={{flexDirection: 'row',marginTop:5}}>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>身故</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 3}}>
                        <Text style={{color:'#999999'}}>身故保险金</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>200,000</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>0</Text>
                    </View>
                </View>

                <View style={{flexDirection: 'row',marginTop:5}}>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>意外</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 3}}>
                        <Text style={{color:'#999999'}}>意外身故保险金</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>200,000</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>0</Text>
                    </View>
                </View>
                <View style={{flexDirection: 'row',marginTop:5}}>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#999999'}}>教育或养老</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 3}}>
                        <Text style={{color:'#999999'}}>教育保险金</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>200,000</Text>
                    </View>
                    <View style={{ alignItems: 'center',justifyContent:'center',flex: 2}}>
                        <Text style={{color:'#FFB100'}}>0</Text>
                    </View>
                </View>

                <View style={{backgroundColor:'#FFFFFF', flexDirection: 'row',justifyContent: 'space-between',height:50}}>
                    <Text style={{fontSize:18,marginTop:12,color:'#666666',marginLeft:10,fontWeight:'400'}}>|  保单</Text>
                    <TouchableWithoutFeedback onPress={()=>{this.setState({modalVisible:true})}}>
                        <View style={{backgroundColor:'#FFFFFF', flexDirection: 'row',marginRight:20}}>
                            <Text style={{color:'#999999',marginTop:20}}>筛选</Text>
                            <Image
                                style={{width:25,height:25,marginTop:15}}
                                source={require('../../../img/Customer/u9010.png')}
                            />
                        </View>
                    </TouchableWithoutFeedback>
                </View>
                <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{fontWeight:'bold',paddingLeft:15,color:'#333333',fontSize:15}}>花开富贵两全保险（分红型）</Text>
                    <Text style={{textAlign:'right',paddingRight:15,color:'#FF9300'}}>保障中</Text>
                </View>
                <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{paddingLeft:15,color:'#333333'}}>BDNU201801010001</Text>
                    <Text style={{textAlign:'right',paddingRight:15,color:'#333333'}}>有效期至2018-12-31</Text>
                </View>
                <View style={{paddingTop:5,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{paddingLeft:15,marginTop:5,marginTop:5,color:'#333333'}}>郑秋冬/罗伊人/贾琦</Text>
                    <View style={{flexDirection: 'row'}}>
                        <Text style={{textAlign:'right',marginTop:5,color:'#CCCCCC'}}>详细信息</Text>
                        <Image
                            style={{width:30,height:30,marginRight:10}}
                            source={require('../../../img/Customer/u1693.gif'
                            )}
                        />
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#F2F2F2'}}/>
                <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{fontWeight:'bold',paddingLeft:15,color:'#333333',fontSize:15}}>花开富贵两全保险（分红型）</Text>
                    <Text style={{textAlign:'right',paddingRight:15,color:'#FF9300'}}>保障中</Text>
                </View>
                <View style={{paddingTop:10,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{paddingLeft:15,color:'#333333'}}>BDNU201801010001</Text>
                    <Text style={{textAlign:'right',paddingRight:15,color:'#333333'}}>有效期至2018-12-31</Text>
                </View>
                <View style={{paddingTop:5,flexDirection: 'row',justifyContent: 'space-between'}}>
                    <Text style={{paddingLeft:15,marginTop:5,marginTop:5,color:'#333333'}}>郑秋冬/罗伊人/贾琦</Text>
                    <View style={{flexDirection: 'row'}}>
                        <Text style={{textAlign:'right',marginTop:5,color:'#CCCCCC'}}>详细信息</Text>
                        <Image
                            style={{width:30,height:30,marginRight:10}}
                            source={require('../../../img/Customer/u1693.gif'
                            )}
                        />
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#F2F2F2'}}/>


                <Modal visible={modalVisible}
                       animationType={'none'}
                       transparent = {true}  >
                    <View style={{justifyContent: 'center',flex:1,backgroundColor:'rgba(0,0,0,0.5)'}}>
                        <View style={{backgroundColor:'white',borderStyle: 'solid',borderWidth: 1,borderColor: 'red',width:ScreenWidth-100,height:220,marginTop:10,marginLeft:50}}>
                            <Text style={{fontWeight:'bold',paddingLeft:20,color:'#666666',paddingTop:10}}>筛选条件</Text>
                            <Text style={{borderBottomWidth:1,borderBottomColor:'#666666',width:ScreenWidth-140,marginLeft:20}}/>
                            <View style={{ flexDirection: 'row',paddingTop:10}}>
                                <Text style={{paddingLeft:30,color:'#666666',paddingTop:10}}>产品名称：</Text>
                                <TextInput
                                    underlineColorAndroid='transparent'
                                    style={{width:ScreenWidth-230,height:30, borderColor: 'gray', borderWidth: 1}}></TextInput>
                            </View>
                            <View style={{ flexDirection: 'row',paddingTop:10}}>
                                <Text style={{paddingLeft:30,color:'#666666',paddingTop:10}}>保单号 ：</Text>
                                <TextInput
                                    underlineColorAndroid='transparent'
                                    style={{width:ScreenWidth-230,height:30, borderColor: 'gray', borderWidth: 1,marginLeft:10}}></TextInput>
                            </View>
                            <View style={{ flexDirection: 'row',paddingTop:10}}>
                                <Text style={{paddingLeft:30,color:'#666666',paddingTop:10}}>保单状态：</Text>
                                <TextInput
                                    underlineColorAndroid='transparent'
                                    style={{width:ScreenWidth-230,height:30, borderColor: 'gray', borderWidth: 1}}></TextInput>
                            </View>
                            <View style={{ flexDirection: 'row',justifyContent: 'space-between',width:ScreenWidth-300}}>
                                <TouchableWithoutFeedback onPress={()=>{this.setState({modalVisible:false})}}>
                                    <View
                                        style={{height: 30,  width: 80, borderRadius: 10, backgroundColor: '#CCCCCC',justifyContent: 'center',margin: 20,}}>
                                        <Text style={{textAlign:'center',color: 'white'}}>取消</Text>
                                    </View>
                                </TouchableWithoutFeedback>
                                <TouchableWithoutFeedback onPress={()=>{this.setState({modalVisible:false})}}>
                                    <View
                                        style={{height: 30,  width: 80, borderRadius: 10, backgroundColor: '#CCCCCC',justifyContent: 'center',margin: 20,}}>
                                        <Text style={{textAlign:'center',color: 'white'}}>确认</Text>
                                    </View>
                                </TouchableWithoutFeedback>
                            </View>
                            <Text style={{borderBottomWidth:1,borderBottomColor:'#ffffff',paddingTop:5,marginBottom:100}}/>
                        </View>
                        </View>
                </Modal>

            </View>
        );
    }
}