import React from 'react';
import {Modal,Button,TouchableOpacity,StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter,Dimensions} from 'react-native';
import CheckBox from 'react-native-checkbox';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Picker from 'react-native-picker';
import Toast from './Toast/Toast';
import * as RequestURL from "../../common/RequestURL";

var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
const halfHeight = (ScreenHeight/2);

const agentGrade = 'SSM';   //暂时用于显示当前职级

import PromotionList from '../achievement/Promotion.json';
export class PromotionApply extends React.Component {
    constructor(props){
        var agentGradeArr = PromotionList;
        super(props);
        this.state = {
            isSe1:false,
            isModal:false,
            //jobApplication:[],
            //agentGrade:agentGrade,   //当前代理人职级
            //  dataSource:[]
            agentGradeList: [],    //当前代理人晋升渠道数据


            id:'',
            description:'',
            type:2,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode: '10000',
            createDatetime:'',
            status:'', //0 表示正在审批当中了；1表示结束；2驳回。
            startTime:'',
            endTime:'',
            endDatetime:'',
            leaveType:'',
            leaveOff:'',
            title:'',
            imcomeproveMonth:'',
            currentGrade:agentGrade,//当前代理人职级
            upGrade:'',
            name: '',
            agentName: 'JohnnyZ'


        }

        this.fetchUpGrade();
        // this.fetchData = this.fetchData.bind(this);
    }


    // 提交数据到后台
    fetchUpGrade() {
        //  Kane哥，后面这么写，搞一个变量，调试的时候也容易。
        let url = RequestURL.HOST+'applyForm/selectPromotion?agentCode=123';

        fetch(url, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
   　　　　      'Content-Type': 'application/json',
            },
           // body: parmars,
        })
            .then((response) => response.json())
            .then((responseJson) => {
                if(responseJson.length==0){
                    this.setState({
                        upGrade:''
                    });
                }
                else if(responseJson.length==1){
                    this.setState({
                        upGrade:responseJson,
                    });
                }else{
                    this.setState({
                        agentGradeList:responseJson,
                    });
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }








    PromotionNoticePage=()=>{
        if(this.state.isSe1 == true){
            //DeviceEventEmitter.emit('PromotionNoticePage'); //晋级详情页面-》晋级通知书页面


            let currGrade = this.state.currentGrade;
            let uGrade = this.state.upGrade;
            // if (this.state.agentGradeList['child'].length==1){
            //     this.setState({
            //         upGrade:uGrade,
            //     })
            // }
            // 用途不写予以警告。
            if(uGrade===''){
                alert('晋升职级没有选择');
            }else{
                let formData = new FormData();
                formData.append("type",this.state.type);
                formData.append("agentName",this.state.agentName);
                formData.append("currentGrade",this.state.currentGrade);
                formData.append("upGrade",this.state.upGrade);
                formData.append("agentCode",this.state.agentCode);



                // 做网络代码提交，此处暂时先做alert处理。
                this.fetchData(formData);
                //alert(formData);
            }





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
                //alert(parmars);
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
    showModal() {
        this.setState({
            isModal:true
        })
    }

    onRequestClose() {
        this.setState({
            isModal:false
        });
    }


    _applicationData(){
        var data = [];
        var agentList = this.state.agentGradeList['child'];
        if(agentList.length==0 || agentList.length==1 ){
            return ;
        }else{
            for (var i = 0; i < agentList.length; i++) {
                data.push(agentList[i]['childEnName']);
            }
        }
        return data;
    }

    _showApplicationPicker() {
        Picker.init({
            pickerData:this.upGrade,
            pickerFontColor: [0, 0 ,0, 1],
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            pickerTitleText:'请选择晋级渠道',
            onPickerConfirm: (pickedValue, pickedIndex) => {
                this.setState({
                    upGrade:pickedValue[0],
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
                <ApplyCommonHeader title={'晋级详情'} onReturn={() => this.props.navigation.goBack()}/>
                <View  style={{flexDirection: 'row',justifyContent: 'space-between',backgroundColor:'#ffffff',height:40}}>
                    <Text  style={{flex:2,lineHeight:40,height:40,marginLeft:25,color:'#000000',fontSize:16}}>当前职位</Text>
                    <Text  style={{flex:7,lineHeight:40,height:40,textAlign:'right',color:'#000000',fontSize:16}}>{this.state.currentGrade}</Text>
                    <Text  style={{flex:1}}></Text>
                </View>

                {/*//当前职位是AD 总监(没有晋升渠道)的时候不显示晋升渠道*/}
                {this.state.agentGradeList['child'].length==0?(null):(
                    <View>
                        <View style={{flexDirection: 'row',height:40,backgroundColor:'white',alignItems:'center',}}>
                            <Text  style={{flex:2,lineHeight:40,height:40,marginLeft:25,color:'#000000',fontSize:16}}>申请职级</Text>
                            <View style={{flex:8,flexDirection:'row',paddingRight:10,}}>
                                {this.state.agentGradeList['child'].length==1?(
                                    <View style={{flexDirection:'row',}}>
                                        <Text style={{flex:9.5,textAlign:'right',paddingRight:15,fontSize:16,color:'#000000',lineHeight:40,}}>{this.state.agentGradeList['child'][0]['childEnName']}</Text>
                                        <Text style={{flex:0.5,}}></Text>
                                    </View>
                                         ):(
                                    <View style={{flex:8,flexDirection: 'row',}}>
                                        <TouchableOpacity style={{height:40,flex:9.5}} onPress={this._showApplicationPicker.bind(this)}>
                                            {this.state.upGrade===''?(
                                                <Text style={{textAlign:'right',paddingRight:15,fontSize:16,color:'#000000',lineHeight:40,}}>请选择</Text>
                                            ):(
                                                <Text style={{textAlign:'right',paddingRight:15,fontSize:16,color:'#000000',lineHeight:40,}}>{this.state.upGrade}</Text>
                                            )}
                                        </TouchableOpacity>
                                        <View  style={{flex:0.5,alignItems:'center',justifyContent:'center',}}>
                                            <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                                        </View>
                                    </View>
                                )}
                            </View>
                        </View>
                        <View style={{flexDirection: 'row',justifyContent: 'space-between',marginTop:15}}>
                            <View style={{flex:1,marginTop:3,flexDirection: 'row',justifyContent: 'center'}}>
                                <CheckBox
                                    checked={this.state.isSe1}
                                    label = ''
                                    checkboxStyle={{width:20,height:20,backgroundColor:'#FBFBFB',borderStyle: 'solid',borderWidth: 1,borderColor: '#A2A2A2'}}
                                    onChange={(checked) => {
                                    this.setState({
                                        isSe1:!checked
                                    });
                                }}
                                />
                            </View>
                            <Text  style={{color:'#A2A2A2',flex:8}}>
                                本人承诺晋级申请由晋级人员本人亲自填写，并知晓达到晋级业绩标准且经公司审批同意后方可晋级至所申请的级别
                            </Text>

                        </View>
                        <TouchableWithoutFeedback  onPress={this.PromotionNoticePage}>
                            <View style={{flexDirection: 'row',marginTop:80}}>
                                <Text style={{flex:0.5}}></Text>
                                <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',backgroundColor:((this.state.isSe1==true)&&(this.state.upGrade!==''))?'#FFDD00':'#EBEBEB'
                                    ,color:((this.state.isSe1==true)&&(this.state.upGrade!==''))?'#000000':'#858585'}}>提交</Text>
                                <Text style={{flex:0.5}}></Text>
                            </View>
                        </TouchableWithoutFeedback>
                        <Modal
                            animationType='slide'           // 从底部滑入
                            transparent={true}             // 不透明
                            visible={this.state.isModal}    // 根据isModal决定是否显示
                            onRequestClose={() => {this.onRequestClose()}}  // android必须实现
                        >

                            <View style={{flex:1,justifyContent:'flex-end',alignItems:'flex-end',backgroundColor:'rgba(0, 0, 0, 0.3)'}}>
                                <View style={{height:300,width:ScreenWidth,backgroundColor:'white'}}>
                                    <Button title='关闭Modal' onPress={() => {{
                                        this.setState({
                                            isModal:false
                                        })
                                    }}}/>

                                </View>
                                {/* <TouchableOpacity
                                onPress={() => {{
                                    this.setState({
                                        isModal:false
                                    })
                                }}}
                            >
                                <Text>关闭页面</Text>
                            </TouchableOpacity> */}
                            </View>
                        </Modal>

                    </View>
                )}
            </ScrollView>
        );
    }
}

