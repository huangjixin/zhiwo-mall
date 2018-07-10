
import React from "react";
import {
    View, Text, TouchableWithoutFeedback, Image, StyleSheet, TextInput, ScrollView, WebView
} from "react-native";
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";
import {FwdLoading} from "./FwdLoading";
import Toast from "./Toast/Toast";

export class IncomeProof extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading:false,
            agentName:'张三',
            agentCode:'10000792',
            isPreview:false,
            period:0,
            purpose:'',
            idNo:'12345678910',
            postTypeName:'寿险顾问（业务经理/销售经理等）',
            income:0,
            type:'7',  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            enterDate:'2016年10月9日',
            proofContent:''
        }
    }

    _submitForm = ()=>{
        const {agentCode,type,purpose,period,proofContent} = this.state;

        if(purpose==null || ''==purpose){
            Toast.show("请填写用途",Toast.LONG);
            return
        }

        const params = {
            agentCode:agentCode,
            type:type,
            title:purpose,
            description:proofContent,
            imcomeproveMonth:period,
        }
        this.setState({isLoading:true});
        FetchUtils.Post({
            url:RequestURL.SUBMIT_SINGLE_FORM,
            params:params,
            headers:{},
            success:(respData)=>{
                if(respData.code!='1'){
                    this.setState({isLoading:false})
                    Toast.show('请求错误',Toast.LONG);
                    return;
                }

                this.props.navigation.pop(2);
            },
            error:(isTimeOut)=>{
                if(isTimeOut){
                    Toast.show('请求超时',Toast.LONG);
                }else{
                    Toast.show('未知错误',Toast.LONG);
                }
                this.setState({isLoading:false});
            }
        })
    }

    _toPreview = ()=>{
        const {agentCode,purpose,period} = this.state;
        if(purpose==null || ''==purpose){
            Toast.show("请填写用途",Toast.LONG);
            return
        }
        const {startYear,startMonth,startDay} = this._getStartDate();
        const {endYear,endMonth,endDay} = this._getEndDate();
        const startDate = startYear+'-'+startMonth+'-'+startDay;
        const endDate = endYear+'-'+endMonth+'-'+endDay;
        const params = {
            agentCode:agentCode,
            startDate:startDate,
            endDate :endDate,
        }
        this.setState({isLoading:true});
        FetchUtils.Get({
            url:RequestURL.MY_INCOME,
            params:params,
            success:(respData)=>{
                if(respData.code!='1'){
                    this.setState({isLoading:false})
                    Toast.show('请求错误',Toast.LONG);
                    return;
                }
                this.setState({
                    income:respData.data.preTax,
                    isLoading:false,
                    isPreview: true
                });
            },
            error:(isTimeOut)=>{
                if(isTimeOut){
                    Toast.show("请求超时",Toast.LONG);
                }else{
                    Toast.show("请求失败",Toast.LONG);
                }
                this.setState({isLoading:false})
            }
        })
    }

    _renderPreview = ()=>{
        let proofHtml = this._getProofTemplate();
        this.state.proofContent = proofHtml;

        return (
        <View>
            <TouchableWithoutFeedback onPress={()=>{this.setState({ isPreview: false});}}>
                <View style={{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                    ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1,}}>
                    <Text style={{color:'#E87722',fontSize:15}}>关闭预览</Text>
                </View>
            </TouchableWithoutFeedback>

            <View style={{backgroundColor:'#FBFBFB',paddingRight:15,paddingLeft:15}}>
                <View style={{height:380}}>
                    <WebView
                        style={{
                            backgroundColor: '#FBFBFB',
                            height: 100,
                        }}
                        source={{html: proofHtml, baseUrl: '' }}
                        scalesPageToFit={true}
                    />
                </View>

                <TouchableWithoutFeedback onPress={this._submitForm}>
                    <View style={{height:46,backgroundColor:'#FFDD00',borderRadius:5,marginTop:20,marginBottom:20
                        ,justifyContent: 'center',alignItems: 'center',}}>
                        <Text style={{fontSize:14,color:'#000000'}}>确认提交</Text>
                    </View>
                </TouchableWithoutFeedback>
            </View>
        </View>
        )
    }

    _periodItem = ()=>{
        const {period} = this.state;
        return (
            <View style={{flexDirection:'row',borderColor:'#F2F2F2',borderBottomWidth:1,
                backgroundColor:'#FFFFFF',paddingLeft:10,height:50,justifyContent: 'center',alignItems: 'center'}}>
                <View style={{flex:1}}>
                    <Text style={{color:'#333333',fontSize:16}}>时间</Text>
                </View>
                <View style={{flex:6,flexDirection:'row',paddingRight:10}}>
                    <TouchableWithoutFeedback onPress={()=>{this.setState({ period: 0});}}>
                        <View style={[styles.periodSelector,period==0 && {borderColor:'#E87722'}]}>
                            <Text style={[{fontSize:14},period==0 && {color:'#E87722'}]}>3个月</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={()=>{this.setState({ period: 1});}}>
                        <View style={[styles.periodSelector,period==1 && {borderColor:'#E87722'}]}>
                            <Text style={[{fontSize:14},period==1 && {color:'#E87722'}]}>6个月</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={()=>{this.setState({ period: 2});}}>
                        <View style={[styles.periodSelector,period==2 && {borderColor:'#E87722'}]}>
                            <Text style={[{fontSize:14},period==2 && {color:'#E87722'}]}>9个月</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={()=>{this.setState({ period: 3});}}>
                        <View style={[styles.periodSelector,period==3 && {borderColor:'#E87722'}]}>
                            <Text style={[{fontSize:14},period==3 && {color:'#E87722'}]}>12个月</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
            </View>
        )
    }
    _renderForm = ()=>{
        const {purpose} = this.state;
        return (
        <ScrollView style={{backgroundColor:'#FBFBFB',flexDirection:'column'}}>
            {/*选择时间*/}
            {
                this._periodItem()
            }
            {/*用途*/}
            <View style={{flexDirection:'column',paddingLeft:10,backgroundColor:'#FFFFFF',paddingTop:10
                ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                <View style={{marginBottom:10}}>
                    <Text style={{color:'#333333',fontSize:16}}>用途</Text>
                </View>
                <TouchableWithoutFeedback onPress={()=>{this.refs.refTextInput.focus(); }}>
                    <View style={{marginRight:10,height:100 ,borderColor:'#F1F1F1',borderWidth:1}}>
                        <TextInput
                            ref="refTextInput"
                            underlineColorAndroid='transparent'
                            editable = {true}
                            maxLength = {400}
                            multiline = {true}
                            value={purpose}
                            onChangeText={(text) => {this.setState({purpose:text});}}
                        />
                    </View>
                </TouchableWithoutFeedback>
            </View>
            {/**/}
            <TouchableWithoutFeedback onPress={this._toPreview}>
                <View style={{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                    ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1,}}>
                    <Text style={{color:'#E87722',fontSize:16}}>预览</Text>
                </View>
            </TouchableWithoutFeedback>
        </ScrollView>
        )
    }

    render() {
        const {isPreview,isLoading} = this.state;

        return (
            <View style={{backgroundColor:'#D7D7D7'}}>
                {/*header*/}
                <ApplyCommonHeader
                    title={'收入证明'}
                    onReturn={() => this.props.navigation.goBack()}
                />
                <FwdLoading isLoading={isLoading}/>
                {!isPreview?(
                    /*body*/
                    this._renderForm()
                )
                :(
                    /*预览*/
                    this._renderPreview()
                )}
            </View>
        );
    }

    _getStartDate = ()=>{
        const {period} = this.state;
        const curDate = new Date();
        const curMonth = curDate.getMonth()+1;
        const periodMonth = (period+1)*3;

        const startDate = new Date();
        startDate.setMonth(curMonth-1-periodMonth);
        startDate.setDate(1);
        return {
            startYear:startDate.getFullYear(),
            startMonth:startDate.getMonth()+1,
            startDay:startDate.getDate(),
        }
    }

    _getEndDate = () =>{
        let endDate = new Date();
        endDate.setDate(0);
        return {
            endYear : endDate.getFullYear(),
            endMonth : endDate.getMonth()+1,
            endDay : endDate.getDate(),
        }
    }

    _getCurDate = () =>{
        let curDate = new Date();
        return {
            curYear : curDate.getFullYear(),
            curMonth : curDate.getMonth()+1,
            curDay : curDate.getDate(),
        }
    }

    _getProofTemplate = ()=>{
        const {agentName,idNo,postTypeName,income,enterDate} = this.state;
        const {startYear,startMonth,startDay} = this._getStartDate();
        const {endYear,endMonth,endDay} = this._getEndDate();
        const {curYear,curMonth,curDay } = this._getCurDate();

        return `
                <!DOCTYPE html>\n
                <html>
                  <head>
                    <title>Hello Static World</title>
                    <meta http-equiv="content-type" content="text/html; charset=utf-8">
                    <style type="text/css">
                      body {
                        margin: 0;
                        padding: 0;
                        font: 62.5% arial, sans-serif;
                        background: #FBFBFB;
                      }
                      h1 {
                        padding: 20px;
                        margin: 0;
                        text-align: center;
                        color: #4A4A4A;
                        font-size：30px
                      }
                      .bodyCls{
                        font-family:'MicrosoftYaHei';
                        font-size : 16px;
                        padding:0px 10px 15px 10px;
                        color:#4A4A4A;
                        line-height:30px;
                      }
                    </style>
                  </head>
                  <body>
                    <h1>收入证明</h1>
                    <div class="bodyCls">
                         兹证明${agentName}（身份证/护照/军官证号：${idNo}）为本公司代理制营销员，
                         入司时间为${enterDate}，目前在我司营销员渠道的级别是${postTypeName}。<br>
                        在${startYear}年${startMonth}月${startDay}日至${endYear}年${endMonth}月${endDay}日期间，其税前收入合计为${income}元人民币。<br>
                        本公司承诺以上情况正确属实，特此证明。<br><br>
                        
                        联系人：王五 <br>
                        联系电话：021-12345678<br><br>
                        
                        <div align="right">富卫人寿保险有限公司</div>
                        <div align="right">${curYear}年${curMonth}月${curDay}日<br></div>
                    </div>
                  </body>
                </html>
                `;
    }
}

const styles = StyleSheet.create({
    periodSelector :{
        flex:1,borderWidth:1,
        borderRadius:5,
        justifyContent: 'center',
        alignItems: 'center',
        height:23,
        marginLeft:7,
        marginRight:7,
        borderColor:'#E2E2E2',
    },
    fontStyles:{
        lineHeight:30,
        fontSize:14,
        color:'#4A4A4A'
    },
    redFont:{
        color:'red',
    }

});