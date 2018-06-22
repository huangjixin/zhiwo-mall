import React from 'react';
import {Alert,StyleSheet, ScrollView,Text,TextInput,
    View,Image,Button,ImageBackground,TouchableWithoutFeedback,
    DeviceEventEmitter,TouchableHighlight} from 'react-native';
import {ApplyCommonHeader} from "../ApplyCommonHeader";
import Textarea from 'react-native-textarea';

export class JobProof extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isPreview:false,
            id:'',
            description:'',
            type:8,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它证明
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

            let obj= {};
            obj.type = this.state.type;
            obj.description = this.state.description;

            // 做网络代码提交，此处暂时先做alert处理。
            alert(JSON.stringify(obj));
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
                        <Text style={{flex:1,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',backgroundColor:this.validate==true?'#FFDD00':'#EBEBEB'
                                    ,color:this.validate==true?'#000000':'#858585'}}>预览</Text>
                        {/* <Text style={{color:'#E87722',fontSize:16}}>预览</Text> */}
                    </View>
                </TouchableWithoutFeedback>
            </View>
        )
    }

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
        const {isPreview,description} = this.state;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                    {/*header*/}
                    <ApplyCommonHeader title={'工作证明'} onReturn={() => this.props.navigation.goBack()}/>

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
