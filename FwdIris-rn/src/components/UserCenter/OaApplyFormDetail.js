import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter,Button,TouchableOpacity,FlatList,Picker } from 'react-native';

//请假UI
class LeaveUI extends React.Component {
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:12,paddingRight:12,paddingBottom:10,paddingTop:10}}>
                    <View style={styles.applyFormDetail}>
                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>请假类型</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>事假</Text>
                        </View>

                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>开始时间</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>2018-05-18 上午</Text>
                        </View>

                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>结束时间</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>2018-05-18 下午</Text>
                        </View>

                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>时长</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>1.0 天</Text>
                        </View>

                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>详细说明</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>宝宝发烧，送孩子去医院</Text>
                        </View>
                        <View style={{flexDirection: 'row',paddingTop:10,}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>图片</Text>
                            <View style={{flexWrap:'wrap',flex:8,paddingLeft:20,flexDirection: 'row',alignItems:'center',}}>
                                <Image style={{width: 80, height: 80,margin:5,}} source={require('../../../img/UserCenter/UserImage.jpeg')}/>
                                <Image style={{width: 80, height: 80,margin:5,}} source={require('../../../img/UserCenter/UserImage.jpeg')}/>
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
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>离职是不需要原因的，活在当下最重要，谁能预知明天在哪个星球</Text>
                        </View>
                    </View>
                </View>
      );
    }
  }

//晋级的UI
class PromotionUI extends React.Component {
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                    <View style={styles.applyFormDetail}>
                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>当前职级</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>LA</Text>
                        </View>

                        <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                            <Text style={{flex:3,textAlign:'right',fontSize:16}}>申请职级</Text>
                            <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>SM</Text>
                        </View>
                    </View>
                </View>
      );
    }
  }
//复效的UI
class RevivalUI extends React.Component {
    render() {
        return (
            <View style={{marginTop:10,paddingLeft:10,paddingRight:10,paddingBottom:10,paddingTop:10}}>
                <View style={styles.applyFormDetail}>
                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-end'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>复效人员</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>林心如</Text>
                    </View>

                    <View style={{flexDirection: 'row',paddingTop:10,alignItems:'flex-start'}}>
                        <Text style={{flex:3,textAlign:'right',fontSize:16}}>详细说明</Text>
                        <Text style={{flex:8,paddingLeft:20,color:'#575756',fontSize:18}}>之前的表现一直不错，感觉后期可以重用。所以申请复效</Text>
                    </View>
                </View>
            </View>
        );
    }
}


export class OaApplyFormDetail extends React.Component {

    constructor(props){
            super(props);
        let { params } = this.props.navigation.state;
        let type = 0;
        let status = '0';
        let baseStatus = '';

        if(params != undefined){
            if(params.hasOwnProperty('value')){
                let obj = params['value'];
                if(obj.hasOwnProperty('type')){
                    type = params.value.type;
                }
                if(obj.hasOwnProperty('status')){
                    status = params.value.status;
                }
                if(obj.hasOwnProperty('baseStatus')){
                    baseStatus = params.value.baseStatus;
                }
            }
        }
         
            this.state = {
            id:'',
            description:'',
            type:type,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode:'',
            createDatetime:'',
            status:status, //0 表示正在审批当中了；1表示结束；2驳回 3待签约。
            startTime:'',
            endTime:'',
            endDatetime:'',
            leaveType:'',
            leaveOff:'',
            title:'台湾还没有回归，心情不好',
            imcomeproveMonth:'',
            currentGrade:'',
            upGrade:'',
            showAgreedAndDismiss:baseStatus,
            showNatationAndAgreed:0,
            dataSource:[]
        }
        this.fetchData = this.fetchData.bind(this);
    }
    // 网络请求
    fetchData() {
        fetch('https://facebook.github.io/react-native/movies.json', {
        })
            .then((response) => response.json())
            .then((responseData) => {
                let data = this.state.dataSource.concat(responseData.movies);

                this.setState( {
                    dataSource:data
                })
            }).done();
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
                <View style={{backgroundColor:'#FFDD00',height:50,flexDirection: 'row',justifyContent:'flex-start',alignItems:'center',}}>
                    <View style={{flex:4,paddingLeft:10,}}>
                        <TouchableWithoutFeedback style={{width:30,height:30,}}  onPress={() => this.props.navigation.goBack()}>
                            <Image style={{width:11,height:22,}} source={require('../../../img/UserCenter/GoBackBlack.png')}/>
                        </TouchableWithoutFeedback>
                    </View>
                    <Text style={{flex:6,fontSize:22,color:'#000000'}}>
                    {this.state.type==0?'离职详情':
                        (this.state.type==1?'请假详情':
                            (this.state.type==2?'晋升详情':
                                (this.state.type==3?'复效详情':'详情')))}
                     </Text>
                </View>

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

                {
                    (this.state.type==0) && (
                        <ResignUI title={this.state.title}></ResignUI>
                    )
                }
                
                {
                    (this.state.type==1) && (
                        <LeaveUI></LeaveUI>
                    )
                }
                
                {
                    (this.state.type==2) && (
                        <PromotionUI></PromotionUI>
                    )
                }
                {
                    (this.state.type==3) && (
                        <RevivalUI></RevivalUI>
                    )
                }
                <View style={styles.process}>
                    <View style={{flexDirection: 'row',alignItems:'center',}}>
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
                        <View style={{flex:8.3,}}>
                            <Text style={{}}>提交申请</Text>
                        </View>
                    </View>
                </View>
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