import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,Button,TouchableOpacity } from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Textarea from 'react-native-textarea';
import Picker from 'react-native-picker';

export class OtherProve extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            // text: '',
            purpose:'',
            materialsType:'',
            submitIndicator:false
        }
    }

    checkSubmitForm = ()=>{
        const {purpose,materialsType,submitIndicator } = this.state;
        if(materialsType.length!=0 && purpose.length!=0 ){
            if(!submitIndicator)
                this.setState({
                    submitIndicator:true
                })
        }else if(submitIndicator){
            this.setState({
                submitIndicator:false
            })
        }
    }

    onInputChange = (text)=>{
        this.state.purpose = text;
        this.checkSubmitForm();
    }

    onSubmit= ()=>{
        if(this.state.submitIndicator){
            alert(JSON.stringify(this.state));
        }
    }

    _showMaterialsPicker() {
        Picker.init({
            pickerData: ['工作经历证明','学历证明','其他'],
            pickerFontColor: [0, 0 ,0, 1],
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            pickerTitleText:'请选择材料类型',
            onPickerConfirm: (pickedValue, pickedIndex) => {
                console.log(pickedValue[0]);
                console.log(pickedIndex[0]);
                this.setState({
                    materialsType:pickedValue[0],
                })
                this.checkSubmitForm();
            },

        });
        Picker.show();
    }

    render() {
        const submitIndicator = this.state.submitIndicator;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader
                    title={'其他证明'}
                    onReturn={() => this.props.navigation.goBack()}
                />
                <View style={{flexDirection:'column',paddingLeft:15,backgroundColor:'#FFFFFF',paddingTop:10
                    ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>

                    <View style={{flexDirection:'row',borderColor:'#F2F2F2',borderBottomWidth:1,
                        backgroundColor:'#FFFFFF',height:45,justifyContent: 'center',alignItems: 'center'}}>
                        <View style={{flex:2}}>
                            <Text style={{color:'#333333',fontSize:16}}>材料类型</Text>
                        </View>
                        <View style={{flex:8,flexDirection:'row',paddingRight:10,}}>
                            <TouchableOpacity style={{height:40,flex:7,}} onPress={this._showMaterialsPicker.bind(this)}>
                                {this.state.materialsType.length==0?(
                                    <Text style={{flex:9,textAlign:'right',paddingRight:15,fontSize:16,lineHeight:40,}}>请选择</Text>
                                ):(
                                    <Text style={{flex:9,textAlign:'right',paddingRight:15,fontSize:16,lineHeight:40,}}>{this.state.materialsType[0]}</Text>
                                )}
                            </TouchableOpacity>
                            <View  style={{flex:1,alignItems:'center',justifyContent:'center',}}>
                                <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                            </View>
                        </View>
                    </View>

                    <View style={{marginBottom:10,marginTop:10}}>
                        <Text style={{color:'#333333',fontSize:16}}>用途</Text>
                    </View>
                    <View style={styles.container}>
                        <Textarea
                                containerStyle={styles.textareaContainer}
                                style={styles.textarea}
                                onChangeText={this.onInputChange}
                                defaultValue={this.state.purpose}
                                maxLength={120}
                                placeholder={'请输入详细说明'}
                                placeholderTextColor={'#c7c7c7'}
                                underlineColorAndroid={'transparent'}
                            />
                    </View>
                    <View style={{marginTop:10,marginBottom:10}}>
                        <Text style={{color:'#333333',fontSize:16}}>图片</Text>
                    </View>
                    <View style={{width:70,height:70,flexDirection:'row',marginRight:15,}}>
                        <Image style={{width:70,height:65,}} source={require('../../../img/UserCenter/addImage.png')} />
                    </View>
                </View>

                <TouchableWithoutFeedback onPress={this.onSubmit}>
                    <View style={[{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                        ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1},
                        submitIndicator?{backgroundColor:'#FFDD00'}:{backgroundColor:'#FFFFFF'}
                    ]}>
                        <Text style={{color:'#E87722',fontSize:16}}>提交</Text>
                    </View>
                </TouchableWithoutFeedback>
            </ScrollView>
        );
    }

}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      padding: 0,
      justifyContent: 'center',
      alignItems: 'center',
      borderColor:'#F1F1F1',
      borderWidth:1
    },
    textareaContainer: {
      height: 180,
      padding: 5,
      backgroundColor: '#FFFFFF',
    },
    textarea: {
      textAlignVertical: 'top',  // hack android
      height: 170,
      fontSize: 16,
      color: '#000000',
    },
  });