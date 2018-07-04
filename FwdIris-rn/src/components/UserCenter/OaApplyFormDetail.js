import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter,Button,TouchableOpacity,FlatList,Picker } from 'react-native';

import * as RequestURL from "../../common/RequestURL";
import PromotionList from '../achievement/Promotion.json';
import {JobProof} from "./apply/JobProof";
import {OtherProof} from "./apply/OtherProof";
import {ApplyCommonHeader} from "./ApplyCommonHeader";
//请假UI
class LeaveUI extends React.Component {
    constructor(props){
        super(props);
        // props.username = '';
    }
// <LeaveUI description={this.state.description} startTime={this.state.startTime} endTime={this.state.endTime}
// leaveOff={this.state.leaveOff} leaveType={this.state.leaveType} // files={this.state.files}
    //时间转换(具体到上下午)
    timeTwistsIntoAmToPm=(timeTwists)=>{
        let standardTime = new Date(timeTwists);
        let year = standardTime.getFullYear();
        let month = standardTime.getMonth()+1;
        let date = standardTime.getDate();
        let hours = standardTime.getHours();
        let AmToPm='';
        if(0<=hours<12){
            AmToPm='上午';
        }else {
            AmToPm='下午';
        }

        return year+'/'+month+'/'+date+' '+AmToPm;
    }
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:12,paddingRight:12,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>
                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>请假类型</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.leaveType==0?'病假':
                            (this.props.leaveType==1?'婚假':
                                (this.props.leaveType==2?'产假':
                                    (this.props.leaveType==3?'陪产假':'丧假')))}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>开始时间</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.timeTwistsIntoAmToPm(this.props.startTime)}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>结束时间</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.timeTwistsIntoAmToPm(this.props.endTime)}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>时长</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.leaveOff} 天</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>详细说明</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.description}</Text>
                    </View>
                    <View style={{flexDirection: 'row',paddingTop:10,}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>图片</Text>
                        <View style={{flexWrap:'wrap',flex:8,paddingLeft:20,flexDirection: 'row',alignItems:'center',}}>
                            {/*//<Text>{this.props.files}</Text>*/}
                            {this.props.files.map((rowData, i) => (
                                <View key={i} style={{width: imageWidth,height: 70,flexDirection: 'row',marginRight: 15,marginBottom: 10,justifyContent: 'center',}}>
                                    <Image source={{uri: rowData}} style={{width: 80, height: 80,margin:5}} />
                                </View>
                                ))
                            }
                            {/*<Image style={{width: 80, height: 80,margin:5,}} source={require('../../../img/UserCenter/UserImage.jpeg')}/>*/}
                            {/*<Image style={{width: 80, height: 80,margin:5,}} source={require('../../../img/UserCenter/UserImage.jpeg')}/>*/}
                        </View>
                    </View>
                </View>
            </View>
        );
    }
}

//离职的UI
class ResignUI extends React.Component {
    constructor(props){
        super(props);
        // props.username = '';
    }
    render() {
        // let title = this.props.title;
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>
                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>离职原因</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.title}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-start'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>详细说明</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.description}</Text>
                    </View>
                </View>
            </View>
        );
    }
}

//晋级的UI
class PromotionUI extends React.Component {
    constructor(props){
        var agentGradeArr = PromotionList;
        super(props);
        this.state={
            currentGradeEn:props.currentGrade===null?'':this.props.currentGrade,
            currentGradeCn:props.currentGrade===null?'':agentGradeArr[this.props.currentGrade]["currentName"],
            upGradeEn:props.upGrade===null?'':this.props.upGrade,
            upGradeCn:props.upGrade===null?'':agentGradeArr[this.props.upGrade]["currentName"],
        }
        // props.username = '';
    }
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetailPromotion}>
                    <View style={{flex:1.5}}></View>
                    <View style={{flex:2.5,alignItems:'center',}}>
                        <Text style={{fontSize:28,color:'#252525'}}>{this.state.currentGradeEn}</Text>
                        <Text style={{fontSize:15,color:'#727272',marginTop:10,}}>{this.state.currentGradeCn}</Text>
                    </View>
                    <View style={{flex:2,alignItems:'center',justifyContent:'center',}}>
                        <Image style={{width:39,height:21,}} source={require('../../../img/achievement/promDirec.png')}/>
                    </View>
                    <View style={{flex:2.5,alignItems:'center',}}>
                        <Text style={{fontSize:28,color:'#A4A4A4'}}>{this.state.upGradeEn}</Text>
                        <Text style={{fontSize:15,color:'#B4B4B4',marginTop:10,}}>{this.state.upGradeCn}</Text>
                    </View>
                    <View style={{flex:1.5}}></View>
                    {/*<View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>当前职级</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>LA</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>申请职级</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>SM</Text>
                    </View>*/}
                </View>
            </View>
        );
    }
}
//复效的UI
class RevivalUI extends React.Component {
    constructor(props){
        super(props);
        // props.username = '';
    }
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>
                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>复效人员</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.name}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-start'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>详细说明</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.description}</Text>
                    </View>
                </View>
            </View>
        );
    }
}
//收入证明的UI
class ProofUI extends React.Component {
    constructor(props){
        super(props);
        // props.username = '';
    }
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>
                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>时间</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.imcomeproveMonth==0?(3):(this.props.imcomeproveMonth==1?(6):(this.props.imcomeproveMonth==2?(9):(12)))}个月</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-start'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>用途</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.description}</Text>
                    </View>
                </View>
            </View>
        );
    }
}

//在职证明的UI
class JobProofUI extends React.Component {
    constructor(props){
        super(props);
        // props.username = '';
    }
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-start'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>用途</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.description}</Text>
                    </View>
                </View>
            </View>
        );
    }
}
//其他证明的UI
class OtherProofUI extends React.Component {
    constructor(props){
        super(props);
        // props.username = '';
    }
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>
                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>材料类型</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.title}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-start'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>用途</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>{this.props.description}</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>图片</Text>
                        <View style={{flexWrap:'wrap',flex:8,paddingLeft:20,flexDirection: 'row',alignItems:'center',}}>
                            {/*//<Text>{this.props.files}</Text>*/}
                            <Image style={{width: 80, height: 80,margin:5,}} source={require('../../../img/UserCenter/UserImage.jpeg')}/>
                            <Image style={{width: 80, height: 80,margin:5,}} source={require('../../../img/UserCenter/UserImage.jpeg')}/>
                        </View>
                    </View>


                </View>
            </View>
        );
    }
}

const withoutApproval = require('../../../img/UserCenter/withoutApproval.png');//不通过
const defaultStatus = require('../../../img/UserCenter/defaultStatus.png')//默认--》提交申请
const inApproval = require('../../../img/UserCenter/inApproval.png')//审批中
const passApproval = require('../../../img/UserCenter/passApproval.png')//审批通过
export class OaApplyFormDetail extends React.Component {

    constructor(props){
        super(props);
        let { params } = this.props.navigation.state;
        let id =0;
        let type = 0;
        let status = '0';
        let title='';
        let description='';
        let baseStatus = '';
        let currentGrade = '';
        let upGrade = '';
        let name = '';
        let agentCode = '';
        let createDatetime = '';
        let startTime = '';
        let endTime = '';
        let endDatetime = '';
        let leaveType = '';
        let leaveOff = '';
        let agentName = '';
        let imcomeproveMonth = '';
        if(params != undefined){
            if(params.hasOwnProperty('value')){
                let objData = params['value'];
                if(objData.hasOwnProperty('itemData')){
                    let obj = objData['itemData'];
                    if(obj.hasOwnProperty('id')){
                        id = params.value.itemData.id;
                    }
                    if(obj.hasOwnProperty('type')){
                        type = params.value.itemData.type;
                    }
                    if(obj.hasOwnProperty('status')){
                        status = params.value.itemData.status;
                    }
                    if(obj.hasOwnProperty('title')){
                        title = params.value.itemData.title;
                    }
                    if(obj.hasOwnProperty('description')){
                        description = params.value.itemData.description;
                    }
                    if(obj.hasOwnProperty('currentGrade')){
                        currentGrade = params.value.itemData.currentGrade;
                    }
                    if(obj.hasOwnProperty('upGrade')){
                        upGrade = params.value.itemData.upGrade;
                    }
                    if(obj.hasOwnProperty('name')){
                        name = params.value.itemData.name;
                    }
                    if(obj.hasOwnProperty('agentCode')){
                        agentCode = params.value.itemData.agentCode;
                    }
                    if(obj.hasOwnProperty('createDatetime')){
                        createDatetime = params.value.itemData.createDatetime;
                    }
                    if(obj.hasOwnProperty('startTime')){
                        startTime = params.value.itemData.startTime;
                    }
                    if(obj.hasOwnProperty('endTime')){
                        endTime = params.value.itemData.endTime;
                    }
                    if(obj.hasOwnProperty('endDatetime')){
                        endDatetime = params.value.itemData.endDatetime;
                    }
                    if(obj.hasOwnProperty('leaveType')){
                        leaveType = params.value.itemData.leaveType;
                    }
                    if(obj.hasOwnProperty('leaveOff')){
                        leaveOff = params.value.itemData.leaveOff;
                    }
                    if(obj.hasOwnProperty('agentName')){
                        agentName = params.value.itemData.agentName;
                    }
                    if(obj.hasOwnProperty('imcomeproveMonth')){
                        imcomeproveMonth = params.value.itemData.imcomeproveMonth;
                    }

                }
                if(objData.hasOwnProperty('baseStatus')){
                    baseStatus=params.value.baseStatus;
                }
            }
        }

        this.state = {
            id:id,
            description:description,
            type:type,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            //type:2,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode:agentCode,
            createDatetime:createDatetime,
            status:status, //0 表示正在审批当中了；1表示结束；2驳回 3待签约。
            //status:3, //0 表示正在审批当中了；1表示结束；2驳回 3待签约。
            startTime:startTime,
            endTime:endTime,
            endDatetime:endDatetime,
            leaveType:leaveType,
            leaveOff:leaveOff,
            title:title,
            imcomeproveMonth:imcomeproveMonth,
            currentGrade:currentGrade,
            upGrade:upGrade,
            name:name,
            agentName:agentName,
            showAgreedAndDismiss:baseStatus,
            showNatationAndAgreed:0,
            processList:[],
            files:[],
        }
        //{this.state.type==2&&this.state.status=='3'?(
        if (this.state.type==1||this.state.type==9||this.state.type==10){this.fetchImageData();}
    }
    componentWillMount() {
        this.fetchData();
    }

    // 网络请求
    fetchImageData=()=> {
        let url = RequestURL.HOST+'attachment/getImage/'+this.state.id;
        fetch(url, {
        })
            .then((response) => response.json())
            .then((responseData) => {
                    if(responseData.code=='1'){
                    let data = responseData.data;
                    this.setState( {
                        files:data
                    })
                }else{
                    console.log(responseData.msg);
                    //alert('网络异常，请稍后再试');
                }
            }).catch((err) => {//2
            console.error(err);
            //alert('网络异常，请稍后再试');
        }).done();
    }


    // 网络请求
    fetchData() {
        let url = RequestURL.HOST+'applyForm/'+this.state.id+'/approvalRecord';
        fetch(url, {
        })
            .then((response) => response.json())
            .then((responseData) => {
                if(responseData.code=='1'){
                    let data = responseData.data;
                    this.setState( {
                        processList:data
                    })
                }else{
                    console.log(responseData.msg);
                    //alert('网络异常，请稍后再试');
                }
            }).catch((err) => {//2
            console.error(err);
            //alert('网络异常，请稍后再试');
        }).done();
    }

    //时间转换
    timeTwistsIntoDate=(timeTwists)=>{
        let standardTime = new Date(timeTwists);
        let year = standardTime.getFullYear();
        let month = standardTime.getMonth()+1;
        let date = standardTime.getDate();
        let hours = standardTime.getHours();
        let minutes = standardTime.getMinutes();

        let currentTime = new Date();

        let backDate='';
        let backTime='';
        if (timeTwists) {
            if (year == currentTime.getFullYear() && (month == currentTime.getMonth() + 1) && date == currentTime.getDate()) {
                backDate = '今天';
            } else if (year == currentTime.getFullYear() && (month == currentTime.getMonth() + 1) && (date == currentTime.getDate() - 1)) {
                backDate = '昨天';
            } else {
                let lessMonth = '';
                let lessDate = '';
                if (month < 10){lessMonth = '0' + month;}
                if (date < 10){lessDate = '0' + date;}
                backDate = lessMonth + '-' + lessDate;
            }
            let lessHours = '';
            let lessMinutes = '';
            if(hours<10){lessHours='0'+hours;}else{lessHours=hours;}
            if(minutes<10){lessMinutes='0'+minutes;}else{lessMinutes=minutes;}
            backTime = lessHours + ':' + lessMinutes;
        }else {
            backDate = '今天';
            let lessHours = '';
            let lessMinutes = '';
            if(currentTime.getHours()<10){lessHours='0'+currentTime.getHours();}else{lessHours=currentTime.getHours();}
            if(currentTime.getMinutes()<10){lessMinutes='0'+currentTime.getMinutes();}else{lessMinutes=currentTime.getMinutes();}
            backTime = lessHours + ':' + lessMinutes;
        }

        return {backDate,backTime};
    }
    //时间转换
    stateTransferDict=(state)=>{
        var titleText;
        var titleImage;
        switch(state){
            case '0':titleText='进行中';titleImage= inApproval;break;
            case '1':titleText='通过';titleImage= passApproval;break;
            case '2':titleText='不通过';titleImage= withoutApproval;break;
            case '3':titleText='其他';titleImage= defaultStatus;break;
            default:
        }
        return {titleImage,titleText};
    }
    agreedClick=()=>{
        this.setState( {
            showAgreedAndDismiss:0,
            showNatationAndAgreed:1,
        })
    }
    dismissClick=()=>{
        //alert('驳回');
        this.setState( {
            showAgreedAndDismiss:0,
            showNatationAndAgreed:1
        })
    }
    natationAgreedClick=()=>{
        alert('审批同意');
        this.setState( {
            showAgreedAndDismiss:0,
            showNatationAndAgreed:0,
        })
    }
    administrativeToSignature=()=>{
        //签名页面
        DeviceEventEmitter.emit('PromotionNoticePage'); //晋级详情-》晋级通知书
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#F9F9F9',}}>

                <ApplyCommonHeader title={this.state.type==0?'离职详情':
                    (this.state.type==1?'请假详情':
                        (this.state.type==2?'晋升详情':
                            (this.state.type==3?'复效详情':
                                (this.state.type==7?'收入证明详情':
                                    (this.state.type==8?'工作证明详情':'其它证明详情')))))}
                                   onReturn={() => this.props.navigation.goBack()}/>

                {/*<View style={{backgroundColor:'#FFDD00',height:50,flexDirection: 'row',justifyContent:'flex-start',alignItems:'center',}}>*/}
                    {/*<View style={{flex:4,paddingLeft:10,}}>*/}
                        {/*<TouchableWithoutFeedback style={{width:30,height:30,}}  onPress={() => this.props.navigation.goBack()}>*/}
                            {/*<Image style={{width:11,height:22,}} source={require('../../../img/UserCenter/GoBackBlack.png')}/>*/}
                        {/*</TouchableWithoutFeedback>*/}
                    {/*</View>*/}
                    {/*<Text style={{flex:6,fontSize:22,color:'#000000'}}>*/}
                        {/*{this.state.type==0?'离职详情':*/}
                            {/*(this.state.type==1?'请假详情':*/}
                                {/*(this.state.type==2?'晋升详情':*/}
                                    {/*(this.state.type==3?'复效详情':*/}
                                        {/*(this.state.type==7?'收入证明详情':*/}
                                            {/*(this.state.type==8?'工作证明详情':'其它证明详情')))))}*/}
                    {/*</Text>*/}
                {/*</View>*/}
                <View style={{backgroundColor:'#FFDD00',flexDirection: 'row',alignItems:'center',paddingBottom:20,paddingTop:20}}>
                    <View>
                        <Image style={{marginLeft:20,width: 80, height: 80,borderRadius:40,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                    </View>
                    <View style={{marginLeft:15}}>
                        <Text style={{fontSize:18,color:'#000000'}} >蒋欣桐</Text>
                        <Text style={{fontSize:18,color:'#000000'}}>SM(业务经理)</Text>
                    </View>
                    <View style={{flex:1,paddingRight:20, flexDirection: 'row',justifyContent: 'flex-end'}}>
                        <Text style={{fontSize:14,color:'#000000',backgroundColor:'#FFDD00',
                            borderRadius:20,textAlign:'center',fontSize:18,borderColor:'#D7BA00',borderWidth:1,
                            paddingTop:5,paddingBottom:5,paddingLeft:15,paddingRight:15}}>
                            {this.state.status=='0'?'审批中':(this.state.status=='1'?'通过':(this.state.status=='2'?'驳回':'待签约'))}</Text>
                    </View>
                </View>

                {//离职的UI
                    (this.state.type==0) && (
                        <ResignUI title={this.state.title} description={this.state.description}></ResignUI>
                    )
                }

                {//请假UI
                    (this.state.type==1) && (
                        <LeaveUI description={this.state.description} startTime={this.state.startTime} endTime={this.state.endTime}
                                 leaveOff={this.state.leaveOff} leaveType={this.state.leaveType} files={this.state.files}
                        ></LeaveUI>
                    )
                }

                {//晋级的UI
                    (this.state.type==2) && (
                        <PromotionUI currentGrade={this.state.currentGrade} upGrade={this.state.upGrade}></PromotionUI>
                    )
                }
                {//复效的UI
                    (this.state.type==3) && (
                        <RevivalUI description={this.state.description} name={this.state.name}></RevivalUI>
                    )
                }
                {//收入证明的UI
                    (this.state.type==7) && (
                        <ProofUI description={this.state.description} imcomeproveMonth={this.state.imcomeproveMonth}></ProofUI>
                    )
                }
                {//在职证明的UI
                    (this.state.type==8) && (
                        <JobProofUI description={this.state.description}></JobProofUI>
                    )
                }
                {//其他证明的UI
                    (this.state.type==9) && (
                        <OtherProofUI description={this.state.description} title={this.state.title}  files={this.state.files}></OtherProofUI>
                    )
                }


                {this.state.processList.length>0?
                <View style={styles.process}>
                    <FlatList
                        style={{}}
                        initialNumToRender ={10}
                        data={this.state.processList.reverse()}

                        renderItem={({item,index}) =>
                            <View style={{flexDirection: 'row',alignItems:'center',}}>
                                <View style={{flex:1.2,alignItems:'flex-end',}}>
                                    <Text style={{color:'#9E9E9E',}}>{this.timeTwistsIntoDate(item.handleDate).backTime}</Text>
                                    <Text style={{color:'#9E9E9E',}}>{this.timeTwistsIntoDate(item.handleDate).backDate}</Text>
                                </View>

                                {index+1==this.state.processList.length?
                                    <View style={{flex:8.8,flexDirection: 'row',alignItems:'center',}}>
                                        <View style={{flex:0.9,alignItems:'center',}}>
                                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                                            <Image style={{marginLeft:0,width: 20, height: 20,borderRadius:25,}} source={require('../../../img/UserCenter/defaultStatus.png')} />
                                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                                        </View>
                                        <View style={{flex:9.1,}}>
                                            <Text style={{}}>提交申请</Text>
                                        </View>
                                    </View>
                                    :
                                    <View style={{flex:8.8,flexDirection: 'row',alignItems:'center',}}>

                                        <View style={{flex:1.1,alignItems:'center',}}>
                                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                                            <Image style={{marginLeft:0,width: 20, height: 20,borderRadius:25,}} source={this.stateTransferDict(item.state).titleImage} />
                                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                                        </View>

                                        <View style={{flex:2,}}>
                                            <Image style={{width:40, height:40,borderRadius:20,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                                        </View>
                                        <View style={{flex:9.2,}}>
                                            <View style={{flexDirection: 'row',alignItems:'center',}}>
                                                <Text style={{flex:2}}>节点{index+1}</Text>
                                                <Text style={{flex:3}}>{item.userId}</Text>
                                                <Text style={{flex:5,color:item.state=='0'?'#EB8B43':item.state=='1'?'#4AA54A':item.state=='2'?'#D1071F':''}}>{this.stateTransferDict(item.state).titleText}</Text>
                                            </View>
                                            <Text style={{color:'#B1B1B1',textAlign:'left'}}>{item.comment}</Text>
                                        </View>
                                    </View>
                                }





                            </View>
                        }
                    />

                    {/* <View style={{flexDirection: 'row',alignItems:'center',}}>
                        <View style={{flex:1.2,alignItems:'flex-end',}}>
                            <Text style={{color:'#9E9E9E',}}>08:00</Text>
                            <Text style={{color:'#9E9E9E',}}>早上</Text>
                        </View>
                        <View style={{flex:1,alignItems:'center',}}>
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                            <Image style={{marginLeft:0,width: 20, height: 20,borderRadius:25,}} source={require('../../../img/UserCenter/withoutApproval.png')} />
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                        </View>
                        <View style={{flex:1.5,}}>
                            <Image style={{width:40, height:40,borderRadius:20,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                        </View>
                        <View style={{flex:6.8}}>
                            <View style={{flexDirection: 'row',}}>
                                <Text style={{flex:2}}>节点1</Text>
                                <Text style={{flex:2}}>陈俊</Text>
                                <Text style={{flex:6,color:'#D1071F'}}>不通过</Text>
                            </View>
                            <Text style={{color:'#B1B1B1',}}>有事情需要帮忙的，就打电话给我</Text>
                        </View>
                    </View>

                    <View style={{flexDirection: 'row',alignItems:'center',}}>
                        <View style={{flex:1.2,alignItems:'flex-end',}}>
                            <Text style={{color:'#9E9E9E',}}>08:00</Text>
                            <Text style={{color:'#9E9E9E',}}>早上</Text>
                        </View>
                        <View style={{flex:1,alignItems:'center',}}>
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                            <Image style={{marginLeft:0,width: 20, height: 20,borderRadius:25,}} source={require('../../../img/UserCenter/inApproval.png')} />
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                        </View>
                        <View style={{flex:1.5,}}>
                            <Image style={{width:40, height:40,borderRadius:20,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                        </View>
                        <View style={{flex:6.8}}>
                            <View style={{flexDirection: 'row',}}>
                                <Text style={{flex:2}}>节点2</Text>
                                <Text style={{flex:2}}>陈俊</Text>
                                <Text style={{flex:6,color:'#EB8B43'}}>审批中</Text>
                            </View>
                            <Text style={{color:'#B1B1B1',}}>有事情需要帮忙的，就打电话给我</Text>
                        </View>
                    </View>

                    <View style={{flexDirection: 'row',alignItems:'center',}}>
                        <View style={{flex:1.2,alignItems:'flex-end',}}>
                            <Text style={{color:'#9E9E9E',}}>16:20</Text>
                            <Text style={{color:'#9E9E9E',}}>昨天</Text>
                        </View>
                        <View style={{flex:1,alignItems:'center',}}>
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                            <Image style={{marginLeft:0,width: 20, height: 20,borderRadius:25,}} source={require('../../../img/UserCenter/passApproval.png')} />
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                        </View>
                        <View style={{flex:1.5,}}>
                            <Image style={{width:40, height:40,borderRadius:20,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                        </View>
                        <View style={{flex:6.8}}>
                            <View style={{flexDirection: 'row',}}>
                                <Text style={{flex:2}}>节点3</Text>
                                <Text style={{flex:2}}>卫兰</Text>
                                <Text style={{flex:6,color:'#4AA54A'}}>通过</Text>
                            </View>
                            <Text style={{color:'#B1B1B1',}}>有事情需要帮忙的，就打电话给我</Text>
                        </View>
                    </View>

                    <View style={{flexDirection: 'row',alignItems:'center',}}>
                        <View style={{flex:1.2,alignItems:'flex-end',}}>
                            <Text style={{color:'#9E9E9E',}}>08:00</Text>
                            <Text style={{color:'#9E9E9E',}}>05-22</Text>
                        </View>
                        <View style={{flex:1,alignItems:'center',}}>
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                            <Image style={{marginLeft:0,width: 20, height: 20,borderRadius:25,}} source={require('../../../img/UserCenter/defaultStatus.png')} />
                            <View style={{marginLeft:0,backgroundColor:'#EAEAEA',height:30,width:1,alignItems:'center',}}></View>
                        </View>
                        <View style={{flex:7.8,}}>
                            <Text style={{}}>提交申请</Text>
                        </View>
                    </View>*/}
                </View>:<View></View>}
                {this.state.showNatationAndAgreed==1?
                    <View style={{ paddingTop: 20}}>
                        <TextInput
                            underlineColorAndroid='transparent'
                            multiline={true}
                            placeholder="说点什么..."
                            style={{
                                marginLeft: 15,
                                marginRight:15,
                                height: 100,
                                borderColor: 'gray',
                                borderWidth: 1,
                                flex: 1,
                                backgroundColor: "#FFFFFF"
                            }}></TextInput>
                        <View style={{flexDirection: 'row',marginTop:10,}}>
                            <Text style={{flex:2.5}}></Text>
                            <TouchableOpacity style={{flex:5,}} onPress={this.natationAgreedClick.bind(this)}>
                                <Text style={{fontSize:20,lineHeight:40,borderRadius:5,textAlign:'center',
                                    fontWeight:'900',backgroundColor:'#FFDD00',color:'#282300'}}
                                >同意</Text>
                            </TouchableOpacity>
                            <Text style={{flex:2.5}}></Text>
                        </View>
                    </View>
                    :<View></View>
                }

                {this.state.showAgreedAndDismiss==1
                    ? (
                        <View style={{flexDirection: 'row',backgroundColor:'white',lineHeight:60,alignItems:'center',}}>
                            <TouchableOpacity style={{flex:5,}} onPress={this.dismissClick.bind(this)}>
                                <Text style={{textAlign:'center',color:'#FFF7F7',fontSize:18,lineHeight:60,backgroundColor:'#FF5353',}}>不通过</Text>
                            </TouchableOpacity>
                            <TouchableOpacity style={{flex:5,}} onPress={this.agreedClick.bind(this)}>
                                <Text style={{textAlign:'center',color:'#282300',fontSize:18,lineHeight:60,backgroundColor:'#FFDD00',}}>通过</Text>
                            </TouchableOpacity>
                        </View>
                    )
                    :
                    <View></View>
                }
                {this.state.type==2&&this.state.status=='3'?(
                    <View style={{marginRight:10,marginLeft:10,marginTop:-50,backgroundColor:'#ffffff',borderRadius:10,}}>
                        <TouchableWithoutFeedback onPress={this.administrativeToSignature}>
                            <Text style={{lineHeight:40,color:'#EE9E61',textAlign:'center'}}>签署《晋级通知书》</Text>
                        </TouchableWithoutFeedback>
                    </View>
                ):(<View></View>)}







            </ScrollView>
        );
    }

}


const styles = StyleSheet.create({
    applyFormDetailPromotion: {
        backgroundColor:'white',borderRadius:20,flexDirection: 'row',justifyContent:'center',
        paddingLeft:0,paddingRight:10,paddingBottom:40,paddingTop:30,
        shadowColor:'black',shadowOpacity: 0.1,shadowRadius: StyleSheet.hairlineWidth,
        shadowOffset: {
            height: StyleSheet.hairlineWidth,
        },
        elevation: 4
    },
    applyFormDetail: {
        backgroundColor:'white',borderRadius:20,
        paddingLeft:0,paddingRight:10,paddingBottom:12,paddingTop:10,
        shadowColor:'black',shadowOpacity: 0.1,shadowRadius: StyleSheet.hairlineWidth,
        shadowOffset: {
            height: StyleSheet.hairlineWidth,
        },
        elevation: 4
    },
    process: {
        backgroundColor:'white',
        marginTop:20,
        borderRadius:20,
        marginLeft:10,
        marginRight:10,
        paddingBottom:20,
        paddingTop:20,
        marginBottom:80,
        paddingLeft:10,
        paddingRight:10,
        shadowColor:'black',shadowOpacity: 0.1,shadowRadius: StyleSheet.hairlineWidth,
        shadowOffset: {
            height: StyleSheet.hairlineWidth,
        },
        elevation: 4
    },
});