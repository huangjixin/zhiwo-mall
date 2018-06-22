
import React from "react";
import {View, Text, TouchableWithoutFeedback, Image, StyleSheet,TextInput} from "react-native";
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import moment from 'moment';


export class OtherProof extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isPreview:false,
            period:0,
            purpose:'',
            id:'',
            description:'',
            type:9,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode:'',
            createDatetime:'',
            status:'', //0 表示正在审批当中了；1表示结束；2驳回。
            startTime:'',
            endTime:'',
            endDatetime:'',
            leaveType:'',
            leaveOff:'',
            title:'台湾还没有回归，心情不好',
            imcomeproveMonth:'',
            currentGrade:'',
            upGrade:''
        }
    }

    // 点击提交
    submitHandler = ()=>{
        let descr = this.state.description;
        // 用途不写予以警告。
        if(descr===''){
            alert('用途没有编写');
        }else if(descr.length > 100){
            alert('字符长度不得大于100');
        }else{

            let obj= {};
            obj.type = this.state.type;
            obj.description = this.state.description;

            // 做网络代码提交，此处暂时先做alert处理。
            alert(JSON.stringify(obj));
        }
    };

    _renderPreview = ()=>{
        return (
        <View>
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
                        兹证明<Text style={styles.redFont}>张三</Text>（<Text style={styles.redFont}>身份证/护照/军官证</Text>号：123456xxxxxxx）为本公司代理制营销员，
                        入司时间为<Text style={styles.redFont}>2019</Text>年<Text style={styles.redFont}>XX</Text>月<Text style={styles.redFont}>XX</Text>日
                        ，目前在我司营销员渠道的级别是<Text style={styles.redFont}>寿险顾问（业务经理/销售经理等）</Text>。
                        {'\n'}
                        在<Text style={styles.redFont}>2019</Text>年<Text style={styles.redFont}>XX</Text>月<Text style={styles.redFont}>XX</Text>日至2019年XX月XX日期间，其税前收入合计为
                        <Text style={styles.redFont}>XXXX</Text>元人民币。
                        {'\n'}
                        本公司承诺以上情况正确属实，特此证明。
                    </Text>
                </View>
                <View style={{flexDirection:'column',justifyContent: 'center',alignItems: 'flex-start'
                    ,marginTop:20}}>
                    <Text style={styles.fontStyles}>联系人：XXX</Text>
                    <Text style={styles.fontStyles}>联系电话：021-12345678</Text>
                </View>
                <View style={{flexDirection:'column',justifyContent: 'center',alignItems: 'flex-end'
                    ,marginTop:20}}>
                    <Text style={{color:'#4A4A4A',fontSize:12}}>富卫人寿保险有限公司</Text>
                    <Text style={{color:'#4A4A4A',fontSize:12,paddingTop:5}}>2018年8月8号</Text>
                </View>

                <View style={{height:46,backgroundColor:'#FFDD00',borderRadius:5,marginTop:20
                    ,justifyContent: 'center',alignItems: 'center',}}>
                    <Text style={{fontSize:14,color:'#000000'}}>确认提交</Text>
                </View>
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
                    <Text style={{color:'#333333',fontSize:15}}>时间</Text>
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
        <View style={{backgroundColor:'#FBFBFB',flexDirection:'column'}}>
            {/*选择时间*/}
            {
                this._periodItem()
            }
            {/*用途*/}
            <View style={{flexDirection:'column',paddingLeft:10,backgroundColor:'#FFFFFF',paddingTop:10
                ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                <View style={{marginBottom:10}}>
                    <Text style={{color:'#333333',fontSize:15}}>用途</Text>
                </View>
                <TouchableWithoutFeedback onPress={()=>{this.refs.refTextInput.focus(); }}>
                    <View style={{marginRight:10,height:100 ,borderColor:'#F1F1F1',borderWidth:1}}>
                        <TextInput
                            ref="refTextInput"
                            underlineColorAndroid='transparent'
                            style={{height:40}}
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
            <TouchableWithoutFeedback onPress={()=>{this.setState({ isPreview: true});}}>
                <View style={{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                    ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1,}}>
                    <Text style={{color:'#E87722',fontSize:16}}>预览</Text>
                </View>
            </TouchableWithoutFeedback>
        </View>
        )
    }

    render() {
        const {isPreview} = this.state;

        return (
            <View style={{backgroundColor:'#D7D7D7'}}>
                {/*header*/}
                <ApplyCommonHeader title={'收入证明'} onReturn={() => this.props.navigation.goBack()}/>

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