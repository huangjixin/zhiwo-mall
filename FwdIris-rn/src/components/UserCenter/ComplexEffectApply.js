import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,TouchableOpacity} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Toast from './Toast/Toast';
//import ModalDropdown from 'react-native-modal-dropdown';
import Textarea from 'react-native-textarea';
import Picker from 'react-native-picker';
import * as RequestURL from "../../common/RequestURL";
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class ComplexEffectApply extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            complexEffectList:[], //复效人员


            id: '',
            description: '',
            type: 3,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode: '10000',
            createDatetime: '',
            status: '', //0 表示正在审批当中了；1表示结束；2驳回。
            startTime: '',
            endTime: '',
            endDatetime: '',
            leaveType: '',
            leaveOff: '',//时长
            title: '',
            imcomeproveMonth: '',
            currentGrade: '',
            upGrade: '',
            name: '',
            agentName: 'JohnnyZ'


        }
        this.fetchNameData();
        // this.fetchData = this.fetchData.bind(this);
    }


    fetchNameData(parmars) {
        let url = RequestURL.HOST+'applyForm/selectComplexEffect?agentCode=123';
        fetch(url, {
            method: 'POST',
//             headers: {
//                 'Accept': 'application/json',
//    　　　　      'Content-Type': 'application/json',
//             },
            body:parmars,
            // body: JSON.stringify({
            //     firstParam: 'yourValue',
            //     secondParam: 'yourOtherValue',
            // })
        })
            .then((response) => response.json( ))
            .then((responseJson) => {
                    this.setState({
                        agentGradeList:responseJson,
                    });
            })
            .catch((error) => {
                console.error(error);
            });
    }





    ComplexEffectApplySubmission=()=>{
        let comPerson = this.state.name;
        let descr = this.state.description;
        // 用途不写予以警告。
        if(comPerson===''){
            alert('复效人员没有选择');
        }else if(descr===''){
            alert('详细说明没有编写');
        }else if(descr.length > 100){
            alert('字符长度不得大于100');
        }else{

            let formData = new FormData();
            formData.append("type",this.state.type);
            formData.append("agentName",this.state.agentName);
            formData.append("description",this.state.description);
            formData.append("agentCode",this.state.agentCode);
            formData.append("name",this.state.name);

            this.fetchData(formData);
    }
    }

    fetchData(parmars) {
        let url = RequestURL.HOST+'applyForm/saveSingleForm';
        fetch(url, {
            method: 'POST',
//             headers: {
//                 'Accept': 'application/json',
//    　　　　      'Content-Type': 'application/json',
//             },
            body:parmars,
            // body: JSON.stringify({
            //     firstParam: 'yourValue',
            //     secondParam: 'yourOtherValue',
            // })
        })
            .then((response) => response.json( ))
            .then((responseJson) => {
           // alert(parmars);
                // if (responseJson===1){
                //     DeviceEventEmitter.emit('userCenterToAdministrative'); //用户中心跳转到行政审批
                // }
                if (responseJson.state==='1'){
                    Toast.show("保存成功",Toast.LONG);
                }else {
                    Toast.show("保存失败",Toast.LONG);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }

    _showOaApplyPicker() {
        Picker.init({
            pickerData: this.state.complexEffectList,
            pickerFontColor: [0, 0 ,0, 1],
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            pickerTitleText:'请选择复效人员',
            onPickerConfirm: (pickedValue, pickedIndex) => {
                this.setState({
                    name:pickedValue[0],
                })
                //console.log('date', pickedValue[0], pickedIndex,this.state.leaveType);
                // alert(pickedValue[0]+'----'+pickedValue[1]);
                // console.log('date', pickedValue, pickedIndex);
            },
            // onPickerCancel: (pickedValue, pickedIndex) => {
            //     console.log('date', pickedValue, pickedIndex);
            // },
            // onPickerSelect: (pickedValue, pickedIndex) => {
            //     console.log('date', pickedValue, pickedIndex);
            // }
        });
        Picker.show();
    }

    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader title={'复效申请'} onReturn={() => this.props.navigation.goBack()}/>
                <View style={{flexDirection: 'row',height:40,backgroundColor:'white',alignItems:'center',}}>
                    <Text  style={{lineHeight:40,height:40,marginLeft:25,color:'#000000',fontSize:16}}>复效人员</Text>

                    <TouchableOpacity style={{height:40,flex:7,}} onPress={this._showOaApplyPicker.bind(this)}>
                        {this.state.name===''?(
                            <Text  style={{lineHeight:40,height:40,textAlign:'right',color:'#000000',marginRight:10,fontSize:16}}>请选择</Text>
                        ):(
                            <Text  style={{lineHeight:40,height:40,textAlign:'right',color:'#000000',marginRight:10,fontSize:16}}>{this.state.name}</Text>
                        )}
                    </TouchableOpacity>

                    <View  style={{flex:1,alignItems:'center',justifyContent:'center',}}>
                        <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                    </View>






                </View>
                <View style={{flexDirection: 'row',backgroundColor:'white',marginTop:2}}>
                    <Text  style={{flex:2,lineHeight:40,height:40,marginLeft:25,color:'#000000',fontSize:16}}>详细说明</Text>
                    <Text  style={{flex:8,lineHeight:40,height:40,color:'#000000'}}></Text>
                </View>
                <View style={{backgroundColor:'#ffffff'}}>
                    <View style={{ flex: 1,
                        padding: 0,
                        justifyContent: 'center',
                        alignItems: 'center',
                        borderColor:'#F1F1F1',
                        backgroundColor:'#000000',
                        borderWidth:1, marginLeft: 25,marginRight:20}}>
                        <Textarea
                            containerStyle={{ height: 180,
                                padding: 5,
                                backgroundColor: '#FFFFFF',}}
                            style={{textAlignVertical: 'top',  // hack android
                                height: 170,
                                fontSize: 16,
                                color: '#000000', borderColor:'#F1F1F1',}}
                            onChangeText={(text) => {this.setState({description:text});}}
                            defaultValue={this.state.description}
                            maxLength={100}
                            placeholder={'请输入详细说明'}
                            placeholderTextColor={'#c7c7c7'}
                            underlineColorAndroid={'transparent'}
                        />
                    </View>
                </View>
                <View style={{flexDirection: 'row',marginTop:80}}>
                    <Text style={{flex:0.5}}></Text>
                    <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',
                        backgroundColor:((this.state.name!=='')&&(this.state.description!==''))?'#FFDD00':'#EBEBEB',
                        color:((this.state.name!=='')&&(this.state.description!==''))?'#000000':'#858585'}}
                          onPress={this.ComplexEffectApplySubmission}
                    >提交</Text>
                    <Text style={{flex:0.5}}></Text>
                </View>
            </ScrollView>
        );
    }
}

