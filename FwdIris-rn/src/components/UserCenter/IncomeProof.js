
import React from "react";
import {View, Text, TouchableWithoutFeedback, Image, StyleSheet,TextInput,ScrollView} from "react-native";
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";
import {FwdLoading} from "./FwdLoading";

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
            income:1250,
            type:'7',  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
        }
    }

    _submitForm = ()=>{
        const {agentCode,type,purpose} = this.state;

        if(purpose==null || ''==purpose){
            alert('请填写用途');
            return
        }

        const params = {
            agentCode:agentCode,
            type:type,
            description:purpose,
        }
        this.setState({isLoading:true});
        FetchUtils.Post({
            url:RequestURL.SUBMIT_SINGLE_FORM,
            params:params,
            headers:{},
            success:(respData)=>{
                if(respData.code==1)
                    this.props.navigation.pop(2);
                else
                    alert('提交失败');
            },
            error:()=>{
                this.setState({isLoading:false});
            }
        })
    }

    _toPreview = ()=>{
        const {purpose} = this.state;
        if(purpose==null || ''==purpose){
            alert('请填写用途');
            return
        }

        this.setState({ isPreview: true});
    }

    _renderPreview = ()=>{
        const {agentName,idNo,postTypeName,income,period} = this.state;

        let curDate = new Date();
        const curYear = curDate.getFullYear();
        const curMonth = curDate.getMonth()+1;
        const curDay = curDate.getDate();

        let endDate = new Date();
        endDate.setDate(0);
        const endYear = endDate.getFullYear();
        const endMonth = endDate.getMonth()+1;
        const endDay = endDate.getDate();

        let startDate = new Date();
        const periodMonth = (period+1)*3;
        startDate.setMonth(curMonth-1-periodMonth);
        startDate.setDate(1);
        const startYear = startDate.getFullYear();
        const startMonth = startDate.getMonth()+1;
        const startDay = startDate.getDate();

        return (
        <ScrollView>
            <TouchableWithoutFeedback onPress={()=>{this.setState({ isPreview: false});}}>
                <View style={{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                    ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1,}}>
                    <Text style={{color:'#E87722',fontSize:15}}>关闭预览</Text>
                </View>
            </TouchableWithoutFeedback>
            <View style={{backgroundColor:'#FBFBFB',paddingRight:15,paddingLeft:15}}>
                <View style={{justifyContent: 'center',alignItems: 'center'
                    ,paddingBottom:10,paddingTop:10}}>
                    <Text style={{fontSize:16,color:'#4A4A4A'}}>收入证明</Text>
                </View>
                <View style={{}}>
                    <Text style={styles.fontStyles}>
                        兹证明<Text style={styles.redFont}>{agentName}</Text>（<Text style={styles.redFont}>身份证/护照/军官证</Text>号：{idNo}）为本公司代理制营销员，
                        入司时间为<Text style={styles.redFont}>2016</Text>年<Text style={styles.redFont}>5</Text>月<Text style={styles.redFont}>6</Text>日
                        ，目前在我司营销员渠道的级别是<Text style={styles.redFont}>{postTypeName}</Text>。
                        {'\n'}
                        在<Text style={styles.redFont}>{startYear}</Text>年<Text style={styles.redFont}>{startMonth}</Text>月<Text style={styles.redFont}>{startDay}</Text>日至{endYear}年{endMonth}月{endDay}日期间，其税前收入合计为
                        <Text style={styles.redFont}>{income}</Text>元人民币。
                        {'\n'}
                        本公司承诺以上情况正确属实，特此证明。
                    </Text>
                </View>
                <View style={{flexDirection:'column',justifyContent: 'center',alignItems: 'flex-start'
                    ,marginTop:20}}>
                    <Text style={styles.fontStyles}>联系人：王五</Text>
                    <Text style={styles.fontStyles}>联系电话：021-12345678</Text>
                </View>
                <View style={{flexDirection:'column',justifyContent: 'center',alignItems: 'flex-end'
                    ,marginTop:20}}>
                    <Text style={{color:'#4A4A4A',fontSize:12}}>富卫人寿保险有限公司</Text>
                    <Text style={{color:'#4A4A4A',fontSize:12,paddingTop:5}}>{curYear}年{curMonth}月{curDay}号</Text>
                </View>

                <TouchableWithoutFeedback onPress={this._submitForm}>
                    <View style={{height:46,backgroundColor:'#FFDD00',borderRadius:5,marginTop:20
                        ,justifyContent: 'center',alignItems: 'center',}}>
                        <Text style={{fontSize:14,color:'#000000'}}>确认提交</Text>
                    </View>
                </TouchableWithoutFeedback>
            </View>
        </ScrollView>
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