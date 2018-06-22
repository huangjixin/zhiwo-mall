import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter,Dimensions,TouchableOpacity} from 'react-native';
import ModalDropdown from 'react-native-modal-dropdown';
import Picker from 'react-native-picker';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
// import area from '../../../area.json';
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class AddAddress extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            region:null,
            detailed:null,
        }
        // this.fetchData = this.fetchData.bind(this);
    }
    //AddAddressPhonePage=(item)=>{
    AddAddressPhonePage=()=>{
        alert("所在地区"+this.state.region + "详细地址" +this.state.detailed)
        DeviceEventEmitter.emit('AddAddressPhonePage'); //添加地址页面-》添加手机号码页面
    }

    render() {
        const { params } = this.props.navigation.state;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader
                    title={'编辑地址'}
                    onReturn={() => this.props.navigation.goBack()}
                    actionName={'删除'}
                    onAction={() => alert('delete')}
                />
                <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:60}}>
                    <Text  style={{flex:2,lineHeight:60,height:60,textAlign:'right',fontSize:16}}>所在地区</Text>
                    <View style={{flex:7,flexDirection: 'column',justifyContent: 'center',marginLeft:10}}>
                        <TextInput
                            placeholder='上海松江区'
                            underlineColorAndroid='transparent'
                            onChangeText={(value)=>this.setState({region: value})}
                            style={{height: 60,marginLeft:10,backgroundColor:"#FFFFFF",fontSize:16}}>{this.state.region}</TextInput>
                    </View>
                    <View style={{flex:1,flexDirection: 'column',justifyContent: 'center'}}>
                        <Image style={{width: 30, height: 20}} source={require('../../../img/UserCenter/addressAdd.gif')} />
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#F4F4F4',backgroundColor:'#ffffff'}}/>
                <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:60}}>
                    <Text  style={{flex:2,lineHeight:60,height:60,textAlign:'right',fontSize:16}}>详细地址</Text>
                    <View style={{flex:7,flexDirection: 'column',justifyContent: 'center',marginLeft:10}}>
                        <TextInput
                            placeholder='上海松江区中山公园'
                            underlineColorAndroid='transparent'
                            onChangeText={(value)=>this.setState({detailed: value})}
                            style={{height: 60,marginLeft:10,backgroundColor:"#FFFFFF",fontSize:16}}>{this.state.detailed}</TextInput>
                    </View>
                    <View style={{flex:1,flexDirection: 'column',justifyContent: 'center'}}>

                    </View>
                </View>
                <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:60}}>
                </View>
                <View style={{flexDirection: 'row',marginTop:80}}>
                    <Text style={{flex:0.5}}></Text>
                    <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center', backgroundColor:'#FFDD00',color:'#000000'}}
                        // onPress={this.AddAddressPage.bind(this,item)}
                          onPress={this.AddAddressPhonePage}
                    >保存</Text>
                    <Text style={{flex:0.5}}></Text>
                </View>
            </ScrollView>

        );
    }
}

