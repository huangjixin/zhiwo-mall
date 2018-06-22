import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,Button,TouchableOpacity,DeviceEventEmitter } from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Textarea from 'react-native-textarea';
// import * as RequestURL from "../../common/RequestURL";
//import ModalDropdown from 'react-native-modal-dropdown';
import * as RequestURL from "../../common/RequestURL";


var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class QuitApply extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            id:'',
            description:'',
            type:0,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
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
            upGrade:'',
            name:'',
            agentName:'JohnnyZ'
        }
        // this.fetchData = this.fetchData.bind(this);
    }

    QuitApplySubmission=()=>{
        // if(this.state.title!==''&&this.state.description!==''){
        let reason = this.state.title;
        let descr = this.state.description;
        // 用途不写予以警告。
        if(reason===''){
            alert('离职原因没有编写');
        }else if(reason.length > 100){
            alert('离职原因不得大于100');
        }else if(descr===''){
            alert('详细说明没有编写');
        }else if(descr.length > 1000){
            alert('详细说明不得大于1000');
        }else{

            let formData = new FormData();
            formData.append("type",this.state.type);
            formData.append("title",this.state.title);
            formData.append("description",this.state.description);
            formData.append("agentCode",this.state.agentCode);
            formData.append("agentName",this.state.agentName);

            this.fetchData(formData);

            // 做网络代码提交，此处暂时先做alert处理。
            // alert(JSON.stringify(obj));
            // }
            //alert("离职原因"+this.state.title + "详细说明" +this.state.description)
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
                alert(parmars);
                // if (responseJson===1){
                //     DeviceEventEmitter.emit('userCenterToAdministrative'); //用户中心跳转到行政审批
                // }
            })
            .catch((error) => {
                console.error(error);
            });
    }
    // _dropdown_enderRow(rowData, rowID, highlighted) {
    //     return (
    //         <View style={{flexDirection: 'row',height: 40,alignItems: 'center'}}>
    //             <Text style={{paddingLeft:10}}>
    //                 {`${rowData}`}
    //             </Text>
    //         </View>
    //     );
    // }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader title={'离职申请'} onReturn={() => this.props.navigation.goBack()}/>
                <View  style={{flexDirection: 'row',backgroundColor:'#ffffff',height:50}}>
                    <Text  style={{lineHeight:50,height:50,marginLeft:25,color:'#000000',fontSize:16}}>离职原因</Text>
                    <TextInput
                        placeholder='请输入离职原因'
                        underlineColorAndroid='transparent'
                        style={{flex:8,height: 50,marginLeft:10,backgroundColor:"#FFFFFF",color:'#000000',fontSize:16}}
                        onChangeText={(value)=>this.setState({title: value})}>
                        {this.state.title}
                    </TextInput>
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
                            maxLength={1000}
                            placeholder={'请输入详细说明'}
                            placeholderTextColor={'#c7c7c7'}
                            underlineColorAndroid={'transparent'}
                        />
                    </View>
                </View>
                <TouchableOpacity  onPress={this.QuitApplySubmission}>
                    <View style={{flexDirection: 'row',marginTop:80}}>
                        <Text style={{flex:0.5}}></Text>
                        <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',
                            backgroundColor:((this.state.title!=='')&&(this.state.description!==''))?'#FFDD00':'#EBEBEB'
                            ,color:((this.state.title!=='')&&(this.state.description!==''))?'#000000':'#858585'}}
                        >提交</Text>
                        <Text style={{flex:0.5}}></Text>
                    </View>
                </TouchableOpacity>
            </ScrollView>
        );
    }

}

