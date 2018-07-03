import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,Button,TouchableOpacity } from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Textarea from 'react-native-textarea';
import * as RequestURL from "../../common/RequestURL";
import Toast from './Toast/Toast';

export class OtherApply extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id:'',
            description:'',
            type:'10',  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode:'10000',
            createDatetime:'',
            status:'', //0 表示正在审批当中了；1表示结束；2驳回。
            startTime:'',
            endTime:'',
            endDatetime:'',
            leaveType:'',
            leaveOff:'',
            title:'',
            imcomeproveMonth:'',
            currentGrade:'',
            agentName:'JohnnyZ'
        }
    }
    submissionForm=()=>{
        let descr = this.state.description;
        // 用途不写予以警告。
        if(descr===''){
            alert('详细说明没有编写');
            return;
        }

        let formData = new FormData();
        formData.append("type",this.state.type);
        formData.append("description",this.state.description);
        formData.append("agentCode",this.state.agentCode);
        formData.append("agentName",this.state.agentName);

        this.fetchData(formData);
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
                //alert(parmars);
                if (responseJson.state==='1'){
                    Toast.show("保存成功",Toast.LONG);
                    //DeviceEventEmitter.emit('userCenterToAdministrative'); //用户中心跳转到行政审批

                }else {
                    Toast.show("保存失败",Toast.LONG);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader
                    title={'万能申请'}
                    onReturn={() => this.props.navigation.goBack()}
                />
                <View style={{flexDirection:'column',paddingLeft:10,paddingRight:10,backgroundColor:'#FFFFFF',paddingTop:10
                    ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                    <View style={{flexDirection:'column',paddingLeft:10,paddingRight:10,backgroundColor:'#FFFFFF',paddingTop:10
                        ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                        <View style={{marginBottom:10}}>
                            <Text style={{color:'#333333',fontSize:15}}>详细说明</Text>
                        </View>
                        <View style={styles.container}>
                        <Textarea
                            containerStyle={styles.textareaContainer}
                            style={styles.textarea}
                            onChangeText={(text) => {this.setState({description:text});}}
                            defaultValue={this.state.description}
                            maxLength={120}
                            placeholder={'请输入详细说明'}
                            placeholderTextColor={'#c7c7c7'}
                            underlineColorAndroid={'transparent'}
                        />
                        </View>
                    </View>

                    <View style={{marginTop:10,marginBottom:10}}>
                        <Text style={{color:'#333333',fontSize:16}}>图片</Text>
                    </View>
                    <View style={{width:70,height:70,flexDirection:'row',marginRight:15,}}>
                        <Image style={{width:70,height:65,}} source={require('../../../img/UserCenter/addImage.png')} />
                    </View>
                </View>

                <TouchableOpacity  onPress={this.submissionForm}>
                    <View style={{flexDirection: 'row',marginTop:80}}>
                        <Text style={{flex:0.5}}></Text>
                        <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',
                            backgroundColor:(this.state.description!=='')?'#FFDD00':'#EBEBEB'
                            ,color:(this.state.description!=='')?'#000000':'#858585'}}
                        >提交</Text>
                        <Text style={{flex:0.5}}></Text>
                    </View>
                </TouchableOpacity>
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