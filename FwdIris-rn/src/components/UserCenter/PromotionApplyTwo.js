import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback} from 'react-native';
import CheckBox from 'react-native-checkbox';

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class PromotionApplyTwo extends React.Component {
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
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <View style={{backgroundColor:'#FFDD00',height:50,flexDirection: 'row',justifyContent:'flex-start',alignItems:'center',}}>
                    <View style={{flex:4,paddingLeft:10,}}>
                        <TouchableWithoutFeedback style={{width:30,height:30,}}  onPress={() => this.props.navigation.goBack()}>
                            <Image style={{width:11,height:22,}}source={require('../../../img/UserCenter/GoBackBlack.png')}/>
                        </TouchableWithoutFeedback>
                    </View>
                    <Text style={{flex:6,fontSize:22}}>晋级申请</Text>
                </View>
                <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',marginTop:20,height:40}}>
                    <Text  style={{flex:2,lineHeight:40,height:40,textAlign:'right',color:'#000000'}}>当前职位</Text>
                    <Text  style={{flex:6.5,lineHeight:40,height:40,textAlign:'right',color:'#000000'}}>LA</Text>
                    <Text  style={{flex:1.5}}></Text>
                </View>
                <View style={{flexDirection: 'row',height:40,backgroundColor:'white',marginTop:10,alignItems:'center',}}>
                    <Text  style={{flex:2,lineHeight:40,height:40,textAlign:'right',color:'#000000'}}>申请职级</Text>
                    <Text  style={{flex:6.5,lineHeight:40,height:40,textAlign:'right',color:'#000000'}}>SM</Text>
                    <Text style={{flex:1.5,textAlign:'center'}}></Text>
                </View>
                <View style={{flexDirection: 'row',marginTop:10,marginLeft:10}}>
                    <CheckBox
                        checked={this.state.isSe1}
                        label = ''
                        checkboxStyle={{width:20,height:20,backgroundColor:'#ffffff'}}
                        onChange={(checked) => {
                            this.setState({
                                isSe1:!checked
                            });
                        }}
                    />
                    <Text  style={{color:'#000000',marginLeft:5,marginRight:3,flex:1}}>
                        本人承诺晋级申请由晋级人员本人亲自填写，并知晓达到晋级业绩标准且经公司审批同意后方可晋级至所申请的级别
                    </Text>
                </View>
                <View style={{flexDirection: 'row',marginTop:80}}>
                    <Text style={{flex:0.5}}></Text>
                    <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',
                        fontWeight:'900',backgroundColor:'#3598FA',color:'#ffffff'}}
                    >提交</Text>
                    <Text style={{flex:0.5}}></Text>
                </View>
            </ScrollView>
        );
    }
}

