import React from 'react';
import {Alert,StyleSheet, ScrollView,Text,TextInput,
    View,Image,Button,ImageBackground,TouchableWithoutFeedback,
    DeviceEventEmitter,TouchableHighlight} from 'react-native';
import {ApplyCommonHeader} from "../ApplyCommonHeader";
import Textarea from 'react-native-textarea';
import * as FetchUtils from "../../../common/FetchUtils";
import * as RequestURL from "../../../common/RequestURL";
import {FwdLoading} from "../FwdLoading";

export class JobProof extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading:false,
            agentName:'张三',
            agentCode:'10000792',
            isPreview:false,
            description:'',
            idNo:'12345678910',
            postTypeName:'寿险顾问（业务经理/销售经理等）',
            type:'8',  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明

        }
    }

    // 验证函数。
    validate = ()=>{
        return this.state.description!='';
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
            const {agentCode,type,description} = this.state;

            if(description==null || ''==description){
                alert('请填写用途');
                return
            }

            const params = {
                agentCode:agentCode,
                type:type,
                description:description,
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
    };

    // 点击预览
    previewHandler = ()=>{
        let descr = this.state.description;
        // 用途不写予以警告。
        if(descr===''){
            alert('用途没有编写');
        }else if(descr.length > 100){
            alert('字符长度不得大于100');
        }else{
            this.setState({ isPreview: true});
        }
    };

    _renderForm = ()=>{
        const {description} = this.state;
        return (
            <View>
                <View style={{flexDirection:'column',paddingLeft:10,backgroundColor:'#FFFFFF',paddingTop:10
                    ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                    <View style={{marginBottom:10}}>
                        <Text style={{color:'#333333',fontSize:16}}>用途</Text>
                    </View>
                    <View style={styles.container}>
                        <Textarea
                                containerStyle={styles.textareaContainer}
                                style={styles.textarea}
                                onChangeText={(text) => {this.setState({description:text});}}
                                defaultValue={this.state.description}
                                maxLength={100}
                                placeholder={'请输入详细说明'}
                                placeholderTextColor={'#c7c7c7'}
                                underlineColorAndroid={'transparent'}
                            />
                    </View>
                </View>
                <TouchableWithoutFeedback onPress={this.previewHandler}>
                    <View style={{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                        ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1,}}>
                        <Text style={{color:'#E87722',fontSize:16}}>预览</Text>
                    </View>
                </TouchableWithoutFeedback>
            </View>
        )
    }

    _renderPreview = ()=>{
        const {agentName,idNo,postTypeName} = this.state;
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
                        <Text style={{fontSize:16,color:'#4A4A4A'}}>工作证明</Text>
                    </View>
                    <View style={{}}>
                        <Text style={styles.fontStyles}>
                            兹证明<Text style={styles.redFont}>张三</Text>（<Text style={styles.redFont}>身份证/护照/军官证</Text>号：123456xxxxxxx）为本公司代理制营销员，
                            入司时间为<Text style={styles.redFont}>2019</Text>年<Text style={styles.redFont}>XX</Text>月<Text style={styles.redFont}>XX</Text>日
                            ，目前在我司营销员渠道的级别是<Text style={styles.redFont}>寿险顾问（业务经理/销售经理等）</Text>。
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

                    <View style={{}}>
                        <Text style={styles.fontStyles}>
                            此证明仅限用于李四申请赴美国旅游签证之用途
                        </Text>
                    </View>

                    <View style={{justifyContent: 'center',alignItems: 'center'
                        ,paddingBottom:10,paddingTop:10}}>
                        <Text style={{fontSize:16,color:'#4A4A4A'}}>Certificate</Text>
                    </View>
                    <View style={{}}>
                        <Text style={styles.fontStyles}>
                            To Whom It May Concern:
                            {'\n'}
                            This is to certify that Mr./Ms. Zhang San (ID/Passport number is 123456X) joined in XXX（公司英文名，TBD） since Apr 1, 2019. He /She has agency contract relationship with our company and takes the position as Life Advisor.
                        </Text>
                    </View>
                    <TouchableWithoutFeedback  onPress={this.submitHandler}>
                        <View style={{height:46,backgroundColor:'#FFDD00',borderRadius:5,marginTop:20
                            ,marginBottom:20
                            ,justifyContent: 'center',alignItems: 'center',}}>
                            <Text style={{fontSize:14,color:'#000000'}}>确认提交</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
            </View>
        )
    }

    render() {
        const {isPreview,description,isLoading} = this.state;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                    {/*header*/}
                    <ApplyCommonHeader title={'工作证明'} onReturn={() => this.props.navigation.goBack()}/>
                    <FwdLoading isLoading={isLoading}/>
                    {!isPreview?(
                        /*body*/
                        this._renderForm()
                    )
                    :(
                        /*预览*/
                        this._renderPreview()
                    )
                    }
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
    fontStyles:{
        lineHeight:30,
        fontSize:14,
        color:'#4A4A4A'
    },
    redFont:{
        color:'red',
    }

});
