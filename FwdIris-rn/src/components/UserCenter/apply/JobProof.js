import React from 'react';
import {Alert,StyleSheet, ScrollView,Text,TextInput,
    View,Image,Button,ImageBackground,TouchableWithoutFeedback,WebView,
    DeviceEventEmitter,TouchableHighlight} from 'react-native';
import {ApplyCommonHeader} from "../ApplyCommonHeader";
import Textarea from 'react-native-textarea';
import * as FetchUtils from "../../../common/FetchUtils";
import * as RequestURL from "../../../common/RequestURL";
import * as DateUtils from "../../../common/DateUtils";
import {FwdLoading} from "../FwdLoading";
import Toast from "../Toast/Toast";
export class JobProof extends React.Component {

    constructor(props) {
        super(props);

        //职级确定
        // let position = global.userInfo.postType;
        let position = 'DD';
        let postTypeName = "";
        if (position != null && position != undefined && position != '') {
            switch(position) {
                case 'DD':
                    postTypeName = '区域总监';
                    break;
                case 'AD':
                    postTypeName = '总监';
                    break;
                case 'SAM':
                    postTypeName = '资深业务经理';
                    break;
                case 'AM':
                    postTypeName = '业务经理';
                    break;
                case 'SD':
                    postTypeName = '销售总监';
                    break;
                case 'SSM':
                    postTypeName = '资深销售经理';
                    break;
                case 'SM':
                    postTypeName = '销售经理';
                    break;
                default:
                    postTypeName = '未知职级';
            }
        }

        this.state = {
            isLoading:false,
            agentName:'张三',
            agentCode:'10000792',
            isPreview:false,
            purpose:'',
            idNo:'12345678910',
            postTypeName:postTypeName,
            type:'8',  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            // enterDate:DateUtils.dateFormat(global.userInfo.createTime,'Y年m月d日'),
            enterDate:DateUtils.dateFormat(new Date().getTime(),'Y年m月d日'),
            proofContent:'',
        }
    }

    // 验证函数。
    validate = ()=>{
        return this.state.purpose!='';
    }

    // 点击提交
    submitHandler = ()=>{
        const {agentCode,type,purpose,proofContent} = this.state;
        const params = {
            agentCode:agentCode,
            type:type,
            title:purpose,
            description:proofContent,
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
                    Toast.show("请求超时",Toast.LONG);
                }else{
                    Toast.show("未知错误",Toast.LONG);
                }
                this.setState({isLoading:false});
            }
        })
    };

    // 点击预览
    previewHandler = ()=>{
        let purpose = this.state.purpose;
        // 用途不写予以警告。
        if(purpose===''){
            Toast.show("请填写用途",Toast.LONG);
        }else if(purpose.length > 100){
            Toast.show("字符长度不得大于100",Toast.LONG);
        }else{
            this.setState({ isPreview: true});
        }
    };

    _renderForm = ()=>{
        const {purpose} = this.state;
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
                                onChangeText={(text) => {this.setState({purpose:text});}}
                                defaultValue={this.state.purpose}
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
        const {isPreview,purpose,isLoading} = this.state;
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

    _getCurDate = () =>{
        let curDate = new Date();
        return {
            curYear : curDate.getFullYear(),
            curMonth : curDate.getMonth()+1,
            curDay : curDate.getDate(),
        }
    }
    _getProofTemplate = ()=>{
        const {agentName,idNo,postTypeName,enterDate} = this.state;
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
